package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.OrganizationState;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.person.service.api.IDomainOrganizationService;

/**
 * 机构信息：（机构信息对应数据库表ORG）
 *
 * @author huxiaobao
 *
 */
@Entity
@Table(name = "SM_ORG")
public class Organization implements IOrganization,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 191792206466727269L;

	@Transient
	private IDomainOrganizationService service;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_ORG")
	@SequenceGenerator(name = "SEQ_SM_ORG", sequenceName = "SEQ_SM_ORG")
	@Column(name = "ID")
	private long id;

	@Column(name = "CODE", nullable = false, length = 40)
	private String code;

	@Column(name = "NAME", nullable = false, length = 40)
	private String name;

	@Column(name = "ADDRESS", nullable = true, length = 60)
	private String address;

	@Column(name = "ZIP", nullable = true, length = 20)
	private String zip;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ORG_TYPE", nullable = true, length = 10)
	private OrganizationType organizationType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATE", nullable = true, length = 10)
	private OrganizationState organizationState;

	// @Column(name = "TYPE", nullable = true, length = 256)
	// private String organizationType;

	@Column(name = "DESCRIPTION", nullable = true, length = 40)
	private String description;

	@Transient
	private IPerson manager;

	@Column(name = "PERSON_ID", nullable = true)
	private long personId;

	@Column(name = "ORG_FLAG", nullable = true, length = 100)
	private String orgFlag;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "PARENT_ID")
	private IOrganization parent;

	@Transient
	private List<Organization> children = new ArrayList<Organization>();

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "SERVICE_OBJECT")
	private IOrganization serviceObject;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ORG_LEVEL", nullable = true, length = 10)
	private OrganizationLevel organizationLevel;

	@Column(name = "AREA_FLAG", nullable = true, length = 24)
	private String areaFlag;

	@Column(name = "IS_NOTE_SEND", nullable = true, length = 2)
	private String isNoteSend;

	@Column(name = "SUGGEST", nullable = true, length = 120)
	private String suggest;

	@Column(name = "LEGAL_PER_CODE", nullable = true, length = 10)
	private String legalPerCode;

	@Column(name = "APPLICATION_PER", nullable = true, length = 24)
	private String applicationPer; 
	
	/**
	 * 是否为叶子机构（默认为叶子机构）;update之前判断上一级机构更改后是否为叶子机构；update之后判断上一级机构是否为叶子机构
	 */
	@org.hibernate.annotations.Type(type="com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name="IS_LEAF", nullable=false)
	private boolean leaf;
	
	public Organization() {}

	public IDomainOrganizationService getService() {
		return service;
	}

	public void setService(IDomainOrganizationService service) {
		this.service = service;
	}

	@Override
	public Organization addChild(String code, String name) {
		Organization result = service.add(code, name, this);
		return result;
	}

	@Override
	public Organization getChild(String code) {
		for (IOrganization item : getChildren()) {
			if (code.equals(item.getCode())) {
				return (Organization)item;
			}
		}
		throw new NotFoundException("child not found");
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getGuid() {
		return String.valueOf(id);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IOrganization getParent() {
		return parent;
	}

	@Override
	public Iterable<IPerson> listAllPerson() {
		return null;
	}

	@Override
	public Iterable<IOrganization> listChildren() {
		return getChildren();
	}

	@Override
	public Iterable<IOrganization> listChildren(OrganizationType organizationType) {

		List<IOrganization> listChildren = new ArrayList<IOrganization>();
		for (IOrganization item : getChildren()) {
			if (organizationType.equals(item.getOrganizationType())) {
				listChildren.add(item);
			}
		}
		return listChildren;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void setGuid(String guid) {
		this.id = Long.valueOf(guid);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setParent(IOrganization organization) {
		this.parent = organization;
		if (organization != null) {
			((Organization) parent).getChildren().add(this);
		}
	}

	public void copyProperties(IOrganization organization) {
		setCode(organization.getCode());
		setName(organization.getName());
		setAddress(organization.getAddress());
		setZip(organization.getZip());
	}

	@Override
	public IPerson getManager() {
		if (this.manager == null && this.personId != 0) {
			this.manager = service.getPersonService().get(String.valueOf(personId));
		}
		return manager;
	}

	@Override
	public void setManager(IPerson manager) {
		this.manager = manager;
		if (manager != null) {
			this.personId = Long.valueOf(manager.getGuid());
		} else {
			this.personId = 0;
		}
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	@Override
	public int compareTo(IOrganization o) {
		return this.getCode().compareTo(o.getCode());
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<IOrganization> getChildren() {
		IFilter filter = new Filter();
		filter.eq("parent.id", this.id);
		return (ArrayList<IOrganization>)this.service.list(filter);
	}

	@Override
	public OrganizationType getOrganizationType() {
		return this.organizationType;
	}

	@Override
	public void setOrganizationType(OrganizationType type) {
		this.organizationType = type;
	}

	@Override
	public OrganizationState getOrganizationState() {
		return this.organizationState;
	}

	@Override
	public void setOrganizationState(OrganizationState state) {
		this.organizationState = state;
	}

	@Override
	public String getOrgFlag() {
		return orgFlag;
	}

	@Override
	public void setOrgFlag(String orgFlag) {
		this.orgFlag = orgFlag;
	}

	@Override
	public IOrganization getServiceObject() {
		return serviceObject;
	}

	@Override
	public void setServiceObject(IOrganization serviceObject) {
		this.serviceObject = serviceObject;
	}

	@Override
	public OrganizationLevel getOrganizationLevel() {
		return organizationLevel;
	}

	@Override
	public void setOrganizationLevel(OrganizationLevel organizationLevel) {
		this.organizationLevel = organizationLevel;
	}

	@Override
	public String getAreaFlag() {
		return this.areaFlag;
	}

	@Override
	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}

	@Override
	public String getIsNoteSend() {
		return this.isNoteSend;
	}

	@Override
	public void setIsNoteSend(String isNoteSend) {
		this.isNoteSend = isNoteSend;
	}

	@Override
	public String getSuggest() {
		return this.suggest;
	}

	@Override
	public void setSuggest(String suggest) {
		this.suggest = suggest;

	}

	@Override
	public String getLegalPerCode() {
		return this.legalPerCode;
	}

	@Override
	public void setLegalPerCode(String legalPerCode) {
		this.legalPerCode = legalPerCode;

	}

	@Override
	public String getApplicationPer() {
		return this.applicationPer;
	}

	@Override
	public void setApplicationPer(String applicationPer) {
		this.applicationPer = applicationPer;
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}

	@Override
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
}
