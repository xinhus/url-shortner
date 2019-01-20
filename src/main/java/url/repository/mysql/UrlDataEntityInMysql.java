package url.repository.mysql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "url_data", indexes = {@Index(columnList = "shortUrl,createdAt, dataKey")})
public class UrlDataEntityInMysql {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(length = 50)
	private String shortUrl;

	@NotNull
	private String dataKey;
	
	@NotNull
	private String dataValue;
	
	@Transient
	private int count;
	
	@NotNull
	@Type(type="timestamp")
	@ColumnDefault("now()")
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
	
	public UrlDataEntityInMysql() {
	}

	public UrlDataEntityInMysql(String shortUrl, String dataKey, String dataValue, long count) {
		setShortUrl(shortUrl);
		setDataKey(dataKey);
		setDataValue(dataValue);
		this.count = (int) (long) count;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	
	public int getCount() {
		return count;
	}

}
