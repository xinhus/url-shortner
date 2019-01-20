package url.cases;

import java.util.List;

import url.entity.UrlDataEntity;
import url.repository.UrlDataRepository;

public class UrlDataCases {

	private UrlDataRepository repository;

	public UrlDataCases(UrlDataRepository reposiroty) {
		this.repository = reposiroty;
	}

	public void saveData(String shortUrl, String key, String value) {
		UrlDataEntity entity = new UrlDataEntity(shortUrl, key, value);
		repository.appendData(entity);
	}

	public List<UrlDataEntity> listData(String shortUrl) {
		return repository.retrieveData(shortUrl);
	}

}
