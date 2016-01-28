package com.yihuacomputer.fish.advert.bs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;

@Entity
@Table(name = "ADV_BSADVERT")
public class BsAdvert implements IBsAdvert, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_BSADVERT")
	@SequenceGenerator(name = "SEQ_ADV_BSADVERT", sequenceName = "SEQ_ADV_BSADVERT")
	@Column(name = "ID")
	private long id;

	@Column(name = "GROUP_ID")
	private long groupId;

	@Column(name = "ADVERT_NAME")
	private String advertName;
	
	@org.hibernate.annotations.Type(type="com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "ACTIVED")
	private boolean actived;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_TIME")
	private Date lastTime;

	@Column(name = "PERSON_ID", nullable = true, length = 20)
	private long personId;

	@Column(name = "ACTIVE_PERSON_ID", nullable = true, length = 20)
	private long activePersonId;

	@OneToMany(targetEntity = BsAdvertResource.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADVERT_ID")
	private List<IBsAdvertResource> advertResources = new ArrayList<IBsAdvertResource>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public boolean getActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getActivePersonId() {
		return activePersonId;
	}

	public void setActivePersonId(long activePersonId) {
		this.activePersonId = activePersonId;
	}

	public List<IBsAdvertResource> getAdvertResources() {
		return advertResources;
	}

	public void setAdvertResources(List<IBsAdvertResource> advertResources) {
		this.advertResources = advertResources;
	}

}
