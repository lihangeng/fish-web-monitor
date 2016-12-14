package com.yihuacomputer.fish.api.fault;


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
    
	private int id;

	private String text;

	private ResponsorType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText(){
		return text;
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
