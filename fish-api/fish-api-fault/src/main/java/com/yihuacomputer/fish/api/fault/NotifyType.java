package com.yihuacomputer.fish.api.fault;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 通知类型
 * 
 * @author YiHua
 * 
 */
public enum NotifyType {
	CREATE(1, "NotifyType.CREATE"), // 创建通知
	UPDATE(2, "NotifyType.UPDATE"), // 升级通知
	CLOSE(3, "NotifyType.CLOSE");// 关闭通知
	private final static String BASENAME = "enum";
    public final static ResourceBundle resource = ResourceBundle.getBundle(BASENAME, FishCfg.locale==null?Locale.CHINA:FishCfg.locale);

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

	private NotifyType(int id, String text) {
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
	
	public static NotifyType getById(int id){
		for(NotifyType each : NotifyType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
