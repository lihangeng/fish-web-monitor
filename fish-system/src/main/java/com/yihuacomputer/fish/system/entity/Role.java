package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.permission.service.api.IDomainRoleService;

/**
 *
 * @author wangchao
 *
 */
@Entity
@Table(name = "SM_ROLE")
public class Role implements IRole,Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7792137637891252883L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_ROLE")
    @SequenceGenerator(name = "SEQ_SM_ROLE", sequenceName = "SEQ_SM_ROLE")
    @Column(name = "PM_ROLE_ID")
    private long id;

    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ROLE_TYPE", length = 15)
    private RoleType type;

    @Column(name = "INHERIT")
    private boolean inherit;

    @Column(name = "RELATION")
    private boolean autoRelation;

    @Column(name = "IS_SYSTEM")
    private boolean system;

    // @Column(name="ORGANIZATION_CODE",nullable = true,length = 255)
    // private String organizationCode;
    /**
     * 描述
     */
    @Column(name = "NOTE", nullable = true, length = 300)
    private String description;

    // public String getOrganizationCode() {
    // return organizationCode;
    // }

    @Transient
    private IDomainRoleService service;

    public Role() {
    }

    public Role(IDomainRoleService service) {
        this.service = service;
    }

    public Role(String name) {
        this.name = name;
        this.autoRelation = false;
        this.inherit = false;
        this.system = false;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public List<IPermission> listPermission() {
        return service.listPermission(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public IDomainRoleService getService() {
        return service;
    }

    public void setService(IDomainRoleService service) {
        this.service = service;
    }

    // public IOrganization getOrganization() {
    // if(organizationCode == null) {
    // throw new
    // AppException(String.format("Role[%s]'s organizationCode is null",
    // getName()));
    // }
    // return service.lookupOrganization(organizationCode);
    // }

    // public void setOrganization(IOrganization organization) {
    // this.organizationCode = organization.getCode();
    // }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public boolean getAutoRelation() {
        return autoRelation;
    }

    @Override
    public boolean getInherit() {
        return inherit;
    }

    @Override
    public void setAutoRelation(boolean auto) {
        this.autoRelation = auto;
    }

    @Override
    public void setInherit(boolean inherit) {
        this.inherit = inherit;
    }

    @Override
    public int compareTo(IRole o) {
        // int result = this.getOrganization().compareTo(o.getOrganization());
        // if(result == 0) {
        // return this.getName().compareTo(o.getName());
        // }
        // return result;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj==null){
    		return false;
    	}
    	if(obj instanceof Role){
    		Role role = (Role) obj;
    	    return this.getId() == role.getId() ? true : false;
    	}
    	return false;
       
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(String.valueOf(this.getId()));
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String descrption) {
        this.description = descrption;

    }

    @Override
    public boolean isSystem() {
        return this.system;
    }

    @Override
    public void setSystem(boolean system) {
        this.system = system;
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCode(String code) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getUniqueMember() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUniqueMember(String uniqueMember) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getGroupType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGroupType(String groupType) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getGroupMember() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGroupMember(String groupMember) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getGroupRule() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGroupRule(String groupRule) {
        // TODO Auto-generated method stub

    }

}
