package com.yihuacomputer.fish.web.report.form;

/**
 * @author YiHua
 *
 */
public enum ReportTitle {
	/**
	 * 钞箱余额报表
	 */
    DeviceBox("ReportTitle.DeviceBox"),
    /**
     * 系统硬件配置报表
     */
    DeviceHardware("ReportTitle.DeviceHardware"),
    /**
     * 设备品牌统计报表
     */
    DeviceTypeCount("ReportTitle.DeviceTypeCount"),
    /**
     * 设备运行情况报表
     */
    DeviceUseCount("ReportTitle.DeviceUseCount"),
    /**
     * 吞卡明细报表
     */
    RetainCard("ReportTitle.RetainCard"),
    /**
     * 吞卡统计报表
     */
    RetainCardCount("ReportTitle.RetainCardCount"),
    /**
     * 交易统计报表
     */
    TransactionCount("ReportTitle.TransactionCount"),
    /**
     * 交易结果统计报表
     */
    TransactionResultCount("ReportTitle.TransactionResultCount"),
    /**
     * 加钞情况报表
     */
    CashIn("ReportTitle.CashIn"),
    /**
     * 清机情况报表
     */
    Settlement("ReportTitle.Settlement"),
    /**
     * 设备明细报表
     */
    Device("ReportTitle.Device"),
    /**
     * 异常交易分析报表
     */
    UncommonTrans("ReportTitle.UncommonTrans"),
    /**
     * 定时任务统计表
     */
    BatchErrorMsg("ReportTitle.BatchError");

    public String getText() {
        return text;
    }

    private String text;

    private ReportTitle(String text) {
        this.text = text;
    }
}
