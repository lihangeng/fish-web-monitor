package com.yihuacomputer.fish.web;

import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.web.atm.format.TransactionMsg;

/**
 * 进程信息测试
 *
 * @author YiHua
 *
 */
public class HttpTransactionClientTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {

		try {
			int i = 1;
			while (true) {
				HttpClient httpClient = new DefaultHttpClient();
				TransactionMsg msg = new TransactionMsg();
				msg.setTermId("test"); // 设备号
				msg.setTransId(String.format("%06d", i));// 流水号
				msg.setAmt(1000);// 交易金额
				msg.setCreditAccount("9559912345678901235");// 交易帐号
				msg.setDebitAccount("9559912345678901234");// 对方帐号
				msg.setDateTime(System.currentTimeMillis());// 交易时间
				msg.setTransDate(Integer.parseInt(DateUtils.get(new Date(), "yyyyMMdd")));
				msg.setTransCode("DEP");// 交易类型
				msg.setHostRet("00");// 主机返回码
				msg.setLocalRet("OK");// 本地返回码
				msg.setCurrency("CNY");// 币种
				String json = JsonUtils.toJson(msg);
				System.out.println(json);

				HttpPost httpPost = new HttpPost("http://172.18.30.38:8086/fish-web-monitor/atm/msg/transaction");

				StringEntity entity = new StringEntity(json, "UTF-8");

				// 设置请求头信息
				Header header = new BasicHeader("Content-Type", "application/json;charset=UTF-8");
				entity.setContentType(header);
				httpPost.setEntity(entity);

				HttpResponse response = httpClient.execute(httpPost);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					// InputStream responseContent =
					// response.getEntity().getContent();
					// resp = IoStream.readResponse(responseContent);
					System.out.println(String.format("send msg success[%s],msg[%s]", "", msg));
				} else {
					System.out.println(String.format("send msg fail[%s],msg[%s],statusLine[%s,%s]", "", msg,
							statusLine.getStatusCode(), statusLine.getReasonPhrase()));
				}

				i++;

				Thread.sleep(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
