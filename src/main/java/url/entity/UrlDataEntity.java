package url.entity;

public class UrlDataEntity {
	
	private String shortUrl;
	private String key;
	private String value;
	private int count = 0;
	
	public UrlDataEntity(String shortUrl, String key, String value) {
		setInitialObject(shortUrl, key, value);
	}
	
	public UrlDataEntity(String shortUrl, String key, String value, int count) {
		setInitialObject(shortUrl, key, value);
		this.count = count;
	}

	private void setInitialObject(String shortUrl, String key, String value) {
		this.shortUrl = shortUrl;
		this.key = key;
		this.value = value;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}

}
