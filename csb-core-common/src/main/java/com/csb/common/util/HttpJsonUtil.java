package com.csb.common.util;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * json接收发送类,将json数据转换为json对象
 */
public class HttpJsonUtil {

	public static JSONArray readJsonArray(HttpServletRequest request)
			throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(request
				.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		String inputData = URLDecoder.decode(reqBody, "utf-8");
		JSONArray jsonArray = new JSONArray(inputData);
		return jsonArray;
	}

	public static JSONObject readJsonObject(HttpServletRequest request)
			throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request
				.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		//特殊处理url中的处理+和%
		reqBody = reqBody.replaceAll("%", "%25");
		reqBody = reqBody.replaceAll("\\+", "%2B");
		String inputData = URLDecoder.decode(reqBody, "utf-8");
		JSONObject jsonObject = new JSONObject(inputData);
		return jsonObject;
	}
	
	public static void writeJsonData(HttpServletResponse response,
			JSONObject jsonResponse) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());	
	}

	public static void writeJsonData(HttpServletResponse response,
			JSONArray jsonArray) throws Exception  {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toString());
	}

}

