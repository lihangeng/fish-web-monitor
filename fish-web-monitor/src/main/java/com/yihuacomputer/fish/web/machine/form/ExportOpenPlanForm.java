package com.yihuacomputer.fish.web.machine.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.PlanState;
import com.yihuacomputer.fish.api.openplan.PlanStateType;
import com.yihuacomputer.fish.api.openplan.PlanType;


public class ExportOpenPlanForm {

	private long id;

	private String name;

	private String startDate;

	private String endDate;

	private String desc;

	private PlanType planType;

	private PlanState planState;

	private String openPlanDetailForms;

	private String createDateTime;

	private String deviceId;

	private String terminalId;

	private String planStateType;

	private int deviceCount;

	private String ip;

	private String orgName;

	private String devType;

	private String devCatalog;

	private String openPlanDetails;



	public ExportOpenPlanForm() {

	}

	public ExportOpenPlanForm(IDeviceOpenPlan deviceOpenPlan, Object objDev[],
			List<IOpenPlanDetail> planDetails) {
		this.id = deviceOpenPlan.getId();
		this.name = deviceOpenPlan.getName();
		this.planStateType = PlanStateType.Enabled.getText();
		if (deviceOpenPlan.getDeviceCount() == 0) {
			this.planStateType = PlanStateType.NotEnabled.getText();
		}
		if (deviceOpenPlan.getStartDate() != null) {
			this.startDate = DateUtils.getDate(deviceOpenPlan.getStartDate());
		}
		if (deviceOpenPlan.getEndDate() != null) {
			this.endDate = DateUtils.getDate(deviceOpenPlan.getEndDate());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = df.parse(DateUtils.getDate(deviceOpenPlan
						.getEndDate()));
				Date d2 = df.parse(DateUtils.getDate(new Date()));
				long diff = d1.getTime() - d2.getTime();
				int days = (int) (diff / (1000 * 60 * 60 * 24));
				if (diff < 0) {
					this.planStateType = PlanStateType.Expired.getText();
				}
				if (days > 0 && days <= 10) {
					this.planStateType = PlanStateType.WExpired.getText();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.desc = deviceOpenPlan.getDesc();
		this.planState = deviceOpenPlan.getPlanState();
		this.planType = deviceOpenPlan.getPlanType();
		this.createDateTime = deviceOpenPlan.getCreateDateTime();
		this.deviceCount = deviceOpenPlan.getDeviceCount();
		if (deviceOpenPlan.getPlanState().ordinal() == PlanState.Stoped
				.ordinal()) {
			this.planStateType = PlanStateType.Stoped.getText();
		}
		if(objDev != null)
		{
			this.terminalId = ((String) objDev[0]);
			this.ip = (objDev[1].toString());
			this.orgName = ((String) objDev[2]);
			this.devType = ((String) objDev[3]);
			this.devCatalog = ((String) objDev[4]);
		}
		StringBuffer openDetails = new StringBuffer();
		int weekPlanFlag = 1;
		int dayPlanFlag = 1;
		for (IOpenPlanDetail openPlanDetail : planDetails) {
			if (openPlanDetail.getWeek() != null) {
				if (weekPlanFlag == 1) {
					openDetails.append("方案详情(星期)\r\n");
					weekPlanFlag++;
				}
				String weekDay = openPlanDetail.getWeek().toString();
				if ("Mon".equals(weekDay)) {
					weekDay = "星期一";
				} else if ("Tues".equals(weekDay)) {
					weekDay = "星期二";
				} else if ("Wed".equals(weekDay)) {
					weekDay = "星期三";
				} else if ("Thur".equals(weekDay)) {
					weekDay = "星期四";
				} else if ("Fri".equals(weekDay)) {
					weekDay = "星期五";
				} else if ("Sat".equals(weekDay)) {
					weekDay = "星期六";
				} else if ("Sun".equals(weekDay)) {
					weekDay = "星期日";
				}
				openDetails.append(weekDay + "    ");
			} else {
				if (dayPlanFlag == 1) {
					openDetails.append("方案详情(日期)\r\n");
					openDetails.append("有效开始日期："+startDate+"\r\n");
					openDetails.append("有效结束日期："+endDate+"\r\n");
					dayPlanFlag++;
				}
			}
			String temp = openPlanDetail.getOpenClose().toString();
			String openClose = "";
			if("Open".equals(temp))
			{
				 openClose = "开机";
			}
			if("Close".equals(temp))
			{
				 openClose = "关机";
			}
			String startTime = openPlanDetail.getStartTime();
			String endTime = openPlanDetail.getEndTime();
			openDetails.append(openClose + "    " + startTime + "    "
					+ endTime + "\r\n");
		}
		this.openPlanDetails = openDetails.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public PlanState getPlanState() {
		return planState;
	}

	public void setPlanState(PlanState planState) {
		this.planState = planState;
	}

	public String getOpenPlanDetailForms() {
		return openPlanDetailForms;
	}

	public void setOpenPlanDetailForms(String openPlanDetailForms) {
		this.openPlanDetailForms = openPlanDetailForms;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getPlanStateType() {
		return planStateType;
	}

	public void setPlanStateType(String planStateType) {
		this.planStateType = planStateType;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevCatalog() {
		return devCatalog;
	}

	public void setDevCatalog(String devCatalog) {
		this.devCatalog = devCatalog;
	}

	public String getOpenPlanDetails() {
		return openPlanDetails;
	}

	public void setOpenPlanDetails(String openPlanDetails) {
		this.openPlanDetails = openPlanDetails;
	}

}
