package com.yihuacomputer.fish.api.version;


/**
 * 版本类型（软件名）
 * 
 * 用于给不同的版本进行分类
 * 
 * @author xuxigang
 * 
 */
public interface IVersionType {
    /**
     * @return
     */
    public long getId();

    /**
     * 设置版本类型名称 不能重复,不区分大小写
     * 
     * @param typeName
     */
    public void setTypeName(String typeName);

    /**
     * @return
     */
    public String getTypeName();

    /**
     * 需要重启完成升级
     * 
     * @param autoDeploy
     */
    public void setAutoDeploy(boolean autoDeploy);

    /**
     * @return
     */
    public boolean isAutoDeploy();

    /**
     * 是否是系统内置的软件分类
     * 
     * @return
     */
    public boolean isSystem();

    /**
     * @param system
     */
    public void setSystem(boolean system);

    /**
     * 是否在版本管理页面显示
     * 
     * @return
     */
    public boolean isDisplay();

    /**
     * @param display
     */
    public void setDisplay(boolean display);

    /**
     * 版本号是否由页面手工输入 true:手工输入 false，版本号有系统自动生成
     * 
     * @return
     */
    public boolean isCustomVersion();

    /**
     * @param customVersion
     */
    public void setCustomVersion(boolean customVersion);

    /**
     * 默认的安装路径
     * 
     * @param defaultInstallPath
     */
    public void setDefaultInstallPath(String defaultInstallPath);

    /**
     * @return
     */
    public String getDefaultInstallPath();

    /**
     * 设置备注
     * 
     * @param desc
     */
    public void setDesc(String desc);

    /**
     * @return
     */
    public String getDesc();

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
