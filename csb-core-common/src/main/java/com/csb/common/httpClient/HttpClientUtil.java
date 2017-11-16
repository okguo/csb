package com.csb.common.httpClient;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {
	private static Logger _log = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 发送请求
	 * 
	 * @param URL
	 * @param sParaTemp
	 * @return
	 * @throws Exception
	 */
	public static String buildRequest(String URL, Map<String, String> sParaTemp)
			throws Exception {
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
				.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);

		// 设置编码集
		request.setCharset("utf-8");
		request.setParameters(generatNameValuePair(sParaTemp));
		request.setUrl(URL);

		HttpResponse response = httpProtocolHandler.execute(request, "", "");
		if (response == null) {
			return null;
		}

		String strResult = response.getStringResult();
		return strResult;
	}

	public static String buildRequest(String URL,
			Map<String, String> sParaTemp, String strParaFileName,
			String strFilePath) throws Exception {
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
				.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);

		// 设置编码集
		request.setCharset("utf-8");
		request.setParameters(generatNameValuePair(sParaTemp));
		request.setUrl(URL);
		HttpResponse response = httpProtocolHandler.execute(request,
				strParaFileName, strFilePath);
		if (response == null) {
			return null;
		}

		String strResult = response.getStringResult();
		return strResult;
	}

	/**
	 * MAP类型数组转换成NameValuePair类型
	 * 
	 * @param properties
	 *            MAP类型数组
	 * @return NameValuePair类型数组
	 */
	private static NameValuePair[] generatNameValuePair(
			Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(),
					entry.getValue());
		}
		return nameValuePair;
	}

	/**
	 * get方法直接调用post方法
	 * 
	 * @param url
	 *            网络地址
	 * @return 返回网络数据
	 */
	public static String get(String url) {
		return post(url, null);
	}

	/**
	 * 设定post方法获取网络资源,如果参数为null,实际上设定为get方法
	 * 
	 * @param url
	 *            网络地址
	 * @param param
	 *            请求参数键值对
	 * @return 返回读取数据
	 */
	public static String post(String url, Map param) {
		HttpURLConnection conn = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			StringBuffer sb = null;
			if (param != null) {// 如果请求参数不为空
				sb = new StringBuffer();
				/*
				 * A URL connection can be used for input and/or output. Set the
				 * DoOutput flag to true if you intend to use the URL connection
				 * for output, false if not. The default is false.
				 */
				// 默认为false,post方法需要写入参数,设定true
				conn.setDoOutput(true);
				// 设定post方法,默认get
				conn.setRequestMethod("POST");
				// 获得输出流
				OutputStream out = conn.getOutputStream();
				// 对输出流封装成高级输出流
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(out));
				// 将参数封装成键值对的形式
				Set<Map.Entry<String, String>> p= param.entrySet(); 
				for (Map.Entry s : p) {
					sb.append(s.getKey()).append("=").append(s.getValue())
							.append("&");
				}
				// 将参数通过输出流写入
				writer.write(sb.deleteCharAt(sb.toString().length() - 1)
						.toString());
				writer.close();// 一定要关闭,不然可能出现参数不全的错误
				sb = null;
			}
			conn.connect();// 建立连接
			sb = new StringBuffer();
			// 获取连接状态码
			int recode = conn.getResponseCode();
			BufferedReader reader = null;
			if (recode == 200) {
				// Returns an input stream that reads from this open connection
				// 从连接中获取输入流
				InputStream in = conn.getInputStream();
				// 对输入流进行封装
				reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
				String str = null;
				sb = new StringBuffer();
				// 从输入流中读取数据
				while ((str = reader.readLine()) != null) {
					sb.append(str).append(System.getProperty("line.separator"));
				}
				// 关闭输入流
				reader.close();
				if (sb.toString().length() == 0) {
					return null;
				}
				return sb.toString()
						.substring(
								0,
								sb.toString().length()
										- System.getProperty("line.separator")
												.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null)// 关闭连接
				conn.disconnect();
		}
		return null;
	}
}
