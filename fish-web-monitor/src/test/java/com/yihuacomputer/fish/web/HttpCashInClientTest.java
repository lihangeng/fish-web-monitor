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
import com.yihuacomputer.fish.monitor.entity.business.BoxInitDetail;
import com.yihuacomputer.fish.web.atm.format.CashInitalMsg;
/**
 * ATMC II 加钞测试
 * @author YiHua
 *
 */
public class HttpCashInClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		
		CashInitalMsg msg =new CashInitalMsg();
		msg.setTermId("13050001");
		msg.setAmt(100000);

		msg.setUuId("201610191");
		msg.setDate("2016-10-19 11:00:00");
		
		List<BoxInitDetail> boxDetail = new ArrayList<BoxInitDetail>();
     
		for(int i = 0 ;i<6;i++){
			BoxInitDetail box = new BoxInitDetail();
			box.setBoxId("Logic01");
			box.setBoxCurrency("100");
			box.setBoxInitAmt(1000);
			box.setBoxAmt(10);
			boxDetail.add(box);
		}
		msg.setBoxDetail(boxDetail);
	
			String json = JsonUtils.toJson(msg);
			System.out.println(json);
			try {
				HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/atmv/atm/msg/checkincash");
				
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
