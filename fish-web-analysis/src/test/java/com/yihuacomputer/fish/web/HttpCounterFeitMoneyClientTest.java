package com.yihuacomputer.fish.web;



import java.util.Date;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.web.atm.format.CounterFeitMoneyMsg;

/**
 * 假币疑问币信息测试
 *
 * @author YiHua
 *
 */
public class HttpCounterFeitMoneyClientTest {
	public static void main(String[] args)  {
		HttpClient httpClient = new DefaultHttpClient();
		CounterFeitMoneyMsg msg = new CounterFeitMoneyMsg();
		msg.setAmt(2000);
		msg.setCounterFeitMoney(3);
		msg.setCreditAccount("9559912345678901235");
		msg.setCurrency("CNY");
		msg.setDateTime(System.currentTimeMillis());
		msg.setDebitAccount("9559912345678901238");
		msg.setHostRet("00");
		msg.setLocalRet("OK");
		msg.setTermId("001");
		msg.setTipFee(0);
		msg.setTransId(String.format("%06d", 1));
		msg.setNoteItem(null);
		msg.setTransCode("DEP");
		msg.setTransDate(Integer.parseInt(DateUtils.get(new Date(), "yyyyMMdd")));
//		List<NoteItem> list = new ArrayList<NoteItem>();
//		//for(int i=0;i<2;i++){
//			NoteItem ni = new NoteItem();
//			ni.setNoteCode("ttydaudwed");
//			ni.setSerial(11);
//			list.add(ni);
//			
//			NoteItem ni2 = new NoteItem();
//			ni2.setNoteCode("dsfdsfsdf");
//			ni2.setSerial(222);
//			list.add(ni2);
//		//}
//		
//		msg.setNoteItem(list);
		
		String json = JsonUtils.toJson(msg);
		System.out.println(json);
		
		try {
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8088/fish-web-jsnx/atm/msg/counterFeitMoney");
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

}