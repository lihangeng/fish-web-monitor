package com.yihuacomputer.fish.web.monitor.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.api.person.IOrganizationService;

@Component
@org.cometd.annotation.Service("ClassifyModStatusCometdService")
public class ClassifyModStatuMonitorCometd {
	public static final String JOIN_CHANNEL = "/service/classifyMod/join";

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

			IClassifyModStatusFilter classifyStatusFilter = filterService.makeClassifyModStatusFilter();

			monitorUser.setModStatusFilter(classifyStatusFilter);


			if (map.get("organizationId") != null) {
				String orgId = String.valueOf(map.get("organizationId"));
				monitorUser.getModStatusFilter().setOrgId(orgId);

				List<Long> orgList = orgService.listSubOrgId(orgId);
				monitorUser.getModStatusFilter().setSubOrg(orgList);
			}

			Object terminalId = map.get("terminalId");
		 	if(terminalId!=null){
		 		monitorUser.getModStatusFilter().setTerminalId(String.valueOf(terminalId));
		 	}else{
		 		monitorUser.getModStatusFilter().setTerminalId(null);
		 	}


//		 	modTerminalIds='',netTerminalIds='';
			// 设备列表
			String boxdevList = String.valueOf(map.get("boxTerminalIds"));
			String[] boxDevces = boxdevList.split(",");
			String moddevList = String.valueOf(map.get("modTerminalIds"));
			String[] modDevces = moddevList.split(",");
			String netdevList = String.valueOf(map.get("netTerminalIds"));
			String[] netDevces = netdevList.split(",");
			monitorUser.getModStatusFilter().setModDeviceList(Arrays.asList(modDevces));
			monitorUser.getModStatusFilter().setNetDeviceList(Arrays.asList(netDevces));
			monitorUser.getModStatusFilter().setBoxDeviceList(Arrays.asList(boxDevces));

			if (map.get("number") != null) {
				monitorUser.getModStatusFilter().setModLimit(Integer.parseInt(String.valueOf(map.get("number"))));
			}

			this.monitorService.addMonitorUser(monitorUser, MonitorUserType.Mod);
		}
	}
}
