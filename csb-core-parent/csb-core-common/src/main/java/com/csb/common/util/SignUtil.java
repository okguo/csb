package com.csb.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SignUtil {
	/**
	 *<p>Description:验证数据有效性</p>
	 *@param params 请求参数列表
	 *@param signature 加密签名，signature有params中的参数进行加密获得
	 *@return
	 */
	@SuppressWarnings("deprecation")
	public static final boolean checkSignature(List<String> params,String signature){
		Collections.sort(params,new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		StringBuffer temp = new StringBuffer();
		for(String param : params){
//			System.out.println("param：" + param);
			temp.append(param);
		}
//		System.out.println("server-signature：" + DigestUtils.shaHex(temp.toString()));
		return DigestUtils.shaHex(temp.toString()).equals(signature);
	}
}
