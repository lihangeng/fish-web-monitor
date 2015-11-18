package com.yihuacomputer.fish.web.daily.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCdm;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCim;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIdc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusJpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPin;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusRpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusSiu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusTtu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.monitor.form.ModStatus;
import com.yihuacomputer.fish.web.monitor.form.StatusCdm;
import com.yihuacomputer.fish.web.monitor.form.StatusCim;
import com.yihuacomputer.fish.web.monitor.form.StatusIdc;
import com.yihuacomputer.fish.web.monitor.form.StatusJpr;
import com.yihuacomputer.fish.web.monitor.form.StatusPin;
import com.yihuacomputer.fish.web.monitor.form.StatusRpr;
import com.yihuacomputer.fish.web.monitor.form.StatusSiu;
import com.yihuacomputer.fish.web.monitor.form.StatusTtu;

/**
 * 设备模块状态控制器
 *
 * @author pengwenchao
 * @date 2011-10-21
 *
 */
@Controller
@RequestMapping("/machine/device")
public class DeviceModuleStatusController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DeviceModuleStatusController.class);

    @Autowired
    private ICollectService collectService;

    @Autowired
    private IXfsService xfsService;

    /**
     * 请求设置基本信息状态
     *
     * @return ModelMap
     */
    @RequestMapping(value = "/deviceModuleStatusForm/initStatus", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap initStatus(@RequestParam String deviceId, @RequestParam String ip) {
        logger.info(String.format("initStatus(%s, %s)", deviceId, ip));

        ModelMap result = new ModelMap();
        String url = MonitorCfg.getHttpUrl(ip) + "/ctr/statusdetail";

        ModStatus modStatus = null;
        try {
            modStatus = (ModStatus) HttpProxy.httpGet(url, ModStatus.class, 5000);
        }
        catch (Exception e) {
            logger.info("remote get status details error！" + e.toString());
            result.put(FishConstant.SUCCESS, false);
            return result;
        }
        if (modStatus != null) {
        	try{
        		updateStatus(deviceId, modStatus);
        	}catch(Exception e){
        		logger.info("updateStatus status details error！" + e.toString());
        	}
            result.put(FishConstant.SUCCESS, true);
            result.put("data", enumI18n(modStatus));
            return result;
        }
        result.put(FishConstant.SUCCESS, false);
        return result;

    }
    private ModStatus enumI18n(ModStatus modStatus){
    	StatusCdm cdm= modStatus.getCdm();
    	cdm.setStatusName(getEnumI18n(cdm.getStatus().getText()));
    	modStatus.setCdm(cdm);
    	StatusCim cim= modStatus.getCim();
    	cim.setStatusName(getEnumI18n(cim.getStatus().getText()));
    	modStatus.setCim(cim);
    	StatusIdc idc= modStatus.getIdc();
    	idc.setStatusName(getEnumI18n(idc.getStatus().getText()));
    	modStatus.setIdc(idc);
    	StatusJpr jpr= modStatus.getJpr();
    	jpr.setStatusName(getEnumI18n(jpr.getStatus().getText()));
    	modStatus.setJpr(jpr);
    	StatusPin pin= modStatus.getPin();
    	pin.setStatusName(getEnumI18n(pin.getStatus().getText()));
    	modStatus.setPin(pin);
    	StatusRpr rpr= modStatus.getRpr();
    	rpr.setStatusName(getEnumI18n(rpr.getStatus().getText()));
    	modStatus.setRpr(rpr);
    	StatusSiu siu= modStatus.getSiu();
    	siu.setStatusName(getEnumI18n(siu.getStatus().getText()));
    	modStatus.setSiu(siu);
    	StatusTtu ttu= modStatus.getTtu();
    	ttu.setStatusName(getEnumI18n(ttu.getStatus().getText()));
    	modStatus.setTtu(ttu);
    	return modStatus;
    }
	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    private void updateStatus(String terminalId, ModStatus modStatus) {
        IXfsStatus histXfsStatus = xfsService.loadXfsStatus(terminalId);
        if (histXfsStatus == null) {
            return;
        }

        IXfsStatus xfsStatus = xfsService.makeXfsStatus();
        xfsStatus.setTerminalId(terminalId);
        xfsStatus.setRunStatus(histXfsStatus.getRunStatus());
        xfsStatus.setNetStatus(NetStatus.Healthy);
        xfsStatus.setBoxStatus(histXfsStatus.getBoxStatus());
        xfsStatus.setBoxInitCount(histXfsStatus.getBoxInitCount());
        xfsStatus.setBoxCurrentCount(histXfsStatus.getBoxCurrentCount());

        // IDC
        IStatusIdc idc = xfsStatus.makeStatusIdc();
        idc.setStatus(modStatus.getIdc().getStatus());
        idc.setCode(modStatus.getIdc().getCode());
        idc.setCards(modStatus.getIdc().getCards());
        idc.setHwCode(modStatus.getIdc().getHwCode());

        // JPR
        IStatusJpr jpr = xfsStatus.makeStatusJpr();
        jpr.setStatus(modStatus.getJpr().getStatus());
        jpr.setCode(modStatus.getJpr().getCode());
        jpr.setHwCode(modStatus.getJpr().getHwCode());

        // RPR
        IStatusRpr rpr = xfsStatus.makeStatusRpr();
        rpr.setStatus(modStatus.getRpr().getStatus());
        rpr.setCode(modStatus.getRpr().getCode());
        rpr.setHwCode(modStatus.getRpr().getHwCode());

        // CDM
        IStatusCdm cdm = xfsStatus.makeStatusCdm();
        cdm.setStatus(modStatus.getCdm().getStatus());
        cdm.setCode(modStatus.getCdm().getCode());
        cdm.setHwCode(modStatus.getCdm().getHwCode());

        // CIM
        IStatusCim cim = xfsStatus.makeStatusCim();
        cim.setStatus(modStatus.getCim().getStatus());
        cim.setCode(modStatus.getCim().getCode());
        cim.setHwCode(modStatus.getCim().getHwCode());

        // SIU
        IStatusSiu siu = xfsStatus.makeStatusSiu();
        siu.setStatus(modStatus.getSiu().getStatus());
        siu.setCode(modStatus.getSiu().getCode());
        siu.setHwCode(modStatus.getSiu().getHwCode());

        // PIN
        IStatusPin pin = xfsStatus.makeStatusPin();
        pin.setStatus(modStatus.getPin().getStatus());
        pin.setCode(modStatus.getPin().getCode());
        pin.setHwCode(modStatus.getPin().getHwCode());

        // TTU
        IStatusTtu ttu = xfsStatus.makeStatusTtu();
        ttu.setStatus(modStatus.getTtu().getStatus());
        ttu.setCode(modStatus.getTtu().getCode());
        ttu.setHwCode(modStatus.getTtu().getHwCode());

        xfsStatus.setStatusIdc(idc);
        xfsStatus.setStatusJpr(jpr);
        xfsStatus.setStatusRpr(rpr);
        xfsStatus.setStatusCdm(cdm);
        xfsStatus.setStatusCim(cim);
        xfsStatus.setStatusSiu(siu);
        xfsStatus.setStatusPin(pin);
        xfsStatus.setStatusTtu(ttu);

        xfsStatus.setModStatus(getModStatus(xfsStatus));

        collectService.collectModuleStatus(terminalId, xfsStatus);
    }

    /**
     * 判断模块总体状态
     *
     * @param xfsStatus
     * @return
     */
    private DeviceStatus getModStatus(IXfsStatus xfsStatus) {
        if (xfsStatus.getStatusIdc().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusCdm().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusCim().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusJpr().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusRpr().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusPin().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusTtu().getStatus().equals(DeviceStatus.Fatal)
                || xfsStatus.getStatusSiu().getStatus().equals(DeviceStatus.Fatal)) {
            return DeviceStatus.Fatal;
        } else if (xfsStatus.getStatusIdc().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusCdm().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusCim().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusJpr().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusRpr().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusPin().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusTtu().getStatus().equals(DeviceStatus.Warning)
                || xfsStatus.getStatusSiu().getStatus().equals(DeviceStatus.Warning)) {
            return DeviceStatus.Warning;
        } else if (xfsStatus.getStatusIdc().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusCdm().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusCim().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusJpr().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusRpr().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusPin().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusTtu().getStatus().equals(DeviceStatus.Unknown)
                && xfsStatus.getStatusSiu().getStatus().equals(DeviceStatus.Unknown)) {
            return DeviceStatus.Unknown;
        } else if (xfsStatus.getStatusIdc().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusCdm().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusCim().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusJpr().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusRpr().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusPin().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusTtu().getStatus().equals(DeviceStatus.NoDevice)
                && xfsStatus.getStatusSiu().getStatus().equals(DeviceStatus.NoDevice)) {

            return DeviceStatus.NoDevice;
        }
        return DeviceStatus.Healthy;
    }
}
