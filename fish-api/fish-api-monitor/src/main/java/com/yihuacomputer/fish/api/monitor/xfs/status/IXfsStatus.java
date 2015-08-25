package com.yihuacomputer.fish.api.monitor.xfs.status;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;

/**
 * 设备模块状态信息
 *
 * @author YiHua
 *
 */
public interface IXfsStatus {

	public void setTerminalId(String terminalId);

	public String getTerminalId();

	public String getDateTime();

	public void setDateTime(String dateTime);

	public RunStatus getRunStatus();

	public void setRunStatus(RunStatus runStatus);

	public BoxStatus getBoxStatus();

	public DeviceStatus getModStatus();

	public NetStatus getNetStatus();

	public IStatusIdc getStatusIdc();

	public IStatusJpr getStatusJpr();

	public IStatusRpr getStatusRpr();

	public IStatusCdm getStatusCdm();

	public IStatusCim getStatusCim();

	public IStatusPin getStatusPin();

	public IStatusSiu getStatusSiu();

	public IStatusTtu getStatusTtu();

	public IStatusNfc getStatusNfc();

	public IStatusPbk getStatusPbk();

	public void setBoxStatus(BoxStatus boxStatus);

	public void setModStatus(DeviceStatus mod);

	public long getBoxInitCount();

	public void setBoxInitCount(long boxInitCount);

	public long getBoxCurrentCount();

	public void setBoxCurrentCount(long boxCurrentCount);

	public void setNetStatus(NetStatus netStatus);

	public void setStatusIdc(IStatusIdc idc);

	public void setStatusJpr(IStatusJpr jpr);

	public void setStatusRpr(IStatusRpr rpr);

	public void setStatusCdm(IStatusCdm cdm);

	public void setStatusCim(IStatusCim cim);

	public void setStatusPin(IStatusPin pin);

	public void setStatusTtu(IStatusTtu ttu);

	public void setStatusSiu(IStatusSiu siu);

	public void setStatusNfc(IStatusNfc nfc);

	public void setStatusPbk(IStatusPbk pbk);

	public IStatusIdc makeStatusIdc();

	public IStatusJpr makeStatusJpr();

	public IStatusRpr makeStatusRpr();

	public IStatusCdm makeStatusCdm();

	public IStatusCim makeStatusCim();

	public IStatusPin makeStatusPin();

	public IStatusSiu makeStatusSiu();

	public IStatusTtu makeStatusTtu();

	public IStatusNfc makeStatusNfc();

	public IStatusPbk makeStatusPbk();

	public void setXfsStatus(IXfsStatus xfsStatus);

	public void setStatusIcc(IStatusIcc icc) ;

	public IStatusIcc getStatusIcc() ;

	public IStatusIcc makeStatusIcc() ;

	public void setStatusFgp(IStatusFgp fgp) ;

	public IStatusFgp getStatusFgp() ;

	public IStatusFgp makeStatusFgp() ;

	public void setStatusIsc(IStatusIsc isc) ;

	public IStatusIsc getStatusIsc() ;

	public IStatusIsc makeStatusIsc() ;
}
