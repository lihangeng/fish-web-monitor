/**
 * 
 */
package com.yihuacomputer.fish.web.version.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.VersionCatalog;

/**
 * @author xuxigang
 * 
 */
public class VersionTypeForm {
    private long id;

    private String typeName;

    private String defaultInstallPath;

    private boolean autoDeploy;
    
    private VersionCatalog versionCatalog;

    private String desc;

    private String cashType;

    private String awayFlag;
    
    private List<Long> atmTypes = new ArrayList<Long>();

    public VersionTypeForm() {
    }

	public VersionTypeForm(IVersionType type) {
        this.id = type.getId();
        this.typeName = type.getTypeName();
        this.defaultInstallPath = type.getDefaultInstallPath();
        this.autoDeploy = type.isAutoDeploy();
        this.versionCatalog = type.getVersionCatalog();
        this.desc = type.getDesc();
//        this.cashType = type.getRestrictionValue(RestrictionColumn.CASH_TYPE) == null ? "" : type.getRestrictionValue(RestrictionColumn.CASH_TYPE);
//        this.awayFlag = type.getRestrictionValue(RestrictionColumn.AWAY_FLAG) == null ? "" : type.getRestrictionValue(RestrictionColumn.AWAY_FLAG);
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDefaultInstallPath() {
        return defaultInstallPath;
    }

    public void setDefaultInstallPath(String defaultInstallPath) {
        this.defaultInstallPath = defaultInstallPath;
    }

    public boolean isAutoDeploy() {
        return autoDeploy;
    }

    public void setAutoDeploy(boolean autoDeploy) {
        this.autoDeploy = autoDeploy;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getAwayFlag() {
        return awayFlag;
    }

    public void setAwayFlag(String awayFlag) {
        this.awayFlag = awayFlag;
    }

	public VersionCatalog getVersionCatalog() {
		return versionCatalog;
	}

	public void setVersionCatalog(VersionCatalog versionCatalog) {
		this.versionCatalog = versionCatalog;
	}

	public List<Long> getAtmTypes() {
		return atmTypes;
	}

	public void setAtmType(List<Long> atmTypes) {
		this.atmTypes = atmTypes;
	}

}
