package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.CardType;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.monitor.H2TestConfig;
import com.yihuacomputer.fish.monitor.MySQLTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class HistoryTransactionDataGeneralTest  extends BindSessionInTest2{

	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private ITransTypeService transTypeService;
	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IDayTradingVolumeService dayTradingVolumeService;
	@Autowired
	private IMonthDailyTradingVolumeService monthTradingVolumeService;
	
	@Autowired
	private IGenericDao dao;
	
	
	@Test
	@Ignore
	public void test(){
//		List<IDevice> deviceList = deviceService.list();
//		IFilter filter = new Filter();
//		filter.ne("inOutFlag", 0);
//		List<ITransType> transTypeList =transTypeService.list(filter);
//		generalTrans("2016-8-31",deviceList,transTypeList,730);
//		generalDailyTrans("2016-9-1",730);
//		generalMonthTrans("2016-9-1",24);
	}

	private void generalDailyTrans(String dateStr,int days){
		Date date = DateUtils.getDate(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		for(int i=0;i<days;i++){
			String dates = DateUtils.getDateShort(calendar.getTime());
			dayTradingVolumeService.generalDayTradingVolumeByDate(dates);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	private void generalMonthTrans(String dateStr,int month){
		Date date = DateUtils.getDate(dateStr);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -month);
		for(int i=0;i<month;i++){
			String dates = DateUtils.getDateShort(calendar.getTime());
			monthTradingVolumeService.generalMonthDailyTradingVolumeByDate(dates);
			calendar.add(Calendar.MONTH, 1);
		}
	}
	
	/**
	 * 指定时间生成当前时间前一个月的交易数据
	 * @param date
	 * @param deviceList
	 * @param transTypeList
	 */
	private void generalTrans(String dateStr,List<IDevice> deviceList,List<ITransType> transTypeList,int days){
		Date date = DateUtils.getDate(dateStr);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		List<ITransaction> transactionList = new ArrayList<ITransaction>();
		Random random = new Random();
		
		for(int i=0;i<days;i++){
			date = calendar.getTime();
			int transDate = Integer.parseInt(DateUtils.getDateShort(date));
			for(IDevice device:deviceList){
				for(ITransType transType:transTypeList){
					ITransaction transaction = transactionService.make();
					transaction.setAmt(random.nextInt(80000));
					transaction.setCardType(CardType.LOCAL_BANK);
					transaction.setCostTime(0l);
					transaction.setCreditAccount(i+device.getTerminalId()+transType.getId());
					transaction.setCurrency("CNY");
					transaction.setDateTime(date);
					transaction.setDebitAccount("0"+i+device.getTerminalId()+transType.getId());
					transaction.setHostRet("00");
					transaction.setLocalRet("00");
					transaction.setOrgCode("root");
					transaction.setOrgName("root");
					transaction.setTerminalId(device.getTerminalId());
					transaction.setTipFee(0.0);
					transaction.setTransCode(transType.getTransCode());
					transaction.setTransDate(transDate);
					transaction.setTransId(i+device.getTerminalId()+transType.getId());
					transactionList.add(transaction);
				}
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		dao.batchSave(transactionList);
	}
	
}
