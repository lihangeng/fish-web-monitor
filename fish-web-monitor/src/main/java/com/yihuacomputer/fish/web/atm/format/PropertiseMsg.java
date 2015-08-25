package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;

/**
 * 设备模块属性信息
 *
 * @author YiHua
 *
 */
public class PropertiseMsg {
    private String termId;

    private DeviceProp idc;

    private DeviceProp cdm;

    private DeviceProp cim;

    private DeviceProp rpr;

    private DeviceProp jpr;

    private DeviceProp ttu;

    private DeviceProp pin;

    private DeviceProp siu;

    private DeviceProp pbk;

    private DeviceProp nfc;

    private DeviceProp icc ;

    private DeviceProp fgp ;

    private DeviceProp isc ;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public DeviceProp isIdc() {
        return idc;
    }

    public void setIdc(DeviceProp idc) {
        this.idc = idc;
    }

    public DeviceProp isCdm() {
        return cdm;
    }

    public void setCdm(DeviceProp cdm) {
        this.cdm = cdm;
    }

    public DeviceProp isCim() {
        return cim;
    }

    public void setCim(DeviceProp cim) {
        this.cim = cim;
    }

    public DeviceProp isRpr() {
        return rpr;
    }

    public void setRpr(DeviceProp rpr) {
        this.rpr = rpr;
    }

    public DeviceProp isJpr() {
        return jpr;
    }

    public void setJpr(DeviceProp jpr) {
        this.jpr = jpr;
    }

    public DeviceProp isTtu() {
        return ttu;
    }

    public void setTtu(DeviceProp ttu) {
        this.ttu = ttu;
    }

    public DeviceProp isPin() {
        return pin;
    }

    public void setPin(DeviceProp pin) {
        this.pin = pin;
    }

    public DeviceProp isSiu() {
        return siu;
    }

    public void setSiu(DeviceProp siu) {
        this.siu = siu;
    }

    public DeviceProp getIdc() {
        return idc;
    }

    public DeviceProp getCdm() {
        return cdm;
    }

    public DeviceProp getCim() {
        return cim;
    }

    public DeviceProp getRpr() {
        return rpr;
    }

    public DeviceProp getJpr() {
        return jpr;
    }

    public DeviceProp getTtu() {
        return ttu;
    }

    public DeviceProp getPin() {
        return pin;
    }

    public DeviceProp getSiu() {
        return siu;
    }

	public DeviceProp getPbk() {
		return pbk;
	}

	public void setPbk(DeviceProp pbk) {
		this.pbk = pbk;
	}

    public DeviceProp isPbk() {
        return pbk;
    }

	public DeviceProp getNfc() {
		return nfc;
	}

	public void setNfc(DeviceProp nfc) {
		this.nfc = nfc;
	}
	  public DeviceProp isNfc() {
	        return nfc;
	    }

	public DeviceProp isIcc() {
		return icc;
	}

	public void setIcc(DeviceProp icc) {
		this.icc = icc;
	}

	public DeviceProp getIcc(){
		return icc ;
	}

	public DeviceProp isFgp() {
		return fgp;
	}

	public void setFgp(DeviceProp fgp) {
		this.fgp = fgp;
	}

	public DeviceProp getFgp(){
		return fgp ;
	}

	public DeviceProp isIsc() {
		return isc;
	}

	public void setIsc(DeviceProp isc) {
		this.isc = isc;
	}

	public DeviceProp getIsc(){
		return isc ;
	}

}
