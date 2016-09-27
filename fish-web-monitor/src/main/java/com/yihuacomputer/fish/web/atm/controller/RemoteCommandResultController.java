package com.yihuacomputer.fish.web.atm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.business.CommandResult;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.web.atm.format.RemoteCommandResult;

/**
 * 远程命令执行结果
 * 
 * @author pengwenchao
 *
 */
@Controller
@RequestMapping("/msg/remoteCommandResult")
public class RemoteCommandResultController {

    private Logger logger = LoggerFactory.getLogger(RemoteCommandResultController.class);

    @Autowired
    private IRemoteCommHistService remoteCommHistService;

    /**
     * 接收远程命令执行结果信息
     * 
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody RemoteCommandResult reciveMsg(@RequestBody RemoteCommandResult result) {

        logger.error(String.format("remoteCommandResult info is [%s]", JsonUtils.toJson(result)));

        try {
            IRemoteCommHist hist = remoteCommHistService.get(result.getId());
            if (hist != null) {
                if ("00".equals(result.getAppRet())) {
                    hist.setCommandResult(CommandResult.EXEC_SUCCESSFULLY);
                } else {
                    hist.setCommandResult(CommandResult.EXEC_FAIL);
                }
                remoteCommHistService.update(hist);
            }
        }
        catch (Exception e) {
            logger.error(String.format(
                    "collection remoteCommandResult info exception![%s],remoteCommandResult info is [%s]", e,
                    JsonUtils.toJson(result)));
        }
        return result;
    }
}
