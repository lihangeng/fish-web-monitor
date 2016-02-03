package com.yihuacomputer.fish.advert.bs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResourceService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;

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
	
    /**
     * 广告类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ADVERT_TYPE", nullable = false, length = 32)
    private AdvertType advertType;
	
	@org.hibernate.annotations.Type(type="com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "ACTIVED",columnDefinition="CHAR",length=1)
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

    @Transient
    private IBsAdvertService advertService;
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

	public AdvertType getAdvertType() {
		return advertType;
	}

	public void setAdvertType(AdvertType advertType) {
		this.advertType = advertType;
	}

	@Override
	public void addAdvertResource(IBsAdvertResource resource) {
		this.advertResources.add(resource);
	     if (this.getId() > 0) {
	            resource.setBsAdvert(this);
	            if(resource.getId()>0){
	            	getResourceService().update(resource);
	            }
	            else{
	            	getResourceService().save(resource);
	            }
	     }
	   
	}
    private IBsAdvertResourceService getResourceService() {
        return this.advertService.getBsAdvertResourceService();
    }
    
    public void insertBsAdvertService(IBsAdvertService advertService){
    	this.advertService = advertService;
    }
	@Override
	public void removeAdvertResource(IBsAdvertResource resource) {
		this.advertResources.remove(resource);
	  if (this.getId() > 0 && resource.getId() > 0) {
            getResourceService().delete(resource);
        }
	}

    public String getAdvertConfigByScreen(Screen screen){
    	 StringBuffer cfg = new StringBuffer();
         cfg.append("{");
         cfg.append("\"id\":\"").append(this.getId()).append("\",");
         cfg.append("\"type\":\"").append(AdvertTypeConversionService.convert(this.getAdvertType())).append("\",");
         cfg.append("\"resources\":[");
         StringBuffer resources = new StringBuffer();
         for (IBsAdvertResource resource : this.getAdvertResources()) {
             if (screen.equals(resource.getScreen())) {
                 resources.append(resource.getConfig()).append(",");
             }
         }
         String r = resources.toString();
         if (StringUtils.isNotEmpty(r)) {
             cfg.append(r.substring(0, r.length() - 1));
         }
         cfg.append("]}");
         return cfg.toString();
    }
}
