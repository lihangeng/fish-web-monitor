package com.yihuacomputer.fish.web.machine.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
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
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.FishWebUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDeviceRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.machine.entity.DeviceOpenPlan;
import com.yihuacomputer.fish.machine.entity.OpenPlanDetail;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.machine.form.ExportOpenPlanForm;
import com.yihuacomputer.fish.web.machine.form.OpenPlanDetailForm;
import com.yihuacomputer.fish.web.machine.form.OpenPlanForm;

/**
 * 开机方案的控制
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping(value = "/plan")
public class OpenPlanController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(OpenPlanController.class);

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

//	@Autowired
//	private TempDeviceService tempDeviceService;

	@Autowired
	private IOpenPlanDeviceRelation relationService;

//	@Autowired
	//private ITempOpenPlanDevRelation tempOpenPlanDevRelation;

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
						filter.addFilterEntry(FilterFactory.like("name", request.getParameter(name)));
					} else if ("startDate".equals(name)) {
						filter.addFilterEntry(FilterFactory.eq("startDate", DateUtils.getDate(request.getParameter(name))));
					} else if ("endDate".equals(name)) {
						filter.addFilterEntry(FilterFactory.eq("endDate", DateUtils.getDate(request.getParameter(name))));
					}
				}
			}
		}

		// 根据创建时间降序排列
		filter.addOrder(new OrderBy("createDateTime", OrderBy.DESC));
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
			System.out.println("-----------"+form.getPlanStateType());
			forms.add(form);
		}
		return forms;
	}
	private String getEnumI18n(String enumText){
		 System.out.println(enumText);
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
			forms.add(form);
		}
		return forms;
	}

	@RequestMapping(value = "/exportPlan", method = RequestMethod.GET)
	public @ResponseBody
	void orgImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

		IFilter filter = new Filter();
		Iterator<String> iterator = webRequest.getParameterNames();
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
						filter.addFilterEntry(FilterFactory.like("name", request.getParameter(name)));
					} else if ("startDate".equals(name)) {
						filter.addFilterEntry(FilterFactory.eq("startDate", DateUtils.getDate(request.getParameter(name))));
					} else if ("endDate".equals(name)) {
						filter.addFilterEntry(FilterFactory.eq("endDate", DateUtils.getDate(request.getParameter(name))));
					}
				}
			}
		}

		// 根据创建时间降序排列
		filter.addOrder(new OrderBy("planState", OrderBy.DESC));
		filter.addOrder(new OrderBy("endDate", OrderBy.ASC));
		List<IDeviceOpenPlan> deviceOpenPlanList = openPlanService.list(filter);
		List<Long> plan_Dev = new ArrayList<Long>();
		List<ExportOpenPlanForm> data = toExportPlanForm(deviceOpenPlanList, plan_Dev);

//		ExportOpenPlan excelExport = new ExportOpenPlan();
//		excelExport.exportOpenPlan(data, plan_Dev, response);
	}

	private List<ExportOpenPlanForm> toExportPlanForm(List<IDeviceOpenPlan> plans, List<Long> plan_Dev) {
		List<ExportOpenPlanForm> forms = new ArrayList<ExportOpenPlanForm>();
		for (IDeviceOpenPlan plan : plans) {
			long planDev_number = 0;
			int deviceCount = openPlanService.deviceCount(plan.getId());
			plan.setDeviceCount(deviceCount);
			List<Object> devList = openPlanService.deviceInfo(plan.getId());
			if (devList.size() == 0) {
				planDev_number++;
				List<IOpenPlanDetail> planDetails = new ArrayList<IOpenPlanDetail>();
				planDetails = openPlanService.getOpenPlanDetialById(plan.getId());
				ExportOpenPlanForm exportOpenPlanForm = new ExportOpenPlanForm(plan, null, planDetails);
				forms.add(exportOpenPlanForm);
				plan_Dev.add(planDev_number);
				continue;

			}
			for (Object devInfo : devList) {
				planDev_number++;
				Object[] objDev = (Object[]) devInfo;
				List<IOpenPlanDetail> planDetails = new ArrayList<IOpenPlanDetail>();
				planDetails = openPlanService.getOpenPlanDetialById(plan.getId());
				ExportOpenPlanForm exportOpenPlanForm = new ExportOpenPlanForm(plan, objDev, planDetails);
				forms.add(exportOpenPlanForm);
			}
			plan_Dev.add(planDev_number);
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

	@RequestMapping(value = "/tempdevopenplan", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchTempDevPlan(@RequestParam int start, @RequestParam int limit, WebRequest request){
		ModelMap result = new ModelMap();
		logger.info(String.format("search searchTempDevPlan : start = %s ,limit = %s ", start, limit));
		Long deviceId = Long.valueOf(request.getParameter("deviceId"));
		List<IDeviceOpenPlan> pageResult  = new ArrayList<IDeviceOpenPlan>();
		IDevice device = deviceService.get(deviceId);
		String terminalId = null;
		String strDeviceId = null;
		if (device != null) {//TODO ....pageResult = tempOpenPlanDevRelation.listPlanByDevice(deviceId);
//			pageResult = tempOpenPlanDevRelation.listPlanByDevice(deviceId);
		    strDeviceId = deviceId + "";
			terminalId = device.getTerminalId();
		}
        result.addAttribute("success",true);
        result.addAttribute("total",pageResult.size());
    	result.addAttribute("data", toPlanForm(pageResult, strDeviceId, terminalId));
        return result;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchPlanDetail(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search plan detail: start = %s ,limit = %s ", start, limit));
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		List<IOpenPlanDetail> planDetails = new ArrayList<IOpenPlanDetail>();
		planDetails = openPlanService.getOpenPlanDetialById(Long.parseLong(request.getParameter("openPlanId")));
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
		List<IOpenPlanDetail> planDetails = new ArrayList<IOpenPlanDetail>();
		planDetails = openPlanService.getOpenPlanDetialById(openPlanId);
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
	public @ResponseBody
	ModelMap update(@PathVariable String id, @RequestBody OpenPlanForm form) {
		logger.info(" update plan : plan.id = " + id);
		IFilter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("openPlanId", form.getId()));
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
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.planExsit", null,FishCfg.locale));
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
			IPageResult<IDevice> pageResult = relationService.pageDeviceByPlan(0, 25, openPlan, new Filter(), String.valueOf(userSession.getOrgId()));
			// 是否与人员有关联
			if (pageResult != null && !pageResult.list().isEmpty()) {
				result.addAttribute("success", false);
				//该方案已应用于设备,无法删除！
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("servicePlan.cannotDelete", null, FishCfg.locale));
				return result;
			}

			openPlanService.deletePlan(id);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			result.addAttribute("success", false);
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
			result.addAttribute("data", DeviceForm.convert(pageResult.list()));
		} else {
			IFilter filter = new Filter();
			filter = request2filter(request);
			pageResult = relationService.pageUnlinkDeviceByPlan(start, limit, openPlanService.getDeviceOpenPlanById(Long.parseLong(planId)), filter, orgId);
			result.addAttribute("success", true);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", DeviceForm.convert(pageResult.list()));
		}
		return result;
	}


	@RequestMapping(value = "/uploadExcel",method = RequestMethod.POST)
	public @ResponseBody
	String upload(WebRequest webRequest, @RequestParam(value = "file") MultipartFile file,
			HttpServletResponse response,HttpServletRequest request){
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String orgId =String.valueOf(userSession.getOrgId());
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

/*	public  List<IDevice> importDevice(InputStream in, String path, String orgId, StringBuffer message) throws IOException
	{
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFWorkbook outWb =new HSSFWorkbook(fs);
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum < 1)
		{
			message.append("0");
			return null;
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		int number = sheet.getLastRowNum();
		if(number < 1 )
		{
			message.append("-1");
			return null;
		}
		if(number > 2000)
		{
			message.append("-2");
			return null;
		}
		String msg = String.valueOf(number);
		message.append(msg);
		List<IDevice> linkDevice = importSheet(sheet,outWb,orgId);
		in.close();
		outExcel(path, outWb);
		return linkDevice;
	}
*/
/*	private List<IDevice> importSheet(HSSFSheet st,HSSFWorkbook outWb,String orgId)
	{
		if( st == null)
		{
			return null;
		}
		String tip = null;
		boolean isWrite = false;
	    Map<String,String> devPlanRelation = new HashMap<String,String>();
	    List<IDevicePlanRelation> devicePlanRelations = relationService.devicePlanRelations();
	    List<IDevice> linkDevice = new ArrayList<IDevice>();
	    for(IDevicePlanRelation  dpr : devicePlanRelations)
	    {
	    	devPlanRelation.put(String.valueOf(dpr.getDeviceId()), String.valueOf(dpr.getOpenPlanId()));
	    }
		for(int rowIndex = 1; rowIndex<=st.getLastRowNum(); rowIndex++)
		{
		    if(isWrite == true)
		    {
		    	updateExcel(outWb, rowIndex - 1, tip, 0, 1);
		    }
		    HSSFRow row = st.getRow(rowIndex);
		    if(null == row)
		    {
		    	tip = "设备编号不能为空";
		    	isWrite = true;
		    	continue;
		    }
		    HSSFCell cell = row.getCell(0);
		    if(null == cell)
		    {
		    	tip = "设备编号不能为空";
		    	isWrite = true;
		        continue;
		    }
		    String deviceCode = getValue(row.getCell(0));
		    if(StringUtils.isBlank(deviceCode))
		    {
		    	tip ="设备编号不能为空";
		    	isWrite = true;
		    	continue;
		    }
		    List<IDevice> listDevice = relationService.getDevicebyCode(deviceCode, orgId);
            if(listDevice.size() > 0)
            {
            	String devId = String.valueOf(listDevice.get(0).getId());
            	if(devPlanRelation.get(devId) != null)
            	{
            		tip ="该设备不存在或不满足要求";
            		isWrite = true;
    		    	continue;
            	}
            	 else
                 {
                 	linkDevice.add(listDevice.get(0));
                 	tip ="导入成功";
                 	isWrite = true;
                 }
            }else
            {
            	tip ="该设备不存在或不满足要求";
        		isWrite = true;
		    	continue;
            }


          }
		   updateExcel(outWb, st.getLastRowNum(), tip, 0, 1);
		   return linkDevice;

	}
*/
	private String getValue(HSSFCell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.toString().trim();
		}
		return null;
	}

	private void outExcel(String path, HSSFWorkbook wb) throws IOException {
		FileOutputStream os = new FileOutputStream(path);
		wb.write(os);
		os.flush();
		os.close();
	}

	private void updateExcel(HSSFWorkbook wb, int rowIndex, String tip,
			int sheetNum, int cellNum) {
		HSSFSheet sheet = wb.getSheetAt(sheetNum);
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		HSSFCell cell = row.createCell(cellNum);
		cell.setCellValue(tip);
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
		} catch (Exception ex) {
			ex.printStackTrace();
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
	ModelMap unlink(@RequestParam String planId, @RequestParam String deviceId) {
		ModelMap result = new ModelMap();
		String[] ids = deviceId.split(",");
		int i = 0;
		try {
			for (String id : ids) {
				relationService.unlink(openPlanService.getDeviceOpenPlanById(Long.parseLong(planId)), deviceService.get(Long.valueOf(id)));
			}
		} catch (Exception ex) {
			i++;
		}
		if (i > 0) {
			result.put("success", false);
			result.put("errors", i);
		} else {
			result.put("success", true);
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
	public @ResponseBody
	ModelMap link(@RequestParam String planId, @RequestParam String deviceId) {
		logger.info(String.format("device %s linked  %s", planId, deviceId));
		ModelMap result = new ModelMap();
		int i = 0;
		IDeviceOpenPlan devicePlan = openPlanService.getDeviceOpenPlanById(Long.parseLong(planId));
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
				e.printStackTrace();
				i++;
			}
		}
		if (i > 0) {
			result.put("success", false);
			result.put("errors", i);
		} else {
			result.put("success", true);
		}
		return result;
	}

//	@RequestMapping(value = "/tempdevlinkplan", method = RequestMethod.POST)
//	public @ResponseBody
//	ModelMap tempDevLinkPlan (@RequestParam String planId, @RequestParam String tempDeviceId)
//	{
//     logger.info(String.format("tempdevice %s linked  %s", planId, tempDeviceId));
//     ModelMap result = new ModelMap();
//     IDeviceOpenPlan devicePlan = openPlanService.getDeviceOpenPlanById(Long.parseLong(planId));
//     TempDevice tempDev = tempDeviceService.get(Long.valueOf(tempDeviceId));
//     try {
//     if(tempDev != null && devicePlan != null)
//        {
//    	 tempOpenPlanDevRelation.link(devicePlan, tempDev.getId());
//        }
//     }
//     catch (Exception e) {
//			e.printStackTrace();
//			result.put("success", false);
//     }
//     result.put("success", true);
//     return result;
//	}


//	@RequestMapping(value = "/tempunlinkplan", method = RequestMethod.POST)
//	public @ResponseBody
//	ModelMap tempDevUnLinkPlan (@RequestParam String planId, @RequestParam String tempDeviceId)
//	{
//     logger.info(String.format("tempdevice %s linked  %s", planId, tempDeviceId));
//     ModelMap result = new ModelMap();
//     IDeviceOpenPlan devicePlan = openPlanService.getDeviceOpenPlanById(Long.parseLong(planId));
//     TempDevice tempDev = tempDeviceService.get(Long.valueOf(tempDeviceId));
//     try {
//     if(tempDev != null && devicePlan != null)
//        {
//    	 tempOpenPlanDevRelation.unlink(devicePlan, tempDev.getId());
//        }
//     }
//     catch (Exception e) {
//			e.printStackTrace();
//			result.put("success", false);
//     }
//     result.put("success", true);
//     return result;
//	}

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
				filter.addFilterEntry(FilterFactory.eq("devType", Long.valueOf(value)));
			} else if ("terminalId".equals(name)) { // 类型
				filter.addFilterEntry(FilterFactory.like("terminalId", value + "%"));
			} else if ("ip".equals(name)) {// 品牌
				try {
					filter.addFilterEntry(FilterFactory.eq("ip", new IP(value)));
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
		filter.addFilterEntry(FilterFactory.eq("name", name));
		for (IDeviceOpenPlan open : openPlanService.list(filter)) {
			if (open.getId() != id) {
				return true;
			}
		}
		return false;
	}
}
