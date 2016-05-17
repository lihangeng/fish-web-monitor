package com.yihuacomputer.fish.web.fault.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.fault.FaultCloseType;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.person.IPerson;

public class CaseFaultForm {

	private long id;

	private String terminalId;

	private DeviceMod devMod;

	private String faultClassify;

	private String faultCode;

	private String vendorHwCode;

	private String faultTime;

	private String closedTime;

	private double duration;

	private FaultStatus faultStatus;

	private int upgrade;

	private String bankPer;

	private String org;
	private String serPer;

	private FaultCloseType faultCloseType ;

	public CaseFaultForm(){

	}

	public CaseFaultForm(ICaseFault caseFault){
		this.id = caseFault.getId();
		this.terminalId = caseFault.getTerminalId();
		this.faultStatus = caseFault.getFaultStatus();
		if(caseFault.getFaultClassify()!=null){
			this.faultClassify = caseFault.getFaultClassify().getClassifyName();
		}
		if(caseFault.getClosedTime()!=null){
			this.closedTime = DateUtils.getTimestamp(caseFault.getClosedTime());
		}
		if(caseFault.getFaultTime()!=null){
			this.faultTime = DateUtils.getTimestamp(caseFault.getFaultTime());
		}
		this.devMod = caseFault.getDevMod();
		this.duration = caseFault.getDuration();
		this.faultCode = caseFault.getFaultCode();
		this.vendorHwCode = caseFault.getVendorHwCode();
		this.faultStatus = caseFault.getFaultStatus();
		this.upgrade = caseFault.getUpgrade();
		int bankCounter=0,serCounter=0;
		this.bankPer="";
		this.serPer="";
		for(IPerson person:caseFault.getBankPerson()){
			this.bankPer+=person.getName();
			bankCounter++;
			if(bankCounter==caseFault.getBankPerson().size()){
				break;
			}
			this.bankPer+=";";
		}
		for(IPerson person:caseFault.getServicePerson()){
			this.serPer+=person.getName();
			serCounter++;
			if(serCounter==caseFault.getServicePerson().size()){
				break;
			}
			this.serPer+=";";
		}
		this.org = caseFault.getOrg().getName();

		this.faultCloseType = caseFault.getCloseType() ;
	}

	/**
     *
     * 方法描述 : 转换
     *
     * @param resources
     * @return List
     */

    public static List<CaseFaultForm> convert(List<ICaseFault> resources) {
        List<CaseFaultForm> result = new ArrayList<CaseFaultForm>();
        for (ICaseFault resource : resources) {
            result.add(new CaseFaultForm(resource));
        }
        return result;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public DeviceMod getDevMod() {
		return devMod;
	}

	public void setDevMod(DeviceMod devMod) {
		this.devMod = devMod;
	}

	public String getFaultClassify() {
		return faultClassify;
	}

	public void setFaultClassify(String faultClassify) {
		this.faultClassify = faultClassify;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getVendorHwCode() {
		return vendorHwCode;
	}

	public void setVendorHwCode(String vendorHwCode) {
		this.vendorHwCode = vendorHwCode;
	}

	public String getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}

	public String getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(String closedTime) {
		this.closedTime = closedTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public FaultStatus getFaultStatus() {
		return faultStatus;
	}

	public void setFaultStatus(FaultStatus faultStatus) {
		this.faultStatus = faultStatus;
	}

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	public String getBankPer() {
		return bankPer;
	}

	public void setBankPer(String bankPer) {
		this.bankPer = bankPer;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSerPer() {
		return serPer;
	}

	public void setSerPer(String serPer) {
		this.serPer = serPer;
	}

	public FaultCloseType getFaultCloseType() {
		return faultCloseType;
	}

	public void setFaultCloseType(FaultCloseType faultCloseType) {
		this.faultCloseType = faultCloseType;
	}

}
