package com.yihuacomputer.fish.api.version;

public enum VersionStaticsStatus {
	TOTALDEVICE(0,"设备总台数"),
	PASHDEVICE(1,"可下发台数"),
	FAILDEVICE(2,"失败台数"),
	SUCCESSDEVICE(3,"成功台数");
	private String text;
	private int id;
	private VersionStaticsStatus(int id,String text) {
		this.text = text;
		this.id =id;
	}
	/**
	 * 如果id的值超出范围，则直接返回TOTALDEVICE
	 * @param id
	 * @return
	 */
	public static VersionStaticsStatus getById(int id){
		VersionStaticsStatus vs = VersionStaticsStatus.TOTALDEVICE;
		for(VersionStaticsStatus vss:VersionStaticsStatus.values()){
			if(vss.getId()==id){
				vs = vss;
				break;
			}
		}
		return vs;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
