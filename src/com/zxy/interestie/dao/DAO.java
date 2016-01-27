package com.zxy.interestie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zxy.interestie.bean.User;
import com.zxy.interestie.util.DBUtil;

public class DAO {

	private static Connection conn;

	public DAO() {
		conn = DBUtil.connect();
	}

	public boolean insert(User user) {

		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		String tags = user.getTags();
		
		// String url = user.get();

		String sql = "insert into USER(username,password,email,tags) VALUES (?,?,?,?)";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, email);
			st.setString(4, tags);

			st.execute();
			System.out.println(username + "插入成功");
			st.close();
             return true;
		} catch (SQLException e) {
			System.out.println("插入失败");
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String login(String name, String pwd) {
		String sql = "select * from USER where username ='" + name + "'";

		String flag = null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				System.out.println("查询操作：找到用户" + name);
				String user = resultSet.getString(1);
				String password = resultSet.getString(2);
				String tags = resultSet.getString(4);
				if (password.equals(pwd)) {
					flag = user;

				} else {
					flag = "2";

				}
				System.out.println("password is :" + password);
			} else {
				System.out.println("查询用户名:" + name + "不存在");
				flag = "3";
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}

	public String getTags(String name) {
		String sql = "select * from USER where username ='" + name + "'";

		String flag = null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				System.out.println("找到该用户名");

				String tags = resultSet.getString(4);
				System.out.println("该用户的标签有：" + tags);

				flag = tags;

			}else{
				flag="";
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}

	public String getEmail(String email) {

		String sql = "select * from USER where email ='" + email + "'";

		String flag = null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				System.out.println("找到该email");

				flag = "2";

			}else{
				flag="";
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}

	public boolean update(String user, String tags) {
		// TODO Auto-generated method stub
		String sql = "update USER set tags ='" + tags + "' where username='" + user + "'";
		Boolean rs = false;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			rs = st.execute();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("更新失败！");
		}

		return rs;

	}
}
