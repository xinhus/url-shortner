package url.repository.mysql;

import org.hibernate.exception.ConstraintViolationException;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.UrlRepository;


public class UrlRepositoryUsingMysql implements UrlRepository {

	private UrlDao urlDao;

	public UrlRepositoryUsingMysql(UrlDao urlDao) {
		this.urlDao = urlDao;
	}

	@Override
	public UrlEntity save(UrlEntity entityToSave) throws UnableToSaveUrlException {
		UrlEntityInMysql entity = new UrlEntityInMysql();
		entity.setOriginalUrl(entityToSave.getOriginalUrl());
		entity.setShortUrl(entityToSave.getShortUrl());
		try {
			urlDao.save(entity);
			return entityToSave;
		} catch (ConstraintViolationException dae) {
			throw new UnableToSaveUrlException();
		}
	}

	@Override
	public UrlEntity getUrlByShortUrl(String shortUrl) throws UrlNotFoundException {
		UrlEntityInMysql entityRetrieved = urlDao.findByShortUrl(shortUrl);
		if (entityRetrieved == null) {
			throw new UrlNotFoundException();
		}
		return new UrlEntity(entityRetrieved.getShortUrl(), entityRetrieved.getOriginalUrl());
	}

}
