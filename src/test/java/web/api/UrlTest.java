package web.api;

import org.junit.Assert;
import org.junit.Test;

public class UrlTest {

	@Test
	public void testUrlApiEntity() {
		Url u = new Url();
		u.setUrl("My custom Url");
		Assert.assertEquals("My custom Url", u.getUrl());
	}

}
