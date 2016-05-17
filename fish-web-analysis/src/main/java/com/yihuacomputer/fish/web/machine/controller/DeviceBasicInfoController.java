package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.fish.web.machine.form.DeviceBasicInfoForm;

/**
 * 设备基本信息控制器
 * 
 * @author pengwenchao
 * @date 2011-10-21
 * 
 */
@Controller
@RequestMapping("/machine/device")
public class DeviceBasicInfoController
{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory
            .getLogger(DeviceBasicInfoController.class);

    private final List<DeviceBasicInfoForm> deviceBasicInfoList = new ArrayList<DeviceBasicInfoForm>();

    @PostConstruct
    public void init()
    {
        DeviceBasicInfoForm deviceBasicInfo = null;
        // 初始化数据
        for (int i = 0; i < 30; i++)
        {
            deviceBasicInfo = new DeviceBasicInfoForm();

            deviceBasicInfo.setId(i);
            deviceBasicInfo.setNumber("A0250001---" + i);
            deviceBasicInfo.setAffiliation("总行(00080000)--" + i);
            deviceBasicInfo.setAddress("南京市雨花区郁金香路6-2郁金香大楼");
            deviceBasicInfo.setModel("HITCHI2845");
            deviceBasicInfo.setOperation("自营");
            deviceBasicInfo.setLineLogo("在行");
            deviceBasicInfo.setIpAddress("192.168.0.143");
            deviceBasicInfo.setMaintainer("怡化");
            deviceBasicInfo.setSwallowCard(0L);
            deviceBasicInfo.setAlarmRateRMB("30000");
            deviceBasicInfo.setAlarmRateHKD("20000");
            deviceBasicInfo.setAdminPhone("18662710000");
            deviceBasicInfo.setMaintainPhone("18662711111");

            deviceBasicInfoList.add(deviceBasicInfo);
        }

    }

    /**
     * 根据条件得到设备基本信息
     * 
     * @param id
     *            设备ID
     * @return ModelMap
     */
    @RequestMapping(value = "/deviceBasicInfoForm/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@PathVariable long id)
    {
        logger.info(String.format("search device linked to deviceID = %s", id));

        ModelMap result = new ModelMap();

        for (DeviceBasicInfoForm form : deviceBasicInfoList)
        {
            if (id != form.getId())
            {
                continue;
            }
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("data", form);

            return result;
        }
        result.addAttribute(FishConstant.SUCCESS, false);

        return result;
    }
    /**
     * 得到设备基本信息
     * @return ModelMap
     */
    @RequestMapping(value = "/deviceBasicInfoForm", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap getAll(@RequestParam int start, @RequestParam int limit,
            WebRequest request)
    {
        logger.info(String.format("get all device basicInfo"));
        
        ModelMap result = new ModelMap();
        
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", deviceBasicInfoList);
        
        return result;
    }
}
