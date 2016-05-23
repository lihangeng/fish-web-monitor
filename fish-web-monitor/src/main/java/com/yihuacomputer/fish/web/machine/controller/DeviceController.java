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

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
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
	private IVersionTypeAtmTypeRelationService versionAtmTypeService;
	
	
	@Autowired
	protected MessageSource messageSource;

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

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody DeviceForm request) {
		logger.info("add Device");
		ModelMap model = new ModelMap();	
		IOrganization org = orgService.get(request.getOrgId());		
		if (org == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.orgNotExist", null, FishCfg.locale));
			return model;
		}

		IOrganization serviceOrg = orgService.get(request.getDevServiceId());
		if (serviceOrg == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.serNotExist", null, FishCfg.locale));
			return model;
		}

		IAtmType atmType = typeService.get(request.getDevTypeId());
		if (atmType == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.typeNotExist", null, FishCfg.locale));
			return model;
		}		  
		
		IDevice device = deviceService.make();
		device.setOrganization(org);
		device.setDevService(serviceOrg);
		device.setDevType(atmType);
		logger.info(request.getInstallDate());
		request.translate(device);
		/*if (request.getInstallDate() != null  && !"".equals(request.getInstallDate())&& !request.getInstallDate().equals(DateUtils.getDate(new Date()))) {
			device.setStatus(DevStatus.UNOPEN);
		}else{*/
			device.setStatus(DevStatus.OPEN);
		/*}*/
		Map<String, Object> result = validator(request, "add");
		if ((Boolean) result.get("validator")) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, result.get("errorMsg"));
			return model;
		}
		try {
			deviceService.add(device);
		} catch (Exception e) {
			logger.error(String.format(messageSource.getMessage("device.addError", null, FishCfg.locale), e));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
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
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
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
		request.setId(Long.toString(id));
		ModelMap model = new ModelMap();

		IDevice device = deviceService.get(id);		
		if (device == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
			return model;
		}

		IOrganization org = orgService.get(request.getOrgId());
		if (org == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.orgNotExist", null, FishCfg.locale));
			return model;
		}

		IOrganization serviceOrg = orgService.get(request.getDevServiceId());
		if (serviceOrg == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.serNotExist", null, FishCfg.locale));
			return model;
		}

		IAtmType atmType = typeService.get(request.getDevTypeId());
		if (atmType == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.typeNotExist", null, FishCfg.locale));
			return model;
		}
		device.setDevService(serviceOrg);
		device.setOrganization(org);
		device.setDevType(atmType);
		request.translate(device);
		try {
			deviceService.update(device);
		} catch(Exception e) {
			logger.error(String.format("add error : %s",e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
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

		String path = createExls(data, messageSource.getMessage("device.devinfo", null, FishCfg.locale));

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

	private String getFileName(HttpServletRequest request, String name) throws Exception {
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		} else {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		}
	}

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	private String createExls(List<IDevice> data, String sheetName) {

		String pathname = FishCfg.getTempDir() + File.separator + DateUtils.getDate(new Date()) + ".xls";

		HSSFWorkbook workBook = new HSSFWorkbook();

		HSSFSheet sheet = workBook.createSheet(sheetName);

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		
		cell = row.createCell(1);
		cell.setCellValue(messageSource.getMessage("device.devIp", null, FishCfg.locale));
		
		cell = row.createCell(2);
		cell.setCellValue(messageSource.getMessage("device.devOrg", null, FishCfg.locale));
		
		cell = row.createCell(3);
		cell.setCellValue(messageSource.getMessage("device.devType", null, FishCfg.locale));
		
		cell = row.createCell(4);
		cell.setCellValue(messageSource.getMessage("device.devVender", null,FishCfg.locale));
		
		
		cell = row.createCell(5);
		cell.setCellValue(messageSource.getMessage("device.devCatalog", null,FishCfg.locale));
		
		cell = row.createCell(6);
		cell.setCellValue(messageSource.getMessage("device.devStatus", null, FishCfg.locale));
		
		cell = row.createCell(7);
		cell.setCellValue(messageSource.getMessage("device.devInsideOutside", null, FishCfg.locale));
		
		cell = row.createCell(8);
		cell.setCellValue(messageSource.getMessage("device.devSer", null, FishCfg.locale));
		

		cell = row.createCell(9);
		cell.setCellValue(messageSource.getMessage("device.devAddress", null, FishCfg.locale));

		cell = row.createCell(10);
		cell.setCellValue(messageSource.getMessage("device.devInstallDate", null, FishCfg.locale));

		cell = row.createCell(11);
		cell.setCellValue(messageSource.getMessage("device.devInstallWay", null, FishCfg.locale));
		
		cell = row.createCell(12);
		cell.setCellValue(messageSource.getMessage("device.devCashWarn", null, FishCfg.locale));


		HSSFCellStyle cellStyle = workBook.createCellStyle();
		HSSFDataFormat format = workBook.createDataFormat();

		cellStyle.setDataFormat(format.getFormat("@"));

		int count = 1;
		for (IDevice device : data) {
			row = sheet.createRow(count);
			count++;
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellValue(device.getTerminalId()));

			cell = row.createCell(1);
			cell.setCellValue(cellValue(device.getIp() == null ? "" : device.getIp().toString()));

			cell = row.createCell(2);
			cell.setCellValue(cellValue(device.getOrganization() == null ? "" : device.getOrganization().getName()));

			cell = row.createCell(3);
			cell.setCellValue(cellValue(device.getDevType() == null ? "" : device.getDevType().getName()));

			cell = row.createCell(4);
			cell.setCellValue(cellValue(device.getDevType() == null ? "" : device.getDevType().getDevVendor().getName()));

			cell = row.createCell(5);
			cell.setCellValue(cellValue(device.getDevType() == null ? "" : device.getDevType().getDevCatalog().getName()));

			cell = row.createCell(6);
			cell.setCellValue(cellValue(device.getStatus()==null ? "" : getEnumI18n(device.getStatus().getText())));

			cell = row.createCell(7);
			cell.setCellValue(cellValue(device.getSetupType()==null ? "" : getEnumI18n(device.getAwayFlag().getText())));
			
			cell = row.createCell(8);
			cell.setCellValue(cellValue(device.getDevService().getName()==null?"":device.getDevService().getName()));
			
			cell = row.createCell(9);
			cell.setCellValue(cellValue(device.getAddress()==null?"":device.getAddress()));
			
			cell = row.createCell(10);
			cell.setCellValue(cellValue(device.getInstallDate()==null?"":device.getInstallDate()));
			
			cell = row.createCell(11);
			cell.setCellValue(cellValue(device.getSetupType()==null?"":getEnumI18n(device.getSetupType().getText())));

			cell = row.createCell(12);
			cell.setCellValue(cellValue(device.getCashboxLimit()));

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
		IOrganization org = orgService.get(organizationID);
		if(null==org){
			result.addAttribute(FishConstant.SUCCESS, false);
			IPageResult<IDevice> pageResult = new PageResult<IDevice>();
			result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
			result.addAttribute(FishConstant.DATA, DeviceForm.convert(pageResult.list()));
			return result;
		}
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

		filter.like("devService.orgFlag",organizationID +  "%");

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
		else if(atmTypeIdList.size()==0){
			model.put(FishConstant.SUCCESS, true);
			model.put(FishConstant.DATA, new AtmTypeForm());
			return model;
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
			errorMsg.append(messageSource.getMessage("device.termIdDup", null, FishCfg.locale)+".<BR>");
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
			} else if ("devTypeId".equals(name)) {
				filter.eq("device.devType.id", Long.parseLong(value));
			} 
//			
			else {
				filter.like("device." + name, value);
			}
		}

		return filter;
	}

	private List<Object> request2filter(WebRequest request, StringBuffer hql, String organization) {
		List<Object> fixedFilters = new ArrayList<Object>();
		hql.append("from Device device where 1=1 and device.organization.orgFlag like ? ");
		IOrganization org = orgService.get(organization);
		fixedFilters.add(org.getOrgFlag() + "%");

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
				fixedFilters.add(orgService.get(value).getOrgFlag() + "%");
			} else if ("organization".equals(name)) {
				hql.append(" and device.organization.orgFlag like ?");
				fixedFilters.add(orgService.get(value).getOrgFlag() + "%");
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
