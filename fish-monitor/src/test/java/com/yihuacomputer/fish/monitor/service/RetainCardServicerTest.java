package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IDCardType;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.H2TestConfig;

//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class RetainCardServicerTest extends BindSessionInTest2 {

//	@Autowired
	private IRetaincardService retaincardService;

//	@Autowired
	private IDeviceService deviceService;

//	@Autowired
	private IOrganizationService orgService;

//	@Test
	public void test() {
		/**
		 * make方法
		 */

		IOrganization org1 = orgService.make();
		org1.setCode("nonghang");
		org1.setName("农业银行");
		orgService.add(org1);

		IRetaincard card1 = retaincardService.make();
		card1.setTerminalId("A#0001");
		card1.setAccountNo("666586985698132458");
		card1.setCardType(IDCardType.IDCARD);
		card1.setCustomerName("张三");
		card1.setCustomerPapers("326589658741256985");
		card1.setCustomerPhone("13656589874");
		card1.setReason("超时");
		card1.setStatus(CardStatus.ALREADY_RECEIVE);
		card1.setTreatmentPeople("王小贱");
		card1.setCardRetainTime(DateUtils.getDate(-2));
		/**
		 * add方法
		 */
		retaincardService.add(card1);
		
		List<Object> lists = retaincardService.statisticsReatainCardTrend(-4);
		for(Object object : lists){
			Object[] obj = (Object[])object;
			System.out.println(obj[0].toString());
			System.out.println(obj[1].toString());
		}
	}

//	@Test
	public void t(){
		String sql = "slect form ${aaa} a;djdlf ${aaa}";
		sql.replace("${aaa}", "goid is a gird");
		System.out.println(sql);
		sql = sql.replaceAll("/\\{aaa\\}/", "goid is a gird");
		System.out.println(sql);
	}

}
