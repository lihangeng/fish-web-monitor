package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;

/**
 * 状态监控信息
 * 
 * @author YiHua
 * 
 */
public class StatusMonitorForm {

    private long id;

    private String code;

    private String org;

    private String type;

    private String seviceMode;

    private String insideOutside;

    private String ip;

    private String address;

    private String appRelease;

    private String runStatus;

    private RunStatus run;

    private String boxInitCount;

    private String modStatus;

    private DeviceStatus mod;

    private String boxCurrentCount;

    private String cashboxLimit;

    private String boxStatus;

    private BoxStatus box;

    private String retainCardCount;

    private String netStatus;

    private NetStatus net;

    private String registerStatus;

    private DeviceStatus idcStatus;

    private DeviceStatus cimStatus;

    private DeviceStatus cdmStatus;

    private DeviceStatus rprStatus;

    private DeviceStatus jprStatus;

    private DeviceStatus pinStatus;

    private DeviceStatus siuStatus;

    private DeviceStatus ttuStatus;
    
    private DeviceStatus nfcStatus;
    
    private DeviceStatus pbkStatus;

    private DeviceStatus iccStatus;

    private DeviceStatus iscStatus ;

    private DeviceStatus fgpStatus ;
    private DeviceStatus camStatus ;
    private DeviceStatus bcrStatus ;

    private String latitude;

    private String longtitude;

    public static List<StatusMonitorForm> convert(List<IStatusReport> list) {
        List<StatusMonitorForm> result = new ArrayList<StatusMonitorForm>();
        for (IStatusReport item : list) {
            result.add(new StatusMonitorForm(item));
        }
        return result;
    }
    public StatusMonitorForm(){}
    public StatusMonitorForm(IStatusReport statusReport) {
        this.id = statusReport.getId();
        this.code = statusReport.getCode();
        this.org = statusReport.getOrg();
        this.type = statusReport.getType();
        this.seviceMode = statusReport.getSeviceMode();
        this.insideOutside = statusReport.getInsideOutside();
        this.ip = statusReport.getIp();
        this.address = statusReport.getAddress();
//        this.appRelease = statusReport.getAppRelease() == null ? "未知" : statusReport.getAppRelease();
        this.appRelease = statusReport.getAppRelease();
        this.registerStatus = statusReport.getRegisterStatus();
        this.runStatus = statusReport.getRunStatus();
        this.run = statusReport.getRun();
        this.boxInitCount = statusReport.getBoxInitCount().equals("-1") ? "0" : statusReport.getBoxInitCount();
        this.modStatus = statusReport.getModStatus();
        this.mod = statusReport.getMod();
        this.boxCurrentCount = statusReport.getBoxCurrentCount().equals("-1") ? "0" : statusReport.getBoxCurrentCount();
        this.cashboxLimit = statusReport.getCashboxLimit().equals("-1") ? "0" : statusReport.getCashboxLimit();
        this.boxStatus = statusReport.getBoxStatus();
        this.box = statusReport.getBox();
        this.retainCardCount = statusReport.getRetainCardCount().equals("-1") ? "0" : statusReport.getRetainCardCount();
        this.netStatus = statusReport.getNetStatus();
        this.net = statusReport.getNet();
        this.registerStatus = statusReport.getRegisterStatus();

        this.idcStatus = statusReport.getIdcStatus();
        this.cimStatus = statusReport.getCimStatus();
        this.cdmStatus = statusReport.getCdmStatus();
        this.rprStatus = statusReport.getRprStatus();
        this.jprStatus = statusReport.getJprStatus();
        this.pinStatus = statusReport.getPinStatus();
        this.siuStatus = statusReport.getSiuStatus();
        this.ttuStatus = statusReport.getTtuStatus();
        this.nfcStatus = statusReport.getNfcStauts();
        this.pbkStatus = statusReport.getPbkStatus();

        this.iccStatus = statusReport.getIccStatus();
        this.iscStatus = statusReport.getIscStatus() ;
        this.fgpStatus = statusReport.getFgpStatus() ;
        this.camStatus = statusReport.getCamStatus();
        this.bcrStatus = statusReport.getBcrStatus();

        this.longtitude = statusReport.getLongtitude();
        this.latitude = statusReport.getLatitude();

    }

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

    public BoxStatus getBox() {
        return box;
    }

    public void setBox(BoxStatus box) {
        this.box = box;
    }

    public NetStatus getNet() {
        return net;
    }

    public void setNet(NetStatus net) {
        this.net = net;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getCashboxLimit() {
        return cashboxLimit;
    }

    public void setCashboxLimit(String cashboxLimit) {
        this.cashboxLimit = cashboxLimit;
    }

	public DeviceStatus getNfcStatus() {
		return nfcStatus;
	}

	public void setNfcStatus(DeviceStatus nfcStatus) {
		this.nfcStatus = nfcStatus;
	}

	public DeviceStatus getPbkStatus() {
		return pbkStatus;
	}

	public void setPbkStatus(DeviceStatus pbkStatus) {
		this.pbkStatus = pbkStatus;
	}

	public DeviceStatus getIccStatus() {
		return iccStatus;
	}

	public void setIccStatus(DeviceStatus iccStatus) {
		this.iccStatus = iccStatus;
	}

	public DeviceStatus getIscStatus() {
		return iscStatus;
	}

	public void setIscStatus(DeviceStatus iscStatus) {
		this.iscStatus = iscStatus;
	}

	public DeviceStatus getFgpStatus() {
		return fgpStatus;
	}

	public void setFgpStatus(DeviceStatus fgpStatus) {
		this.fgpStatus = fgpStatus;
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

}
