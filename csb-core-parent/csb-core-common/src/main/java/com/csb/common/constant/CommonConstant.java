package com.csb.common.constant;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通用参数类
 * 
 * @author Administrator
 * @since 2014/7/3 9:27
 */
public class CommonConstant {

	// 系统常用状态
	public static final Map SUCC_ERR_MAP = new HashMap(){
		{
			put("ERR","0");//失败
			put("SUCC","1");//成功
		}
	};
	
	/*
	 * 常用列表JSON
	 */
	public static final Map<String, String> COMMON_LIST_MAP = new HashMap<String, String>() {
		{
			/*************************** 天气START *************************/
			// 潍坊天气列表
			put("潍坊", "META-INF" + File.separator + "json"
					+ File.separator + "潍坊.json");
			
			// 肥乡天气列表
			put("湖州", "META-INF" + File.separator + "json"
					+ File.separator + "湖州.json");
			
			// 衢州天气列表
			put("衢州", "META-INF" + File.separator + "json"
					+ File.separator + "衢州.json");
			
			// 湖州天气列表
			put("馆陶", "META-INF" + File.separator + "json"
					+ File.separator + "馆陶.json");

			// 大理天气列表
			put("大理", "META-INF" + File.separator + "json"
					+ File.separator + "大理.json");

			// 南阳天气列表
			put("南阳", "META-INF" + File.separator + "json"
					+ File.separator + "南阳.json");

			// 南阳天气列表
			put("大名", "META-INF" + File.separator + "json"
					+ File.separator + "大名.json");

			// 枣庄天气列表
			put("枣庄", "META-INF" + File.separator + "json"
					+ File.separator + "枣庄.json");

			// 溧阳天气列表
			put("溧阳", "META-INF" + File.separator + "json"
					+ File.separator + "溧阳.json");

			// 象山天气列表
			put("象山", "META-INF" + File.separator + "json"
					+ File.separator + "象山.json");

			// 江山天气列表
			put("江山", "META-INF" + File.separator + "json"
					+ File.separator + "江山.json");

			// 鄂尔多斯天气列表
			put("鄂尔多斯", "META-INF" + File.separator + "json"
					+ File.separator + "鄂尔多斯.json");

			// 长兴天气列表
			put("长兴", "META-INF" + File.separator + "json"
					+ File.separator + "长兴.json");

			// 高台天气列表
			put("高台", "META-INF" + File.separator + "json"
					+ File.separator + "高台.json");
			/*************************** 天气END *************************/
		}
	};

	public static Map<Object,String> CITIZEN_CARD_PROGRESS_LIST = new LinkedHashMap<Object, String>(){
		{
			put("1","已受理申请");
			put("2","市民卡正在制卡中");
			put("3","市民卡已制卡完成，正在配送中");
			put("4","银行开卡中，请与15个工作日后领取");

			put("501","退卡注销");
			put("502","注销退款");
			put("503","补卡注销");
			put("504","换卡注销");
			put("505","退卡审核通过");
			put("506","退卡审核驳回");
			put("601","口头挂失");
			put("602","正式挂失");
			put("603","解除挂失");
			put("604","自动解挂");
			put("605","置黑");
			put("606","解除置黑");
			put("607","手动置黑");
			put("608","解除手动置黑");
			put("620","卡产品变更");
		}
	};

	public static final int PAGE_SIZE = 20;

	public static final String ZAIDALI_APP_ID = "UWEMZUDBTQ7VGWTS54TFSUBFNRSBQ6MU";

	public static final String JTB_TTZZ_MODULE_ID = "31CC4AD9532833A65FB02A5B23F797E2";

	public static final String JTB_YICHE_MODULE_ID = "A955287C24EA189641B5506C747918BD";

	public static final String JTB_TTZZ_NOTICE_TITLE = "【停停找找】您设置的停车提醒时间已到，请及时处理";

	public static final String ZUIMEIDALI_MODULE_ID = "2014521D2D785C53BDC693CE5533E077";

	public static final String ELECT_AWARD_NOTICE_TITLE = "您上传的作品已中奖，请点击查看";

	public static final String ELECT_PIC_NOTICE_TITLE = "您上传的作品已入围，请点击查看";
}
