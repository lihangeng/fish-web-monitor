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
import com.yihuacomputer.fish.api.fault.INotifyMould;
import com.yihuacomputer.fish.api.fault.INotifyMouldService;
import com.yihuacomputer.fish.api.fault.NotifyType;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.web.fault.form.NotifyMouldForm;

@Controller
@RequestMapping("/case/notifyMould")
@ClassNameDescrible(describle="userlog.notifyMouldController")
public class NotifyMouldController
{

    private Logger logger = LoggerFactory.getLogger(NotifyMouldController.class);

    @Autowired
    private INotifyMouldService notifyMouldService;
    
    @Autowired
	protected MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request)
    {
        ModelMap result = new ModelMap();
        logger.info(String.format("search NotifyMould : start = %s ,limt = %s ", start, limit));
        Filter filter =  new Filter();
        IPageResult<INotifyMould> pageResult = notifyMouldService.page(start, limit,filter);
        List<NotifyMouldForm> resultList = new ArrayList<NotifyMouldForm>();
        for (INotifyMould item : pageResult.list())
        {
            resultList.add(new NotifyMouldForm(item));
        }
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        logger.info("notifyMould size:" + pageResult.getTotal());
        result.addAttribute("data", resultList);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.notifyMouldController.update",hasReqBodyParam=true,reqBodyClass=NotifyMouldForm.class,bodyProperties="classifyName")
    public @ResponseBody
    ModelMap update(@PathVariable long id, @RequestBody NotifyMouldForm request)
    {
        logger.info("update Param: param.id = " + id);
		request.setId(id);
        ModelMap result = new ModelMap();
        try
        {
            INotifyMould notifyMould = notifyMouldService.getNotifyMouldById(id);
            notifyMould.setNotifySet(request.getNotifySet());
            notifyMould.setNotifyType(NotifyType.getById(Integer.parseInt(request.getNotifyType())));
            notifyMould.setNotifyWay(NotifyWay.valueOf(request.getNotifyWay()));
            notifyMouldService.updateNotifyMould(notifyMould);
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
