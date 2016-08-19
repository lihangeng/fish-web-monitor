/**
 * 
 */
package com.yihuacomputer.fish.api.version;

import org.apache.commons.lang.StringUtils;


/**
 * 版本号
 * 
 * @author xuxigang
 * 
 */
public class VersionNo{
    /**
     * 主版本号
     */
    private int major = 0;

    /**
     * 次版本号
     */
    private int minor = 0;

    /**
     * 增量版本号
     */
    private int incremental = 0;

    /**
     * 修正版本号
     */
    private int revision = 0;

    /**
     * 里程碑版本，可以看作是一些说明
     */
    private String desc;

    /**
     * 主版本、次版本、增量版本、修正版本之间的连接符
     */
    private String link1 = "\\.";

    /**
     * 里程碑版本之前的连接符
     */
    private String link2 = "-";

    public VersionNo() {
    }

    public VersionNo(String versionNo) {
        int index = versionNo.indexOf(this.link2);
        if (index > 0) {
            this.desc = versionNo.substring(index + 1);
            versionNo = versionNo.substring(0, index - 1);
        }
        String[] parts = versionNo.split(this.link1);
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                this.major = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 1) {
                this.minor = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 2) {
                this.incremental = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 3) {
                this.revision = Integer.parseInt(parts[i]);
                continue;
            }
        }
    }
    
    public VersionNo(String versionNo,String link1 ,String link2){
        int index = versionNo.indexOf(link1);
        if (index > 0) {
            this.desc = versionNo.substring(index + 1);
            versionNo = versionNo.substring(0, index - 1);
        }
        String[] parts = versionNo.split(link2);
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                this.major = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 1) {
                this.minor = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 2) {
                this.incremental = Integer.parseInt(parts[i]);
                continue;
            }
            if (i == 2) {
                this.revision = Integer.parseInt(parts[i]);
                continue;
            }
        } 
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#toString()
     */
    public String toString() {
        StringBuilder version = new StringBuilder();
        version.append(this.major);
        if (this.minor != -1) {
            version.append(this.link1).append(this.major);
        }
        if (this.incremental != -1) {
            version.append(this.link1).append(this.incremental);
        }
        if (this.revision != -1) {
            version.append(this.link1).append(this.revision);
        }
        if (StringUtils.isNotEmpty(this.desc)) {
            version.append(this.link2).append(this.desc);
        }
        return version.toString();
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getMajor()
     */
    public int getMajor() {
        return major;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setMajor(int)
     */
    public void setMajor(int major) {
        this.major = major;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getMinor()
     */
    public int getMinor() {
        return minor;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setMinor(int)
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getIncremental()
     */
    public int getIncremental() {
        return incremental;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setIncremental(int)
     */
    public void setIncremental(int incremental) {
        this.incremental = incremental;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getRevision()
     */
    public int getRevision() {
        return revision;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setRevision(int)
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getDesc()
     */
    public String getDesc() {
        return desc;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setDesc(java.lang.String)
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getLink1()
     */
    public String getLink1() {
        return link1;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setLink1(java.lang.String)
     */
    public void setLink1(String link1) {
        this.link1 = link1;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#getLink2()
     */
    public String getLink2() {
        return link2;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#setLink2(java.lang.String)
     */
    public void setLink2(String link2) {
        this.link2 = link2;
    }

    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionNo#isBiggerThan(com.yihuacomputer.fish.api.version.VersionNo)
     */
    public boolean isBiggerThan(VersionNo other) {
        if (this.major > other.getMajor()) {
            return true;
        }
        if(this.major < other.getMajor()){
            return false;
        }
        if (this.minor > other.getMinor()) {
            return true;
        }
        if (this.minor < other.getMinor()) {
            return false;
        }
        if (this.incremental > other.getIncremental()) {
            return true;
        }
        if (this.incremental < other.getIncremental()) {
            return false;
        }
        if (this.revision > other.getRevision()) {
            return true;
        }
        if (this.revision < other.getRevision()) {
            return false;
        }
        String temp1 = this.desc == null ? "" : this.desc;
        String temp2 = other.getDesc() == null ? "" : other.getDesc();
        if (temp1.compareToIgnoreCase(temp2) > 0) {
            return true;
        }
        return false;
    }

}
