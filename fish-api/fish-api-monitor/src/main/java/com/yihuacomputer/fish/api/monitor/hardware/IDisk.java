package com.yihuacomputer.fish.api.monitor.hardware;

/**
 * 磁盘信息
 * @author YiHua
 *
 */
public interface IDisk {
	public  long getId();
	
	public void setLabel(String label);

	public void setName(String name);

	public void setTotalSize(long totalSize);

	public void setFreeSize(long freeSize);

	public void setFileSys(String fileSys);

	public void setMemo(String memo);

	public void setLabelAndname(String labelAndname);

	public String getLabel();

	public String getName();

	public long getTotalSize();

	public long getFreeSize();

	public String getFileSys();

	public String getMemo();

	public String getLabelAndname();
	
	public void setHardware(IHardware hardware);

}
