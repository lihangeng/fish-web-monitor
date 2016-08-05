package com.yihuacomputer.fish.sms.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message.MessageEncodings;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;
import org.smslib.modem.SerialModemGateway;

import com.yihuacomputer.fish.api.ISMSLibMessageService;
import com.yihuacomputer.fish.sms.utils.DllManager;
import com.yihuacomputer.fish.sms.utils.SerialPortDetector;


public class SMSLibMessageService implements ISMSLibMessageService {

	private static Logger logger = LoggerFactory.getLogger(SMSLibMessageService.class);

//	@Autowired
//	private IParamService paramService;

	static { // 拷贝dll/so到jdk安装目录对应的位置
		try {
			String systemType = System.getProperty("os.name");
			String targetPath = System.getProperty("java.home") + File.separator + "bin";
			if (systemType.toLowerCase().indexOf("win") != -1) {
				DllManager.copyDllFromJar("rxtxSerial.dll", targetPath);
			} else {
				String libraryPath[] = System.getProperty("java.library.path").split(":");
				if (libraryPath.length > 0) {
					DllManager.copyDllFromJar("librxtxSerial.so", libraryPath[0]);
					DllManager.copyDllFromJar("javax.comm.properties", System.getProperty("java.home")
							+ File.separator + "lib");
				} else {
					throw new RuntimeException("无法部署动态库librxtxSerial.so");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * modem:网关ID（即短信猫端口编号）
	 * COM4:串口名称（在window中以COMXX表示端口名称，在linux,unix平台下以ttyS0-N或ttyUSB0
	 * -N表示端口名称），通过端口检测程序得到可用的端口 115200：
	 * 串口每秒发送数据的bit位数,必须设置正确才可以正常发送短信，可通过程序进行检测。常用的有115200、9600
	 * Wavecom：短信猫生产厂商，不同的短信猫生产厂商smslib所封装的AT指令接口会不一致
	 * ，必须设置正确.常见的有Huawei、wavecom等厂商 最后一个参数表示设备的型号，可选
	 */
	private AGateway getGateway(String comName) {
		String gateWayId = getParamValue("sms_gateWayId", "modem");// 网关ID
		String portName =comName;// 串口名称
		int buns = Integer.parseInt(getParamValue("sms_baudRate", "9600"));// 比特率
		String productName = getParamValue("sms_productName", "Wavecom");// 短信猫生产厂商
		String productType = getParamValue("sms_productType", "M1206B");// 短信猫生产厂商的产品型号
		String simPin = getParamValue("sms_simPin", "0000");
		String smsNumber = getParamValue("sms_smscNumber", "+8613800250500");

		SerialModemGateway gateway = new SerialModemGateway(gateWayId, portName, buns, productName, productType);
		gateway.setInbound(true);// 设置true，表示该网关可以接收短信,根据需求修改
		gateway.setOutbound(true);// 设置true，表示该网关可以发送短信,根据需求修改
		gateway.setSimPin(simPin);// sim卡锁，一般默认为0000或1234
		gateway.setSmscNumber(smsNumber);// 设置短信中心号码
		return gateway;
	}

	private String getParamValue(String key, String defautValue) {
//		IParam param = null;
//		if(paramService!=null)
//			param = paramService.getParam(key);
//		if (param == null) {
			return defautValue;
//		} else {
//			return param.getParamValue();
//		}
	}
//	private String getParamValue(String key) {
//		IParam param = null;
//		if(paramService!=null){
//			param = paramService.getParam(key);
//			return param.getParamValue();
//		}
//		return "";
//	}

	/**
	 * 服务初始化
	 *
	 * @throws Exception
	 */
	public boolean init(String comName) {
		String realComPorts = SerialPortDetector.getComPorts();
		String cfgComPorts = comName;
		if ("".equals(realComPorts) || !realComPorts.equals(cfgComPorts)) {
			logger.error(String.format("当前服务器上配置的串口为[%s],扫描到的串口为[%s]", cfgComPorts, realComPorts));
			System.out.println(String.format("当前服务器上配置的串口为[%s],扫描到的串口为[%s]", cfgComPorts, realComPorts));
			return false;
		}
		OutboundNotification outboundNotification = new OutboundNotification();
		Service.getInstance().setOutboundMessageNotification(outboundNotification);// 发送短信成功后的回调函方法
		try {
			Service.getInstance().addGateway(getGateway(comName));// 将网关添加到短信猫服务中
			logger.info("正在启动短信猫服务...");
			Service.getInstance().startService();// 启动服务，进入短信发送就绪状态
			logger.info("启动短信猫服务成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("启动短信猫服务失败:" + e.getMessage());
			logger.error("启动短信猫服务失败:" + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 销毁服务
	 */
	@javax.annotation.PreDestroy
	public void destory() {
		try {
			 Service.getInstance().stopService();
			logger.info("services destory ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void sendMsg(String telePhoneNum, String messageInfo,String comName) {
		if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED
				&& Service.getInstance().getServiceStatus() != ServiceStatus.STARTING) {
			if (!init(comName)) {
				return;
			}

		}
		// 如果主设备为已启动服务则正常工作
		if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
			logger.info("TEL:" + telePhoneNum + ";INFO:" + messageInfo);
			OutboundMessage msg = new OutboundMessage(telePhoneNum, messageInfo);
			// 发送中文时进行编码
			msg.setEncoding(MessageEncodings.ENCUCS2);
			// 异步发送短信
			Service.getInstance().queueMessage(msg);
		} else {
			logger.error("短信猫处于非正常状态!");
		}
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(AGateway gateway, OutboundMessage msg) {
			logger.info(String.format("成功发送短信到手机号[%s],短信的内容是[%s]",msg.getRecipient(),msg.getText()));
			System.out.println(String.format("成功发送短信到手机号[%s],短信的内容是[%s]",msg.getRecipient(),msg.getText()));
		}
	}

	public void sendMsg(List<String> telePhoneNums, String msg) {
		// TODO Auto-generated method stub

	}


}
