package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;



/**
 * @author GQ
 * 设备UKey Reader状态信息
 */
public class StatusUkr
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
    private String chipModule;
    
    /**
     * 获取回收匣状态
     */
    private String binStatus;
    
    /**
     * 吞UKEY个数
     */
    private int ukeys;

    public DeviceStatus getStatus()
    {
        return status;
    }

    public String getBinStatus() {
		return binStatus;
	}

	public void setBinStatus(String binStatus) {
		this.binStatus = binStatus;
	}

	public int getUkeys() {
		return ukeys;
	}

	public void setUkeys(int ukeys) {
		this.ukeys = ukeys;
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


    public String getChipModule()
    {
        return chipModule;
    }

    public void setChipModule(String chipModule)
    {
        this.chipModule = chipModule;
    }

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

    
}
