package com.wm.lejia.utils.http;

import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
//import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpUtils {
	
	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final String CHARSET_UTF8 = "UTF-8";

	private static int maxPerRoute = 100; // 每个路由允许活跃连接数
	private static int maxTotal = 200; // 所有路由总连接数上限
	private static int connectionRequestTimeout = 1000 * 15; // 向连接池申请连接超时，单位：毫秒
	private static int connectTimeout = 1000 * 5; // 建立连接超时，单位：毫秒
	private static int socketTimeout = 1000 * 10; // 等待数据超时，单位：毫秒
	
	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(maxTotal);
		connMgr.setDefaultMaxPerRoute(maxPerRoute);
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(connectTimeout);
		// 设置读取超时
		configBuilder.setSocketTimeout(socketTimeout);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
		configBuilder.setConnectTimeout(connectTimeout);
		configBuilder.setSocketTimeout(socketTimeout);
		requestConfig = configBuilder.build();
	}
	
	public static CloseableHttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
	}

	/**
	 * 发送 GET 请求（HTTP），不带输入数据
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doGet(String url) throws ClientProtocolException, IOException {
		return doGet(url, new HashMap<String, Object>());
	}

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doGet(String url, Map<String, Object> params) throws ClientProtocolException, IOException {
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key).toString());
			i++;
		}
		apiUrl += param;
		String result = null;
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		HttpGet httpGet = new HttpGet(apiUrl);
		CloseableHttpResponse response = null;
		httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET_UTF8);
			}
		}
		closeByGet(response, httpGet, httpClient);
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 * 
	 * @param apiUrl
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPost(String apiUrl) throws ClientProtocolException, IOException {
		return doPost(apiUrl, new HashMap<String, Object>());
	}
	
	/**
	 * 发送POST请求 (HTTP),数据在URL后  apiUrl?K=V&K=V... 
	 * @author tanxin
	 * @param apiUrl
	 * @param map
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPostParam(String apiUrl, Map<String, String> params)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		String httpStr = null;
		// params.
		Set<Entry<String, String>> entrySet = params.entrySet();
		String data = "";
		for (Entry<String, String> entry : entrySet) {
			String key = entry.getKey();
			String value = entry.getValue();
			data = data + key + "=" + value + "&";
		}
		HttpPost httpPost = null;
		if (!"".equals(data)) {
			data = data.substring(0, data.length() - 1);
			try {
				data = URLEncoder.encode(data, CHARSET_UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpPost = new HttpPost(apiUrl + "?" + data);
		} else {
			httpPost = new HttpPost(apiUrl);
		}
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		CloseableHttpResponse response = null;
		httpPost.setConfig(requestConfig);
		response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
			}
		}
		closeByPost(response, httpPost, httpClient);
		return httpStr;
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPost(String apiUrl, Map<String, String> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		CloseableHttpResponse response = null;
		httpPost.setConfig(requestConfig);
		List<NameValuePair> pairList = new ArrayList<>(params.size());
		for (Map.Entry<String, String> entry : params.entrySet()) {
			NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
			pairList.add(pair);
		}
		httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
		response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
			}
		}
		closeByPost(response, httpPost, httpClient);
		return httpStr;
	}

	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 * 
	 * @param apiUrl
	 * @param json
	 *            json对象
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPost(String apiUrl, Object json) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		httpPost.setConfig(requestConfig);
		StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
		stringEntity.setContentEncoding("UTF-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
			}
		}
		closeByPost(response, httpPost, httpClient);
		return httpStr;
	}
	
	public static String doPostXML(String apiUrl, String xmlStr) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8");
		StringEntity stringEntity = new StringEntity(xmlStr, "UTF-8");// 解决中文乱码问题
		httpPost.setEntity(stringEntity);
		response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
			}
		}
		closeByPost(response, httpPost, httpClient);
		return httpStr;
	}
	
	/**
	 * 下载文件
	 * @author tanxin
	 * @param apiUrl
	 * @param saveUrl
	 * @param filename
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void downLoad(String apiUrl, String saveUrl, String filename)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		HttpGet httpGet = new HttpGet(apiUrl);
		CloseableHttpResponse response = null;
		response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				File file = new File(saveUrl + filename);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[2048];
				int len = -1;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				is.close();
			}
		}
	}
	
	/**
	 * 发送带证书post请求
	 * @author tanxin
	 * @param apiUrl
	 * @param keyPath
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
/*	public static String doPostXMLAndPKCS12(String apiUrl, String keyPath, String xmlStr) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(keyPath));
		try {
			keyStore.load(instream, WeiXinConfig.mch_id.toCharArray());
		} finally {
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeiXinConfig.mch_id.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		// 发送http请求
		String httpStr = "";
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		try {
			httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8");
			StringEntity stringEntity = new StringEntity(xmlStr, "UTF-8");// 解决中文乱码问题
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				httpStr = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeByPost(response, httpPost, httpClient);
		}
		return httpStr;
	}*/
	
	/**
	 * 正常关闭post请求
	 * @author tanxin
	 * @param response
	 * @param httpPost
	 * @param httpClient
	 */
	private static void closeByPost(CloseableHttpResponse response,HttpPost httpPost,CloseableHttpClient httpClient){
		if (response != null) {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {		
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(httpPost != null){
			try {
				httpPost.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if(httpClient != null){
//			try {
//				httpClient.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	/**
	 * 正常关闭post请求
	 * @author tanxin
	 * @param response
	 * @param httpGet
	 * @param httpClient
	 */
	private static void closeByGet(CloseableHttpResponse response,HttpGet httpGet,CloseableHttpClient httpClient){
		if (response != null) {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {		
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(httpGet != null){
			try {
				httpGet.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if(httpClient != null){
//			try {
//				httpClient.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
}
