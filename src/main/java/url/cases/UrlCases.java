package url.cases;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.UrlRepository;

public class UrlCases {

	private UrlRepository repository;

	public UrlCases(UrlRepository repo) {
		repository = repo;
	}
	
	public UrlEntity shortUrl(String originalUrl) throws UnableToSaveUrlException {
		int maxInterations = 30;
		for (int i = 0; i < maxInterations; i++) {			
			UrlEntity entity = generateEntity(originalUrl);
			if (saveEntity(entity)) {
				return entity;
			}
		}
		throw new UnableToSaveUrlException();
	}

	private UrlEntity generateEntity(String originalUrl) {
		String shortUrlToSave = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 6);
		UrlEntity entity = new UrlEntity(shortUrlToSave, originalUrl);
		return entity;
	}

	private boolean saveEntity(UrlEntity entity) {
		try {
			repository.save(entity);
			return true;
		} catch (UnableToSaveUrlException e) {
			return false;
		}
	}
	
	public UrlEntity findUrlByShortUrl(String shortUrl) throws UrlNotFoundException {
		return repository.getUrlByShortUrl(shortUrl);
	}
}
