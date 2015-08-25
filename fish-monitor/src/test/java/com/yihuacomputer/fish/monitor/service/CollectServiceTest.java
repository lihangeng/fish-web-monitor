package com.yihuacomputer.fish.monitor.service;

import com.yihuacomputer.domain.test.BindSessionInTest2;

/**
 * 广告服务单元测试类
 *
 * @author xuxigang
 *
 */
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class CollectServiceTest extends BindSessionInTest2 {

//	@Autowired
//	private ICollectService collectService;
//
//	@Autowired
//	private IDeviceService deviceService;
//
//	@Autowired
//	private IOrganizationService orgService;
//
//	@Test
//	public void testCollectModuleStatus() {
//
//		/**
//		 * make方法
//		 */
//		IOrganization org1 = orgService.make();
//		org1.setCode("nonghang1");
//		org1.setName("农业银行");
//		orgService.add(org1);
//
//		IOrganization org2 = orgService.make();
//		org2.setCode("xinjiekou1");
//		org2.setName("新街口支行");
//		orgService.add(org2);
//
//		IDevice device = deviceService.make();
//		device.setOrganization(org1);
//		device.setTerminalId("A10012");
//
//		DeviceExtend de = new DeviceExtend();
//
//		// de.setAtmcSoft("atmcSoft");
//		// de.setAwayFlag(AwayFlag.FROM_THE_LINE);
//		de.setBuyDate(new Date());
//		// de.setCareLevel(CareLevel.EMPHASIS);
//		de.setCarrier("carrier");
//		// de.setCashType(CashType.CASH);
//		de.setCloseTime("10:10:10");
//		de.setCostInterest("costInterest");
//		de.setDecoration(1);
//		de.setDecorationCost(1);
//		de.setDepreciationLife(1);
//		de.setExpireDate(new Date());
//		de.setExpirePmDate(new Date());
//		de.setGovernanceCost(1);
//		de.setGovernanceRent(1);
//		de.setLastPmDate(new Date());
//		de.setMoneyCost(1);
//		de.setMoneyOrg("moneyOrg");
//		de.setNetCost(1);
//		de.setNetType(NetType.CABLE);
//		de.setOpenTime("10:10:10");
//		de.setPatrolPeriod(1);
//		de.setPowerCost(1);
//		de.setPrice(1);
//		// de.setRegStatus(RegStatus.REGISTRATION);
//		de.setSerial("serial");
//		// de.setSetupType(SetupType.LOBBY);
//		// de.setSp("sp");
//		de.setStartDate(new Date());
//		de.setStopDate(new Date());
//		de.setTerminalId("A10012");
//		// de.setTrminate("trminate");
//		// de.setWorkType(WorkType.COOPERATION);
//		de.setInstallDate(new Date());
//
//		device.setOrganization(orgService.getByCode("nonghang1"));
//		device.setDeviceExtend(de);
//		device.setStatus(Status.OPENING);
//		device.setDevService(orgService.getByCode("nonghang1",OrganizationType.MAINTAINER));
//		device.setAddress(String.format("nanjing-%d", 1));
//		device.setDeviceExtend(de);
//		deviceService.add(device);
//		IXfsStatus xfsStatus = new XfsStatus();
//
//		IStatusIdc idc = xfsStatus.makeStatusIdc();
//		idc.setStatus(DeviceStatus.Healthy);
//		idc.setCode("IDC000000000");
//
//		IStatusJpr jpr = xfsStatus.makeStatusJpr();
//		jpr.setStatus(DeviceStatus.Fatal);
//		jpr.setCode("JPR000000000");
//
//		IStatusRpr rpr = xfsStatus.makeStatusRpr();
//		rpr.setStatus(DeviceStatus.Healthy);
//		rpr.setCode("RPR000000000");
//
//		IStatusCdm cdm = xfsStatus.makeStatusCdm();
//		cdm.setStatus(DeviceStatus.Healthy);
//		cdm.setCode("CDM00000000");
//
//		IStatusCim cim = xfsStatus.makeStatusCim();
//		cim.setStatus(DeviceStatus.Healthy);
//		cim.setCode("CIM000000000");
//
//		IStatusSiu siu = xfsStatus.makeStatusSiu();
//		siu.setStatus(DeviceStatus.Healthy);
//		siu.setCode("SIU1001");
//
//		IStatusPin pin = xfsStatus.makeStatusPin();
//		pin.setStatus(DeviceStatus.Healthy);
//		pin.setCode("PIN000000000");
//
//		IStatusTtu ttu = xfsStatus.makeStatusTtu();
//		ttu.setStatus(DeviceStatus.Healthy);
//		ttu.setCode("TTU000000000");
//
//		IStatusIcc icc = xfsStatus.makeStatusIcc() ;
//		icc.setStatus(DeviceStatus.Healthy) ;
//		icc.setCode("ICC000000000") ;
//		icc.setHwCode("123444") ;
//		icc.setIccCurrCards(100) ;
//		icc.setCards(5) ;
//
//		IStatusFgp fgp = xfsStatus.makeStatusFgp();
//		fgp.setStatus(DeviceStatus.Healthy);
//		fgp.setCode("FGP000000000");
//		fgp.setHwCode("1111111") ;
//
//		IStatusIsc isc = xfsStatus.makeStatusIsc();
//		isc.setStatus(DeviceStatus.Healthy);
//		isc.setCode("ISC000000000");
//		isc.setHwCode("2222222222") ;
//
//		xfsStatus.setModStatus(DeviceStatus.Healthy);
//		xfsStatus.setBoxStatus(BoxStatus.Full);
//		xfsStatus.setStatusIdc(idc);
//		xfsStatus.setStatusJpr(jpr);
//		xfsStatus.setStatusRpr(rpr);
//		xfsStatus.setStatusCdm(cdm);
//		xfsStatus.setStatusCim(cim);
//		xfsStatus.setStatusSiu(siu);
//		xfsStatus.setStatusPin(pin);
//		xfsStatus.setStatusTtu(ttu);
//		xfsStatus.setStatusIcc(icc) ;
//		//collectService.initDeviceCollect(device);
//		collectService.collectModuleStatus("A10012", xfsStatus);
//	}

}
