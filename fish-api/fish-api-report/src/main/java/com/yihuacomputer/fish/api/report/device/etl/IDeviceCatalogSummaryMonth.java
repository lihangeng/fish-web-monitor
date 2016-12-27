package com.yihuacomputer.fish.api.report.device.etl;

/**
 * @author GQ
 * 设备按分类进行分类月统计(CRS,ATM...)
 */
public interface IDeviceCatalogSummaryMonth {
	
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 设备分类(CRS,ASM,ATM)
	 * @return
	 */
	public String getCatalog() ;
	/**
	 * 设备分类(CRS,ASM,ATM)
	 * @param catalog
	 */
	public void setCatalog(String catalog) ;
	/**
	 * 设备数量(CRS,ASM,ATM)
	 * @return
	 */
	public int getNum() ;
	/**
	 * 设备数量(CRS,ASM,ATM)
	 * @param num
	 */
	public void setNum(int num);

	/**
	 * 设备统计日期
	 * @return
	 */
	public String getDate();
	/**
	 * 设备统计日期
	 * @param date
	 */
	public void setDate(String date);


	/**
	 * 设备新增数量，针对上一次统计
	 * @return
	 */
	public int getAddDevNum();
	/**
	 * 设备新增数量，针对上一次统计
	 * @param addDevNum
	 */
	public void setAddDevNum(int addDevNum);
	/**
	 * 设备报废数量，针对上一次统计
	 * @return
	 */
	public int getScrappedDevNum();
	/**
	 * 设备报废数量，针对上一次统计
	 * @param scrappedDevNum
	 */
	public void setScrappedDevNum(int scrappedDevNum);
	/**
	 * 增加设备数量，针对第一次统计
	 * @return
	 */
	public int getAllAddDevNum();
	/**
	 * 增加设备数量，针对第一次统计
	 * @param allAddDevNum
	 */
	public void setAllAddDevNum(int allAddDevNum);

	/**
	 * 报废设备数量，针对第一次统计
	 * @return
	 */
	public int getAllScrappedDevNum();
	/**
	 * 报废设备数量，针对第一次统计
	 * @param allScrappedDevNum
	 */
	public void setAllScrappedDevNum(int allScrappedDevNum);
}
