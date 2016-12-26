package com.yihuacomputer.fish.api.monitor.software;

/**
 * 设备参数
 * 
 * @author YiHua
 * 
 */
public interface IDeviceParam {

    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 获取设备号
     * 
     * @return
     */
    public String getTerminalId();

    /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * 获取参数
     * 
     * @return
     */
    public String getKey();

    /**
     * @param key
     */
    public void setKey(String key);

    /**
     * 获取值
     * 
     * @return
     */
    public String getValue();

    /**
     * @param value
     */
    public void setValue(String value);

    /**
     * 默认值
     * 
     * @return
     */
    public String getDefaultValue();

    /**
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue);

    /**
     * 模块
     * 
     * @return
     */
    public String getModule();

    /**
     * @param module
     */
    public void setModule(String module);

    /**
     * 名称
     * 
     * @return
     */
    public String getLabel();

    /**
     * @param label
     */
    public void setLabel(String label);
}
