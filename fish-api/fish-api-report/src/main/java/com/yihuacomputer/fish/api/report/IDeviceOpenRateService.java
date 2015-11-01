package com.yihuacomputer.fish.api.report;


public interface IDeviceOpenRateService {
    /**
     * 设备日开机率
     * 
     * @param date
     *            format:yyyy-MM-dd
     */
    public void dayOpenRate(String date);

}
