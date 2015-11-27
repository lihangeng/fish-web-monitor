package com.yihuacomputer.fish.api.monitor.business;

import com.yihuacomputer.fish.api.device.IDevice;

public interface IRemoteCommHist {

    /**
     * 主键,唯一
     * 
     * @return
     */
    public long getId();

    public void setId(long id);

    /**
     * 执行日期时间 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public String getDatetime();

    public void setDatetime(String datetime);

    // /**
    // * 设备号
    // *
    // * @return
    // */
    // public String getTerminalId();
    //
    // public void setTerminalId(String terminalId);

    /**
     * 命令类型
     * 
     * @return
     */
    public CommandType getCommandType();

    public void setCommandType(CommandType commandType);

    /**
     * 命令执行结果
     * 
     * @return
     */
    public CommandResult getCommandResult();

    public void setCommandResult(CommandResult commandResult);

    /**
     * 操作人
     * 
     * @return
     */
    public String getHandlePerson();

    public void setHandlePerson(String handlePerson);

    /**
     * 设备对象
     * 
     * @return
     */
    public IDevice getDevice();

    public void setDevice(IDevice device);
}
