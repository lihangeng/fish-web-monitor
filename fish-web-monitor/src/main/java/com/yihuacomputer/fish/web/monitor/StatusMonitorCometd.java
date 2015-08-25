package com.yihuacomputer.fish.web.monitor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.filter.BoxStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.NetStatusFilter;

/**
 * 设备状态监控服务端订阅 监控页面上送状态条件，筛选条件 当服务有满足条件的信息时，主动推向订阅的客户端
 *
 * @author YiHua
 * @since 2012/1/17
 * */

@Component
@org.cometd.annotation.Service("StatusCometdService")
public class StatusMonitorCometd {

	public static final String JOIN_CHANNEL = "/service/status/join";

	@Autowired
	private IMonitorService monitorService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IFilterService filterService;

	/**
	 * 开始监控，将监控条件保存
	 * */

	@org.cometd.annotation.Listener(JOIN_CHANNEL)
	public void join(ServerSession remote, ServerMessage.Mutable message) {

		Object data = message.getData();
		String userId = remote.getId();
		if (data instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) data;

			IMonitorUser monitorUser = this.monitorService.makeMonitorUser(userId);
			monitorUser.setUserSession(remote);

			String filterUserId = map.get("filterUserId").toString();
			IStatusFilter statuFilterInDB = filterService.loadStatusFilter(filterUserId);
			if (statuFilterInDB == null) {
				statuFilterInDB = filterService.makeAndSaveStatusFilter(filterUserId);
				statuFilterInDB.getRunStatusFilter().setAll(true);
				statuFilterInDB.getBoxStatusFilter().setAll(true);
				statuFilterInDB.getModStatusFilter().setAll(true);
				statuFilterInDB.getNetStatusFilter().setAll(true);
				if (map.get("orgId") != null) {
					statuFilterInDB.setOrgId(map.get("orgId").toString());
				}

				filterService.updateStatusFilter(statuFilterInDB);
			}

			// 运行状态
			IRunStatusFilter runFilter = monitorUser.getStatusFilter().getRunStatusFilter();
			runFilter.setInitial(statuFilterInDB.getRunStatusFilter().isInitial());
			runFilter.setHealth(statuFilterInDB.getRunStatusFilter().isHealth());
			runFilter.setHalf(statuFilterInDB.getRunStatusFilter().isHalf());
			runFilter.setCustomer(statuFilterInDB.getRunStatusFilter().isCustomer());
			runFilter.setShutdown(statuFilterInDB.getRunStatusFilter().isShutdown());
			runFilter.setReBoot(statuFilterInDB.getRunStatusFilter().isReBoot());
			runFilter.setVdm(statuFilterInDB.getRunStatusFilter().isVdm());
			runFilter.setAtmpStop(statuFilterInDB.getRunStatusFilter().isAtmpStop());
			runFilter.setMaintain(statuFilterInDB.getRunStatusFilter().isMaintain());
			runFilter.setStopManMade(statuFilterInDB.getRunStatusFilter().isStopManMade());
			runFilter.setStopMod(statuFilterInDB.getRunStatusFilter().isStopMod());
			runFilter.setUnknow(statuFilterInDB.getRunStatusFilter().isUnknow());
			runFilter.setStopUnCashIn(statuFilterInDB.getRunStatusFilter().isStopUnCashIn());
			runFilter.setStop(statuFilterInDB.getRunStatusFilter().isStop());
			runFilter.setAll(statuFilterInDB.getRunStatusFilter().isAll());

			monitorUser.getStatusFilter().setRunStattusFilter(runFilter);

			// 模块状态
			IModStatusFilter modFilter = new ModStatusFilter();
			modFilter.setHealth(statuFilterInDB.getModStatusFilter().isHealth());
			modFilter.setWarning(statuFilterInDB.getModStatusFilter().isWarning());
			modFilter.setFatal(statuFilterInDB.getModStatusFilter().isFatal());
			modFilter.setUnknown(statuFilterInDB.getModStatusFilter().isUnknown());
			modFilter.setNodevice(statuFilterInDB.getModStatusFilter().isNodevice());
			modFilter.setAll(statuFilterInDB.getModStatusFilter().isAll());
			monitorUser.getStatusFilter().setModStatusFilter(modFilter);

			// 钞箱状态
			IBoxStatusFilter boxFilter = new BoxStatusFilter();

			boxFilter.setEmpty(statuFilterInDB.getBoxStatusFilter().isEmpty());
			boxFilter.setFull(statuFilterInDB.getBoxStatusFilter().isFull());
			boxFilter.setLow(statuFilterInDB.getBoxStatusFilter().isLow());
			boxFilter.setHigh(statuFilterInDB.getBoxStatusFilter().isHigh());
			boxFilter.setFatal(statuFilterInDB.getBoxStatusFilter().isFatal());
			boxFilter.setUnknown(statuFilterInDB.getBoxStatusFilter().isUnknown());
			boxFilter.setHealthy(statuFilterInDB.getBoxStatusFilter().isHealthy());
			boxFilter.setAll(statuFilterInDB.getBoxStatusFilter().isAll());
			monitorUser.getStatusFilter().setBoxStatusFilter(boxFilter);

			// 网络状态
			INetStatusFilter netFilter = new NetStatusFilter();
			netFilter.setHealth(statuFilterInDB.getNetStatusFilter().isHealth());
			netFilter.setWarning(statuFilterInDB.getNetStatusFilter().isWarning());
			netFilter.setFatal(statuFilterInDB.getNetStatusFilter().isFatal());
			netFilter.setUnknown(statuFilterInDB.getNetStatusFilter().isUnknown());
			netFilter.setAll(statuFilterInDB.getNetStatusFilter().isAll());
			monitorUser.getStatusFilter().setNetStatusFilter(netFilter);

			// 过滤条件
			monitorUser.getStatusFilter().setAwayFlag(statuFilterInDB.getAwayFlag());
			monitorUser.getStatusFilter().setDevType(statuFilterInDB.getDevType());
			monitorUser.getStatusFilter().setDevVendor(statuFilterInDB.getDevVendor());
			monitorUser.getStatusFilter().setWorkType(statuFilterInDB.getWorkType());

			monitorUser.getStatusFilter().setUserId(statuFilterInDB.getUserId());

			if (map.get("orgId") != null) {
				String orgId = String.valueOf(map.get("orgId"));
				monitorUser.getStatusFilter().setOrgId(orgId);

				List<Long> orgList = orgService.listSubOrgId(orgId);
				monitorUser.getStatusFilter().setSubOrg(orgList);
			}

			Object terminalId = map.get("deviceCode");
		 	if(terminalId!=null){
		 		monitorUser.getStatusFilter().setTerminalId(String.valueOf(terminalId));
		 	}else{
		 		monitorUser.getStatusFilter().setTerminalId(null);
		 	}

			// 设备列表
			String devList = String.valueOf(map.get("devices"));

			String[] devces = devList.split("/");
			monitorUser.getStatusFilter().setDeviceList(Arrays.asList(devces));

			if (map.get("number") != null) {
				monitorUser.getStatusFilter().setLimit(Integer.parseInt(String.valueOf(map.get("number"))));
			}

			this.monitorService.addMonitorUser(monitorUser, MonitorUserType.Status);
		}
	}

}
