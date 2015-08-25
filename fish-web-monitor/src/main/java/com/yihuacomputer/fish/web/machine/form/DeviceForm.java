package com.yihuacomputer.fish.web.machine.form;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.CareLevel;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.device.WorkType;

public class DeviceForm {
    private String id;

    /**
     * 设备号
     */
    private String terminalId;

    /**
     * 设备IP地址
     */
    private String ip;

    /**
     * 所属机构
     */
    private String orgId;

    private String orgName;

    /**
     * 设备型号
     */
    private long devTypeId;

    private String devTypeName;

    private String devVendorName;

    private String devCatalogName;

    /**
     * 设备状态
     */
    private String status;

    /**
     * 设备维护商name
     */
    private String devServiceName;

    /**
     * 设备维护商ID
     */
    private String devServiceId;

    /**
     * 设备地址
     */
    private String address;

    /**
     * 钱箱报警金额．单位：张数
     */
    private int cashboxLimit;

    /**
     * 设备序列号
     */
    private String serial;

    /**
     * 设备购买日期 format(yyyy-MM-dd)
     */
    private String buyDate;

    /**
     * 设备安装日期 format(yyyy-MM-dd)
     */
    private String installDate;

    /**
     * 设备启用日期 format(yyyy-MM-dd)
     */
    private String startDate;

    /**
     * 设备信用日期 format(yyyy-MM-dd)
     */
    private String stopDate;

    /**
     * 每日开机时间 format(HH:mm:ss)
     */
    private String openTime;

    private String openTimeHour;

    private String openTimeMinute;

    private String openTimeSecond;

    /**
     * 每日关机时间 format(HH:mm:ss)
     */
    private String closeTime;

    private String closeTimeHour;

    private String closeTimeMinute;

    private String closeTimeSecond;

    /**
     * 保修到期日期 format(yyyy-MM-dd)
     */
    private String expireDate;

    /**
     * 设备巡检周期
     */
    private int patrolPeriod;

    /**
     * 设备关注程序
     */
    private String careLevel;

    /**
     * 上次巡检日期
     */
    private String lastPmDate;

    /**
     * 巡检到期日期
     */
    private String expirePmDate;

    /**
     * 非现金标志
     */
    private String cashType;

    /**
     * 安装方式
     */
    private String setupType;

    /**
     * 运营商
     */
    private String carrier;

    /**
     * 加钞机构
     */
    private String moneyOrg;

    /**
     * 入账成本. 单位：元
     */
    private double price;

    /**
     * 折旧年限. 单位：年
     */
    private double depreciationLife;

    /**
     * 装修费用
     */
    private double decoration;

    /**
     * 装修摊销年限.　单位：年
     */
    private double decorationCost;

    /**
     * 物业租赁费. 单位：元/月
     */
    private double governanceRent;

    /**
     * 物业管理费用.　单位：元/月
     */
    private double governanceCost;

    /**
     * 通讯线路费用．单位：元/月
     */
    private double netCost;

    /**
     * 电费．单位：元/月
     */
    private double powerCost;

    /**
     * 加钞维护费用．单位：元/次
     */
    private double moneyCost;

    /**
     * 资金成本利率
     */
    private String costInterest;

    /**
     * atmc软件
     */
    private String atmcSoft;

    /**
     * 厂商sp类型
     */
    private String sp;

    /**
     * 网络类型
     */
    private String netType;

    /**
     * 在行离行标志
     */
    private String awayFlag;

    /**
     * 经营方式
     */
    private String workType;

    /**
     * 虚拟柜员号
     */
	public String virtual;


    public DeviceForm() {
    }

    /**
     * 将接口数据保存至本地
     *
     * @param device
     *            接口
     * @param isDate
     *            是否需要转换日期
     */
    public DeviceForm(IDevice device) {
        setAddress(device.getAddress());
        setCashboxLimit(device.getCashboxLimit());
        setAwayFlag(device.getAwayFlag() == null ? null : String.valueOf(device.getAwayFlag().getId()));
        setCareLevel(device.getCareLevel() == null ? null : String.valueOf(device.getCareLevel().getId()));
        setCashType(device.getCashType() == null ? null : String.valueOf(device.getCashType().getId()));
        setSetupType(device.getSetupType() == null ? null : String.valueOf(device.getSetupType().getId()));
        setWorkType(device.getWorkType() == null ? null : String.valueOf(device.getWorkType().getId()));
        setAtmcSoft(device.getAtmcSoft());
        setSp(device.getSp());
        setVirtual(device.getVirtual());
        if (device.getDeviceExtend() != null) {
            IDeviceExtend deviceExtend = device.getDeviceExtend();

            setNetType(deviceExtend.getNetType() == null ? null : String.valueOf(deviceExtend.getNetType().getId()));

            setBuyDate(nullString(deviceExtend.getBuyDate()));
            setExpireDate(nullString(deviceExtend.getExpireDate()));
            setExpirePmDate(nullString(deviceExtend.getExpirePmDate()));
            setInstallDate(nullString(deviceExtend.getInstallDate()));
            setLastPmDate(nullString(deviceExtend.getLastPmDate()));
            setStartDate(nullString(deviceExtend.getStartDate()));
            setStopDate(nullString(deviceExtend.getStopDate()));

            // setOpenTime(deviceExtend.getOpenTime().toString());
            // setCloseTime(deviceExtend.getCloseTime().toString());

            if (deviceExtend.getOpenTime() != null && deviceExtend.getOpenTime().matches("\\d{2}:\\d{2}:\\d{2}")) {
                String[] openTimes = deviceExtend.getOpenTime().split(":");
                setOpenTimeHour(openTimes[0]);
                setOpenTimeMinute(openTimes[1]);
                setOpenTimeSecond(openTimes[2]);
            }
            if (deviceExtend.getCloseTime() != null && deviceExtend.getCloseTime().matches("\\d{2}:\\d{2}:\\d{2}")) {
                String[] closeTimes = deviceExtend.getCloseTime().split(":");
                setCloseTimeHour(closeTimes[0]);
                setCloseTimeMinute(closeTimes[1]);
                setCloseTimeSecond(closeTimes[2]);
            }

            setCarrier(deviceExtend.getCarrier());
            setCostInterest(deviceExtend.getCostInterest());
            setDecoration(deviceExtend.getDecoration());
            setDecorationCost(deviceExtend.getDecorationCost());
            setDepreciationLife(deviceExtend.getDepreciationLife());
            setGovernanceCost(deviceExtend.getGovernanceCost());
            setGovernanceRent(deviceExtend.getGovernanceRent());
            setMoneyCost(deviceExtend.getMoneyCost());
            setMoneyOrg(deviceExtend.getMoneyOrg());
            setNetCost(deviceExtend.getNetCost());
            setTerminalId(deviceExtend.getTerminalId());
            setPatrolPeriod(deviceExtend.getPatrolPeriod());
            setPowerCost(deviceExtend.getPowerCost());
            setPrice(deviceExtend.getPrice());
            setSerial(deviceExtend.getSerial());
        }

        if (device.getDevService() != null) {
            setDevServiceName(device.getDevService().getName());
            setDevServiceId(device.getDevService().getGuid());
        }

        if (device.getDevType() != null) {
            setDevTypeId(device.getDevType().getId());
            setDevTypeName(device.getDevType().getName());
            setDevCatalogName(device.getDevType().getDevCatalog().getName());
            setDevVendorName(device.getDevType().getDevVendor().getName());
        }
        setId(String.valueOf(device.getId()));
        setIp(device.getIp().toString());
        if (device.getOrganization() != null) {
            setOrgId(device.getOrganization().getGuid());
            setOrgName(device.getOrganization().getName());
        }

        setStatus(device.getStatus() == null ? null : String.valueOf(device.getStatus().getId()));

        setTerminalId(device.getTerminalId());

    }

    /**
     * 本地数据保存至接口
     *
     * @param device
     *            接口
     */
    public void translate(IDevice device) {
        device.setAddress(getAddress());
        device.setCashboxLimit(getCashboxLimit());

//        device.getDevService().setGuid(getDevServiceId());

        // device.setId(getId());
        device.setIp(new IP(getIp()));
        device.setTerminalId(getTerminalId());
        // 机构
//        device.getOrganization().setGuid(getOrgId());
        // device.setOrganization(org);
        // org.setName(getOrgName());

        // 型号
//        device.getDevType().setId(getDevTypeId());
        // device.setDevType(type);
        // atmType.setName(getDevTypeName());

        // 品牌
        // IAtmVendor atmVendor = atmType.getDevVendor();
        // atmVendor.setId(getDevVendorId());
        // atmVendor.setName(getDevVendorName());

        // 类型
        // IAtmCatalog atmCatalog = atmType.getDevCatalog();
        // atmCatalog.setId(getDevCatalogId());
        // atmCatalog.setName(getDevCatalogName());

        // 扩展信息
        IDeviceExtend deviceExtend = device.getDeviceExtend();
        device.setAtmcSoft(getAtmcSoft());
        device.setVirtual(getVirtual());
        device.setStatus(nullObject(getStatus(), Status.class));
        device.setAwayFlag(nullObject(getAwayFlag(), AwayFlag.class));
        device.setCareLevel(nullObject(getCareLevel(), CareLevel.class));
        device.setCashType(nullObject(getCashType(), CashType.class));
        deviceExtend.setNetType(nullObject(getNetType(), NetType.class));
        // deviceExtend.setRegStatus(nullObject(getRegStatus(),
        // RegStatus.class));
        device.setSetupType(nullObject(getSetupType(), SetupType.class));
        device.setWorkType(nullObject(getWorkType(), WorkType.class));

        if (getCloseTimeHour() == null || getCloseTimeMinute() == null || getCloseTimeSecond() == null) {
            deviceExtend.setCloseTime(null);
        } else {
            deviceExtend.setCloseTime(getCloseTimeHour() + ":" + getCloseTimeMinute() + ":" + getCloseTimeSecond());
        }
        if (getOpenTimeHour() == null || getOpenTimeMinute() == null || getOpenTimeSecond() == null) {
            deviceExtend.setOpenTime(null);
        } else {
            deviceExtend.setOpenTime(getOpenTimeHour() + ":" + getOpenTimeMinute() + ":" + getOpenTimeSecond());
        }

        deviceExtend.setBuyDate(nullDate(getBuyDate()));
        deviceExtend.setExpireDate(nullDate(getExpireDate()));
        deviceExtend.setExpirePmDate(nullDate(getExpirePmDate()));
        deviceExtend.setInstallDate(nullDate(getInstallDate()));
        deviceExtend.setLastPmDate(nullDate(getLastPmDate()));
        deviceExtend.setStartDate(nullDate(getStartDate()));
        deviceExtend.setStopDate(nullDate(getStopDate()));
        deviceExtend.setCarrier(getCarrier());
        deviceExtend.setCostInterest(getCostInterest());
        deviceExtend.setDecoration(getDecoration());
        deviceExtend.setDecorationCost(getDecorationCost());
        deviceExtend.setDepreciationLife(getDepreciationLife());
        deviceExtend.setGovernanceCost(getGovernanceCost());
        deviceExtend.setGovernanceRent(getGovernanceRent());
        // deviceExtend.setId(getId());
        deviceExtend.setMoneyCost(getMoneyCost());
        deviceExtend.setMoneyOrg(getMoneyOrg());
        deviceExtend.setNetCost(getNetCost());
        deviceExtend.setTerminalId(getTerminalId());
        deviceExtend.setPatrolPeriod(getPatrolPeriod());
        deviceExtend.setPowerCost(getPowerCost());
        deviceExtend.setPrice(getPrice());
        deviceExtend.setSerial(getSerial());
        device.setSp(getSp());
        // deviceExtend.setTrminate(getTrminate());
    }

    public static List<DeviceForm> convert(List<IDevice> list) {
        List<DeviceForm> result = new ArrayList<DeviceForm>();
        for (IDevice item : list) {
            result.add(new DeviceForm(item));
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevServiceName() {
        return devServiceName;
    }

    public void setDevServiceName(String devServiceName) {
        this.devServiceName = devServiceName;
    }

    public String getDevServiceId() {
        return devServiceId;
    }

    public void setDevServiceId(String devServiceId) {
        this.devServiceId = devServiceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCashboxLimit() {
        return cashboxLimit;
    }

    public void setCashboxLimit(int cashboxLimit) {
        this.cashboxLimit = cashboxLimit;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getDevTypeId() {
        return devTypeId;
    }

    public void setDevTypeId(long devTypeId) {
        this.devTypeId = devTypeId;
    }

    public String getDevTypeName() {
        return devTypeName;
    }

    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }

    // public long getDevVendorId()
    // {
    // return devVendorId;
    // }

    // public void setDevVendorId(long devVendorId)
    // {
    // this.devVendorId = devVendorId;
    // }

    public String getDevVendorName() {
        return devVendorName;
    }

    public void setDevVendorName(String devVendorName) {
        this.devVendorName = devVendorName;
    }

    // public long getDevCatalogId()
    // {
    // return devCatalogId;
    // }

    // public void setDevCatalogId(long devCatalogId)
    // {
    // this.devCatalogId = devCatalogId;
    // }

    public String getDevCatalogName() {
        return devCatalogName;
    }

    public void setDevCatalogName(String devCatalogName) {
        this.devCatalogName = devCatalogName;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
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

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getPatrolPeriod() {
        return patrolPeriod;
    }

    public void setPatrolPeriod(int patrolPeriod) {
        this.patrolPeriod = patrolPeriod;
    }

    public String getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }

    public String getLastPmDate() {
        return lastPmDate;
    }

    public void setLastPmDate(String lastPmDate) {
        this.lastPmDate = lastPmDate;
    }

    public String getExpirePmDate() {
        return expirePmDate;
    }

    public void setExpirePmDate(String expirePmDate) {
        this.expirePmDate = expirePmDate;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getSetupType() {
        return setupType;
    }

    public void setSetupType(String setupType) {
        this.setupType = setupType;
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

    public String getAtmcSoft() {
        return atmcSoft;
    }

    public void setAtmcSoft(String atmcSoft) {
        this.atmcSoft = atmcSoft;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getAwayFlag() {
        return awayFlag;
    }

    public void setAwayFlag(String awayFlag) {
        this.awayFlag = awayFlag;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getOpenTimeHour() {
        return openTimeHour;
    }

    public void setOpenTimeHour(String openTimeHour) {
        this.openTimeHour = openTimeHour;
    }

    public String getOpenTimeMinute() {
        return openTimeMinute;
    }

    public void setOpenTimeMinute(String openTimeMinute) {
        this.openTimeMinute = openTimeMinute;
    }

    public String getOpenTimeSecond() {
        return openTimeSecond;
    }

    public void setOpenTimeSecond(String openTimeSecond) {
        this.openTimeSecond = openTimeSecond;
    }

    public String getCloseTimeHour() {
        return closeTimeHour;
    }

    public void setCloseTimeHour(String closeTimeHour) {
        this.closeTimeHour = closeTimeHour;
    }

    public String getCloseTimeMinute() {
        return closeTimeMinute;
    }

    public void setCloseTimeMinute(String closeTimeMinute) {
        this.closeTimeMinute = closeTimeMinute;
    }

    public String getCloseTimeSecond() {
        return closeTimeSecond;
    }

    public void setCloseTimeSecond(String closeTimeSecond) {
        this.closeTimeSecond = closeTimeSecond;
    }

    private String nullString(Date date) {
        if (date == null) {
            return null;
        }
        return DateUtils.getDate(date);
    }

    private Date nullDate(String string) {
        if (string == null || "".equals(string)) {
            return null;
        }
        return DateUtils.getDate(string);
    }

    @SuppressWarnings("unchecked")
    private <T> T nullObject(String value, Class<T> targetClass) {
        T resultObj = null;
        if (value == null || "".equals(value)) {
            return null;
        }
        try {
            Method method = targetClass.getMethod("getById", new Class[]{int.class});
            resultObj = (T) method.invoke(null, new Object[]{Integer.valueOf(value)});
        }
        catch (Exception e) {
        }
        return resultObj;
    }

	public String getVirtual() {
		return virtual;
	}

	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}


}
