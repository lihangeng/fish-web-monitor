package com.yihuacomputer.fish.advert.bs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.advert.bs.GroupType;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;

@Entity
@Table(name = "ADV_GROUP")
public class AdvertGroup implements IAdvertGroup, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_GROUP")
	@SequenceGenerator(name = "SEQ_ADV_GROUP", sequenceName = "SEQ_ADV_GROUP")
	@Column(name = "ID")
	private long id;

	/**
	 * 组类型
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "GROUP_TYPE", nullable = false, length = 32)
	private GroupType groupType;

	@Column(name = "ORG_ID")
	private long orgId;

	@Column(name = "GROUP_NAME", length = 140)
	private String groupName;

	@Column(name = "RESOURCE_PATH", length = 140)
	private String path;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public GroupType getGroupType() {
		return groupType;
	}

	@Override
	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	@Override
	public long getOrgId() {
		return orgId;
	}

	@Override
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
    public String getGuid() {
        return String.valueOf(id);
    }
	

}
