package com.yihuacomputer.fish.web;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.web.atm.format.ParamUpdateMsg;

/**
 * 参数下发测试
 * 
 * @author zhengnan
 *
 */
public class HttpParamUpdateClientTest {
	public static void main(String[] args) {
		ParamUpdateMsg request = new ParamUpdateMsg();
		request.setTerminalId("");
		request.setTimestamp(1113);

		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8080/atmv/atm/msg/paramUpdate");
			
			StringEntity entity = new StringEntity(JsonUtils.toJson(request),"UTF-8");

			// 设置请求头信息
			Header header = new BasicHeader("Content-Type","application/json;charset=UTF-8");
			entity.setContentType(header);
			httpPost.setEntity(entity);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			System.out.println(httpPost.getURI());
			String responseBody = httpClient.execute(httpPost,responseHandler);
			System.out.println("responseBody");
			System.out.println(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
