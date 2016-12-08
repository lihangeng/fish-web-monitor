package com.yihuacomputer.common.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ini格式文件解析
 * @author GQ
 *
 */
public class INIFileReader {
	private Map<String,Properties> sections = new HashMap<String,Properties>();
	private transient String currentSecion;
	private transient Properties current;

	public Map<String,Properties> getIniInfo(){
		return sections;
	}

	/**
	 * @param filename
	 * @throws IOException
	 */
	public INIFileReader(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		read(reader);
		reader.close();
	}

	protected void read(BufferedReader reader) throws IOException {
		String line;
		int num = 0;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.matches("\\[.*\\]")) {
				currentSecion = line.replaceFirst("\\[(.*)\\]", "$1");
				current = new Properties();
				sections.put(currentSecion, current);
			}else if(line.matches("#.*") ){
				if( num ==0){
					currentSecion = line.replaceFirst("#.*", "");
					current = new Properties();
					sections.put(currentSecion, current);
				}
				num++;

			}

			else if (line.matches(".*=.*")&&current != null) {
				int i = line.indexOf('=');
				String name = line.substring(0, i);
				String value = line.substring(i + 1);
				current.setProperty(name, value);
			}
		}
	}

	protected void parseLine(String line) {
		String lineTmp = line;
		lineTmp = lineTmp.trim();
		if (lineTmp.matches("\\[.*\\]")) {
			currentSecion = lineTmp.replaceFirst("\\[(.*)\\]", "$1");
			current = new Properties();
			sections.put(currentSecion, current);
		} else if (lineTmp.matches(".*=.*")) {
			if (current != null) {
				int i = lineTmp.indexOf('=');
				String name = lineTmp.substring(0, i);
				String value = lineTmp.substring(i + 1);
				current.setProperty(name, value);
			}
		}
	}

	/**
	 * @param section
	 * @param name
	 * @return
	 */
	public String getValue(String section, String name) {
		Properties p = sections.get(section);
		if (p == null) {
			return null;
		}
		return p.getProperty(name);
	}
}
