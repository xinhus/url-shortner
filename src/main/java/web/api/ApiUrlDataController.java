package web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import url.cases.UrlDataCases;
import url.entity.UrlDataEntity;
import url.repository.mysql.UrlDataDao;
import url.repository.mysql.UrlDataRepositoryUsingMysql;

@RestController
@RequestMapping("api/analytics")
public class ApiUrlDataController {

	@Autowired
	private UrlDataDao urlDataDao;
	
	@RequestMapping(method=RequestMethod.GET, value="/{shortUrl}")
	public List<UrlDataEntity> getUrlByShortUrl(@PathVariable("shortUrl") String shortUrl) {
		UrlDataCases urlData = new UrlDataCases(new UrlDataRepositoryUsingMysql(urlDataDao));
		return urlData.listData(shortUrl);
	}
}
