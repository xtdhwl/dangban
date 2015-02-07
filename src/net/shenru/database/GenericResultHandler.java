package net.shenru.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenericResultHandler implements ResultHandler {

	@Override
	public void connectionHandler(Connection conn) {

	}

	@Override
	public void statementHandler(PreparedStatement sta) {

	}

	@Override
	public Object resultSetHandler(ResultSet res) {
		return null;
	}

}
