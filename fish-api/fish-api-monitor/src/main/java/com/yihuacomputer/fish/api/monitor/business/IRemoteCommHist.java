package com.yihuacomputer.fish.api.monitor.business;


/**
 * @author YiHua
 *
 */
public interface IRemoteCommHist {

    /**
     * 主键,唯一
     * 
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 执行日期时间 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public String getDatetime();

    /**
     * @param datetime
     */
    public void setDatetime(String datetime);

     /**
     * 设备号
     *
     * @return
     */
     public String getTerminalId();
    
     /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * 命令类型
     * 
     * @return
     */
    public CommandType getCommandType();

    /**
     * @param commandType
     */
    public void setCommandType(CommandType commandType);

    /**
     * 命令执行结果
     * 
     * @return
     */
    public CommandResult getCommandResult();

    /**
     * @param commandResult
     */
    public void setCommandResult(CommandResult commandResult);

    /**
     * 操作人
     * 
     * @return
     */
    public String getHandlePerson();

    /**
     * @param handlePerson
     */
    public void setHandlePerson(String handlePerson);

}
