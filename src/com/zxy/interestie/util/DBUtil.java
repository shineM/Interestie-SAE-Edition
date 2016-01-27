package com.zxy.interestie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	private static String username = "root";
	private static String password = "123456";
//	private static String url = "jdbc:mysql://SAE_MYSQL_HOST_M:SAE_MYSQL_PORT/SAE_MYSQL_DB?useUnicode=true&characterEncoding=utf-8";
//	private static String username = "SAE_MYSQL_USER";
//	private static String password = "SAE_MYSQL_PASS";

	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			System.out.println("DB驱动加载成功");
		} catch (Exception e) {
			System.out.println("DB驱动加载异常");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("DB CONNECT SUCCESS");
		} catch (SQLException e) {
			System.out.println("DB CONNECT ERROR");
			e.printStackTrace();
		}
		return con;

	}
}
