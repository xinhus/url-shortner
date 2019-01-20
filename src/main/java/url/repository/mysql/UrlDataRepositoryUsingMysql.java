package url.repository.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import url.entity.UrlDataEntity;
import url.repository.UrlDataRepository;

public class UrlDataRepositoryUsingMysql implements UrlDataRepository {

	public boolean appendData(UrlDataEntity dataToAppend) {

		Connection repository = MysqlConnection.getConnection();
		try (PreparedStatement statement = repository.prepareStatement("INSERT INTO url_data(short_url,data_key,data_value) VALUES(?, ?, ?)")) {	
			statement.setString(1, dataToAppend.getShortUrl());
			statement.setString(2, dataToAppend.getKey());
			statement.setString(3, dataToAppend.getValue());
			int rows = statement.executeUpdate();
			return (rows > 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				repository.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<UrlDataEntity> retrieveData(String shortUrl) {
		Connection repository = MysqlConnection.getConnection();
		String query = "SELECT data_key, data_value, count(data_key) as count " + 
				"FROM url_data " + 
				"WHERE short_url = ? " + 
				"GROUP BY data_key, data_value";
		List<UrlDataEntity> dataToReturn = new ArrayList<UrlDataEntity>();
		try (PreparedStatement statement = repository.prepareStatement(query)) {	
			statement.setString(1, shortUrl);
			
			try (ResultSet result = statement.executeQuery()) {
				while(result.next()) {
					UrlDataEntity entity = new UrlDataEntity(
							shortUrl, 
							result.getString("data_key"), 
							result.getString("data_value"),
							result.getInt("count")
					);
					dataToReturn.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				repository.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataToReturn;
	}

}
