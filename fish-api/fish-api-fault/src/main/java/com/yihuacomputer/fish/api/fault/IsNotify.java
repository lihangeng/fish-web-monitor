package com.yihuacomputer.fish.api.fault;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

public enum IsNotify {

	YES(1,"IsNotify.YES"),
	
	NO(2,"IsNotify.NO");
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
	
	private IsNotify(int id, String text) {
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
	public static IsNotify parsGender(String isNotify){
		if(isNotify.equals("是")||isNotify.equals("YES")){
			return IsNotify.YES;
		}else{
			return IsNotify.NO;
		}
	}
}
