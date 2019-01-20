package url.repository;

import java.util.ArrayList;
import java.util.List;

import url.entity.UrlDataEntity;

public class UrlDataRepositoryInMemory implements UrlDataRepository {

	List<UrlDataEntity> reposiroty = new ArrayList<UrlDataEntity>();
			
	public boolean appendData(UrlDataEntity dataToAppend) {
		reposiroty.add(dataToAppend);
		return true;
	}

	public List<UrlDataEntity> retrieveData(String shortUrl) {
		List<UrlDataEntity> dataToReturn = new ArrayList<UrlDataEntity>();

		
		for (UrlDataEntity urlDataEntity : reposiroty) {
			if (!urlDataEntity.getShortUrl().equals(shortUrl)) {
				continue;
			}
			
			boolean shouldAdd = true;
			for (UrlDataEntity urlDataToReturn : dataToReturn) {
				if (
					urlDataToReturn.getShortUrl().equals(urlDataEntity.getShortUrl()) &&
					urlDataToReturn.getKey().equals(urlDataEntity.getKey()) &&
					urlDataToReturn.getValue().equals(urlDataEntity.getValue())
				) {
					urlDataToReturn.incrementCount();
					shouldAdd = false;
				}
				
			}
			if (shouldAdd) {				
				urlDataEntity.incrementCount();
				dataToReturn.add(urlDataEntity);
			}
		}
		return dataToReturn;
	}

}
