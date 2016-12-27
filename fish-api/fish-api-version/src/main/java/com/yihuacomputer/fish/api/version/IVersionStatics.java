/**
 * 
 */
package com.yihuacomputer.fish.api.version;

/**
 * 本本分发信息统计
 * @author xuxigang
 * @since 0.15
 *
 */
public interface IVersionStatics {
    /**
     * @param version
     */
    void setVersion(IVersion version);
    /**
     * @return
     */
    IVersion getVersion();
    /**
     * @return
     */
    long getDeviceTotal();
    /**
     * @return
     */
    long getMayBeDownTotal();
    /**
     * @return
     */
    long getDownedTotal();
    /**
     * @return
     */
    long getSuccessTotal();
    /**
     * @return
     */
    long getFailTotal();
    /**
     * @return
     */
    long getUnDownTotal();
}
