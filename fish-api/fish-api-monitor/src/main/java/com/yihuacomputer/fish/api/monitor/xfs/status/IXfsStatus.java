package com.yihuacomputer.fish.api.monitor.xfs.status;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;

/**
 * 设备模块状态信息
 *
 * @author YiHua
 *
 */
public interface IXfsStatus {

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @return
	 */
	public String getDateTime();

	/**
	 * @param dateTime
	 */
	public void setDateTime(String dateTime);

	/**
	 * @return
	 */
	public RunStatus getRunStatus();

	/**
	 * @param runStatus
	 */
	public void setRunStatus(RunStatus runStatus);

	/**
	 * @return
	 */
	public BoxStatus getBoxStatus();

	/**
	 * @return
	 */
	public DeviceStatus getModStatus();

	/**
	 * @return
	 */
	public NetStatus getNetStatus();

	/**
	 * @return
	 */
	public IStatusIdc getStatusIdc();

	/**
	 * @return
	 */
	public IStatusJpr getStatusJpr();

	/**
	 * @return
	 */
	public IStatusRpr getStatusRpr();

	/**
	 * @return
	 */
	public IStatusCdm getStatusCdm();

	/**
	 * @return
	 */
	public IStatusCim getStatusCim();

	/**
	 * @return
	 */
	public IStatusPin getStatusPin();

	/**
	 * @return
	 */
	public IStatusSiu getStatusSiu();

	/**
	 * @return
	 */
	public IStatusTtu getStatusTtu();

	/**
	 * @return
	 */
	public IStatusNfc getStatusNfc();

	/**
	 * @return
	 */
	public IStatusPbk getStatusPbk();

	/**
	 * @param boxStatus
	 */
	public void setBoxStatus(BoxStatus boxStatus);

	/**
	 * @param mod
	 */
	public void setModStatus(DeviceStatus mod);

	/**
	 * @return
	 */
	public long getBoxInitCount();

	/**
	 * @param boxInitCount
	 */
	public void setBoxInitCount(long boxInitCount);

	/**
	 * @return
	 */
	public long getBoxCurrentCount();

	/**
	 * @param boxCurrentCount
	 */
	public void setBoxCurrentCount(long boxCurrentCount);

	/**
	 * @param netStatus
	 */
	public void setNetStatus(NetStatus netStatus);

	/**
	 * @param idc
	 */
	public void setStatusIdc(IStatusIdc idc);

	/**
	 * @param jpr
	 */
	public void setStatusJpr(IStatusJpr jpr);

	/**
	 * @param rpr
	 */
	public void setStatusRpr(IStatusRpr rpr);

	/**
	 * @param cdm
	 */
	public void setStatusCdm(IStatusCdm cdm);

	/**
	 * @param cim
	 */
	public void setStatusCim(IStatusCim cim);

	/**
	 * @param pin
	 */
	public void setStatusPin(IStatusPin pin);

	/**
	 * @param ttu
	 */
	public void setStatusTtu(IStatusTtu ttu);

	/**
	 * @param siu
	 */
	public void setStatusSiu(IStatusSiu siu);

	/**
	 * @param nfc
	 */
	public void setStatusNfc(IStatusNfc nfc);

	/**
	 * @param pbk
	 */
	public void setStatusPbk(IStatusPbk pbk);

	/**
	 * @return
	 */
	public IStatusIdc makeStatusIdc();

	/**
	 * @return
	 */
	public IStatusJpr makeStatusJpr();

	/**
	 * @return
	 */
	public IStatusRpr makeStatusRpr();

	/**
	 * @return
	 */
	public IStatusCdm makeStatusCdm();

	/**
	 * @return
	 */
	public IStatusCim makeStatusCim();

	/**
	 * @return
	 */
	public IStatusPin makeStatusPin();

	/**
	 * @return
	 */
	public IStatusSiu makeStatusSiu();

	/**
	 * @return
	 */
	public IStatusTtu makeStatusTtu();

	/**
	 * @return
	 */
	public IStatusNfc makeStatusNfc();

	/**
	 * @return
	 */
	public IStatusPbk makeStatusPbk();

	/**
	 * @param xfsStatus
	 */
	public void setXfsStatus(IXfsStatus xfsStatus);

	/**
	 * @param icc
	 */
	public void setStatusIcc(IStatusIcc icc) ;

	/**
	 * @return
	 */
	public IStatusIcc getStatusIcc() ;

	/**
	 * @return
	 */
	public IStatusIcc makeStatusIcc() ;

	/**
	 * @param fgp
	 */
	public void setStatusFgp(IStatusFgp fgp) ;

	/**
	 * @return
	 */
	public IStatusFgp getStatusFgp() ;

	/**
	 * @return
	 */
	public IStatusFgp makeStatusFgp() ;

	/**
	 * @param isc
	 */
	public void setStatusIsc(IStatusIsc isc) ;

	/**
	 * @return
	 */
	public IStatusIsc getStatusIsc() ;

	/**
	 * @return
	 */
	public IStatusIsc makeStatusIsc() ;

	/**
	 * @return
	 */
	public IXfsStatus getHisXfsStatus() ;

	/**
	 * @param hisXfsStatus
	 */
	public void setHisXfsStatus(IXfsStatus hisXfsStatus) ;
	
    /**
     * @param bcr
     */
    public void setStatusBcr(IStatusBcr bcr);

    /**
     * @return
     */
    public IStatusBcr getStatusBcr();

    /**
     * @return
     */
    public IStatusBcr makeStatusBcr();
    
    /**
     * @param cam
     */
    public void setStatusCam(IStatusCam cam);

    /**
     * @return
     */
    public IStatusCam getStatusCam();

    /**
     * @return
     */
    public IStatusCam makeStatusCam();
    
    /**
     * @param ukr
     */
    public void setStatusUkr(IStatusUkr ukr);

    /**
     * @return
     */
    public IStatusUkr getStatusUkr();

    /**
     * @return
     */
    public IStatusUkr makeStatusUkr();
    
    /**
     * @param ukd
     */
    public void setStatusUkd(IStatusUkd ukd);

    /**
     * @return
     */
    public IStatusUkd getStatusUkd();

    /**
     * @return
     */
    public IStatusUkd makeStatusUkd();
}
