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
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.web.atm.format.AppRunInfoMsg;
/**
 * 进程信息测试
 * @author YiHua
 *
 */
public class HttpHardwareClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		
		String data = "{\"termId\":\"A#0001\"}";
		AppRunInfoMsg msg = new AppRunInfoMsg();
		msg.setTermId("A007");
		msg.setRunStatus(RunStatus.ReBoot);
	
			String json = JsonUtils.toJson(data);
			System.out.println(json);
			try {
//				HttpPost httpPost = new HttpPost("http://192.168.91.130:8080?action=update");
				HttpPost httpPost = new HttpPost("http://192.168.0.33:8085/fish-web/api/msg/runstatus");
				
				 StringEntity entity = new StringEntity(data, "UTF-8");

				
				 // 设置请求头信息
		        Header header = new BasicHeader("Content-Type",
		                "application/json;charset=UTF-8");
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
	//}

}
