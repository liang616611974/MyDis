package com.lf.helper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * Title: HttpClientHelper.java<／p>
 * <p>
 * Description: HttpClient帮助类<／p>
 * 
 * @author Liangfeng
 * @date 2017-1-2
 * @version 1.0
 */
public class HttpClientHelper {
	
	private static final String CHARSET_UTF8 = "UTF-8";

	private static final int REQUEST_TIME_OUT = 30000;
	
	private HttpClientHelper() {
	}

	/**
	 * 发送post请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String url, Map<String, String> param) {
		String result = null;
		try {
			//1.校验参数
			if (StringUtils.isBlank(url)) {
				throw new Exception("请求参数url不能为空!");
			}
			if (MapUtils.isEmpty(param)) {
				throw new Exception("请求参数param不能为空!");
			}
			//2.创建请求参数实体
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : param.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				nameValuePairs.add(new BasicNameValuePair(key, value));
			}
			HttpEntity requestEntity = new UrlEncodedFormEntity(nameValuePairs, CHARSET_UTF8);
			//3.获取结果
			result = request(url, requestEntity);
		} catch (Exception e) {
			throw new RuntimeException("httpclient发送post请求 异常", e);
		} 
		return result;
	}

	/**
	 * 上传文件
	 * @param url
	 * @param file
	 * @return
	 */
	public static String upload(String url, File file) {
		String result = null;
		try {
			//1.校验参数
			if (StringUtils.isBlank(url)) {
				throw new Exception("请求参数url不能为空!");
			}
			if (file==null) {
				throw new Exception("上传文件不能为空!");
			}
			// 2.创建请求参数实体
			FileBody bin = new FileBody(file);
			HttpEntity requestEntity = MultipartEntityBuilder.create().addPart("bin", bin).build();
			// 3.获取结果
			result = request(url, requestEntity);
		} catch (Exception e) {
			throw new RuntimeException("httpclient发送post请求 异常", e);
		} 
		return result;
	}
	

	/**
	 * 创建连接ssl httpclient
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		CloseableHttpClient client = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			client = HttpClients.createDefault();
		}
		return client;
	}
	
	/**
	 * 发送请求
	 * @param url
	 * @param requestEntity
	 * @return
	 */
	private static String request(String url,HttpEntity requestEntity) {
		//1.定义参数
		String body = ""; //响应体内容
		CloseableHttpClient httpClient = null; 
		CloseableHttpResponse httpResponse = null;
		try {
			// 2.创建httpClient实例
			if (url.startsWith("https")) {
				httpClient = createSSLClientDefault();// 连接ssl实例
			} else {
				httpClient = HttpClients.createDefault();// 默认实例
			}
			// 3.创建httpPost实例
			HttpPost httpPost = new HttpPost(url);
			// 4.设置表单实体
			httpPost.setEntity(requestEntity);
			// 5.设置请求配置
			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(REQUEST_TIME_OUT).setConnectTimeout(REQUEST_TIME_OUT).setConnectionRequestTimeout(REQUEST_TIME_OUT).build();
			httpPost.setConfig(requestConfig);
			// 6.发送请求
			httpResponse = httpClient.execute(httpPost);
			// 7.处理返回结果
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			HttpEntity httpEntity = httpResponse.getEntity();
			if (null != httpEntity) {
				ContentType contentType = ContentType.getOrDefault(httpEntity);
				Charset charset = contentType.getCharset();
				body = EntityUtils.toString(httpEntity, charset);
				EntityUtils.consume(httpEntity);
			}
			if (statusCode < 200 || statusCode >= 300) {
				throw new Exception("处理失败status:" + statusCode + ",返回值:" + body);
			}
		} catch (Exception e) {
			throw new RuntimeException("httpclient发送post请求 异常", e);
		} finally {
			close(httpClient, httpResponse);
		}
		return body;
	}
	
	
	/**
	 * 关闭httpclient资源
	 * @param httpClient
	 * @param httpResponse
	 */
	private static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
		if (null != httpResponse) {
			try {
				httpResponse.close();
			} catch (IOException e) {
			}
		}
		if (null != httpClient) {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
	}
}
