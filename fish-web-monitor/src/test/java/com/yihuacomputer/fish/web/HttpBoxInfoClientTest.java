package com.yihuacomputer.fish.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.box.BoxType;
import com.yihuacomputer.fish.web.atm.format.BoxDetailReportMsg;
import com.yihuacomputer.fish.web.atm.format.DeviceBoxReportMsg;
import com.yihuacomputer.fish.web.command.format.BoxStatus;

/**
 * 进程信息测试
 *
 * @author YiHua
 *
 */
public class HttpBoxInfoClientTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {

		// 测试交易数据：0为无限次数,>0为指定次数
		int count = 2;
		try{
		// 设备号
		String termId = "13050002";
		DeviceBoxReportMsg dbrm = new DeviceBoxReportMsg();
		dbrm.setTermianlId(termId);
		List<BoxDetailReportMsg> boxdetailList = new ArrayList<BoxDetailReportMsg>();
		for (int i = 0; i < count; i++) {
			BoxDetailReportMsg bdrm = new BoxDetailReportMsg();
			bdrm.setBoxStatus(BoxStatus.OK.toString());
			bdrm.setBoxType(BoxType.BILLCASSETTE.toString());
			bdrm.setCurrency("CNY");
			bdrm.setId("cas" + i);
			bdrm.setMaximum(2750);
			bdrm.setNumber(100);
			bdrm.setValue(100);
			boxdetailList.add(bdrm);
		}
		dbrm.setBoxdetailList(boxdetailList);

		String json = JsonUtils.toJson(dbrm);
		System.out.println(json);

		HttpClient httpClient = new DefaultHttpClient();
		// HttpPost httpPost = new
		// HttpPost("http://172.18.30.38:8086/fish-web-monitor/atm/msg/transaction");
		HttpPost httpPost = new HttpPost("http://localhost:8080/atmv/atm/msg/reportboxdetail");

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
			System.out.println(String.format("send msg success[%s],msg[%s]", "", dbrm));
		} else {
			System.out.println(String.format("send msg fail[%s],msg[%s],statusLine[%s,%s]", "", dbrm, statusLine.getStatusCode(), statusLine.getReasonPhrase()));
		}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
}
