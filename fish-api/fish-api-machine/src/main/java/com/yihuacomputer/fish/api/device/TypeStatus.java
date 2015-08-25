package com.yihuacomputer.fish.api.device;

/**
 * 设备类型状态
 * @author yihua
 *
 */
public enum TypeStatus {

	WIDE(0,"广泛"),

	USUALLY(1,"常见"),

	ORDINARY(2,"一般"),

	SOME(3,"较少"),

	LITTLE(4,"微量");

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

	public String getText() {
		return text;
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
