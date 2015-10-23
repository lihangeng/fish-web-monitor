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
 * @author YiHua
 *
 */
public class HttpStatusClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();


		//for(int i = 0 ;i<1000;i++){
//		int i = 1;
			StatusMsg status = new StatusMsg();
			//String data = "{\"id\":0,\"code\":\"A007\",\"method\":\"ADD\",\"runStatus\":\"Healthy\",\"boxInitCount\":\"10000\",\"modStatus\":\"Healthy\",\"boxCurrentCount\":\"10000\",\"boxStatus\":\"Full\",\"retainCardCount\":\"10\",\"netStatus\":\"Healthy\",\"idcStatus\":\"Healthy\",\"cimStatus\":\"Healthy\",\"cdmStatus\":\"Healthy\",\"rprStatus\":\"Healthy\",\"jprStatus\":\"Healthy\",\"pinStatus\":\"Healthy\",\"siuStatus\":\"Fatal\",\"ttuStatus\":\"Healthy\"}";

//			String dataUnknown = "{\"termId\":\"A0001\",\"runStatus\":\"Maintain\",\"boxStatus\":\"Low\",\"modStatus\":\"Warning\",\"idc\":\"Healthy\",\"idcCode\":\"00000000000\",\"idcReatianCard\":0,\"jpr\":\"Healthy\",\"jprCode\":\"0100000000\",\"rpr\":\"Warning\",\"rprCode\":\"0020000000\",\"cdm\":\"Healthy\",\"cdmCode\":\"00101004000\",\"cim\":\"Healthy\",\"cimCode\":\"00001000000\",\"pin\":\"Healthy\",\"pinCode\":\"00000000000\",\"ttu\":\"Unknown\",\"siu\":\"Healthy\",\"siuCode\":\"00000000000\",\"boxInitCount\":0,\"boxCurrentCount\":0}";

//			String stopUnknown="{\"termId\":\"A0001\",\"runStatus\":\"Healthy\",\"boxStatus\":\"Low\",\"modStatus\":\"Healthy\",\"idc\":\"Healthy\",\"idcCode\":\"00000000000\",\"idcReatianCard\":0,\"jpr\":\"Warning\",\"jprCode\":\"0020000000\",\"rpr\":\"Warning\",\"rprCode\":\"0020000000\",\"cdm\":\"Healthy\",\"cdmCode\":\"00101004000\",\"cim\":\"Healthy\",\"cimCode\":\"00001000000\",\"pin\":\"Healthy\",\"pinCode\":\"00000000000\",\"ttu\":\"Unknown\",\"siu\":\"Healthy\",\"siuCode\":\"00000000000\",\"boxInitCount\":0,\"boxCurrentCount\":0}";

//			String data = "{\"termId\":\"0001\",\"runStatus\":\"Unknown\",\"boxStatus\":\"Unknown\",\"modStatus\":\"Healthy\",\"idc\":\"Healthy\",\"idcCode\":\"00300000000\",\"idcHwCode\":\"0000000000\",\"idcReatianCard\":0,\"jpr\":\"Unknown\",\"jprHwCode\":\"unknow\",\"rpr\":\"Healthy\",\"rprCode\":\"0103300000\",\"rprHwCode\":\"0000000000\",\"cdm\":\"Unknown\",\"cdmHwCode\":\"unknow\",\"cim\":\"Unknown\",\"cimHwCode\":\"unknow\",\"pin\":\"Unknown\",\"pinHwCode\":\"unknow\",\"ttu\":\"Unknown\",\"ttuHwCode\":\"unknow\",\"siu\":\"Unknown\",\"boxInitCount\":0,\"boxCurrentCount\":0,\"icc\":\"Unknown\",\"iccHwCode\":\"\",\"iccReatianCard\":0,\"iccCurrentCount\":0,\"isc\":\"Healthy\",\"iscCode\":\"00300000000\",\"iscHwCode\":\"0000000000\",\"fgp\":\"Unknown\",\"fgpHwCode\":\"\"}";

			status.setTermId("00001");
			status.setModStatus(DeviceStatus.Fatal);
			status.setBoxStatus(BoxStatus.Fatal);
			status.setRunStatus(RunStatus.Healthy);
			status.setBoxInitCount(10300);
			status.setIdc(DeviceStatus.Healthy);
			status.setIdcCode("00300000000");
			status.setIdcHwCode("0000000000");
			status.setBoxCurrentCount(144077);
			status.setCdm(DeviceStatus.Unknown);
			status.setCdmCode("unknown");
			status.setCdmHwCode("00-122");
			status.setCim(DeviceStatus.Unknown);
			status.setCimCode("unknown");
			status.setJpr(DeviceStatus.Unknown);
			status.setJprCode("01110000440");
			status.setJprHwCode("unknown");
			status.setRpr(DeviceStatus.Healthy);
			status.setRprCode("0103300000");
			status.setRprHwCode("0000000000");
			status.setTtu(DeviceStatus.Healthy);
			status.setPin(DeviceStatus.Healthy);
			status.setSiu(DeviceStatus.Unknown);
			status.setIdcReatianCard(0);

			status.setIcc(DeviceStatus.Unknown);
			status.setIccCode("121111");
			status.setIccHwCode("44-555");
			status.setIccCurrentCount(0) ;
			status.setIccReatianCard(20) ;

			status.setIsc(DeviceStatus.Fatal) ;
			status.setIscCode("11111") ;
			status.setIscHwCode("00-1111") ;

			status.setFgp(DeviceStatus.Fatal) ;
			status.setFgpCode("9999") ;
			status.setFgpHwCode("444444") ;

			String json = JsonUtils.toJson(status);
			System.out.println(json);
			try {
//				HttpPost httpPost = new HttpPost("http://192.168.91.130:8080?action=update");
				HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/atmv/atm/msg/status");

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
