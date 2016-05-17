package com.yihuacomputer.fish.web.system.form;

import com.yihuacomputer.fish.api.person.IOrganization;

public class OrganizationTreeForm {

	private String id;
	private String cls;
	private String text;
	private boolean leaf = true;
    private int orgLevel;

	public OrganizationTreeForm() {
	}

	public OrganizationTreeForm(IOrganization organization) {
		id = organization.getGuid();
		cls = organization.getCode();
		text = organization.getName();
		leaf = organization.isLeaf();
		//orgLevel = organization.getOrganizationLevel().getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCls() {
		return cls;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

}
