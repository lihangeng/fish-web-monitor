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
import com.yihuacomputer.fish.monitor.entity.business.BoxSettleDetail;
import com.yihuacomputer.fish.web.atm.format.SettlementMsg;
/**
 * ATMC II 加钞测试
 * @author YiHua
 *
 */
public class HttpSettlementClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		
		SettlementMsg msg =new SettlementMsg();
		msg.setTermId("ATM001");
		msg.setUuId("201010101");
		msg.setDate("2012-10-22");
		msg.setLeftAmt(111111);
		msg.setDeposit(23565);
		msg.setDepositAmt(3556454);
		msg.setWithdrawal(5656);
		msg.setWithdrawalAmt(56587);
		msg.setTransaction(56568);
		msg.setTransactionAmt(787879);
		
		
		List<BoxSettleDetail> boxDetail = new ArrayList<BoxSettleDetail>();
     
		for(int i = 0 ;i<6;i++){
			BoxSettleDetail box = new BoxSettleDetail();
			box.setBoxId("Logic01");
			box.setBoxCurrency("100");
			box.setBoxLeftAmt(1000);
			box.setBoxAmt(10);
			boxDetail.add(box);
		}
		msg.setBoxDetail(boxDetail);
	
			String json = JsonUtils.toJson(msg);
			System.out.println(json);
			try {
				HttpPost httpPost = new HttpPost("http://172.18.30.25:8085/fish-web-monitor/atm/msg/checkoutcash");
				
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
