package com.yihuacomputer.fish.api.monitor.business;

/**
 * 交易主机码
 * @author huxiaobao
 *
 */
public interface IHostRet {
    
    public long getId();
    
    public String getName();
    
    public void setName(String name);
    
    public String getCode();
    
    public void setCode(String name);

}
