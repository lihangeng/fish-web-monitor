package com.yihuacomputer.fish.web.fault.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.fault.ResponsorType;
import com.yihuacomputer.fish.web.fault.form.FaultClassifyForm;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/case/faultClassify")
@ClassNameDescrible(describle="userlog.faultClassifyController")
public class FaultClassifyController
{

    private Logger logger = LoggerFactory
            .getLogger(FaultClassifyController.class);

    @Autowired
    private IFaultClassifyService faultClassifyService;

    @Autowired
	protected MessageSource messageSource;

    /**
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit,
            WebRequest request)
    {
        ModelMap result = new ModelMap();
        logger.info(String.format(
                "search FaultClassify : start = %s ,limt = %s ", start, limit));

        Filter filter = new Filter();
        IPageResult<IFaultClassify> pageResult = faultClassifyService.page(
                start, limit, filter);
        List<FaultClassifyForm> resultList = new ArrayList<FaultClassifyForm>();
        for (IFaultClassify item : pageResult.list())
        {
            resultList.add(new FaultClassifyForm(item));
        }
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        logger.info("notifyMould size:" + pageResult.getTotal());
        result.addAttribute("data", resultList);
        return result;
    }

    /**
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.faultClassifyController.update",hasReqBodyParam=true,reqBodyClass=FaultClassifyForm.class,bodyProperties="classifyName")
    public @ResponseBody
    ModelMap update(@PathVariable String id,
            @RequestBody FaultClassifyForm request)
    {
        logger.info("update Param: param.id = " + id);
		request.setId(id);
        ModelMap result = new ModelMap();
        try
        {
            IFaultClassify faultClassify = faultClassifyService
                    .getFaultClassifyById(id);
            faultClassify.setNotifyTimes(request.getNotifyTimes());
            faultClassify
                    .setNotifyWay(NotifyWay.valueOf(request.getNotifyWay()));
            faultClassify.setResolveHour(Double.valueOf(request
                    .getResolveHour()));
            faultClassify.setResponsorType(ResponsorType.getById(Integer
                    .parseInt(request.getResponsorType())));
            faultClassify.setUpgrade(request.getUpgrade());
            faultClassifyService.updateFaultClassify(faultClassify);
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("data", request);
        }
        catch (Exception e)
        {
        	logger.error(String.format("[%s]", e));
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.updateError", null, FishCfg.locale));
        }
        return result;
    }

}
