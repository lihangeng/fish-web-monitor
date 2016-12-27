package com.yihuacomputer.fish.api.version;

/**
 * @author YiHua
 *
 */
public enum VersionStaticsStatus {
	/**
	 * 设备总台数
	 */
	TOTALDEVICE(0,"VersionStaticsStatus.TOTALDEVICE"),
	/**
	 * 可下发台数
	 */
	PASHDEVICE(1,"VersionStaticsStatus.PASHDEVICE"),
	/**
	 * 失败台数
	 */
	FAILDEVICE(2,"VersionStaticsStatus.FAILDEVICE"),
	/**
	 * 成功台数
	 */
	SUCCESSDEVICE(3,"VersionStaticsStatus.SUCCESSDEVICE");
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

	public int getId() {
		return id;
	}
	
}
