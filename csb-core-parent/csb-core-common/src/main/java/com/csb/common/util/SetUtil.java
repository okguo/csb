package com.csb.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetUtil {

	private static Logger _log = LoggerFactory.getLogger(SetUtil.class);


	// 保证线程安全，且数据不重复，用来装载系统帐号
	private static Set codes = Collections.synchronizedSet(new HashSet());

	public static void loadCode(String code) {
		codes.add(code);
	}

	public static Set getCodes() {
		return codes;
	}

	/**
	 * 获取10位随机数字
	 * 
	 * @return
	 */
	public static String getCode() {
		Random random = new Random();
		StringBuffer rand = new StringBuffer();
		rand.append(random.nextInt(9) + 1);
		for (int i = 1; i < 10; i++) {
			rand.append(random.nextInt(10));
		}
		Set set = getCodes();
		String code = "";
		do {
			code = rand.toString();
		} while (set.contains(code));
		return code;
	}

	public static void main(String args[]) {
		System.out.println(getCode());
	}
}
