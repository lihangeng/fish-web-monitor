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
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.web.atm.format.StatusMsg;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;

public class HttpDeviceInfoTest {
//	address:""
//	awayFlag:1
//	cashboxLimit:"0"
//	devServiceId:"2"
//	devServiceName:"深圳怡化"
//	devTypeId:1
//	installDate:"2016-11-22"
//	ip:"127.0.0.1"
//	netType:1
//	orgId:"3"
//	orgName:"code"
//	serial:""
//	setupType:1
//	terminalId:"00001"
//	virtual	:	""
//	workType	:	1
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		DeviceForm deviceForm = new DeviceForm();
		deviceForm.setAddress("");
		deviceForm.setAwayFlag("1");
		deviceForm.setCashboxLimit(0);
		deviceForm.setDevServiceId("2");
		deviceForm.setDevServiceName("深圳怡化");
		deviceForm.setDevTypeId(1);
		deviceForm.setInstallDate("2016-11-22");
		deviceForm.setIp("127.0.0.1");
		deviceForm.setNetType("1");
		deviceForm.setOrgId("3");
		deviceForm.setOrgName("code");
		deviceForm.setSerial("");
		deviceForm.setSetupType("1");
//		deviceForm.setStatus(status);("1");

		deviceForm.setVirtual("");
		deviceForm.setWorkType("1");
		for(int i=0;i<10000;i++){
			String terminalId="00000000"+i;
			terminalId = terminalId.substring(terminalId.length()-8);
			deviceForm.setTerminalId(terminalId);
			String json = JsonUtils.toJson(deviceForm);
			System.out.println(json);
			try {
//				HttpPost httpPost = new HttpPost("http://10.0.8.134:8090/atmv/atm/msg/status");
//				new Date().;
				HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/atmv/api/machine/device?_dc=1479808226896");
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
}
