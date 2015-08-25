package com.yihuacomputer.fish.web.daily.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.daily.form.SoftAndHardwareInfoForm;
import com.yihuacomputer.fish.web.monitor.form.ModHalVersion;

/**
 * 设备软件，硬件信息
 *
 * @author pengwenchao
 *
 */
@Controller
@RequestMapping("machine/device/softhardware")
public class SoftAndHardwareInfoController {

	private Logger logger = LoggerFactory.getLogger(SoftAndHardwareInfoController.class);

    /**
     * 软件信息接口
     */
    @Autowired
    private ISoftwareService softwareService;

    /**
     * 硬件信息接口
     */
    @Autowired
    private IHardwareService hardwareService;

    @PostConstruct
    public void init() {
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap softAndHardwareInfo(@RequestParam String terminalId) {
        logger.info(String.format("search device linked to terminalId = %s", terminalId));

        ModelMap model = new ModelMap();

        model.put(FishConstant.SUCCESS, true);
        model.put("data",
                new SoftAndHardwareInfoForm(hardwareService.load(terminalId), softwareService.load(terminalId)));

        return model;
    }

    /**
     * 获取硬件版本信息
     */
    @RequestMapping(value = "/initHardware", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap initHardware(@RequestParam String terminalId, @RequestParam String ip) {
        logger.info(String.format("initHardware(%s, %s)", terminalId, ip));
        ModelMap result = new ModelMap();

		String url = MonitorCfg.getHttpUrl(ip) + "/ctr/modharverinfo";

         try {
        	ModHalVersion modHalVersion = (ModHalVersion) HttpProxy.httpGet(url, ModHalVersion.class);
            result.put(FishConstant.SUCCESS, true);
            result.put("data", modHalVersion);
        }catch (Exception e) {
            logger.info("The DeviceModuleStatusController of the method deviceIDC error!" + e.toString());
            result.put(FishConstant.SUCCESS, false);
        }
        return result;
    }

}
