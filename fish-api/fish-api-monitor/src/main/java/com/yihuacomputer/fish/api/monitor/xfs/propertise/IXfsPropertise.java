package com.yihuacomputer.fish.api.monitor.xfs.propertise;



/**
 * 设备模块属性信息
 * @author YiHua
 *
 */
public interface IXfsPropertise {

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public IPropNfc getPropNfc();

	/**
	 * @return
	 */
	public IPropIdc getPropIdc();

	/**
	 * @return
	 */
	public IPropJpr getPropJpr();

	/**
	 * @return
	 */
	public IPropRpr getPropRpr();

	/**
	 * @return
	 */
	public IPropCdm getPropCdm();

	/**
	 * @return
	 */
	public IPropCim getPropCim();

	/**
	 * @return
	 */
	public IPropPin getPropPin();

	/**
	 * @return
	 */
	public IPropTtu getPropTtu();

	/**
	 * @return
	 */
	public IPropSiu getPropSiu();

	/**
	 * @return
	 */
	public IPropPbk getPropPbk();

	/**
	 * @param idc
	 */
	public void setIdc(IPropIdc idc);

	/**
	 * @param rpr
	 */
	public void setRpr(IPropRpr rpr);

	/**
	 * @param cdm
	 */
	public void setCdm(IPropCdm cdm);

	/**
	 * @param cim
	 */
	public void setCim(IPropCim cim);

	/**
	 * @param jpr
	 */
	public void setJpr(IPropJpr jpr);

	/**
	 * @param ttu
	 */
	public void setTtu(IPropTtu ttu);

	/**
	 * @param siu
	 */
	public void setSiu(IPropSiu siu);

	/**
	 * @param pin
	 */
	public void setPin(IPropPin pin);

	/**
	 * @param pbk
	 */
	public void setPbk(IPropPbk pbk);

	/**
	 * @param nfc
	 */
	public void setNfc(IPropNfc nfc);

	/**
	 * @return
	 */
	public IPropIdc makePropIdc();

	/**
	 * @return
	 */
	public IPropRpr makePropRpr();

	/**
	 * @return
	 */
	public IPropCdm makePropCdm();

	/**
	 * @return
	 */
	public IPropCim makePropCim();

	/**
	 * @return
	 */
	public IPropJpr makePropJpr();

	/**
	 * @return
	 */
	public IPropTtu makePropTtu();

	/**
	 * @return
	 */
	public IPropSiu makePropSiu();

	/**
	 * @return
	 */
	public IPropPin makePropPin();

	/**
	 * @return
	 */
	public IPropPbk makePropPbk();

	/**
	 * @return
	 */
	public IPropNfc makePropNfc();

	/**
	 * @return
	 */
	public IPropIcc getPropIcc();

	/**
	 * @param icc
	 */
	public void setIcc(IPropIcc icc);

	/**
	 * @return
	 */
	public IPropIcc makePropIcc();

	/**
	 * @param fgp
	 */
	public void setFgp(IPropFgp fgp);

	/**
	 * @return
	 */
	public IPropFgp makePropFgp();

	/**
	 * @return
	 */
	public IPropFgp getPropFgp();

	/**
	 * @param isc
	 */
	public void setIsc(IPropIsc isc);

	/**
	 * @return
	 */
	public IPropIsc makePropIsc();

	/**
	 * @return
	 */
	public IPropIsc getPropIsc();

	/**
	 * @return
	 */
	public IPropBcr getBcr();

	/**
	 * @param bcr
	 */
	public void setBcr(IPropBcr bcr);

	/**
	 * @return
	 */
	public IPropCam getCam();

	/**
	 * @param cam
	 */
	public void setCam(IPropCam cam);
	
    /**
     * @return
     */
    public IPropBcr makePropBcr();

    /**
     * @return
     */
    public IPropCam makePropCam();
    
    /**
     * @return
     */
    public IPropUkr getPropUkr();

    /**
     * @param ukr
     */
    public void setUkr(IPropUkr ukr);

    /**
     * @return
     */
    public IPropUkr makePropUkr();
    
    /**
     * @return
     */
    public IPropUkd getPropUkd();

    /**
     * @param ukd
     */
    public void setUkd(IPropUkd ukd);

    /**
     * @return
     */
    public IPropUkd makePropUkd();
}
