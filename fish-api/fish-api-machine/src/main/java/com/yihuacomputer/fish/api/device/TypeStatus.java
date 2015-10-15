package com.yihuacomputer.fish.api.device;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 设备类型状态
 * @author yihua
 *
 */
public enum TypeStatus {
	
	WIDE(0,"TypeStatus.WIDE"),USUALLY(1,"TypeStatus.USUALLY"),ORDINARY(2,"TypeStatus.ORDINARY"),SOME(3,"TypeStatus.SOME"),LITTLE(4,"TypeStatus.LITTLE");
//	WIDE(0,"广泛"),USUALLY(1,"常见"),ORDINARY(2,"一般"),SOME(3,"较少"),LITTLE(4,"微量");
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

	private TypeStatus(int id, String text) {
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

	public static TypeStatus getById(int id) {
		for (TypeStatus each : TypeStatus.values()) {
			if (each.getId() == id) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
