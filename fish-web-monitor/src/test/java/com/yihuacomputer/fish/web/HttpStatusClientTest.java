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
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.web.atm.format.StatusMsg;

/**
 * 状态信息测试
 * 
 * @author YiHua
 *
 */
public class HttpStatusClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		StatusMsg status = new StatusMsg();
//		status.setTermId("A10000196");
		status.setTermId("13050003");
		status.setModStatus(DeviceStatus.Healthy);
		status.setBoxStatus(BoxStatus.Healthy);
		status.setRunStatus(RunStatus.Healthy);
		status.setBoxInitCount(10300);
		status.setIdc(DeviceStatus.Healthy);
		status.setIdcCode("00300000000");
		status.setIdcHwCode("0000000000");
		status.setBoxCurrentCount(144077);
		status.setCdm(DeviceStatus.Healthy);
		status.setCdmCode("unknown");
		status.setCdmHwCode("00-122");
		status.setCim(DeviceStatus.Fatal);
		status.setCimCode("unknown");
		status.setJpr(DeviceStatus.Healthy);
		status.setJprCode("01110000440");
		status.setJprHwCode("unknown");
		status.setRpr(DeviceStatus.Healthy);
		status.setRprCode("0103300000");
		status.setRprHwCode("0000000000");
		status.setTtu(DeviceStatus.Healthy);
		status.setPin(DeviceStatus.Healthy);
		status.setSiu(DeviceStatus.Healthy);
		status.setIdcReatianCard(0);

//		status.setIcc(DeviceStatus.Healthy);
//		status.setIccCode("121111");
//		status.setIccHwCode("44-555");
//		status.setIccCurrentCount(0);
//		status.setIccReatianCard(20);
//
//		status.setIsc(DeviceStatus.Healthy);
//		status.setIscCode("11111");
//		status.setIscHwCode("00-1111");
//
//		status.setFgp(DeviceStatus.Healthy);
//		status.setFgpCode("9999");
//		status.setFgpHwCode("444444");

		String json = JsonUtils.toJson(status);
		System.out.println(json);
		try {
//			HttpPost httpPost = new HttpPost("http://10.0.8.134:8090/atmv/atm/msg/status");
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/atmv/atm/msg/status");
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
