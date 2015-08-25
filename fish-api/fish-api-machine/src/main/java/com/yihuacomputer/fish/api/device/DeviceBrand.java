package com.yihuacomputer.fish.api.device;

public enum DeviceBrand {
	
	YIHUA(0, "怡化"),
	
	WINCOR(1, "WINCOR"),
	
	DEBOL(2, "迪堡");
	
	private int id;
	private String text;
	
	private DeviceBrand(int id, String text) {
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
	
	public static DeviceBrand getById(int id){
		for(DeviceBrand each : DeviceBrand.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
}
