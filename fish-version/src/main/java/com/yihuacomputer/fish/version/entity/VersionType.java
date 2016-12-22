/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

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

import com.yihuacomputer.fish.api.version.IVersionType;
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

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean isAutoDeploy() {
        return autoDeploy;
    }

    @Override
    public void setAutoDeploy(boolean autoDeploy) {
        this.autoDeploy = autoDeploy;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getDefaultInstallPath() {
        return defaultInstallPath;
    }

    @Override
    public void setDefaultInstallPath(String defaultInstallPath) {
        this.defaultInstallPath = defaultInstallPath;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean isDisplay() {
        return display;
    }

    @Override
    public void setDisplay(boolean system) {
        this.display = system;
    }

    @Override
    public boolean isSystem() {
        return system;
    }

    @Override
    public void setSystem(boolean system) {
        this.system = system;
    }

    @Override
    public boolean isCustomVersion() {
        return customVersion;
    }

    @Override
    public void setCustomVersion(boolean customVersion) {
        this.customVersion = customVersion;
    }

    @Override
    public VersionCatalog getVersionCatalog() {
        return versionCatalog;
    }

    @Override
    public void setVersionCatalog(VersionCatalog versionCatalog) {
        this.versionCatalog = versionCatalog;
    }

}
