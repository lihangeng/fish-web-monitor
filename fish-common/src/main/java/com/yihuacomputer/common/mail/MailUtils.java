package com.yihuacomputer.common.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.FishCfg;
/**
 * 邮件发送辅助类
 * @since 2.1.1.0
 */
public class MailUtils {
	private static Logger logger = LoggerFactory.getLogger(MailUtils.class);
	
	/**
	 * 发送邮件
	 * @param title 主题
	 * @param content 内容
	 * @param perList 收件人列表
	 * @param fileList 附件列表
	 */
	public static void sendEmail(String title,String content,List<String> perList,List<String> fileList){
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
			if(fileList!=null){
				for (Iterator<String> iterator = fileList.iterator(); iterator.hasNext();) {
					String file = (String) iterator.next();
					emailHandle.addFileAffix(file);
				}
			}
			
			emailHandle.setFrom(fromAddress);
			emailHandle.setNeedAuth(true);
			emailHandle.setNamePass(fromAddress, fromAPass);
			emailHandle.sendEmail();
		} catch (Exception e) {
			logger.error(String.format("send mail errors [%s]",e));
		}
		
	}
	
	/**
	 * 发送邮件
	 * @param title 主题
	 * @param content 内容
	 * @param perList 收件人列表
	 */
	public static void sendEmail(String title,String content,List<String> perList){
		MailUtils.sendEmail(title, content, perList,new ArrayList<String>());
	}

}
