package com.yihuacomputer.common.filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yihuacomputer.common.mail.MailUtils;

public class MailUtilsTest {
	
//	EmailHandle emailHandle = new EmailHandle("smtp.yihuacomputer.com");// 邮件处理对象
//	String fromAddress = "xuxigang@yihuacomputer.com";
//	String fromAPass = "my@163.com";
	
	@Test
	@Ignore
	public void sendMail(){
		List<String> persons = new ArrayList<String>();
		persons.add("xuxigang@yihuacomputer.com");
		try {
			MailUtils.sendEmail("java1", "auto generate111", persons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void sendMailWithAttachments(){
		List<String> persons = new ArrayList<String>();
		persons.add("xuxigang@yihuacomputer.com");
		List<String> files = new ArrayList<String>();
		files.add("D:/check-config.zip");
		try {
			MailUtils.sendEmail("java1", "auto generate111", persons,files);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
