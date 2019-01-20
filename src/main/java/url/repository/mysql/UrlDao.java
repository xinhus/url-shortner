package url.repository.mysql;

import org.springframework.data.repository.CrudRepository;

public interface UrlDao extends CrudRepository<UrlEntityInMysql, Long> {

	public UrlEntityInMysql findByShortUrl(String shortUrl);

}
