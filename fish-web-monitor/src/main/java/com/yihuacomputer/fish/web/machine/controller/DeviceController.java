package com.yihuacomputer.fish.web.machine.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.api.device.IDeviceExtendService;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.web.machine.form.AtmTypeForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonForm;

@Controller
@RequestMapping("/machine/device")
public class DeviceController {

	private Logger logger = LoggerFactory.getLogger(DeviceController.class);

	/**
	 * 设备接口
	 */
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceExtendService deviceExtendService;
	
	@Autowired
	private IVersionTypeAtmTypeRelationService versionAtmTypeService;

	/**
	 * 机构接口
	 */
	@Autowired
	private IOrganizationService orgService;

	/**
	 * 型号接口
	 */
	@Autowired
	private IAtmTypeService typeService;

	@Autowired
	private IDevicePersonRelation devicePersonRelation;

	@PostConstruct
	public void init() {
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody DeviceForm request) {
		logger.info("add Device");
		ModelMap model = new ModelMap();
		IDevice device = deviceService.make();
		IDeviceExtend de = deviceExtendService.make();

		IOrganization org = orgService.get(request.getOrgId());
		if (org == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "机构不存在.");
			return model;
		}

		IOrganization serviceOrg = orgService.get(request.getDevServiceId());
		if (serviceOrg == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "维护商不存在.");
			return model;
		}

		IAtmType atmType = typeService.get(request.getDevTypeId());
		if (atmType == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "设备型号不存在.");
			return model;
		}

		device.setOrganization(org);
		device.setDevService(serviceOrg);
		device.setDevType(atmType);
		device.setDeviceExtend(de);
		request.translate(device);

		Map<String, Object> result = validator(request, "add");

		if ((Boolean) result.get("validator")) {

			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, result.get("errorMsg"));

			return model;
		}

		try {
			deviceService.add(device);
		} catch (Exception e) {
			logger.error(String.format("增加设备失败!错误信息[%s]", e));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "后台处理出错.");
			return model;
		}

		model.addAttribute(FishConstant.DATA, new DeviceForm(device));

		return model;
	}

	/**
	 *
	 * 根据ID删除设备
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete device: device.id = " + id);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);

		try {
			deviceService.remove(id);
		} catch (ServiceException se) {

			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, se.getMessage());

		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "后台处理出错.");
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据GUID更新用户
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody DeviceForm request) {
		logger.info("update Device: device.id = " + id);
		ModelMap model = new ModelMap();

		IDevice device = deviceService.get(id);
		if (device == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, "记录不存在,请刷新后操作.");

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
			return model;
		}

		device = deviceService.get(id);

		IOrganization org = orgService.get(request.getOrgId());
		if (org == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "机构不存在.");
			return model;
		}

		IOrganization serviceOrg = orgService.get(request.getDevServiceId());
		if (serviceOrg == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "维护商不存在.");
			return model;
		}

		IAtmType atmType = typeService.get(request.getDevTypeId());
		if (atmType == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "设备型号不存在.");
			return model;
		}

		device.setDevService(serviceOrg);
		device.setOrganization(org);
		device.setDevType(atmType);

		request.translate(device);

		try {
			deviceService.update(device);
		} catch(Exception e) {
			logger.error(String.format("更改设备失败!错误信息[%s]", e));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", "后台处理出错.");
			return model;
		}

		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA, new DeviceForm(device));
		return model;
	}

	/**
	 *
	 * 根据条件得到设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// IFilter filter = new Filter();
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		StringBuffer hql = new StringBuffer();

		List<Object> fixedFilters = request2filter(webRequest, hql, organization);

		// 获得机构下所有的设备信息
		List<IDevice> data = deviceService.list(hql.toString(), fixedFilters);

		String path = createExls(data, "设备信息");

		File file = new File(path);

		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + getFileName(request, path.substring(path.lastIndexOf(File.separator)))
						+ "\"");
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/x-msdownload;charset=UTF-8");
		OutputStream out = null;
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		try {
			out = response.getOutputStream();
			int len = 0;
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

	private String getFileName(HttpServletRequest request, String name) throws Exception {
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		} else {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		}
	}

	private String createExls(List<IDevice> data, String sheetName) {

		String pathname = FishCfg.getTempDir() + File.separator + DateUtils.getDate(new Date()) + ".xls";

		HSSFWorkbook workBook = new HSSFWorkbook();

		HSSFSheet sheet = workBook.createSheet(sheetName);

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");

		cell = row.createCell(1);
		cell.setCellValue("设备号");

		cell = row.createCell(2);
		cell.setCellValue("设备IP地址");

		cell = row.createCell(3);
		cell.setCellValue("设备状态");

		cell = row.createCell(4);
		cell.setCellValue("设备维护商");

		cell = row.createCell(5);
		cell.setCellValue("所属机构");

		cell = row.createCell(6);
		cell.setCellValue("设备型号");

		cell = row.createCell(7);
		cell.setCellValue("钞箱报警金额");

		cell = row.createCell(8);
		cell.setCellValue("设备地址");

		cell = row.createCell(9);
		cell.setCellValue("设备序列号");

		cell = row.createCell(10);
		cell.setCellValue("运营商");

		cell = row.createCell(11);
		cell.setCellValue("加钞机构");

		cell = row.createCell(12);
		cell.setCellValue("资金成本利率");

		cell = row.createCell(13);
		cell.setCellValue("atmc软件");

		cell = row.createCell(14);
		cell.setCellValue("厂商sp类型");

		cell = row.createCell(15);
		cell.setCellValue("购买日期");

		cell = row.createCell(16);
		cell.setCellValue("安装日期");

		cell = row.createCell(17);
		cell.setCellValue("启用日期");

		cell = row.createCell(18);
		cell.setCellValue("停用日期");

		cell = row.createCell(19);
		cell.setCellValue("保修到期日期");

		cell = row.createCell(20);
		cell.setCellValue("每日开机时间");

		cell = row.createCell(21);
		cell.setCellValue("每日关机时间");

		cell = row.createCell(22);
		cell.setCellValue("上次巡检日期");

		cell = row.createCell(23);
		cell.setCellValue("巡检到期日期");

		cell = row.createCell(24);
		cell.setCellValue("入账成本(元)");

		cell = row.createCell(25);
		cell.setCellValue("折旧年限(年)");

		cell = row.createCell(26);
		cell.setCellValue("装修费用");

		cell = row.createCell(27);
		cell.setCellValue("装修摊销年限(年)");

		cell = row.createCell(28);
		cell.setCellValue("物业租赁费用(元/月)");

		cell = row.createCell(29);
		cell.setCellValue("物业管理费用(元/月)");

		cell = row.createCell(30);
		cell.setCellValue("通讯线路费用(元/月)");

		cell = row.createCell(31);
		cell.setCellValue("电费(元/月)");

		cell = row.createCell(32);
		cell.setCellValue("加钞维护费用(元/月)");

		cell = row.createCell(33);
		cell.setCellValue("设备关注程度");

		cell = row.createCell(34);
		cell.setCellValue("非现金标志");

		cell = row.createCell(35);
		cell.setCellValue("安装方式");

		cell = row.createCell(36);
		cell.setCellValue("网络类型");

		cell = row.createCell(37);
		cell.setCellValue("在行离行标志");

		cell = row.createCell(38);
		cell.setCellValue("经营方式");

		HSSFCellStyle cellStyle = workBook.createCellStyle();
		HSSFDataFormat format = workBook.createDataFormat();

		cellStyle.setDataFormat(format.getFormat("@"));

		int count = 1;
		for (IDevice device : data) {

			row = sheet.createRow(count);

			cell = row.createCell(0);
			cell.setCellValue(count++);

			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellValue(device.getTerminalId()));

			cell = row.createCell(2);
			cell.setCellValue(cellValue(device.getIp() == null ? "" : device.getIp().toString()));

			cell = row.createCell(3);
			cell.setCellValue(cellValue(device.getStatus() == null ? "" : device.getStatus().getText()));

			cell = row.createCell(4);
			cell.setCellValue(cellValue(device.getDevService() == null ? "" : device.getDevService().getName()));

			cell = row.createCell(5);
			cell.setCellValue(cellValue(device.getOrganization() == null ? "" : device.getOrganization().getName()));

			cell = row.createCell(6);
			cell.setCellValue(cellValue(device.getDevType() == null ? "" : device.getDevType().getName()));

			cell = row.createCell(7);
			cell.setCellValue(cellValue(device.getCashboxLimit()));

			cell = row.createCell(8);
			cell.setCellValue(cellValue(device.getAddress()));

			cell = row.createCell(13);
			cell.setCellValue(cellValue(device.getAtmcSoft()));

			cell = row.createCell(14);
			cell.setCellValue(cellValue(device.getSp()));

			if (device.getDeviceExtend() != null) {

				cell = row.createCell(9);
				cell.setCellValue(cellValue(device.getDeviceExtend().getSerial()));

				cell = row.createCell(10);
				cell.setCellValue(cellValue(device.getDeviceExtend().getCarrier()));

				cell = row.createCell(11);
				cell.setCellValue(cellValue(device.getDeviceExtend().getMoneyOrg()));

				cell = row.createCell(12);
				cell.setCellValue(cellValue(device.getDeviceExtend().getCostInterest()));

				cell = row.createCell(15);
				cell.setCellValue(cellValue(device.getDeviceExtend().getBuyDate()));

				cell = row.createCell(16);
				cell.setCellValue(cellValue(device.getDeviceExtend().getInstallDate()));

				cell = row.createCell(17);
				cell.setCellValue(cellValue(device.getDeviceExtend().getStartDate()));

				cell = row.createCell(18);
				cell.setCellValue(cellValue(device.getDeviceExtend().getStopDate()));

				cell = row.createCell(19);
				cell.setCellValue(cellValue(device.getDeviceExtend().getExpireDate()));

				cell = row.createCell(20);
				cell.setCellValue(cellValue(device.getDeviceExtend().getOpenTime()));

				cell = row.createCell(21);
				cell.setCellValue(cellValue(device.getDeviceExtend().getCloseTime()));

				cell = row.createCell(22);
				cell.setCellValue(cellValue(device.getDeviceExtend().getLastPmDate()));

				cell = row.createCell(23);
				cell.setCellValue(cellValue(device.getDeviceExtend().getExpirePmDate()));

				cell = row.createCell(24);
				cell.setCellValue(cellValue(device.getDeviceExtend().getPrice()));

				cell = row.createCell(25);
				cell.setCellValue(cellValue(device.getDeviceExtend().getDepreciationLife()));

				cell = row.createCell(26);
				cell.setCellValue(cellValue(device.getDeviceExtend().getDecoration()));

				cell = row.createCell(27);
				cell.setCellValue(cellValue(device.getDeviceExtend().getDecorationCost()));

				cell = row.createCell(28);
				cell.setCellValue(cellValue(device.getDeviceExtend().getGovernanceRent()));

				cell = row.createCell(29);
				cell.setCellValue(cellValue(device.getDeviceExtend().getGovernanceCost()));

				cell = row.createCell(30);
				cell.setCellValue(cellValue(device.getDeviceExtend().getNetCost()));

				cell = row.createCell(31);
				cell.setCellValue(cellValue(device.getDeviceExtend().getPowerCost()));

				cell = row.createCell(32);
				cell.setCellValue(cellValue(device.getDeviceExtend().getMoneyCost()));

				NetType netType = device.getDeviceExtend().getNetType();
				cell = row.createCell(36);
				cell.setCellValue(cellValue(netType == null ? "" : netType.getText()));

			}

			cell = row.createCell(33);
			cell.setCellValue(cellValue(device.getCareLevel() == null ? "" : device.getCareLevel().getText()));

			cell = row.createCell(34);
			cell.setCellValue(cellValue(device.getCashType() == null ? "" : device.getCashType().getText()));

			cell = row.createCell(35);
			cell.setCellValue(cellValue(device.getSetupType() == null ? "" : device.getSetupType().getText()));

			cell = row.createCell(37);
			cell.setCellValue(cellValue(device.getAwayFlag() == null ? "" : device.getAwayFlag().getText()));

			cell = row.createCell(38);
			cell.setCellValue(cellValue(device.getWorkType() == null ? "" : device.getWorkType().getText()));

		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pathname);
			workBook.write(fos);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
		return pathname;
	}

	private String cellValue(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			return obj.toString();
		} else if (obj instanceof Date) {
			return DateUtils.getDate((Date) obj);
		} else if (obj instanceof Integer || obj instanceof Long || obj instanceof Double) {
			return String.valueOf(obj.toString());
		}
		return obj.toString();
	}

	/**
	 *
	 * 根据条件得到设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(webRequest);

		ModelMap result = new ModelMap();

		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		// 获得机构下所有的设备信息
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter, organization);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, DeviceForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/findMatch", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchMatch(@RequestParam int start, @RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = new Filter();
		if(null != request.getParameter("query")){
			filter.like("terminalId", request.getParameter("query").toString()+"%");
		}

		ModelMap result = new ModelMap();

		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		// 获得机构下所有的设备信息
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter, organization);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL,  pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, DeviceForm.convert(pageResult.list()));
		return result;
	}


	@RequestMapping(value = "/findByOrg", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap findByOrg(@RequestParam int start, @RequestParam int limit, @RequestParam String organizationID,
			WebRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();

		filter.like("organization.orgFlag", "%" + organizationID);

		// 获得机构下所有的设备信息organizationID
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, DeviceForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/findByService", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap findByService(@RequestParam int start, @RequestParam int limit, @RequestParam String organizationID,
			WebRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();

		filter.like("devService.orgFlag", "%" + organizationID);

		// 获得机构下所有的设备信息organizationID
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, DeviceForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "findByTerminalId", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam String terminalId) {
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new DeviceForm(deviceService.get(terminalId)));
		return result;
	}

	/**
	 * 获取所有品牌信息
	 *
	 * @param id
	 *            设备的ID
	 * @return
	 */
	@RequestMapping(value = "/queryAtmType", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryAtmType(HttpServletRequest request) {
		logger.info(String.format("search device : queryAtmType"));
		//版本关联机型品牌信息
		String versionId = request.getParameter("versionId");
		List<Long> atmTypeIdList = null;
		if(null!=versionId){
			atmTypeIdList = versionAtmTypeService.getAtmTypeIdsByVersionId(Long.parseLong(versionId));
		}
		ModelMap model = new ModelMap();
		List<IAtmType> atmTypeList = null;
		if(null==atmTypeIdList){
			atmTypeList = typeService.list();
		}
		else{
			IFilter filter = new Filter();
			filter.in("id", atmTypeIdList);
			atmTypeList =typeService.list(filter);
		}

		model.put(FishConstant.SUCCESS, true);
		model.put(FishConstant.DATA, AtmTypeForm.convert(atmTypeList));

		return model;
	}

	private Map<String, Object> validator(DeviceForm form, String action) {
		Map<String, Object> result = new HashMap<String, Object>();

		boolean validatorTi = false;
		StringBuffer errorMsg = new StringBuffer();

		IDevice device = deviceService.get(form.getTerminalId());
		result.put("validator", false);
		if (device != null) {
			// 该设备号已经被使用
			validatorTi = true;
			errorMsg.append("设备号重复.<BR>");
		}

		result.put("validator", validatorTi);
		result.put("errorMsg", errorMsg.toString());
		return result;
	}

	/**
	 * 查询设备对应的人员
	 *
	 * @param terminalId
	 *            设备号
	 * @param type
	 *            0管机员,1维护员
	 * @return
	 */
	@RequestMapping(value = "/queryPerson", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryPerson(@RequestParam String terminalId, @RequestParam int type) {
		logger.info(String.format("search device : validatorTerminalId"));
		ModelMap model = new ModelMap();
		List<IPerson> personList = devicePersonRelation.listPersonByDevice(terminalId);
		List<IPerson> newPersonList = new ArrayList<IPerson>();
		for (IPerson person : personList) {
			if (person.getType().getId() == type) {
				newPersonList.add(person);
			}
		}
		model.put(FishConstant.SUCCESS, true);
		model.put(FishConstant.DATA, PersonForm.convert(newPersonList));

		return model;
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

			if ("startCashboxLimit".equals(name)) {
				filter.ge("device.cashboxLimit", Integer.valueOf(value));
			} else if ("endCashboxLimit".equals(name)) {
				filter.le("device.cashboxLimit", Integer.valueOf(value));
			} else if ("startInstallDate".equals(name)) {

				filter.ge("device.deviceExtend.installDate", DateUtils.getDate(value));

			} else if ("endInstallDate".equals(name)) {

				filter.le("device.deviceExtend.installDate", DateUtils.getDate(value));

			} else if ("devType".equals(name)) {// 型号

				filter.eq("device.devType.id", Long.valueOf(value));

			} else if ("devCatalogId".equals(name)) { // 类型

				filter.eq("device.devType.devCatalog.id", Long.valueOf(value));

			} else if ("devVendorId".equals(name)) {// 品牌

				filter.eq("device.devType.devVendor.id", Long.valueOf(value));

			} else if ("devService".equals(name)) {
				IOrganization org = orgService.get(value);

				filter.like("device.devService.orgFlag", org.getOrgFlag());

			} else if ("organization".equals(name)) {
				IOrganization org = orgService.get(value);

				filter.like("device.organization.orgFlag", org.getOrgFlag());

			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				filter.eq("device.ip", ip);
			} else if ("awayFlag".equals(name)) {
				filter.eq("device.awayFlag", AwayFlag.getById(Integer.valueOf(value)));
			} else {
				filter.like("device." + name, value);
			}
		}

		return filter;
	}

	private List<Object> request2filter(WebRequest request, StringBuffer hql, String organization) {
		List<Object> fixedFilters = new ArrayList<Object>();
		hql.append("from Device device where 1=1 and device.organization.orgFlag like ? ");
		IOrganization org = orgService.get(organization);
		fixedFilters.add("%" + org.getOrgFlag());

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

			if ("startCashboxLimit".equals(name)) {
				hql.append(" and device.cashboxLimit >= ?");
				fixedFilters.add(Integer.valueOf(value));
			} else if ("endCashboxLimit".equals(name)) {
				hql.append(" and device.cashboxLimit <= ?");
				fixedFilters.add(Integer.valueOf(value));
			} else if ("startInstallDate".equals(name)) {
				hql.append(" and device.deviceExtend.installDate >= ?");
				fixedFilters.add(DateUtils.getDate(value));
			} else if ("endInstallDate".equals(name)) {
				hql.append(" and device.deviceExtend.installDate <= ?");
				fixedFilters.add(DateUtils.getDate(value));
			} else if ("devType".equals(name)) {// 型号
				hql.append(" and device.devType.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devCatalogId".equals(name)) { // 类型
				hql.append(" and device.devType.devCatalog.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devVendorId".equals(name)) {// 品牌
				hql.append(" and device.devType.devVendor.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devService".equals(name)) {
				hql.append(" and device.devService.orgFlag like ?");
				fixedFilters.add("%" + orgService.get(value).getOrgFlag());
			} else if ("organization".equals(name)) {
				hql.append(" and device.organization.orgFlag like ?");
				fixedFilters.add("%" + orgService.get(value).getOrgFlag());
			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				hql.append(" and device.ip = ?");
				fixedFilters.add(ip);
			} else if ("awayFlag".equals(name)) {
				hql.append(" and device.awayFlag = ?");
				fixedFilters.add(AwayFlag.getById(Integer.valueOf(value)));
			} else {
				hql.append(" and device.").append(name).append(" like ?");
				fixedFilters.add("%" + value + "%");
			}
		}
		return fixedFilters;
	}

	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name)
				|| "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}

}
