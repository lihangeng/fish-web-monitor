package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * 设备模块状态信息
 *
 * @author liuwei
 *
 */
public class StatusMsg {

    // 设备编号
    private String termId;

    // ATMC应用运行状态
    private RunStatus runStatus;

    // 钞箱总体状态
    private BoxStatus boxStatus;

    // 设备模块总状态
    private DeviceStatus modStatus;

    // 读卡器模块总状态
    private DeviceStatus idc;

    // 读卡器模块状态代码
    private String idcCode;

    // 读卡器厂商硬件状态码
    private String idcHwCode;

    //读卡器当前吞卡数量
    private int idcReatianCard;

    // 射频读卡器模块总状态
    private DeviceStatus nfc;

    // 射频读卡器模块状态代码
    private String nfcCode;

    // 射频读卡器厂商硬件状态码
    private String nfcHwCode;

    // 日志打印模块总状态
    private DeviceStatus jpr;

    // 日志打印模块状态代码
    private String jprCode;

    // 日志打印厂商硬件状态代码
    private String jprHwCode;

    // 凭条打印模块总状态
    private DeviceStatus rpr;

    // 凭条打印模块状态代码
    private String rprCode;

    // 凭条打印厂商硬件状态代码
    private String rprHwCode;

    // 取款模块总状态
    private DeviceStatus cdm;

    // 取款模块状态代码
    private String cdmCode;

    // 取款模块厂商硬件状态代码
    private String cdmHwCode;

    // 存款模块总状态
    private DeviceStatus cim;

    // 存款模块状态代码
    private String cimCode;

    // 存款模块厂商硬件状态代码
    private String cimHwCode;

    // 密码键盘模块总状态
    private DeviceStatus pin;

    // 密码键盘模块状态代码
    private String pinCode;

    // 密码键盘厂商硬件状态代码
    private String pinHwCode;

    // 文本终端模块总状态
    private DeviceStatus ttu;

    // 文本终端模块状态代码
    private String ttuCode;

    // 文本终端厂商硬件状态代码
    private String ttuHwCode;

    // 传感器模块总状态
    private DeviceStatus siu;

    // 传感器模块状态代码
    private String siuCode;

    // 传感器模块厂商硬件状态代码
    private String siuHwCode;

    // 存折打印模块总状态
    private DeviceStatus pbk;

    // 存折打印模块状态代码
    private String pbkCode;

    // 存折打印厂商硬件状态码
    private String pbkHwCode;

    // 钞箱初始化金额
    private int boxInitCount;

    // 钞箱剩余金额
    private int boxCurrentCount;


    // 发卡读卡器模块总状态
    private DeviceStatus icc;

    // 发卡读卡器模块状态代码
    private String iccCode;

    // 发卡读卡器厂商硬件状态码
    private String iccHwCode;

    //发卡读卡器当前吞卡数量
    private int iccReatianCard;

    //发卡读卡器当前卡槽剩余卡数
    private int iccCurrentCount ;

    // 指纹仪模块总状态
    private DeviceStatus fgp;

    // 指纹仪模块状态代码
    private String fgpCode;

    // 指纹仪厂商硬件状态码
    private String fgpHwCode;

    // 身份证扫描仪模块总状态
    private DeviceStatus isc;

    // 身份证扫描仪模块状态代码
    private String iscCode;

    // 身份证扫描仪厂商硬件状态码
    private String iscHwCode;

    /**
     * 条形码扫描模块
     */
    private DeviceStatus bcr;

    // 条形码扫描模块状态代码
    private String bcrCode;

    // 条形码扫描模块厂商硬件状态码
    private String bcrHwCode;
    
	/**
	 * 出钞口摄像头
	 * @return
	 */
	private DeviceStatus exitSlotStatus;
	
	/**
	 * 客户口摄像头
	 * @return
	 */
	private DeviceStatus personStatus;
	
	/**
	 * 房间口摄像头
	 * @return
	 */
	private DeviceStatus roomStatus;
	
    /**
     * 摄像头模块
     */
    private DeviceStatus cam;

    // 摄像头模块状态代码
    private String camCode;

    // 摄像头模块厂商硬件状态码
    private String camHwCode;
    
    public StatusMsg() {

    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public BoxStatus getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(BoxStatus boxStatus) {
        this.boxStatus = boxStatus;
    }

    public DeviceStatus getModStatus() {
        return modStatus;
    }

    public void setModStatus(DeviceStatus modStatus) {
        this.modStatus = modStatus;
    }

    public DeviceStatus getIdc() {
        return idc;
    }

    public void setIdc(DeviceStatus idc) {
        this.idc = idc;
    }

    public String getIdcCode() {
        return idcCode;
    }

    public void setIdcCode(String idcCode) {
        this.idcCode = idcCode;
    }

    public String getIdcHwCode() {
        return idcHwCode;
    }

    public void setIdcHwCode(String idcHwCode) {
        this.idcHwCode = idcHwCode;
    }

    public int getIdcReatianCard() {
        return idcReatianCard;
    }

    public void setIdcReatianCard(int idcReatianCard) {
        this.idcReatianCard = idcReatianCard;
    }

    public DeviceStatus getJpr() {
        return jpr;
    }

    public void setJpr(DeviceStatus jpr) {
        this.jpr = jpr;
    }

    public String getJprCode() {
        return jprCode;
    }

    public void setJprCode(String jprCode) {
        this.jprCode = jprCode;
    }

    public String getJprHwCode() {
        return jprHwCode;
    }

    public void setJprHwCode(String jprHwCode) {
        this.jprHwCode = jprHwCode;
    }

    public DeviceStatus getRpr() {
        return rpr;
    }

    public void setRpr(DeviceStatus rpr) {
        this.rpr = rpr;
    }

    public String getRprCode() {
        return rprCode;
    }

    public void setRprCode(String rprCode) {
        this.rprCode = rprCode;
    }

    public String getRprHwCode() {
        return rprHwCode;
    }

    public void setRprHwCode(String rprHwCode) {
        this.rprHwCode = rprHwCode;
    }

    public DeviceStatus getCdm() {
        return cdm;
    }

    public void setCdm(DeviceStatus cdm) {
        this.cdm = cdm;
    }

    public String getCdmCode() {
        return cdmCode;
    }

    public void setCdmCode(String cdmCode) {
        this.cdmCode = cdmCode;
    }

    public String getCdmHwCode() {
        return cdmHwCode;
    }

    public void setCdmHwCode(String cdmHwCode) {
        this.cdmHwCode = cdmHwCode;
    }

    public DeviceStatus getCim() {
        return cim;
    }

    public void setCim(DeviceStatus cim) {
        this.cim = cim;
    }

    public String getCimCode() {
        return cimCode;
    }

    public void setCimCode(String cimCode) {
        this.cimCode = cimCode;
    }

    public String getCimHwCode() {
        return cimHwCode;
    }

    public void setCimHwCode(String cimHwCode) {
        this.cimHwCode = cimHwCode;
    }

    public DeviceStatus getPin() {
        return pin;
    }

    public void setPin(DeviceStatus pin) {
        this.pin = pin;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinHwCode() {
        return pinHwCode;
    }

    public void setPinHwCode(String pinHwCode) {
        this.pinHwCode = pinHwCode;
    }

    public DeviceStatus getTtu() {
        return ttu;
    }

    public void setTtu(DeviceStatus ttu) {
        this.ttu = ttu;
    }

    public String getTtuCode() {
        return ttuCode;
    }

    public void setTtuCode(String ttuCode) {
        this.ttuCode = ttuCode;
    }

    public String getTtuHwCode() {
        return ttuHwCode;
    }

    public void setTtuHwCode(String ttuHwCode) {
        this.ttuHwCode = ttuHwCode;
    }

    public DeviceStatus getSiu() {
        return siu;
    }

    public void setSiu(DeviceStatus siu) {
        this.siu = siu;
    }

    public String getSiuCode() {
        return siuCode;
    }

    public void setSiuCode(String siuCode) {
        this.siuCode = siuCode;
    }

    public String getSiuHwCode() {
        return siuHwCode;
    }

    public void setSiuHwCode(String siuHwCode) {
        this.siuHwCode = siuHwCode;
    }

    public int getBoxInitCount() {
        return boxInitCount;
    }

    public void setBoxInitCount(int boxInitCount) {
        this.boxInitCount = boxInitCount;
    }

    public int getBoxCurrentCount() {
        return boxCurrentCount;
    }

    public void setBoxCurrentCount(int boxCurrentCount) {
        this.boxCurrentCount = boxCurrentCount;
    }

	public DeviceStatus getNfc() {
		return nfc;
	}

	public void setNfc(DeviceStatus nfc) {
		this.nfc = nfc;
	}

	public String getNfcCode() {
		return nfcCode;
	}

	public void setNfcCode(String nfcCode) {
		this.nfcCode = nfcCode;
	}

	public String getNfcHwCode() {
		return nfcHwCode;
	}

	public void setNfcHwCode(String nfcHwCode) {
		this.nfcHwCode = nfcHwCode;
	}

	public DeviceStatus getPbk() {
		return pbk;
	}

	public void setPbk(DeviceStatus pbk) {
		this.pbk = pbk;
	}

	public String getPbkCode() {
		return pbkCode;
	}

	public void setPbkCode(String pbkCode) {
		this.pbkCode = pbkCode;
	}

	public String getPbkHwCode() {
		return pbkHwCode;
	}

	public void setPbkHwCode(String pbkHwCode) {
		this.pbkHwCode = pbkHwCode;
	}

	public DeviceStatus getIcc() {
		return icc;
	}

	public void setIcc(DeviceStatus icc) {
		this.icc = icc;
	}

	public String getIccCode() {
		return iccCode;
	}

	public void setIccCode(String iccCode) {
		this.iccCode = iccCode;
	}

	public String getIccHwCode() {
		return iccHwCode;
	}

	public void setIccHwCode(String iccHwCode) {
		this.iccHwCode = iccHwCode;
	}

	public int getIccReatianCard() {
		return iccReatianCard;
	}

	public void setIccReatianCard(int iccReatianCard) {
		this.iccReatianCard = iccReatianCard;
	}

	public int getIccCurrentCount() {
		return iccCurrentCount;
	}

	public void setIccCurrentCount(int iccCurrentCount) {
		this.iccCurrentCount = iccCurrentCount;
	}

	public DeviceStatus getFgp() {
		return fgp;
	}

	public void setFgp(DeviceStatus fgp) {
		this.fgp = fgp;
	}

	public String getFgpCode() {
		return fgpCode;
	}

	public void setFgpCode(String fgpCode) {
		this.fgpCode = fgpCode;
	}

	public String getFgpHwCode() {
		return fgpHwCode;
	}

	public void setFgpHwCode(String fgpHwCode) {
		this.fgpHwCode = fgpHwCode;
	}

	public DeviceStatus getIsc() {
		return isc;
	}

	public void setIsc(DeviceStatus isc) {
		this.isc = isc;
	}

	public String getIscCode() {
		return iscCode;
	}

	public void setIscCode(String iscCode) {
		this.iscCode = iscCode;
	}

	public String getIscHwCode() {
		return iscHwCode;
	}

	public void setIscHwCode(String iscHwCode) {
		this.iscHwCode = iscHwCode;
	}

	public DeviceStatus getBcr() {
		return bcr;
	}

	public void setBcr(DeviceStatus bcr) {
		this.bcr = bcr;
	}

	public String getBcrCode() {
		return bcrCode;
	}

	public void setBcrCode(String bcrCode) {
		this.bcrCode = bcrCode;
	}

	public String getBcrHwCode() {
		return bcrHwCode;
	}

	public void setBcrHwCode(String bcrHwCode) {
		this.bcrHwCode = bcrHwCode;
	}

	public DeviceStatus getCam() {
		return cam;
	}

	public void setCam(DeviceStatus cam) {
		this.cam = cam;
	}

	public String getCamCode() {
		return camCode;
	}

	public void setCamCode(String camCode) {
		this.camCode = camCode;
	}

	public String getCamHwCode() {
		return camHwCode;
	}

	public void setCamHwCode(String camHwCode) {
		this.camHwCode = camHwCode;
	}

	public DeviceStatus getExitSlotStatus() {
		return exitSlotStatus;
	}

	public void setExitSlotStatus(DeviceStatus exitSlotStatus) {
		this.exitSlotStatus = exitSlotStatus;
	}

	public DeviceStatus getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(DeviceStatus personStatus) {
		this.personStatus = personStatus;
	}

	public DeviceStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(DeviceStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	
	
}
