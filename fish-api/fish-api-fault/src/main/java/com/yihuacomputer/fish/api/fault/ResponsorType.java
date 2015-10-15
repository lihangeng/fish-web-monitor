package com.yihuacomputer.fish.api.fault;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 责任人类型
 * 
 * @author YiHua
 * 
 */
public enum ResponsorType {
	ADMIN(1, "ResponsorType.ADMIN"), // 管机员
	MAINTAIN(2, "ResponsorType.MAINTAIN"), // 维护员
	BOTH(3, "ResponsorType.BOTH");// 管机员与维护员
	private final static String BASENAME = "enum";
    private final static ResourceBundle resource = ResourceBundle.getBundle(BASENAME, FishCfg.locale==null?Locale.CHINA:FishCfg.locale);

    public String getText(){
    	String result =  text == null || resource == null || !resource.containsKey(text) ? text : resource.getString(text);
    	try {
			return new String(result.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return text;
		}
    }
	private int id;

	private String text;

	private ResponsorType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static ResponsorType getById(int id) {
		for (ResponsorType each : ResponsorType.values()) {
			if (each.getId() == id) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
