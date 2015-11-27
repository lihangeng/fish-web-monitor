package com.yihuacomputer.fish.web.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.FishWebUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.monitor.form.RemoteCommandForm;

//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;

/**
 * 远程命令查询
 *
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/monitor/remoteCommand")
public class RemoteCommandController {

    private Logger logger = LoggerFactory.getLogger(RemoteCommandController.class);

    @Autowired
    private IRemoteCommHistService remoteCommHistService;

    @Autowired
    private MessageSource messageSourceEnum;
    
    @Autowired
    private IDeviceService deviceService;
    
    @Autowired
    private IOrganizationService organizationService;

    /**
     * 获取状态监控页面，矩形显示时的数据
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {
        logger.info("/monitor/remoteCommand search");

        IFilter filter = request2filter(webRequest, request);
        
        IPageResult<IRemoteCommHist> pageResult = remoteCommHistService.page(start, limit, filter);

        ModelMap map = new ModelMap();
        map.addAttribute(FishConstant.SUCCESS, true);
        map.addAttribute("total", pageResult.getTotal());
        map.addAttribute("data", convert(pageResult.list()));
        return map;
    }

    private List<RemoteCommandForm> convert(List<IRemoteCommHist> listHist) {
        List<RemoteCommandForm> result = new ArrayList<RemoteCommandForm>();

        RemoteCommandForm form = null;
        for (IRemoteCommHist hist : listHist) {

            form = new RemoteCommandForm();

            form.setId(hist.getId());
            form.setCommandResult(messageSourceEnum.getMessage(hist.getCommandResult().getText(), null, FishCfg.locale));
            form.setCommandType(messageSourceEnum.getMessage(hist.getCommandType().getText(), null, FishCfg.locale));
            form.setDatetime(hist.getDatetime());
            form.setHandlePerson(hist.getHandlePerson());

            IDevice device = hist.getDevice();
            if (device != null) {
                form.setTerminalId(device.getTerminalId());
                IOrganization org = device.getOrganization();
                if (org != null) {
                    form.setOrgName(org.getName());
                }
            }

            result.add(form);
        }

        return result;
    }

    private IFilter request2filter(WebRequest webRequest, HttpServletRequest request) {
        
        UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        
        String orgId = "" + userSession.getOrgId();
        
        IFilter filter = new Filter();
        Iterator<String> iterator = webRequest.getParameterNames();
        while (iterator.hasNext()) {
            String name = iterator.next();
            String value = webRequest.getParameter(name);
            if (isNotFilterName(name) || value.isEmpty()) {
                continue;
            }
            
            if ("endDate".equals(name)) {
                filter.le("datetime", value);
                continue;
            }
            if ("startDate".equals(name)) {
                filter.ge("datetime", value);
                continue;
            }
            if ("terminalId".equals(name)) {
                filter.like("device.terminalId", value);
                continue;
            }
            if ("organization".equals(name)) {
                orgId = value;
                continue;
            }
        }
        
        IOrganization organization = organizationService.get(orgId);
        filter.rlike("device.organization.orgFlag", organization.getOrgFlag());
        
        return filter;
    }

    private boolean isNotFilterName(String name) {
        return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
                || "sort".equals(name) || "blacklist".equals(name) || "organizationId".equals(name);
    }
}
