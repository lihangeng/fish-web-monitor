/**
 *
 */
package com.yihuacomputer.fish.web.mock;

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
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.person.IOrganizationService;

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

	/**
	 * 初始化
	 */
	public void init() {
		// 初始化品牌信息
		initBrand();
		// 初始化设备分类信息
		initCatalog();
		// 初始化品牌类型信息(设备型号)
		initAtmType();
		// 初始化模块
		initModule();
		// 初始化设备
		initDevice();

	}

	private void initDevice() {
		for (int i = 1; i <= 1600; i++) {
			IDevice device = deviceService.make();
			device.setStatus(DevStatus.OPEN);
			device.setIp(new IP("172.18.30.10" + i));
			device.setTerminalId("ATM00" + i);
			if (i % 2 == 0) {
				device.setDevType(atmTypeService.get(0001));
			} else {
				device.setDevType(atmTypeService.get(0002));
			}
//			device.setNetType(NetType.CABLE);
//			device.setSerial("serial");
			device.setSetupType(SetupType.LOBBY);
			device.setWorkType(WorkType.COOPERATION);
			device.setOrganization(orgService.get("1"));
			device.setStatus(DevStatus.OPEN);
			device.setDevService(orgService.get("2"));
			device.setAddress(String.format("nanjing-%d", i));

			deviceService.add(device);
		}

	}

	private void initModule() {

		IAtmModule IDC = atmModuleService.make();
		IDC.setName("IDC");
		IDC.setNo("0001");
		IDC.setNote("读卡器模块");
		IDC.setCase(true);
		atmModuleService.add(IDC);

		IAtmModule JPR = atmModuleService.make();
		JPR.setName("JPR");
		JPR.setNo("0002");
		JPR.setNote("日志打印模块");
		JPR.setCase(true);
		atmModuleService.add(JPR);

		IAtmModule RPR = atmModuleService.make();
		RPR.setName("RPR");
		RPR.setNo("0003");
		RPR.setNote("凭条打印模块");
		RPR.setCase(true);
		atmModuleService.add(RPR);

		IAtmModule CDM = atmModuleService.make();
		CDM.setName("CDM");
		CDM.setNo("0004");
		CDM.setNote("取款模块");
		CDM.setCase(true);
		atmModuleService.add(CDM);

		IAtmModule CIM = atmModuleService.make();
		CIM.setName("CIM");
		CIM.setNo("0005");
		CIM.setNote("存款模块");
		CIM.setCase(true);
		atmModuleService.add(CIM);

		IAtmModule PIN = atmModuleService.make();
		PIN.setName("PIN");
		PIN.setNo("0006");
		PIN.setNote("密码键盘模块");
		PIN.setCase(true);
		atmModuleService.add(PIN);

		IAtmModule TTU = atmModuleService.make();
		TTU.setName("TTU");
		TTU.setNo("0007");
		TTU.setNote("文本终端模块");
		TTU.setCase(true);
		atmModuleService.add(TTU);

		IAtmModule SIU = atmModuleService.make();
		SIU.setName("SIU");
		SIU.setNo("0008");
		SIU.setNote("传感器模块");
		SIU.setCase(true);
		atmModuleService.add(SIU);
	}

	private void initAtmType() {
		IAtmType type = atmTypeService.make();
		type.setId(0001);
		type.setName("CSD6040w");
		type.setDevCatalog(atmCatalogService.get("02"));
		type.setDevVendor(atmBrandService.get(001));
		atmTypeService.add(type);

		type = atmTypeService.make();
		type.setId(0002);
		type.setName("CSD6040t");
		type.setDevCatalog(atmCatalogService.get("03"));
		type.setDevVendor(atmBrandService.get(002));
		atmTypeService.add(type);

		type = atmTypeService.make();
		type.setId(0003);
		type.setName("DB0001");
		type.setDevCatalog(atmCatalogService.get("01"));
		type.setDevVendor(atmBrandService.get(003));
		atmTypeService.add(type);

		type = atmTypeService.make();
		type.setId(0004);
		type.setName("DB0002");
		type.setDevCatalog(atmCatalogService.get("04"));
		type.setDevVendor(atmBrandService.get(001));
		atmTypeService.add(type);

		type = atmTypeService.make();
		type.setId(0005);
		type.setName("ZJ0007");
		type.setDevCatalog(atmCatalogService.get("05"));
		type.setDevVendor(atmBrandService.get(002));
		atmTypeService.add(type);

		type = atmTypeService.make();
		type.setId(0006);
		type.setName("ZJ0008");
		type.setDevCatalog(atmCatalogService.get("06"));
		type.setDevVendor(atmBrandService.get(003));
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
		catalog.setName("存取款循环机");
		atmCatalogService.add(catalog);

		catalog = atmCatalogService.make();
		catalog.setNo("04");
		catalog.setName("存取款一体机");
		atmCatalogService.add(catalog);

		catalog = atmCatalogService.make();
		catalog.setNo("05");
		catalog.setName("自助终端查询机");
		atmCatalogService.add(catalog);

		catalog = atmCatalogService.make();
		catalog.setNo("06");
		catalog.setName("自助终端票据打印机");
		atmCatalogService.add(catalog);

	}

	private void initBrand() {
		IAtmVendor brand = atmBrandService.make();
		brand.setId(001);
		brand.setCountry("中国深圳");
		brand.setHotline1("0751-2025000");
		brand.setHotline2("0751-5888585");
		brand.setAddress("雨花台区郁金香路");
		brand.setName("深圳怡化电脑有限公司");
		atmBrandService.add(brand);

		brand = atmBrandService.make();
		brand.setId(002);
		brand.setCountry("中国南京");
		brand.setHotline1("025-20250000");
		brand.setHotline2("025-58888585");
		brand.setAddress("荒落的小岛");
		brand.setName("迪堡有限公司");
		atmBrandService.add(brand);

		brand = atmBrandService.make();
		brand.setId(003);
		brand.setCountry("中国杭州");
		brand.setHotline1("0565-2020000");
		brand.setHotline2("0565-5888885");
		brand.setAddress("一个地方2号");
		brand.setName("紫金有限公司");
		atmBrandService.add(brand);
	}
}
