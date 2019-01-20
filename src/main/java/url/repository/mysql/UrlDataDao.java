package url.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UrlDataDao extends CrudRepository<UrlDataEntityInMysql, Long> {

	@Query(value = "SELECT " + 
			"new url.repository.mysql.UrlDataEntityInMysql(v.shortUrl, v.dataKey, v.dataValue, count(v.dataKey) as count) " + 
			"FROM UrlDataEntityInMysql v " + 
			"WHERE v.shortUrl = :shortUrl " +
			"GROUP BY v.dataKey, v.dataValue")
	List<UrlDataEntityInMysql> getDataConsilidatedByShortUrl(@Param("shortUrl") String shortUrl);
}
