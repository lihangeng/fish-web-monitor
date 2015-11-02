package com.yihuacomputer.fish.web.report.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

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

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.api.report.IStartPlanDeviceRelation;
import com.yihuacomputer.fish.api.report.IStartPlanService;
import com.yihuacomputer.fish.web.report.form.LinkDeviceForm;
import com.yihuacomputer.fish.web.report.form.PlanDeviceForm;
import com.yihuacomputer.fish.web.report.form.StartPlanForm;

/**
 * 开机方案
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping("/report/plan")
public class StartPlanController {

	private Logger logger = LoggerFactory.getLogger(StartPlanController.class);

	@Autowired
	private IStartPlanService startPlanService;

	/**
     * 设备接口
     */
    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IStartPlanDeviceRelation startPlanDeviceRelation;


    /**
     *
     * 根据方案Id获得关联设备列表
     *
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(value = "/linkdeDevice", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchLinkedDevice(@RequestParam int start,
            @RequestParam int limit, @RequestParam int flag,
            @RequestParam String planId,WebRequest request,HttpServletRequest req) {
        logger.info(String.format("search device : start = %s ,limt = %s ",
                start, limit));
        ModelMap result = new ModelMap();
        UserSession userSession = (UserSession)req.getSession().getAttribute("SESSION_USER");
        IPageResult<IDevice> pageResult = null;
        if (flag == 0) {
            Filter filter = new Filter();
            pageResult = startPlanDeviceRelation
                      .pageDeviceByPlan(start, limit, startPlanService.get(Long.parseLong(planId)), filter,String.valueOf(userSession.getOrgId()));
            result.addAttribute("success", true);
            result.addAttribute("total", pageResult.getTotal());
            result.addAttribute("data", LinkDeviceForm.convert(pageResult.list()));
        }
        else {
            IFilter filter = new Filter();
            pageResult = startPlanDeviceRelation
                            .pageUnlinkDeviceByPlan(start, limit, startPlanService.get(Long.parseLong(planId)),
                                    filter,String.valueOf(userSession.getOrgId()));
            result.addAttribute("success", true);
            result.addAttribute("total", pageResult.getTotal());
            result.addAttribute("data", LinkDeviceForm.convert(pageResult.list()));
        }
        return result;
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
        try {
            for (String id : ids) {
            	startPlanDeviceRelation.unlink(startPlanService.get(Long.parseLong(planId)),
                        deviceService.get(Long.valueOf(id)));
            }
            result.addAttribute("success", true);
        }
        catch (Exception ex) {
            logger.info(ex.getMessage());
            result.addAttribute("success", false);
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
    ModelMap link(@RequestBody PlanDeviceForm request) {
        logger.info(String.format("device %s linked  %s",
                request.getPlanId(), request.getDeviceId()));
        ModelMap result = new ModelMap();
        startPlanDeviceRelation.link(
        		startPlanService.get(request.getPlanId()),
                deviceService.get(request.getDeviceId()));
        result.put("success", true);
        result.put("data", request);
        return result;
    }


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search startPlan : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IStartPlan> pageResult = startPlanService.page(start, limit, filter);
		result.addAttribute("success", true);
		result.addAttribute("total", pageResult.getTotal());
		logger.info("catalog size:" + pageResult.getTotal());
		result.addAttribute("data", StartPlanForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete startPlan: startPlan.id = " + id);
		ModelMap result = new ModelMap();
		IStartPlan plan = null;
		plan = startPlanService.get(id);
		if(plan!=null){
			try {
				startPlanService.remove(id);
				result.addAttribute("success", true);
			} catch (Exception ex) {
				result.addAttribute("success", false);
				result.addAttribute("errors", "删除失败");
			}
		}else{
			result.addAttribute("success", false);
			result.addAttribute("errors", "该方案已不存在，请刷新后再操作！");
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody StartPlanForm request) {
		logger.info("add startPlan");
		ModelMap result = new ModelMap();
		boolean isExist = this.isExistCode(request.getId(), request.getName());
        if (isExist) {
            result.addAttribute("success", false);
            result.addAttribute("errors", "该方案名称已存在，请重新输入！");
        }
        else {
			IStartPlan plan = startPlanService.make();
			request.translate(plan);
			startPlanService.add(plan);
			result.put("success", true);
			result.addAttribute("data", new StartPlanForm(plan));
        }
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody StartPlanForm request) {
		logger.info("update startPlan: startPlan.id = " + id);
		ModelMap result = new ModelMap();
		IStartPlan plan = null;
		plan = startPlanService.get(id);
		if(plan==null){
			result.addAttribute("success", false);
			result.addAttribute("errors", "该方案已不存在，请刷新后再操作！");
		}else{
			boolean isExist = this.isExistCode(request.getId(), request.getName());
			if (isExist) {
	            result.addAttribute("success", false);
	            result.addAttribute("errors", "该方案名称已存在，请重新输入！");
		    }
		    else {
		    	IStartPlan startPlan = startPlanService.get(id);
				request.translate(startPlan);
				startPlanService.update(startPlan);
				result.addAttribute("success", true);
				result.addAttribute("data", request);
		    }
		}

		return result;
	}

	private boolean isExistCode(long id, String name) {
		try {
			logger.info("update.atmGroup.id is" + id);
			IStartPlan startPlan = startPlanService.get(name);
			logger.info("get.atmGroup.id is " + startPlan.getId());
			if (startPlan.getId()==id) {
				// 找到的Id和传入的Id相等，说明是同一个分组
				return false;
			} else {
				// 说明存在
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if (name.equals("sort")) {
						continue;
					}else {
						filter.addFilterEntry(FilterFactory.like(name, request.getParameter(name)));
					}
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
}
