package com.imp.dao;


import com.dbquery.dal.DBConnection;
import com.dbquery.factory.AccessFactory;
import com.dbquery.factory.DatabaseFactory;
import com.dbquery.factory.MSSQLFactory;
import com.dbquery.interfaces.AbstractQueryObject;
import com.dbquery.interfaces.DBBasicInfo;

import java.io.*;
import java.util.Properties;


public abstract class QueryObject extends AbstractQueryObject {

	public  String path = null;
	public  String databaseName = null;
	public  String userName = null;
	public  String password = null;
	public  Integer port = 30000;
	@Override
	public void setupDatabase() {

		Properties prop = new Properties();

		InputStreamReader in = null;
		try {
			in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
			prop.load(in);

			path  = prop.getProperty("access.path").trim();
			databaseName = prop.getProperty("access.databaseName").trim();
			userName     = prop.getProperty("access.userName").trim();
			password     = prop.getProperty("access.password").trim();
			
			DatabaseFactory factory = new AccessFactory();
			DBBasicInfo access = factory.databaseInfo(databaseName);
			DBConnection connection = new DBConnection();
			access.setPath(path);
			access.setUsername(userName);
			access.setPassword(password);
			connection.setDbinfo(access);
			dbManager = connection;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
