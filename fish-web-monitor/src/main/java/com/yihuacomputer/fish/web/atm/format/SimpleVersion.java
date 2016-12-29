package com.yihuacomputer.fish.web.atm.format;

/**
 * @author YiHua
 *
 */
public class SimpleVersion {
    private String typeName;

    private String versionNo;

    public SimpleVersion() {
    }

    /**
     * @param typeName
     * @param versionNo
     */
    public SimpleVersion(String typeName, String versionNo) {
        this.typeName = typeName;
        this.versionNo = versionNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

}
