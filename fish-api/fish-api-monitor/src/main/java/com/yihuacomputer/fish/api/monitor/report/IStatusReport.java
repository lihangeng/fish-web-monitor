package com.yihuacomputer.fish.api.monitor.report;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;

/**
 * 设备状态监控信息
 *
 * @author YiHua
 *
 */
public interface IStatusReport {

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public long getId();

    /**
     * @return
     */
    public String getCode();

    /**
     * @param code
     */
    public void setCode(String code);

    /**
     * @return
     */
    public String getOrg();

    /**
     * @param org
     */
    public void setOrg(String org);

    /**
     * @return
     */
    public String getType();

    /**
     * @param type
     */
    public void setType(String type);

    /**
     * @return
     */
    public String getSeviceMode();

    /**
     * @param seviceMode
     */
    public void setSeviceMode(String seviceMode);

    /**
     * @return
     */
    public String getInsideOutside();

    /**
     * @param insideOutside
     */
    public void setInsideOutside(String insideOutside);

    /**
     * @return
     */
    public String getIp();

    /**
     * @param ip
     */
    public void setIp(String ip);

    /**
     * @return
     */
    public String getAddress();

    /**
     * @param address
     */
    public void setAddress(String address);

    /**
     * @return
     */
    public String getAppRelease();

    /**
     * @param appRelease
     */
    public void setAppRelease(String appRelease);

    /**
     * @return
     */
    public String getRunStatus();

    /**
     * @param runStatus
     */
    public void setRunStatus(String runStatus);

    /**
     * @return
     */
    public String getBoxInitCount();

    /**
     * @param boxInitCount
     */
    public void setBoxInitCount(String boxInitCount);

    /**
     * @return
     */
    public String getModStatus();

    /**
     * @param modStatus
     */
    public void setModStatus(String modStatus);

    /**
     * @return
     */
    public String getBoxCurrentCount();

    /**
     * @param boxCurrentCount
     */
    public void setBoxCurrentCount(String boxCurrentCount);

    /**
     * @return
     */
    public String getBoxStatus();

    /**
     * @param boxStatus
     */
    public void setBoxStatus(String boxStatus);

    /**
     * @return
     */
    public String getRetainCardCount();

    /**
     * @param retainCardCount
     */
    public void setRetainCardCount(String retainCardCount);

    /**
     * @return
     */
    public String getNetStatus();

    /**
     * @param netStatus
     */
    public void setNetStatus(String netStatus);

    /**
     * @return
     */
    public String getRegisterStatus();

    /**
     * @param registerStatus
     */
    public void setRegisterStatus(String registerStatus);

    /**
     * @return
     */
    public DeviceStatus getIdcStatus();

    /**
     * @param idcStatus
     */
    public void setIdcStatus(DeviceStatus idcStatus);

    /**
     * @return
     */
    public DeviceStatus getCimStatus();

    /**
     * @param cimStatus
     */
    public void setCimStatus(DeviceStatus cimStatus);

    /**
     * @return
     */
    public DeviceStatus getCdmStatus();

    /**
     * @param cdmStatus
     */
    public void setCdmStatus(DeviceStatus cdmStatus);

    /**
     * @return
     */
    public DeviceStatus getRprStatus();

    /**
     * @param rprStatus
     */
    public void setRprStatus(DeviceStatus rprStatus);

    /**
     * @return
     */
    public DeviceStatus getJprStatus();

    /**
     * @param jprStatus
     */
    public void setJprStatus(DeviceStatus jprStatus);

    /**
     * @return
     */
    public DeviceStatus getPinStatus();

    /**
     * @param pinStatus
     */
    public void setPinStatus(DeviceStatus pinStatus);

    /**
     * @return
     */
    public DeviceStatus getSiuStatus();

    /**
     * @param siuStatus
     */
    public void setSiuStatus(DeviceStatus siuStatus);

    /**
     * @return
     */
    public DeviceStatus getTtuStatus();

    /**
     * @param ttuStatus
     */
    public void setTtuStatus(DeviceStatus ttuStatus);

    /**
     * @return
     */
    public RunStatus getRun();

    /**
     * @param run
     */
    public void setRun(RunStatus run);

    /**
     * @return
     */
    public DeviceStatus getMod();

    /**
     * @param mod
     */
    public void setMod(DeviceStatus mod);

    /**
     * @return
     */
    public NetStatus getNet();

    /**
     * @param net
     */
    public void setNet(NetStatus net);

    /**
     * @return
     */
    public BoxStatus getBox();

    /**
     * @param box
     */
    public void setBox(BoxStatus box);

    /**
     * 地图坐标经度
     * @param latitude
     */
    public void setLatitude(String latitude);

    /**
     * 地图坐标经度
     * @return
     */
    public String getLatitude();

    /**
     * 地图坐标纬度
     * @return
     */
    public String getLongtitude();

    /**
     * 地图坐标纬度
     * @param longtitude
     */
    public void setLongtitude(String longtitude);

    /**
     * @return
     */
    public String getCashboxLimit();

    /**
     * @param cashboxLimit
     */
    public void setCashboxLimit(String cashboxLimit);

	/**
	 * @return
	 */
	public DeviceStatus getPbkStatus();

	/**
	 * @param pbkStatus
	 */
	public void setPbkStatus(DeviceStatus pbkStatus);

	/**
	 * @return
	 */
	public DeviceStatus getNfcStatus();

	/**
	 * @param nfcStauts
	 */
	public void setNfcStatus(DeviceStatus nfcStauts);

    /**
     * @param iccStatus
     */
    public void setIccStatus(DeviceStatus iccStatus);

    /**
     * @return
     */
    public DeviceStatus getIccStatus();

    /**
     * @param fgpStatus
     */
    public void setFgpStatus(DeviceStatus fgpStatus);

    /**
     * @return
     */
    public DeviceStatus getFgpStatus();

    /**
     * @param iscStatus
     */
    public void setIscStatus(DeviceStatus iscStatus);

    /**
     * @return
     */
    public DeviceStatus getIscStatus();
    
    /**
     * @return
     */
    public DeviceStatus getCamStatus();

	/**
	 * @param camStatus
	 */
	public void setCamStatus(DeviceStatus camStatus);

	/**
	 * @return
	 */
	public DeviceStatus getBcrStatus();

	/**
	 * @param bcrStatus
	 */
	public void setBcrStatus(DeviceStatus bcrStatus);
	
	
	/**
	 * @return
	 */
	public DeviceStatus getUkrStatus();

	/**
	 * @param ukrStatus
	 */
	public void setUkrStatus(DeviceStatus ukrStatus);

	/**
	 * @return
	 */
	public DeviceStatus getUkdStatus();

	/**
	 * @param ukdStatus
	 */
	public void setUkdStatus(DeviceStatus ukdStatus);
}
