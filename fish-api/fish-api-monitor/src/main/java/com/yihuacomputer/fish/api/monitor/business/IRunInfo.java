package com.yihuacomputer.fish.api.monitor.business;

/**
 * C端应用运行状态通知报文.
 * 
 * @author huxiaobao
 * 
 */
public interface IRunInfo {

    /**
     * 设置终端号.
     * 
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * 设置应用状态.
     * 
     * @param runStatus
     */
    public void setRunStatus(RunStatus runStatus);

    /**
     * 获取应用状态.
     * 
     * @return
     */
    public RunStatus getRunStatus();

    /**
     * 设置应用状态更新时间
     * 
     * @param statusTime
     */
    public void setStatusTime(String statusTime);

    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public String getTerminalId();

    /**
     * @return
     */
    public String getStatusTime();

}
