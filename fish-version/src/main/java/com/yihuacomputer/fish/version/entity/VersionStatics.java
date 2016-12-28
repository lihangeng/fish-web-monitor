/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionStatics;

/**
 * 版本统计信息，
 * 对一个版本的分发情况进行统计
 * @author xuxigang
 * @since 0.15
 */
public class VersionStatics implements IVersionStatics {

    private IVersion version;

    private long deviceTotal;

    private long successTotal;

    private long failTotal;

    private long mayBeDownTotal;

    /**
     * @param version
     */
    public VersionStatics(IVersion version) {
        this.version = version;
    }

    @Override
    public void setVersion(IVersion version) {
        this.version = version;
    }

    @Override
    public IVersion getVersion() {
        return this.version;
    }

    @Override
    public long getDeviceTotal() {
        return this.deviceTotal;
    }

    @Override
    public long getMayBeDownTotal() {
        return this.mayBeDownTotal;
    }

    @Override
    public long getDownedTotal() {
        return this.successTotal + this.failTotal;
    }

    @Override
    public long getSuccessTotal() {
        return this.successTotal;
    }

    @Override
    public long getFailTotal() {
        return this.failTotal;
    }

    public void setDeviceTotal(long deviceTotal) {
        this.deviceTotal = deviceTotal;
    }

    public void setSuccessTotal(long successTotal) {
        this.successTotal = successTotal;
    }

    public void setFailTotal(long failTotal) {
        this.failTotal = failTotal;
    }

    public void setMayBeDownTotal(long mayBeDownTotal) {
        this.mayBeDownTotal = mayBeDownTotal;
    }

    @Override
    public long getUnDownTotal() {
       return this.mayBeDownTotal - this.getDownedTotal();
    }

}
