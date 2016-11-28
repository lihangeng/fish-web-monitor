package com.yihuacomputer.fish.monitor.mq;

import java.io.Serializable;


import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusBcr;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusCam;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusCdm;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusCim;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusFgp;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusIcc;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusIdc;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusIsc;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusJpr;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusNfc;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusPbk;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusPin;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusRpr;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusSiu;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusTtu;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusUkd;
import com.yihuacomputer.fish.monitor.entity.xfs.status.StatusUkr;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;

public class MqXfsStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private String terminalId;

	private String dateTime;

	private RunStatus runStatus;

	private NetStatus netStatus;

	private BoxStatus boxStatus;

	private long boxInitCount;

	private long boxCurrentCount;

	private DeviceStatus modStatus;

	private StatusIdc idc;

	private StatusNfc nfc;

	private StatusCim cim;

	private StatusCdm cdm;

	private StatusJpr jpr;

	private StatusRpr rpr;

	private StatusPbk pbk;

	private StatusTtu ttu;

	private StatusPin pin;

	private StatusSiu siu;

	private StatusIcc icc;

	private StatusFgp fgp;

	private StatusIsc isc;

	private StatusBcr bcr;
    
    private StatusCam cam;

    private StatusUkd ukd;
    
    private StatusUkr ukr;

	private IXfsStatus hisXfsStatus;

	public MqXfsStatus() {
		this.boxCurrentCount = -1;
		this.boxInitCount = -1;
		this.boxStatus = BoxStatus.Unknown;
		this.runStatus = RunStatus.Unknown;
		this.netStatus = NetStatus.Unknown;
		this.modStatus = DeviceStatus.Unknown;
		this.idc = new StatusIdc();
		this.nfc = new StatusNfc();
		this.rpr = new StatusRpr();
		this.cim = new StatusCim();
		this.cdm = new StatusCdm();
		this.jpr = new StatusJpr();
		this.pbk = new StatusPbk();
		this.pin = new StatusPin();
		this.ttu = new StatusTtu();
		this.siu = new StatusSiu();
		this.icc = new StatusIcc();
		this.fgp = new StatusFgp();
		this.isc = new StatusIsc();
	}

	public void setXfsStatus(IXfsStatus xfsStatus) {
		this.terminalId = xfsStatus.getTerminalId();
		this.dateTime = xfsStatus.getDateTime();
		this.setBoxStatus(xfsStatus.getBoxStatus());
		this.setBoxCurrentCount(xfsStatus.getBoxCurrentCount());
		this.setBoxInitCount(xfsStatus.getBoxInitCount());
		this.setModStatus(xfsStatus.getModStatus());
		this.setRunStatus(xfsStatus.getRunStatus());
		this.setNetStatus(xfsStatus.getNetStatus());
		this.setCdm((StatusCdm)xfsStatus.getStatusCdm());
		this.setCim((StatusCim)xfsStatus.getStatusCim());
		this.setIdc((StatusIdc)xfsStatus.getStatusIdc());
		this.setJpr((StatusJpr)xfsStatus.getStatusJpr());
		this.setPin((StatusPin)xfsStatus.getStatusPin());
		this.setRpr((StatusRpr)xfsStatus.getStatusRpr());
		this.setSiu((StatusSiu)xfsStatus.getStatusSiu());
		this.setTtu((StatusTtu)xfsStatus.getStatusTtu());
		this.setNfc((StatusNfc)xfsStatus.getStatusNfc());
		this.setPbk((StatusPbk)xfsStatus.getStatusPbk());
		this.setIcc((StatusIcc)xfsStatus.getStatusIcc());
		this.setFgp((StatusFgp)xfsStatus.getStatusFgp());
		this.setIsc((StatusIsc)xfsStatus.getStatusIsc());
		this.setUkd((StatusUkd)xfsStatus.getStatusUkd());
		this.setUkr((StatusUkr)xfsStatus.getStatusUkr());
		this.setBcr((StatusBcr)xfsStatus.getStatusBcr());
		this.setCam((StatusCam)xfsStatus.getStatusCam());
		
	}
	
	public XfsStatus makeXfsStatus(){
		XfsStatus xfsStatus = new XfsStatus();
		xfsStatus.setTerminalId(this.getTerminalId());
		xfsStatus.setDateTime(this.getDateTime());
		xfsStatus.setBoxStatus(this.getBoxStatus());
		xfsStatus.setBoxCurrentCount(this.getBoxCurrentCount());
		xfsStatus.setBoxInitCount(this.getBoxInitCount());
		xfsStatus.setModStatus(this.getModStatus());
		xfsStatus.setRunStatus(this.getRunStatus());
		xfsStatus.setNetStatus(this.getNetStatus());
		xfsStatus.setStatusCdm(this.getCdm());
		xfsStatus.setStatusCim(this.getCim());
		xfsStatus.setStatusIdc(this.getIdc());
		xfsStatus.setStatusJpr(this.getJpr());
		xfsStatus.setStatusPin(this.getPin());
		xfsStatus.setStatusRpr(this.getRpr());
		xfsStatus.setStatusSiu(this.getSiu());
		xfsStatus.setStatusTtu(this.getTtu());
		xfsStatus.setStatusNfc(this.getNfc());
		xfsStatus.setStatusPbk(this.getPbk());
		xfsStatus.setStatusIcc(this.getIcc());
		xfsStatus.setStatusFgp(this.getFgp());
		xfsStatus.setStatusIsc(this.getIsc());
		xfsStatus.setStatusUkd(this.getUkd());
		xfsStatus.setStatusUkr(this.getUkr());
		xfsStatus.setStatusBcr(this.getBcr());
		xfsStatus.setStatusCam(this.getCam());
		return xfsStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + (int) (boxCurrentCount ^ (boxCurrentCount >>> 32));
        result = prime * result + (int) (boxInitCount ^ (boxInitCount >>> 32));
        result = prime * result + ((boxStatus == null) ? 0 : boxStatus.hashCode());
        result = prime * result + ((cdm == null) ? 0 : cdm.hashCode());
        result = prime * result + ((cim == null) ? 0 : cim.hashCode());
        result = prime * result + ((idc == null) ? 0 : idc.hashCode());
        result = prime * result + ((nfc == null) ? 0 : nfc.hashCode());
        result = prime * result + ((pbk == null) ? 0 : pbk.hashCode());
        result = prime * result + ((jpr == null) ? 0 : jpr.hashCode());
        result = prime * result + ((modStatus == null) ? 0 : modStatus.hashCode());
        result = prime * result + ((netStatus == null) ? 0 : netStatus.hashCode());
        result = prime * result + ((pin == null) ? 0 : pin.hashCode());
        result = prime * result + ((rpr == null) ? 0 : rpr.hashCode());
        result = prime * result + ((runStatus == null) ? 0 : runStatus.hashCode());
        result = prime * result + ((siu == null) ? 0 : siu.hashCode());
        result = prime * result + ((ttu == null) ? 0 : ttu.hashCode());
        result = prime * result + ((icc == null) ? 0 : icc.hashCode());
        result = prime * result + ((fgp == null) ? 0 : fgp.hashCode());
        result = prime * result + ((isc == null) ? 0 : isc.hashCode());
        result = prime * result + ((bcr == null) ? 0 : bcr.hashCode());
        result = prime * result + ((cam == null) ? 0 : cam.hashCode());
        result = prime * result + ((ukr == null) ? 0 : ukr.hashCode());
        result = prime * result + ((ukd == null) ? 0 : ukd.hashCode());
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MqXfsStatus other = (MqXfsStatus) obj;
		if (boxCurrentCount != other.boxCurrentCount) {
			return false;
		}
		if (boxInitCount != other.boxInitCount) {
			return false;
		}
		if (boxStatus != other.boxStatus) {
			return false;
		}
		
		if (cdm == null) {
			if (other.cdm != null) {
				return false;
			}
		} else if (!cdm.equals(other.cdm)) {
			return false;
		}
		if (cim == null) {
			if (other.cim != null) {
				return false;
			}
		} else if (!cim.equals(other.cim)) {
			return false;
		}
		if (idc == null) {
			if (other.idc != null) {
				return false;
			}
		} else if (!idc.equals(other.idc)) {
			return false;
		}
		if (nfc == null) {
			if (other.nfc != null) {
				return false;
			}
		} else if (!nfc.equals(other.nfc)) {
			return false;
		}
		if (jpr == null) {
			if (other.jpr != null) {
				return false;
			}
		} else if (!jpr.equals(other.jpr)) {
			return false;
		}
		if (pbk == null) {
			if (other.pbk != null) {
				return false;
			}
		} else if (!pbk.equals(other.pbk)) {
			return false;
		}
		if (modStatus != other.modStatus) {
			return false;
		}
		if (netStatus != other.netStatus) {
			return false;
		}
		if (pin == null) {
			if (other.pin != null) {
				return false;
			}
		} else if (!pin.equals(other.pin)) {
			return false;
		}
		if (rpr == null) {
			if (other.rpr != null) {
				return false;
			}
		} else if (!rpr.equals(other.rpr)) {
			return false;
		}
		if (runStatus != other.runStatus) {
			return false;
		}
		if (siu == null) {
			if (other.siu != null) {
				return false;
			}
		} else if (!siu.equals(other.siu)) {
			return false;
		}
		if (ttu == null) {
			if (other.ttu != null) {
				return false;
			}
		} else if (!ttu.equals(other.ttu)) {
			return false;
		}

		return true;
	}
	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public RunStatus getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(RunStatus runStatus) {
		this.runStatus = runStatus;
	}

	public NetStatus getNetStatus() {
		return netStatus;
	}

	public void setNetStatus(NetStatus netStatus) {
		this.netStatus = netStatus;
	}

	public BoxStatus getBoxStatus() {
		return boxStatus;
	}

	public void setBoxStatus(BoxStatus boxStatus) {
		this.boxStatus = boxStatus;
	}

	public long getBoxInitCount() {
		return boxInitCount;
	}

	public void setBoxInitCount(long boxInitCount) {
		this.boxInitCount = boxInitCount;
	}

	public long getBoxCurrentCount() {
		return boxCurrentCount;
	}

	public void setBoxCurrentCount(long boxCurrentCount) {
		this.boxCurrentCount = boxCurrentCount;
	}

	public DeviceStatus getModStatus() {
		return modStatus;
	}

	public void setModStatus(DeviceStatus modStatus) {
		this.modStatus = modStatus;
	}

	public StatusIdc getIdc() {
		return idc;
	}

	public void setIdc(StatusIdc idc) {
		this.idc = idc;
	}

	public StatusNfc getNfc() {
		return nfc;
	}

	public void setNfc(StatusNfc nfc) {
		this.nfc = nfc;
	}

	public StatusCim getCim() {
		return cim;
	}

	public void setCim(StatusCim cim) {
		this.cim = cim;
	}

	public StatusCdm getCdm() {
		return cdm;
	}

	public void setCdm(StatusCdm cdm) {
		this.cdm = cdm;
	}

	public StatusJpr getJpr() {
		return jpr;
	}

	public void setJpr(StatusJpr jpr) {
		this.jpr = jpr;
	}

	public StatusRpr getRpr() {
		return rpr;
	}

	public void setRpr(StatusRpr rpr) {
		this.rpr = rpr;
	}

	public StatusPbk getPbk() {
		return pbk;
	}

	public void setPbk(StatusPbk pbk) {
		this.pbk = pbk;
	}

	public StatusTtu getTtu() {
		return ttu;
	}

	public void setTtu(StatusTtu ttu) {
		this.ttu = ttu;
	}

	public StatusPin getPin() {
		return pin;
	}

	public void setPin(StatusPin pin) {
		this.pin = pin;
	}

	public StatusSiu getSiu() {
		return siu;
	}

	public void setSiu(StatusSiu siu) {
		this.siu = siu;
	}

	public StatusIcc getIcc() {
		return icc;
	}

	public void setIcc(StatusIcc icc) {
		this.icc = icc;
	}

	public StatusFgp getFgp() {
		return fgp;
	}

	public void setFgp(StatusFgp fgp) {
		this.fgp = fgp;
	}

	public StatusIsc getIsc() {
		return isc;
	}

	public void setIsc(StatusIsc isc) {
		this.isc = isc;
	}

	public IXfsStatus getHisXfsStatus() {
		return hisXfsStatus;
	}

	public void setHisXfsStatus(IXfsStatus hisXfsStatus) {
		this.hisXfsStatus = hisXfsStatus;
	}

	public StatusBcr getBcr() {
		return bcr;
	}

	public void setBcr(StatusBcr bcr) {
		this.bcr = bcr;
	}

	public StatusCam getCam() {
		return cam;
	}

	public void setCam(StatusCam cam) {
		this.cam = cam;
	}

	public StatusUkd getUkd() {
		return ukd;
	}

	public void setUkd(StatusUkd ukd) {
		this.ukd = ukd;
	}

	public StatusUkr getUkr() {
		return ukr;
	}

	public void setUkr(StatusUkr ukr) {
		this.ukr = ukr;
	}

}
