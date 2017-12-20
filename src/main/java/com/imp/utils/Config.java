package com.imp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config{
	
	private static Config config = null;

	public String MACHINE_ID;
	public String KEYWORD;
	public String DATABASE_URL;
	public String DATABASE_NAME;
	public String DATABASE_USER;
	public String DATABASE_PASSWORD;
	public String WEBSERVICE_URL;
	public Integer INTERVAL_RECORD;
	//定位缓存列表扫描频率
	public Integer LOCATION_SCAN;

	

	public Config() {
		// TODO Auto-generated constructor stub
		InputStreamReader in = null;
		Properties prop = new Properties();   
		try {
			in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
			prop.load(in);
			MACHINE_ID  = prop.getProperty("machineId").trim().replaceAll("\r|\n", "");
			KEYWORD  = prop.getProperty("keyword").trim().replaceAll("\r|\n", "");
			DATABASE_URL  = prop.getProperty("access.databaseUrl").trim().replaceAll("\r|\n", "");
			DATABASE_NAME  = prop.getProperty("access.databaseName").trim().replaceAll("\r|\n", "");
			DATABASE_USER  = prop.getProperty("access.userName").trim().replaceAll("\r|\n", "");
			DATABASE_PASSWORD  = prop.getProperty("access.password").trim().replaceAll("\r|\n", "");
			WEBSERVICE_URL  = prop.getProperty("webService.url").trim().replaceAll("\r|\n", "");
			INTERVAL_RECORD  = Integer.parseInt(prop.getProperty("interval.record").trim().replaceAll("\r|\n", ""));   
			LOCATION_SCAN  = Integer.parseInt(prop.getProperty("location.scan").trim().replaceAll("\r|\n", ""));

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
	
	public static Config getInstance(){
		if (config == null) {
			config = new Config();
		}
		return config;
	}

}
