package url.repository.mysql;

import java.util.ArrayList;
import java.util.List;

import url.entity.UrlDataEntity;
import url.repository.UrlDataRepository;


public class UrlDataRepositoryUsingMysql implements UrlDataRepository {

	private UrlDataDao urlDataDao;

	public UrlDataRepositoryUsingMysql(UrlDataDao urlDataDao) {
		this.urlDataDao = urlDataDao;
	}

	@Override
	public boolean appendData(UrlDataEntity dataToAppend) {
		UrlDataEntityInMysql entity = new UrlDataEntityInMysql();
		entity.setShortUrl(dataToAppend.getShortUrl());
		entity.setDataKey(dataToAppend.getKey());
		entity.setDataValue(dataToAppend.getValue());
		entity = urlDataDao.save(entity);
		return (entity == null) ? false : true;
	}

	@Override
	public List<UrlDataEntity> retrieveData(String shortUrl) {
		List<UrlDataEntityInMysql> memory = urlDataDao.getDataConsilidatedByShortUrl(shortUrl);
		List<UrlDataEntity> dataToReturn = new ArrayList<UrlDataEntity>();
		for (UrlDataEntityInMysql tempData : memory) {
			UrlDataEntity urlDataEntity = new UrlDataEntity(
				tempData.getShortUrl(),
				tempData.getDataKey(),
				tempData.getDataValue(),
				tempData.getCount()
			);
			dataToReturn.add(urlDataEntity);
		}
		return dataToReturn;
	}

}
