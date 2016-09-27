package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.software.IDeviceParam;
import com.yihuacomputer.fish.api.monitor.software.IRuntimeParamService;
import com.yihuacomputer.fish.web.monitor.form.DeviceParamForm;
import com.yihuacomputer.fish.web.monitor.form.DeviceParamMsg;

/**
 * ＡＴＭＣ运行参数控制器
 * 
 * @author pengwenchao
 * 
 */
@Controller
@RequestMapping("/msg/deviceRunParams")
public class AppRuntimeParamController {

    @Autowired
    private IRuntimeParamService runtimeParamService;

    @Autowired
    private IDeviceService deviceService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(AppRuntimeParamController.class);

    /**
     * 增加ATM机运行参数：
     * 
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap reciveMsg(@RequestBody DeviceParamMsg msg) {
        logger.info("add tmMoveService:"+JsonUtils.toJson(msg));
        ModelMap result = new ModelMap();
        result.put(FishConstant.SUCCESS, true);
        result.put("ret", "RET_00");
        saveOrUpdate(msg);
        return result;
    }

    /**
     * 保存或者修改参数(存在则修改，否则保存)
     * 
     * @param msg
     *            参数对象
     */
    private List<DeviceParamForm> saveOrUpdate(DeviceParamMsg msg) {
        String content = msg.getContent();

        if (content == null || content.isEmpty()) {
            content = JsonUtils.toJson(msg.getConfigMap());
        }
        String terminalId = msg.getTerminalId();

        // 如果没有设备，则不进行添加运行参数
        IDevice device = deviceService.get(terminalId);
        if (device == null) {
            return null;
        }

        int deviceParamCount = runtimeParamService.count(terminalId);

        if (deviceParamCount > 0) {
            runtimeParamService.remove(terminalId);
        }
        
        List<DeviceParamForm> list = stringToList(content, terminalId);
        
        for (DeviceParamForm from : list) {
            IDeviceParam iDeviceParam = runtimeParamService.make();
            from.translate(iDeviceParam);
            runtimeParamService.save(iDeviceParam);
        }
        
        return list;
    }
    
    /**
     * 将参数转换为list格式
     * 
     * @param id
     *            数据库id
     * @param terminalId
     *            设备号
     * @param maxMap
     *            参数
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<DeviceParamForm> stringToList(String content, String terminalId) {

        Map<String, List<Map<String, String>>> configMap = JsonUtils.fromJson(content, Map.class);

        List<DeviceParamForm> result = new ArrayList<DeviceParamForm>();

        DeviceParamForm param = null;

        Set<Entry<String, List<Map<String, String>>>> headSet = configMap.entrySet();
        int index = 1;
        for (Entry<String, List<Map<String, String>>> headEntry : headSet) {

            String module = headEntry.getKey();
            List<Map<String, String>> headValue = headEntry.getValue();

            for (Map<String, String> headMap : headValue) {
                String def = headMap.get("def");
                String key = headMap.get("key");
                String value = headMap.get("value");
                String label = headMap.get("label");
                
                param = new DeviceParamForm();
                param.setId(index);
                param.setKey(key);
                param.setModule(module);
                param.setTerminalId(terminalId);
                param.setValue(value);
                param.setDefaultValue(def);
                param.setLabel(label);
                result.add(param);
                index++;
            }
        }
        return result;
    }
}
