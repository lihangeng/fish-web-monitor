package com.yihuacomputer.fish.monitor.service;

import com.yihuacomputer.domain.test.BindSessionInTest2;

//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class RetainCardServicerTest extends BindSessionInTest2 {

//	@Autowired
//	private IRetaincardService retaincardService;
//
//	@Autowired
//	private IDeviceService deviceService;
//
//	@Autowired
//	private IOrganizationService orgService;
//
//	@Test
//	public void test() {
//		/**
//		 * make方法
//		 */
//
//		IOrganization org1 = orgService.make();
//		org1.setCode("nonghang");
//		org1.setName("农业银行");
//		orgService.add(org1);
//
//		IOrganization org2 = orgService.make();
//		org2.setCode("xinjiekou");
//		org2.setName("新街口支行");
//		orgService.add(org2);
//
//		IRetaincard card1 = retaincardService.make();
//		card1.setTerminalId("A#0001");
//		card1.setAccountNo("666586985698132458");
////		card1.setCardDistributionBankGuid("2");
//		card1.setCardType(IDCardType.IDCARD);
//		card1.setCustomerName("张三");
//		card1.setCustomerPapers("326589658741256985");
//		card1.setCustomerPhone("13656589874");
////		card1.setOrgGuid(orgService.getByCode("nonghang").getGuid());
//		card1.setReason("超时");
//		card1.setStatus(CardStatus.ALREADY_RECEIVE);
//		card1.setTreatmentPeople("王小贱");
//		Date retainCardTime = DateUtils.getTimestamp("2012-03-07 01:00:00");
//		card1.setCardRetainTime(retainCardTime);
//		/**
//		 * add方法
//		 */
//		retaincardService.add(card1);
//
//		Date treatmentTime = DateUtils.getTimestamp("2012-03-08 12:00:00");
//		card1.setTreatmentTime(treatmentTime);
//
//		retaincardService.update(card1);
//
//		assertNotNull(card1.getTreatmentTime());
//
//
//
//		IDevice device = deviceService.make();
//		device.setOrganization(org1);
//		device.setTerminalId("A#0001");
//
//		DeviceExtend de = new DeviceExtend();
//
////		de.setAtmcSoft("atmcSoft");
////		de.setAwayFlag(AwayFlag.FROM_THE_LINE);
//		de.setBuyDate(new Date());
////		de.setCareLevel(CareLevel.EMPHASIS);
//		de.setCarrier("carrier");
////		de.setCashType(CashType.CASH);
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
////		de.setRegStatus(RegStatus.REGISTRATION);
//		de.setSerial("serial");
////		de.setSetupType(SetupType.LOBBY);
////		de.setSp("sp");
//		de.setStartDate(new Date());
//		de.setStopDate(new Date());
//		de.setTerminalId("A#0001");
////		de.setTrminate("trminate");
////		de.setWorkType(WorkType.COOPERATION);
//		de.setInstallDate(new Date());
//
//		device.setOrganization(orgService.getByCode("nonghang"));
//		device.setDeviceExtend(de);
//		device.setStatus(Status.OPENING);
//		device.setDevService(orgService.getByCode("nonghang"));
//		device.setAddress(String.format("nanjing-%d",1));
//		device.setDeviceExtend(de);
//		deviceService.add(device);
//
//		IRetaincard card2 = retaincardService.make();
//		card2.setTerminalId("0002");
//		card2.setAccountNo("0002");
////		card2.setCardDistributionBankGuid("2");
//		card2.setCardType(IDCardType.PASSPORT);
//		card2.setCustomerName("李四");
//		card2.setCustomerPapers("326546456456454");
//		card2.setReason("操作超时");
//		card2.setCustomerPhone("18965899856");
//		card2.setStatus(CardStatus.DESTORYED);
//		retaincardService.add(card2);
//
//		IFilter filter = new Filter();
//														//page方法 特殊写法！！！
//		filter.addFilterEntry(FilterFactory.like("retaincard.accountNo", "666586985698132458"));
//		/**
//		 * page方法
//		 */
////		IPageResult<IRetaincard> page = retaincardService.page(0, 10, filter, 1);
////		assertEquals(1, page.getTotal());
//
//		/**
//		 * get方法
//		 */
//		IRetaincard result = retaincardService.get(card1.getId());
//		assertEquals("A#0001", result.getTerminalId());
//
//		/**
//		 * public Iterable<IRetaincard> list()
//		 */
//		Iterable<IRetaincard> cards = retaincardService.list();
//		List<IRetaincard> list = EntityUtils.convert(cards);
//		assertEquals(2,list.size());
//
//		/**
//		 * Iterable<IRetaincard> list(IFilter filter)
//		 */
//		IFilter newFilter = new Filter();
//		newFilter.addFilterEntry(FilterFactory.like("accountNo", "666586985698132458"));
//		Iterable<IRetaincard> cardResult = retaincardService.list(newFilter);
//		List<IRetaincard> cardResultList = EntityUtils.convert(cardResult);
//		assertEquals(1,cardResultList.size());
//
//
////		IPageResult<IRetaincard> pageResult = retaincardService.page(0, 10, null, 1, CardStatus.ALREADY_RECEIVE, CardStatus.WAIT_RECEIVE);
////		assertEquals(1,pageResult.getTotal());
//	}
//
///*	@Test
//	public void t(){
//		String sql = "slect form ${aaa} a;djdlf ${aaa}";
//		sql.replace("${aaa}", "goid is a gird");
//		System.out.println(sql);
//		sql = sql.replaceAll("/\\{aaa\\}/", "goid is a gird");
//		System.out.println(sql);
//	}*/

}
