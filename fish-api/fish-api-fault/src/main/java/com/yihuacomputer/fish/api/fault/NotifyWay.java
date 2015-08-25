package com.yihuacomputer.fish.api.fault;
/**
 * 通知方式
 * @author YiHua
 *
 */
public enum NotifyWay {
	SMS(1,"短信"),//短信
	MAIL(2,"邮件"),//邮件
	BOTH(3,"短信和邮件");
	
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

	public String getText() {
		return text;
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
