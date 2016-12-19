package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;

public class OrganizationForm {
    private long id;

    private String guid;

    private String code;

    private String name;
    
    private String displayName;

    private String userGuid;

    private String manager;

    private String parent;

    private String parentId;
    
    private int orgLevel;

    private String node;

    private String address;

    private String zip;

    private String organizationType;

    private String description;

    private String organizationState;

    private long serviceObjectId;

    private String serviceObjectName;

    public OrganizationForm() {
    }

    /**
     * 
     * @param organization
     */
    public OrganizationForm(IOrganization organization) {
    	if(null!=organization){
	        id = Long.valueOf(organization.getGuid());
	        guid = organization.getGuid();
	        code = organization.getCode();
	        name = organization.getName();
	        displayName = reDoDisplayName(organization);
	        address = organization.getAddress();
	        if(organization.getZip()!=null){
	        	zip = organization.getZip().trim();
	        }
	        organizationType = Integer
	                .toString(organization.getOrganizationType() == null ? 1
	                        : organization.getOrganizationType().getId());
	        description = organization.getDescription();
	        organizationState = Integer.toString(organization
	                .getOrganizationState() == null ? 0 : organization
	                .getOrganizationState().getId());
	        if(null!=organization.getOrganizationLevel()){
	        	orgLevel = organization.getOrganizationLevel().getId();
	        }
	        if (organization.getManager() != null) {
	            userGuid = organization.getManager().getGuid();
	            manager = organization.getManager().getName();
	        }
	        if (organization.getParent() != null) {
	            parent = organization.getParent().getName();
	            parentId = organization.getParent().getGuid();
	        }
	        if (organization.getServiceObject() != null) {
	            serviceObjectId = Integer.valueOf(organization.getServiceObject()
	                    .getGuid());
	            serviceObjectName = organization.getServiceObject().getName();
	        }
    	}
    }
    
    
    
    public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
     * 将机构名称按层级进行显示
     * @param organization
     * @return
     */
    private String reDoDisplayName(IOrganization organization){
		String displayName = "";
		if(null==organization){
			return displayName;
		}
		IOrganization organizationValue = organization;
		while(null!=organizationValue.getParent()){
			if("".equals(displayName)){
				displayName = organizationValue.getName();
			}
			else{
				displayName = organizationValue.getName()+"/"+displayName;
			}
			organizationValue = organizationValue.getParent();
		}
		return displayName;
	}
    public void translate(IOrganization organization) {
        organization.setGuid(getGuid());
        organization.setName(getName());
    }

    /**
     * 
     * 方法描述 : 转换
     * 
     * @param resources
     * @return List
     */
    public static List<OrganizationForm> convert(List<IOrganization> resources) {
        List<OrganizationForm> result = new ArrayList<OrganizationForm>();
        for (IOrganization resource : resources) {
            result.add(new OrganizationForm(resource));
        }
        return result;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationState() {
        return organizationState;
    }

    public void setOrganizationState(String organizationState) {
        this.organizationState = organizationState;
    }

    public long getServiceObjectId() {
        return serviceObjectId;
    }

    public void setServiceObjectId(long serviceObjectId) {
        this.serviceObjectId = serviceObjectId;
    }

    public String getServiceObjectName() {
        return serviceObjectName;
    }

    public void setServiceObjectName(String serviceObjectName) {
        this.serviceObjectName = serviceObjectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

}
