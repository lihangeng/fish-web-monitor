package com.yihuacomputer.fish.web.monitor;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;
import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.monitor.entity.filter.BoxStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.NetStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RunStatusFilter;
import com.yihuacomputer.fish.web.monitor.form.MapMonitorForm;
import com.yihuacomputer.fish.web.monitor.form.ModProperty;
import com.yihuacomputer.fish.web.monitor.form.ModStatusForm;
import com.yihuacomputer.fish.web.monitor.form.MonitorFilterForm;
import com.yihuacomputer.fish.web.monitor.form.StatusFilterForm;
import com.yihuacomputer.fish.web.monitor.form.StatusMonitorForm;

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

	@Autowired
	private IStateCodeService stateCodeService;

	/**
	 * 初始化设备属性信息
	 */
	@RequestMapping(value = "/initProperty", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap initProperty(@RequestParam String deviceId, @RequestParam String ip) {
		logger.info(String.format("initProperty(%s, %s)", deviceId, ip));
		ModelMap result = new ModelMap();

		String url = MonitorCfg.getHttpUrl(ip) + "/ctr/propertisedetail";
		try {
			ModProperty modProperty = (ModProperty) HttpProxy.httpGet(url, ModProperty.class);
			System.out.println(JsonUtils.toJson(modProperty));
			result.put(FishConstant.SUCCESS, true);
			result.put("data", modProperty);
			return result;
		} catch (Exception e) {
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
	public ModelMap matrixData(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		if (StringUtils.isNotEmpty(request.getParameter("startP"))) {
			start = Integer.parseInt(request.getParameter("startP"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("limitP"))) {
			limit = Integer.parseInt(request.getParameter("limitP"));
		}

		IPageResult<IStatusReport> pageResult = xfsService.pageStatus(start, limit, getStatusFilter(request));

		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("total", pageResult.getTotal());
		map.addAttribute("data", StatusMonitorForm.convert(pageResult.list()));
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
	public ModelMap mapviewDevice(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		if (StringUtils.isNotEmpty(request.getParameter("startP"))) {
			start = Integer.parseInt(request.getParameter("startP"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("limitP"))) {
			limit = Integer.parseInt(request.getParameter("limitP"));
		}

		IPageResult<IStatusReport> pageResult = xfsService.pageStatus(start, limit, getStatusFilter(request), true);

		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("total", pageResult.getTotal());
		map.addAttribute("data", StatusMonitorForm.convert(pageResult.list()));
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

	private IStatusFilter getStatusFilter(WebRequest request) {
		String filterUserId = request.getParameter("filterUserId");
		IStatusFilter statusFilter = filterService.loadStatusFilter(filterUserId);

		if (statusFilter == null) {
			statusFilter = filterService.makeAndSaveStatusFilter(filterUserId);
			statusFilter.setUserId(filterUserId);
			IRunStatusFilter runStatusFilter = statusFilter.getRunStatusFilter();
			IBoxStatusFilter boxStatusFilter = statusFilter.getBoxStatusFilter();
			IModStatusFilter modStatusFilter = statusFilter.getModStatusFilter();
			INetStatusFilter netStatusFilter = statusFilter.getNetStatusFilter();
			runStatusFilter.setAll(true);
			boxStatusFilter.setAll(true);
			modStatusFilter.setAll(true);
			netStatusFilter.setAll(true);
		} else {
			this.request2StatusFilter(request, statusFilter);
		}

		/* 机构信息 */
		if (StringUtils.isNotEmpty(request.getParameter("orgId"))) {
			statusFilter.setOrgId(request.getParameter("orgId"));
		}

		/**
		 * 只监控开通的设备
		 */
		statusFilter.setDeviceStatus(Status.OPENING);

		filterService.updateStatusFilter(statusFilter);
		return statusFilter;
	}

	/**
	 * 获取监控状态条件
	 */
	@RequestMapping(value = "/getFilter", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getFilter(@RequestParam String userId) {
		IStatusFilter statusFilter = filterService.loadStatusFilter(userId);
		if (statusFilter == null) {
			statusFilter = this.filterService.makeStatusFilter();
		}
		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("filter", StatusFilterForm.toForm(statusFilter));
		return map;
	}

	/**
	 * 获取监控过滤条件
	 */
	@RequestMapping(value = "/getMonitorFilter", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getMonitorFilter(@RequestParam String userId) {
		IStatusFilter statusFilter = filterService.loadStatusFilter(userId);
		if (statusFilter == null) {
			statusFilter = this.filterService.makeStatusFilter();
		}
		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("filter", new MonitorFilterForm(statusFilter));
		return map;
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

		IStateAnalysis analysis = stateCodeService.getStateCode(stateCode, deviceMod);
		map.addAttribute("data", ModStatusForm.toForm(analysis));
		map.addAttribute(FishConstant.SUCCESS, true);
		return map;
	}

	private void request2StatusFilter(WebRequest request, IStatusFilter statusFilter) {
		Iterator<String> params = request.getParameterNames();
		while (params.hasNext()) {
			String param = params.next();
			if (param.equals("deviceCode")) {
				statusFilter.setTerminalId(request.getParameter("deviceCode"));
				break;
			}
			if (param.startsWith("box_") || param.startsWith("deal_") || param.startsWith("module_")
					|| param.startsWith("net_") || param.startsWith("run_")) {
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
				modStatusFilter.setAll(getValue(request, "module_all"));
				modStatusFilter.setHealth(getValue(request, "module_healthy"));
				modStatusFilter.setWarning(getValue(request, "module_waring"));
				modStatusFilter.setFatal(getValue(request, "module_fatal"));
				modStatusFilter.setUnknown(getValue(request, "module_unknown"));
				modStatusFilter.setNodevice(getValue(request, "module_noDevice"));
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
				break;
			}
			if (param.equals("brandItem") || param.equals("classifyItem") || param.equals("ingItem")
					|| param.equals("sellItem") || param.equals("atmGroup")) {
				statusFilter.setAwayFlag(Integer.parseInt(request.getParameter("ingItem")));
				String classifyItem = request.getParameter("classifyItem");
				if (classifyItem == null || classifyItem.isEmpty()) {
					statusFilter.setDevType(0);
				} else {
					statusFilter.setDevType(Long.parseLong(classifyItem));
				}
				statusFilter.setDevVendor(Long.parseLong(request.getParameter("brandItem")));
				statusFilter.setWorkType(Integer.parseInt(request.getParameter("sellItem")));
				statusFilter.setAtmGroup(Long.parseLong(request.getParameter("atmGroup")));
			}
		}
	}

	private boolean getValue(WebRequest request, String name) {
		return Boolean.valueOf(request.getParameter(name)).booleanValue();
	}

}
