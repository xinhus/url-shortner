package url.repository.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.UrlRepository;

public class UrlRepositoryUsingMysql implements UrlRepository {
	
	public UrlEntity save(UrlEntity entityToSave) throws UnableToSaveUrlException {
		Connection repository = MysqlConnection.getConnection();
		try (PreparedStatement statement = repository.prepareStatement("INSERT INTO url(short_url,original_url) VALUES(?, ?)")) {	
			statement.setString(1, entityToSave.getShortUrl());
			statement.setString(2, entityToSave.getOriginalUrl());
			int rows = statement.executeUpdate();
			if (rows == 0) {
				throw new UnableToSaveUrlException();
			}
			return entityToSave;
		} catch (SQLException e) {
			throw new UnableToSaveUrlException();
		} finally {
			try {
				repository.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UrlEntity getUrlByShortUrl(String shortUrl) throws UrlNotFoundException {
		Connection repository = MysqlConnection.getConnection();
		try (PreparedStatement statement = repository.prepareStatement("SELECT original_url FROM url WHERE short_url = ? ")) {	
			statement.setString(1, shortUrl);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					String originalUrl = result.getString(1);
					return new UrlEntity(shortUrl, originalUrl);
				}
				throw new UrlNotFoundException();
			}
		} catch (SQLException e) {
			throw new UrlNotFoundException();
		} finally {
			try {
				repository.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
