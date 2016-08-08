package com.yihuacomputer.fish.web.atm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.web.atm.format.BoxDetailReportMsg;
import com.yihuacomputer.fish.web.atm.format.DeviceBoxReportMsg;


@Controller
@RequestMapping("/msg/reportboxdetail")
public class BoxDetailController {
	private Logger logger = LoggerFactory.getLogger(BoxDetailController.class);

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;
	
	@Autowired
	private IDeviceBoxDetailInfoService deviceBoxDetailInfoService;
	
	 /**
     * 接收设备定时状态报告
     *
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap acceptStatus(@RequestBody DeviceBoxReportMsg msg)
    {
        logger.info(String.format("collection transaction :[%s]",JsonUtils.toJson(msg)));

        ModelMap result = new ModelMap();
        result.addAttribute("Ret", "00");
        try{
        	IDevice device = deviceService.get(msg.getTermianlId());
        	if(device==null){
        		logger.error(String.format("collection boxdetail exception!boxdetail is [%s],device don't exist",JsonUtils.toJson(msg)));
        		return result;
        	}
        	IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.findByDeviceId(device.getId());
        	//钞箱信息不存在全部新建
        	if(deviceBoxInfo==null){
        		deviceBoxInfo = deviceBoxInfoService.make();
        		deviceBoxInfo.setDeviceId(device);
        		deviceBoxInfo.setBoxChange(true);
//        		List<IDeviceBoxDetailInfo> detailInfoList = new ArrayList<IDeviceBoxDetailInfo>();
        		List<BoxDetailReportMsg>  detailList = msg.getBoxdetailList();
        		for(BoxDetailReportMsg bdrm:detailList){
        			IDeviceBoxDetailInfo dbdi = deviceBoxDetailInfoService.make();
        			dbdi.setBoxType(bdrm.getBoxType());
        			dbdi.setCashId(bdrm.getBoxType());
        			dbdi.setCurrency(bdrm.getBoxType());
        			dbdi.setEffect(true);
        			dbdi.setMaxiNum(bdrm.getMaximum());
        			dbdi.setValue(bdrm.getValue());
        			dbdi.setNumber(bdrm.getNumber());
        			dbdi.setDeviceBoxInfo(deviceBoxInfo);
//        			detailInfoList.add(dbdi);
        			deviceBoxInfo.add(dbdi);
        		}
//        		deviceBoxInfo.setDeviceBoxDetails(detailInfoList);
        		deviceBoxInfoService.save(deviceBoxInfo);
        	}
        	//钞箱信息存在，更改钞箱明细的
        	else{
//        		deviceBoxDetailInfoService.updateBoxDetailEffect(deviceBoxInfo.getId());
        		IFilter filter = new Filter();
        		filter.eq("deviceBoxInfo.id", deviceBoxInfo.getId());
        		filter.eq("effect", true);
//        		List<IDeviceBoxDetailInfo> dbdiList = deviceBoxDetailInfoService.list(filter);
//        		Map<String,IDeviceBoxDetailInfo> dbdiMap = deviceBoxDetailInfoService.getCashIdMap(filter);
        		Map<String,IDeviceBoxDetailInfo> dbdiMap = new HashMap<String,IDeviceBoxDetailInfo>();
        		for(IDeviceBoxDetailInfo idbdi:deviceBoxInfo.getDeviceBoxDetails()){
        			dbdiMap.put(idbdi.getCashId(), idbdi);
        		}
        		List<BoxDetailReportMsg>  detailList = msg.getBoxdetailList();
//        		List<IDeviceBoxDetailInfo> detailInfoList = new ArrayList<IDeviceBoxDetailInfo>();
        		for(BoxDetailReportMsg bdrm:detailList){
        			IDeviceBoxDetailInfo dbdi = dbdiMap.get(bdrm.getId());
        			IDeviceBoxDetailInfo dbdiHist = dbdi;
        			if(dbdi==null){
        				dbdi = deviceBoxDetailInfoService.make();
        				dbdi.setBoxType(bdrm.getBoxType());
            			dbdi.setCashId(bdrm.getBoxType());
            			dbdi.setCurrency(bdrm.getBoxType());
            			dbdi.setEffect(true);
            			dbdi.setMaxiNum(bdrm.getMaximum());
            			dbdi.setValue(bdrm.getValue());
            			dbdi.setNumber(bdrm.getNumber());
            			dbdi.setDeviceBoxInfo(deviceBoxInfo);
            			deviceBoxInfo.add(dbdi);
        			}
        			else{
        				dbdi.setBoxType(bdrm.getBoxType());
            			dbdi.setCashId(bdrm.getBoxType());
            			dbdi.setCurrency(bdrm.getBoxType());
            			dbdi.setEffect(true);
            			dbdi.setMaxiNum(bdrm.getMaximum());
            			dbdi.setValue(bdrm.getValue());
            			dbdi.setNumber(bdrm.getNumber());
            			dbdi.setDeviceBoxInfo(deviceBoxInfo);
            			if(!dbdiHist.equals(dbdi)){
                			deviceBoxDetailInfoService.update(dbdi);
            			}
        			} 
            		deviceBoxInfoService.update(deviceBoxInfo);
        		}
        	}
        }catch(Exception e){
            logger.error(String.format("collection transaction exception![%s],transaction is [%s]",e,JsonUtils.toJson(msg)));
        }

        return result;
    }
}
