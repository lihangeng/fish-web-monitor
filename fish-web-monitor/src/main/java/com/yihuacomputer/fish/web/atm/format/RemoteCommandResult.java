package com.yihuacomputer.fish.web.atm.format;

/**
 * @author YiHua
 *
 */
public class RemoteCommandResult {
    private long id;

    /**
     * 00成功,01失败
     */
    private String appRet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppRet() {
        return appRet;
    }

    public void setAppRet(String appRet) {
        this.appRet = appRet;
    }
}
