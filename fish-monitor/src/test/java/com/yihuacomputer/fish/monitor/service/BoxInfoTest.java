package com.yihuacomputer.fish.monitor.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.monitor.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class BoxInfoTest {
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;
	
	@Autowired
	private IDeviceBoxDetailInfoService deviceBoxDetailInfoService;
	
	@Test
	public void test(){
		update();
	}
	
	public void add(){
		IDevice device = deviceService.get("13050001");
    	if(device==null){
    		return;
    	}
    	IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.findByDeviceId(device.getId());
    	//钞箱信息不存在全部新建
    	if(deviceBoxInfo==null){
    		deviceBoxInfo = deviceBoxInfoService.make();
    		deviceBoxInfo.setDeviceId(device);
    		deviceBoxInfo.setBoxChange(true);
//    		List<IDeviceBoxDetailInfo> detailInfoList = new ArrayList<IDeviceBoxDetailInfo>();
    		for(int i=0;i<2;i++){
    			IDeviceBoxDetailInfo dbdi = deviceBoxDetailInfoService.make();
    			dbdi.setBoxType(BoxType.BILLCASSETTE);
    			dbdi.setCashId("case"+i);
    			dbdi.setCurrency("cny");
    			dbdi.setEffect(true);
    			dbdi.setMaxiNum(2750);
    			dbdi.setValue(100);
    			dbdi.setNumber(1000);
    			dbdi.setDeviceBoxInfo(deviceBoxInfo);
//    			detailInfoList.add(dbdi);
    			deviceBoxInfo.add(dbdi);
    		}
//    		deviceBoxInfo.setDeviceBoxDetails(detailInfoList);
    		deviceBoxInfoService.save(deviceBoxInfo);
    	}
	}
	
	public void update(){
		IDevice device = deviceService.get("13050001");
    	if(device==null){
    		return ;
    	}
    	IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.findByDeviceId(device.getId());
    	if(null==deviceBoxInfo){
    		return;
    	}
    	System.out.println(JsonUtils.toJson(deviceBoxInfo));
    	//钞箱信息不存在全部新建
    	//钞箱信息存在，更改钞箱明细的
//    		deviceBoxDetailInfoService.updateBoxDetailEffect(deviceBoxInfo.getId());
    		IFilter filter = new Filter();
    		filter.eq("deviceBoxInfo.id", deviceBoxInfo.getId());
    		filter.eq("effect", true);
//    		List<IDeviceBoxDetailInfo> dbdiList = deviceBoxDetailInfoService.list(filter);
    		
    		Map<String,IDeviceBoxDetailInfo> dbdiMap = new HashMap<String,IDeviceBoxDetailInfo>();
    		for(IDeviceBoxDetailInfo idbdi:deviceBoxInfo.getDeviceBoxDetails()){
    			dbdiMap.put(idbdi.getCashId(), idbdi);
    		}
    		for(int i=2;i<5;i++){
    			IDeviceBoxDetailInfo dbdi = dbdiMap.get("case"+i);
    			IDeviceBoxDetailInfo dbdiHist = dbdi;
    			if(dbdi==null){
    				dbdi = deviceBoxDetailInfoService.make();
    				dbdi.setBoxType(BoxType.BILLCASSETTE);
        			dbdi.setCashId("case"+i);
        			dbdi.setCurrency("cny5");
        			dbdi.setEffect(true);
        			dbdi.setMaxiNum(2750);
        			dbdi.setValue(100);
        			dbdi.setNumber(1002);
        			dbdi.setDeviceBoxInfo(deviceBoxInfo);
        			deviceBoxInfo.add(dbdi);
    			}
    			else{
    				dbdi.setBoxType(BoxType.BILLCASSETTE);
        			dbdi.setCashId("case"+i);
        			dbdi.setCurrency("cny5");
        			dbdi.setEffect(true);
        			dbdi.setMaxiNum(2752);
        			dbdi.setValue(101);
        			dbdi.setNumber(1000);
        			dbdi.setDeviceBoxInfo(deviceBoxInfo);
        			if(!dbdiHist.equals(dbdi)){
            			deviceBoxDetailInfoService.update(dbdi);
        			}
//        			deviceBoxInfo.add(dbdi);
    			} 
//    			deviceBoxInfo.setDeviceBoxDetails(detailInfoList);
//    			deviceBoxDetailInfoService.update(dbdi);
    		}
    		deviceBoxInfoService.update(deviceBoxInfo);
	}
}
