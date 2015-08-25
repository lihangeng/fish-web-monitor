package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.cometd.bayeux.server.ServerSession;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.IMonitorListener;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.monitor.entity.report.CounterFeitMoneyReport;
import com.yihuacomputer.fish.monitor.entity.report.ProcessReport;
import com.yihuacomputer.fish.monitor.entity.report.RetaincardReport;
import com.yihuacomputer.fish.monitor.entity.report.StatusReport;
import com.yihuacomputer.fish.monitor.entity.report.TransactionReport;

/**
 * 页面监控事件监听器.
 *
 * @author YiHua
 * @since 2012/2/6
 * @version 1.0
 */

public class MonitorListener implements IMonitorListener{

	private final String STATUS_DATA_CHANNEL = "/service/status/join";

	private final String TRANSACTION_DATA_CHANNEL = "/service/transaction/join";

	private final String PROCESS_DATA_CHANNEL = "/service/schindler/join";

	private final String COUNTER_FEIT_MONEY_DATA_CHANNEL = "/service/counterFeitMoney/join";
	private final String RETAINCARD_DATA_CHANNEL = "/service/retaincard/join";

	private final String CLASSIFY_DATA_CHANNEL = "/service/classifyMod/join";
	/**
	 * 初始化或重置设备列表.
	 * @param userId
	 * @param devices
	 */
	public void restart(ServerSession userSession, PageResult<IDeviceReport> devices){

	}

	/**
	 * 更新设备.
	 * @param userId
	 * @param device
	 */
	public void updateDevice(ServerSession userSession, IDeviceReport device){
		StatusReport statusMonitor = new StatusReport();
		statusMonitor.setStatusReport(device);

		statusMonitor.setMethod(ReportMedthod.UPDATE);
		userSession.deliver(userSession, STATUS_DATA_CHANNEL, JsonUtils.toJson(statusMonitor), null);
	}

	/**
	 * 添加设备.
	 * @param userId
	 * @param device
	 */
	public void addDevice(ServerSession userSession, IDeviceReport device){
		StatusReport statusMonitor = new StatusReport();
		statusMonitor.setStatusReport(device);

		statusMonitor.setMethod(ReportMedthod.ADD);
		userSession.deliver(userSession, STATUS_DATA_CHANNEL, JsonUtils.toJson(statusMonitor), null);
	}


	/**
	 * 设备删除.
	 * @param userId
	 * @param device
	 */
	public void removeDevice(ServerSession userSession, IDeviceReport device){
		StatusReport statusMonitor = new StatusReport();
		statusMonitor.setStatusReport(device);
		statusMonitor.setMethod(ReportMedthod.DELETE);

		userSession.deliver(userSession, STATUS_DATA_CHANNEL, JsonUtils.toJson(statusMonitor), null);
	}

	/**
	 * 增加监控数量.
	 * @param userId
	 * @param total
	 * @return
	 */
	public int updateTotal(ServerSession userSerssion, int total){
		return 0;
	}

	/**
	 * 推送交易数据
	 */
	public void addTransation(ServerSession userSession, IDeviceReport device) {
		TransactionReport report = new TransactionReport();
		report.setTransaction(device.getTransaction());
		userSession.deliver(userSession, TRANSACTION_DATA_CHANNEL, JsonUtils.toJson(report), null);

	}
	/**
	 * 推送非白名单进程数据
	 */
	public void addProcess(ServerSession userSession,IDeviceReport device){
		List<ProcessReport> reportList = new ArrayList<ProcessReport>();
		for(IIllegalProcess process:device.getProcess()){
			ProcessReport processReport = new ProcessReport(process);
			reportList.add(processReport);
		}
		userSession.deliver(userSession, PROCESS_DATA_CHANNEL, JsonUtils.toJson(reportList), null);
	}

	/**
	 * 推送设备签到信息
	 */
	public void deviceSign(ServerSession userSession, IDeviceReport device) {
		StatusReport statusMonitor = new StatusReport();
		statusMonitor.setDeviceSign(device);
		statusMonitor.setMethod(ReportMedthod.UPDATE);
		userSession.deliver(userSession, PROCESS_DATA_CHANNEL, JsonUtils.toJson(statusMonitor), null);
	}
	@Override
	public void addRetaincard(ServerSession userSession, IDeviceReport device,int cardNum) {
		RetaincardReport report = new RetaincardReport();
		report.setId(cardNum);
		report.setRetaincard(device.getRetaincard());
		report.setSubsidiaryorganId(device.getDevice().getOrganization().getGuid());
		report.setSubsidiaryorganName(device.getDevice().getOrganization().getName());

		userSession.deliver(userSession, RETAINCARD_DATA_CHANNEL, JsonUtils.toJson(report), null);
	}

	@Override
	public void reportClassifyMonitor(ServerSession userSession,IClassifyReport classifyReport) {
		userSession.deliver(userSession, CLASSIFY_DATA_CHANNEL, JsonUtils.toJson(classifyReport), null);
	}
	/**
	 * 推送假币疑问币数据
	 */
	@Override
	public void addCounterFeitMoney(ServerSession userSession,IDeviceReport device) {
		List<CounterFeitMoneyReport> reportList = new ArrayList<CounterFeitMoneyReport>();
		for(CounterFeitMoneyForms forms:device.getForms()){
			CounterFeitMoneyReport report = new CounterFeitMoneyReport(forms);
			userSession.deliver(userSession, COUNTER_FEIT_MONEY_DATA_CHANNEL, JsonUtils.toJson(report), null);
			reportList.add(report);
		}
	}
}
