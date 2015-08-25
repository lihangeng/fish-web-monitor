package com.yihuacomputer.fish.web.atm.format;

public class TaskMsg {
    private long taskId;

    private String downTypeName;

    private String downVersionNo;

    private DeployStatus deployStatus;
    
    public TaskMsg(){
        this.deployStatus = DeployStatus.UNCHECK;
    }
    
    public TaskMsg(long taskId,String downTypeName,String downVersionNo){
        this();
        this.taskId = taskId;
        this.downTypeName = downTypeName;
        this.downVersionNo = downVersionNo;
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
