package com.yihuacomputer.fish.api.report.device.etl;

/**
 * @author GQ 设备按型号进行分类月统计(cds6040w,cds6040t...)
 */
public interface IDeviceTypeSummaryMonth {
	public long getId();

	public void setId(long id);

	/**
	 * 设备型号
	 * 
	 * @return
	 */
	public String getDevType();

	public void setDevType(String devType);

	/**
	 * 设备型号数量
	 * 
	 * @return
	 */
	public int getNum();

	public void setNum(int num);

	/**
	 * 统计日期
	 * 
	 * @return
	 */
	public String getDate();

	public void setDate(String date);

	/**
	 * 新增设备数量
	 * 
	 * @return
	 */
	public int getAddDevNum();

	public void setAddDevNum(int addDevNum);

	/**
	 * 报废设备数量
	 * 
	 * @return
	 */
	public int getScrappedDevNum();

	public void setScrappedDevNum(int scrappedDevNum);

	/**
	 * 共增设备数量(第一次统计开始)
	 * 
	 * @return
	 */
	public int getAllAddDevNum();

	public void setAllAddDevNum(int allAddDevNum);

	/**
	 * 共报废设备数量(第一次统计开始)
	 * 
	 * @return
	 */
	public int getAllScrappedDevNum();

	public void setAllScrappedDevNum(int allScrappedDevNum);
}
