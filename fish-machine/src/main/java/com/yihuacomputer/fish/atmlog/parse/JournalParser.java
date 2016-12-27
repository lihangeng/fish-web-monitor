package com.yihuacomputer.fish.atmlog.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.atmlog.BizJournal;
import com.yihuacomputer.fish.api.atmlog.ICustomerCycle;
import com.yihuacomputer.fish.api.atmlog.ITransCycle;
import com.yihuacomputer.fish.atmlog.entity.AtmCycle;
import com.yihuacomputer.fish.atmlog.entity.CustomerCycle;
import com.yihuacomputer.fish.atmlog.entity.TransCycle;

/**
 * @author YiHua
 *
 */
@Service
public class JournalParser {
	
	private static Logger logger = LoggerFactory.getLogger(JournalParser.class);
	
	/*ATM周期开始匹配表达式*/
	private Pattern cashInPattern = null;
	
	/*客户周期开始匹配表达式*/	
	private Pattern customerPattern = null;
	
	/*交易周期开始匹配表达式*/
	private Pattern transPattern = null;
	
	
	/*交易金额匹配表达式*/
	private Pattern transAmountPattern = null;
	
	/* 交易时间匹配表达式*/
/*	private Pattern transTimePattern = null;*/
	
	/* 日期匹配表达式*/
	private Pattern dateTimePattern = null;
	
	/*交易帐号匹配表达式*/
	private Pattern transAccountPattern = null;
	
	/*交易类型匹配表达式*/
	private Pattern transTypePattern = null;
	
	/*主机应答码匹配表达式*/
	private Pattern hostRetcodePattern = null;
	
	/*主机流水号匹配表达式*/
	private Pattern hostSerialNoPattern = null;
	
	/*本地流水号匹配表达式*/
	private Pattern localSerialNoPattern = null;
	
	/*钞箱列表匹配表达式*/
	private Pattern transBoxlistPattern = null;
	
	private Properties pros = null;
	
	/**
	 * 初始化
	 */
	public JournalParser(){
		this.init();
	}
	
	/**
	 * 初始化
	 */
	@PostConstruct
	public void init(){
		pros = new Properties();
		InputStream ins = null;
		BufferedReader bf =null;
		try{
			ins = this.getClass().getResourceAsStream("/com/yihuacomputer/fish/atmlog/YiHuaJournalParser.propertise");			
			bf = new BufferedReader(new InputStreamReader(ins,"utf-8"));  
			pros.load(bf);		
			
			/*加载参数*/
			this.cashInPattern = Pattern.compile(pros.getProperty("CYCLE_BEGIN_PATTERN_TEXT"));
			this.customerPattern = Pattern.compile(pros.getProperty("CLIENT_BEGIN_PATTERN_TEXT"));
			this.dateTimePattern = Pattern.compile(pros.getProperty("CLIENT_INSERTCARD_PATTERN_TEXT"));
			this.transAccountPattern = Pattern.compile(pros.getProperty("TRANS_ACCOUNT_PATTERN_TEXT"));
			this.transPattern = Pattern.compile(pros.getProperty("TRANS_BEGIN_PATTERN_TEXT"));
			this.localSerialNoPattern = Pattern.compile(pros.getProperty("TRANS_TERMINALSERIAL_PATTERN_TEXT"));
			this.hostSerialNoPattern = Pattern.compile(pros.getProperty("TRANS_HOSTSERIAL_PATTERN_TEXT"));
			this.transTypePattern = Pattern.compile(pros.getProperty("TRANS_TYPE_PATTERN_TEXT"));
			this.transAmountPattern = Pattern.compile(pros.getProperty("TRANS_AMOUNT_PATTERN_TEXT"));
			this.hostRetcodePattern = Pattern.compile(pros.getProperty("TRANS_HOSTRETURN_PATTERN_TEXT"));
/*			this.transTimePattern = Pattern.compile(pros.getProperty("TRANS_TIME_PATTERN_TEXT"));*/
			this.transBoxlistPattern = Pattern.compile(pros.getProperty("TRANS_BOXLIST_PATTERN_TEXT"));
/*			this.transTimePattern = Pattern.compile(pros.getProperty("TRANS_TIME_PATTERN_TEXT"));*/
			
		}catch( Exception e ){
			logger.error(String.format("[%s]", e));
			return;
		}finally{
			if ( null != bf  ){
				try{
					bf.close();
				}catch(IOException e){
					logger.error(String.format("[%s]", e));
				}
			}
			if ( null != ins ){
				try {
					ins.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}
		}	
	}

	/**
	 * 读取日志行，并且判断日志类型
	 * @param content
	 * @return
	 */
	public BizJournal readLineJournalLog(String content) throws Exception{
		
		Matcher transMatch = transPattern.matcher(content);
		Matcher customerMatch = customerPattern.matcher(content);
		Matcher cashInMatch = cashInPattern.matcher(content);
		if(transMatch.find()){
			return BizJournal.TRANS;
		}else if(customerMatch.find()){
			return BizJournal.CUSTOMER;
		}else if(cashInMatch.find()){
			return BizJournal.CASHIN;
		}else{
			return null;
		}
	}
	

	/**
	 * 根据交易内容解析交易信息
	 * 
	 * @param content
	 * @return
	 */
	public TransCycle readTransBiz(String content){
		TransCycle trans = new TransCycle();
		
		//TODO 解析交易信息		
		Matcher transTerminalSerialMatch = localSerialNoPattern.matcher(content);
		Matcher transHostserialMatch = hostSerialNoPattern.matcher(content);
		Matcher transTypeMatch = transTypePattern.matcher(content);
		Matcher transAmountMatch = transAmountPattern.matcher(content);
		Matcher transHostreturnMatch = hostRetcodePattern.matcher(content);
		Matcher transBoxListMatch = transBoxlistPattern.matcher(content);
		if(transTerminalSerialMatch.find()){
		    trans.setTerminalSerial(transTerminalSerialMatch.group());
		}
		if(transHostserialMatch.find()){
		    trans.setHostserial(transHostserialMatch.group());
		}
		if(transTypeMatch.find()){
		    trans.setTransType(transTypeMatch.group());
		}
		if(transAmountMatch.find()){
		    trans.setTransAmount(transAmountMatch.group());
		}
		if(transHostreturnMatch.find()){
		    trans.setHostreturn(transHostreturnMatch.group());
		}
		if(transBoxListMatch.find()){
		    trans.setBoxList(transBoxListMatch.group());
		}
		return trans;
	}
	
	/**
	 * 根据客户插卡解析客户卡信息
	 * 
	 * @param content
	 * @return
	 */
	public CustomerCycle readCustomer(String content){
		CustomerCycle customer = new CustomerCycle();
		List<ITransCycle> trans = new ArrayList<ITransCycle>();
		customer.setTrans(trans);
		Matcher dateTimeMatch = dateTimePattern.matcher(content);
		Matcher transAccountMatch = transAccountPattern.matcher(content);
		if(dateTimeMatch.find()){
		    customer.setDateTime(dateTimeMatch.group());
		}
		if(transAccountMatch.find()){
		    customer.setAccount(transAccountMatch.group());
		}
		return customer;
	}

	/**
	 * 根据周期解析周期信息
	 * @param content
	 * @return
	 */
	public AtmCycle readCashIn(String content){
		AtmCycle atm = new AtmCycle();
		List<ICustomerCycle> customer = new ArrayList<ICustomerCycle>();
		atm.setCustomers(customer);
/*		Matcher cashInMatch = cashInPattern.matcher(content);*/
/*		if*/
		//TODO 解析ATM周期信息
		return atm;
	}
}
