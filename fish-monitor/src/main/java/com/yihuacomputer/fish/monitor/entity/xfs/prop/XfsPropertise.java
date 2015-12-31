package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropBcr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCam;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCdm;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCim;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropFgp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIcc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIdc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIsc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropJpr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropNfc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPbk;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPin;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropRpr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropSiu;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropTtu;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;

@Entity
@Table(name = "DEV_XFS_PROPERTISE")
public class XfsPropertise implements IXfsPropertise {

    @Id
    @Column(name = "TERMINAL_ID", nullable = false)
    private String terminalId;

    @Embedded
    private PropIdc idc;

    @Embedded
    private PropRpr rpr;

    @Embedded
    private PropCdm cdm;

    @Embedded
    private PropCim cim;

    @Embedded
    private PropJpr jpr;

    @Embedded
    private PropTtu ttu;

    @Embedded
    private PropSiu siu;

    @Embedded
    private PropPin pin;

    @Embedded
    private PropNfc nfc;

    @Embedded
    private PropPbk pbk;

    @Embedded
    private PropIcc icc;

    @Embedded
    private PropFgp fgp;

    @Embedded
    private PropIsc isc;
    
    @Embedded
    private PropBcr bcr;

    @Embedded
    private PropCam cam;

    public XfsPropertise() {
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTermialId() {
        return this.terminalId;
    }

    @Override
    public IPropIdc getPropIdc() {
        return this.idc;
    }

    @Override
    public IPropJpr getPropJpr() {
        return this.jpr;
    }

    @Override
    public IPropRpr getPropRpr() {
        return this.rpr;
    }

    @Override
    public IPropCdm getPropCdm() {
        return this.cdm;
    }

    @Override
    public IPropCim getPropCim() {
        return this.cim;
    }

    @Override
    public IPropPin getPropPin() {
        return this.pin;
    }

    @Override
    public IPropTtu getPropTtu() {
        return this.ttu;
    }

    @Override
    public IPropSiu getPropSiu() {
        return this.siu;
    }

    public void setIdc(IPropIdc idc) {
        this.idc = (PropIdc) idc;
    }

    public void setRpr(IPropRpr rpr) {
        this.rpr = (PropRpr) rpr;
    }

    public void setCdm(IPropCdm cdm) {
        this.cdm = (PropCdm) cdm;
    }

    public void setCim(IPropCim cim) {
        this.cim = (PropCim) cim;
    }

    public void setJpr(IPropJpr jpr) {
        this.jpr = (PropJpr) jpr;
    }

    public void setTtu(IPropTtu ttu) {
        this.ttu = (PropTtu) ttu;
    }

    public void setSiu(IPropSiu siu) {
        this.siu = (PropSiu) siu;
    }

    public void setPin(IPropPin pin) {
        this.pin = (PropPin) pin;
    }

    
    public IPropBcr getBcr() {
		return bcr;
	}

	public void setBcr(IPropBcr bcr) {
		this.bcr = (PropBcr)bcr;
	}

	public IPropCam getCam() {
		return cam;
	}

	public void setCam(IPropCam cam) {
		this.cam = (PropCam)cam;
	}

	@Override
    public IPropIdc makePropIdc() {
        return new PropIdc();
    }

    @Override
    public IPropRpr makePropRpr() {
        return new PropRpr();
    }

    @Override
    public IPropCdm makePropCdm() {
        return new PropCdm();
    }

    @Override
    public IPropCim makePropCim() {
        return new PropCim();
    }

    @Override
    public IPropJpr makePropJpr() {
        return new PropJpr();
    }

    @Override
    public IPropTtu makePropTtu() {
        return new PropTtu();
    }

    @Override
    public IPropSiu makePropSiu() {
        return new PropSiu();
    }

    @Override
    public IPropPin makePropPin() {
        return new PropPin();
    }

    public IPropPbk makePropPbk() {
        return new PropPbk();
    }

    public IPropNfc makePropNfc() {
        return new PropNfc();
    }

    public IPropBcr makePropBcr() {
        return new PropBcr();
    }

    public IPropCam makePropCam() {
        return new PropCam();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdm == null) ? 0 : cdm.hashCode());
        result = prime * result + ((cim == null) ? 0 : cim.hashCode());
        result = prime * result + ((idc == null) ? 0 : idc.hashCode());
        result = prime * result + ((jpr == null) ? 0 : jpr.hashCode());
        result = prime * result + ((pin == null) ? 0 : pin.hashCode());
        result = prime * result + ((rpr == null) ? 0 : rpr.hashCode());
        result = prime * result + ((siu == null) ? 0 : siu.hashCode());
        result = prime * result + ((ttu == null) ? 0 : ttu.hashCode());
        result = prime * result + ((icc == null) ? 0 : icc.hashCode());
        result = prime * result + ((fgp == null) ? 0 : fgp.hashCode());
        result = prime * result + ((isc == null) ? 0 : isc.hashCode());
        result = prime * result + ((bcr == null) ? 0 : bcr.hashCode());
        result = prime * result + ((cam == null) ? 0 : cam.hashCode());
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
        XfsPropertise other = (XfsPropertise) obj;
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
        if (jpr == null) {
            if (other.jpr != null) {
                return false;
            }
        } else if (!jpr.equals(other.jpr)) {
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
        return true;
    }

    @Override
    public IPropNfc getPropNfc() {
        return this.nfc;
    }

    @Override
    public IPropPbk getPropPbk() {
        return this.pbk;
    }

    @Override
    public void setPbk(IPropPbk pbk) {
        this.pbk = (PropPbk) pbk;

    }

    @Override
    public void setNfc(IPropNfc nfc) {
        this.nfc = (PropNfc) nfc;
    }

    @Override
    public IPropIcc getPropIcc() {
        return this.icc;
    }

    @Override
    public void setIcc(IPropIcc icc) {
        this.icc = (PropIcc) icc;
    }

    @Override
    public IPropIcc makePropIcc() {
        return new PropIcc();
    }

    @Override
    public IPropFgp getPropFgp() {
        return this.fgp;
    }

    @Override
    public void setFgp(IPropFgp fgp) {
        this.fgp = (PropFgp) fgp;
    }

    @Override
    public IPropFgp makePropFgp() {
        return new PropFgp();
    }

    @Override
    public IPropIsc getPropIsc() {
        return this.isc;
    }

    @Override
    public void setIsc(IPropIsc isc) {
        this.isc = (PropIsc) isc;
    }

    @Override
    public IPropIsc makePropIsc() {
        return new PropIsc();
    }
}
