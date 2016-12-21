package com.yihuacomputer.fish.monitor.entity.report;

import org.cometd.bayeux.server.ServerSession;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.IMonitorListener;
import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IProcessFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;

public class MonitorUser implements IMonitorUser {


	private String userId;
	private String userName;
	private IStatusFilter statusFilter;
	private ITransationFilter transFilter;
	private IProcessFilter processFilter;
	private ServerSession userSession;
	private IMonitorService monitorService;
	private IClassifyModStatusFilter modStatusFilter;

	private IRetaincardFilter retaincardFilter;

	private ITransationFilter counterFeitMoneyFilter;

	public void setMonitorService(IMonitorService monitorService){
		this.monitorService = monitorService;
	}

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public IWorkUnit getWorkUnit() {
		return null;
	}

	@Override
	public PageResult<IDeviceReport> restart(int offset, int limit,
			IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IFilter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<IDeviceReport> getResult() {
		// TODO Auto-generated method stub
		return null;
	}


	public void modFilter(String deviceId, IDeviceReport deviceReport,MessageSource messageSourceRef){
		if (this.userSession.isHandshook() && this.userSession.isConnected()) {
			IMonitorListener monitorListener = this.monitorService.getMonitorListener();
			IClassifyReport classifyReport = modStatusFilter.filterMod(deviceReport,messageSourceRef);
			if(!classifyReport.getMethod().equals(ReportMedthod.BEFILTERED)||!classifyReport.getBoxMethod().equals(ReportMedthod.BEFILTERED)||
					!classifyReport.getNetMethod().equals(ReportMedthod.BEFILTERED)){
				//不为删除项，则要生成ID;
				if(!classifyReport.getMethod().equals(ReportMedthod.DELETE)&&!classifyReport.getBoxMethod().equals(ReportMedthod.DELETE)
						&&!classifyReport.getNetMethod().equals(ReportMedthod.DELETE)){
					if(this.userSession.getAttribute("counter")==null){
						int modSize = modStatusFilter.getModDeviceList().size();
						int netSize = modStatusFilter.getNetDeviceList().size();
						int boxSize = modStatusFilter.getBoxDeviceList().size();
						//作为最大的ID插入
						int max = modSize+netSize+boxSize;
						classifyReport.setId(++max);
						this.userSession.setAttribute("counter", max);
					}
					else{
						int max = Integer.parseInt(String.valueOf(this.userSession.getAttribute("counter")));
						classifyReport.setId(++max);
						this.userSession.setAttribute("counter", max);
					}
				}
				monitorListener.reportClassifyMonitor(this.userSession,classifyReport);
			}
		}
		else {
			this.monitorService.removeMonitorUser(this.userId, MonitorUserType.Mod);
		}
	}

	/**
	 * 过滤并状态数据
	 * @param deviceId
	 */
	public void statusFilter(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef) {

		if (this.userSession.isHandshook()&&this.userSession.isConnected()) {

			IMonitorListener monitorListener = this.monitorService
					.getMonitorListener();
			monitorListener.setMessageSourceRef(messageSourceRef);
			ReportMedthod reportMedthod = statusFilter.filterStatus(deviceReport);
			switch (reportMedthod) {
				case ADD: {
					statusFilter.addDevice(deviceReport.getDeviceId());
					monitorListener.addDevice(this.userSession, deviceReport);
					break;
				}
				case UPDATE: {
					monitorListener.updateDevice(this.userSession, deviceReport);
					break;
				}
				case DELETE: {
					statusFilter.remoteDevice(deviceReport.getDeviceId());
					monitorListener.removeDevice(this.userSession, deviceReport);
					break;
				}
				case BEFILTERED:{
					if(statusFilter.getDeviceList().contains(deviceId)){
						statusFilter.remoteDevice(deviceReport.getDeviceId());
						monitorListener.removeDevice(this.userSession, deviceReport);
					}
					break;
				}
			}
		} else {
			this.monitorService.removeMonitorUser(this.userId,MonitorUserType.Status);
		}
	}
	/**
	 * 过滤交易数据
	 * @param deviceId
	 */
	public void transationFilter(String deviceId,IDeviceReport deviceReport){
		if(this.userSession.isConnected()){
			if(this.transFilter.filterTransaction(deviceReport.getDevice(),deviceReport.getTransaction())){
				IMonitorListener monitorListener = this.monitorService.getMonitorListener();
				monitorListener.addTransation(this.userSession, deviceReport);
			}
		}else{
			this.monitorService.removeMonitorUser(this.userId,MonitorUserType.Trans);
		}
	}

	/**
	 * 过滤假币疑问币数据
	 * @param deviceId
	 */
	public void counterFeitMoneyFilter(String deviceId,IDeviceReport deviceReport){
		if(this.userSession.isConnected()){
			if(this.transFilter.filterCounterFeitMoney(deviceReport.getDevice(),deviceReport.getForms())){
				IMonitorListener monitorListener = this.monitorService.getMonitorListener();
				monitorListener.addCounterFeitMoney(this.userSession, deviceReport);
			}
		}else{
			this.monitorService.removeMonitorUser(this.userId,MonitorUserType.CounterFeitMoney);
		}
  }

	/**
	 * 过滤吞卡数据
	 *
	 * @param deviceId
	 */
	public void retaincardFilter(String deviceId, IDeviceReport deviceReport) {
		if (this.userSession.isConnected()) {
			if (this.retaincardFilter.filterRetaincard(deviceReport.getDevice(), deviceReport.getRetaincard())) {
				IMonitorListener monitorListener = this.monitorService.getMonitorListener();
				int cardNum = retaincardFilter.getCardRetainNum();
				retaincardFilter.setCardRetainNum(++cardNum);
				monitorListener.addRetaincard(this.userSession, deviceReport,cardNum);
			}
		} else {
			this.monitorService.removeMonitorUser(this.userId, MonitorUserType.Retaincard);
		}
	}

	/**
	 * 过滤白名单进程报警数据
	 * @param deviceId
	 * @param deviceReport
	 */
	public void processFilter(String deviceId,IDeviceReport deviceReport){

		if(this.userSession.isConnected()){
			if(this.processFilter.filterProcess(deviceReport.getDevice().getOrganization().getGuid())){
				IMonitorListener monitorListener = this.monitorService.getMonitorListener();
				monitorListener.addProcess(this.userSession, deviceReport);
			}
		}else{
			this.monitorService.removeMonitorUser(this.userId,MonitorUserType.Process);
		}
	}

	public void deviceSign(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef){
		if(this.userSession.isConnected()){
			IMonitorListener monitorListener = this.monitorService.getMonitorListener();
			monitorListener.setMessageSourceRef(messageSourceRef);
			monitorListener.deviceSign(userSession, deviceReport);
		}else{
			this.monitorService.removeMonitorUser(this.userId,MonitorUserType.Status);
		}
	}

	@Override
	public ServerSession getUserSession() {
		return this.userSession;
	}

	@Override
	public void setUserSession(ServerSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void setStatusFilter(IStatusFilter statusFilter) {
		this.statusFilter = statusFilter;
	}

	@Override
	public IStatusFilter getStatusFilter() {
		return this.statusFilter;
	}

	@Override
	public ITransationFilter getTransFilter() {
		return transFilter;
	}

	@Override
	public void setTransFilter(ITransationFilter transFilter) {
		this.transFilter = transFilter;
	}

	@Override
	public IProcessFilter getProcessFilter() {
		return processFilter;
	}

	@Override
	public void setProcessFilter(IProcessFilter processFilter) {
		this.processFilter = processFilter;
	}

	@Override
	public ITransationFilter getTransationFilter() {
		return this.transFilter;
	}

	@Override
	public IRetaincardFilter getRetaincardFilter() {
		return this.retaincardFilter;
	}

	@Override
	public void setRetaincardFilter(IRetaincardFilter retaincardFilter) {
		this.retaincardFilter = retaincardFilter;
	}

	@Override
	public IClassifyModStatusFilter getModStatusFilter() {
		return modStatusFilter;
	}

	@Override
	public void setModStatusFilter(IClassifyModStatusFilter modStatusFilter) {
		this.modStatusFilter = modStatusFilter;
	}

	@Override
	public ITransationFilter getCounterFeitMoneyFilter() {
		return counterFeitMoneyFilter;
	}

	@Override
	public void setCounterFeitMoneyFilter(ITransationFilter transFilter) {
		this.transFilter = transFilter;
	}

}
