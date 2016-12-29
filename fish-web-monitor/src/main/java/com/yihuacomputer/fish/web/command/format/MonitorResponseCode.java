package com.yihuacomputer.fish.web.command.format;

/**
 * @author YiHua
 *
 */
public class MonitorResponseCode {
    /**
     * 成功
     */
    public static final String SUCCEED = "00";

    /**
     * 失败
     */
    public static final String FAILURE = "01";

    /**
     * 设备不存在
     */
    public static final String DEVICE_NOT_EXIST = "02";

    /**
     * 设备未注册
     */
    public static final String DEVICE_NOT_REGISTERED = "03";

    /**
     * 设备未签到
     */
    public static final String DEVICE_NOT_SIGN = "04";

    /**
     * 设备状态异常
     */
    public static final String DEVICE_STATUS_ABNORMAL = "04";
}
