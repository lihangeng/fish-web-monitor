package com.yihuacomputer.fish.api.fault;


/**
 * 通知方式
 * @author YiHua
 *
 */
public enum NotifyWay {
	SMS(1,"NotifyWay.SMS"),//短信
	MAIL(2,"NotifyWay.MAIL"),//邮件
	BOTH(3,"NotifyWay.BOTH"),
	NONE(4,"NotifyWay.NONE");//不发送
    
	private int id;

	private String text;

	private NotifyWay(int id, String text) {
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
	 * @param id
	 * @return
	 */
	public static NotifyWay getById(int id){
		for(NotifyWay each : NotifyWay.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
}
