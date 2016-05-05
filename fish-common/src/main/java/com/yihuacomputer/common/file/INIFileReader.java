package com.yihuacomputer.common.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class INIFileReader {
	public Map<String,Properties> sections = new HashMap<String,Properties>();
	private transient String currentSecion;
	private transient Properties current;

	public Map<String,Properties> getIniInfo(){
		return sections;
	}

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

			else if (line.matches(".*=.*")) {
				if (current != null) {
					int i = line.indexOf('=');
					String name = line.substring(0, i);
					String value = line.substring(i + 1);
					current.setProperty(name, value);
				}
			}
		}
	}

	protected void parseLine(String line) {
		line = line.trim();
		if (line.matches("\\[.*\\]")) {
			currentSecion = line.replaceFirst("\\[(.*)\\]", "$1");
			current = new Properties();
			sections.put(currentSecion, current);
		} else if (line.matches(".*=.*")) {
			if (current != null) {
				int i = line.indexOf('=');
				String name = line.substring(0, i);
				String value = line.substring(i + 1);
				current.setProperty(name, value);
			}
		}
	}

	public String getValue(String section, String name) {
		Properties p = (Properties) sections.get(section);
		if (p == null) {
			return null;
		}
		String value = p.getProperty(name);
		return value;
	}
}
