package com.yihuacomputer.fish.sms.utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YiHua
 *
 */
public class DllManager {
	
	private static Logger logger = LoggerFactory.getLogger(DllManager.class);
			
	/**
	 * @param libFullName
	 *            文件名称
	 * @param libPath
	 *            “/”
	 * @param targetPath
	 * @throws IOException
	 */
	public synchronized static void copyDllFromJar(String libFullName, String targetPath) throws IOException {

		String nativeTempDir = System.getProperty("java.io.tmpdir");
		InputStream in = null;
		BufferedInputStream reader = null;
		FileOutputStream writer = null;
		File extractedLibFile = new File(nativeTempDir + File.separator + libFullName);
		if (!extractedLibFile.exists()) {
			try {
				in = DllManager.class.getResourceAsStream("/" + libFullName);
				if (in == null)
					in = DllManager.class.getResourceAsStream(libFullName);
				DllManager.class.getResource(libFullName);
				reader = new BufferedInputStream(in);
				writer = new FileOutputStream(extractedLibFile);
				byte[] buffer = new byte[1024];
				while (reader.read(buffer) > 0) {
					writer.write(buffer);
					buffer = new byte[1024];
				}
			} catch (IOException e) {
				logger.error(String.format("IOException is [%s]", e));
			} finally {
				if (in != null)
					in.close();
				if (writer != null)
					writer.close();
			}
		}
		copyDllFile(new File(extractedLibFile.toString()), new File(targetPath + File.separator + libFullName));
	}

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		// copyDllFile("")
	}

	private static void copyDllFile(File sourceFile, File targetFile) throws IOException {
//		FileInputStream input = new FileInputStream(sourceFile);
//		BufferedInputStream inBuff = new BufferedInputStream(input);
//		
//		// 新建文件输出流并对它进行缓冲
//		FileOutputStream output = new FileOutputStream(targetFile);
//		BufferedOutputStream outBuff = new BufferedOutputStream(output);
//		// 缓冲数组
//		byte[] b = new byte[1024 * 5];
//		int len;
//		while ((len = inBuff.read(b)) != -1) {
//			outBuff.write(b, 0, len);
//		}
//		// 刷新此缓冲的输出流
//		outBuff.flush();
//		// 关闭流
//		inBuff.close();
//		outBuff.close();
//		output.close();
//		input.close();
	}
}
