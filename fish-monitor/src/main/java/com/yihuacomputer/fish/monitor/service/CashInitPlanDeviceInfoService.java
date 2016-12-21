package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.CashInitPlanDeviceInfoForm;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUnique;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUniqueService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolume;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitPlanDeviceInfo;
@Service
@Transactional
public class CashInitPlanDeviceInfoService implements ICashInitPlanDeviceInfoService {

	private Logger logger = LoggerFactory.getLogger(CashInitPlanDeviceInfoService.class);
	
	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IParamService paramService;

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;

	@Autowired
	private ICashInitUniqueService cashInitUniqueService;
	
	@Override
	public ICashInitPlanDeviceInfo make() {
		return new CashInitPlanDeviceInfo();
	}

	@Override
	public ICashInitPlanDeviceInfo save(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		return dao.save(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanDeviceInfo update(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		return dao.update(cashInitPlanInfo);
	}

	/* (non-Javadoc)
	 * 因为级联关系，所以先解除关联关系，再做删除操作
	 * @see com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService#remove(com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo)
	 */
	@Override
	public void remove(ICashInitPlanDeviceInfo cashInitPlanDeviceInfo) {
		cashInitPlanDeviceInfo.getCashInitPlanInfo().getCashInitPlanDeviceList().remove(cashInitPlanDeviceInfo);
		cashInitPlanDeviceInfo.setCashInitPlanInfo(null);
		dao.delete(cashInitPlanDeviceInfo);
	}

	@Override
	public ICashInitPlanDeviceInfo get(long id) {
		return dao.get(id, CashInitPlanDeviceInfo.class);
	}

	@Override
	public List<ICashInitPlanDeviceInfo> list(IFilter filter) {
		Object orgIdObject = filter.getValue("orgId");
		Object devTypeObject = filter.getValue("devType");
		Object awayFlagObject = filter.getValue("awayFlag");
		Object terminalIdObject = filter.getValue("terminalId");
		StringBuilder sb= new StringBuilder();
		sb.append("select planDeviceInfo from ").append(CashInitPlanDeviceInfo.class.getSimpleName())
		.append(" planDeviceInfo ,").append(Device.class.getSimpleName()).append(" device ")
		.append(" where  planDeviceInfo.cashInitPlanInfo.id=? and planDeviceInfo.terminalId = device.terminalId ");
		List<Object> argList = new ArrayList<Object>();
		long planId = Long.parseLong(String.valueOf(filter.getValue("planid")));
		argList.add(planId);
		if(orgIdObject!=null){
			String orgId = String.valueOf(orgIdObject);
			IOrganization org = orgService.get(orgId);
			argList.add(org.getOrgFlag()+"%");
			sb.append(" and device.organization.orgFlag like ? ");
		}if(devTypeObject!=null){
			long devType = Long.parseLong(String.valueOf(devTypeObject));
			argList.add(devType);
			sb.append(" and device.devType.id = ? ");
		}if(awayFlagObject!=null){
			int awayFlag =  Integer.parseInt(String.valueOf(awayFlagObject));
			argList.add(AwayFlag.getById(awayFlag));
			sb.append(" and device.awayFlag = ? ");
		}if(terminalIdObject!=null){
			String terminalId = String.valueOf(terminalIdObject);
			argList.add(terminalId+"%");
			sb.append(" and device.terminalId LIKE ? ");
		}
		return dao.findByHQL(sb.toString(),argList.toArray());
	}
	
	/**
	 * 获取可选的设备列表 1.列出当前计划所有可以加钞的设备，2.排除设备中已经添加的设备
	 * @param planInfo
	 * @return
	 */
	@Override
	public List<CashInitPlanDeviceInfoForm> listSelectAble(ICashInitPlanInfo planInfo,IFilter filter){
		List<CashInitPlanDeviceInfoForm> planDeviceList = new ArrayList<CashInitPlanDeviceInfoForm>();
		List<ICashInitPlanDeviceInfo>  devicePlanList = planInfo.getCashInitPlanDeviceList();
		IOrganization org = planInfo.getOrg();

		List<Object> argList = new ArrayList<Object>();
		Object orgIdObject = filter.getValue("orgId");
		Object devTypeObject = filter.getValue("devType");
		Object awayFlagObject = filter.getValue("awayFlag");
		Object terminalIdObject = filter.getValue("terminalId");
		StringBuilder sb = new StringBuilder();
		sb.append("from ").append(Device.class.getSimpleName()).append(" device where device.organization.orgFlag like ? ")
		.append(" and device.devType.devCatalog.no in ('01','02')");
		argList.add(org.getOrgFlag()+"%");
		if(orgIdObject!=null){
			String orgId = String.valueOf(orgIdObject);
			IOrganization org1 = orgService.get(orgId);
			argList.add(org1.getOrgFlag()+"%");
			sb.append(" and device.organization.orgFlag like ? ");
		}if(devTypeObject!=null){
			long devType = Long.parseLong(String.valueOf(devTypeObject));
			argList.add(devType);
			sb.append(" and device.devType.id = ? ");
		}if(awayFlagObject!=null){
			int awayFlag =  Integer.parseInt(String.valueOf(awayFlagObject));
			argList.add(AwayFlag.getById(awayFlag));
			sb.append(" and device.awayFlag = ? ");
		}if(terminalIdObject!=null){
			String terminalId = String.valueOf(terminalIdObject);
			argList.add(terminalId+"%");
			sb.append(" and device.terminalId LIKE ? ");
		}
		List<IDevice> deviceList = dao.findByHQL(sb.toString(), argList.toArray());
		IParam cashInitDaysParam = paramService.getParam("cashinit_days");
		int cashInitDays = 7;
		//获取加钞天数
		if(cashInitDaysParam!=null){
			try{
				cashInitDays = Integer.parseInt(cashInitDaysParam.getParamValue());
			}catch(Exception e){
				logger.error(String.format("[%s]", e));
			}
		}
		//获取日均交易信息
		String lastMonthYear = DateUtils.getLastMonthShortDates();
		IFilter filterVolume  = new Filter();
		filterVolume.eq("transMonth", Integer.parseInt(lastMonthYear));
		Map<String,IMonthDailyTradingVolume> monthDailyVolume = monthDailyTradingVolumeService.getMonthDailyMap(filterVolume);
		//获取所有加钞历史
		Map<String, ICashInitUnique> cashInitMap = cashInitUniqueService.getCashInitMap(org);
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(org.getOrgFlag());
				
		Map<String,ICashInitPlanDeviceInfo> cashPlanDeviceMap = new HashMap<String,ICashInitPlanDeviceInfo>();
		for(ICashInitPlanDeviceInfo cash:devicePlanList){
			cashPlanDeviceMap.put(cash.getTerminalId(), cash);
		}
		for(IDevice device:deviceList){
			if(null==cashPlanDeviceMap.get(device.getTerminalId())){
				CashInitPlanDeviceInfoForm cashInitPlanDeviceInfoForm = new CashInitPlanDeviceInfoForm();
				String terminalId = device.getTerminalId();
				//设备日均交易量
				IMonthDailyTradingVolume monthDailyTradingVolume = monthDailyVolume.get(terminalId);
				//设备清机加钞信息
				ICashInitUnique cashInitUnique = cashInitMap.get(terminalId);
				IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoMap.get(terminalId);
				//设置加钞设备基础信息
				cashInitPlanDeviceInfoForm.setAwayFlag(messageSource.getMessage(device.getAwayFlag().getText(), null, FishCfg.locale));
//				cashInitPlanDeviceInfoForm.setAwayFlag(device.getAwayFlag().getText());
				cashInitPlanDeviceInfoForm.setCashInitPlanInfoId(planInfo.getId());
				cashInitPlanDeviceInfoForm.setAddress(device.getAddress());
				cashInitPlanDeviceInfoForm.setFlag(BoxInitRuleType.MANAUL.getNo());//TODO 判断加钞类型
				cashInitPlanDeviceInfoForm.setDevType(device.getDevType().getName());
				cashInitPlanDeviceInfoForm.setOrgName(device.getOrganization().getName());
				cashInitPlanDeviceInfoForm.setTerminalId(device.getTerminalId());
				cashInitPlanDeviceInfoForm.setAwayFlagType(device.getAwayFlag());
				cashInitPlanDeviceInfoForm.setId(device.getId());
				//设置加钞设备的清机信息
				if(cashInitUnique!=null){
					String date = DateUtils.getDate(DateUtils.getDate(-cashInitDays))+" 00:0:00";
					cashInitPlanDeviceInfoForm.setLastAmt(cashInitUnique.getAmt());
					cashInitPlanDeviceInfoForm.setLastDate(cashInitUnique.getDate());
					if(cashInitUnique.getDate().compareTo(date)<0){
						cashInitPlanDeviceInfoForm.setFlag(BoxInitRuleType.DAYSLIMIT.getNo());
					}
				}
				//设置加钞设备加钞限额信息
				double dailyVolume = 0.0;
				if(monthDailyTradingVolume!=null){
					if(Double.compare(monthDailyTradingVolume.getLastYearAmtOutAvg(), 0.0) == 0){
						dailyVolume = monthDailyTradingVolume.getMonthAmtOutAvg()*cashInitDays;
					}
					else{
						dailyVolume = monthDailyTradingVolume.getLastYearAmtOutAvg()+monthDailyTradingVolume.getMonthAmtOutAvg();
						dailyVolume = dailyVolume/2*cashInitDays;
					}
				}
				if(deviceBoxInfo!=null){
					dailyVolume = deviceBoxInfo.getDefaultBill()>dailyVolume?dailyVolume:deviceBoxInfo.getDefaultBill();
					cashInitPlanDeviceInfoForm.setMaxAmt(deviceBoxInfo.getDefaultBill());
					if(deviceBoxInfo.getMaxAlarm()>deviceBoxInfo.getCashInValue()||deviceBoxInfo.getMinAlarm()<deviceBoxInfo.getBillValue()){
						cashInitPlanDeviceInfoForm.setFlag(BoxInitRuleType.CASHLIMIT.getNo());
					}		
					cashInitPlanDeviceInfoForm.setBillAmt(deviceBoxInfo.getBillValue());
					cashInitPlanDeviceInfoForm.setCashInAmt(deviceBoxInfo.getCashInValue());
				}
				cashInitPlanDeviceInfoForm.setAdviceAmt(dailyVolume);
				planDeviceList.add(cashInitPlanDeviceInfoForm);	
			}
		}
		return planDeviceList;
	}


}
