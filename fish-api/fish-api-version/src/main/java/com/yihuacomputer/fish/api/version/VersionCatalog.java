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
    APP("应用"),
    ADVERT("广告"),
    ATM_PARAM("ATM参数"),
    V_AGENT("监控客户端"),
    OTHER("其它");

    private String text;

    private VersionCatalog(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static boolean isApp(VersionCatalog versionCatalog){
    	if(VersionCatalog.APP.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }

    public static boolean isAdvert(VersionCatalog versionCatalog){
    	if(VersionCatalog.ADVERT.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }

    public static boolean isAgent(VersionCatalog versionCatalog){
    	if(VersionCatalog.V_AGENT.equals(versionCatalog)){
    		return true;
    	}
    	return false;
    }
}
