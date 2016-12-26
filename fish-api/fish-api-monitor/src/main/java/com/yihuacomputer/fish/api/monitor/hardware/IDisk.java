package com.yihuacomputer.fish.api.monitor.hardware;

/**
 * 磁盘信息
 * @author YiHua
 *
 */
public interface IDisk {
	/**
	 * @return
	 */
	public  long getId();
	
	/**
	 * @param label
	 */
	public void setLabel(String label);

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @param totalSize
	 */
	public void setTotalSize(long totalSize);

	/**
	 * @param freeSize
	 */
	public void setFreeSize(long freeSize);

	/**
	 * @param fileSys
	 */
	public void setFileSys(String fileSys);

	/**
	 * @param memo
	 */
	public void setMemo(String memo);

	/**
	 * @param labelAndname
	 */
	public void setLabelAndname(String labelAndname);

	/**
	 * @return
	 */
	public String getLabel();

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @return
	 */
	public long getTotalSize();

	/**
	 * @return
	 */
	public long getFreeSize();

	/**
	 * @return
	 */
	public String getFileSys();

	/**
	 * @return
	 */
	public String getMemo();

	/**
	 * @return
	 */
	public String getLabelAndname();
	
	/**
	 * @param hardware
	 */
	public void setHardware(IHardware hardware);

}
