package com.yihuacomputer.fish.web.machine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.fish.web.machine.form.DeviceDetailForm;

@Controller
@RequestMapping("/machine/devicedetail")
public class DeviceDetailController
{
    private Logger logger = LoggerFactory
            .getLogger(DeviceDetailController.class);

    /**
     * 
     * 根据条件得到设备列表
     * 
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(WebRequest request)
    {
        logger.info(String.format("search device linked to area= "));

        ModelMap result = new ModelMap();
        result.addAttribute("data", new DeviceDetailForm());
        result.addAttribute(FishConstant.SUCCESS, true);
        return result;
    }
}
