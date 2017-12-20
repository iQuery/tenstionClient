package com.dbquery.dal;

import com.dbquery.interfaces.DBBasicInfo;

/**
 * Access数据库信息
 * @author i_it
 * @version 1.0
 */
public class AccessDBInfo extends DBBasicInfo{

	/**
	 * 构造函数，默认使用本机数据库和默认端口
	 */
	public AccessDBInfo() {
		this.driver = "com.hxtt.sql.access.AccessDriver";
		// 默认URL地址
		this.url = "jdbc:Access";
	}

	/**
	 * 创建数据库并指定数据库名和IP地址
	 * @param databaseName 数据库名
	 * @param path 数据库路径
	 */
	public AccessDBInfo(String databaseName, String path) {
		this();
		this.databaseName = databaseName;
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see com.databasegenerate.dal.DBBasicInfo#getUrl()
	 */
	@Override
	public String getUrl() {
		if (this.databaseName == null || this.databaseName.equals("")) {
			this.setDatabaseName("accessTemp.accdb");
		}
		if (this.getPath() == null){
			// 未指定数据库路径则使用默认本机地址
			return this.url + ":" + this.databaseName;
		}
		return this.url + ":/" + this.path + "/" + this.databaseName;
	}

}
