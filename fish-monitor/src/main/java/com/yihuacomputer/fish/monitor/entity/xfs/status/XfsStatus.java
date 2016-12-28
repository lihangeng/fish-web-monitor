package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusBcr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCam;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCdm;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCim;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusFgp;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIcc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIdc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIsc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusJpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusNfc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPbk;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPin;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusRpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusSiu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusTtu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkd;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_XFS_STATUS")
public class XfsStatus implements IXfsStatus,Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TERMINAL_ID", nullable = false)
    private String terminalId;

    @Column(name = "DATE_TIME")
    private String dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "RUN_STATUS")
    private RunStatus runStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "NET_STATUS")
    private NetStatus netStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOX_STATUS")
    private BoxStatus boxStatus;

    @Column(name = "BOX_INIT_COUNT")
    private long boxInitCount;

    @Column(name = "BOX_CURRENT_COUNT")
    private long boxCurrentCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOD_STATUS")
    private DeviceStatus modStatus;

    @Embedded
    private StatusIdc idc;

    @Embedded
    private StatusNfc nfc;

    @Embedded
    private StatusCim cim;

    @Embedded
    private StatusCdm cdm;

    @Embedded
    private StatusJpr jpr;

    @Embedded
    private StatusRpr rpr;

    @Embedded
    private StatusPbk pbk;

    @Embedded
    private StatusTtu ttu;

    @Embedded
    private StatusPin pin;

    @Embedded
    private StatusSiu siu;

    @Embedded
    private StatusIcc icc;

    @Embedded
    private StatusFgp fgp;

    @Embedded
    private StatusIsc isc;
    
    @Embedded
    private StatusBcr bcr;
    
    @Embedded
    private StatusCam cam;

    @Embedded
    private StatusUkd ukd;
    
    @Embedded
    private StatusUkr ukr;

    @Transient
    private IXfsStatus hisXfsStatus ;

    /**
     * 初始化
     */
    public XfsStatus() {
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
        this.bcr = new StatusBcr();
        this.cam = new StatusCam();
        this.ukd = new StatusUkd();
        this.ukr = new StatusUkr();
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public RunStatus getRunStatus() {
        return runStatus;
    }

    @Override
    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    @Override
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String getTerminalId() {
        return this.terminalId;
    }

    @Override
    public BoxStatus getBoxStatus() {
        return this.boxStatus;
    }

    @Override
    public NetStatus getNetStatus() {
        return netStatus;
    }

    @Override
    public void setNetStatus(NetStatus netStatus) {
        this.netStatus = netStatus;
    }

    @Override
    public long getBoxInitCount() {
        return boxInitCount;
    }

    @Override
    public void setBoxInitCount(long boxInitCount) {
        this.boxInitCount = boxInitCount;
    }

    @Override
    public long getBoxCurrentCount() {
        return boxCurrentCount;
    }

    @Override
    public void setBoxCurrentCount(long boxCurrentCount) {
        this.boxCurrentCount = boxCurrentCount;
    }

    @Override
    public DeviceStatus getModStatus() {
        return this.modStatus;
    }

    @Override
    public IStatusIdc getStatusIdc() {
        return this.idc;
    }

    @Override
    public IStatusNfc getStatusNfc() {
        return this.nfc;
    }

    @Override
    public IStatusJpr getStatusJpr() {
        return this.jpr;
    }

    @Override
    public IStatusRpr getStatusRpr() {
        return this.rpr;
    }

    @Override
    public IStatusPbk getStatusPbk() {
        return this.pbk;
    }

    @Override
    public IStatusCdm getStatusCdm() {
        return this.cdm;
    }

    @Override
    public IStatusCim getStatusCim() {
        return this.cim;
    }

    @Override
    public IStatusPin getStatusPin() {
        return this.pin;
    }

    @Override
    public IStatusSiu getStatusSiu() {
        return this.siu;
    }

    @Override
    public IStatusTtu getStatusTtu() {
        return this.ttu;
    }

    @Override
    public void setBoxStatus(BoxStatus boxStatus) {
        this.boxStatus = boxStatus;
    }

    @Override
    public void setModStatus(DeviceStatus modStatus) {
        this.modStatus = modStatus;
    }

    @Override
    public void setStatusIdc(IStatusIdc idc) {
        this.idc = (StatusIdc) idc;
    }

    @Override
    public void setStatusNfc(IStatusNfc nfc) {
        this.nfc = (StatusNfc) nfc;
    }

    @Override
    public void setStatusJpr(IStatusJpr jpr) {
        this.jpr = (StatusJpr) jpr;
    }

    @Override
    public void setStatusRpr(IStatusRpr rpr) {
        this.rpr = (StatusRpr) rpr;
    }

    @Override
    public void setStatusPbk(IStatusPbk pbk) {
        this.pbk = (StatusPbk) pbk;
    }

    @Override
    public void setStatusCdm(IStatusCdm cdm) {
        this.cdm = (StatusCdm) cdm;
    }

    @Override
    public void setStatusCim(IStatusCim cim) {
        this.cim = (StatusCim) cim;
    }

    @Override
    public void setStatusPin(IStatusPin pin) {
        this.pin = (StatusPin) pin;
    }

    @Override
    public void setStatusSiu(IStatusSiu siu) {
        this.siu = (StatusSiu) siu;
    }

    @Override
    public void setStatusTtu(IStatusTtu ttu) {
        this.ttu = (StatusTtu) ttu;
    }

    @Override
    public IStatusIdc makeStatusIdc() {
        return new StatusIdc();
    }

    @Override
    public IStatusJpr makeStatusJpr() {
        return new StatusJpr();
    }

    @Override
    public IStatusRpr makeStatusRpr() {
        return new StatusRpr();
    }

    @Override
    public IStatusCdm makeStatusCdm() {
        return new StatusCdm();
    }

    @Override
    public IStatusCim makeStatusCim() {
        return new StatusCim();
    }

    @Override
    public IStatusPin makeStatusPin() {
        return new StatusPin();
    }

    @Override
    public IStatusSiu makeStatusSiu() {
        return new StatusSiu();
    }

    @Override
    public IStatusTtu makeStatusTtu() {
        return new StatusTtu();
    }

    @Override
    public IStatusNfc makeStatusNfc() {
        return new StatusNfc();
    }

    @Override
    public IStatusPbk makeStatusPbk() {
        return new StatusPbk();
    }

    @Override
    public void setXfsStatus(IXfsStatus xfsStatus) {
    	this.terminalId = xfsStatus.getTerminalId() ;
        this.setBoxStatus(xfsStatus.getBoxStatus());
        this.setBoxCurrentCount(xfsStatus.getBoxCurrentCount());
        this.setBoxInitCount(xfsStatus.getBoxInitCount());
        this.setModStatus(xfsStatus.getModStatus());
        this.setRunStatus(xfsStatus.getRunStatus());
        this.setNetStatus(xfsStatus.getNetStatus());
        this.setStatusCdm(xfsStatus.getStatusCdm());
        this.setStatusCim(xfsStatus.getStatusCim());
        this.setStatusIdc(xfsStatus.getStatusIdc());
        this.setStatusJpr(xfsStatus.getStatusJpr());
        this.setStatusPin(xfsStatus.getStatusPin());
        this.setStatusRpr(xfsStatus.getStatusRpr());
        this.setStatusSiu(xfsStatus.getStatusSiu());
        this.setStatusTtu(xfsStatus.getStatusTtu());
        this.setStatusNfc(xfsStatus.getStatusNfc());
        this.setStatusPbk(xfsStatus.getStatusPbk());
        this.setStatusIcc(xfsStatus.getStatusIcc());
        this.setStatusFgp(xfsStatus.getStatusFgp());
        this.setStatusIsc(xfsStatus.getStatusIsc());
        this.setStatusBcr(xfsStatus.getStatusBcr());
        this.setStatusCam(xfsStatus.getStatusCam());
        this.setStatusUkd(xfsStatus.getStatusUkd());
        this.setStatusUkr(xfsStatus.getStatusUkr());
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
        XfsStatus other = (XfsStatus) obj;
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

        if (icc == null) {
            if (other.icc != null) {
                return false;
            }
        } else if (!icc.equals(other.icc)) {
            return false;
        }
        if (fgp == null) {
            if (other.fgp != null) {
                return false;
            }
        } else if (!fgp.equals(other.fgp)) {
            return false;
        }
        if (isc == null) {
            if (other.isc != null) {
                return false;
            }
        } else if (!isc.equals(other.isc)) {
            return false;
        }
        
        if (bcr == null) {
            if (other.bcr != null) {
                return false;
            }
        } else if (!bcr.equals(other.bcr)) {
            return false;
        }

        if (cam == null) {
            if (other.cam != null) {
                return false;
            }
        } else if (!cam.equals(other.cam)) {
            return false;
        }
        if (ukr == null) {
            if (other.ukr != null) {
                return false;
            }
        } else if (!ukr.equals(other.ukr)) {
            return false;
        }
        if (ukd == null) {
            if (other.ukd != null) {
                return false;
            }
        } else if (!ukd.equals(other.ukd)) {
            return false;
        }
        return true;
    }

    @Override
    public void setStatusIcc(IStatusIcc icc) {
        this.icc = (StatusIcc) icc;
    }

    @Override
    public IStatusIcc getStatusIcc() {
        return this.icc;
    }

    @Override
    public IStatusIcc makeStatusIcc() {
        return new StatusIcc();
    }

    @Override
    public void setStatusFgp(IStatusFgp fgp) {
        this.fgp = (StatusFgp) fgp;
    }

    @Override
    public IStatusFgp getStatusFgp() {
        return this.fgp;
    }

    @Override
    public IStatusFgp makeStatusFgp() {
        return new StatusFgp();
    }

    @Override
    public void setStatusIsc(IStatusIsc isc) {
        this.isc = (StatusIsc) isc;
    }

    @Override
    public IStatusIsc getStatusIsc() {
        return this.isc;
    }

    @Override
    public IStatusIsc makeStatusIsc() {
        return new StatusIsc();
    }

	@Override
	public IXfsStatus getHisXfsStatus() {
		return this.hisXfsStatus;
	}

	@Override
	public void setHisXfsStatus(IXfsStatus hisXfsStatus) {
		this.hisXfsStatus = hisXfsStatus ;
	}
	
	@Override
    public void setStatusBcr(IStatusBcr bcr) {
        this.bcr = (StatusBcr) bcr;
    }

    @Override
    public IStatusBcr getStatusBcr() {
        return this.bcr;
    }

    @Override
    public IStatusBcr makeStatusBcr() {
        return new StatusBcr();
    }
    
	@Override
    public void setStatusCam(IStatusCam cam) {
        this.cam = (StatusCam) cam;
    }

    @Override
    public IStatusCam getStatusCam() {
        return this.cam;
    }

    @Override
    public IStatusCam makeStatusCam() {
        return new StatusCam();
    }
    
    @Override
    public void setStatusUkr(IStatusUkr ukr) {
        this.ukr = (StatusUkr) ukr;
    }

    @Override
    public IStatusUkr getStatusUkr() {
        return this.ukr;
    }

    @Override
    public IStatusUkr makeStatusUkr() {
        return new StatusUkr();
    }
    
    @Override
    public void setStatusUkd(IStatusUkd ukd) {
        this.ukd = (StatusUkd) ukd;
    }

    @Override
    public IStatusUkd getStatusUkd() {
        return this.ukd;
    }

    @Override
    public IStatusUkd makeStatusUkd() {
        return new StatusUkd();
    }
    
}
