package com.dbquery.factory;

import com.dbquery.dal.AccessDBInfo;
import com.dbquery.interfaces.DBBasicInfo;

public class AccessFactory implements DatabaseFactory{

	@Override
	public DBBasicInfo databaseInfo() {
		// TODO Auto-generated method stub
		return new AccessDBInfo();
	}

	@Override
	public DBBasicInfo databaseInfo(String databaseName) {
		// TODO Auto-generated method stub
		return new AccessDBInfo(databaseName, "127.0.0.1");
	}

	@Override
	public DBBasicInfo databaseInfo(String databaseName, int port) {
		// TODO Auto-generated method stub
		DBBasicInfo info = databaseInfo(databaseName);
		info.setPort(port);
		return info;
	}

	@Override
	public DBBasicInfo databaseInfo(String url, String databaseName, int port) {
		DBBasicInfo info = databaseInfo(databaseName, port);
		info.setMachineAddress(url);
		return info;
	}

}
