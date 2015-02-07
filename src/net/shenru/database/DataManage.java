package net.shenru.database;


public class DataManage {
	private static DataManage mInstace;

	private DataManage() {

	};

	public static DataManage getInstace() {
		if (mInstace == null) {
			mInstace = new DataManage();
		}
		return mInstace;
	}

	public void destroy() {
		throw new UnsupportedOperationException();
	}
}
