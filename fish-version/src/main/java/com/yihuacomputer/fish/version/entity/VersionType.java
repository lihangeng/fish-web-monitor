/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeRestriction;
import com.yihuacomputer.fish.api.version.RestrictionColumn;
import com.yihuacomputer.fish.api.version.VersionCatalog;

/**
 * 软件类型的实体定义
 * 
 * @author xuxigang
 * 
 */
@Entity
@Table(name = "VER_VERSION_TYPE")
public class VersionType implements IVersionType, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_VERSION_TYPE")
    @SequenceGenerator(name = "SEQ_VER_VERSION_TYPE", sequenceName = "SEQ_VER_VERSION_TYPE")
    @Column(name = "ID")
    private long id;

    @Column(name = "TYPE_NAME", nullable = false, length = 64, unique = true)
    private String typeName;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "AUTO_DEPLOY", columnDefinition = "CHAR", length = 1)
    @Deprecated
    private boolean autoDeploy;

    @Column(name = "DEFAULT_INSTALL_PATH", nullable = true, length = 50)
    private String defaultInstallPath;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "IS_DISPLAY", nullable = false, columnDefinition = "CHAR", length = 1)
    private boolean display;
    
    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "IS_SYSTEM", nullable = false, columnDefinition = "CHAR", length = 1)
    private boolean system;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "CUSTOM_VERSION", nullable = false, columnDefinition = "CHAR", length = 1)
    private boolean customVersion;

    @Column(name = "REMARK", nullable = true, length = 128)
    private String desc;

    @OneToMany(targetEntity = VersionTypeRestriction.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VERSION_TYPE_ID")
    private List<IVersionTypeRestriction> versionTypeRestrictions = new ArrayList<IVersionTypeRestriction>();

    @Enumerated(EnumType.STRING)
    @Column(name = "VERSION_CATALOG")
    private VersionCatalog versionCatalog;

    public VersionType() {
        this.autoDeploy = false;
        this.display = true;
        this.system = false;
        this.customVersion = true;
        this.versionCatalog = VersionCatalog.OTHER;
    }

    public VersionType(String typeName) {
        this();
        this.typeName = typeName;
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

    public boolean isAutoDeploy() {
        return autoDeploy;
    }

    public void setAutoDeploy(boolean autoDeploy) {
        this.autoDeploy = autoDeploy;
    }

    public String getDesc() {
        return desc;
    }

    public String getDefaultInstallPath() {
        return defaultInstallPath;
    }

    public void setDefaultInstallPath(String defaultInstallPath) {
        this.defaultInstallPath = defaultInstallPath;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean system) {
        this.display = system;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean isCustomVersion() {
        return customVersion;
    }

    public void setCustomVersion(boolean customVersion) {
        this.customVersion = customVersion;
    }

    @Override
    public List<IVersionTypeRestriction> listVersionTypeRestrictions() {
        return this.versionTypeRestrictions;
    }

    public void addVersionTypeRestriction(IVersionTypeRestriction versionTypeRestriction) {
        this.versionTypeRestrictions.add(versionTypeRestriction);
    }

    @Override
    public void addVersionTypeRestriction(RestrictionColumn rc, String value) {
        IVersionTypeRestriction vtr = getVersionTypeRestriction(rc);
        if (vtr == null) {
            vtr = new VersionTypeRestriction();
            vtr.setRestrictionColumn(rc);
            vtr.setRestrictionValue(value);
            this.addVersionTypeRestriction(vtr);
        }
        else {
            vtr.setRestrictionValue(value);
        }
    }

    public void removeVersionTypeRestriction(IVersionTypeRestriction versionTypeRestriction) {
        this.versionTypeRestrictions.remove(versionTypeRestriction);
    }

    @Override
    public void removeVersionTypeRestriction(RestrictionColumn rc, String value) {
        IVersionTypeRestriction vtr = getVersionTypeRestriction(rc, value);
        if (vtr != null) {
            this.removeVersionTypeRestriction(vtr);
        }
    }

    private IVersionTypeRestriction getVersionTypeRestriction(RestrictionColumn rc, String value) {
        for (IVersionTypeRestriction vtr : this.versionTypeRestrictions) {
            if (vtr.getRestrictionColumn().equals(rc) && vtr.getRestrictionValue().equalsIgnoreCase(value)) {
                return vtr;
            }
        }
        return null;
    }

    private IVersionTypeRestriction getVersionTypeRestriction(RestrictionColumn rc) {
        for (IVersionTypeRestriction vtr : this.versionTypeRestrictions) {
            if (vtr.getRestrictionColumn().equals(rc)) {
                return vtr;
            }
        }
        return null;
    }

    @Override
    public String getRestrictionValue(RestrictionColumn rc) {
        for (IVersionTypeRestriction vtr : this.versionTypeRestrictions) {
            if (vtr.getRestrictionColumn().equals(rc)) {
                return vtr.getRestrictionValue();
            }
        }
        return null;
    }

    public VersionCatalog getVersionCatalog() {
        return versionCatalog;
    }

    public void setVersionCatalog(VersionCatalog versionCatalog) {
        this.versionCatalog = versionCatalog;
    }

}
