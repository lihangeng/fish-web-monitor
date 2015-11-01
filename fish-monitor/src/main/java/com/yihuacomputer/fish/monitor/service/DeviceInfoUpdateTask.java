package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.ITempDevInfo;
import com.yihuacomputer.fish.api.device.ITempDevInfoService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.entity.Device;

@Service("devInfoUpdateJob")
@Transactional
public class DeviceInfoUpdateTask {
	private Logger logger = LoggerFactory
			.getLogger(DeviceInfoUpdateTask.class);

	@Autowired
	private IOrganizationService orgService;

	/**
	 * 型号接口
	 */
	@Autowired
	private IAtmTypeService typeService;

	@Autowired
	ITempDevInfoService tempDeviceService;

	@Autowired
	private IDeviceService deviceService;

	/*@Autowired
	private IOpenPlanDeviceRelation openPlanDeviceRelationService;

	@Autowired
	private IOpenPlanService openPlanService;

	@Autowired
	private ITempOpenPlanDevRelation tempDevPlanRelationService;*/

	@Autowired(required = false)
	private ICollectService collectService;

	public void deviceInfoUpdate() {
		try {
			System.out.println("deviceInfoUpdate"+"被调用");
			StringBuffer hql = new StringBuffer();
			List<Object> valueObj = new ArrayList<Object>();
			hql.append("select t from TempDevInfo t where t.effectiveDate = ?");
			String date = DateUtils.getDate(new Date());
			valueObj.add(DateUtils.getDate(date));
			List<ITempDevInfo> tempDevices = tempDeviceService.list(
					hql.toString(), valueObj);
			for (ITempDevInfo tempDev : tempDevices) {
				IDevice dev = deviceService.get(tempDev.getTerminalId());				
				if (dev == null) {
					dev = deviceService.make();
					dev = this.translate(tempDev,dev);
					if(tempDev.getDeviceId() != 0)
	    			{
						dev.setId(tempDev.getDeviceId());
	    			}
					deviceService.add(dev);
					tempDeviceService.remove(tempDev.getTerminalId());
					/*if (collectService != null) {
						collectService.initDeviceCollect(dev);
					}*/
				} else {
					dev= this.translate(tempDev,dev);
					deviceService.update(dev);
					tempDeviceService.remove(tempDev.getTerminalId());
				}
			}

	      } catch (Exception e) {
			logger.error(String
					.format("DeviceInfo update thread error![%s]", e));
		}

	}	
    public IDevice translate(ITempDevInfo tempDev ,IDevice device) {
        device.setAddress(tempDev.getAddress());
        device.setCashboxLimit(tempDev.getCashboxLimit());
        device.setIp(tempDev.getIp());
        device.setTerminalId(tempDev.getTerminalId());
        device.setVirtual(tempDev.getVirtual());
        device.setStatus(tempDev.getStatus());
        device.setAwayFlag(tempDev.getAwayFlag());
        device.setSetupType(tempDev.getSetupType());
        device.setWorkType(tempDev.getWorkType());
        device.setSerial(tempDev.getSerial());
        device.setNetType(tempDev.getNetType());        
        device.setInstallDate(tempDev.getInstallDate());
        device.setMac(tempDev.getMac());
        device.setOrganization(tempDev.getOrganization());
        device.setDevService(tempDev.getDevService());
        device.setStatus(tempDev.getStatus());
        return device;
    }



}
