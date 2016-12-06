package com.yihuacomputer.fish.web.atm.format;



/**
 * 状态属性报文(code,hwcode)
 * @author GQ
 *
 */
public class StatusPropertiesMsg {

	// 设备编号
	private String termId;

	// 读卡器模块状态代码
	private String idcCode;

	// 读卡器厂商硬件状态码
	private String idcHwCode;
	
	// 读卡器模块状态代码
	private String nfcCode;

	// 读卡器厂商硬件状态码
	private String nfcHwCode;

	private int idcReatianCard;

	// 日志打印模块状态代码
	private String jprCode;

	// 日志打印厂商硬件状态代码
	private String jprHwCode;

	// 凭条打印模块状态代码
	private String rprCode;

	// 凭条打印厂商硬件状态代码
	private String rprHwCode;

	// 取款模块状态代码
	private String cdmCode;

	// 取款模块厂商硬件状态代码
	private String cdmHwCode;

	// 存款模块状态代码
	private String cimCode;

	// 存款模块厂商硬件状态代码
	private String cimHwCode;

	// 密码键盘模块状态代码
	private String pinCode;

	// 密码键盘厂商硬件状态代码
	private String pinHwCode;

	// 文本终端模块状态代码
	private String ttuCode;

	// 文本终端厂商硬件状态代码
	private String ttuHwCode;

	// 传感器模块状态代码
	private String siuCode;

	// 传感器模块厂商硬件状态代码
	private String siuHwCode;

	// 存折打印模块状态代码
	private String pbkCode;

	// 存折打印厂商硬件状态码
	private String pbkHwCode;

	// 钞箱初始化金额
	private int boxInitCount;

	// 钞箱剩余金额
	private int boxCurrentCount;

	// 发卡读卡器模块状态代码
	private String iccCode;

	// 发卡读卡器厂商硬件状态码
	private String iccHwCode;

	private int iccReatianCard;

	private long iccCurrentCount;

	// 身份证扫描仪模块状态代码
	private String iscCode;

	// 身份证扫描仪厂商硬件状态码
	private String iscHwCode;

	// 指纹仪模块状态代码
	private String fgpCode;

	// 指纹仪厂商硬件状态码
	private String fgpHwCode;

	private String camCode;

	private String camHwCode;
	
	
	//Ukey 发卡主状态
	private String ukeyDispenserCode;
	private String ukeyDispenserHwCode;
	
	
	
	//Ukey 读卡主状态
	private String ukeyReaderCode;
	private String ukeyReaderHwCode;

	private String bcrCode;
	private String bcrHwCode;
	
	public StatusPropertiesMsg(){}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
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

	public int getIdcReatianCard() {
		return idcReatianCard;
	}

	public void setIdcReatianCard(int idcReatianCard) {
		this.idcReatianCard = idcReatianCard;
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

	public long getIccCurrentCount() {
		return iccCurrentCount;
	}

	public void setIccCurrentCount(long iccCurrentCount) {
		this.iccCurrentCount = iccCurrentCount;
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

	public String getUkeyDispenserCode() {
		return ukeyDispenserCode;
	}

	public void setUkeyDispenserCode(String ukeyDispenserCode) {
		this.ukeyDispenserCode = ukeyDispenserCode;
	}

	public String getUkeyDispenserHwCode() {
		return ukeyDispenserHwCode;
	}

	public void setUkeyDispenserHwCode(String ukeyDispenserHwCode) {
		this.ukeyDispenserHwCode = ukeyDispenserHwCode;
	}

	public String getUkeyReaderCode() {
		return ukeyReaderCode;
	}

	public void setUkeyReaderCode(String ukeyReaderCode) {
		this.ukeyReaderCode = ukeyReaderCode;
	}

	public String getUkeyReaderHwCode() {
		return ukeyReaderHwCode;
	}

	public void setUkeyReaderHwCode(String ukeyReaderHwCode) {
		this.ukeyReaderHwCode = ukeyReaderHwCode;
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

}
