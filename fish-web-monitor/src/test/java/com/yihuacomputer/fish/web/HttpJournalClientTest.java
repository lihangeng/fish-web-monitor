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
import com.yihuacomputer.fish.web.atm.format.JournalMsg;

/**
 * 日志实时上传测试
 * 
 * @author YiHua
 * 
 */
public class HttpJournalClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();

		JournalMsg msg = new JournalMsg();

		msg.setTerminalId("A1001");

		msg.setContent("13-11-04 08:15:01  清交易流水成功13-11-04 08:15:01  获取出钞箱状态成功.13-11-04 08:25:10  取款设备钞箱满13-11-04 08:47:47  取款设备自恢复成功13-11-04 08:48:05  主机授权码为：0000  013-11-04 08:48:07  获取出钞箱状态成功.13-11-04 08:48:21  平帐查询13-11-04 08:48:21  主机授权码为：0000  03-11-04 08:48:21 ************ 平帐查询清单 **************日期:2013-11-04 08:48 设备号:18C6----------------------------------------取款钞箱:    余    额:320,000.0013-11-04 08:49:07  退出管理员状态13-11-04 08:49:07  进入退出服务13-11-04 08:49:09  申请RQK成功13-11-04 08:49:11  进入服务状态13-11-04 08:49:15  读卡器：正常13-11-04 08:49:15  通讯设备：正常13-11-04 08:49:19  通讯设备：正常13-11-04 08:59:45  有插卡 设备编号:18C613-11-04 08:59:46  插入IC芯片卡13-11-04 08:59:46  建立候选列表成功，采用芯片方式对外交易13-11-04 08:59:49  插卡 IC账号:621495101017167913-11-04 08:59:49  帐号:621495101017167913-11-04 08:59:51  InputBuf:C_S_CDSCON");
		msg.setCreatedTime("2013-09-11");

		String json = JsonUtils.toJson(msg);
		System.out.println(json);
		try {
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8085/fish-web-monitor/atm/msg/journal");

			StringEntity entity = new StringEntity(json, "UTF-8");

			// 设置请求头信息
			Header header = new BasicHeader("Content-Type", "application/json;charset=UTF-8");
			entity.setContentType(header);
			httpPost.setEntity(entity);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			System.out.println(httpPost.getURI());
			String responseBody = httpClient.execute(httpPost, responseHandler);
			System.out.println("responseBody");
			System.out.println(responseBody);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
