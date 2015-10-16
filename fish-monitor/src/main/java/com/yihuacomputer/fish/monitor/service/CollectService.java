package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceListener;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.monitor.AlarmInfo;
import com.yihuacomputer.fish.api.monitor.BusinessInfo;
import com.yihuacomputer.fish.api.monitor.ICollectListener;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.api.monitor.alarm.ISoftwareFailure;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoneyService;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.INoteItem;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTrans;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTransService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;
import com.yihuacomputer.fish.monitor.entity.business.RunInfo;
import com.yihuacomputer.fish.monitor.entity.report.DeviceReport;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;

@Service
@Transactional
public class CollectService implements ICollectService , IDeviceListener{

	private Logger logger = LoggerFactory.getLogger(CollectService.class);

    private ICollectListener collectListener;

    @Autowired
    private IRetaincardService retaincardService;

    @Autowired
    private IProcessService processService;

    @Autowired
    private IXfsService xfsService;

    @Autowired
    private IHardwareService hardwareService;

    @Autowired
    private ISoftwareService softwareService;

    @Autowired
    private IRegistService registSerivce;

    @Autowired
    private IRunInfoService runInfoService;

    @Autowired
    private ISettlementService settle;

    @Autowired
    private ICashInitService cashInit;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private ICounterFeitMoneyService counterFeitMoneyService;
    

    @Autowired
    private MessageSource messageSourceEnum;
    
    @Autowired
    private IUncommonTransService uncommonTransService;

    /** CASE处理相关注入 */
    @Autowired(required = false)
    private IDeviceCaseService deviceCaseService;


    /**
     * 注册信息收集监听器
     *
     * @param listener
     */
    public void setClollectListener(ICollectListener listener) {
        this.collectListener = listener;
    }

    /**
     * 收集服务初始化
     */
    @PostConstruct
    public void init() {
    	deviceService.addDeviceListener(this);
    }

    /**
     * 收集服务关闭
     * */
    public void close() {

    }

    /**
     * 设备开机签到
     *
     * @param terminalId
     * @param regSn
     * @return 签到是否成功
     */
    public void collectBootSign(String terminalId,
            IDeviceRegister deviceRegister,MessageSource messageSourceRef) {
        DeviceReport deviceReport = this.getDeviceReport(terminalId);
        deviceReport.setDeviceRegister((DeviceRegister) deviceRegister);
        collectListener.signed(terminalId, deviceReport,messageSourceRef);
    }

    /**
     * 添加数据加工单元
     *
     * @param unit
     */
    public void addWorkUnit(IWorkUnit unit) {
        collectListener.setWorkUnit(unit);

    }

    /**
     * 收集设备硬件模块状态信息
     *
     * @param terminalId
     * @param status
     */
    public void collectModuleStatus(String terminalId, IXfsStatus xfsStatus) {

        IXfsStatus histXfsStatus = xfsService.loadXfsStatus(terminalId);
        if (histXfsStatus == null) {
            return;
        }
        if (histXfsStatus.getRunStatus().equals(RunStatus.StopManmade)) {
            xfsStatus.setRunStatus(RunStatus.StopManmade);
        }
        histXfsStatus.setDateTime(DateUtils.getTimestamp(new Date()));

        if (histXfsStatus.equals(xfsStatus)) {
            histXfsStatus.setBoxCurrentCount(xfsStatus.getBoxCurrentCount());
            xfsService.updateXfsStatus(histXfsStatus);
            return;
        }

        IRunInfo runInfo = new RunInfo();
        runInfo.setRunStatus(histXfsStatus.getRunStatus());

        /** 处理模块故障相关 */
        if (deviceCaseService != null) {
        	try{
        		deviceCaseService.handleModStatus(xfsStatus);
        	}catch(Exception e){
//        		logger.error(String.format("处理设备故障工单异常[%s]", e));
        		logger.error(String.format("handle device caseFault exception[%s]", e));
        		return ;
        	}
        }

        histXfsStatus.setXfsStatus(xfsStatus);


        DeviceReport deviceReport = this.getDeviceReport(histXfsStatus
                .getTerminalId());
        deviceReport.setXfsStatus((XfsStatus) histXfsStatus);
        deviceReport.setRunInfo(runInfo);
        // 如果状态不一致想设备运行状态表中插入数据
        if (!runInfo.getRunStatus().equals(xfsStatus.getRunStatus())) {
            runInfo.setTerminalId(terminalId);
            runInfo.setRunStatus(xfsStatus.getRunStatus());
            runInfo.setStatusTime(DateUtils.getTimestamp(new Date()));
            this.runInfoService.save(runInfo);
        }
        IDevice device = this.deviceService.get(terminalId);

        deviceReport.setDevice(device);
        deviceReport.setDeviceRegister((DeviceRegister) this.registSerivce
                .load(terminalId));

        xfsService.updateXfsStatus(histXfsStatus);
        this.collectListener.receivedStatus(terminalId, deviceReport,messageSourceEnum);

    }

    /**
     * 收集设备网络故障信息
     *
     * @param terminalId
     * @param xfsStatus
     */
    public void collectNetError(String terminalId, IXfsStatus xfsStatus) {

        DeviceReport deviceReport = this.getDeviceReport(terminalId);
        deviceReport.setXfsStatus((XfsStatus) xfsStatus);
        IRunInfo runInfo = new RunInfo();
        runInfo.setRunStatus(xfsStatus.getRunStatus());
        runInfo.setTerminalId(terminalId);
        runInfo.setStatusTime(DateUtils.getTimestamp(new Date()));
        deviceReport.setRunInfo(runInfo);
        this.runInfoService.save(runInfo);

        deviceReport.setDevice(this.deviceService.get(terminalId));
        deviceReport.setDeviceRegister((DeviceRegister) this.registSerivce
                .load(terminalId));

        xfsService.updateXfsStatus(xfsStatus);
        this.collectListener.receivedStatus(terminalId, deviceReport,messageSourceEnum);
    }

    /**
     * 根据terminalId获取内存中的DeviceReport对象 获取失败创建新对象
     *
     * @param terminalId
     * @return
     */
    public DeviceReport getDeviceReport(String terminalId) {

        DeviceReport deviceReport = new DeviceReport();

        // if (deviceReport == null) {
        // deviceReport = new DeviceReport();
        //
        // DeviceRegister deviceRegister = new DeviceRegister();
        // deviceReport.setDeviceRegister(deviceRegister);
        // }
        deviceReport.setDeviceId(terminalId);
        return deviceReport;
    }

    /**
     * 保存内存对象
     *
     * @param terminalId
     * @param deviceReport
     */
    // public void setMapDeviceReport(String terminalId, DeviceReport
    // deviceReport) {
    // DeviceReport entity = memService.interface2Entity(deviceReport);
    // collectMap.put(terminalId, entity);
    //
    // }

    /**
     * 收集设备开机上送系统主机硬件信息
     *
     * @param terminalId
     * @param hardware
     */
    public void collectDeviceHardware(String terminalId, IHardware hardware) {
        if (hardware != null) {
            this.hardwareService.update(hardware);
        }
    }

    /**
     * 收集设备操作系统软件信息
     *
     * @param terminalId
     * @param software
     */
    public void collectDeviceSoftware(String terminalId, ISoftware software) {
        if (software != null) {
            this.softwareService.update(software);
        }
    }

    /**
     * 收集系统软件报错信息
     *
     * @param terminalId
     * @param failure
     */
    public void collectSoftwareFailure(String terminalId,
            ISoftwareFailure failure) {
        // dao.save(failure);
    }

    /**
     * 收集设备非百名但进程信息报警
     *
     * @param terminalId
     * @param process
     */
    public void collectSchindlerAlarm(String terminalId,
            List<IIllegalProcess> processList) {
        DeviceReport deviceReport = this.getDeviceReport(terminalId);

        this.processService.saveSchindlerAlarm(terminalId, processList);
        deviceReport.setProcess(processList);
        deviceReport.setDevice(this.deviceService.get(terminalId));
        this.collectListener.receivedAlarm(terminalId,
                AlarmInfo.SCHINDLER_ALARM, deviceReport);
    }

    /**
     * 收集ATMC应用状态切换信息
     *
     * @param terminalId
     * @param runInfo
     */
    public void collectATMCRunInfo(String terminalId, IRunInfo runInfo) {

        IXfsStatus xfsStatus = xfsService.loadXfsStatus(terminalId);

        if (xfsStatus == null
                || xfsStatus.getRunStatus().equals(RunStatus.StopManmade)
                || runInfo.getRunStatus() == null
                || runInfo.getRunStatus().equals(xfsStatus.getRunStatus())) {
            return;
        }

        // 如果状态不一致想设备运行状态表中插入数据
        if (!runInfo.getRunStatus().equals(xfsStatus.getRunStatus())) {
            runInfo.setTerminalId(terminalId);
            runInfo.setStatusTime(DateUtils.getTimestamp(new Date()));
            this.runInfoService.save(runInfo);
        }

        /** 处理状态切换通知 */
        if (deviceCaseService != null) {
        	deviceCaseService.handleAppStatus(runInfo);
        }

        DeviceReport deviceReport = this.getDeviceReport(xfsStatus
                .getTerminalId());
        runInfo.setStatusTime(DateUtils.getTimestamp(new Date()));
        deviceReport.setRunInfo(runInfo);
        xfsStatus.setRunStatus(runInfo.getRunStatus());
        xfsStatus.setDateTime(DateUtils.getTimestamp(new Date()));
        xfsStatus.setNetStatus(NetStatus.Healthy);
        deviceReport.setXfsStatus((XfsStatus) xfsStatus);
        this.xfsService.updateXfsStatus(xfsStatus);
        deviceReport.setDevice(this.deviceService.get(terminalId));
        deviceReport.setDeviceRegister((DeviceRegister) this.registSerivce
                .load(terminalId));

        this.collectListener.receivedStatus(terminalId,
                 deviceReport,messageSourceEnum);
    }

    /**
     * 收集ATMC加钞信息
     *
     * @param terminalId
     * @param checkin
     */
    public void collectCashInit(String terminalId, ICashInit cashInit) {
        this.cashInit.save(cashInit);
    }

    /**
     * 收集ATMC清机结算信息
     *
     * @param terminalId
     * @param checkout
     */
    public void collectSettlement(String terminalId, ISettlement settlement) {
        this.settle.save(settlement);
    }

    /**
     * 收集ATMC吞卡信息
     *
     * @param terminalId
     * @param retainCard
     */
    public void collectATMCRetainCard(String terminalId, IRetaincard retainCard) {
        retainCard.setTerminalId(terminalId);
        retaincardService.add(retainCard);
        if (deviceCaseService != null) {
        	deviceCaseService.handleRetainCard(retainCard);
        }

        DeviceReport deviceReport = this.getDeviceReport(terminalId);

        retainCard.setTerminalId(terminalId);
        deviceReport.setRetaincard(retainCard);
        deviceReport.setDevice(this.deviceService.get(terminalId));

        this.collectListener.receivedBusiness(terminalId,
                BusinessInfo.RETAIN_CARD, deviceReport);
    }

    /**
     * 收集设备硬件模块属性信息
     *
     * @param terminalId
     * @param xfsPro
     */
    public void collectModulePropertise(String terminalId, IXfsPropertise xfsPro) {

        IXfsPropertise oldXfsPro = this.xfsService.loadXfsProp(terminalId);
        if (oldXfsPro == null || oldXfsPro.equals(xfsPro)) {
            return;
        }

        oldXfsPro.setCdm(xfsPro.getPropCdm());
        oldXfsPro.setCim(xfsPro.getPropCim());
        oldXfsPro.setIdc(xfsPro.getPropIdc());
        oldXfsPro.setJpr(xfsPro.getPropJpr());
        oldXfsPro.setPin(xfsPro.getPropPin());
        oldXfsPro.setRpr(xfsPro.getPropRpr());
        oldXfsPro.setSiu(xfsPro.getPropSiu());
        oldXfsPro.setTtu(xfsPro.getPropTtu());
        oldXfsPro.setPbk(xfsPro.getPropPbk());
        oldXfsPro.setNfc(xfsPro.getPropNfc());
        oldXfsPro.setIcc(xfsPro.getPropIcc()) ;
        oldXfsPro.setFgp(xfsPro.getPropFgp()) ;
        oldXfsPro.setIsc(xfsPro.getPropIsc()) ;
        oldXfsPro.setIsc(xfsPro.getPropIsc());

        this.xfsService.updateXfsProp(oldXfsPro);
    }

    @Override
    public void collectATMCTransaction(String terminalId,
            ITransaction transaction) {
        DeviceReport deviceReport = this.getDeviceReport(terminalId);

        transaction.setTerminalId(terminalId);
        transactionService.save(transaction);
        deviceReport.setTransaction(transaction);
        deviceReport.setDevice(this.deviceService.get(terminalId));
        this.collectListener.receivedBusiness(terminalId,
                BusinessInfo.TRANSACTION, deviceReport);
    }

    @Override
    public void initDeviceCollect(IDevice device) {
        this.hardwareService.init(device.getTerminalId());
        this.softwareService.init(device.getTerminalId());
        IXfsStatus xfsStatus = this.xfsService.initXfsStatus(device
                .getTerminalId());
        this.xfsService.initXfsProp(device.getTerminalId());
        IDeviceRegister reg = this.registSerivce.init(device.getTerminalId());

        DeviceReport deviceReport = this
                .getDeviceReport(device.getTerminalId());
        deviceReport.setXfsStatus((XfsStatus) xfsStatus);
        IRunInfo runInfo = new RunInfo();
        runInfo.setRunStatus(xfsStatus.getRunStatus());
        deviceReport.setRunInfo(runInfo);
        deviceReport.setDevice(device);
        deviceReport.setDeviceRegister((DeviceRegister) reg);

        this.collectListener.receivedStatus(device.getTerminalId(),
                deviceReport,messageSourceEnum);
    }

	public void deleteDevice(IDevice device) {

		IXfsStatus xfsStatus = this.xfsService.loadXfsStatus(device
				.getTerminalId());
		IDeviceRegister reg = this.registSerivce.load(device.getTerminalId());

		this.hardwareService.delete(device.getTerminalId());
		this.softwareService.delete(device.getTerminalId());
		this.xfsService.deleteXfsProp(this.xfsService.loadXfsProp(device
				.getTerminalId()));
		this.xfsService.deleteXfsStatus(xfsStatus);
		this.registSerivce.delete(reg);

		DeviceReport deviceReport = this
				.getDeviceReport(device.getTerminalId());
		deviceReport.setXfsStatus((XfsStatus) xfsStatus);
		IRunInfo runInfo = new RunInfo();
		runInfo.setRunStatus(xfsStatus.getRunStatus());
		deviceReport.setRunInfo(runInfo);
		deviceReport.setDevice(device);
		deviceReport.setDeviceRegister((DeviceRegister) reg);
		device.setOrganization(null);
		this.collectListener.receivedStatus(device.getTerminalId(),
				deviceReport,messageSourceEnum);
	}

    @Override
    public void collectSetup(String terminalId, IDeviceRegister deviceRegister) {
        registSerivce.update(deviceRegister);
        if (deviceCaseService != null) {
        	deviceCaseService.handleDeviceRegister(deviceRegister);
        }
    }

    @Override
    public void collectRumtimeInfo(String terminalId, IRuntimeInfo runtimeInfo) {
        if (deviceCaseService != null) {
        	deviceCaseService.handleRumtimeInfo(runtimeInfo);
        }
    }

	@Override
	public void beforeAdd(IDevice device) {
	}

	@Override
	public void afterAdd(IDevice device) {
		this.initDeviceCollect(device);
	}

	@Override
	public void beforeDelete(IDevice device) {
	}

	@Override
	public void afterDelete(IDevice device) {
		this.deleteDevice(device);
	}

	@Override
	public String getListenerName() {
		return "STATUS_MONITOR";
	}

	@Override
	public void beforeUpdate(IDevice device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterUpdate(IDevice device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collectATMCCounterFeitMoney(String terminalId,
			ICounterFeitMoney counterFeitMoney) {
		DeviceReport deviceReport = this.getDeviceReport(terminalId);

		counterFeitMoney.setTerminalId(terminalId);
		counterFeitMoneyService.save(counterFeitMoney);
		
		List<CounterFeitMoneyForms> result = new ArrayList<CounterFeitMoneyForms>();
        List<INoteItem> noteResultList = counterFeitMoney.getNoteItem();
		
		for(INoteItem noteItem : noteResultList){
			CounterFeitMoneyForms forms = new CounterFeitMoneyForms();
			forms.setTermId(counterFeitMoney.getTerminalId());
			forms.setTransId(counterFeitMoney.getTransId());
			forms.setDebitAccount(counterFeitMoney.getDebitAccount());
			forms.setCreditAccount(counterFeitMoney.getCreditAccount());
			forms.setTransCode(counterFeitMoney.getTransCode());
			forms.setAmt(counterFeitMoney.getAmt());
			forms.setCurrency(counterFeitMoney.getCurrency());
			forms.setDateTime(DateUtils.getTimestamp(counterFeitMoney.getDateTime()));
			forms.setHostRet(counterFeitMoney.getHostRet());
			forms.setLocalRet(counterFeitMoney.getLocalRet());
			forms.setTipFee(counterFeitMoney.getTipFee());
			forms.setCounterFeitMoney(counterFeitMoney.getCounterFeitMoney());
			forms.setSerial(noteItem.getSerial());
			forms.setNoteCode(noteItem.getNoteCode());
			
			IDevice device = deviceService.get(terminalId);
			forms.setOrgName(device.getOrganization().getName());
			result.add(forms);
		}
        deviceReport.setCounterFeitMoneyForms(result);
        deviceReport.setDevice(this.deviceService.get(terminalId));
        this.collectListener.receivedBusiness(terminalId,
                BusinessInfo.COUNTERFEITMONEY, deviceReport);

	}


	@Override
	public void collectATMCUncommonTrans(String terminalId,
			IUncommonTrans uncommonTrans) {

		uncommonTrans.setTerminalId(terminalId);
		uncommonTransService.save(uncommonTrans);
		
	}

}
