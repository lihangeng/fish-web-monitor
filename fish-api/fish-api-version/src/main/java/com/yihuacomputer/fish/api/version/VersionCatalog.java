/**
 *
 */
package com.yihuacomputer.fish.api.version;

/**
 *
 * 软件分类
 *
 * @author xuxigang
 * @since 0.20
 */
public enum VersionCatalog {
    /**
     * 应用
     */
    APP("VersionCatalog.APP"),
    /**
     * 广告
     */
    ADVERT("VersionCatalog.ADVERT"),
    /**
     * ATM参数
     */
    ATM_PARAM("VersionCatalog.ATM_PARAM"),
    /**
     * 监控客户端
     */
    V_AGENT("VersionCatalog.V_AGENT"),
    /**
     * 其它
     */
    OTHER("VersionCatalog.OTHER");

    private String text;

    private VersionCatalog(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /**
     * @param versionCatalog
     * @return
     */
    public static boolean isApp(VersionCatalog versionCatalog){
    	if(VersionCatalog.APP.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }

    /**
     * @param versionCatalog
     * @return
     */
    public static boolean isAdvert(VersionCatalog versionCatalog){
    	if(VersionCatalog.ADVERT.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }

    /**
     * @param versionCatalog
     * @return
     */
    public static boolean isAgent(VersionCatalog versionCatalog){
    	if(VersionCatalog.V_AGENT.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }
}
