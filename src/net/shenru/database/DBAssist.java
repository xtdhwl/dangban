package net.shenru.database;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class DBAssist {

	// 需要数据源
	public DBAssist( ) {
	}

	/**
	 * @since jdk1.2
	 * @param sql
	 * @param params
	 */
	public static void update(String sql, Object[] params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataUtil.getConn();
			stmt = conn.prepareStatement(sql);
			// 获取sql中的参数的个数
			ParameterMetaData md = stmt.getParameterMetaData();
			for (int i = 1; i <= md.getParameterCount(); i++) {
				stmt.setObject(i, params[i - 1]);
			}
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static Object query(String sql, Object[] params, ResultHandler handler) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataUtil.getConn();
			stmt = conn.prepareStatement(sql);
			// 获取sql中的参数的个数
			ParameterMetaData md = stmt.getParameterMetaData();
			for (int i = 1; i <= md.getParameterCount(); i++) {
				stmt.setObject(i, params[i - 1]);
			}
			rs = stmt.executeQuery();
			return handler.resultSetHandler(rs);// 策略设计模式
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
