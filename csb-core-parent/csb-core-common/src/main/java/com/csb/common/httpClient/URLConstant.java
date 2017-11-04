package com.csb.common.httpClient;

public class URLConstant {
	// 测试 http://dvpt.icloudcity.cn:8445/sdk/
	// 生产 http://api.zjcitybao.com:8061/sdk/
	public static final String SDK_USER_DOMAIN = "http://api.zjcitybao.com:8061/sdk/";

	// 用户登录信息记录员到kvstore（阿里云redis）
	public static final String ADD_USER_KVSTORE = SDK_USER_DOMAIN
			+ "kv/addUser";

	// 根据userToken获取kvstore用户信息
	public static final String GET_USER_KVSTORE = SDK_USER_DOMAIN
			+ "kv/getUser";

	// 记录用户登录失败信息
	public static final String ADD_ERROR_LOGIN = SDK_USER_DOMAIN
			+ "kv/errLogin";

	// 获取用户登录失败信息
	public static final String GET_ERROR_LOGIN = SDK_USER_DOMAIN
			+ "kv/getErrLogin";

	// 删除用户登录失败信息
	public static final String DELETE_ERROR_LOGIN = SDK_USER_DOMAIN
			+ "kv/delErrLogin";

	public static final String MNS_PUBLISH_URL = SDK_USER_DOMAIN + "message/publish";
}
