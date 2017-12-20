package com.dbquery.dal;

import com.dbquery.interfaces.DBBasicInfo;
/**
 * SQLite数据库信息
 * @author i_it
 * @version 1.0
 */
public class SQLiteDBInfo extends DBBasicInfo{

	/**
	 * 构造函数，默认使用本机数据库和默认端口
	 */
	public SQLiteDBInfo() {
		this.driver = "org.sqlite.JDBC";
		this.url = "jdbc:sqlite";// 默认URL地址
	}

	/**
	 * 创建数据库并指定数据库名和IP地址
	 * @param databaseName 数据库名
	 * @param machineAddr IP地址
	 */
	public SQLiteDBInfo(String databaseName, String machineAddr) {
		this();
		this.databaseName = databaseName;
		this.machineAddress = machineAddr;
	}
	
	/* (non-Javadoc)
	 * @see com.databasegenerate.dal.DBBasicInfo#getUrl()
	 */
	@Override
	public String getUrl() {
		return this.databaseName != null ? this.url + ":" + this.databaseName : this.url + ":sqlite.db";
	}

}
