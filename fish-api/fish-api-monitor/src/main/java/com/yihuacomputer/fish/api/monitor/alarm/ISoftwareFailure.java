package com.yihuacomputer.fish.api.monitor.alarm;

/**
 * 设备软件故障信息
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-29 上午11:25:15
 * @version 设备软件故障报文
 */
public interface ISoftwareFailure
{
    /**
     * 服务端URL
     * 
     * @return String
     */
    public String getUrl();

    /**
     * 设备号
     * 
     * @return String
     */
    public String getTermId();

    /**
     * 软件故障类型<BR>
     * ATMC、SP
     * 
     * @return String
     */
    public String getSoftCatclog();

    /**
     * 故障代码
     * 
     * @return String
     */
    public String getCode();

    /**
     * 响应码<BR>
     * 参见《附录：监控响应码》
     * 
     * @return String
     */
    public String getRet();
}
