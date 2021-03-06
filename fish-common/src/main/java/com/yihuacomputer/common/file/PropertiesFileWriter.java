package com.yihuacomputer.common.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * property文件操作
 *
 */
public class PropertiesFileWriter {
	private StringBuilder sb = new StringBuilder();

	/**
	 * 目录分隔符
	 */
	public static String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * @param filename
	 * @param sections
	 * @throws IOException
	 */
	public PropertiesFileWriter(String filename, Map<String, Map<String, String>> sections) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = initWriter(filename);
			initIni(sections);
			writer.write(sb.toString());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	// 初始化流
	private BufferedWriter initWriter(String filename) throws IOException {
		File file = new File(filename);
		if(!file.exists()){
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(file, false));
		return writer;
	}

	// 组装ini文件
	private void initIni(Map<String, Map<String, String>> sections) {
		for (Map.Entry<String, Map<String, String>> e : sections.entrySet()) {
			String k = e.getKey();
			Map<String, String> values = e.getValue();
			for (Map.Entry<String, String> ev : values.entrySet()) {
				sb.append(k).append(".").append(ev.getKey() + "=" + ev.getValue());
				sb.append(LINE_SEPARATOR);
			}
		}
	}
}
