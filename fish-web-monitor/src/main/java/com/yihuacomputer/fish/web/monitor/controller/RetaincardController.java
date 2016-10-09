package com.yihuacomputer.fish.web.monitor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.CardRetainType;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IDCardType;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.monitor.form.RetainCardForm;
import com.yihuacomputer.fish.web.util.ExcelViewUtils;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 吞卡信息管理
 *
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/monitor/retainCard")
@ClassNameDescrible(describle="userlog.RetaincardController")
public class RetaincardController {

	private Logger logger = LoggerFactory.getLogger(RetaincardController.class);

	@Autowired
	private IRetaincardService retaincardService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	@MethodNameDescrible(describle="userlog.RetaincardController.add",hasArgs=false)
	ModelMap add(@RequestBody RetainCardForm request) {
		ModelMap result = new ModelMap();
		try {
			String terminalId = request.getTerminalId();
			if (!this.isInDevicelist(terminalId)) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFailDev", null, FishCfg.locale));
			} else if (!this.isConsilientTerminalId(terminalId,
					request.getUserOrgId())) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("retaincard.addRefuseOrg", null, FishCfg.locale));
			} else if (this.checkAccountNo(request.getAccountNo(),
					request.getUserOrgId(), request.getCardRetainTime())) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("retaincard.addRefuseDup", null, FishCfg.locale));
			} else {
				IDevice device = deviceService.get(terminalId);
				String subsidiaryorganId = device.getOrganization().getGuid();
				request.setSubsidiaryorganId(subsidiaryorganId);
				IRetaincard retaincard = retaincardService.make();
				request.translate(retaincard);
				retaincard.setCardRetainType(CardRetainType.MANUAL_CARD);
				retaincard.setTreatmentOrganization(device.getOrganization());
				retaincardService.add(retaincard);
				result.put(FishConstant.SUCCESS, true);
				result.addAttribute("data", new RetainCardForm(retaincard,
						orgService, deviceService));
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("retaincard.addRefuseError", null, FishCfg.locale));
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam long organizationId, @RequestParam int start,
			@RequestParam int limit, WebRequest request) {
		logger.info(String.format(
				"search retaincardinfo : start = %s ,limt = %s ", start, limit));

		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();

		IPageResult<IRetaincard> pageResult = retaincardService.page(start,
				limit, filter, organizationId);
		List<RetainCardForm> resultList = new ArrayList<RetainCardForm>();
		for (IRetaincard item : pageResult.list()) {
			resultList.add(new RetainCardForm(item, orgService, deviceService));
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", resultList);
		return result;
	}

	/**
	 * 删除吞卡信息
	 * @param id
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.RetaincardController.delete",hasArgs=false,urlArgs=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete Retaincard: Retaincard.id = " + id);
		IRetaincard card = retaincardService.get(id);
		ModelMap result = new ModelMap();
		if(card != null)
		{
		if (card.getCardRetainType() == CardRetainType.AUTOMATIC_CARD) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.delFailAuto", null, FishCfg.locale));
		} else {
			try {
				retaincardService.remove(id);
				result.addAttribute(FishConstant.SUCCESS, true);
			} catch (Exception ex) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.delError", null, FishCfg.locale));
			}
		}
		}
		else
		{
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.delSuccess", null, FishCfg.locale));
		}

		return result;
	}

	/**
	 * 客户领取卡片
	 *
	 * @param organizationId
	 * @param id
	 * @param cardType
	 * @param customerName
	 * @param customerPapers
	 * @param customerPhone
	 * @param status
	 * @param treatmentPeople
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.RetaincardController.reveive",hasArgs=true,argsContext="id")
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap reveive(@RequestParam String organizationId,
			@RequestParam long id, @RequestParam int cardType,
			@RequestParam String customerName,
			@RequestParam String customerPapers,
			@RequestParam String customerPhone, @RequestParam int status,
			@RequestParam String treatmentPeople) {
		logger.info("receive Retaincard: Retaincard.id = " + id);
		ModelMap result = new ModelMap();
		IRetaincard card = retaincardService.get(id);
		try {
			if (card == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailNotExist", null, FishCfg.locale));
			} else if(card.getStatus().equals(CardStatus.DESTORYED)){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailDestroied", null, FishCfg.locale));
				return result;
			} else {
				card.setCardType(IDCardType.getById(cardType));
				card.setCustomerName(customerName);
				card.setCustomerPapers(customerPapers);
				card.setCustomerPhone(customerPhone);
				card.setStatus(CardStatus.getById(status));
				card.setTreatmentPeople(treatmentPeople);
				card.setTreatmentTime(new Date());
				retaincardService.update(card);
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 验证卡片是否能被当前登录的人员处理
	 *
	 * @param id
	 *            吞卡信息ID
	 * @param organizationId
	 *            当前登录人员所属机构ID
	 * @return
	 */
	@RequestMapping(value = "/checkGuid", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap checkGuid(@RequestParam long id,
			@RequestParam String organizationId) {
		ModelMap result = new ModelMap();
		logger.info("check guid: Retaincard.id = " + id);
		try {
			IRetaincard card = retaincardService.get(id);
			List<Long> orgIdList = orgService.listSubOrgId(organizationId);
//			List<IOrganization> orgs = EntityUtils.convert(orgList);
			if (card != null) {
				if (card.getTreatmentOrganization() == null) {
					result.addAttribute(FishConstant.SUCCESS, true);
				} else {
//					IOrganization org = orgService.get(card.getTreatmentOrganization().getGuid());
					if (orgIdList.contains(card.getTreatmentOrganization().getId())) {
						result.addAttribute(FishConstant.SUCCESS, true);
					} else {
						result.addAttribute(FishConstant.SUCCESS, false);
						result.addAttribute("errorMsg", messageSource.getMessage("retaincard.operateFaileRight", null, FishCfg.locale));
					}
				}
			}
			else
			{
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.operateError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 客户领卡时验证
	 *
	 * @param id
	 * @param organizationId
	 * @return
	 */
	@RequestMapping(value = "/checkGuidReveice", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap checkGuidReceive(@RequestParam long id,
			@RequestParam String organizationId) {
		ModelMap result = new ModelMap();
		logger.info("check guid: Retaincard.id = " + id);
		try {
			IRetaincard card = retaincardService.get(id);
			if (card != null) {
				if (card.getStatus().getId() == 1) { // 如果卡片状态已经变为1(已领)
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailNotExist", null, FishCfg.locale));
				} else if (card.getStatus().getId() == 2) {
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailDestroied", null, FishCfg.locale));
				} else {
					if (card.getHandOverOrg() == null) {
						result.addAttribute(FishConstant.SUCCESS, true);
					} else {
						if (card.getHandOverOrg().getGuid()
								.equals(organizationId)) { // 当移交机构是当前登录人员所属机构时才有权限处理这张卡片
							result.addAttribute(FishConstant.SUCCESS, true);
						} else {
							result.addAttribute(FishConstant.SUCCESS, false);
							result.addAttribute("errorMsg",
									messageSource.getMessage("retaincard.getFailHandover", null, FishCfg.locale));
						}
					}
				}

			} else {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailNotExist", null, FishCfg.locale));
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.getFailError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 找出待领与调出状态的卡片
	 *
	 * @param organizationId
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findCardByStatus", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap findCardByStatus(@RequestParam long organizationId,
			@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();

		int WAIT_RECEIVE = 0;
		int HAND_OVER = 3;
		IFilter filter = request2filter(request);
		IPageResult<IRetaincard> pageResult = retaincardService.page(start,
				limit, filter, organizationId, CardStatus.getById(HAND_OVER),
				CardStatus.getById(WAIT_RECEIVE));
		List<RetainCardForm> resultList = new ArrayList<RetainCardForm>();
		for (IRetaincard item : pageResult.list()) {
			resultList.add(new RetainCardForm(item, orgService, deviceService));
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", resultList);
		return result;
	}

	/**
	 * 卡片移交处理
	 *
	 * @param orgGuid
	 * @param id
	 * @param status
	 * @param treatmentPeople
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.RetaincardController.handover",hasArgs=true,argsContext="id")
	@RequestMapping(value = "/handover", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap handover(@RequestParam String organizationId,
			@RequestParam String orgGuid, @RequestParam long id,
			@RequestParam int status, @RequestParam String treatmentPeople) {
		logger.info("handover Retaincard: Retaincard.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IRetaincard card = retaincardService.get(id);
			// Iterable<IOrganization> orgList =
			// orgService.list(Long.parseLong(organizationId));
			// List<IOrganization> orgs = EntityUtils.convert(orgList);

			// if (StringUtils.isEmpty(card.getOrgGuid())) {
			if (card == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.handoverFailNotExist", null, FishCfg.locale));
			} else {
				if (card.getHandOverOrg() == null) {
					card.setTreatmentTime(new Date());
					card.setTreatmentPeople(treatmentPeople);
					card.setStatus(CardStatus.getById(status));
					// card.setOrgGuid(orgGuid);
					card.setHandOverOrg(orgService.get(orgGuid));
					retaincardService.update(card);
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute("data", new RetainCardForm(card,
							orgService, deviceService));
				} else {
					// IOrganization org =
					// orgService.get(card.getHandOverOrg().getGuid());
					if (card.getHandOverOrg().getGuid().equals(organizationId)) { // 当处理机构与当前登录用户所属的机构一致时才有权限对卡片进行处理
						card.setTreatmentTime(new Date());
						card.setTreatmentPeople(treatmentPeople);
						card.setStatus(CardStatus.getById(status));
						card.setHandOverOrg(orgService.get(orgGuid));
						retaincardService.update(card);
						result.addAttribute(FishConstant.SUCCESS, true);
						result.addAttribute("data", new RetainCardForm(card,
								orgService, deviceService));
					} else {
						result.addAttribute(FishConstant.SUCCESS, false);
						result.addAttribute("errorMsg", messageSource.getMessage("retaincard.handoverFailRight", null, FishCfg.locale));
					}
				}
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.handoverError", null, FishCfg.locale));
		}

		return result;
	}

	/**
	 * 卡片销毁
	 *
	 * @param id
	 * @param name
	 *            处理人员
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.RetaincardController.update",hasArgs=true,argsContext="id")
	@RequestMapping(method = RequestMethod.GET, value = "/destory")
	public @ResponseBody
	ModelMap update(@RequestParam long id, @RequestParam String name,
			@RequestParam String organizationId) {
		logger.info("destory Retaincard:Retaincard.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IRetaincard retaincard = retaincardService.get(id);
			if(retaincard == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.destroyFailNotExist", null, FishCfg.locale));
			}
			else if(retaincard.getStatus().equals(CardStatus.ALREADY_RECEIVE)){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("retaincard.destroyFailGeted", null, FishCfg.locale));
				return result;
			}
			else{
				retaincard.setStatus(CardStatus.DESTORYED);
				retaincard.setTreatmentTime(new Date());
				retaincard.setTreatmentPeople(name);
				// retaincard.setTreatmentOrganization(orgService.get(organizationId));
				retaincardService.update(retaincard);
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("retaincard.destroyFailError", null, FishCfg.locale));
		}

		return result;
	}

	/**
	 * 判断吞卡日期距当前日期的天数 (吞卡天数大于14天的卡片才能处理)
	 *
	 * @param retaincardTime
	 * @param today
	 * @return
	 */
	/*
	 * private int getDaysBetween(Date retaincardTime, Date today) {
	 *
	 * Calendar d1 = Calendar.getInstance(); d1.setTime(retaincardTime);
	 *
	 * Calendar d2 = Calendar.getInstance(); d2.setTime(today);
	 *
	 * if (d1.after(d2)) { Calendar swap = d1; d1 = d2; d2 = swap; } int days =
	 * d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR); int y2 =
	 * d2.get(Calendar.YEAR); if (d1.get(Calendar.YEAR) != y2) { d1 = (Calendar)
	 * d1.clone(); do { days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
	 * d1.add(Calendar.YEAR, 1); } while (d1.get(Calendar.YEAR) != y2); } return
	 * days; }
	 */

	/**
	 * 验证当前登录人员是否能处理卡片
	 *
	 * @param id
	 * @param organizationId
	 * @return true :可以处理 false: 不能处理
	 */
	@SuppressWarnings("unused")
	private boolean check(long id, String organizationId) {
		IRetaincard card = retaincardService.get(id);
		boolean flag = false;
		if (card.getHandOverOrg() == null) {
			flag = true;
		} else {
			if (card.getHandOverOrg().getGuid().equals(organizationId)) { // 如果当前处理人员所属机构与卡片的handOverOrg一致，那么可以处理此卡片
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	public List<RetainCardForm> convert(List<IRetaincard> list) {
		List<RetainCardForm> result = new ArrayList<RetainCardForm>();
		for (IRetaincard item : list) {
			RetainCardForm rcf = new RetainCardForm();
			rcf.setTerminalId(item.getTerminalId());
			IDevice device = deviceService.get(item.getTerminalId());
			IOrganization org = device.getOrganization();
			rcf.setSubsidiaryorganName(org.getName());
			rcf.setAccountNo(item.getAccountNo());
			rcf.setCardRetainType(getI18N(item.getCardRetainType().getText()));
			rcf.setCardRetainTime(DateUtils.getTimestamp(item.getCardRetainTime()));
			rcf.setCardDistributionBank(item.getCardDistributionBank());
			rcf.setReason(item.getReason());
			result.add(rcf);
		}
		return result;
	}
	/**
	 * 导出吞卡信息 生成Excel
	 *
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.RetaincardController.poiExcel",hasArgs=false)
	@RequestMapping(value = "/poiExcel", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView poiExcel(WebRequest wRequest, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(String.format("Export Retain Card Information : "));
		IFilter filter = request2filter(wRequest);
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		List<IRetaincard> entities = retaincardService.listCardByOrgId(userSession.getOrgId(), filter);
		Map<String,Object> map = new HashMap<String,Object>();
		String theme = String.format("%s",getI18N("retaincard.info"));
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devinfo
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		List<RetainCardForm> formList = convert(entities);
		map.put(ExcelViewUtils.BODY_CONTEXTS, formList);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
	}
	@Autowired
	private MessageSource messageSourceEnum;
    private String getI18N(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }

	/**
	 * 手动添加时检查卡片状态，若卡片为待领或者调出状态时则不能添加此卡片信息
	 *
	 * @param accountNo
	 * @param orgId
	 * @return false : check 通过 true : check 不通过
	 */
	private boolean checkAccountNo(String accountNo, String orgId,
			String cardRetainTime) {
		boolean flag = false;
		List<IRetaincard> retaincardList = retaincardService.listCardByOrgId(
				Long.parseLong(orgId), new Filter());
		for (IRetaincard item : retaincardList) {
			if ( item.getAccountNo() != null && item.getAccountNo().equals(accountNo)) {
				// if (item.getStatus().equals(CardStatus.WAIT_RECEIVE) ||
				// item.getStatus().equals(CardStatus.HAND_OVER)) {
				// flag = true;
				// break;
				// }
				if (DateUtils.getTimestamp(item.getCardRetainTime()).equals(
						cardRetainTime)) {
					flag = true;
					break;
				}

			}
		}
		return flag;
	}

	/**
	 * 检查增加时输入的设备号是否在设备列表中
	 *
	 * @param terminalId
	 * @return
	 */
	private boolean isInDevicelist(String terminalId) {
		List<IDevice> list = deviceService.list();

		boolean isInDevicelist = false;
		for (IDevice item : list) {
			if (item.getTerminalId().equals(terminalId)) {
				isInDevicelist = true;
				break;
			}
		}
		return isInDevicelist;
	}

	/**
	 * 判断设备是否属于当前登录人员所属机构下
	 *
	 * @param terminalId
	 *            设备号
	 * @param orgId
	 *            当前登录人员所属机构
	 * @return true(属于) false(不属于)
	 */
	private boolean isConsilientTerminalId(String terminalId, String orgId) {
		boolean flag = false;
		List<IDevice> deviceList = deviceService.orgList(new Filter(),
				Long.parseLong(orgId));
		for (IDevice item : deviceList) {
			if (item.getTerminalId().equals(terminalId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 查询条件
	 *
	 * @param request
	 * @return
	 */
	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		filter.descOrder("id");
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				if (name.equals("sort")) {
					continue;
				}
				if (name.equals("endDate")) {
					filter.le("retaincard.cardRetainTime",
							DateUtils.getTimestamp(value +" 23:5:59"));
				} else if (name.equals("startDate")) {
					filter.ge("retaincard.cardRetainTime",
							DateUtils.getTimestamp(value + " 00:00:00"));
				} else if (name.equals("devVendorId")) {
					filter.eq("device.devType.devVendor.id",
							Long.valueOf(value));
				} else if (name.equals("devTypeId")) {
					filter.eq("device.devType.id",
							Long.valueOf(value));
				} else if (name.equals("orgId")) {
					String orgFlag = orgService.get(value).getOrgFlag();
					filter.like("device.organization.orgFlag", orgFlag);
				} else if (name.equals("status")) {
					filter.eq("retaincard.status",
							CardStatus.getById(Long.valueOf(value).intValue()));
				} else if (name.equals("accountNo")) {
					filter.like("retaincard.accountNo", value);
				} else if (name.equals("inOut")) {
					filter.eq("device.awayFlag",
							AwayFlag.getById(Long.valueOf(value).intValue()));
				} else if (name.equals("terminalId")) {
					filter.like("retaincard.terminalId", value);

				}
			}
		}

		return filter;
	}

}
