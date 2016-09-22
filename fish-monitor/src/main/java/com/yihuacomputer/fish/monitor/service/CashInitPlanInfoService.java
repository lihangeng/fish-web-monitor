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
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
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
	
	@Autowired
	private IParamService paramService;
	
	@Override
	public void generalCashInitPlan() {
		IOrganization organization = orgService.get("1");
		String date = DateUtils.getTodayDate();
		generalCashInitPlan(organization,date);
	}
	
	public void generalCashInitPlan(IOrganization organization,String cashInitDate){
		
		IParam cashInitUnitParam = paramService.getParam("cashinit_orglevel");
		OrganizationLevel orgLevel = OrganizationLevel.TOTAL;
		IParam cashInitDaysParam = paramService.getParam("cashinit_days");
		int cashInitDays = 7;
		//获取加钞天数
		if(cashInitDaysParam!=null){
			try{
				cashInitDays = Integer.parseInt(cashInitDaysParam.getParamValue());
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		//获取加钞计划机构单位(总分支行)
		if(cashInitUnitParam!=null){
			try{
				orgLevel = OrganizationLevel.getById(Integer.parseInt(cashInitUnitParam.getParamValue()));
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		//获取日均交易信息
		Date generalDate = DateUtils.getDateShort(cashInitDate);
		String lastMonthYear = DateUtils.lastMonthFormatWithYM(generalDate);
		IFilter filterVolume  = new Filter();
		filterVolume.eq("transMonth", Integer.parseInt(lastMonthYear));
		Map<String,IMonthDailyTradingVolume> monthDailyVolume = monthDailyTradingVolumeService.getMonthDailyMap(filterVolume);
		
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(organization.getOrgFlag());
		IFilter filter = new Filter();
		filter.like("orgFlag", organization.getOrgFlag()+"%");
		filter.eq("organizationLevel", orgLevel);
		//获取当前机构下可以进行操作加钞操作的机构
		List<IOrganization> orgList = orgService.listMatching(filter);
		//获取当前加钞规则状态
		ICashInitRule cashLimitRule = deviceBoxInitRuleService.get(BoxInitRuleType.CASHLIMIT);
		ICashInitRule daysLimitRule = deviceBoxInitRuleService.get(BoxInitRuleType.DAYSLIMIT);
		for(IOrganization org:orgList){

			//获取所有加钞历史
			Map<String, ICashInitUnique> cashInitMap = cashInitUniqueService.getCashInitMap(org);
			ICashInitPlanInfo cashInitPlanInfo = this.make();
			Map<String,IDevice> initDeviceMap = new HashMap<String,IDevice>();
			//当前加钞总金额
			double amt=0;
			//除非设置为不启用，否则按规则进行生成加钞计划
			if(null==cashLimitRule||cashLimitRule.isStartUsing()){
				List<IDeviceBoxInfo> deviceBoxInfoList = deviceBoxInfoService.getCashLimitRuleDevice(org.getOrgFlag());
				for(IDeviceBoxInfo deviceBoxInfo:deviceBoxInfoList){
					ICashInitPlanDeviceInfo cashInitPlanDeviceInfo = cashInitPlanDeviceInfoService.make();
					IDevice device = deviceBoxInfo.getDeviceId();
					//此机器上次加钞信息
					ICashInitUnique cashInitUnique = cashInitMap.get(device.getTerminalId());
					IMonthDailyTradingVolume monthDailyTradingVolume = monthDailyVolume.get(device.getTerminalId());
					long dailyVolume = 0;
					if(monthDailyTradingVolume!=null){
						if(monthDailyTradingVolume.getLastYearAmtOutAvg()==0){
							dailyVolume = (long)monthDailyTradingVolume.getMonthAmtOutAvg()*cashInitDays;
						}
						else{
							dailyVolume = (long)monthDailyTradingVolume.getLastYearAmtOutAvg()+(long)monthDailyTradingVolume.getMonthAmtOutAvg();
							dailyVolume = dailyVolume/2*cashInitDays;
						}
					}
					dailyVolume = deviceBoxInfo.getDefaultBill()>dailyVolume?dailyVolume:deviceBoxInfo.getDefaultBill();
					amt+=dailyVolume;
					cashInitPlanDeviceInfo.setActualAmt(dailyVolume);
					cashInitPlanDeviceInfo.setAdviceAmt(dailyVolume);
					cashInitPlanDeviceInfo.setAddress(device.getAddress());
					cashInitPlanDeviceInfo.setFlag(BoxInitRuleType.CASHLIMIT);
					cashInitPlanDeviceInfo.setDevType(device.getDevType().getName());
					cashInitPlanDeviceInfo.setAwayFlag(device.getAwayFlag());
					if(cashInitUnique!=null){
						cashInitPlanDeviceInfo.setLastAmt(cashInitUnique.getAmt());
						cashInitPlanDeviceInfo.setLastDate(cashInitUnique.getDate());
					}
					cashInitPlanDeviceInfo.setOrgName(device.getOrganization().getName());
					cashInitPlanDeviceInfo.setTerminalId(device.getTerminalId());
					cashInitPlanDeviceInfo.setCashInitPlanInfo(cashInitPlanInfo);
					initDeviceMap.put(device.getTerminalId(), device);
					cashInitPlanInfo.add(cashInitPlanDeviceInfo);
				}
			}
			//除非设置为不启用，否则按规则进行生成加钞计划
			if(null==daysLimitRule||daysLimitRule.isStartUsing()){
				IFilter cashInitFilter = new Filter();
				String date = DateUtils.getDate(DateUtils.getDate(-cashInitDays));
				cashInitFilter.lt("date", date+" 00:0:00");
				List<ICashInitUnique> cashInitList = cashInitUniqueService.getCashInitByOrg(org,cashInitDays);
				for(ICashInitUnique cashInitUnique:cashInitList){
					//如果己经加入到加钞计划中,则跳过不做处理
					if(initDeviceMap.get(cashInitUnique.getTerminalId())!=null){
						continue;
					}
					IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoMap.get(cashInitUnique.getTerminalId());
					ICashInitPlanDeviceInfo cashInitPlanDeviceInfo = cashInitPlanDeviceInfoService.make();
					//此机器日均交易
					IMonthDailyTradingVolume monthDailyTradingVolume = monthDailyVolume.get(cashInitUnique.getTerminalId());
					long dailyVolume = 0;
					if(monthDailyTradingVolume!=null){
						if(monthDailyTradingVolume.getLastYearAmtOutAvg()==0){
							dailyVolume = (long)monthDailyTradingVolume.getMonthAmtOutAvg()*cashInitDays;
						}
						else{
							dailyVolume = (long)monthDailyTradingVolume.getLastYearAmtOutAvg()+(long)monthDailyTradingVolume.getMonthAmtOutAvg();
							dailyVolume = dailyVolume/2*cashInitDays;
						}
					}
					amt+=dailyVolume;
					IDevice device = null;
					if(deviceBoxInfo!=null){
						device = deviceBoxInfo.getDeviceId();
						dailyVolume = deviceBoxInfo.getDefaultBill()>dailyVolume?dailyVolume:deviceBoxInfo.getDefaultBill();
					}
					else{
						device = deviceService.get(cashInitUnique.getTerminalId());
					}
					//如果设备不存在，不做处理
					if(device==null){
						continue;
					}
					cashInitPlanDeviceInfo.setDevType(device.getDevType().getName());
					cashInitPlanDeviceInfo.setAwayFlag(device.getAwayFlag());
					cashInitPlanDeviceInfo.setActualAmt(dailyVolume);
					cashInitPlanDeviceInfo.setAdviceAmt(dailyVolume);
					cashInitPlanDeviceInfo.setAddress(device.getAddress());
					cashInitPlanDeviceInfo.setFlag(BoxInitRuleType.DAYSLIMIT);
					if(cashInitUnique!=null){
						cashInitPlanDeviceInfo.setLastAmt(cashInitUnique.getAmt());
						cashInitPlanDeviceInfo.setLastDate(cashInitUnique.getDate());
					}
					cashInitPlanDeviceInfo.setOrgName(device.getOrganization().getName());
					cashInitPlanDeviceInfo.setTerminalId(device.getTerminalId());
					cashInitPlanDeviceInfo.setCashInitPlanInfo(cashInitPlanInfo);
					cashInitPlanInfo.add(cashInitPlanDeviceInfo);
				}
			}
			cashInitPlanInfo.setOrg(org);
			cashInitPlanInfo.setAmt(amt);
			cashInitPlanInfo.setDate(Integer.parseInt(cashInitDate));
			cashInitPlanInfo.setCashInitCode(this.getCashInitPlanInfoNextCode(cashInitDate));
			this.save(cashInitPlanInfo);
		}
	}

	@Override
	public ICashInitPlanInfo make() {
		return new CashInitPlanInfo();
	}

	/**
	 * 获取指定日期内的加钞计划单号,如果之前没有单号,直接返回日期+"0000"否则返回最大单号+1
	 * @param cashInitDate
	 * @return
	 */
	public String getCashInitPlanInfoNextCode(String cashInitDate){
		StringBuffer sb = new StringBuffer();
		sb.append("select cashInitCode from ").append(CashInitPlanInfo.class.getSimpleName())
		.append(" where date=? order by cashInitCode desc");
		List<String> list = dao.findByHQL(sb.toString(), new Object[]{Integer.parseInt(cashInitDate)});
		if(null==list||list.size()==0){
			return cashInitDate+"0000";
		}
		long nowCode = Long.parseLong(list.get(0));
		return ++nowCode+"";
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

	public List<ICashInitPlanInfo> list(IFilter filter){
		return dao.findByFilter(filter, ICashInitPlanInfo.class);
	}
}
