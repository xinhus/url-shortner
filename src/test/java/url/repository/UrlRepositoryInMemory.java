package url.repository;

import java.util.ArrayList;
import java.util.List;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;

public class UrlRepositoryInMemory implements UrlRepository {

	List<UrlEntity> repository = new ArrayList<UrlEntity>();
	
	@Override
	public UrlEntity save(UrlEntity entityToBeSaved) throws UnableToSaveUrlException {
		UrlEntity entity = findUrlByShortUrl(entityToBeSaved.getShortUrl());
		if (entity == null) {
			repository.add(entityToBeSaved);
			return entityToBeSaved;
		}
		throw new UnableToSaveUrlException();		
	}

	@Override
	public UrlEntity getUrlByShortUrl(String shortUrl) throws UrlNotFoundException {
		UrlEntity entity = findUrlByShortUrl(shortUrl);
		if (entity == null) {
			throw new UrlNotFoundException();
		}
		return entity; 
	}

	private UrlEntity findUrlByShortUrl(String shortUrl) {
		for (UrlEntity urlEntity : repository) {
			if (urlEntity.getShortUrl().equals(shortUrl)) {
				return urlEntity;
			}
		}
		return null;
	}

}
