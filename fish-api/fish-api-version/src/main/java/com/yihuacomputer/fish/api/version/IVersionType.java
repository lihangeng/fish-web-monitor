package com.yihuacomputer.fish.api.version;

import java.util.List;

/**
 * 版本类型（软件名）
 * 
 * 用于给不同的版本进行分类
 * 
 * @author xuxigang
 * 
 */
public interface IVersionType {
    public long getId();

    /**
     * 设置版本类型名称 不能重复,不区分大小写
     * 
     * @param typeName
     */
    public void setTypeName(String typeName);

    public String getTypeName();

    /**
     * 需要重启完成升级
     * 
     * @param autoDeploy
     */
    public void setAutoDeploy(boolean autoDeploy);

    public boolean isAutoDeploy();

    /**
     * 是否是系统内置的软件分类
     * 
     * @return
     */
    public boolean isSystem();

    public void setSystem(boolean system);

    /**
     * 是否在版本管理页面显示
     * 
     * @return
     */
    public boolean isDisplay();

    public void setDisplay(boolean display);

    /**
     * 版本号是否由页面手工输入 true:手工输入 false，版本号有系统自动生成
     * 
     * @return
     */
    public boolean isCustomVersion();

    public void setCustomVersion(boolean customVersion);

    /**
     * 默认的安装路径
     * 
     * @param defaultInstallPath
     */
    public void setDefaultInstallPath(String defaultInstallPath);

    public String getDefaultInstallPath();

    /*  *//**
     * 设置软件版本号除了里程碑之外的连接符
     * 
     * @param link1
     */
    /*
     * public void setLink1(String link1);
     * 
     * public String getLink1();
     *//**
     * 设置里程碑之前的连接符
     * 
     * @param link2
     */
    /*
     * public void setLink2(String link2);
     * 
     * public String getLink2();
     *//**
     * 设置软件版本号的格式
     * 
     * @param versionFormat
     */
    /*
     * public void setVersionFormat(String versionFormat);
     * 
     * public String getVersionFormat();
     */

    /**
     * 设置备注
     * 
     * @param desc
     */
    public void setDesc(String desc);

    public String getDesc();

    /**
     * 列出限制条件
     * 
     * @since 0.15
     * @return
     */
    public List<IVersionTypeRestriction> listVersionTypeRestrictions();

    /**
     * 增加一个限制条件
     * 
     * @since 0.15
     */
    public void addVersionTypeRestriction(RestrictionColumn rc,String value);

    /**
     * 删除一个限制条件
     * 
     * @since 0.15
     */
    public void removeVersionTypeRestriction(RestrictionColumn rc,String value);
    /**
     * 得到限制的值，没有返回null
     * @param rc
     * @return
     */
    public String getRestrictionValue(RestrictionColumn rc);
    /**
     * 软件分类
     * @param versionCatalog
     * @since 0.20
     */
    public void setVersionCatalog(VersionCatalog versionCatalog);
    /**
     * 获得软件分类
     * @return
     */
    public VersionCatalog getVersionCatalog();

}
