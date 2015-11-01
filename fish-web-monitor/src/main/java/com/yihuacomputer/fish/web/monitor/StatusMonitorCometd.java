package com.yihuacomputer.fish.web.monitor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.api.person.IOrganizationService;

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

            Object filterId = map.get("filterId");

            IStatusFilter statuFilterInDB = null;
            if (filterId != null) {
                statuFilterInDB = filterService.get(Long.valueOf(filterId.toString()));
            }

            if (statuFilterInDB == null) {
                statuFilterInDB = filterService.makeStatusFilter();
                statuFilterInDB.getRunStatusFilter().setAll(true);
                statuFilterInDB.getBoxStatusFilter().setAll(true);
                statuFilterInDB.getModStatusFilter().setAll(true);
                statuFilterInDB.getNetStatusFilter().setAll(true);
            }

            // 当条件没有设置机构时,取用户所有机构作为条件
            if (statuFilterInDB.getOrgId() == null) {
                if (map.get("orgId") != null) {
                    statuFilterInDB.setOrgId(map.get("orgId").toString());
                }
            }

            monitorUser.setStatusFilter(statuFilterInDB);

            if (statuFilterInDB.getOrgId() != null) {
                List<Long> orgList = orgService.listSubOrgId(statuFilterInDB.getOrgId());
                monitorUser.getStatusFilter().setSubOrg(orgList);
            }

            Object terminalId = map.get("deviceCode");
            monitorUser.getStatusFilter().setTerminalId(terminalId != null ? String.valueOf(terminalId) : null);

            // 设备列表
            String devList = String.valueOf(map.get("devices"));

            String[] devces = devList.split("/");
            monitorUser.getStatusFilter().setDeviceList(Arrays.asList(devces));

            this.monitorService.addMonitorUser(monitorUser, MonitorUserType.Status);
        }
    }

}
