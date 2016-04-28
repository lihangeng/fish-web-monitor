package com.yihuacomputer.fish.web.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author GQ
 * 接收参数更新进度
 */
@Controller
@RequestMapping("/msg/paramUpdateResult")
public class ParamUpdateResultController {
	

	private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IParamPublishResultService paramPublishResultService;

	@Autowired
	private MessageSource messageSourceVersion;
	/**
	 * 接收参数上报更新状态
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody IParamPublishResult reciveMsg(@RequestBody ParamInfo msg) {
		IParamPublishResult paramPublishResult = paramPublishResultService.get(msg.getTaskId());
		if(null==paramPublishResult){
			logger.error(String.format("param update task %d not exist", msg.getTaskId()));
			return null;
		}
		try {
			 	AgentRet agentRet = AgentRet.valueOf(msg.getRet());
		        if (agentRet.equals(AgentRet.RET40)) {
		        	paramPublishResult.setRet(TaskStatus.DOWNLOADED);
		        } else if (AgentRet.isDownFail(agentRet)) {
		        	paramPublishResult.setRet(TaskStatus.DOWNLOADED_FAIL);
		        	paramPublishResult.setReason(getEnumI18n(agentRet.getText()));
		        } else if (agentRet.equals(AgentRet.RET50)) {
		            paramPublishResult.setRet(TaskStatus.DEPLOYED);
		            paramPublishResult.setReason(null);
		        } else if (agentRet.equals(AgentRet.RET51)) {
		            paramPublishResult.setRet(TaskStatus.DEPLOYED_WAIT);
		            paramPublishResult.setReason(null);
		        } else if (AgentRet.isDeployFail(agentRet)) {
		            paramPublishResult.setRet(TaskStatus.DEPLOYED_FAIL);
		            paramPublishResult.setReason(getEnumI18n(agentRet.getText()));
		        } else {
		            paramPublishResult.setRet(TaskStatus.OTHER);
		        }
			paramPublishResult = paramPublishResultService.update(paramPublishResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramPublishResult;
	}

	private String getEnumI18n(String key){
		return messageSourceVersion.getMessage(key,null, FishCfg.locale);
	}

}
enum AgentRet {
    /**
     * 成功
     */
    RET00("AgentRet.RET00"), // 成功
    /**
     * 失败
     */
    RET01("AgentRet.RET01"), // 失败
    /**
     * 相同的软件分类正在升级
     */
    RET0100("AgentRet.RET0100"),
    /**
     * ATC应用忙
     */
    RET02("AgentRet.RET02"), // ATMC应用忙
    /**
     * Agent异常
     */
    RET03("AgentRet.RET03"), // Agent异常
    /**
     * 升级包下载成功
     */
    RET40("AgentRet.RET40"),
    /**
     * 升级包下载失败
     */
    RET41("AgentRet.RET41"),
    /**
     * D盘磁盘空间不足
     */
    RET4100("AgentRet.RET4100"),
    /**
     * 部署路径所在盘磁盘空间不足
     */
    RET4101("AgentRet.RET4101"),
    /**
     * 部署路径所在盘不存在
     */
    RET4102("AgentRet.RET4102"),
    /**
     * 部署路径没有配置
     */
    RET4103("AgentRet.RET4103"),
    /**
     * 服务器中断
     */
    RET4104("AgentRet.RET4104"),
    /**
     * 下发文件MD5值校验失败
     */
    RET4105("AgentRet.RET4105"),
    /**
     * 分发的版本号比ATM上已有的版本号低,无需分发
     */
    RET4106("AgentRet.RET4106"),
    /**
     * 正在部署
     */
    RET50("AgentRet.RET50"),
    /**
     * 等待部署
     */
    RET51("AgentRet.RET51"),
    /**
     * 部署失败
     */
    RET52("AgentRet.RET52"),
    /**
     * 其它
     */
    RET99("AgentRet.RET99");

    private String text;

    private AgentRet(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static boolean isDownFail(AgentRet agentRet) {
        if (agentRet.name().startsWith("RET41")) {
            return true;
        }
        return false;
    }

    public static boolean isDeployFail(AgentRet agentRet) {
        if (agentRet.name().startsWith("RET52")) {
            return true;
        }
        return false;
    }

}
