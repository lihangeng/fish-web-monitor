package com.yihuacomputer.fish.api.fault;


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

	private int id;

	private String text;

	private NotifyType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText(){
		return text;
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
