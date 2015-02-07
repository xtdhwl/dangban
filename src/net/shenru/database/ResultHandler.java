package net.shenru.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ResultHandler {
	void connectionHandler(Connection conn);
	void statementHandler(PreparedStatement sta);
	Object resultSetHandler(ResultSet res);
}
