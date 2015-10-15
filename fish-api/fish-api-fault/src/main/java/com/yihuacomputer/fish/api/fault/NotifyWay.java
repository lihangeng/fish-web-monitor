package com.yihuacomputer.fish.api.fault;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 通知方式
 * @author YiHua
 *
 */
public enum NotifyWay {
	SMS(1,"NotifyWay.SMS"),//短信
	MAIL(2,"NotifyWay.MAIL"),//邮件
	BOTH(3,"NotifyWay.BOTH");
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
	
	private NotifyWay(int id, String text) {
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
	
	public static NotifyWay getById(int id){
		for(NotifyWay each : NotifyWay.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
}
