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
import com.yihuacomputer.fish.web.atm.format.AutoUpdateMsg;
import com.yihuacomputer.fish.web.atm.format.PatchMsg;
import com.yihuacomputer.fish.web.atm.format.SimpleVersion;
/**
 * 进程信息测试
 * @author YiHua
 *
 */
public class  HttpAutoUpdateClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AutoUpdateMsg request= new AutoUpdateMsg();
		request.setTermId("13050003");
		List<SimpleVersion> currentPatches = new ArrayList<SimpleVersion>(); 
		currentPatches.add(new SimpleVersion("ATMVC","1.0.1"));
		request.setCurrentPatches(currentPatches);
		
//		AutoUpdateMsg response = (AutoUpdateMsg)HttpProxy.httpPost("http://localhost:8085/fish-web/api/msg/autoupdate", request, AutoUpdateMsg.class);
		
		
		HttpClient httpClient = new DefaultHttpClient();
		
		
		try {
//			HttpPost httpPost = new HttpPost("http://192.168.91.130:8080?action=update");
			HttpPost httpPost = new HttpPost("http://localhost:8080/atmv/atm/msg/autoupdate");
			
			 StringEntity entity = new StringEntity(JsonUtils.toJson(request), "UTF-8");

			
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
			
			AutoUpdateMsg response =  JsonUtils.fromJson(responseBody, AutoUpdateMsg.class);
			for(PatchMsg patch : response.getAutoUpdatePatches()){
				System.out.println(patch.getPatch());
				System.out.println(patch.getPatchNo());
				System.out.println(patch.getFileName());
				System.out.println(patch.getLocalPath());
				System.out.println(patch.getServerPath());
				System.out.println(patch.getFileSize());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	
	}
}
