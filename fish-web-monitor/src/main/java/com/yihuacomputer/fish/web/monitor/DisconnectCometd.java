package com.yihuacomputer.fish.web.monitor;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;

/**
 * 交易监控
 * 
 * @author YiHUa
 * 
 */
@Component
@org.cometd.annotation.Service("DisconnectCometd")
public class DisconnectCometd {

	public static final String DEPARTURE_CHANNEL = "/meta/disconnect";

	@Autowired
	private IMonitorService monitorService;

	
    /**
     * 停止监控，去除监控条件
     * */
    
	@org.cometd.annotation.Listener(DEPARTURE_CHANNEL)
	public void departure(ServerSession remote, ServerMessage.Mutable message) {	
		this.monitorService.removeMonitorUser(remote.getId(),MonitorUserType.All);
	}  

}
