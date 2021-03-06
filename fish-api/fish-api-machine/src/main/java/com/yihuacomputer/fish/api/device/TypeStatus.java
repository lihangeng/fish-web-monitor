package com.yihuacomputer.fish.api.device;

/**
 * 设备类型状态
 * @author yihua
 *
 */
public enum TypeStatus {
	
	WIDE(0,"TypeStatus.WIDE"),USUALLY(1,"TypeStatus.USUALLY"),ORDINARY(2,"TypeStatus.ORDINARY"),SOME(3,"TypeStatus.SOME"),LITTLE(4,"TypeStatus.LITTLE");

	private int id;
	private String text;

	private TypeStatus(int id, String text) {
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
	public static TypeStatus getById(int id) {
		for (TypeStatus each : TypeStatus.values()) {
			if (each.getId() == id) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
