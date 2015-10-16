package com.yihuacomputer.fish.monitor.entity.report;


import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;

public class ClassifyReport implements IClassifyReport {

    private long id;
    /** 设备号 */
    private String code;

    private ReportMedthod method;// 模块数据方法 新增，更新，删除

    private ReportMedthod boxMethod;// 钞箱数据方法 新增，更新，删除

    private ReportMedthod netMethod;// 网络数据方法 新增，更新，删除

    /** 设备所属机构 */
    private String org;

    /** 设备类型 */
    private String type;

    /** 经营方式 */
    private String seviceMode;

    /** 在行标志 */
    private String insideOutside;

    /** IP地址 */
    private String ip;

    /** 安装地址 */
    private String address;

    /** ATMC应用版本号 */
    private String appRelease;

    /** 运行状态 */
    private String runStatus;

    private RunStatus run;

    private DeviceStatus mod;

    private NetStatus net;

    private BoxStatus box;

    /** 钱箱初始金额 */
    private String boxInitCount;

    /** 模块状态 */
    private String modStatus;

    /** 钱箱当前金额 */
    private String boxCurrentCount;

    /** 报警金额阈值 */
    private String cashboxLimit;

    /** 钱箱状态 */
    private String boxStatus;

    /** 设备吞卡数量 */
    private String retainCardCount;

    /** 忘了状态 */
    private String netStatus;

    /** 注册状态 */
    private String registerStatus;

    /** 读卡器状态 */
    private DeviceStatus idcStatus;

    /** 取款模块状态 */
    private DeviceStatus cimStatus;

    /** 取款模块状态 */
    private DeviceStatus cdmStatus;

    /** 凭条打印机状态 */
    private DeviceStatus rprStatus;

    /** 日志打印机状态 */
    private DeviceStatus jprStatus;

    /** 密码键盘状态 */
    private DeviceStatus pinStatus;

    /** 传感器状态 */
    private DeviceStatus siuStatus;

    /** 文本终端状态 */
    private DeviceStatus ttuStatus;

    /**存折打印模块状态*/
    private DeviceStatus pbkStatus;

    /**射频读卡器状态*/
    private DeviceStatus nfcStauts;

    /**发卡读卡器状态*/
	private DeviceStatus iccStatus;

	/**指纹仪状态*/
	private DeviceStatus fgpStatus;

	/**身份证读卡器状态*/
	private DeviceStatus iscStatus;

    /**
     * 地图坐标经度
     */
    private String latitude;

    /**
     * 地图坐标纬度
     */
    private String longtitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReportMedthod getMethod() {
        return method;
    }

    public void setMethod(ReportMedthod method) {
        this.method = method;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeviceMode() {
        return seviceMode;
    }

    public void setSeviceMode(String seviceMode) {
        this.seviceMode = seviceMode;
    }

    public String getInsideOutside() {
        return insideOutside;
    }

    public void setInsideOutside(String insideOutside) {
        this.insideOutside = insideOutside;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppRelease() {
        return appRelease;
    }

    public void setAppRelease(String appRelease) {
        this.appRelease = appRelease;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getBoxInitCount() {
        return boxInitCount;
    }

    public void setBoxInitCount(String boxInitCount) {
        this.boxInitCount = boxInitCount;
    }

    public String getModStatus() {
        return modStatus;
    }

    public void setModStatus(String modStatus) {
        this.modStatus = modStatus;
    }

    public String getBoxCurrentCount() {
        return boxCurrentCount;
    }

    public void setBoxCurrentCount(String boxCurrentCount) {
        this.boxCurrentCount = boxCurrentCount;
    }

    public String getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(String boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getRetainCardCount() {
        return retainCardCount;
    }

    public void setRetainCardCount(String retainCardCount) {
        this.retainCardCount = retainCardCount;
    }

    public String getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(String netStatus) {
        this.netStatus = netStatus;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public DeviceStatus getIdcStatus() {
        return idcStatus;
    }

    public void setIdcStatus(DeviceStatus idcStatus) {
        this.idcStatus = idcStatus;
    }

    public DeviceStatus getCimStatus() {
        return cimStatus;
    }

    public void setCimStatus(DeviceStatus cimStatus) {
        this.cimStatus = cimStatus;
    }

    public DeviceStatus getCdmStatus() {
        return cdmStatus;
    }

    public void setCdmStatus(DeviceStatus cdmStatus) {
        this.cdmStatus = cdmStatus;
    }

    public DeviceStatus getRprStatus() {
        return rprStatus;
    }

    public void setRprStatus(DeviceStatus rprStatus) {
        this.rprStatus = rprStatus;
    }

    public DeviceStatus getJprStatus() {
        return jprStatus;
    }

    public void setJprStatus(DeviceStatus jprStatus) {
        this.jprStatus = jprStatus;
    }

    public DeviceStatus getPinStatus() {
        return pinStatus;
    }

    public void setPinStatus(DeviceStatus pinStatus) {
        this.pinStatus = pinStatus;
    }

    public DeviceStatus getSiuStatus() {
        return siuStatus;
    }

    public void setSiuStatus(DeviceStatus siuStatus) {
        this.siuStatus = siuStatus;
    }

    public DeviceStatus getTtuStatus() {
        return ttuStatus;
    }

    public void setTtuStatus(DeviceStatus ttuStatus) {
        this.ttuStatus = ttuStatus;
    }

    public RunStatus getRun() {
        return run;
    }

    public void setRun(RunStatus run) {
        this.run = run;
    }

    public DeviceStatus getMod() {
        return mod;
    }

    public void setMod(DeviceStatus mod) {
        this.mod = mod;
    }

    public NetStatus getNet() {
        return net;
    }

    public void setNet(NetStatus net) {
        this.net = net;
    }

    public BoxStatus getBox() {
        return box;
    }

    public void setBox(BoxStatus box) {
        this.box = box;
    }

    public String getCashboxLimit() {
        return cashboxLimit;
    }

    public void setCashboxLimit(String cashboxLimit) {
        this.cashboxLimit = cashboxLimit;
    }

    /**
     * 构造函数
     */
    public ClassifyReport() {

    }

//    private MessageSource messageSourceEnum;
//    
//    public MessageSource getMessageSourceEnum() {
//		return messageSourceEnum;
//	}
//
//	public void setMessageSourceEnum(MessageSource messageSourceEnum) {
//		this.messageSourceEnum = messageSourceEnum;
//	}

	/**
     * 格式化设备状态监控页面显示信息
     *
     * @param device
     * @return
     */
    public void setStatusReport(IDeviceReport device) {
        try {

            this.code = device.getDeviceId();
            if (device.getRunInfo() != null) {
                this.runStatus = device.getRunInfo().getRunStatus().getText();
                this.run = device.getRunInfo().getRunStatus();
            }
            if (device.getXfsStatus() != null) {
                this.boxStatus = getInfo(device.getXfsStatus().getBoxStatus().getText());
                this.modStatus = getInfo(device.getXfsStatus().getModStatus().getText());
                this.netStatus = getInfo(device.getXfsStatus().getNetStatus().getText());

                this.box = device.getXfsStatus().getBoxStatus();
                this.mod = device.getXfsStatus().getModStatus();
                this.net = device.getXfsStatus().getNetStatus();

                this.retainCardCount = getInfo(device.getXfsStatus().getStatusIdc().getCards());
                this.idcStatus = device.getXfsStatus().getStatusIdc()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIdc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIdc().getStatus();
                this.cdmStatus = device.getXfsStatus().getStatusCdm()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCdm().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCdm().getStatus();
                this.cimStatus = device.getXfsStatus().getStatusCim()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCim().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCim().getStatus();
                this.rprStatus = device.getXfsStatus().getStatusRpr()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusRpr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusRpr().getStatus();
                this.jprStatus = device.getXfsStatus().getStatusJpr()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusJpr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusJpr().getStatus();
                this.pinStatus = device.getXfsStatus().getStatusPin()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPin().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPin().getStatus();
                this.ttuStatus = device.getXfsStatus().getStatusTtu()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusTtu().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusTtu().getStatus();
                this.siuStatus = device.getXfsStatus().getStatusSiu()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusSiu().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusSiu().getStatus();
                this.nfcStauts = device.getXfsStatus().getStatusNfc()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusNfc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusNfc().getStatus();
                this.pbkStatus = device.getXfsStatus().getStatusPbk()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPbk().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPbk().getStatus();
                this.fgpStatus = device.getXfsStatus().getStatusFgp()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusFgp().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusFgp().getStatus();
                this.iccStatus = device.getXfsStatus().getStatusIcc()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIcc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIcc().getStatus();
                this.iscStatus = device.getXfsStatus().getStatusIsc()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIsc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIsc().getStatus();


                this.boxInitCount = getInfo(device.getXfsStatus().getBoxInitCount());
                this.boxCurrentCount = getInfo(device.getXfsStatus().getBoxCurrentCount());

            }

            if (device.getDevice() != null) {
            	this.id = device.getDevice().getId();

                this.ip = device.getDevice().getIp().toString();
                this.address = device.getDevice().getAddress();
                this.org = device.getDevice().getOrganization().getName();
                this.type = device.getDevice().getDevType().getName();
                this.insideOutside = getInfo(device.getDevice().getAwayFlag().getText());
                this.seviceMode = getInfo(device.getDevice().getWorkType().getText());
                this.cashboxLimit = getInfo(device.getDevice().getCashboxLimit());
            }

            if (device.getDeviceRegister() != null) {
                this.appRelease = device.getDeviceRegister().getAtmcVersion();
                this.registerStatus = device.getDeviceRegister().getRegStatus().getText();
            }
        }
        catch (Exception e) {
            return;
        }
    }

    private String getInfo(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    public void setDeviceSign(IDeviceReport device) {
        this.code = device.getDeviceId();
        this.registerStatus = device.getDeviceRegister().getRegStatus().getText();
        this.appRelease = device.getDeviceRegister().getAtmcVersion();
    }

    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String getLatitude() {
        return this.latitude;
    }

    @Override
    public String getLongtitude() {
        return this.longtitude;
    }

    @Override
    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

	public DeviceStatus getPbkStatus() {
		return pbkStatus;
	}

	public void setPbkStatus(DeviceStatus pbkStatus) {
		this.pbkStatus = pbkStatus;
	}

	public DeviceStatus getNfcStauts() {
		return nfcStauts;
	}

	public void setNfcStauts(DeviceStatus nfcStauts) {
		this.nfcStauts = nfcStauts;
	}

	public ReportMedthod getBoxMethod() {
		return boxMethod;
	}

	public void setBoxMethod(ReportMedthod boxMethod) {
		this.boxMethod = boxMethod;
	}

	public ReportMedthod getNetMethod() {
		return netMethod;
	}

	public void setNetMethod(ReportMedthod netMethod) {
		this.netMethod = netMethod;
	}

	public DeviceStatus getIccStatus() {
		return iccStatus;
	}

	public void setIccStatus(DeviceStatus iccStatus) {
		this.iccStatus = iccStatus;
	}

	public DeviceStatus getFgpStatus() {
		return fgpStatus;
	}

	public void setFgpStatus(DeviceStatus fgpStatus) {
		this.fgpStatus = fgpStatus;
	}

	public DeviceStatus getIscStatus() {
		return iscStatus;
	}

	public void setIscStatus(DeviceStatus iscStatus) {
		this.iscStatus = iscStatus;
	}

}
