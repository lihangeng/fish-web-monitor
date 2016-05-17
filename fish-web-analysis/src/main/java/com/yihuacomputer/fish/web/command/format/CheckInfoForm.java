package com.yihuacomputer.fish.web.command.format;

/**
 * ATM体检信息
 * @author huxiaobao
 *
 */
public class CheckInfoForm {
    
    /**
     * CPU空闲度
     */
    private String cpuIdle;
    
    /**
     * 内存空闲度
     */
    private String memoryIdle;
    
    /**
     * 硬盘空闲度
     */
    private String hardDiskIdle;
    
    /**
     * 体检总得分
     */
    private int checkPoint;
    
    private String url;

    private String cmdKey;

    private String appRet;

    private String httpStatusCode;
    
    public CheckInfoForm(){
        
    }

    public String getCpuIdle() {
        return cpuIdle;
    }

    public void setCpuIdle(String cpuIdle) {
        this.cpuIdle = cpuIdle;
    }

    public String getMemoryIdle() {
        return memoryIdle;
    }

    public void setMemoryIdle(String memoryIdle) {
        this.memoryIdle = memoryIdle;
    }

    public String getHardDiskIdle() {
        return hardDiskIdle;
    }

    public void setHardDiskIdle(String hardDiskIdle) {
        this.hardDiskIdle = hardDiskIdle;
    }

    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCmdKey() {
        return cmdKey;
    }

    public void setCmdKey(String cmdKey) {
        this.cmdKey = cmdKey;
    }

    public String getAppRet() {
        return appRet;
    }

    public void setAppRet(String appRet) {
        this.appRet = appRet;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
    
}
