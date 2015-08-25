package com.yihuacomputer.fish.web.mock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IDCardType;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.person.IOrganizationService;

public class RetaincardDataLoader {

	@Autowired
	private IRetaincardService retaincardService;
	
	@Autowired
	private IOrganizationService orgService;

	public void init() {
		IRetaincard card1 = retaincardService.make();
		card1.setAccountNo("666586985698132458");
		card1.setCardDistributionBank("农业银行");
//		card1.setCardDistributionBankGuid("17");
		card1.setCardType(IDCardType.IDCARD);
		card1.setCustomerName("张三");
		card1.setCustomerPapers("326589658741256985");
		card1.setCustomerPhone("13656589874");
//		card1.setOrgGuid(orgService.getByCode("yihua").getGuid());
		card1.setReason("超时");
		card1.setStatus(CardStatus.ALREADY_RECEIVE);
		card1.setTerminalId("0001");
		card1.setTreatmentPeople("王小贱");
		Date retainCardTime = DateUtils.getTimestamp("2012-03-07 01:00:00");
		Date treatmentTime = DateUtils.getTimestamp("2012-03-08 12:00:00");
		card1.setCardRetainTime(retainCardTime);
		card1.setTreatmentTime(treatmentTime);
		retaincardService.add(card1);

		IRetaincard card2 = retaincardService.make();
		card2.setAccountNo("666586985698132422");
		card2.setCardDistributionBank("农业银行新街口支行");
//		card2.setCardDistributionBankGuid("19");
//		card2.setOrgGuid(orgService.getByCode("yihua").getGuid());
		card2.setReason("超时");
		card2.setStatus(CardStatus.WAIT_RECEIVE);
		card2.setTerminalId("0004");
		Date retainCardTime1 = DateUtils.getTimestamp("2012-03-07 00:00:00");
		card2.setCardRetainTime(retainCardTime1);
		retaincardService.add(card2);
	}
}
