package url.entity;

import org.junit.Test;
import org.junit.Assert;

public class UrlEntityTest {

	@Test
	public void testUrlEntity() {
		String shortUrl = "myshorturl";
		String originalUrl = "http://www.google.com/";
		UrlEntity entity = new UrlEntity(shortUrl, originalUrl);
		Assert.assertEquals(shortUrl, entity.getShortUrl());
	}
}
