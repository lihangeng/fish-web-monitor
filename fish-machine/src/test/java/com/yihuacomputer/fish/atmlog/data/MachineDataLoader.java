/**
 * 
 */
package com.yihuacomputer.fish.atmlog.data;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.entity.DeviceExtend;

/**
 * @author dell
 * 
 */
public class MachineDataLoader {
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IAtmBrandService atmBrandService;

	@Autowired
	private IAtmTypeService atmTypeService;

	@Autowired
	private IAtmCatalogService atmCatalogService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IAtmModuleService atmModuleService;
	
	
	public void init() {
		orgService.add("yihua", "深圳怡化");
		
		// 初始化品牌信息
		initBrand();
		// 初始化设备分类信息
		initCatalog();
		// 初始化品牌类型信息(设备型号)
		initAtmBrandType();
		// 初始化模块
		initModule();
		// 初始化设备
		initDevice();
		// 初始化区域
		// initArea();
		
	}


	private void initDevice() {
		for (int i = 1; i <= 40; i++) {
			IDevice device = deviceService.make();
			int ip = (i == 1 ? 51 : i);
			device.setIp(new IP("192.168.0." + ip));
			device.setTerminalId("000" + i);
//			if (i % 2 == 0) {
//				device.setDevType(atmTypeService.get("01"));
//			} else {
//				device.setDevType(atmTypeService.get("02"));
//			}
			DeviceExtend de = new DeviceExtend();

			de.setBuyDate(new Date());
			de.setCarrier("carrier");
			de.setCloseTime("10:10:10");
			de.setCostInterest("costInterest");
			de.setDecoration(1);
			de.setDecorationCost(1);
			de.setDepreciationLife(1);
			de.setExpireDate(new Date());
			de.setExpirePmDate(new Date());
			de.setGovernanceCost(1);
			de.setGovernanceRent(1);
			de.setLastPmDate(new Date());
			de.setMoneyCost(1);
			de.setMoneyOrg("moneyOrg");
			de.setNetCost(1);
			de.setNetType(NetType.CABLE);
			de.setOpenTime("10:10:10");
			de.setPatrolPeriod(1);
			de.setPowerCost(1);
			de.setPrice(1);
			de.setSerial("serial");
			de.setStartDate(new Date());
			de.setStopDate(new Date());
			de.setTerminalId("000" + i);
			de.setInstallDate(new Date());

			device.setOrganization(orgService.getByCode("yihua"));
			device.setDeviceExtend(de);
			device.setStatus(Status.OPENING);
			device.setDevService(orgService.getByCode("yihua"));
			device.setAddress(String.format("nanjing-%d", i));
			deviceService.add(device);
		}
	}

	private void initModule() {

		IAtmModule IDC = atmModuleService.make();
		IDC.setName("IDC");
		IDC.setNo("0001");
		IDC.setNote("读卡器模块");
		atmModuleService.add(IDC);

		IAtmModule JPR = atmModuleService.make();
		JPR.setName("JPR");
		JPR.setNo("0002");
		JPR.setNote("日志打印模块");
		atmModuleService.add(JPR);

		IAtmModule RPR = atmModuleService.make();
		RPR.setName("RPR");
		RPR.setNo("0003");
		RPR.setNote("凭条打印模块");
		atmModuleService.add(RPR);

		IAtmModule CDM = atmModuleService.make();
		CDM.setName("CDM");
		CDM.setNo("0004");
		CDM.setNote("取款模块");
		atmModuleService.add(CDM);

		IAtmModule CIM = atmModuleService.make();
		CIM.setName("CIM");
		CIM.setNo("0005");
		CIM.setNote("存款模块");
		atmModuleService.add(CIM);

		IAtmModule PIN = atmModuleService.make();
		PIN.setName("PIN");
		PIN.setNo("0006");
		PIN.setNote("密码键盘模块");
		atmModuleService.add(PIN);

		IAtmModule TTU = atmModuleService.make();
		TTU.setName("TTU");
		TTU.setNo("0007");
		TTU.setNote("文本终端模块");
		atmModuleService.add(TTU);

		IAtmModule SIU = atmModuleService.make();
		SIU.setName("SIU");
		SIU.setNo("0008");
		SIU.setNote("传感器模块");
		atmModuleService.add(SIU);
	}

	private void initAtmBrandType() {
		IAtmType type = atmTypeService.make();
//		type.setNo("01");
		type.setName("yh6040w");
		type.setWeight("2000");
		type.setDevCatalog(atmCatalogService.get("02"));
//		type.setDevVendor(atmBrandService.get("YH"));
		atmTypeService.add(type);

		type = atmTypeService.make();
//		type.setNo("02");
		type.setName("yh6040t");
		type.setWeight("2000");
		type.setDevCatalog(atmCatalogService.get("03"));
//		type.setDevVendor(atmBrandService.get("YH"));
		atmTypeService.add(type);
	}

	private void initCatalog() {
		IAtmCatalog catalog = atmCatalogService.make();
		catalog.setNo("01");
		catalog.setName("存款机");
		atmCatalogService.add(catalog);

		catalog = atmCatalogService.make();
		catalog.setNo("02");
		catalog.setName("取款机");
		atmCatalogService.add(catalog);

		catalog = atmCatalogService.make();
		catalog.setNo("03");
		catalog.setName("存取款一体机");
		atmCatalogService.add(catalog);
	}

	private void initBrand() {
		IAtmVendor brand = atmBrandService.make();
//		brand.setNo("YH");
		brand.setAddress("雨花台区郁金香路");
		brand.setName("深圳怡化电脑有限公司");
		atmBrandService.add(brand);

		brand = atmBrandService.make();
//		brand.setNo("DiBao");
		brand.setAddress("荒落的小岛");
		brand.setName("迪堡有限公司");
		atmBrandService.add(brand);

		brand = atmBrandService.make();
//		brand.setNo("Zijin");
		brand.setAddress("一个地方2号");
		brand.setName("紫金有限公司");
		atmBrandService.add(brand);
	}
}
