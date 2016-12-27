package com.yihuacomputer.fish.api.report.device.etl;

/**
 * @author GQ 设备按型号进行分类月统计(cds6040w,cds6040t...)
 */
public interface IDeviceTypeSummaryMonth {
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 设备型号
	 * 
	 * @return
	 */
	public String getDevType();

	/**
	 * @param devType
	 */
	public void setDevType(String devType);

	/**
	 * 设备型号数量
	 * 
	 * @return
	 */
	public int getNum();

	/**
	 * @param num
	 */
	public void setNum(int num);

	/**
	 * 统计日期
	 * 
	 * @return
	 */
	public String getDate();

	/**
	 * @param date
	 */
	public void setDate(String date);

	/**
	 * 新增设备数量
	 * 
	 * @return
	 */
	public int getAddDevNum();

	/**
	 * @param addDevNum
	 */
	public void setAddDevNum(int addDevNum);

	/**
	 * 报废设备数量
	 * 
	 * @return
	 */
	public int getScrappedDevNum();

	/**
	 * @param scrappedDevNum
	 */
	public void setScrappedDevNum(int scrappedDevNum);

	/**
	 * 共增设备数量(第一次统计开始)
	 * 
	 * @return
	 */
	public int getAllAddDevNum();

	/**
	 * @param allAddDevNum
	 */
	public void setAllAddDevNum(int allAddDevNum);

	/**
	 * 共报废设备数量(第一次统计开始)
	 * 
	 * @return
	 */
	public int getAllScrappedDevNum();

	/**
	 * @param allScrappedDevNum
	 */
	public void setAllScrappedDevNum(int allScrappedDevNum);
}
