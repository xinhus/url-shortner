package url.repository.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Driver;

import url.entity.UrlEntity;
import url.exception.UnableToSaveUrlException;
import url.exception.UrlNotFoundException;
import url.repository.UrlRepository;

public class UrlRepositoryUsingMysql implements UrlRepository {

	private Connection getConnection() {
		try {
			DriverManager.registerDriver(
			        (Driver)Class.forName( "com.mysql.jdbc.Driver" ).newInstance()
			);
	        DriverManager.setLoginTimeout(10);
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/url" , "root", "password");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public UrlEntity save(UrlEntity entityToSave) throws UnableToSaveUrlException {
		Connection repository = getConnection();
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
		Connection repository = getConnection();
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
