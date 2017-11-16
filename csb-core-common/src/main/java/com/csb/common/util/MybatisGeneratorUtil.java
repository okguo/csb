package com.csb.common.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 * Created by administrator on 2017/1/10.
 */
public class MybatisGeneratorUtil {

	// 模板路径
	private static String VM_PATH = "csb-core-common/src/main/resources/template/generatorConfig.vm";
	//Service模板路径
	private static String SERVICE_VM_PATH = "csb-core-common/src/main/resources/template/Service.vm";
	// ServiceMock模板路径
	private static String SERVICEIMPL_VM_PATH = "csb-core-common/src/main/resources/template/ServiceImpl.vm";
	// ServiceImpl模板路径
	private static String SERVICEMOCK_VM_PATH = "csb-core-common/src/main/resources/template/ServiceMock.vm";
	//服务启动调试类模版路径
	private static String START_SERVICE_VM_PATH = "csb-core-common/src/main/resources/template/StartServiceApplication.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param module_prefix_name
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String table_prfix_name,
			String module_prefix_name,
			String project_name,
			String database_name) {
		String module_path = project_name + "-" + module_prefix_name.replaceAll("\\.", "-") + "/" + "src/main/resources/generator/generatorConfig.xml";
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database_name + "' AND table_name LIKE '" + table_prfix_name + module_prefix_name.replaceAll("\\.", "_") + "%';";
		System.out.println("module_path: " + module_path);
		System.out.println("sql: " + sql);
		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context= new VelocityContext();
			Map<String, Object> table = null;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, AESUtil.AESDecode(jdbc_password));
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				//模块命名去除前缀
				table.put("model_name", StringUtil.lineToHump(ObjectUtils.toString(map.get("TABLE_NAME")).replaceAll(table_prfix_name,"")));
				tables.add(table);
			}
			jdbcUtil.release();

			String modelProject = project_name + "-" + module_prefix_name;
			context.put("tables", tables);
			context.put("generator_javaTypeResolver_targetPackage","");
			context.put("generator_javaModelGenerator_targetPackage", "com." + project_name + "." + module_prefix_name + ".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", "com." + project_name + "." + module_prefix_name + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", "com." + project_name + "." + module_prefix_name + ".dao.mapper");
			context.put("targetProject", modelProject);
			context.put("generator_jdbc_password", AESUtil.AESDecode(jdbc_password));
			VelocityUtil.generate(VM_PATH, module_path, context);
			// 删除旧代码
			deleteDir(new File(modelProject + "/src/main/java/com/" + project_name + "/" + module_prefix_name.replaceAll("\\.", "/") + "/dao/model"));
			deleteDir(new File(modelProject + "/src/main/java/com/" + project_name + "/" + module_prefix_name.replaceAll("\\.", "/") + "/dao/mapper"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		// 生成代码
		System.out.println("========== 开始运行MybatisGenerator ==========");
		System.out.println("========== 开始生成Mapper,Model ==========");
		generatorModel(module_path);
		System.out.println("========== 生成Mapper,Model完毕 ==========");
		System.out.println("========== 结束运行MybatisGenerator ==========");

		System.out.println("========== 开始生成Service,ServiceImpl,ServiceMock ==========");
		generatorService(table_prfix_name, module_prefix_name, project_name, tables);
		System.out.println("========== 结束生成Service,ServiceImpl,ServiceMock ==========");

		// TODO: 2017/3/22 生成Controller
		System.out.println("========== 开始生成Controller ==========");
		System.out.println("========== 结束生成Controller ==========");
	}

	/**
	 * 生成Mapper,Model
	 * @param module_path
     */
	private static void generatorModel(String module_path) {
		try {
			List<String> warnings = new ArrayList<>();
			File configFile = new File(module_path);
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(true);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			for (String warning : warnings) {
				System.out.println(warning);
			}
		} catch (Exception e) {
			System.out.println("========== 生成Mapper,Model失败 ==========");
			e.printStackTrace();
		}
	}

	/**
	 * 生成Service,ServiceImpl,ServiceMock,startService
	 * @param module_prefix_name
	 * @param project_name
	 * @param tables
	 * @throws Exception
     */
	private static void generatorService(String table_prfix_name, String module_prefix_name, String project_name, List<Map<String, Object>> tables){
		try{
			String ctime = DateUtil.getCurrDateTime();

			String servicePath = project_name + "-" + module_prefix_name.replaceAll("\\.", "-")
					+ "/src/main/java/com/" + project_name + "/" + module_prefix_name.replaceAll("\\.", "/") + "/api";

			String serviceImplPath = project_name + "-" + module_prefix_name.replaceAll("\\.", "-")
					+ "/src/main/java/com/" + project_name + "/" + module_prefix_name.replaceAll("\\.", "/") + "/service/impl";

			String startServicePath = project_name + "-" + module_prefix_name.replaceAll("\\.", "-")
					+ "/src/main/java/com/" + project_name + "/" + module_prefix_name.replaceAll("\\.", "/") + "/service";

			for (int i = 0; i < tables.size(); i++) {
				String model = StringUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")).replaceAll(table_prfix_name,""));
				String service = servicePath + "/" + model + "Service.java";
				String serviceMock = servicePath + "/" + model + "ServiceMock.java";
				String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
//				String startService = startServicePath + "/CSB" + StringUtil.toUpperCaseFirstOne(module_prefix_name) + "RpcServiceApplication.java";

				// 生成service
				File serviceFile = new File(service);
				if (!serviceFile.exists()) {
					VelocityContext context = new VelocityContext();
					context.put("groupId", "com." + project_name);
					context.put("module", module_prefix_name.replaceAll("\\.", "/"));
					context.put("model", model);
					context.put("ctime", ctime);
					VelocityUtil.generate(SERVICE_VM_PATH, service, context);
					System.out.println(service);
				}

				// 生成serviceMock
				File serviceMockFile = new File(serviceMock);
				if (!serviceMockFile.exists()) {
					VelocityContext context = new VelocityContext();
					context.put("groupId", "com." + project_name);
					context.put("module", module_prefix_name.replaceAll("\\.", "/"));
					context.put("model", model);
					context.put("ctime", ctime);
					VelocityUtil.generate(SERVICEMOCK_VM_PATH, serviceMock, context);
					System.out.println(serviceMock);
				}

				// 生成serviceImpl
				File serviceImplFile = new File(serviceImpl);
				if (!serviceImplFile.exists()) {
					VelocityContext context = new VelocityContext();
					context.put("groupId", "com." + project_name);
					context.put("module", module_prefix_name.replaceAll("\\.", "/"));
					context.put("model", model);
					context.put("service", StringUtil.toLowerCaseFirstOne(model));
					context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
					context.put("ctime", ctime);
					VelocityUtil.generate(SERVICEIMPL_VM_PATH, serviceImpl, context);
					System.out.println(serviceImpl);
				}

				// 生成startService
//				File startServiceFile = new File(startService);
//				if (!startServiceFile.exists()) {
//					VelocityContext context = new VelocityContext();
//					context.put("groupId", "com." + project_name);
//					context.put("module", module_prefix_name.replaceAll("\\.", "/"));
//					context.put("model", model);
//					context.put("ctime", ctime);
//					VelocityUtil.generate(START_SERVICE_VM_PATH, startService, context);
//					System.out.println(startService);
//				}
			}
		}catch (Exception e){
			System.out.println("========== 生成Service,ServiceImpl,ServiceMock 失败 ==========");
			e.printStackTrace();
		}

	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i ++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

}
