package com.yihuacomputer.fish.web.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.api.person.IOrganizationService;

/**
 * 吞卡监控
 *
 * @author pengwenchao
 *
 */
@Component
@org.cometd.annotation.Service("RetaincardMonitorCometd")
public class RetaincardMonitorCometd {

	public static final String JOIN_CHANNEL = "/service/retaincard/join";

	@Autowired
	private IMonitorService monitorService;

	@Autowired
	private IFilterService filterService;

	@Autowired
	private IOrganizationService orgService;

	/**
	 * 开始监控，将监控条件保存
	 * */

	@org.cometd.annotation.Listener(JOIN_CHANNEL)
	public void join(ServerSession remote, ServerMessage.Mutable message) {

		Object data = message.getData();
		String userId = remote.getId();
		if (data instanceof HashMap) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) data;

			IMonitorUser monitorUser = this.monitorService.makeMonitorUser(userId);
			monitorUser.setUserSession(remote);

			// 机构号
			String orgId = (String) map.get("organizationId");

			// 设备号
			String terminalId = (String) map.get("terminalId");

			// 下属机构集合
			List<Long> orgList = orgService.listSubOrgId(orgId);

			IRetaincardFilter retaincardFilter = filterService.makeRetaincardFilter();
			retaincardFilter.setOrgId(orgId);
			retaincardFilter.setSubOrg(orgList);
			retaincardFilter.setTerminalId(terminalId);

			monitorUser.setRetaincardFilter(retaincardFilter);
			monitorUser.getRetaincardFilter().setCardRetainNum(0);
			monitorService.addMonitorUser(monitorUser, MonitorUserType.Retaincard);
		}
	}
}
