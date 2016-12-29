package com.yihuacomputer.fish.web.daily.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.api.person.IPerson;

/**
 * @author YiHua
 *
 */
public class AtmMoveForm {
    private long id;

    private String date;

    private String terminalId;

    private String address;

    private long orgId;
    private String orgName;

    private String responsibility;

    private String targetAddress;

    private String targetOrganization;

    private long targetOrganizationId;
    private String targetPerson;
    private String destPerson;
    private String notice;

    public String getTargetPerson() {
		return targetPerson;
	}

	public void setTargetPerson(String targetPerson) {
		this.targetPerson = targetPerson;
	}

	public String getDestPerson() {
		return destPerson;
	}

	public void setDestPerson(String destPerson) {
		this.destPerson = destPerson;
	}

	public AtmMoveForm() {
    };

    /**
     * @param atmMove
     */
    public AtmMoveForm(IAtmMove atmMove) {
        this.id = atmMove.getId();
        this.address = atmMove.getAddress();
        this.orgName = atmMove.getOrganization().getName();
        this.date = DateUtils.getDate(atmMove.getDate());
        this.notice = atmMove.getNotice();
        this.terminalId = atmMove.getTerminalId();
        this.responsibility = atmMove.getResponsibility();
        this.targetAddress = atmMove.getTargetAddress();
        this.targetOrganization = atmMove.getTargetOrganization().getName();
        IPerson destPerson = atmMove.getOrganization().getManager();
        IPerson targetPerson = atmMove.getOrganization().getManager();
        if(null!=destPerson){
        	this.destPerson = destPerson.getName();
        }
        if(null!=targetPerson){
        this.targetPerson = targetPerson.getName();
        }
    }

    /**
     * @param atmMove
     */
    public void translate(IAtmMove atmMove) {
        atmMove.setId(getId());
        atmMove.setAddress(getAddress());

        atmMove.getOrganization().setGuid(getOrgId() + "");

        atmMove.setDate(DateUtils.getDate(this.date));
        atmMove.setNotice(getNotice());
        atmMove.setTerminalId(getTerminalId());
        atmMove.setResponsibility(getResponsibility());
        atmMove.setTargetAddress(getTargetAddress());
        atmMove.getTargetOrganization().setGuid("" + getTargetOrganizationId());

    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    /**
     * @param list
     * @return
     */
    public static List<AtmMoveForm> convert(List<IAtmMove> list) {
        List<AtmMoveForm> result = new ArrayList<AtmMoveForm>();
        for (IAtmMove item : list) {
            result.add(new AtmMoveForm(item));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTargetOrganization() {
        return targetOrganization;
    }

    public void setTargetOrganization(String targetOrganization) {
        this.targetOrganization = targetOrganization;
    }

    public long getTargetOrganizationId() {
        return targetOrganizationId;
    }

    public void setTargetOrganizationId(long targetOrganizationId) {
        this.targetOrganizationId = targetOrganizationId;
    }

}
