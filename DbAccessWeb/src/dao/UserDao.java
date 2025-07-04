package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {

	private static final String SQL_SELECT_ID_AND_PASS = "SELECT user_id, user_name, password FROM users WHERE user_id = ? AND password = ?";

	private Connection connection;

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public User findByIdAndPass(String id, String pass) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_AND_PASS)) {
			stmt.setString(1, id);
			stmt.setString(2, pass);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("password"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
