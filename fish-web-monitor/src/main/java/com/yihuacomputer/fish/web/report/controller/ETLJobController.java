package com.yihuacomputer.fish.web.report.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.batch.base.IETLjobDaysService;
import com.yihuacomputer.fish.api.batch.base.IETLjobMonthService;
import com.yihuacomputer.fish.api.batch.base.IETLjobService;
import com.yihuacomputer.fish.web.report.form.JobCountForm;
import com.yihuacomputer.fish.web.report.form.JobForm;

@Controller
@RequestMapping("/report/ETLJob")
public class ETLJobController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(ETLJobController.class);

	public static final String STANDARD_DATE = "yyyyMMdd";
	public static final String STANDARD_MONTH_FULL1 = "yyyyMM";

	@Autowired
	private IETLjobService eService;

	@Autowired
	private IETLjobDaysService iDaysService;

	@Autowired
	private IETLjobMonthService iMonthService;

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest Request, HttpServletRequest req) {
		logger.info(String.format("search interface : start = %s ,limt = %s ", start, limit));
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String startTime = Request.getParameter("startTime");
		String endTime = Request.getParameter("endTime");
		if (startTime != null) {
			filter.like("startTime", startTime);
		}
		if (endTime != null) {
			filter.like("endTime", endTime);
		}
		IPageResult<Object> lists = eService.listData(start, limit, filter);
		List<JobForm> forms = new ArrayList<JobForm>();
		for (Object obj : lists.list()) {
			Object[] object = (Object[]) obj;
			JobForm form = new JobForm();
			form.setId(((BigInteger) object[0]).intValue());
			form.setJobName(object[1].toString());
			form.setStartTime(DateUtils.getTimestamp(DateUtils.getTimestamp(object[2].toString())));
			form.setEndTime(DateUtils.getTimestamp(DateUtils.getTimestamp(object[3].toString())));
			form.setTradeTime(object[4].toString());
			form.setOperaResult(object[5].toString());
			forms.add(form);
		}
		result.addAttribute("total", lists.getTotal());
		result.addAttribute("success", true);
		result.addAttribute("data", forms);
		return result;
	}

	/**
	 * 失败job重做
	 *
	 * @param tradeTime
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@RequestMapping(value = "/operaAgain", method = RequestMethod.POST)
	public @ResponseBody ModelMap retry(@RequestParam int id, @RequestParam String tradeTime, @RequestParam String jobName, HttpServletRequest request, WebRequest webrequest) {
		ModelMap result = new ModelMap();
		try {
			if (jobName.equals("Month_Trans_job")) {
//				eService.reStartMonthOpera(tradeTime);
				eService.deteleMonthOpera(tradeTime);
				iMonthService.extractDate(tradeTime);
			} else {
				eService.deteleDayOpera(tradeTime);
//				eService.reStartMonthOpera(tradeTime);
				iDaysService.extractDate(tradeTime);
			}
		} catch (Exception e) {

			logger.error(e.toString());

			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, null);
		}
		return result;
	}

	/**
	 * 得到错误提示
	 *
	 * @param tradeTime
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@RequestMapping(value = "/getErrorMsg", method = RequestMethod.POST)
	public @ResponseBody ModelMap getErrorMsg(@RequestParam int id, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
		ModelMap result = new ModelMap();
		String errorMsg = null;
		try {
			errorMsg = eService.getErrorMsg(id);
			// result.addAttribute(FishConstant.DATA, errorMsg);
			String path = createTxt(errorMsg);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, path);
		} catch (Exception e) {

			logger.error(e.toString());

			result.addAttribute(FishConstant.SUCCESS, false);
			// result.addAttribute(FishConstant.ERROR_MSG, null);
		}
		return result;
	}
	//生成错误提示本地文件
	private String createTxt(String data) {
		String fileName = "errorMsg.txt";
		String pathname = FishCfg.getTempDir() + File.separator + fileName;
		try {
			File file = new File(pathname);
			if (file.exists()) {

			} else {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bufferedWriter.write(data);
			bufferedWriter.close();

		} catch (Exception e) {
		}

		finally {
		}
		return pathname;
	}
	
	//返回前一个月未执行数据统计的日期集合
	public List<JobCountForm> getDates() {
		List<JobCountForm> forms = new ArrayList<JobCountForm>();
		Calendar old = Calendar.getInstance();
		old.add(Calendar.MONTH, -1);
		Calendar xin = Calendar.getInstance();
		xin.add(Calendar.DAY_OF_MONTH, -1);
		List<String> SureList = new ArrayList<String>();
		String oldstr = new SimpleDateFormat(STANDARD_DATE).format(old.getTime());
		String xinstr = new SimpleDateFormat(STANDARD_DATE).format(xin.getTime());

		SureList.add(oldstr);
		long o = Long.parseLong(oldstr);
		long n = Long.parseLong(xinstr);
		List<String> getList = iDaysService.dateList(o, n);
		do {
			old.set(Calendar.DAY_OF_MONTH, old.get(Calendar.DAY_OF_MONTH) + 1);// 让日期加1
			oldstr = new SimpleDateFormat(STANDARD_DATE).format(old.getTime());
			SureList.add(oldstr);
			o = Long.parseLong(oldstr);
		} while (o < n);
		int index = 0;
		for (Iterator<String> iterator = SureList.iterator(); iterator.hasNext();) {
			String date = (String) iterator.next();
			index++;
			if (getList.contains(date)) {
				continue;
			} else {
//				 AgainList.add(date);
				JobCountForm form = new JobCountForm();
				form.setId(index);
				form.setTradeTime(date);
				forms.add(form);
			}
		}

		return forms;
	}

	/**
	 * 统计
	 * @param tradeTime
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody ModelMap checkTrade(HttpServletRequest request, WebRequest webrequest) {
		ModelMap result = new ModelMap();
		List<JobCountForm> forms = new ArrayList<JobCountForm>();
		try {
			forms = getDates();
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", forms);
		} catch (Exception e) {
			logger.error(e.toString());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, null);
			result.addAttribute("data", forms);
		}
		return result;
	}

	/**
	 * 全部重做
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/AllOpera", method = RequestMethod.GET)
	public @ResponseBody ModelMap AgainAll(WebRequest Request, HttpServletRequest req) {
		logger.info(String.format("All opera!!"));
		ModelMap result = new ModelMap();
		try {
			List<JobCountForm> forms = getDates();
			Calendar date1 = Calendar.getInstance();
			date1.add(Calendar.MONTH, -1);
			String str1 = new SimpleDateFormat(STANDARD_MONTH_FULL1).format(date1.getTime());
			boolean index1 = false;
			Calendar date2 = Calendar.getInstance();
			date2.add(Calendar.MONTH, -2);
			String str2 = new SimpleDateFormat(STANDARD_MONTH_FULL1).format(date2.getTime());
			boolean index2 = false;
			for (Iterator<JobCountForm> iterator = forms.iterator(); iterator.hasNext();) {
				JobCountForm jobCountForm = (JobCountForm) iterator.next();
				iDaysService.extractDate(jobCountForm.getTradeTime());
				if (jobCountForm.getTradeTime().contains(str1)) {
					index1 = true;
				}
				if (jobCountForm.getTradeTime().contains(str2)) {
					index2 = true;
				}
			}
			if (index1) {
				eService.reStartMonthOpera(str1);
				eService.deteleMonthOpera(str1);

				iMonthService.extractDate(str1);
			}
			if (index2) {
				eService.reStartMonthOpera(str2);
				eService.deteleMonthOpera(str2);

				iMonthService.extractDate(str2);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.ERROR_MSG, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, null);
		}
		return result;
	}
}
