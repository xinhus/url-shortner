package url.entity;

public class UrlEntity {

	String shortUrl;
	String originalUrl;
	
	public UrlEntity(String shortUrlToSave, String origianlUrlToSave) {
		shortUrl = shortUrlToSave;
		originalUrl = origianlUrlToSave;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public String getOriginalUrl() {
		return originalUrl;
	}
}
