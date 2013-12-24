package com.anyonecantest.jenkins.plugins.mypeople;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

import java.util.logging.Logger;



/**
 * @author Hyunil Shin
 */
public class MyPeople {
	
	// My People Open API
	private final static String RESULT_CODE_NAME = "code";
	private final static String RESULT_MESSAGE_NAME = "message";
	private final static int RESULT_OK = 200;

	private static String API_URL_PREFIX = "https://apis.daum.net";
	private static String MYPEOPLE_BOT_APIKEY;
	private static String API_URL_POSTFIX;
	
	
	private final static Logger LOG = Logger.getLogger("mypeople");
	
	
	private MyPeople() {

	}
	
	public static void setAPIKEY(String apikey) {
		MYPEOPLE_BOT_APIKEY = apikey;
		API_URL_POSTFIX = "?apikey=" + MYPEOPLE_BOT_APIKEY;
	}

		
	public static void sendMessage(String buddyId, String msg) throws MpImException 
			//throws ClientProtocolException, IOException, UnsupportedEncodingException, MpImException
        {
		
		try {
			String requestUrl = MyPeople.API_URL_PREFIX + "/mypeople/buddy/send.json";
			requestUrl += API_URL_POSTFIX;
   
	
			MultipartEntity reqEntity = new MultipartEntity();

			StringBody sbody = new StringBody(buddyId);
			reqEntity.addPart("buddyId", sbody);

			sbody = new StringBody(msg);
			reqEntity.addPart("content", sbody);

			postData(requestUrl, reqEntity);
		}catch(IOException e) {
			
		}

	}



	public static void postData(String url, MultipartEntity reqEntity) throws ClientProtocolException, IOException, MpImException {

		LOG.info("url: " + url);

		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		
		HttpClient httpclient = new DefaultHttpClient(myParams);

		String proxyPort = System.getProperty("http.proxyPort");
		String proxyHost = System.getProperty("http.proxyHost");
		LOG.info("proxy: " + proxyHost + ":" + proxyPort);

		if(proxyHost != null) {
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(proxyHost, Integer.parseInt(proxyPort), "http"));
		}
				
		HttpPost httppost = new HttpPost(url.toString());
		httppost.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(httppost);
		
		String result = EntityUtils.toString(response.getEntity());
		LOG.info("response: " + result);
		
		JSONObject jo = JSONObject.fromObject(result);
		int resultCode = Integer.valueOf((String)jo.get(MyPeople.RESULT_CODE_NAME));
		if(resultCode != MyPeople.RESULT_OK) {
			throw new MpImException((String)jo.get(MyPeople.RESULT_MESSAGE_NAME) + ", code: " + resultCode);
		}
		
								
	}

}
