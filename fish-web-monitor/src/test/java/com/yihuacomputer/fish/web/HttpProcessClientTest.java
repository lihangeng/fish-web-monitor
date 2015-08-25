package com.yihuacomputer.fish.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;
import com.yihuacomputer.fish.web.atm.format.SchindlerMSg;
/**
 * 进程信息测试
 * @author YiHua
 *
 */
public class HttpProcessClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		
     
		List<SysProcess> procList = new ArrayList<SysProcess>();

		
		for(int i = 0 ;i<10;i++){			
			SysProcess illegalProc = new SysProcess();
			illegalProc.setId(i);
			illegalProc.setName("Demo");
			illegalProc.setCpuRate(45);
			illegalProc.setMemoryRate(100);
			illegalProc.setUser("administrator");			
			procList.add(illegalProc);
		}

		SchindlerMSg msg = new SchindlerMSg();
		msg.setTermId("YHATM001");
		msg.setProcList(procList);
	
			String json = JsonUtils.toJson(msg);
			System.out.println(json);
			try {
//				HttpPost httpPost = new HttpPost("http://192.168.91.130:8080?action=update");
				HttpPost httpPost = new HttpPost("http://172.18.30.25:8085/fish-web-monitor/atm/msg/schindleralarm");
				
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
