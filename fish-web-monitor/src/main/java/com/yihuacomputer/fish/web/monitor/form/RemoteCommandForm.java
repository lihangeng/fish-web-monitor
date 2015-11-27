package com.yihuacomputer.fish.web.monitor.form;


public class RemoteCommandForm {
    private long id;

    /**
     * 日期时间:yyyy-MM-dd HH:mm:ss
     */
    private String datetime;

    /**
     * 设备号
     */
    private String terminalId;

    /**
     * 远程命令类型(重启(正常),重启(强制),开启服务，暂停服务，强制复位)
     */
    private String commandType;

    /**
     * 远程命令执行结果(执行中，执行成功，执行失败，连接失败)
     */
    private String commandResult;

    /**
     * 操作人
     */
    private String handlePerson;

    /**
     * 所属机构
     */
    private String orgName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
