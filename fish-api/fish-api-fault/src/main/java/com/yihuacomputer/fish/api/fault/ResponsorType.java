package com.yihuacomputer.fish.api.fault;

/**
 * 责任人类型
 * 
 * @author YiHua
 * 
 */
public enum ResponsorType {
	ADMIN(1, "管机员"), // 管机员
	MAINTAIN(2, "维护员"), // 维护员
	BOTH(3, "管机员与维护员");// 管机员与维护员

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

	public String getText() {
		return text;
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
