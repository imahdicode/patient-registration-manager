package com.chp1_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class GetConnection {

	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/db8";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String pass = System.getenv("DB_PASS");

		// Fallback to config.properties if env vars are not set
		if (url == null || user == null || pass == null) {
			Properties props = new Properties();
			try (FileInputStream fis = new FileInputStream("config.properties")) {
				props.load(fis);
				if (url == null)
					url = props.getProperty("db.url");
				if (user == null)
					user = props.getProperty("db.user");
				if (pass == null)
					pass = props.getProperty("db.password");
			} catch (IOException e) {
				// config.properties not found or unreadable - it's okay, we'll use defaults or
				// environment vars
			}
		}

		if (url == null)
			url = DEFAULT_URL;
		if (user == null)
			user = "root";
		if (pass == null)
			pass = "";

		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
