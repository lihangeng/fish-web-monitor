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
public class HttpRunInfoClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		
     

		AppRunInfoMsg msg = new AppRunInfoMsg();
		msg.setTermId("0001");
		msg.setRunStatus(RunStatus.Maintain);
	
			String json = JsonUtils.toJson(msg);
			System.out.println(json);
			try {
//				HttpPost httpPost = new HttpPost("http://192.168.91.130:8080?action=update");
				HttpPost httpPost = new HttpPost("http://127.0.0.1:8085/fish-web-srcb/atm/msg/runstatus");
				
				 StringEntity entity = new StringEntity(json, "UTF-8");

				
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
