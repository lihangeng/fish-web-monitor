package com.yihuacomputer.fish.api.openplan;


public interface IOpenPlanDetail {

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 周几
	 * @return
	 */
	public Week getWeek();

	/**
	 * @param week
	 */
	public void setWeek(Week week);

	/**
	 * 开机/关机
	 * @return
	 */
	public OpenClose getOpenClose() ;

	/**
	 * @param openClose
	 */
	public void setOpenClose(OpenClose openClose);

	/**
	 * 开始时间
	 * @return
	 */
	public String getStartTime();

	/**
	 * @param startTime
	 */
	public void setStartTime(String startTime);

	/**
	 * 结束时间
	 * @return
	 */
	public String getEndTime() ;

	/**
	 * @param endTime
	 */
	public void setEndTime(String endTime);

	/**
	 * 所属开机方案
	 * @return
	 */
	public IDeviceOpenPlan getDeviceOpenPlan();

	/**
	 * @param deviceOpenPlan
	 */
	public void setDeviceOpenPlan(IDeviceOpenPlan deviceOpenPlan);
}
