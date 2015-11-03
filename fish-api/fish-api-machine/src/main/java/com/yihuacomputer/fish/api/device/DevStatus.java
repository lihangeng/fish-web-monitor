package com.yihuacomputer.fish.api.device;

/**
 * 设备状态
 *  
 * 1.未开通、2.已开通 3.报停、4.报废
 */
public enum DevStatus {
	UNOPEN(1, "DevStatus.UPOPEN"),
	OPEN(2, "DevStatus.OPEN"),
	DISABLED(3, "DevStatus.DISABLED"),
	SCRAPPED(4, "DevStatus.SCRAPPED");
	
	public String getText() {
		return text;
	}

	private int id;

	private String text;

	private DevStatus(int id, String text) {
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

	public static DevStatus getById(int id) {
		for (DevStatus each : DevStatus.values()) {
			if (each.getId() == id) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
}
