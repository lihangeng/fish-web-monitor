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

    public void setId(long id);

    public long getId();

    public String getCode();

    public void setCode(String code);

    public String getOrg();

    public void setOrg(String org);

    public String getType();

    public void setType(String type);

    public String getSeviceMode();

    public void setSeviceMode(String seviceMode);

    public String getInsideOutside();

    public void setInsideOutside(String insideOutside);

    public String getIp();

    public void setIp(String ip);

    public String getAddress();

    public void setAddress(String address);

    public String getAppRelease();

    public void setAppRelease(String appRelease);

    public String getRunStatus();

    public void setRunStatus(String runStatus);

    public String getBoxInitCount();

    public void setBoxInitCount(String boxInitCount);

    public String getModStatus();

    public void setModStatus(String modStatus);

    public String getBoxCurrentCount();

    public void setBoxCurrentCount(String boxCurrentCount);

    public String getBoxStatus();

    public void setBoxStatus(String boxStatus);

    public String getRetainCardCount();

    public void setRetainCardCount(String retainCardCount);

    public String getNetStatus();

    public void setNetStatus(String netStatus);

    public String getRegisterStatus();

    public void setRegisterStatus(String registerStatus);

    public DeviceStatus getIdcStatus();

    public void setIdcStatus(DeviceStatus idcStatus);

    public DeviceStatus getCimStatus();

    public void setCimStatus(DeviceStatus cimStatus);

    public DeviceStatus getCdmStatus();

    public void setCdmStatus(DeviceStatus cdmStatus);

    public DeviceStatus getRprStatus();

    public void setRprStatus(DeviceStatus rprStatus);

    public DeviceStatus getJprStatus();

    public void setJprStatus(DeviceStatus jprStatus);

    public DeviceStatus getPinStatus();

    public void setPinStatus(DeviceStatus pinStatus);

    public DeviceStatus getSiuStatus();

    public void setSiuStatus(DeviceStatus siuStatus);

    public DeviceStatus getTtuStatus();

    public void setTtuStatus(DeviceStatus ttuStatus);

    public RunStatus getRun();

    public void setRun(RunStatus run);

    public DeviceStatus getMod();

    public void setMod(DeviceStatus mod);

    public NetStatus getNet();

    public void setNet(NetStatus net);

    public BoxStatus getBox();

    public void setBox(BoxStatus box);

    /**
     * 地图坐标经度
     */
    public void setLatitude(String latitude);

    /**
     * 地图坐标经度
     */
    public String getLatitude();

    /**
     * 地图坐标纬度
     */
    public String getLongtitude();

    /**
     * 地图坐标纬度
     */
    public void setLongtitude(String longtitude);

    public String getCashboxLimit();

    public void setCashboxLimit(String cashboxLimit);

	public DeviceStatus getPbkStatus();

	public void setPbkStatus(DeviceStatus pbkStatus);

	public DeviceStatus getNfcStauts();

	public void setNfcStauts(DeviceStatus nfcStauts);

    public void setIccStatus(DeviceStatus iccStatus);

    public DeviceStatus getIccStatus();

    public void setFgpStatus(DeviceStatus fgpStatus);

    public DeviceStatus getFgpStatus();

    public void setIscStatus(DeviceStatus iscStatus);

    public DeviceStatus getIscStatus();
    
    public DeviceStatus getCamStatus();

	public void setCamStatus(DeviceStatus camStatus);

	public DeviceStatus getBcrStatus();

	public void setBcrStatus(DeviceStatus bcrStatus);
}
