package com.yihuacomputer.fish.web.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.CommandLevel;
import com.yihuacomputer.fish.web.command.format.MonitorResponseCode;
import com.yihuacomputer.fish.web.command.format.RestartForm;
import com.yihuacomputer.fish.web.command.format.RestartParamForm;

/**
 * 远程重启
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent")
public class RestartController {

    @Autowired
    private ICollectService collectService;

    @Autowired
    private IRunInfoService runInfoService;

    @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap restart(@RequestParam String terminalId, @RequestParam CommandLevel restartType, @RequestParam String ip,
            WebRequest request) {

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();
        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/reboot";

        RestartParamForm restartParamForm = new RestartParamForm();
        restartParamForm.setRestartType(restartType);
        try {
            RestartForm restartForm = (RestartForm) HttpProxy.httpPost(url, restartParamForm, RestartForm.class, 5000);
            String appRet = restartForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);

            // 设置运行状态为重启
            runInfo.setRunStatus(RunStatus.ReBoot);
            collectService.collectATMCRunInfo(terminalId, runInfo);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }


        return result;
    }

    @RequestMapping(value = "/normalReboot", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap normalRestart(@RequestParam String terminalId, @RequestParam CommandLevel restartType, @RequestParam String ip,
            WebRequest request) {

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();
        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/normalReboot";

        RestartParamForm restartParamForm = new RestartParamForm();
        restartParamForm.setRestartType(restartType);
        try {
            RestartForm restartForm = (RestartForm) HttpProxy.httpPost(url, restartParamForm, RestartForm.class, 5000);
            String appRet = restartForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);

            // 设置运行状态为重启
            runInfo.setRunStatus(RunStatus.ReBoot);
            collectService.collectATMCRunInfo(terminalId, runInfo);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }


        return result;
    }


}
