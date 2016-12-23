package com.yihuacomputer.fish.api.fault;


/**
 * 是否通知
 * @author GQ
 *
 */
public enum IsNotify {

	/**
	 * 是
	 */
	YES(1,"IsNotify.YES"),
	
	/**
	 * 否
	 */
	NO(2,"IsNotify.NO");

	private int id;
	
	private String text;
	
	private IsNotify(int id, String text) {
		this.id = id;
		this.text = text;
	}
	public int getId() {
		return id;
	}
	
	public String getText(){
		return text;
    }

	/**
	 * @param isNotify
	 * @return
	 */
	public static IsNotify parsGender(String isNotify){
		if("YES".equals(isNotify)){
			return IsNotify.YES;
		}else{
			return IsNotify.NO;
		}
	}
}
