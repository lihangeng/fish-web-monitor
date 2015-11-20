package com.yihuacomputer.fish.web.atm.format;

/**
 * 软件版本升级报告
 * 
 * @author YiHua
 * 
 */
public class UpdateReportMsg {
    /**
     * 任务编号
     */
    private long taskId;

    /**
     * 设备编号
     */
    private String termId;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 版本号
     */
    private String versionNo;

    /**
     * 版本文件下载次数
     */
    private int fileDownTimes;

    private String downUrl;
    /**
     * 响应码
     */
    private String ret;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public int getFileDownTimes() {
        return fileDownTimes;
    }

    public void setFileDownTimes(int fileDownTimes) {
        this.fileDownTimes = fileDownTimes;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

}
