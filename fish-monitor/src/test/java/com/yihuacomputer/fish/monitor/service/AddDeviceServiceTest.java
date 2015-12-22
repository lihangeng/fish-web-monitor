package com.yihuacomputer.fish.monitor.service;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.MySQLTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class AddDeviceServiceTest extends BindSessionInTest2
{
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IOrganizationService orgService;
	@Autowired
	private IAtmTypeService typeService;
	@Autowired
	private IHardwareService hardwareService;
	@Autowired
	private ISoftwareService softwareService;
	@Autowired
	private IXfsService xfsService;
	@Autowired
	private IRegistService registSerivce;
	@Test
	@Ignore
    public void addTest(){
		IOrganization serviceOrg = orgService.get("2");
		Date dateNow = new Date();
    	for(int i=1;i<10000;i++){

        	IDevice device = deviceService.make();
    		int guid = i%2000;
    		IOrganization org = orgService.get(String.valueOf(guid>2?guid:guid+2));
    		IAtmType  atmtype= typeService.get(1l);
			device.setOrganization(org);
			device.setDevService(serviceOrg);
			device.setDevType(atmtype);
			device.setStatus(DevStatus.OPEN);
			device.setCashboxLimit(1111);
			device.setIp(new IP((long)i));
			device.setTerminalId(getTerminalId(i));
			device.setAwayFlag(AwayFlag.LINE);
			device.setSetupType(SetupType.WEAR_WALL);
			device.setWorkType( WorkType.FROM_OPERATING);
			device.setNetType( NetType.CABLE);
			device.setInstallDate(dateNow);
//			hardwareService.init(device.getTerminalId());
//			softwareService.init(device.getTerminalId());
//			xfsService.initXfsStatus(device.getTerminalId());
//			xfsService.initXfsProp(device.getTerminalId());
//			registSerivce.init(device.getTerminalId());

			deviceService.add(device);
    	}
    }
    
    private String getTerminalId (int i ){
    	String number =  "00000000"+i;
    	return number.substring(number.length()-8);
    }
}
