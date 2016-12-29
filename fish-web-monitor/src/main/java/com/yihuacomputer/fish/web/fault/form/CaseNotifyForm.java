package com.yihuacomputer.fish.web.fault.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.fault.ICaseNotify;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.person.IPerson;

/**
 * @author YiHua
 *
 */
public class CaseNotifyForm {
	
	private long id; 
	
	private long faultId;
	
	private String terminalId;
	
	private String createTime;
	
	private String bankPer;
	
	private String serPer;

	private String content;

	private NotifyWay notifyWay;

	private String mobile;
	
	private String mail;
	
	private int notifyTimes;
	
	private int sendTimes;
	
	private int sendInterval;
	
	private String sendTime;
	
	
	public CaseNotifyForm(){
		
	}
	
	/**
	 * @param caseNotify
	 */
	public CaseNotifyForm(ICaseNotify caseNotify){
		this.id = caseNotify.getId();
		this.terminalId = caseNotify.getTerminalId();
		this.faultId = caseNotify.getFaultId();
		this.content = caseNotify.getContent();
		if(caseNotify.getCreateTime()!=null){
			this.createTime = DateUtils.getTimestamp(caseNotify.getCreateTime());
		}
		this.notifyWay = caseNotify.getNotifyWay();
		this.mail = caseNotify.getMail();
		this.mobile = caseNotify.getMobile();
		this.notifyTimes = caseNotify.getNotifyTimes();
		this.sendInterval = caseNotify.getSendInterval();
		this.sendTimes = caseNotify.getSendTimes();
		if(caseNotify.getSendTime()!=null){
			this.sendTime = DateUtils.getTimestamp(caseNotify.getSendTime());
		}
		this.bankPer = "";
		this.serPer = "";
		int bankCounter=0,serCounter=0;
		for(IPerson person:caseNotify.getBankPerson()){
			this.bankPer+=person.getName();
			bankCounter++;
			if(bankCounter==caseNotify.getBankPerson().size()){
				break;
			}
			this.bankPer+=";";
		}
		for(IPerson person:caseNotify.getSerPerson()){
			this.serPer+=person.getName();
			serCounter++;
			if(serCounter==caseNotify.getSerPerson().size()){
				break;
			}
			this.serPer+=";";
		}
	}
	
	/**
     * 
     * 方法描述 : 转换
     * 
     * @param resources
     * @return List
     */

    public static List<CaseNotifyForm> convert(List<ICaseNotify> resources) {
        List<CaseNotifyForm> result = new ArrayList<CaseNotifyForm>();
        for (ICaseNotify resource : resources) {
            result.add(new CaseNotifyForm(resource));
        }
        return result;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getFaultId() {
		return faultId;
	}

	public void setFaultId(long faultId) {
		this.faultId = faultId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotifyWay getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(NotifyWay notifyWay) {
		this.notifyWay = notifyWay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(int notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public int getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(int sendTimes) {
		this.sendTimes = sendTimes;
	}

	public int getSendInterval() {
		return sendInterval;
	}

	public void setSendInterval(int sendInterval) {
		this.sendInterval = sendInterval;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

	public String getBankPer() {
		return bankPer;
	}

	public void setBankPer(String bankPer) {
		this.bankPer = bankPer;
	}

	public String getSerPer() {
		return serPer;
	}

	public void setSerPer(String serPer) {
		this.serPer = serPer;
	}	
	
}
