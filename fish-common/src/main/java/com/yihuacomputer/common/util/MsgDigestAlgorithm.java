package com.yihuacomputer.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.http.HttpProxy;

/**
 * @author GQ
 *
 */
public class MsgDigestAlgorithm {

	private static Logger logger = LoggerFactory.getLogger(HttpProxy.class);

	private MsgDigestAlgorithm() {
		throw new IllegalAccessError("Utils Class");
	}

	/**
	 * MD5加密字符串算法
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {

		MessageDigest messageDigest = null;

		StringBuilder md5StrBuff = new StringBuilder();
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException " + e);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException " + e);
		}
		if (null == messageDigest) {
			logger.error("messageDigest is null ");
			return md5StrBuff.toString();
		}
		byte[] byteArray = messageDigest.digest();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}
}
