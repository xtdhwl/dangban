package net.shenru.database;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLDatabase {

	public boolean executeUpdate(String sql, Object obj[], ResultHandler handler) {
		Connection conn = null;
		PreparedStatement stmt = null;
		// ResultSet res = null;

		try {
			System.out.println("executeUpdate sql:" + sql);
			// open();
			conn = DataUtil.getConn();
			if (handler != null) {
				handler.connectionHandler(conn);
			}
			stmt = conn.prepareStatement(sql);

			ParameterMetaData md = stmt.getParameterMetaData();
			for (int i = 1; i <= md.getParameterCount(); i++) {
				stmt.setObject(i, obj[i - 1]);
			}
			stmt.executeUpdate();

			if (handler != null) {
				handler.statementHandler(stmt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DataUtil.release(null, stmt, conn);
		}
		return true;
	}

	/**
	 * 执行更新语句
	 * 
	 * @param sql
	 * @param obj
	 * @return true执行成功,false执行失败
	 */
	public boolean executeUpdate(String sql, Object obj[]) {
		return executeUpdate(sql, obj, null);
	}

	public Object executeQuery(String sql, Object obj[], ResultHandler handler) {
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			System.out.println("executeQuery sql:" + sql);
			// open();
			conn = DataUtil.getConn();
			if (handler != null) {
				handler.connectionHandler(conn);
			}
			sta = conn.prepareStatement(sql);
			
			ParameterMetaData paraMetaData = sta.getParameterMetaData();
			for (int i = 1, count = paraMetaData.getParameterCount(); i <= count; i++) {
				sta.setObject(i, obj[i - 1]);
			}
			res = sta.executeQuery();

			if (handler != null) {
				handler.statementHandler(sta);
			}

			if (handler != null) {
				return handler.resultSetHandler(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataUtil.release(res, sta, conn);
		}
		return null;
	}

}
