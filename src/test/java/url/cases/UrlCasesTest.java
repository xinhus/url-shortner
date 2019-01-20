package url.cases;


import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.UrlRepositoryInMemory;

public class UrlCasesTest {

	private UrlCases cases;

	@Before
	public void setUp() {
		cases = new UrlCases(new UrlRepositoryInMemory());
	}
	
	@Test
	public void testUrlCase() {
		String originalUrl = "https://www.mycustomurl.com/";
		UrlEntity entity = saveUrlAndAssertOriginalUrl(originalUrl);
		Assert.assertEquals(originalUrl, entity.getOriginalUrl());
	}

	@Test
	public void testUrlCase_SaveSameOriginalUrlTwice_shouldGenerateTwoShortUrl() {
		String originalUrl = "https://www.mycustomurl.com/";
		UrlEntity entity_1 = saveUrlAndAssertOriginalUrl(originalUrl);
		UrlEntity entity_2 = saveUrlAndAssertOriginalUrl(originalUrl);
		Assert.assertNotEquals(entity_1.getShortUrl(), entity_2.getShortUrl());
	}
	
	@Test
	public void testUrlCase_SaveUrl_shouldReturnWhenSearchedByShortUrl() {
		String originalUrl = "https://www.mycustomurl.com/";
		UrlEntity entity = saveUrlAndAssertOriginalUrl(originalUrl);
		try {
			UrlEntity entityRetrieved = cases.findUrlByShortUrl(entity.getShortUrl());
			Assert.assertEquals(originalUrl, entityRetrieved.getOriginalUrl());
		} catch (UrlNotFoundException e) {
			Assert.fail("Url not found");
		}
	}
	
	@Test
	public void testUrl_trySaveWithoutHttp_shouldPrefixWithHttp() {
		String originalUrlWithoutHttp = "www.mycustomurl.com/";
		UrlEntity entity = saveUrlAndAssertOriginalUrl(originalUrlWithoutHttp);
		Assert.assertEquals("http://www.mycustomurl.com/", entity.getOriginalUrl());
	}
	
	private UrlEntity saveUrlAndAssertOriginalUrl(String originalUrl) {
		try {
			UrlEntity entity = cases.shortUrl(originalUrl);
			return entity;
		} catch (UnableToSaveUrlException e) {
			Assert.fail("Unable to save Url");
		}
		return null;
	}
}
