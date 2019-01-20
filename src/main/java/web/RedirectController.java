package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import url.cases.UrlCases;
import url.cases.UrlDataCases;
import url.entity.UrlEntity;
import url.exception.UrlNotFoundException;
import url.repository.mysql.UrlDao;
import url.repository.mysql.UrlDataDao;
import url.repository.mysql.UrlDataRepositoryUsingMysql;
import url.repository.mysql.UrlRepositoryUsingMysql;

@RestController
public class RedirectController {

	@Autowired
	private UrlDao urlDao;

	@Autowired
	private UrlDataDao urlDataDao;

	@RequestMapping(method=RequestMethod.GET, value="/{shortUrl:[a-zA-Z0-9]{6}}")
	public RedirectView getUrlByShortUrl(
		@PathVariable("shortUrl") final String shortUrl,
		@RequestHeader(value="referer", defaultValue="unknown") final String referer,
		@RequestHeader(value="User-Agent", defaultValue="unknown") final String userAgent
	) {
		UrlCases cases = new UrlCases(new UrlRepositoryUsingMysql(urlDao));
		UrlDataCases urlData = new UrlDataCases(new UrlDataRepositoryUsingMysql(urlDataDao));
		try {
			UrlEntity url = cases.findUrlByShortUrl(shortUrl);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(url.getOriginalUrl());
			urlData.saveData(shortUrl, "access", "new visitor");
			urlData.saveData(shortUrl, "referer", referer);
			urlData.saveData(shortUrl, "UserAgent", userAgent);
		    return redirectView;
		} catch (UrlNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Url not found"
			);
		}
	}
}
