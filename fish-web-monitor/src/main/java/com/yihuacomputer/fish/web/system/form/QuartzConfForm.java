package com.yihuacomputer.fish.web.system.form;


public class QuartzConfForm {
	private String triggerName;
	private String triggerGroup = "DEFAULT";
	private String jobName;
	private String jobGroup = "DEFAULT";

	private String jobDescription;
	private String jobClassName;
	private String cronExpression;
	private String startTime;
	private String endTime;
	private String nextFireTime;
	private String prevFireTime;

	private String triggerState;

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(String prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder ts = new StringBuilder();
		ts.append("\n");
		ts.append("triggerName").append(":").append(triggerName).append("\n");
		ts.append("triggerGroup").append(":").append(triggerGroup).append("\n");
		ts.append("jobName").append(":").append(jobName).append("\n");
		ts.append("jobGroup").append(":").append(jobGroup).append("\n");
		ts.append("jobDescription").append(":").append(jobDescription)
				.append("\n");
		ts.append("jobClassName").append(":").append(jobClassName).append("\n");
		ts.append("cronExpression").append(":").append(cronExpression)
				.append("\n");
		return ts.toString();
	}
}
