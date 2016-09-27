package com.yihuacomputer.fish.web.monitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.filter.BoxStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.NetStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RunStatusFilter;
import com.yihuacomputer.fish.web.monitor.form.MonitorFilterForm;

//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;

/**
 * 订阅条件管理
 *
 * @author pengwenchao
 *
 */
@Controller
@RequestMapping("/monitor/device/monitorFilter")
@ClassNameDescrible(describle="userlog.MonitorFilterController")
public class MonitorFilterController {

    private Logger logger = LoggerFactory.getLogger(MonitorFilterController.class);

    @Autowired
    private IFilterService filterService;

    @Autowired
    private IAtmBrandService atmBrandService;

    @Autowired
    private IAtmTypeService atmTypeService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IAtmGroupService atmGroupService;

    @Autowired
    private MessageSource messageSourceStateCode;

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取监控过滤条件
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getMonitorFilter(@RequestParam int start, @RequestParam int limit, @RequestParam String userId,
            WebRequest request) {

        IFilter filter = new Filter();

        filter.eq("userId", userId);
        String filterName = request.getParameter("filterName");
        if (StringUtils.isNotEmpty(filterName)) {
            filter.like("filterName", filterName);
        }

        IPageResult<IStatusFilter> pageList = filterService.page(start, limit, filter);

        // 机构信息
        Map<String, IOrganization> orgMap = new HashMap<String, IOrganization>();
        Iterable<IAtmVendor> listVendor = atmBrandService.list();
        List<IAtmType> listType = atmTypeService.list();
        Iterable<IAtmGroup> listGroup = atmGroupService.list();

        List<MonitorFilterForm> result = new ArrayList<MonitorFilterForm>();
        MonitorFilterForm filterForm = null;
        for (IStatusFilter statusFilter : pageList.list()) {
            filterForm = new MonitorFilterForm(statusFilter);

            // 获取机构名称
            String orgId = statusFilter.getOrgId();
            if (StringUtils.isNotEmpty(orgId)) {
                if (orgMap.containsKey(orgId)) {
                    filterForm.setOrgName(orgMap.get(orgId).getName());
                } else {
                    IOrganization org = organizationService.get(orgId);
                    if (org != null) {
                        orgMap.put(orgId, org);
                        filterForm.setOrgName(org.getName());
                    }
                }
            }

            // 获取设备品牌名称
            if (statusFilter.getDevVendor() > 0) {
                for (IAtmVendor vendor : listVendor) {
                    if (vendor.getId() == statusFilter.getDevVendor()) {
                        filterForm.setBrandItemName(vendor.getName());
                        break;
                    }
                }
            }

            // 获取设备型号名称
            if (statusFilter.getDevType() > 0) {
                for (IAtmType type : listType) {
                    if (type.getId() == statusFilter.getDevType()) {
                        filterForm.setClassifyItemName(type.getName());
                        break;
                    }
                }
            }

            // 获取设备分钟名称
            if (statusFilter.getAtmGroup() > 0) {
                for (IAtmGroup group : listGroup) {
                    if (group.getId() == statusFilter.getAtmGroup()) {
                        filterForm.setAtmGroupName(group.getName());
                        break;
                    }
                }
            }

            result.add(filterForm);
        }

        ModelMap map = new ModelMap();
        map.addAttribute(FishConstant.SUCCESS, true);
        map.addAttribute("total", pageList.getTotal());
        map.addAttribute("data", result);
        return map;
    }

    /**
     * 增加
     * 
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.MonitorFilterController.monitorFilterAdd",hasArgs=true,argsContext="userId")
    @ResponseBody
    public ModelMap monitorFilterAdd(@RequestParam String userId, WebRequest request) {

        ModelMap result = new ModelMap();

        try {
            String filterName = request.getParameter("filterName");
            IStatusFilter sf = filterService.getByFilterName(userId, filterName);

            if (sf != null) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG,
                        messageSource.getMessage("monitorFilter.duplicateName", null, FishCfg.locale));
                return result;
            }

            IStatusFilter statusFilter = filterService.makeStatusFilter();
            this.getWebParams(request, statusFilter);
            /* 机构信息 */
            if (StringUtils.isNotEmpty(request.getParameter("orgId"))) {
                statusFilter.setOrgId(request.getParameter("orgId"));
            }

            statusFilter.setUserId(userId);
            statusFilter.setFilterName(filterName);

            filterService.save(statusFilter);

            result.addAttribute(FishConstant.SUCCESS, true);

        }
        catch (Exception ex) {
            logger.error(String.format("保存监控状态失败!失败信息[%s]", ex.getMessage()));
            result.addAttribute(FishConstant.SUCCESS, false);
        }

        return result;
    }

    /**
     * 修改
     * 
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.MonitorFilterController.monitorFilterUpdate",hasArgs=true,argsContext="id")
    @ResponseBody
    public ModelMap monitorFilterUpdate(@RequestParam long id, WebRequest request) {

        ModelMap result = new ModelMap();

        try {
            IStatusFilter statusFilter = filterService.get(id);

            this.getWebParams(request, statusFilter);

            /* 机构信息 */
            if (StringUtils.isNotEmpty(request.getParameter("orgId"))) {
                statusFilter.setOrgId(request.getParameter("orgId"));
            } else {
                statusFilter.setOrgId(null);

            }
            String filterName = request.getParameter("filterName");

            IStatusFilter sf = filterService.getByFilterName(statusFilter.getUserId(), filterName);

            // 如果修改的名称存在，并且不是该条记录
            if (sf != null && (sf.getId() != id)) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG,
                        messageSource.getMessage("monitorFilter.duplicateName", null, FishCfg.locale));
                return result;
            }
            
            statusFilter.setFilterName(filterName);
            filterService.updateStatusFilter(statusFilter);

            result.addAttribute(FishConstant.SUCCESS, true);

        }
        catch (Exception ex) {
            logger.error(String.format("修改监控状态失败!失败信息[%s]", ex.getMessage()));
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }

        return result;
    }

    /**
     * 根据id删除监控条件
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.MonitorFilterController.delete",hasArgs=false,urlArgs=true)
    public @ResponseBody ModelMap delete(@PathVariable long id) {
        logger.info(" delete monitorFilter: monitorFilter.id = " + id);
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
        try {
            filterService.delete(id);
        }
        catch (ServiceException se) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, se.getMessage());
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
        }
        return result;
    }

    private void getWebParams(WebRequest request, IStatusFilter statusFilter) {

        IRunStatusFilter runStatusFilter = statusFilter.getRunStatusFilter();
        if (runStatusFilter == null) {
            runStatusFilter = new RunStatusFilter();
        }
        runStatusFilter.setAll(getValue(request, "run_all"));
        runStatusFilter.setUnknow(getValue(request, "run_unknown"));
        runStatusFilter.setInitial(getValue(request, "run_initial"));
        runStatusFilter.setHealth(getValue(request, "run_healthy"));
        runStatusFilter.setCustomer(getValue(request, "run_customer"));
        runStatusFilter.setMaintain(getValue(request, "run_maintain"));
        runStatusFilter.setVdm(getValue(request, "run_vdm"));
        runStatusFilter.setHalf(getValue(request, "run_subHealth"));
        runStatusFilter.setShutdown(getValue(request, "run_halt"));
        runStatusFilter.setReBoot(getValue(request, "run_reboot"));
        runStatusFilter.setAtmpStop(getValue(request, "run_stopAtmp"));
        runStatusFilter.setStopManMade(getValue(request, "run_stopManmade"));
        runStatusFilter.setStopMod(getValue(request, "run_stopMod"));
        runStatusFilter.setStopUnCashIn(getValue(request, "run_stopUnCashIn"));
        runStatusFilter.setStop(getValue(request, "run_stopunknown"));

        statusFilter.setRunStattusFilter(runStatusFilter);

        IBoxStatusFilter boxStatusFilter = statusFilter.getBoxStatusFilter();
        if (boxStatusFilter == null) {
            boxStatusFilter = new BoxStatusFilter();
        }
        boxStatusFilter.setAll(getValue(request, "box_all"));
        boxStatusFilter.setFull(getValue(request, "box_full"));
        boxStatusFilter.setLow(getValue(request, "box_low"));
        boxStatusFilter.setEmpty(getValue(request, "box_empty"));
        boxStatusFilter.setHigh(getValue(request, "box_high"));
        boxStatusFilter.setFatal(getValue(request, "box_fatal"));
        boxStatusFilter.setUnknown(getValue(request, "box_unknown"));
        boxStatusFilter.setHealthy(getValue(request, "box_healthy"));

        statusFilter.setBoxStatusFilter(boxStatusFilter);

        IModStatusFilter modStatusFilter = statusFilter.getModStatusFilter();
        if (modStatusFilter == null) {
            modStatusFilter = new ModStatusFilter();
        }
        modStatusFilter.setAll(getValue(request, "mod_all"));
        modStatusFilter.setHealth(getValue(request, "mod_healthy"));
        modStatusFilter.setWarning(getValue(request, "mod_waring"));
        modStatusFilter.setFatal(getValue(request, "mod_fatal"));
        modStatusFilter.setUnknown(getValue(request, "mod_unknown"));
        modStatusFilter.setNodevice(getValue(request, "mod_noDevice"));
        statusFilter.setModStatusFilter(modStatusFilter);

        INetStatusFilter netStatusFilter = statusFilter.getNetStatusFilter();
        if (netStatusFilter == null) {
            netStatusFilter = new NetStatusFilter();
        }
        netStatusFilter.setAll(getValue(request, "net_all"));
        netStatusFilter.setHealth(getValue(request, "net_healthy"));
        netStatusFilter.setWarning(getValue(request, "net_warning"));
        netStatusFilter.setFatal(getValue(request, "net_fatal"));
        netStatusFilter.setUnknown(getValue(request, "net_unknown"));
        statusFilter.setNetStatusFilter(netStatusFilter);

        if (StringUtils.isNotEmpty(request.getParameter("ingItem"))) {
            statusFilter.setAwayFlag(Integer.parseInt(request.getParameter("ingItem")));
        } else {
            statusFilter.setAwayFlag(0);
        }

        if (StringUtils.isNotEmpty(request.getParameter("classifyItem"))) {
            statusFilter.setDevType(Long.parseLong(request.getParameter("classifyItem")));
        } else {
            statusFilter.setDevType(0);
        }

        if (StringUtils.isNotEmpty(request.getParameter("brandItem"))) {
            statusFilter.setDevVendor(Long.parseLong(request.getParameter("brandItem")));
        } else {
            statusFilter.setDevVendor(0);
        }

        if (StringUtils.isNotEmpty(request.getParameter("sellItem"))) {
            statusFilter.setWorkType(Integer.parseInt(request.getParameter("sellItem")));
        } else {
            statusFilter.setWorkType(0);
        }

        if (StringUtils.isNotEmpty(request.getParameter("atmGroup"))) {
            statusFilter.setAtmGroup(Long.parseLong(request.getParameter("atmGroup")));
        } else {
            statusFilter.setAtmGroup(0);
        }
    }

    private boolean getValue(WebRequest request, String name) {
        return Boolean.valueOf(request.getParameter(name)).booleanValue();
    }

}
