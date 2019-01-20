package url.entity;


import org.junit.Assert;
import org.junit.Test;

public class UrlDataEntityTest {

	@Test
	public void testUrlDataEntityWithoutCount() {
		UrlDataEntity entity = new UrlDataEntity("foobar", "my custom key", "my custom value");
		Assert.assertEquals("foobar", entity.getShortUrl());
		Assert.assertEquals("my custom key", entity.getKey());
		Assert.assertEquals("my custom value", entity.getValue());
		Assert.assertEquals(0, entity.getCount());
	}

	@Test
	public void testUrlDataEntityWithCount() {
		UrlDataEntity entity = new UrlDataEntity("foobar", "my second custom key", "my second custom value", 100);
		Assert.assertEquals("foobar", entity.getShortUrl());
		Assert.assertEquals("my second custom key", entity.getKey());
		Assert.assertEquals("my second custom value", entity.getValue());
		Assert.assertEquals(100, entity.getCount());
	}

}
