package com.yihuacomputer.fish.web.monitor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCard;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCardService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;
import com.yihuacomputer.fish.api.person.IOrganizationService;

/**
 * 交易监控
 * 
 * @author YiHUa
 * 
 */
@Component
@org.cometd.annotation.Service("TransMonitorCometd")
public class TransMonitorCometd {

	public static final String JOIN_CHANNEL = "/service/transaction/join";

	@Autowired
	private IMonitorService monitorService;

	@Autowired
	private IFilterService filterService;
	
    @Autowired
    private IOrganizationService orgService;
    
    @Autowired
    private IBlackListCardService blackListCardservice;
    
	/**
	 * 开始监控，将监控条件保存
	 * */

	@org.cometd.annotation.Listener(JOIN_CHANNEL)
	public void join(ServerSession remote, ServerMessage.Mutable message) {

		Object data = message.getData();
		String userId = remote.getId();
		if (data instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) data;
			

			IMonitorUser monitorUser = this.monitorService
					.makeMonitorUser(userId);
			monitorUser.setUserSession(remote);

			ITransationFilter transactionFilter = filterService
					.makeTransactionFilter();
			String orgId = (String)map.get("organizationId");
			String terminalId = (String)map.get("terminalId");//设备号
			String currency = (String)map.get("currency");//币种
			String creditAccount = (String)map.get("creditAccount");//对方账号
			String debitAccount = (String)map.get("debitAccount");//客户账号
			String startAmt = (String)map.get("startAmt");//金额上限
			String endAmt = (String)map.get("endAmt");//金额下限
	 		List<Long> orgList = orgService.listSubOrgId(orgId);
	 		transactionFilter.setSubOrg(orgList);
	 		
	 		List<String> blackCardNoList = new ArrayList<String>();
	 		
	 		if(map.get("blacklist")!=null){
	 			if(!((String)map.get("blacklist")).equals("0")){
	 				for(IBlackListCard blackCard : blackListCardservice.list()){
	 		              blackCardNoList.add(blackCard.getCardNo());
	 		           }
	 		 		   if(((String)map.get("blacklist")).equals("1")){
	 		 		      transactionFilter.setIsBlackCardList(true);
	 		 		   }else{
	 		 		      transactionFilter.setIsBlackCardList(false); 
	 		 		   }
	 			}else{
	 				blackCardNoList = null;
	 			}
	 		}else{
	 		   blackCardNoList = null;
	 		}
	 		transactionFilter.setBlackCardList(blackCardNoList);
	 		transactionFilter.setTerminalId(terminalId);
	 		transactionFilter.setCurrency(currency);
	 		transactionFilter.setCreditAccount(creditAccount);
	 		transactionFilter.setDebitAccount(debitAccount);
	 		transactionFilter.setStartAmt((startAmt==null||startAmt.isEmpty())?0:Double.parseDouble(startAmt));
	 		transactionFilter.setEndAmt((endAmt==null||endAmt.isEmpty())?0:Double.parseDouble(endAmt));	 		

			monitorUser.setTransFilter(transactionFilter);
			monitorService.addMonitorUser(monitorUser,MonitorUserType.Trans);
		}
	}	

}
