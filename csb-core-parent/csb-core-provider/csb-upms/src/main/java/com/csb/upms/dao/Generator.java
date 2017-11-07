package com.csb.upms.dao;

import com.csb.common.util.MybatisGeneratorUtil;
import com.csb.common.util.PropertiesFileUtil;

/**
 * 代码生成类
 * Created by administrator on 2017/1/10.
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String MODULE_PREFIX_NAME = "upms";

	private static String TABLE_PREFIX_NAME = "t_csb_";
	// 模板路径
	private static String VM_PATH = "csb-common/src/main/resources/template/generatorConfig.vm";
	// 项目名称
	private static String PROJECT_NAME = "csb";
	// 数据库名称
	private static String DATABASE_NAME =  PropertiesFileUtil.getInstance("generator").get("generator.jdbc.database");
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, TABLE_PREFIX_NAME, MODULE_PREFIX_NAME,PROJECT_NAME,DATABASE_NAME);
	}

}
