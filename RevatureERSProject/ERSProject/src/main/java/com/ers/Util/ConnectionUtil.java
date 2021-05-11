package com.ers.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	public ConnectionUtil() {
		super();
	}

	private static Connection connection;

	public static Connection getConnection() throws IOException, SQLException, Exception {
		Properties p = new Properties();
		InputStream resourceStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("connection.properties");
		p.load(resourceStream);
		Class.forName("org.postgresql.Driver");
		String url = p.getProperty("url");
		System.out.println(url+"url found from properties");
		String username = p.getProperty("user");
		String password = p.getProperty("pwd");
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connection done");
		}
		return connection;
	}

}
