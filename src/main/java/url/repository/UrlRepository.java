package url.repository;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;

public interface UrlRepository {

	public UrlEntity save(UrlEntity entityToSave) throws UnableToSaveUrlException;

	public UrlEntity getUrlByShortUrl(String shortUrl) throws UrlNotFoundException;
	
}
