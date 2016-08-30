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

	@Override
	public void remove(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		dao.delete(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanDeviceInfo get(long id) {
		return dao.get(id, CashInitPlanDeviceInfo.class);
	}

	@Override
	public List<ICashInitPlanDeviceInfo> list(IFilter filter) {
		return dao.findByFilter(filter, ICashInitPlanDeviceInfo.class);
	}
	
	/**
	 * 获取可选的设备列表 1.列出当前计划所有可以加钞的设备，2.排除设备中已经添加的设备
	 * @param planInfo
	 * @return
	 */
	public List<CashInitPlanDeviceInfoForm> listSelectAble(ICashInitPlanInfo planInfo){
		List<CashInitPlanDeviceInfoForm> planDeviceList = new ArrayList<CashInitPlanDeviceInfoForm>();
		List<ICashInitPlanDeviceInfo>  devicePlanList = planInfo.getCashInitPlanDeviceList();
		IOrganization org = planInfo.getOrg();
		StringBuilder sb = new StringBuilder();
		sb.append("from ").append(Device.class.getSimpleName()).append(" device where device.organization.orgFlag like ? ")
		.append(" and device.devType.devCatalog.no in ('01','02')");
		
		List<IDevice> deviceList = dao.findByHQL(sb.toString(), new Object[]{org.getOrgFlag()+"%"});
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
					if(monthDailyTradingVolume.getLastYearAmtOutAvg()==0){
						dailyVolume = monthDailyTradingVolume.getMonthAmtOutAvg()*cashInitDays;
					}
					else{
						dailyVolume = monthDailyTradingVolume.getLastYearAmtOutAvg()+monthDailyTradingVolume.getMonthAmtOutAvg();
						dailyVolume = dailyVolume/2*cashInitDays;
					}
					cashInitPlanDeviceInfoForm.setActualAmt(dailyVolume);
					cashInitPlanDeviceInfoForm.setAdviceAmt(dailyVolume);
				}
				if(deviceBoxInfo!=null){
					cashInitPlanDeviceInfoForm.setMaxAmt(deviceBoxInfo.getDefaultBill());
					if(deviceBoxInfo.getMaxAlarm()<deviceBoxInfo.getCashInValue()||deviceBoxInfo.getMinAlarm()>deviceBoxInfo.getBillValue()){
						cashInitPlanDeviceInfoForm.setFlag(BoxInitRuleType.CASHLIMIT.getNo());
					}
				}
				planDeviceList.add(cashInitPlanDeviceInfoForm);	
			}
		}
		return planDeviceList;
	}

//	public List<Object> listPage(IFilter filter){
//		StringBuilder sb = new StringBuilder();
//		sb.append("select cashInitPlanDevice,deviceBoxInfo from ").append(CashInitPlanDeviceInfo.class.getSimpleName()).append(" cashInitPlanDevice ,")
//		.append(DeviceBoxInfo.class.getSimpleName()).append(" deviceBoxInfo where deviceBoxInfo.deviceId.terminalId = cashInitPlanDevice.terminalId ");
//		Object terminalId = filter.getValue("terminalId");
//		Object orgId = filter.getValue("orgId");
//		Object awayflag = filter.getValue("awayflag");
//		Object devType = filter.getValue("devType");
//		Object planId = filter.getValue("cashInitPlanInfo.id");
//		List<Object> list = new ArrayList<Object>();
//		if(planId!=null){
//			sb.append(" and cashInitPlanDevice.cashInitPlanInfo.id=? ");
//			list.add(Long.parseLong(String.valueOf(planId)));
//		}
//		if(terminalId!=null){
//			sb.append(" and cashInitPlanDevice.terminalId=? ");
//			list.add(terminalId.toString());
//		}
//		if(awayflag!=null){
//			sb.append(" and deviceBoxInfo.deviceId.awayFlag=? ");
//			list.add(awayflag.toString());
//		}
//		if(devType!=null){
//			sb.append(" and deviceBoxInfo.deviceId.devType.id=? ");
//			list.add(Long.parseLong(devType.toString()));
//		}
//		if(orgId!=null){
//			sb.append(" and deviceBoxInfo.deviceId.organization.orgFlag like ? ");
//			IOrganization org = orgService.get(String.valueOf(orgId));
//			list.add(org.getOrgFlag()+"%");
//		}
//		return dao.findByHQL(sb.toString(), list.toArray());
//	}

}
