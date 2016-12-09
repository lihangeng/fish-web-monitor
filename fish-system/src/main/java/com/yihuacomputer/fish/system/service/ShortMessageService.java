package com.yihuacomputer.fish.system.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.system.sms.IShortMessage;
import com.yihuacomputer.fish.api.system.sms.IShortMessageService;
import com.yihuacomputer.fish.system.entity.ShortMessage;

@Service
public class ShortMessageService implements IShortMessageService {

	@Override
	public ShortMessage make(){
		ShortMessage msg = new ShortMessage(this);
		return msg;
	}
	
	@Override
	public boolean send(IShortMessage message) throws Exception {
		List<String> messageList = message.listMobile();
		String mobile = null; // 手机号码
		for (int i = 0; i < messageList.size(); i++) {
			if (i == 0) {
				mobile = messageList.get(i);
			} else if (i > 0) {
				mobile = mobile + "|" + messageList.get(i);
			}
		}
		String content = message.getContent(); // 短信内容
		byte[] s1Arr = mobile.getBytes("UTF-8");
		byte[] s2Arr = content.getBytes("UTF-8");
		int lenMobile = s1Arr.length; // 手机号的长度
		int lenContent = s2Arr.length; // 短信内容的长度
		int len = 7 + lenMobile + lenContent + 1; // 报文的总长度
		byte[] msg = new byte[len];
		msg[0] = (byte) 0x7E;
		msg[1] = (byte) 0xA0;
		byte[] b = "Q".getBytes("UTF-8");
		msg[2] = (byte) b[0];
		msg[3] = (byte) (0xFF & (len));
		msg[4] = (byte) (0xFF & (len >> 8));
		msg[5] = (byte) (0xFF & (lenMobile));
		msg[6] = (byte) (0xFF & (lenMobile >> 8));
		System.arraycopy(s1Arr, 0, msg, 7, lenMobile);
		System.arraycopy(s2Arr, 0, msg, 7 + lenMobile, lenContent);
		byte cBack = 0; // 报文末尾的校验码
		for (int i = 0; i < len - 1; i++) {
			cBack += (0xFF & msg[i]);
		}
		msg[len - 1] = (byte) (0xFF & cBack);
		String ip = FishCfg.getParamValue("sms_ip");
		String port = FishCfg.getParamValue("sms_port");
		Socket client = new Socket(ip, port == null ? 0 : Integer.parseInt(port));
		DataOutputStream ds = new DataOutputStream(client.getOutputStream());
		ds.write(msg);
		ds.flush();
		
		byte[] head = new byte[7];
		BufferedInputStream in = new BufferedInputStream(new DataInputStream(client.getInputStream()));
		in.read(head);
		ds.close();
		in.close();
		client.close();
		if ((0xFF & head[0]) != 0x7E) {
//			System.out.println("报文头错误");
			return false;
		}
		if ((0xFF & head[1]) != 0xA1) {
//			System.out.println("报文类型错误");
			return false;
		}
		if ((0xFF & head[2]) != 0x52) {
			// System.out.println("请求标识错误");
			return false;
		}
		int lenrev = (0xFF & (head[3])) | (0xFFFF & (head[4] << 8)); // 总长度
		if (lenrev != 7) {
			// System.out.println("报文总长度错误");
			return false;
		}
		if ((0xFF & head[5]) != 0xE0) {
			return false;
		}
		byte cBackrev = 0; // 报文末尾的校验码
		for (int i = 0; i < lenrev - 1; i++) {
			cBackrev += (0xFF & head[i]);
		}
//		System.out.println("校验码 --- " + Integer.toBinaryString(0xFF & head[6]));
		if ((0xFF & head[6]) != cBackrev) {
			// System.out.println("校验码错误, 报文无效");
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		IShortMessage message = new ShortMessage();

		message.setContent("A1204320557|2012-06-15 08:30:20|传输U|78-1184|故障");
		message.addMobile("18028706932");
		ShortMessageService service = new ShortMessageService();
		service.send(message);

	}

}
