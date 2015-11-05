package com.yihuacomputer.fish.api.openplan;


public interface IOpenPlanDetail {

	public long getId();

	public void setId(long id);

	/**
	 * 周几
	 * @return
	 */
	public Week getWeek();

	public void setWeek(Week week);

	/**
	 * 开机/关机
	 * @return
	 */
	public OpenClose getOpenClose() ;

	public void setOpenClose(OpenClose openClose);

	/**
	 * 开始时间
	 * @return
	 */
	public String getStartTime();

	public void setStartTime(String startTime);

	/**
	 * 结束时间
	 * @return
	 */
	public String getEndTime() ;

	public void setEndTime(String endTime);

	/**
	 * 所属开机方案
	 * @return
	 */
	public IDeviceOpenPlan getDeviceOpenPlan();

	public void setDeviceOpenPlan(IDeviceOpenPlan deviceOpenPlan);
}
