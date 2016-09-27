package com.yihuacomputer.fish.web.command.controller;

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
import com.yihuacomputer.fish.api.monitor.business.CommandResult;
import com.yihuacomputer.fish.api.monitor.business.CommandType;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.RemoteCommand;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/agent/logic")
public class LogicController {

    @Autowired
    private IRemoteCommHistService remoteCommHistService;

    @RequestMapping(method = RequestMethod.POST, value = "/open")
    public @ResponseBody ModelMap logicOpen(@RequestParam String terminalId, @RequestParam String ip,
            WebRequest webRequest, HttpServletRequest request) {

        ModelMap result = new ModelMap();

        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/logicopen";

        IRemoteCommHist hist = remoteCommHistService.make();
        hist.setCommandResult(CommandResult.EXEC_LOADING);
        hist.setCommandType(CommandType.OPEN_SERVICES);
        hist.setDatetime(DateUtils.getTimestamp(new Date()));
        hist.setHandlePerson(session.getUserName());
        hist.setTerminalId(terminalId);
        remoteCommHistService.save(hist);

        RemoteCommand rc = new RemoteCommand();
        rc.setId(hist.getId());

//        Runnable runnable = new RemoteCommandRunnable(url, null, "POST", hist.getId(), remoteCommHistService);
//        Thread thread = new Thread(runnable);
//        thread.start();

        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
            
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }
        
        
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/close")
    public @ResponseBody ModelMap logicClose(@RequestParam String terminalId, @RequestParam String ip,
            WebRequest webRequest, HttpServletRequest request) {
        ModelMap result = new ModelMap();

        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/logicclose";

        IRemoteCommHist hist = remoteCommHistService.make();
        hist.setCommandResult(CommandResult.EXEC_LOADING);
        hist.setCommandType(CommandType.CLOSE_SERVICES);
        hist.setDatetime(DateUtils.getTimestamp(new Date()));
        hist.setHandlePerson(session.getUserName());
        hist.setTerminalId(terminalId);
        remoteCommHistService.save(hist);

        RemoteCommand rc = new RemoteCommand();
        rc.setId(hist.getId());

//        Runnable runnable = new RemoteCommandRunnable(url, null, "POST", hist.getId(), remoteCommHistService);
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//        result.put(FishConstant.SUCCESS, true);
        
        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
            
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }
        
        return result;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset")
    public @ResponseBody ModelMap reset(@RequestParam String terminalId, @RequestParam String ip,
            WebRequest webRequest, HttpServletRequest request) {
        ModelMap result = new ModelMap();

        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/reset";

        IRemoteCommHist hist = remoteCommHistService.make();
        hist.setCommandResult(CommandResult.EXEC_LOADING);
        hist.setCommandType(CommandType.RESET);
        hist.setDatetime(DateUtils.getTimestamp(new Date()));
        hist.setHandlePerson(session.getUserName());
        hist.setTerminalId(terminalId);
        remoteCommHistService.save(hist);

        RemoteCommand rc = new RemoteCommand();
        rc.setId(hist.getId());

//        Runnable runnable = new RemoteCommandRunnable(url, rc, "POST", hist.getId(), remoteCommHistService);
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//        result.put(FishConstant.SUCCESS, true);
        
        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
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
