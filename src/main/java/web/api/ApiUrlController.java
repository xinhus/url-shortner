package web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import url.cases.UrlCases;
import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.mysql.UrlRepositoryUsingMysql;

@RestController
@RequestMapping("api/url")
public class ApiUrlController {

	@RequestMapping(method=RequestMethod.POST, value="")
	public UrlEntity createUrl(@RequestBody Url url) {
		UrlCases cases = new UrlCases(new UrlRepositoryUsingMysql());
		try {
			return cases.shortUrl(url.getUrl());
		} catch (UnableToSaveUrlException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					"Could not save, please try again"
			);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{shortUrl}")
	public UrlEntity getUrlByShortUrl(@PathVariable("shortUrl") String shortUrl) {
		UrlCases cases = new UrlCases(new UrlRepositoryUsingMysql());
		try {
			return cases.findUrlByShortUrl(shortUrl);
		} catch (UrlNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Url not found"
			);
		}
	}
}
