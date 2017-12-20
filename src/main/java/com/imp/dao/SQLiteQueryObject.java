package com.imp.dao;


import com.dbquery.dal.DBConnection;
import com.dbquery.factory.AccessFactory;
import com.dbquery.factory.DatabaseFactory;
import com.dbquery.factory.SQLiteFactory;
import com.dbquery.interfaces.AbstractQueryObject;
import com.dbquery.interfaces.DBBasicInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


public abstract class SQLiteQueryObject extends AbstractQueryObject {

	public  String path = null;
	public  String databaseName = null;
	public  String userName = null;
	public  String password = null;

	@Override
	public void setupDatabase() {
		try {
			userName = "root";
			password = "123456Az";
			DatabaseFactory factory = new SQLiteFactory();
			DBBasicInfo sqlite = factory.databaseInfo();
			DBConnection connection = new DBConnection();
			sqlite.setUsername(userName);
			sqlite.setPassword(password);
			connection.setDbinfo(sqlite);
			dbManager = connection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
