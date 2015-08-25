package com.yihuacomputer.fish.api.monitor.xfs.propertise;


/**
 * 设备模块属性信息
 * @author YiHua
 *
 */
public interface IXfsPropertise {

	public void setTerminalId(String terminalId);

	public IPropNfc getPropNfc();

	public IPropIdc getPropIdc();

	public IPropJpr getPropJpr();

	public IPropRpr getPropRpr();

	public IPropCdm getPropCdm();

	public IPropCim getPropCim();

	public IPropPin getPropPin();

	public IPropTtu getPropTtu();

	public IPropSiu getPropSiu();

	public IPropPbk getPropPbk();

	public void setIdc(IPropIdc idc);

	public void setRpr(IPropRpr rpr);

	public void setCdm(IPropCdm cdm);

	public void setCim(IPropCim cim);

	public void setJpr(IPropJpr jpr);

	public void setTtu(IPropTtu ttu);

	public void setSiu(IPropSiu siu);

	public void setPin(IPropPin pin);

	public void setPbk(IPropPbk pbk);

	public void setNfc(IPropNfc nfc);

	public IPropIdc makePropIdc();

	public IPropRpr makePropRpr();

	public IPropCdm makePropCdm();

	public IPropCim makePropCim();

	public IPropJpr makePropJpr();

	public IPropTtu makePropTtu();

	public IPropSiu makePropSiu();

	public IPropPin makePropPin();

	public IPropPbk makePropPbk();

	public IPropNfc makePropNfc();

	public IPropIcc getPropIcc();

	public void setIcc(IPropIcc icc);

	public IPropIcc makePropIcc();

	public void setFgp(IPropFgp fgp);

	public IPropFgp makePropFgp();

	public IPropFgp getPropFgp();

	public void setIsc(IPropIsc isc);

	public IPropIsc makePropIsc();

	public IPropIsc getPropIsc();


}
