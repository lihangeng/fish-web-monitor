package com.yihuacomputer.fish.web.machine.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDeviceRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.machine.entity.DeviceOpenPlan;
import com.yihuacomputer.fish.machine.entity.OpenPlanDetail;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.machine.form.OpenPlanDetailForm;
import com.yihuacomputer.fish.web.machine.form.OpenPlanForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 开机方案的控制
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping(value = "/plan")
@ClassNameDescrible(describle="userlog.openPlanController")
public class OpenPlanController {

	private Logger logger = LoggerFactory.getLogger(OpenPlanController.class);

	@Autowired
	private IOpenPlanService openPlanService;

	@Autowired
	protected MessageSource messageSource;
	
	
	/**
	 * 设备接口
	 */
	
	@Autowired
	private MessageSource messageSourceEnum;
	
	@Autowired
	private IDeviceService deviceService;


	@Autowired
	private IOpenPlanDeviceRelation relationService;


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchOpenPlan(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search openPlan : start = %s ,limit = %s ", start, limit));
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
						continue;
					} else if ("name".equals(name)) {
						filter.like("name", request.getParameter(name));
					} else if ("startDate".equals(name)) {
						filter.eq("startDate", DateUtils.getDate(request.getParameter(name)));
					} else if ("endDate".equals(name)) {
						filter.eq("endDate", DateUtils.getDate(request.getParameter(name)));
					}
				}
			}
		}

		// 根据创建时间降序排列
		filter.order("createDateTime");
		//filter.addOrder(new OrderBy("endDate", OrderBy.ASC));
		IPageResult<IDeviceOpenPlan> pageResult = openPlanService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toPlanForm(pageResult.list()));
		return result;
	}

	private List<OpenPlanForm> toPlanForm(List<IDeviceOpenPlan> plans) {
		List<OpenPlanForm> forms = new ArrayList<OpenPlanForm>();
		for (IDeviceOpenPlan plan : plans) {
			int deviceCount = openPlanService.deviceCount(plan.getId());
			plan.setDeviceCount(deviceCount);
			OpenPlanForm form = new OpenPlanForm(plan);
			form.setPlanStateType(getEnumI18n(form.getPlanStateType()));
			forms.add(form);
		}
		return forms;
	}
	private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	private List<OpenPlanForm> toPlanForm(List<IDeviceOpenPlan> plans, String deviceId, String terminalId) {
		List<OpenPlanForm> forms = new ArrayList<OpenPlanForm>();
		for (IDeviceOpenPlan plan : plans) {
			int deviceCount = openPlanService.deviceCount(plan.getId());
			OpenPlanForm form = new OpenPlanForm(plan, deviceId, terminalId, deviceCount);
			form.setPlanStateType(getEnumI18n(form.getPlanStateType()));
			forms.add(form);
		}
		return forms;
	}


	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchPlanForDevice(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search searchPlanForDevice : start = %s ,limit = %s ", start, limit));
		IDevice device = null;
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
						continue;
					} else if ("deviceId".equals(name)) {
						device = deviceService.get(Long.valueOf(request.getParameter(name)));
					}
				}
			}
		}

		List<IDeviceOpenPlan> pageResult = new ArrayList<IDeviceOpenPlan>();
		String deviceId = null;
		String terminalId = null;
		if (device != null) {
			pageResult = relationService.listPlanByDevice(device);
			deviceId = device.getId() + "";
			terminalId = device.getTerminalId();
		}
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		result.addAttribute("total", pageResult.size());
		result.addAttribute("data", toPlanForm(pageResult, deviceId, terminalId));
		return result;
	}
	/**
	 * 查看开机方案详情
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchPlanDetail(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search plan detail: start = %s ,limit = %s ", start, limit));
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		List<IOpenPlanDetail> planDetails = openPlanService.getOpenPlanDetialById(Long.parseLong(request.getParameter("openPlanId")));
		result.addAttribute("data", toPlanDetailForm(planDetails));
		return result;
	}

	@RequestMapping(value = "/detailsForDevice", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchPlanDetailForDevice(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("searchPlanDetailForDevice: start = %s ,limit = %s ", start, limit));
		ModelMap result = new ModelMap();
		String terminalId = request.getParameter("terminalId");
		IDevice device = deviceService.get(terminalId);
		List<IDeviceOpenPlan> list = null;
		if(device != null)
		{
			list = relationService.listPlanByDevice(device);
		}

		long openPlanId = 0;
		if (list != null && list.size() > 0) {
			openPlanId = list.get(0).getId();
		}
		result.addAttribute("success", true);
		List<IOpenPlanDetail> planDetails = openPlanService.getOpenPlanDetialById(openPlanId);
		result.addAttribute("data", toPlanDetailForm(planDetails));
		return result;
	}

	private List<OpenPlanDetailForm> toPlanDetailForm(List<IOpenPlanDetail> planDetails) {
		List<OpenPlanDetailForm> forms = new ArrayList<OpenPlanDetailForm>();
		for (IOpenPlanDetail planDetail : planDetails) {
			OpenPlanDetailForm form = new OpenPlanDetailForm(planDetail);
			forms.add(form);
		}
		return forms;
	}

	@RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.openPlanController.add",hasReqBodyParam=true,reqBodyClass=OpenPlanForm.class,bodyProperties="name")
	public @ResponseBody
	ModelMap add(@RequestBody OpenPlanForm form, WebRequest webRequest, HttpSession session, HttpServletRequest request) {
		ModelMap result = new ModelMap();

		if (isExistCode(form.getId(), form.getName()) == true) {
			result.put("success", false);
			//该开机方案已存在，请重新输入！
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.planExsit", null,FishCfg.locale));
		} else {
			IDeviceOpenPlan openPlan = openPlanService.getDeviceOpenPlanByName(form.getName());
			openPlan = new DeviceOpenPlan();
			openPlan.setDesc(form.getDesc());
			openPlan.setName(form.getName());

			openPlan.setCreateDateTime(DateUtils.getTimestamp(new Date()));

			openPlan.setEndDate(DateUtils.getDate(form.getEndDate()));
			openPlan.setStartDate(DateUtils.getDate(form.getStartDate()));
			openPlan.setPlanState(form.getPlanState());
			openPlan.setPlanType(form.getPlanType());
			List<IOpenPlanDetail> details = new ArrayList<IOpenPlanDetail>();
			for (OpenPlanDetailForm detail : form.getOpenPlanDetails()) {
				OpenPlanDetail planDetail = new OpenPlanDetail();
				planDetail.setEndTime(detail.getEndTime());
				planDetail.setOpenClose(detail.getOpenClose());
				planDetail.setStartTime(detail.getStartTime());
				planDetail.setWeek(detail.getWeek());
				planDetail.setDeviceOpenPlan(openPlan);
				details.add(planDetail);
			}
			openPlan.setOpenPlanDetail(details);
			IDeviceOpenPlan plan = openPlanService.saveOpenPlan(openPlan);
			if (plan == null) {
				result.addAttribute("success", false);
				//增加方案失败，请重新操作！
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("openPlan.addFail", null,FishCfg.locale));
				return result;
			}
			form.setId(plan.getId());
			form.setCreateDateTime(plan.getCreateDateTime());
			result.addAttribute("success", true);
			result.addAttribute("data", form);
		}
		return result;
	}

	/**
	 *
	 * 根据ID更新开机方案信息
	 *
	 * @param id
	 * @param form
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.openPlanController.update",hasReqBodyParam=true,reqBodyClass=OpenPlanForm.class,bodyProperties="name")
	public @ResponseBody
	ModelMap update(@PathVariable String id, @RequestBody OpenPlanForm form) {
		logger.info(" update plan : plan.id = " + id);
		form.setId(Long.parseLong(id));
		IFilter filter = new Filter();
		filter.eq("openPlanId", form.getId());
		ModelMap result = new ModelMap();
		if (!relationService.isExistPlanLink(filter)) {
			IDeviceOpenPlan openPlan = openPlanService.getDeviceOpenPlanById(Long.valueOf(id));
			if (openPlan == null) {
				result.addAttribute("success", false);
				result.addAttribute("type", 1);
				//该方案不存在，请刷新后查看！
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.planNoExsit", null,FishCfg.locale));
				return result;
			}
			if (isExistCode(Long.valueOf(id), form.getName()) == true) {
				result.put("success", false);
				//该开机方案已存在，请重新输入！
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.updatePlanExsit", null,FishCfg.locale));
				return result;
			}
			openPlan.setDesc(form.getDesc());
			openPlan.setName(form.getName());
			openPlan.setEndDate(DateUtils.getDate(form.getEndDate()));
			openPlan.setStartDate(DateUtils.getDate(form.getStartDate()));
			openPlan.setPlanState(form.getPlanState());
			openPlan.setPlanType(form.getPlanType());
			List<IOpenPlanDetail> details = new ArrayList<IOpenPlanDetail>();
			for (OpenPlanDetailForm detail : form.getOpenPlanDetails()) {
				OpenPlanDetail planDetail = new OpenPlanDetail();
				planDetail.setEndTime(detail.getEndTime());
				planDetail.setOpenClose(detail.getOpenClose());
				planDetail.setStartTime(detail.getStartTime());
				planDetail.setWeek(detail.getWeek());
				planDetail.setDeviceOpenPlan(openPlan);
				details.add(planDetail);
			}
			openPlan.setOpenPlanDetail(details);
			IDeviceOpenPlan plan = openPlanService.updatePlan(openPlan);
			if (plan == null) {
				result.addAttribute("success", false);
				//修改方案失败，请重新操作！
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.updatePlanFail", null,FishCfg.locale));
				return result;
			}
			form.setId(plan.getId());
			result.addAttribute("success", true);
			result.addAttribute("data", form);
		} else {
			result.addAttribute("success", false);
			//该方案已与设备关联，不能被更改！
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.cannotUpdate", null,FishCfg.locale));
		}
		return result;
	}

	// 删除方案
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.openPlanController.delete",hasLogKey=true)
	public @ResponseBody
	ModelMap delete(@PathVariable long id, HttpServletRequest req) {
		logger.info(" delete plan with cascade: plan.id = " + id);
		ModelMap result = new ModelMap();
		try {
			UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
			IDeviceOpenPlan openPlan = openPlanService.getDeviceOpenPlanById(id);
			if (openPlan == null) {
				result.addAttribute("success", true);
				return result;
			}
			result.addAttribute(FishConstant.LOG_KEY, openPlan.getName());
			IPageResult<IDevice> pageResult = relationService.pageDeviceByPlan(0, 25, openPlan, new Filter(), String.valueOf(userSession.getOrgId()));
			// 是否与人员有关联
			if (pageResult != null && !pageResult.list().isEmpty()) {
				result.addAttribute("success", false);
				//该方案已应用于设备,无法删除！
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.cannotDelete", null, FishCfg.locale));
				return result;
			}

			openPlanService.deletePlan(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			logger.error(ex.getMessage());
			//删除失败
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.deleteFail", null,FishCfg.locale) + ex.getMessage());
		}
		return result;
	}

	/**
	 *
	 * 根据方案Id获得关联设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/linkedDevice", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchLinkedDevice(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		String orgId = request.getParameter("orgId");
		if (null == orgId || orgId.isEmpty()) {
			orgId = String.valueOf(userSession.getOrgId());
		}
		IPageResult<IDevice> pageResult = null;
		String flag1 = request.getParameter("flag");
		int flag = 0;
		if ("1".equals(flag1)) {
			flag = 1;
		}
		String planId = request.getParameter("planId");
		if (flag == 0) {
			IFilter filter = new Filter();

			filter = request2filter(request);
			pageResult = relationService.pageDeviceByPlan(start, limit, openPlanService.getDeviceOpenPlanById(Long.parseLong(planId)), filter, orgId);
			result.addAttribute("success", true);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", convert(pageResult.list()));
		} else {
			IFilter filter = new Filter();
			filter = request2filter(request);
			pageResult = relationService.pageUnlinkDeviceByPlan(start, limit, openPlanService.getDeviceOpenPlanById(Long.parseLong(planId)), filter, orgId);
			result.addAttribute("success", true);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", convert(pageResult.list()));
		}
		return result;
	}


	@RequestMapping(value = "/uploadExcel",method = RequestMethod.POST)
	public @ResponseBody
	String upload(WebRequest webRequest, @RequestParam(value = "file") MultipartFile file,
			HttpServletResponse response,HttpServletRequest request){
		StringBuffer message = new StringBuffer();
		List <IDevice> linkDeviceList = new ArrayList<IDevice>();
		StringBuffer dataJson = new StringBuffer();
		Iterator<IDevice> iterator = linkDeviceList.iterator();
		dataJson.append("'data':[");
		while (iterator.hasNext()){
			IDevice device = iterator.next();
			dataJson = dataJson.append("{" + "'id':'"
					+ device.getId() +"','code':'"+device.getTerminalId()+ "'},");

		}
		String data = dataJson.substring(0, dataJson.length() - 1) + "]";
		if (linkDeviceList.size() != 0) {
			return "{'success':true," + "'total':'" + linkDeviceList.size()
					+ "','message':" + message.toString() + "," + data + "}";
		} else {
			return "{'success':true," + "'total':'" + linkDeviceList.size()
					+ "','message':" + message.toString() + "}";
		}

	}

	@RequestMapping(value = "delFile", method = RequestMethod.POST)
	public @ResponseBody
	void upload(WebRequest webRequest, String type,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		File file = null;
		file = new File(request.getSession().getServletContext()
				.getRealPath("/importResult.xls"));
		file.delete();
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void download(WebRequest webRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file = new File(request.getSession().getServletContext()
				.getRealPath("/importResult.xls"));
		response.setHeader("Content-Disposition", "attachment; filename="
				+ file.getName());
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/x-msdownload;charset=UTF-8");
		OutputStream out = null;
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		try {
			int len = 0;
			out = response.getOutputStream();
			long contentLength = 0;
			contentLength = contentLength + randomFile.length();
			randomFile.seek(0);
			byte[] cache = new byte[1024];
			while ((len = randomFile.read(cache)) != -1) {
				out.write(cache, 0, len);
				contentLength += len;
			}
			out.close();
			randomFile.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
			if (randomFile != null) {
				randomFile.close();
			}
		}
	}
	/**
	 * 解除关联关系：
	 *
	 * @param personId
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/unlink", method = RequestMethod.POST)
	public @ResponseBody
	@MethodNameDescrible(describle="userlog.openPlanController.unlink",hasLogKey=true)
	ModelMap unlink(@RequestParam String planId, @RequestParam String deviceId) {
		ModelMap result = new ModelMap();
		String[] ids = deviceId.split(",");
		int i = 0;
		try {
			IDeviceOpenPlan openPlan = openPlanService.getDeviceOpenPlanById(Long.parseLong(planId));
			result.addAttribute(FishConstant.LOG_KEY, openPlan.getName());
			for (String id : ids) {
				relationService.unlink(openPlan, deviceService.get(Long.valueOf(id)));
			}
		} catch (Exception ex) {
			logger.error(String.format("Exception is %s", ex.getMessage()));
			i++;
		}
		if (i > 0) {
			result.put(FishConstant.SUCCESS, false);
			result.put("errors", i);
		} else {
			result.put(FishConstant.SUCCESS, true);
		}
		return result;
	}

	/**
	 * 建立关联关系：
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/link", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.openPlanController.link",hasLogKey=true)
	public @ResponseBody
	ModelMap link(@RequestParam String planId, @RequestParam String deviceId) {
		logger.info(String.format("device %s linked  %s", planId, deviceId));
		ModelMap result = new ModelMap();
		int i = 0;
		IDeviceOpenPlan devicePlan = openPlanService.getDeviceOpenPlanById(Long.parseLong(planId));
		result.addAttribute(FishConstant.LOG_KEY,devicePlan.getName());
		String[] ids = deviceId.split(",");
		for (String id : ids) {
			try {
				IDevice device = deviceService.get(Long.valueOf(id));
				List<IDeviceOpenPlan> plans = new ArrayList<IDeviceOpenPlan>();
				if(device != null)
				{
				 plans = relationService.listPlanByDevice(device);
				}
				boolean flag = true;
				for (IDeviceOpenPlan plan : plans) {
					if (!devicePlan.getStartDate().after(plan.getStartDate()) && !plan.getStartDate().after(devicePlan.getEndDate())) {
						flag = false;
						break;
					}
					if (!devicePlan.getStartDate().after(plan.getEndDate()) && !devicePlan.getEndDate().before(plan.getEndDate())) {
						flag = false;
						break;
					}
					if (!devicePlan.getStartDate().before(plan.getStartDate()) && !devicePlan.getEndDate().after(plan.getEndDate())) {
						flag = false;
						break;
					}
					if (!devicePlan.getStartDate().after(plan.getStartDate()) && !devicePlan.getEndDate().before(plan.getEndDate())) {
						flag = false;
						break;
					}
				}
				if (flag) {
					if(device != null)
					{
						relationService.link(devicePlan, device);
					}
					else
					{
						i++;
					}

				} else {
					i++;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				i++;
			}
		}
		if (i > 0) {
			result.put(FishConstant.SUCCESS, false);
			result.put("errors", i);
		} else {
			result.put(FishConstant.SUCCESS, true);
		}
		return result;
	}


	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			}
			String value = request.getParameter(name);
			if (value == null || value.isEmpty()) {
				continue;
			}

			if ("devType".equals(name)) {
				filter.eq("devType", Long.valueOf(value));
			} else if ("terminalId".equals(name)) { // 类型
				filter.like("terminalId", value + "%");
			} else if ("ip".equals(name)) {// 品牌
				try {
					filter.eq("ip", new IP(value));
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "orgName".equals(name) || "organizationID".equals(name) || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name) || "orgId".equals(name)
				|| "flag".equals(name) || "sort".equals(name);
	}

	private boolean isExistCode(long id, String name) {
		IFilter filter = new Filter();
		filter.eq("name", name);
		for (IDeviceOpenPlan open : openPlanService.list(filter)) {
			if (open.getId() != id) {
				return true;
			}
		}
		return false;
	}
	
	public List<DeviceForm> convert(List<IDevice> list) {
		List<DeviceForm> result = new ArrayList<DeviceForm>();
		for (IDevice item : list) {
			result.add(toFrom(item));
		}
		return result;
	}
	/**
	 * 将接口数据保存至本地
	 *
	 * @param device
	 *            接口
	 * @param isDate
	 *            是否需要转换日期
	 */
	public DeviceForm toFrom(IDevice device) {
		DeviceForm deviceForm = new DeviceForm();
		deviceForm.setAddress(device.getAddress());
		deviceForm.setCashboxLimit(device.getCashboxLimit());

		deviceForm.setAwayFlag(device.getAwayFlag() == null ? null : String.valueOf(device.getAwayFlag().getId()));
		deviceForm.setAwayFlagName(device.getAwayFlag() == null ? null : getI18N(device.getAwayFlag().getText()));
		deviceForm.setSetupType(device.getSetupType() == null ? null : String.valueOf(device.getSetupType().getId()));
		deviceForm.setSetupTypeName(device.getSetupType() == null ? null : getI18N(device.getSetupType().getText()));
		deviceForm.setWorkType(device.getWorkType() == null ? null : String.valueOf(device.getWorkType().getId()));
		deviceForm.setVirtual(device.getVirtual());
		deviceForm.setSerial(device.getSerial());
		deviceForm.setNetType(device.getNetType() == null ? String.valueOf(NetType.CABLE.getId()) : String.valueOf(device.getNetType().getId()));
		if (device.getDevService() != null) {
			deviceForm.setDevServiceName(device.getDevService().getName());
			deviceForm.setDevServiceId(device.getDevService().getGuid());
		}

		if (device.getDevType() != null) {
			deviceForm.setDevTypeId(device.getDevType().getId());
			deviceForm.setDevTypeName(device.getDevType().getName());
			deviceForm.setDevCatalogName(device.getDevType().getDevCatalog().getName());
			deviceForm.setDevVendorName(device.getDevType().getDevVendor().getName());
		}
		deviceForm.setId(String.valueOf(device.getId()));
		deviceForm.setIp(device.getIp().toString());
		if (device.getOrganization() != null) {
			deviceForm.setOrgId(device.getOrganization().getGuid());
			deviceForm.setOrgName(device.getOrganization().getName());
		}
		deviceForm.setStatus(device.getStatus() == null ? null : String.valueOf(device.getStatus().getId()));
		deviceForm.setStatusName(device.getStatus() == null ? null : getI18N(device.getStatus().getText()));
		deviceForm.setTerminalId(device.getTerminalId());
		deviceForm.setInstallDate(device.getInstallDate() != null ? DateUtils.getDate(device.getInstallDate()) : "");
		return deviceForm;
	}
	
	private String getI18N(String code){
		return messageSource.getMessage(code, null, FishCfg.locale);
	}
}
