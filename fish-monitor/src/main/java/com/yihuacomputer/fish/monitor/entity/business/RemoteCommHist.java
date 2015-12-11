package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.CommandResult;
import com.yihuacomputer.fish.api.monitor.business.CommandType;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;

/**
 * 远程命令历史表
 * 
 * @author pengwenchao
 *
 */
@Entity
@Table(name = "ATMC_COMMAND_HIST")
public class RemoteCommHist implements IRemoteCommHist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_COMMAND_HIST")
    @SequenceGenerator(name = "SEQ_ATMC_COMMAND_HIST", sequenceName = "SEQ_ATMC_COMMAND_HIST")
    @Column(name = "ID")
    private long id;

    /**
     * 日期时间:yyyy-MM-dd HH:mm:ss
     */
    @Column(name = "DATETIME")
    private String datetime;

    /**
     * 设备号
     */
    @Column(name = "TERMINAL_ID", length = 20, nullable = false)
    private String terminalId;
    
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Device.class)
//    @JoinColumn(name = "DEVICE_ID")
//    private IDevice device;

    /**
     * 远程命令类型(重启(正常),重启(强制),开启服务，暂停服务，强制复位)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "COMMAND_TYPE", length = 20)
    private CommandType commandType;

    /**
     * 远程命令执行结果(执行中，执行成功，执行失败，连接失败)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "COMMAND_RESULT", length = 20)
    private CommandResult commandResult;

    /**
     * 操作人
     */
    @Column(name = "HANDLE_PERSON")
    private String handlePerson;

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

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

//    public IDevice getDevice() {
//        return device;
//    }
//
//    public void setDevice(IDevice device) {
//        this.device = device;
//    }

}