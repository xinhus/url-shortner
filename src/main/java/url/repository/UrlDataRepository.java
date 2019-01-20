package url.repository;

import java.util.List;

import url.entity.UrlDataEntity;

public interface UrlDataRepository {
	
	public boolean appendData(UrlDataEntity dataToAppend);
	
	public List<UrlDataEntity> retrieveData(String shortUrl);

}
