package com.csb.common.util;

import com.csb.common.entity.ErrCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * map操作，可配合WebAppInit初始化时加载数据库系统配置
 * 创建人      zhangxiang
 * 创建日期  2016-02-25 14:09:10
 * 修改人      
 * 修改日期  
 * 版本号      0.1
 */
public class MapUtil {

	private static Log logger = LogFactory.getLog(MapUtil.class);
	
	//系统常用错误码配置
	private static Map<String,ErrCode> errCodeMap = new HashMap<String,ErrCode>();
	public static void loadCode(String code,ErrCode errCode) {
		errCodeMap.put(code,errCode);
	}

	public static ErrCode getErrCode(String code) {
		return errCodeMap.get(code);
	}
	
	//用户行为配置
	private static Map<String,String> operateMap = new HashMap<String,String>();
	public static void loadOperateConfig(String operateCode,String operateDesc) {
		operateMap.put(operateCode,operateDesc);
	}

	public static String getOperate(String operateCode) {
		return operateMap.get(operateCode);
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, String> transBean2Map(Object obj) {

		if(obj == null){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value.toString());
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

}
