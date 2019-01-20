package url.cases;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import url.entity.UrlDataEntity;
import url.repository.UrlDataRepositoryInMemory;

public class UrlDataCasesTest {

	@Test
	public void testUrlDataCases_shouldRetrieveSingleDataConsolidated() {
		UrlDataCases repository = new UrlDataCases(new UrlDataRepositoryInMemory());
		repository.saveData("short-url", "key info", "key value");
		repository.saveData("short-url", "key info", "key value");
		List<UrlDataEntity> list = repository.listData("short-url");
		UrlDataEntity entityFromMemory = list.get(0);
		Assert.assertEquals("short-url", entityFromMemory.getShortUrl());
		Assert.assertEquals("key info", entityFromMemory.getKey());
		Assert.assertEquals("key value", entityFromMemory.getValue());
		Assert.assertEquals(2, entityFromMemory.getCount());
	}

	@Test
	public void testUrlDataCases_shouldRetrieveOnlyDataFromSpecificShortUrl() {
		UrlDataCases repository = new UrlDataCases(new UrlDataRepositoryInMemory());
		repository.saveData("short-url", "key info", "key value");
		repository.saveData("another-url", "key info", "key value");
		List<UrlDataEntity> list = repository.listData("short-url");
		UrlDataEntity entityFromMemory = list.get(0);
		Assert.assertEquals("short-url", entityFromMemory.getShortUrl());
		Assert.assertEquals("key info", entityFromMemory.getKey());
		Assert.assertEquals("key value", entityFromMemory.getValue());
		Assert.assertEquals(1, entityFromMemory.getCount());
	}

	@Test
	public void testUrlDataCases_shouldRetrieveAllDataFromSpecificShortUrl() {
		UrlDataCases repository = new UrlDataCases(new UrlDataRepositoryInMemory());
		repository.saveData("short-url", "key info", "key value");
		repository.saveData("short-url", "another key info", "another key value");
		List<UrlDataEntity> list = repository.listData("short-url");
		UrlDataEntity entityFromMemory = list.get(0);
		Assert.assertEquals("short-url", entityFromMemory.getShortUrl());
		Assert.assertEquals("key info", entityFromMemory.getKey());
		Assert.assertEquals("key value", entityFromMemory.getValue());
		Assert.assertEquals(1, entityFromMemory.getCount());
		
		entityFromMemory = list.get(1);
		Assert.assertEquals("short-url", entityFromMemory.getShortUrl());
		Assert.assertEquals("another key info", entityFromMemory.getKey());
		Assert.assertEquals("another key value", entityFromMemory.getValue());
		Assert.assertEquals(1, entityFromMemory.getCount());
	}

}
