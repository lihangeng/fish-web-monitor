package com.yihuacomputer.fish.web.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.FishWebUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.CommandResult;
import com.yihuacomputer.fish.api.monitor.business.CommandType;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.CommandLevel;
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

    @Autowired
    private IRemoteCommHistService remoteCommHistService;

    @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    public @ResponseBody ModelMap restart(@RequestParam String terminalId, @RequestParam CommandLevel restartType,
            @RequestParam String ip, WebRequest webRequest, HttpServletRequest request) {
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();
        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/reboot";

        RestartParamForm restartParamForm = new RestartParamForm();
        restartParamForm.setRestartType(restartType);

        IRemoteCommHist hist = remoteCommHistService.make();
        hist.setDatetime(DateUtils.getTimestamp(new Date()));
        hist.setCommandResult(CommandResult.EXEC_LOADING);
        hist.setCommandType(CommandType.RESTART_ENFORCE);
        hist.setHandlePerson(session.getUserName());
        hist.setTerminalId(terminalId);
        remoteCommHistService.save(hist);

        restartParamForm.setId(hist.getId());
        
        try {
            HttpProxy.httpPost(url, restartParamForm, null, 5000);
            
            // 设置运行状态为重启
            runInfo.setRunStatus(RunStatus.ReBoot);
            collectService.collectATMCRunInfo(terminalId, runInfo);

            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
            
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }

        return result;
    }

    // @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    // public @ResponseBody
    // ModelMap restart(@RequestParam String terminalId, @RequestParam
    // CommandLevel restartType, @RequestParam String ip,
    // WebRequest request) {
    //
    // IRunInfo runInfo = runInfoService.make();
    // runInfo.setTerminalId(terminalId);
    //
    // ModelMap result = new ModelMap();
    // String url = MonitorCfg.getHttpUrl(ip) + "/ctr/reboot";
    //
    // RestartParamForm restartParamForm = new RestartParamForm();
    // restartParamForm.setRestartType(restartType);
    // try {
    // RestartForm restartForm = (RestartForm) HttpProxy.httpPost(url,
    // restartParamForm, RestartForm.class, 5000);
    // String appRet = restartForm.getAppRet();
    // result.put(FishConstant.SUCCESS, true);
    // result.addAttribute("appRet", appRet);
    //
    // // 设置运行状态为重启
    // runInfo.setRunStatus(RunStatus.ReBoot);
    // collectService.collectATMCRunInfo(terminalId, runInfo);
    // }
    // catch (Exception e) {
    // result.put(FishConstant.SUCCESS, false);
    // result.addAttribute("appRet", MonitorResponseCode.FAILURE);
    // }
    //
    //
    // return result;
    // }

    // @RequestMapping(value = "/normalReboot", method = RequestMethod.POST)
    // public @ResponseBody ModelMap normalRestart(@RequestParam String
    // terminalId,
    // @RequestParam CommandLevel restartType, @RequestParam String ip,
    // WebRequest request) {
    //
    // IRunInfo runInfo = runInfoService.make();
    // runInfo.setTerminalId(terminalId);
    //
    // ModelMap result = new ModelMap();
    // String url = MonitorCfg.getHttpUrl(ip) + "/ctr/normalReboot";
    //
    // RestartParamForm restartParamForm = new RestartParamForm();
    // restartParamForm.setRestartType(restartType);
    // try {
    // RestartForm restartForm = (RestartForm) HttpProxy.httpPost(url,
    // restartParamForm, RestartForm.class, 5000);
    // String appRet = restartForm.getAppRet();
    // result.put(FishConstant.SUCCESS, true);
    // result.addAttribute("appRet", appRet);
    //
    // // 设置运行状态为重启
    // runInfo.setRunStatus(RunStatus.ReBoot);
    // collectService.collectATMCRunInfo(terminalId, runInfo);
    // }
    // catch (Exception e) {
    // result.put(FishConstant.SUCCESS, false);
    // result.addAttribute("appRet", MonitorResponseCode.FAILURE);
    // }
    //
    // return result;
    // }

    @RequestMapping(value = "/normalReboot", method = RequestMethod.POST)
    public @ResponseBody ModelMap normalRestart(@RequestParam String terminalId,
            @RequestParam CommandLevel restartType, @RequestParam String ip, WebRequest webRequest,
            HttpServletRequest request) {

        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();
        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/normalReboot";

        RestartParamForm restartParamForm = new RestartParamForm();
        restartParamForm.setRestartType(restartType);
        
        IRemoteCommHist hist = remoteCommHistService.make();
        hist.setDatetime(DateUtils.getTimestamp(new Date()));
        hist.setCommandResult(CommandResult.EXEC_LOADING);
        hist.setCommandType(CommandType.RESTART_NORMAL);
        hist.setHandlePerson(session.getUserName());
        hist.setTerminalId(terminalId);
        remoteCommHistService.save(hist);

//        restartParamForm.setId(hist.getId());
//        Runnable runnable = new RemoteCommandRunnable(url, restartParamForm, "POST", hist.getId(),
//                remoteCommHistService);
//        Thread thread = new Thread(runnable);
//        thread.start();
        
        restartParamForm.setId(hist.getId());
        try {
            // 设置运行状态为重启
            runInfo.setRunStatus(RunStatus.ReBoot);
            collectService.collectATMCRunInfo(terminalId, runInfo);
            HttpProxy.httpPost(url, restartParamForm, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
            
        }
        
        return result;
    }

}
