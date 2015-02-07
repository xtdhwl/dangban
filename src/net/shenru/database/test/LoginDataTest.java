package net.shenru.database.test;

import org.junit.Test;

import net.shenru.database.DBAssist;
import net.shenru.database.LoginData;

public class LoginDataTest {
	@Test
	public void addUser() {
		int uid = 0;
		String name = "xtdhwl";
		String password = "123456";

		LoginData data = new LoginData();
		data.addUser(uid, name, password);
	}
	@Test
	public void addUser2() {
		int uid = 0;
		String name = "xtdhwl";
		String password = "123456";
		
		String sql = "insert into user(uid, name,password) value(?, ?,?);";
		Object params[] = {uid,name,password};
		DBAssist.update(sql, params);
	}
}
