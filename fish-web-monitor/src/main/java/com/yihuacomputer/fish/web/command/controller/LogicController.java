package com.yihuacomputer.fish.web.command.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
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

/**
 * @author YiHua
 *
 */
@ClassNameDescrible(describle="userlog.LogicController")
@Controller
@RequestMapping("/agent/logic")
public class LogicController {

	private static Logger logger = LoggerFactory.getLogger(LogicController.class);
	
    @Autowired
    private IRemoteCommHistService remoteCommHistService;

    /**
     * @param terminalId
     * @param ip
     * @param webRequest
     * @param request
     * @return
     */
    @MethodNameDescrible(describle="userlog.LogicController.open",hasArgs=true,argsContext="terminalId")
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

        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
        	logger.error(String.format("[%s]", e));
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }
        
        
        return result;
    }

    /**
     * @param terminalId
     * @param ip
     * @param webRequest
     * @param request
     * @return
     */
    @MethodNameDescrible(describle="userlog.LogicController.close",hasArgs=true,argsContext="terminalId")
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

        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
        	logger.error(String.format("[%s]", e));
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }
        
        return result;

    }

    /**
     * @param terminalId
     * @param ip
     * @param webRequest
     * @param request
     * @return
     */
    @MethodNameDescrible(describle="userlog.LogicController.reset",hasArgs=true,argsContext="terminalId")
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

        try {

            HttpProxy.httpPost(url, rc, null, 5000);
            
            result.put(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
        	logger.error(String.format("[%s]", e));
            result.put(FishConstant.SUCCESS, false);
            
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }
        
        return result;
    }
}
