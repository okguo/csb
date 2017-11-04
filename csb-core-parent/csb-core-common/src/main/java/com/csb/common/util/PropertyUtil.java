package com.csb.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	private static Log logger = LogFactory.getLog(PropertyUtil.class);

	private static Properties props = new Properties();

	public static void loadProperty(InputStream in) throws Exception {
		props.load(in);
	}

	public static String getProperty(String propName) {
		return props.getProperty(propName);
	}
}
