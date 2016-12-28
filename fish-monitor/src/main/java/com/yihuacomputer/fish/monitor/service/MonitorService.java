package com.yihuacomputer.fish.monitor.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.IMonitorListener;
import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.monitor.entity.report.MonitorUser;
import com.yihuacomputer.fish.monitor.entity.report.WorkUnit;

/**
 * @author YiHua
 *
 */
@Service
public class MonitorService implements IMonitorService {

	private Map<String, MonitorUser> monitorUserMap = new HashMap<String, MonitorUser>();

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IFilterService filterService;

	private IMonitorListener monitorListener;

	private IWorkUnit workUnit;

	@Override
	public void setMonitorListener(IMonitorListener listener) {
		this.monitorListener = listener;
	}

	@Override
	public IMonitorListener getMonitorListener() {
		return this.monitorListener;
	}

	@Override
	@PostConstruct
	public void init() {
		MonitorListener monitorListener = new MonitorListener();
		CollectListener collectListener = new CollectListener();

		WorkUnit workUnit = new WorkUnit();
		this.setMonitorListener(monitorListener);
		this.addWorkUnit(workUnit);
		collectListener.setWorkUnit(workUnit);
		collectService.setClollectListener(collectListener);
	}

	@Override
	@PreDestroy
	public void close() {

	}

	@Override
	public IDeviceReport getMonitorReport(String deviceId) {
		IDeviceReport deviceReport = collectService.getDeviceReport(deviceId);
		return deviceReport;
	}

	@Override
	public void addWorkUnit(IWorkUnit workUnit) {
		this.workUnit = workUnit;
	}

	@Override
	public IMonitorUser makeMonitorUser(String userId) {
		IMonitorUser monitorUser = this.getMonitoruser(userId);
		if (monitorUser == null) {
			monitorUser = new MonitorUser();
			monitorUser.setUserId(userId);
			monitorUser.setStatusFilter(filterService.makeStatusFilter());
		}
		return monitorUser;
	}

	@Override
	public void addMonitorUser(IMonitorUser user, MonitorUserType type) {
		MonitorUser monitorUser = (MonitorUser) user;
		monitorUser.setMonitorService(this);
		this.workUnit.link(monitorUser, type);
		monitorUserMap.put(monitorUser.getUserId(), monitorUser);
	}

	@Override
	public IMonitorUser getMonitoruser(String userId) {
		return this.monitorUserMap.get(userId);
	}

	@Override
	public void removeMonitorUser(String userId, MonitorUserType type) {
		MonitorUser user = this.monitorUserMap.get(userId);
		if (user != null) {
			this.monitorUserMap.remove(userId);
			this.workUnit.unlink(user, type);
		}
	}

}
