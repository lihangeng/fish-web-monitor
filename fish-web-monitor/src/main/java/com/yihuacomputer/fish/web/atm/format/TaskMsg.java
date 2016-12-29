package com.yihuacomputer.fish.web.atm.format;

/**
 * @author YiHua
 *
 */
public class TaskMsg {
    private long taskId;

    private String downTypeName;

    private String downVersionNo;

    private DeployStatus deployStatus;
    
    private String downUrl;
    
    /**
     * 初始化部署状态
     */
    public TaskMsg(){
        this.deployStatus = DeployStatus.UNCHECK;
    }
    
    public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	/**
	 * @param taskId
	 * @param downTypeName
	 * @param downVersionNo
	 * @param downUrl
	 */
	public TaskMsg(long taskId,String downTypeName,String downVersionNo,String downUrl){
        this();
        this.taskId = taskId;
        this.downTypeName = downTypeName;
        this.downVersionNo = downVersionNo;
        this.downUrl = downUrl;
    }
    

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getDownTypeName() {
        return downTypeName;
    }

    public void setDownTypeName(String downTypeName) {
        this.downTypeName = downTypeName;
    }

    public String getDownVersionNo() {
        return downVersionNo;
    }

    public void setDownVersionNo(String downVersionNo) {
        this.downVersionNo = downVersionNo;
    }

    public DeployStatus getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(DeployStatus deployStatus) {
        this.deployStatus = deployStatus;
    }

}
