package com.yihuacomputer.fish.system.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.permission.service.api.IDomainGroupService;

/**
 * 组织
 * @author 樊晓雨
 * @version
 * @date 2010-8-18
 */
//@Entity
//@Table(name = "SM_GROUP")
public class Group implements IGroup {
	/** 用户组的ID**/
	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_SM_GROUP")
	@SequenceGenerator(name="SEQ_SM_GROUP", sequenceName="SEQ_SM_GROUP")
	@Column(name="PM_GROUP_ID")
	private long id;

	@Column(name="GROUP_NAME", length=128)
	private String name;

//	@ManyToOne(targetEntity = com.yihuacomputer.fish.person.Organization.class)
//	@JoinColumn(name = "ORGANIZATION_ID")
//	private IOrganization organization;
	@Column(name="ORGANIZATION_CODE")
	private String organizationCode;

	public String getOrganizationCode() {
		return organizationCode;
	}


	@Transient
	private IDomainGroupService service;

	public Group(){
	}

	public Group(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IDomainGroupService getService() {
		return service;
	}

	public void setService(IDomainGroupService service) {
		this.service = service;
	}

	@Override
	public List<IUser> listUser() {
		return service.listUser(this);
	}

	@Override
	public List<IRole> listRole() {
		return service.listRole(this);
	}

	public IOrganization getOrganization() {
		return null;
	}

	public void setOrganization(IOrganization organization) {
		this.organizationCode = organization.getCode();
	}

}