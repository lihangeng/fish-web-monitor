package com.yihuacomputer.fish.web.report.controller;

public enum ReportTitle {
    DeviceBox("钞箱余额报表"),

    DeviceHardware("系统硬件配置报表"),

    DeviceTypeCount("设备品牌统计报表"),

    DeviceUseCount("设备运行情况报表"),

    RetainCard("吞卡明细报表"),

    RetainCardCount("吞卡统计报表"),

    TransactionCount("交易统计报表"),

    TransactionResultCount("交易结果统计报表"),

    CashIn("加钞情况报表"),

    Settlement("清机情况报表"),

    Device("设备明细报表"),

    UncommonTrans("异常交易分析报表");

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    private ReportTitle(String text) {
        this.text = text;
    }
}
