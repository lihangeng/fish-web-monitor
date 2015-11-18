package com.yihuacomputer.fish.web.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;
//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.monitor.entity.xfs.StateAnalysis;
import com.yihuacomputer.fish.web.monitor.form.MapMonitorForm;
import com.yihuacomputer.fish.web.monitor.form.ModProperty;
import com.yihuacomputer.fish.web.monitor.form.ModStatusForm;
import com.yihuacomputer.fish.web.monitor.form.StatusMonitorForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 状态信息查询
 *
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/monitor/device")
public class StatusMonitorController {
    private Logger logger = LoggerFactory.getLogger(StatusMonitorController.class);

    @Autowired
    private IXfsService xfsService;

    @Autowired
    private IFilterService filterService;

    // @Autowired
    // private IStateCodeService stateCodeService;

    @Autowired
    private MessageSource messageSourceStateCode;

    @Autowired
    private MessageSource messageSource;

    /**
     * 初始化设备属性信息
     */
    @RequestMapping(value = "/initProperty", method = RequestMethod.GET)
    public @ResponseBody ModelMap initProperty(@RequestParam String deviceId, @RequestParam String ip) {
        logger.info(String.format("initProperty(%s, %s)", deviceId, ip));
        ModelMap result = new ModelMap();

        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/propertisedetail";
        try {
            ModProperty modProperty = (ModProperty) HttpProxy.httpGet(url, ModProperty.class, 5000);
            System.out.println(JsonUtils.toJson(modProperty));
            result.put(FishConstant.SUCCESS, true);
            result.put("data", modProperty);
            return result;
        }
        catch (Exception e) {
            logger.info("The DeviceModuleStatusController of the method deviceIDC error!" + e.toString());

            result.put(FishConstant.SUCCESS, false);
            return result;
        }
    }

    /**
     * 获取状态监控页面，矩形显示时的数据
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/matrixData", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap matrixData(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {

        IPageResult<IStatusReport> pageResult = xfsService.pageStatus(start, limit,
                getStatusFilter(webRequest, request));

        ModelMap map = new ModelMap();
        map.addAttribute(FishConstant.SUCCESS, true);
        map.addAttribute("total", pageResult.getTotal());
        map.addAttribute("data", convert(pageResult.list()));
        return map;
    }

    /**
     * 地图监控设备
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/mapview", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap mapviewDevice(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getParameter("startP"))) {
            start = Integer.parseInt(request.getParameter("startP"));
        }
        if (StringUtils.isNotEmpty(request.getParameter("limitP"))) {
            limit = Integer.parseInt(request.getParameter("limitP"));
        }

        IPageResult<IStatusReport> pageResult = xfsService.pageStatus(start, limit,
                getStatusFilter(webRequest, request), true);

        ModelMap map = new ModelMap();
        map.addAttribute(FishConstant.SUCCESS, true);
        map.addAttribute("total", pageResult.getTotal());
        map.addAttribute("data", convert(pageResult.list()));
        return map;
    }

    /**
     * 地图监控机构
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/mapviewOrg", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap mapviewOrg(@RequestParam int start, @RequestParam int limit, WebRequest request) {
        String orgId = "";
        if (StringUtils.isNotEmpty(request.getParameter("orgId"))) {
            orgId = request.getParameter("orgId");
        }
        if (StringUtils.isNotEmpty(request.getParameter("startP"))) {
            start = Integer.parseInt(request.getParameter("startP"));
        }
        if (StringUtils.isNotEmpty(request.getParameter("limitP"))) {
            limit = Integer.parseInt(request.getParameter("limitP"));
        }

        IPageResult<IStatusMonitorMapOrg> pageResult = xfsService.pageStatusMapOrg(start, limit, orgId);

        ModelMap map = new ModelMap();
        map.addAttribute(FishConstant.SUCCESS, true);
        map.addAttribute("total", pageResult.getTotal());
        map.addAttribute("data", MapMonitorForm.convert(pageResult.list()));
        return map;
    }

    private IStatusFilter getStatusFilter(WebRequest webRequest, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String filterId = request.getParameter("filterId");
        String terminalId = request.getParameter("deviceCode");
        
        IStatusFilter statusFilter = null;

        if (StringUtils.isNotEmpty(filterId)) {
            statusFilter = filterService.get(Long.valueOf(filterId));
        }

        if (statusFilter == null) {
            statusFilter = filterService.makeStatusFilter();
            statusFilter.setUserId(userId);
            IRunStatusFilter runStatusFilter = statusFilter.getRunStatusFilter();
            IBoxStatusFilter boxStatusFilter = statusFilter.getBoxStatusFilter();
            IModStatusFilter modStatusFilter = statusFilter.getModStatusFilter();
            INetStatusFilter netStatusFilter = statusFilter.getNetStatusFilter();
            runStatusFilter.setAll(true);
            boxStatusFilter.setAll(true);
            modStatusFilter.setAll(true);
            netStatusFilter.setAll(true);
        }

        /* 机构信息 */
        if (StringUtils.isEmpty(statusFilter.getOrgId())) {
            UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
            statusFilter.setOrgId("" + userSession.getOrgId());
        }

        if (StringUtils.isNotEmpty(terminalId)) {
            statusFilter.setTerminalId(terminalId);
        }
        
        // 只监控开通的设备
        statusFilter.setDeviceStatus(DevStatus.OPEN);

        return statusFilter;
    }

    @SuppressWarnings("incomplete-switch")
    @RequestMapping(value = "/getModeState", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getModeState(@RequestParam String terminalId, @RequestParam String mod) {

        ModelMap map = new ModelMap();
        IXfsStatus status = xfsService.loadXfsStatus(terminalId);
        DeviceMod deviceMod = DeviceMod.valueOf(mod);
        String stateCode = null;
        switch (deviceMod) {
            case IDC: {
                stateCode = status.getStatusIdc().getCode();
                break;
            }
            case CIM: {
                stateCode = status.getStatusCim().getCode();
                break;
            }
            case CDM: {
                stateCode = status.getStatusCdm().getCode();
                break;
            }
            case RPR: {
                stateCode = status.getStatusRpr().getCode();
                break;
            }
            case JPR: {
                stateCode = status.getStatusJpr().getCode();
                break;
            }
            case PIN: {
                stateCode = status.getStatusPin().getCode();
                break;
            }
            case TTU: {
                stateCode = status.getStatusTtu().getCode();
                break;
            }
            case SIU: {
                stateCode = status.getStatusSiu().getCode();
                break;
            }
            case ICC: {
                stateCode = status.getStatusIcc().getCode();
                break;
            }
            case ISC: {
                stateCode = status.getStatusIsc().getCode();
                break;
            }
            case FGP: {
                stateCode = status.getStatusFgp().getCode();
                break;
            }
        }

        IStateAnalysis analysis = this.getStateCode(stateCode, deviceMod);
        map.addAttribute("data", ModStatusForm.toForm(analysis));
        map.addAttribute(FishConstant.SUCCESS, true);
        return map;
    }

    private IStateAnalysis getStateCode(String stateCode, DeviceMod deviceMod) {
        StateAnalysis stAna = new StateAnalysis();
        if (stateCode == null || deviceMod == null) {
            stAna.setDescription("No explanation");
            stAna.setSolution("No Explication");
            return stAna;
        }

        String mod = deviceMod.toString();
        StringBuffer desc = new StringBuffer();
        StringBuffer solution = new StringBuffer();
        String code = null;

        int codeAt = 5;// 由于客户端在组织状态码时少组一位，所以从第五位开始计算
        for (int codeIdx = 0; codeIdx < stateCode.length(); codeIdx++) {
            code = mod + Integer.toHexString(codeAt).toUpperCase() + stateCode.charAt(codeIdx);
            codeAt++;
            String descD = messageSourceStateCode.getMessage(code + ".desc", null, FishCfg.locale);
            String soluD = messageSourceStateCode.getMessage(code + ".slution", null, FishCfg.locale);
            if (descD.equals(code + ".desc") || soluD.equals(code + ".slution")) {// 如果找不到，返回的是原字符串。舍弃
                continue;
            }

            desc.append(descD).append(".");
            solution.append(soluD).append(".");
        }
        stAna.setDescription(desc.toString());
        stAna.setSolution(solution.toString());
        return stAna;

    }

    // add by panxin 国际化将“未知”提示放入controller层中
    private List<StatusMonitorForm> convert(List<IStatusReport> list) {

        List<StatusMonitorForm> result = new ArrayList<StatusMonitorForm>();
        String unkown = messageSource.getMessage("monitor.statusMonitor.unkown", null, FishCfg.locale);

        for (IStatusReport item : list) {
            if (item.getAppRelease() == null) {
                item.setAppRelease(unkown);
            }
            result.add(new StatusMonitorForm(item));
        }
        return result;

    }

}
