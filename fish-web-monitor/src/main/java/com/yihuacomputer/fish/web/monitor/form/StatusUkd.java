package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;



/**
 * @author GQ
 * 获取issue UKEY 状态
 */
public class StatusUkd
{
    /**
     * 硬件主状态
     */
    private DeviceStatus status ;
    private String statusName ;

    /**
     * 状态代码
     */
    private String code;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;
    /**
     * media状态
     */
    private String media;

    /**
     * 芯片模块状态
     */
    private long ukeyCount;

    public DeviceStatus getStatus()
    {
        return status;
    }

    public void setStatus(DeviceStatus status)
    {
        this.status = status;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
    public String getHwCode() {
		return hwCode;
	}

	public void setHwCode(String hwCode) {
		this.hwCode = hwCode;
	}
    public String getMedia()
    {
        return media;
    }

    public void setMedia(String media)
    {
        this.media = media;
    }

    public long getUkeyCount() {
		return ukeyCount;
	}

	public void setUkeyCount(long ukeyCount) {
		this.ukeyCount = ukeyCount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	
}
