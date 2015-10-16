package com.yihuacomputer.fish.monitor.entity.report;

import java.util.ArrayList;
import java.util.List;


import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;

/**
 * 数据加工单元.
 * @author yantao
 *
 */
public class WorkUnit implements IWorkUnit{

	private List<MonitorUser> monitorUserList = new ArrayList<MonitorUser>();
	private List<MonitorUser> monitorTransUserList = new ArrayList<MonitorUser>();
	private List<MonitorUser> monitorProcessUserList = new ArrayList<MonitorUser>();
	private List<MonitorUser> monitorCounterFeitMoneyUserList = new ArrayList<MonitorUser>();
	private List<MonitorUser> monitorRetaincardUserList = new ArrayList<MonitorUser>();
	private List<MonitorUser> monitorClassifyUserList = new ArrayList<MonitorUser>();

	/**
	 * 该数据单元设备数
	 * @return
	 */
	public int getTotalDevice(){
		return -1;
	}

	/**
	 * 该数据单元在线设备数
	 * @return
	 */
	public int getTotalOnline(){
		return -1;
	}

	/**
	 * 该数据单元设备列表
	 * @return
	 */
	public List<String> listDevice(){
		return null;
	}

	/**
	 * 判断设备是否是需要加工.
	 * @param deviceId
	 * @return 判断结果
	 */
	public boolean hasDevice(String deviceId){
		return true;
	}

	public PageResult<IDeviceReport> page(int offset, int limit){
		return null;
	}

	public PageResult<IDeviceReport> page(int offset, int limit, IFilter filter){
		return null;
	}

	/**
	 * 将用户与数据加工单元绑定
	 * @param user
	 */
	@SuppressWarnings("incomplete-switch")
	public void link(IMonitorUser user,MonitorUserType type){
		switch (type) {
		case Status: {
			if(!this.monitorUserList.contains(user)){
				this.monitorUserList.add((MonitorUser)user);
			}
			break;
		}
		case Mod:{
			if (!this.monitorClassifyUserList.contains(user)) {
				this.monitorClassifyUserList.add((MonitorUser) user);
			}
			break;
		}
		case Trans: {
			if(!this.monitorTransUserList.contains(user)){
				this.monitorTransUserList.add((MonitorUser)user);
			}
			break;
		}
		case Process: {
			if(!this.monitorProcessUserList.contains(user)){
				this.monitorProcessUserList.add((MonitorUser)user);
			}
			break;
		}
		case CounterFeitMoney:{
			if(!this.monitorCounterFeitMoneyUserList.contains(user)){
				this.monitorCounterFeitMoneyUserList.add((MonitorUser)user);
			}
			break;
		}
		case Retaincard: {
			if (!this.monitorRetaincardUserList.contains(user)) {
				this.monitorRetaincardUserList.add((MonitorUser) user);
			}
		}
		}
	}

	/**
	 * 解除用户与数据加工单元的绑定关系
	 * @param user
	 */
	@SuppressWarnings("incomplete-switch")
	public void unlink(IMonitorUser user,MonitorUserType type){
		switch (type) {
		case Status:
			this.monitorUserList.remove(user);
			break;
		case Mod:
			this.monitorClassifyUserList.remove(user);
			break;
		case Trans:
			this.monitorTransUserList.remove(user);
			break;
		case Process:
			this.monitorProcessUserList.remove(user);
			break;
		case Retaincard:
			this.monitorRetaincardUserList.remove(user);
			break;
		case CounterFeitMoney:
			this.monitorCounterFeitMoneyUserList.remove(user);
		case All: {
			this.monitorUserList.remove(user);
			this.monitorClassifyUserList.remove( user);
			this.monitorTransUserList.remove(user);
			this.monitorProcessUserList.remove(user);
			this.monitorRetaincardUserList.remove(user);
			this.monitorCounterFeitMoneyUserList.remove(user);
			break;
		}
		}
	}

	/**
	 * 列出该数据单元的绑定的用户
	 * @return
	 */
	public List<String> listUser(){
		return null;
	}
//	private MessageSource messageSourceEnum;
//    
//    public MessageSource getMessageSourceEnum() {
//		return messageSourceEnum;
//	}
//
//	public void setMessageSourceEnum(MessageSource messageSourceEnum) {
//		this.messageSourceEnum = messageSourceEnum;
//	}
	/**
	 * 加工状态数据
	 * @param deviceId
	 */
	public void fireMonitorUser(String deviceId,IDeviceReport deviceReport){
		for(int i=this.monitorUserList.size()-1;i>=0;i--){
			MonitorUser user = this.monitorUserList.get(i);
			user.statusFilter(deviceId, deviceReport);
		}
		for(int i = this.monitorClassifyUserList.size() - 1; i >= 0; i--){
			MonitorUser user = this.monitorClassifyUserList.get(i);
			user.modFilter(deviceId, deviceReport);
		}
	}
	/**
	 * 加工交易数据
	 * @param deviceId
	 */
	public void fireTransationUser(String deviceId,IDeviceReport deviceReport){
		for(int i=this.monitorTransUserList.size()-1;i>=0;i--){
			MonitorUser user = this.monitorTransUserList.get(i);
			user.transationFilter(deviceId, deviceReport);
		}
	}

	/**
	 * 加工白名单进程数据
	 * @param deviceId
	 */

	public void fireProcessUser(String deviceId,IDeviceReport deviceReport){
		for(int i = this.monitorProcessUserList.size() - 1 ; i>=0;i--){
			MonitorUser user = this.monitorProcessUserList.get(i);
			user.processFilter(deviceId, deviceReport);
		}
	}

	@Override
	public void fireBootSign(String deviceId, IDeviceReport deviceReport) {
		for(int i=this.monitorUserList.size()-1;i>=0;i--){
			MonitorUser user = this.monitorUserList.get(i);
			user.deviceSign(deviceId, deviceReport);
		}
	}

	@Override
	public void fireCounterFeitMoneyUser(String deviceId,IDeviceReport deviceReport) {
		for(int i=this.monitorCounterFeitMoneyUserList.size()-1;i>=0;i--){
			MonitorUser user = this.monitorCounterFeitMoneyUserList.get(i);
			user.counterFeitMoneyFilter(deviceId, deviceReport);
		}

	}
	@Override
	public void fireRetaincardUser(String deviceId, IDeviceReport deviceReport) {
		for (int i = this.monitorRetaincardUserList.size() - 1; i >= 0; i--) {
			MonitorUser user = this.monitorRetaincardUserList.get(i);
			user.retaincardFilter(deviceId, deviceReport);
		}
	}
}
