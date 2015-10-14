package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.api.device.NetType;

/**
 * 设备扩展信息表
 *
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 上午11:29:03
 */
@Entity
@Table(name = "DEV_EXTEND")
public class DeviceExtend implements IDeviceExtend, Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_EXTEND")
	@SequenceGenerator(name = "SEQ_DEV_EXTEND", sequenceName = "SEQ_DEV_EXTEND")
	@Column(name = "ID")
	private long id;

	/**
	 * 设备号
	 */
	@Column(name = "TEMINAL_ID", length = 20, unique = true)
	private String terminalId;

	/**
	 * 设备序列号
	 */
	@Column(name = "SERIAL", length = 40)
	private String serial;

	/**
	 * 设备购买日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BUY_DATE", length = 10)
	private Date buyDate;

	/**
	 * 设备安装日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "INSTALL_DATE", length = 10)
	private Date installDate;

	/**
	 * 设备启用日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 10)
	private Date startDate;

	/**
	 * 设备信用日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "STOP_DATE", length = 10)
	private Date stopDate;

	/**
	 * 每日开机时间 format(HH:mm:ss)
	 */
	@Column(name = "OPEN_TIME", length = 10)
	private String openTime;

	/**
	 * 每日关机时间 format(HH:mm:ss)
	 */
	@Column(name = "CLOSE_TIME", length = 10)
	private String closeTime;

	/**
	 * 保修到期日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_DATE", length = 10)
	private Date expireDate;

	/**
	 * 设备巡检周期
	 */
	@Column(name = "PATROL_PERIOD")
	private int patrolPeriod;

	/**
	 * 上次巡检日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_PM_DATE", length = 10)
	private Date lastPmDate;

	/**
	 * 巡检到期日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_PM_DATE", length = 10)
	private Date expirePmDate;

	/**
	 * 运营商
	 */
	@Column(name = "CARRIER", length = 20)
	private String carrier;

	/**
	 * 加钞机构
	 */
	@Column(name = "MONEY_ORG", length = 20)
	private String moneyOrg;

	/**
	 * 入账成本. 单位：元
	 */
	@Column(name = "PRICE")
	private double price;

	/**
	 * 折旧年限. 单位：年
	 */
	@Column(name = "DEPRECIATION_LIFE")
	private double depreciationLife;

	/**
	 * 装修费用
	 */
	@Column(name = "DECORATION")
	private double decoration;

	/**
	 * 装修摊销年限.　单位：年
	 */
	@Column(name = "DECORATION_COST")
	private double decorationCost;

	/**
	 * 物业租赁费. 单位：元/月
	 */
	@Column(name = "GOVERNANCE_RENT")
	private double governanceRent;

	/**
	 * 物业管理费用.　单位：元/月
	 */
	@Column(name = "GOVERNANCE_COST")
	private double governanceCost;

	/**
	 * 通讯线路费用．单位：元/月
	 */
	@Column(name = "NET_COST")
	private double netCost;

	/**
	 * 电费．单位：元/月
	 */
	@Column(name = "POWER_COST")
	private double powerCost;

	/**
	 * 加钞维护费用．单位：元/次
	 */
	@Column(name = "MONEY_COST")
	private double moneyCost;

	/**
	 * 资金成本利率
	 */
	@Column(name = "COST_INTEREST", length = 50)
	private String costInterest;

	/**
	 * 网络类型
	 */
	@Column(name = "NET_TYPE", length = 2)
	private NetType netType;

	@OneToOne(targetEntity = Device.class, mappedBy = "deviceExtend")
	private IDevice device;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getPatrolPeriod() {
        return patrolPeriod;
    }

    public void setPatrolPeriod(int patrolPeriod) {
        this.patrolPeriod = patrolPeriod;
    }

    public Date getLastPmDate() {
        return lastPmDate;
    }

    public void setLastPmDate(Date lastPmDate) {
        this.lastPmDate = lastPmDate;
    }

    public Date getExpirePmDate() {
        return expirePmDate;
    }

    public void setExpirePmDate(Date expirePmDate) {
        this.expirePmDate = expirePmDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMoneyOrg() {
        return moneyOrg;
    }

    public void setMoneyOrg(String moneyOrg) {
        this.moneyOrg = moneyOrg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDepreciationLife() {
        return depreciationLife;
    }

    public void setDepreciationLife(double depreciationLife) {
        this.depreciationLife = depreciationLife;
    }

    public double getDecoration() {
        return decoration;
    }

    public void setDecoration(double decoration) {
        this.decoration = decoration;
    }

    public double getDecorationCost() {
        return decorationCost;
    }

    public void setDecorationCost(double decorationCost) {
        this.decorationCost = decorationCost;
    }

    public double getGovernanceRent() {
        return governanceRent;
    }

    public void setGovernanceRent(double governanceRent) {
        this.governanceRent = governanceRent;
    }

    public double getGovernanceCost() {
        return governanceCost;
    }

    public void setGovernanceCost(double governanceCost) {
        this.governanceCost = governanceCost;
    }

    public double getNetCost() {
        return netCost;
    }

    public void setNetCost(double netCost) {
        this.netCost = netCost;
    }

    public double getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(double powerCost) {
        this.powerCost = powerCost;
    }

    public double getMoneyCost() {
        return moneyCost;
    }

    public void setMoneyCost(double moneyCost) {
        this.moneyCost = moneyCost;
    }

    public String getCostInterest() {
        return costInterest;
    }

    public void setCostInterest(String costInterest) {
        this.costInterest = costInterest;
    }

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }

    public IDevice getDevice() {
        return device;
    }

    public void setDevice(IDevice device) {
        this.device = device;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
