package com.yihuacomputer.fish.web.daily.form;
/**
 * 实时监控－－设备状态
 * <Br>
 * 设备模块状态
 * 
 * @TODO 说明：这里的属性应该用状态来显示。目前为了显示所以用String来实现。
 * 
 * @author pengwenchao
 * @date 2011-10-21
 * 
 */
public class DeviceModuleStatusForm
{
    private long id;
    /**
     * 照相模块(CMA)
     */
    private String photo;

    /**
     * 取款模块(CDM)
     */
    private String withdrawals;

    /**
     * 支票读写扫描模块(CHK)
     */
    private String checkRW;

    /**
     * 存款模块(CIM)
     */
    private String deposit;

    /**
     * 信封存款模块(DEP)
     */
    private String envelopeDeposit;

    /**
     * 读卡器模块(IDC)
     */
    private String cardReader;

    /**
     * 日志打印机模块(JPR)
     */
    private String logPrinter;

    /**
     * 密码键盘模块(PIN)
     */
    private String pinPad;

    /**
     * 存折模块(PBK)
     */
    private String bankBook;

    /**
     * 凭条打印机模块(RPR)
     */
    private String slipPrinter;

    /**
     * 传感器模块(SIU)
     */
    private String sensor;

    /**
     * 银行结单打印机(SPR)
     */
    private String bankStatements;

    /**
     * 文本终端单元(TTU)
     */
    private String textTerminalUnit;

    /**
     * 不间断电源(UPS)
     */
    private String ups;

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getWithdrawals()
    {
        return withdrawals;
    }

    public void setWithdrawals(String withdrawals)
    {
        this.withdrawals = withdrawals;
    }

    public String getCheckRW()
    {
        return checkRW;
    }

    public void setCheckRW(String checkRW)
    {
        this.checkRW = checkRW;
    }

    public String getDeposit()
    {
        return deposit;
    }

    public void setDeposit(String deposit)
    {
        this.deposit = deposit;
    }

    public String getEnvelopeDeposit()
    {
        return envelopeDeposit;
    }

    public void setEnvelopeDeposit(String envelopeDeposit)
    {
        this.envelopeDeposit = envelopeDeposit;
    }

    public String getCardReader()
    {
        return cardReader;
    }

    public void setCardReader(String cardReader)
    {
        this.cardReader = cardReader;
    }

    public String getLogPrinter()
    {
        return logPrinter;
    }

    public void setLogPrinter(String logPrinter)
    {
        this.logPrinter = logPrinter;
    }

    public String getPinPad()
    {
        return pinPad;
    }

    public void setPinPad(String pinPad)
    {
        this.pinPad = pinPad;
    }

    public String getBankBook()
    {
        return bankBook;
    }

    public void setBankBook(String bankBook)
    {
        this.bankBook = bankBook;
    }

    public String getSlipPrinter()
    {
        return slipPrinter;
    }

    public void setSlipPrinter(String slipPrinter)
    {
        this.slipPrinter = slipPrinter;
    }

    public String getSensor()
    {
        return sensor;
    }

    public void setSensor(String sensor)
    {
        this.sensor = sensor;
    }

    public String getBankStatements()
    {
        return bankStatements;
    }

    public void setBankStatements(String bankStatements)
    {
        this.bankStatements = bankStatements;
    }

    public String getTextTerminalUnit()
    {
        return textTerminalUnit;
    }

    public void setTextTerminalUnit(String textTerminalUnit)
    {
        this.textTerminalUnit = textTerminalUnit;
    }

    public String getUps()
    {
        return ups;
    }

    public void setUps(String ups)
    {
        this.ups = ups;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "DeviceModuleStatusForm [id=" + id + ", photo=" + photo
                + ", withdrawals=" + withdrawals + ", checkRW=" + checkRW
                + ", deposit=" + deposit + ", envelopeDeposit="
                + envelopeDeposit + ", cardReader=" + cardReader
                + ", logPrinter=" + logPrinter + ", pinPad=" + pinPad
                + ", bankBook=" + bankBook + ", slipPrinter=" + slipPrinter
                + ", sensor=" + sensor + ", bankStatements=" + bankStatements
                + ", textTerminalUnit=" + textTerminalUnit + ", ups=" + ups
                + "]";
    }

}
