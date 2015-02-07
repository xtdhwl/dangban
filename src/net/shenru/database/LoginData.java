package net.shenru.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.shenru.mould.User;

public class LoginData {

	/**
	 * 添加用户
	 * 
	 * @param name
	 * @param passwrod
	 * @param uid
	 */
	public void addUser(int uid, String name, String password) {
		// insert into user(uid,name,password) value(1, "shenru",
		// "123456");
		String sql = "insert into user(uid, name,password) value(?, ?,?);";

		SQLDatabase dataBase = new SQLDatabase();
		Object obj[] = { uid, name, password };

		dataBase.executeUpdate(sql, obj, new GenericResultHandler() {
			@Override
			public void statementHandler(PreparedStatement sta) {
//				try {
//					ResultSet generatedKeys = sta.getGeneratedKeys();
//					int generatedId = generatedKeys.getInt(1);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}
		});
	}

	/**
	 * 获取用户
	 * 
	 * @param name
	 * @param passwrod
	 */
	public User getUser(String name, String password) {
		// select uid,name,password from user where name="shenru"
		// and password="123456";
		if (name == null || password == null || "".equals(name) || "".equals(password)) {
			return null;
		}

		String sql = "select uid,name,password from user where name=? and password=?";

		SQLDatabase dataBase = new SQLDatabase();
		Object obj[] = { name, password };

		User resultUser = (User) dataBase.executeQuery(sql, obj, new GenericResultHandler() {

			@Override
			public Object resultSetHandler(ResultSet res) {
				try {
					User user = null;
					if (res.next()) {
						user = new User();
						int uid = res.getInt("uid");
						String name = res.getString("name");
						String password = res.getString("password");
						user.setUid(uid);
						user.setName(name);
						user.setPassword(password);
					}
					return user;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		return resultUser;
	}
}
