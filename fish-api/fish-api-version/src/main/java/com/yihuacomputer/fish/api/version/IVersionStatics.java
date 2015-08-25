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
    void setVersion(IVersion version);
    IVersion getVersion();
    long getDeviceTotal();
    long getMayBeDownTotal();
    long getDownedTotal();
    long getSuccessTotal();
    long getFailTotal();
    long getUnDownTotal();
}
