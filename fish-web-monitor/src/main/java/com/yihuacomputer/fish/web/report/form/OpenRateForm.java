package com.yihuacomputer.fish.web.report.form;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;

/**
 * @author YiHua
 *
 */
public class OpenRateForm {

	private DecimalFormat doubleFormat = new DecimalFormat("0.00");

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
	
	private double avgOpenRate;

	private String orgName;

	private String devCatalogName;

	public OpenRateForm() {
	}

	   /**
	 * @param iOpenRate
	 */
	public OpenRateForm(IDayOpenRate iOpenRate) {

	        setAtmpTimeReal(getTimes(iOpenRate.getAtmpTimeReal()));
	        setFaultTimeReal(getTimes(iOpenRate.getFaultTimeReal()));
	        setHealthyTimeReal(getTimes(iOpenRate.getHealthyTimeReal()));
	        setId(iOpenRate.getId());
	        setMaintainTimeReal(getTimes(iOpenRate.getMaintainTimeReal()));
	        setOpenTimes(getTimes(iOpenRate.getOpenTimes()));
	        setStatDate(iOpenRate.getStatDate());
	        setStopTimeReal(getTimes(iOpenRate.getStopTimeReal()));
	        setTerminalId(iOpenRate.getTerminalId());
	        setUnknownTimeReal(getTimes(iOpenRate.getUnknownTimeReal()));
	        setDevCatalogName(iOpenRate.getDevCatalogName());
	        setOrgName(iOpenRate.getOrgName());

	        if (iOpenRate.getHealthyTimeReal() == 0
	                || iOpenRate.getOpenTimes() == 0) {
	            setOpenRate(0.00);
	        } else {
	            String value = doubleFormat.format(iOpenRate.getHealthyTimeReal()
	                    * 1.0 / iOpenRate.getOpenTimes() * 100);
	            setOpenRate(Double.valueOf(value));
	        }
	        setAvgOpenRate(iOpenRate.getAvgOpenRate());

	    }

	    private String getTimes(int times){
	    	StringBuffer sb = new StringBuffer("");
	    	String t1=(times/60/60)+"";
	    	String t2=(times/60%60)+"";
	    	String t3=(times%60)+"";
	    	sb.append(t1.length()==1?"0"+t1:t1).append(":");
	    	sb.append(t2.length()==1?"0"+t2:t2).append(":");
	    	sb.append(t3.length()==1?"0"+t3:t3);
	    	return sb.toString();
	    }

	/**
	 * @param target
	 * @return
	 */
	public static List<OpenRateForm> convert(List<IDayOpenRate> target) {
		List<OpenRateForm> result = new ArrayList<OpenRateForm>();

		for (IDayOpenRate openRate : target) {
			result.add(new OpenRateForm(openRate));
		}
		return result;
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
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDevCatalogName() {
		return devCatalogName;
	}

	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}

	public double getAvgOpenRate() {
		return avgOpenRate;
	}

	public void setAvgOpenRate(double avgOpenRate) {
		this.avgOpenRate = avgOpenRate;
	}
}
