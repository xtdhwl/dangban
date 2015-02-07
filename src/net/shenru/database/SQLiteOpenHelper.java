package net.shenru.database;

public class SQLiteOpenHelper {

	// 初始化数据库
	public void init() {
	}

	public void onCreate(SQLDatabase db) {
		String note_sql = "create table if not exists note(" + "id int primary key auto_increment,"
				+ "title varchar(200)," + "content varchar(20000)," + "filepaths varchar(200));";

		String user_sql = "create table if not exists user(id bigint primary key auto_increment,uid int not null,name varchar(16) not null,password varchar(16) not null)";
		db.executeUpdate(note_sql, null);
		db.executeUpdate(user_sql, null);
	}

	public void onUpgrade(SQLDatabase db, int oldVersion, int newVersion) {

	}

}
