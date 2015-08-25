package com.yihuacomputer.fish.web.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.LogicForm;
import com.yihuacomputer.fish.web.command.format.MonitorResponseCode;
import com.yihuacomputer.fish.web.command.format.ResetForm;

@Controller
@RequestMapping("/agent/logic")
public class LogicController {

    @RequestMapping(method = RequestMethod.POST, value = "/open")
    public @ResponseBody
    ModelMap logicOpen(WebRequest request) {
        // TODO 没有指定到某一台设备
        ModelMap result = new ModelMap();
        try {
            String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/logicopen";

            LogicForm logicForm = (LogicForm) HttpProxy.httpGet(url, LogicForm.class);
            String appRet = logicForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            // result.put(FishConstant.ERROR_MSG, e.getMessage());
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/close")
    public @ResponseBody
    ModelMap logicClose(WebRequest request) {
        ModelMap result = new ModelMap();
        try {
            String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/logicclose";
            LogicForm logicForm = (LogicForm) HttpProxy.httpGet(url, LogicForm.class);
            String appRet = logicForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);
        }
        catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset")
    public @ResponseBody
    ModelMap reset(WebRequest request) {
        ModelMap result = new ModelMap();
        try {
            String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/reset";
            ResetForm resetForm = (ResetForm) HttpProxy.httpGet(url, ResetForm.class);
            String appRet = resetForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);
        }
        catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }
        return result;
    }

}
