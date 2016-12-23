package com.yihuacomputer.fish.api.monitor.business;

/**
 * 交易主机码
 * @author huxiaobao
 *
 */
public interface IHostRet {
    
    /**
     * @return
     */
    public long getId();
    
    /**
     * @return
     */
    public String getName();
    
    /**
     * @param name
     */
    public void setName(String name);
    
    /**
     * @return
     */
    public String getCode();
    
    /**
     * @param name
     */
    public void setCode(String name);

}
