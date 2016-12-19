package com.yihuacomputer.common.mail;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.http.HttpProxy;

/**
 * 邮件处理辅助类
 * 
 * @author xuxiang
 *
 */
public class EmailHandle {

	private static Logger logger = LoggerFactory.getLogger(HttpProxy.class);

	private MimeMessage mimeMsg; // 邮件对象

	private Session session; // 发送邮件的Session会话

	private Properties props;// 邮件发送时的一些配置信息的一个属性对象

	private String sendUserName; // 发件人的用户名

	private String sendUserPass; // 发件人密码

	private Multipart mp;// 附件添加的组件

	private List<FileDataSource> files = new LinkedList<FileDataSource>();// 存放附件文件

	public EmailHandle(String smtp) {
		sendUserName = "";
		sendUserPass = "";
		setSmtpHost(smtp);// 设置邮件服务器
		createMimeMessage(); // 创建邮件
	}

	public void setSmtpHost(String hostName) {
		if (props == null) {
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
	}

	public boolean createMimeMessage() {
		try {
			// 用props对象来创建并初始化session对象
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			logger.error(String.format("获取邮件会话对象时发生错误 [%s]", e));
			return false;
		}
		try {
			mimeMsg = new MimeMessage(session); // 用session对象来创建并初始化邮件对象
			mp = new MimeMultipart();// 生成附件组件的实例
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
			return false;
		}
		return true;
	}

	/**
	 * 设置SMTP的身份认证
	 */
	public void setNeedAuth(boolean need) {
		if (props == null) {
			props = System.getProperties();
		}
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * 进行用户身份验证时，设置用户名和密码
	 */
	public void setNamePass(String name, String pass) {
		sendUserName = name;
		sendUserPass = pass;
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param mailSubject
	 * @return
	 */
	public boolean setSubject(String mailSubject) {
		try {
			mimeMsg.setSubject(mailSubject);
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
			return false;
		}
		return true;
	}

	/**
	 * 设置邮件内容,并设置其为文本格式或HTML文件格式，编码方式为UTF-8
	 * 
	 * @param mailBody
	 * @return
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + mailBody, "text/html;charset=UTF-8");
			// 在组件上添加邮件文本
			mp.addBodyPart(bp);
		} catch (Exception e) {
			logger.error(String.format("设置邮件正文时发生错误 [%s]", e));
			return false;
		}
		return true;
	}

	/**
	 * 增加发送附件
	 * 
	 * @param filename
	 *            邮件附件的地址，只能是本机地址而不能是网络地址，否则抛出异常
	 * @return
	 */
	public boolean addFileAffix(String filename) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(MimeUtility.encodeText(fileds.getName(), "utf-8", null)); // 解决附件名称乱码
			mp.addBodyPart(bp);// 添加附件
			files.add(fileds);
		} catch (Exception e) {
			logger.error(String.format("增加邮件附件 [%s]发生错误[%s]", filename, e));
			return false;
		}
		return true;
	}

	/**
	 * 设置发件人地址
	 * 
	 * @param from
	 *            发件人地址
	 * @return
	 */
	public boolean setFrom(String from) {
		try {
			mimeMsg.setFrom(new InternetAddress(from));
		} catch (Exception e) {
			logger.error(String.format("Exception is: [%s]",e));
			return false;
		}
		return true;
	}

	/**
	 * 设置收件人地址
	 * 
	 * @param to收件人的地址
	 * @return
	 */
	public boolean setTo(String to) {
		if (to == null) {
			return false;
		}
		try {
			mimeMsg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		} catch (Exception e) {
			logger.error(String.format("Exception is: [%s]",e));
			return false;
		}
		return true;
	}

	/**
	 * 发送附件
	 * 
	 * @param copyto
	 * @return
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null) {
			return false;
		}
		try {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(copyto));
		} catch (Exception e) {
			logger.error(String.format("Exception is: [%s]",e));
			return false;
		}
		return true;
	}

	/**
	 * 发送邮件
	 * 
	 * @return
	 */
	public boolean sendEmail() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			logger.info("准备发送邮件....");
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			// 连接邮件服务器并进行身份验证
			transport.connect((String) props.get("mail.smtp.host"), sendUserName, sendUserPass);
			// 发送邮件
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));

			logger.info("发送邮件成功.");
			transport.close();
			return true;
		} catch (Exception e) {
			logger.error(String.format("发送邮件失败 [%s]", e));
			return false;
		}
	}

}
