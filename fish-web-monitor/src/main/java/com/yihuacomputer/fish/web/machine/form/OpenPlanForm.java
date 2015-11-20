package com.yihuacomputer.fish.web.machine.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.PlanState;
import com.yihuacomputer.fish.api.openplan.PlanStateType;
import com.yihuacomputer.fish.api.openplan.PlanType;

public class OpenPlanForm {

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

	public OpenPlanForm() {

	}

	public OpenPlanForm(IDeviceOpenPlan deviceOpenPlan) {
		this.id = deviceOpenPlan.getId();
		this.name = deviceOpenPlan.getName();
		this.planStateType = PlanStateType.Enabled.getId();
		if (deviceOpenPlan.getDeviceCount() == 0) {
			this.planStateType = PlanStateType.NotEnabled.getId();
		}
		if (deviceOpenPlan.getStartDate() != null) {
			this.startDate = DateUtils.getDate(deviceOpenPlan.getStartDate());
		}
		if (deviceOpenPlan.getEndDate() != null) {
			this.endDate = DateUtils.getDate(deviceOpenPlan.getEndDate());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = df.parse(DateUtils.getDate(deviceOpenPlan.getEndDate()));
				Date d2 = df.parse(DateUtils.getDate(new Date()));
				long diff = d1.getTime() - d2.getTime();
				int days = (int) (diff / (1000 * 60 * 60 * 24));
				if (diff <= 0) {
					this.planStateType = PlanStateType.Expired.getId();
				}
				if (days > 0 && days <= 10) {
					this.planStateType = PlanStateType.WExpired.getId();
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		this.desc = deviceOpenPlan.getDesc();
		this.planState = deviceOpenPlan.getPlanState();
		this.planType = deviceOpenPlan.getPlanType();
		this.createDateTime = deviceOpenPlan.getCreateDateTime();
		this.deviceCount = deviceOpenPlan.getDeviceCount();
		if (deviceOpenPlan.getPlanState().ordinal() == PlanState.Stoped.ordinal()) {
			this.planStateType = PlanStateType.Stoped.getId();
		}
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

	public OpenPlanForm(IDeviceOpenPlan deviceOpenPlan, String deviceId, String terminalId, int deviceCount) {
		this.id = deviceOpenPlan.getId();
		this.planStateType = PlanStateType.Enabled.getText();
		if (deviceCount == 0) {
			this.planStateType = PlanStateType.NotEnabled.getText();
		}
		this.name = deviceOpenPlan.getName();
		if (deviceOpenPlan.getStartDate() != null) {
			this.startDate = DateUtils.getDate(deviceOpenPlan.getStartDate());
		}
		if (deviceOpenPlan.getEndDate() != null) {
			this.endDate = DateUtils.getDate(deviceOpenPlan.getEndDate());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = df.parse(DateUtils.getDate(deviceOpenPlan.getEndDate()));
				Date d2 = df.parse(DateUtils.getDate(new Date()));
				long diff = d1.getTime() - d2.getTime();
				int days = (int) (diff / (1000 * 60 * 60 * 24));
				if (diff < 0) {
					this.planStateType = PlanStateType.Expired.getText();
				}
				if (days > 0 && days <= 10) {
					this.planStateType = PlanStateType.WExpired.getText();
				}
			}
			catch (Exception e){
			}
		}
		this.desc = deviceOpenPlan.getDesc();
		this.planState = deviceOpenPlan.getPlanState();
		if (deviceOpenPlan.getPlanState().ordinal() == PlanState.Stoped.ordinal()) {
			this.planStateType = PlanStateType.Stoped.getText();
		}
		this.planType = deviceOpenPlan.getPlanType();
		this.createDateTime = deviceOpenPlan.getCreateDateTime();
		this.deviceId = deviceId;
		this.terminalId = terminalId;
		this.deviceCount = deviceCount;
	}

	public List<OpenPlanDetailForm> getOpenPlanDetails() {
		List<OpenPlanDetailForm> openPlanDetails = new ArrayList<OpenPlanDetailForm>();
		if (StringUtils.isNotEmpty(this.openPlanDetailForms)) {
			try {
				JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
				openPlanDetails = JsonUtils.om.readValue(this.openPlanDetailForms, new TypeReference<List<OpenPlanDetailForm>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return openPlanDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public void setOpenPlanDetailForms(String openPlanDetailForms) {
		this.openPlanDetailForms = openPlanDetailForms;
	}

	public String getOpenPlanDetailForms() {
		return openPlanDetailForms;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

}
