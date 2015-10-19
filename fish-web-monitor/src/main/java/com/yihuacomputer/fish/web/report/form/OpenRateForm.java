package com.yihuacomputer.fish.web.report.form;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.report.base.IDayOpenRate;

public class OpenRateForm {

	private DecimalFormat doubleFormat = new DecimalFormat("0.00");

	private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

	private long id;

	private String terminalId;

	/**
	 * 统计日期
	 */
	private String statDate;

	/** 设备应开机时长 */
	private String openTimes;

	/** 设备正常时间段内实际开机时长 */
	private String healthyTimeReal;

	/** 设备未知时长 */
	private String unknownTimeReal;

	/** 设备维护时长 */
	private String maintainTimeReal;

	/** 设备故障时长 */
	private String faultTimeReal;

	/** 设备atmp故障时长 */
	private String atmpTimeReal;

	/** 设备暂停时长 */
	private String stopTimeReal;

	/**
	 * 开机率,保留二位小数点.eg:98.15
	 */
	private double openRate;

	public OpenRateForm() {
	}

	public OpenRateForm(IDayOpenRate iOpenRate) {

		setAtmpTimeReal(timeFormat(iOpenRate.getAtmpTimeReal()));
		setFaultTimeReal(timeFormat(iOpenRate.getFaultTimeReal()));
		setHealthyTimeReal(timeFormat(iOpenRate.getHealthyTimeReal()));
		setId(iOpenRate.getId());
		setMaintainTimeReal(timeFormat(iOpenRate.getMaintainTimeReal()));
		setOpenTimes(timeFormat(iOpenRate.getOpenTimes()));
		setStatDate(iOpenRate.getStatDate());
		setStopTimeReal(timeFormat(iOpenRate.getStopTimeReal()));
		setTerminalId(iOpenRate.getTerminalId());
		setUnknownTimeReal(timeFormat(iOpenRate.getUnknownTimeReal()));

		if (iOpenRate.getHealthyTimeReal() == 0
				|| iOpenRate.getOpenTimes() == 0) {
			setOpenRate(0.00);
		} else {
			String value = doubleFormat.format(iOpenRate.getHealthyTimeReal()
					* 1.0 / iOpenRate.getOpenTimes() * 100);
			setOpenRate(Double.valueOf(value));
		}
	}

	public static List<OpenRateForm> convert(List<IDayOpenRate> target) {
		List<OpenRateForm> result = new ArrayList<OpenRateForm>();

		for (IDayOpenRate openRate : target) {
			result.add(new OpenRateForm(openRate));
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	private String timeFormat(long times) {

	    int day = (int) (times / (60 * 1000 * 60 * 24));

		Date d = new Date(times);
		d.setHours(d.getHours() - 8);

		return day + "day:" + formatter.format(d);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(String openTimes) {
		this.openTimes = openTimes;
	}

	public String getHealthyTimeReal() {
		return healthyTimeReal;
	}

	public void setHealthyTimeReal(String healthyTimeReal) {
		this.healthyTimeReal = healthyTimeReal;
	}

	public String getUnknownTimeReal() {
		return unknownTimeReal;
	}

	public void setUnknownTimeReal(String unknownTimeReal) {
		this.unknownTimeReal = unknownTimeReal;
	}

	public String getMaintainTimeReal() {
		return maintainTimeReal;
	}

	public void setMaintainTimeReal(String maintainTimeReal) {
		this.maintainTimeReal = maintainTimeReal;
	}

	public String getFaultTimeReal() {
		return faultTimeReal;
	}

	public void setFaultTimeReal(String faultTimeReal) {
		this.faultTimeReal = faultTimeReal;
	}

	public String getAtmpTimeReal() {
		return atmpTimeReal;
	}

	public void setAtmpTimeReal(String atmpTimeReal) {
		this.atmpTimeReal = atmpTimeReal;
	}

	public String getStopTimeReal() {
		return stopTimeReal;
	}

	public void setStopTimeReal(String stopTimeReal) {
		this.stopTimeReal = stopTimeReal;
	}

	public double getOpenRate() {
		return openRate;
	}

	public void setOpenRate(double openRate) {
		this.openRate = openRate;
	}
}
