package com.yihuacomputer.common.util;

import java.util.Iterator;
import java.util.List;

import com.yihuacomputer.common.FishCfg;

public class MailUtils {

	public static void sendEmail(String title,String content,List<String> perList,List<String> fileList) throws Exception {
		
		EmailHandle emailHandle = new EmailHandle(FishCfg.getParamValue("mail_system_address"));//邮件处理对象
		String fromAddress = FishCfg.getParamValue("mail_userName");
		String fromAPass = FishCfg.getParamValue("mail_password");
		try {
			emailHandle.setSubject(title);
			emailHandle.setBody(content);
			for (Iterator<String> iterator = perList.iterator(); iterator.hasNext();) {
				String mail = (String) iterator.next();
				emailHandle.setTo(mail);
			}
			
			for (Iterator<String> iterator = fileList.iterator(); iterator.hasNext();) {
				String file = (String) iterator.next();
				emailHandle.addFileAffix(file);
			}
			
			emailHandle.setFrom(fromAddress);
			emailHandle.setNeedAuth(true);
			// emailHandle.addFileAffix("D:/settings_all.xml");// 附件文件路径
			emailHandle.setNamePass(fromAddress, fromAPass);
			emailHandle.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
