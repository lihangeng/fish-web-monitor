package com.yihuacomputer.fish.monitor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.CashInitPlanCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUnique;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUniqueService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRule;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRuleService;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolume;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitPlanInfo;

@Service
@Transactional
public class CashInitPlanInfoService implements ICashInitPlanInfoService {

	private Logger logger = LoggerFactory.getLogger(CashInitPlanInfoService.class);
	@Autowired
	private IGenericDao dao;

	@Autowired
	private ICashInitRuleService deviceBoxInitRuleService;

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;

	@Autowired
	private ICashInitUniqueService cashInitUniqueService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;

	@Override
	public void generalCashInitPlan() {
		IOrganization organization = orgService.get("1");
		String date = DateUtils.getTodayDate();
		generalCashInitPlan(organization, date);
	}

	public void generalCashInitPlan(IOrganization organization, String cashInitDate) {
		// 获取加钞计划机构单位(总分支行)
		OrganizationLevel orgLevel = OrganizationLevel.TOTAL;
		try {
			orgLevel = OrganizationLevel.getById(CashInitPlanCfg.getCashInitPlanUtils());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// 获取规定的清机加钞周期
		int cashInitDays = CashInitPlanCfg.getCashInitDays();
		// 获取日均交易预警金额
		long tradingVolumeIn = CashInitPlanCfg.getDaliyTradingVolumeCashIn();
		long tradingVolumeOut = CashInitPlanCfg.getDaliyTradingVolumeBill();
		// 获取日均交易信息
		Date generalDate = DateUtils.getDateShort(cashInitDate);
		String lastMonthYear = DateUtils.lastMonthFormatWithYM(generalDate);
		IFilter filterVolume = new Filter();
		filterVolume.eq("transMonth", Integer.parseInt(lastMonthYear));
		Map<String, IMonthDailyTradingVolume> monthDailyVolume = monthDailyTradingVolumeService.getMonthDailyMap(filterVolume);

		IFilter filter = new Filter();
		filter.like("orgFlag", organization.getOrgFlag() + "%");
		filter.eq("organizationLevel", orgLevel);
		// 获取当前机构下可以进行操作加钞操作的机构
		List<IOrganization> orgList = orgService.listMatching(filter);
		// 获取当前加钞规则状态
		ICashInitRule cashLimitRule = deviceBoxInitRuleService.get(BoxInitRuleType.CASHLIMIT);
		ICashInitRule daysLimitRule = deviceBoxInitRuleService.get(BoxInitRuleType.DAYSLIMIT);
		ICashInitRule tradingVolumeRule = deviceBoxInitRuleService.get(BoxInitRuleType.TRADINGVOLUME);
		for (IOrganization org : orgList) {
			// 获取所有加钞历史
			Map<String, ICashInitUnique> cashInitMap = cashInitUniqueService.getCashInitMap(org);
			ICashInitPlanInfo cashInitPlanInfo = this.make();
			Map<String, IDevice> initDeviceMap = new HashMap<String, IDevice>();
			// 当前加钞总金额
			double amt = 0;
			// （设备钞箱阈值预警）除非设置为不启用，否则按规则进行生成加钞计划
			if (null == cashLimitRule || cashLimitRule.isStartUsing()) {
				amt = getCashLimit(cashInitMap,monthDailyVolume,initDeviceMap,cashInitPlanInfo,org, amt, cashInitDays, tradingVolumeOut);
			}
			// （清机加钞时间超期）除非设置为不启用，否则按规则进行生成加钞计划
			if (null == daysLimitRule || daysLimitRule.isStartUsing()) {
				amt = getCashInitDays(cashInitMap,monthDailyVolume,initDeviceMap,cashInitPlanInfo,org, amt, cashInitDays, tradingVolumeOut);
			}
			// 加载符合日均交易预警的设备
			if (tradingVolumeRule == null||tradingVolumeRule.isStartUsing() ) {
				amt = getTradingVolume(cashInitMap,monthDailyVolume,initDeviceMap,cashInitPlanInfo,org, amt, cashInitDays, tradingVolumeOut,tradingVolumeIn);
			}
			//当前机构没有加钞的设备，不生成加钞计划
			if(initDeviceMap.size()==0){
				continue;
			}
			cashInitPlanInfo.setOrg(org);
			cashInitPlanInfo.setAmt(amt);
			cashInitPlanInfo.setDate(Integer.parseInt(cashInitDate));
			cashInitPlanInfo.setCashInitCode(this.getCashInitPlanInfoNextCode(cashInitDate));
			this.save(cashInitPlanInfo);
		}
	}
	
	/**
	 * 获取钞箱预警加钞设备
	 * @param cashInitMap
	 * @param monthDailyVolume
	 * @param initDeviceMap
	 * @param cashInitPlanInfo
	 * @param org
	 * @param amt
	 * @param cashInitDays
	 * @param tradingVolumeOut
	 * @return
	 */
	private double getCashLimit(Map<String,ICashInitUnique>cashInitMap,Map<String,IMonthDailyTradingVolume>monthDailyVolume,
			Map<String,IDevice>initDeviceMap,ICashInitPlanInfo cashInitPlanInfo,IOrganization org,double amt,int cashInitDays,long tradingVolumeOut){

		List<IDeviceBoxInfo> deviceBoxInfoList = deviceBoxInfoService.getCashLimitRuleDevice(org.getOrgFlag());
		for (IDeviceBoxInfo deviceBoxInfo : deviceBoxInfoList) {
			IDevice device = deviceBoxInfo.getDeviceId();
			// 此机器上次加钞信息
			ICashInitUnique cashInitUnique = cashInitMap.get(device.getTerminalId());
			// 此机器日均交易
			long dailyVolume = getDailyTradingVolume(monthDailyVolume,deviceBoxInfo,device.getTerminalId(),cashInitDays,tradingVolumeOut);
			amt += dailyVolume;
			initCashInitPlanDeviceInfo(BoxInitRuleType.CASHLIMIT,dailyVolume,cashInitPlanInfo,device,cashInitUnique);
			initDeviceMap.put(device.getTerminalId(), device);
		}
		return amt;
	}

	/**
	 * 初始化设备加钞信息
	 * @param type
	 * @param tradingVolume
	 * @param cashInitPlanInfo
	 * @param device
	 * @param cashInitUnique
	 */
	private void initCashInitPlanDeviceInfo(BoxInitRuleType type, long tradingVolume,ICashInitPlanInfo cashInitPlanInfo,IDevice device,ICashInitUnique cashInitUnique){
		ICashInitPlanDeviceInfo cashInitPlanDeviceInfo = cashInitPlanDeviceInfoService.make();
		cashInitPlanDeviceInfo.setActualAmt(tradingVolume);
		cashInitPlanDeviceInfo.setAdviceAmt(tradingVolume);
		cashInitPlanDeviceInfo.setAddress(device.getAddress());
		cashInitPlanDeviceInfo.setFlag(BoxInitRuleType.CASHLIMIT);
		cashInitPlanDeviceInfo.setDevType(device.getDevType().getName());
		cashInitPlanDeviceInfo.setAwayFlag(device.getAwayFlag());
		if (cashInitUnique != null) {
			cashInitPlanDeviceInfo.setLastAmt(cashInitUnique.getAmt());
			cashInitPlanDeviceInfo.setLastDate(cashInitUnique.getDate());
		}
		cashInitPlanDeviceInfo.setOrgName(device.getOrganization().getName());
		cashInitPlanDeviceInfo.setTerminalId(device.getTerminalId());
		cashInitPlanDeviceInfo.setCashInitPlanInfo(cashInitPlanInfo);
		cashInitPlanInfo.add(cashInitPlanDeviceInfo);
	}
	
	/**
	 * 获取上次加钞时间过期设备
	 * @param cashInitMap
	 * @param monthDailyVolume
	 * @param initDeviceMap
	 * @param cashInitPlanInfo
	 * @param org
	 * @param amt
	 * @param cashInitDays
	 * @param tradingVolumeOut
	 * @return
	 */
	private double getCashInitDays(Map<String,ICashInitUnique>cashInitMap,Map<String,IMonthDailyTradingVolume>monthDailyVolume,
			Map<String,IDevice>initDeviceMap,ICashInitPlanInfo cashInitPlanInfo,IOrganization org,double amt,int cashInitDays,long tradingVolumeOut){
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(org.getOrgFlag());
		IFilter cashInitFilter = new Filter();
		String date = DateUtils.getDate(DateUtils.getDate(-cashInitDays));
		cashInitFilter.lt("date", date + " 00:0:00");
		List<ICashInitUnique> cashInitList = cashInitUniqueService.getCashInitByOrg(org, cashInitDays);
		for (ICashInitUnique cashInitUnique : cashInitList) {
			// 如果己经加入到加钞计划中,则跳过不做处理
			if (initDeviceMap.get(cashInitUnique.getTerminalId()) != null) {
				continue;
			}
			IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoMap.get(cashInitUnique.getTerminalId());
			
			IDevice device = null;
			if (deviceBoxInfo != null) {
				device = deviceBoxInfo.getDeviceId();
			} else {
				device = deviceService.get(cashInitUnique.getTerminalId());
			}
			// 如果设备不存在，不做处理
			if (device == null) {
				continue;
			}
			// 此机器日均交易
			long dailyVolume = getDailyTradingVolume(monthDailyVolume,deviceBoxInfo,device.getTerminalId(),cashInitDays,tradingVolumeOut);
			
			amt += dailyVolume;
			initCashInitPlanDeviceInfo(BoxInitRuleType.DAYSLIMIT,dailyVolume,cashInitPlanInfo,device,cashInitUnique);
			initDeviceMap.put(device.getTerminalId(), device);
		}
		return amt;
	}
	
	/**
	 * 获取不能维持一天运行的设备(和日均交易量比较)
	 * @param cashInitMap
	 * @param monthDailyVolume
	 * @param initDeviceMap
	 * @param cashInitPlanInfo
	 * @param org
	 * @param amt
	 * @param cashInitDays
	 * @param tradingVolumeOut
	 * @param tradingVolumeIn
	 * @return
	 */
	private double getTradingVolume(Map<String,ICashInitUnique>cashInitMap,Map<String,IMonthDailyTradingVolume>monthDailyVolume,
			Map<String,IDevice>initDeviceMap,ICashInitPlanInfo cashInitPlanInfo,IOrganization org,double amt,int cashInitDays,long tradingVolumeOut,long tradingVolumeIn){

		Map<String, IDeviceBoxInfo> orgDeviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(org.getOrgFlag());
		for (Map.Entry<String, IDeviceBoxInfo> entry : orgDeviceBoxInfoMap.entrySet()) {
			String terminalId = entry.getKey();
			IDeviceBoxInfo deviceBoxInfo = entry.getValue();
			// 此机器上次加钞信息
			ICashInitUnique cashInitUnique = cashInitMap.get(terminalId);
			// 如果己经加入到加钞计划中,则跳过不做处理
			if (initDeviceMap.get(terminalId) != null) {
				continue;
			}
			long dailyVolume = getDailyTradingVolume(monthDailyVolume,deviceBoxInfo,terminalId,cashInitDays,tradingVolumeOut);
			amt += dailyVolume;
			if (deviceBoxInfo.getBillValue() < dailyVolume||(deviceBoxInfo.getCashInValue()+tradingVolumeIn)>deviceBoxInfo.getDefaultCashIn()) {
				IDevice device = deviceService.get(terminalId);
				if (device == null) {
					continue;
				}
				initCashInitPlanDeviceInfo(BoxInitRuleType.TRADINGVOLUME,dailyVolume,cashInitPlanInfo,device,cashInitUnique);
				initDeviceMap.put(device.getTerminalId(), device);
			}

		}
		return amt;
	}
	
	private long getDailyTradingVolume(Map<String,IMonthDailyTradingVolume>monthDailyVolume,IDeviceBoxInfo deviceBoxInfo,String terminalId,int cashInitDays,long tradingVolumeOut){
		IMonthDailyTradingVolume monthDailyTradingVolume = monthDailyVolume.get(terminalId);
		long dailyVolume = 0;
		if (monthDailyTradingVolume != null) {
			if (monthDailyTradingVolume.getLastYearAmtOutAvg() == 0) {
				dailyVolume = (long) monthDailyTradingVolume.getMonthAmtOutAvg() * cashInitDays;
			} else {
				dailyVolume = (long) monthDailyTradingVolume.getLastYearAmtOutAvg() + (long) monthDailyTradingVolume.getMonthAmtOutAvg();
				dailyVolume = dailyVolume / 2 * cashInitDays;
			}
		} else {
			dailyVolume = tradingVolumeOut;
		}
		if (deviceBoxInfo != null) {
			dailyVolume = deviceBoxInfo.getDefaultBill() > dailyVolume ? dailyVolume : deviceBoxInfo.getDefaultBill();
		} 
		return dailyVolume;
	}
	
	@Override
	public ICashInitPlanInfo make() {
		return new CashInitPlanInfo();
	}

	/**
	 * 获取指定日期内的加钞计划单号,如果之前没有单号,直接返回日期+"0000"否则返回最大单号+1
	 * 
	 * @param cashInitDate
	 * @return
	 */
	public String getCashInitPlanInfoNextCode(String cashInitDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select cashInitCode from ").append(CashInitPlanInfo.class.getSimpleName()).append(" where date=? order by cashInitCode desc");
		List<String> list = dao.findByHQL(sb.toString(), new Object[] { Integer.parseInt(cashInitDate) });
		if (null == list || list.size() == 0) {
			return cashInitDate + "0000";
		}
		long nowCode = Long.parseLong(list.get(0));
		return ++nowCode + "";
	}

	@Override
	public ICashInitPlanInfo save(ICashInitPlanInfo cashInitPlanInfo) {
		return dao.save(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanInfo update(ICashInitPlanInfo cashInitPlanInfo) {
		return dao.update(cashInitPlanInfo);
	}

	@Override
	public void remove(ICashInitPlanInfo cashInitPlanInfo) {
		dao.delete(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanInfo get(long id) {
		return dao.get(id, CashInitPlanInfo.class);
	}

	@Override
	public IPageResult<ICashInitPlanInfo> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, CashInitPlanInfo.class);
	}

	public List<ICashInitPlanInfo> list(IFilter filter) {
		return dao.findByFilter(filter, ICashInitPlanInfo.class);
	}
}
