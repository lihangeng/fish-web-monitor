package com.yihuacomputer.fish.monitor.entity.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;

public class StatusReport implements IStatusReport {
	
	private static Logger logger = LoggerFactory.getLogger(StatusReport.class);

    private long id;

    /** 设备号 */
    private String code;

    private ReportMedthod method;// 数据方法 新增，更新，删除

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
    private DeviceStatus nfcStatus;

    /**发卡读卡器状态*/
    private DeviceStatus iccStatus;

    /**指纹仪状态*/
    private DeviceStatus fgpStatus;

    /**身份证扫描仪*/
    private DeviceStatus iscStatus;
    /**摄像头*/
    private DeviceStatus camStatus;
    /**条形码扫描*/
    private DeviceStatus bcrStatus;
    /**读UKEY模块*/
    private DeviceStatus ukrStatus;
    /**发UKEY模块*/
    private DeviceStatus ukdStatus;
    
    

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
    public StatusReport() {

    }
    
    private String getEnumI18n(String enumText,MessageSource messageSourceRef){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceRef.getMessage(enumText, null, FishCfg.locale);
    }
    /**
     * 格式化设备状态监控页面显示信息
     *
     * @param device
     * @return
     */
    public void setStatusReport(IDeviceReport device,MessageSource messageSourceRef) {
        try {

            this.code = device.getDeviceId();
            if (device.getRunInfo() != null) {
                this.runStatus = getEnumI18n(device.getRunInfo().getRunStatus().getText(),messageSourceRef);
                this.run = device.getRunInfo().getRunStatus();
            }
            if (device.getXfsStatus() != null) {
                this.boxStatus = getEnumI18n(device.getXfsStatus().getBoxStatus().getText(),messageSourceRef);
                this.modStatus = getEnumI18n(device.getXfsStatus().getModStatus().getText(),messageSourceRef);
                this.netStatus = getEnumI18n(device.getXfsStatus().getNetStatus().getText(),messageSourceRef);

                this.box = device.getXfsStatus().getBoxStatus();
                this.mod = device.getXfsStatus().getModStatus();
                this.net = device.getXfsStatus().getNetStatus();

                this.retainCardCount = getInfo(device.getXfsStatus().getStatusIdc().getCards());
                this.idcStatus = device.getXfsStatus().getStatusIdc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIdc().getStatus();
                this.cdmStatus = device.getXfsStatus().getStatusCdm().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCdm().getStatus();
                this.cimStatus = device.getXfsStatus().getStatusCim().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCim().getStatus();
                this.rprStatus = device.getXfsStatus().getStatusRpr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusRpr().getStatus();
                this.jprStatus = device.getXfsStatus().getStatusJpr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusJpr().getStatus();
                this.pinStatus = device.getXfsStatus().getStatusPin().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPin().getStatus();
                this.ttuStatus = device.getXfsStatus().getStatusTtu().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusTtu().getStatus();
                this.siuStatus = device.getXfsStatus().getStatusSiu().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusSiu().getStatus();
                this.nfcStatus = device.getXfsStatus().getStatusNfc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusNfc().getStatus();
                this.pbkStatus = device.getXfsStatus().getStatusPbk().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusPbk().getStatus();
                
                this.boxInitCount = getInfo(device.getXfsStatus().getBoxInitCount());
                this.boxCurrentCount = getInfo(device.getXfsStatus().getBoxCurrentCount());

                this.iccStatus = device.getXfsStatus().getStatusIcc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIcc().getStatus();
                this.fgpStatus = device.getXfsStatus().getStatusFgp().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusFgp().getStatus();
                this.iscStatus = device.getXfsStatus().getStatusIsc().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusIsc().getStatus();
                this.bcrStatus = device.getXfsStatus().getStatusBcr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusBcr().getStatus();
                this.camStatus = device.getXfsStatus().getStatusCam().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusCam().getStatus();
                this.ukdStatus = device.getXfsStatus().getStatusUkd().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusUkd().getStatus();
                this.ukrStatus = device.getXfsStatus().getStatusUkr().getStatus()==null?DeviceStatus.Unknown:device.getXfsStatus().getStatusUkr().getStatus();
            }

            if (device.getDevice() != null) {
            	this.id = device.getDevice().getId();

                this.ip = device.getDevice().getIp().toString();
                this.address = device.getDevice().getAddress();
                this.org = device.getDevice().getOrganization().getName();
                this.type = device.getDevice().getDevType().getName();
                this.insideOutside = getEnumI18n(device.getDevice().getAwayFlag().getText(),messageSourceRef);
                this.seviceMode = getEnumI18n(device.getDevice().getWorkType().getText(),messageSourceRef);
                this.cashboxLimit = getInfo(device.getDevice().getCashboxLimit());
            }

            if (device.getDeviceRegister() != null) {
                this.appRelease = device.getDeviceRegister().getAtmcVersion();
                this.registerStatus = getEnumI18n(device.getDeviceRegister().getRegStatus().getText(),messageSourceRef);
            }
        }
        catch (Exception e) {
        	logger.error(String.format("[%s]", e));
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

    public void setDeviceSign(IDeviceReport device,MessageSource messageSourceRef) {
        this.code = device.getDeviceId();
        this.registerStatus = getEnumI18n(device.getDeviceRegister().getRegStatus().getText(),messageSourceRef);
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

	public DeviceStatus getNfcStatus() {
		return nfcStatus;
	}

	public void setNfcStatus(DeviceStatus nfcStauts) {
		this.nfcStatus = nfcStauts;
	}

	@Override
	public void setIccStatus(DeviceStatus iccStatus) {
		this.iccStatus = iccStatus ;
	}

	@Override
	public DeviceStatus getIccStatus() {
		return iccStatus ;
	}

	@Override
	public void setFgpStatus(DeviceStatus fgpStatus) {
		this.fgpStatus = fgpStatus ;
	}

	@Override
	public DeviceStatus getFgpStatus() {
		return fgpStatus ;
	}

	@Override
	public void setIscStatus(DeviceStatus iscStatus) {
		this.iscStatus = iscStatus ;
	}

	@Override
	public DeviceStatus getIscStatus() {
		return iscStatus ;
	}

	public DeviceStatus getCamStatus() {
		return camStatus;
	}

	public void setCamStatus(DeviceStatus camStatus) {
		this.camStatus = camStatus;
	}

	public DeviceStatus getBcrStatus() {
		return bcrStatus;
	}

	public void setBcrStatus(DeviceStatus bcrStatus) {
		this.bcrStatus = bcrStatus;
	}

	public DeviceStatus getUkrStatus() {
		return ukrStatus;
	}

	public void setUkrStatus(DeviceStatus ukrStatus) {
		this.ukrStatus = ukrStatus;
	}

	public DeviceStatus getUkdStatus() {
		return ukdStatus;
	}

	public void setUkdStatus(DeviceStatus ukdStatus) {
		this.ukdStatus = ukdStatus;
	}

}
