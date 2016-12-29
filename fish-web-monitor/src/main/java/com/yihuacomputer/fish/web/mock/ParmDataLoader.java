/**
 *
 */
package com.yihuacomputer.fish.web.mock;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.api.fault.INotifyMould;
import com.yihuacomputer.fish.api.fault.INotifyMouldService;
import com.yihuacomputer.fish.api.fault.NotifyType;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.fault.ResponsorType;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.system.entity.Param;

/**
 * @author dell
 *
 */
public class ParmDataLoader {

	@Autowired
	private IParamService parmService;

	@Autowired
	private INotifyMouldService notifyMouldService;

	@Autowired
	private IFaultClassifyService faultClassifyService;

	/**
	 * 初始化
	 */
	public void init() {
		initFishHome();
		initAtmPort();
		initAtmLogFileCfg();
		initAtmLogDoc();
		initDeviceOffline();
		initStatusCheckTime();
		initRegisterSerial();
		initCheckTime();
		initNotifyMould();
		parmService.init();
	}

	private void initFishHome() {

		IParam param = new Param();
		param.setParamKey("fish_home");
		param.setParamValue("D:\\fish_home");
		param.setClassify(0);
		param.setDescription("监控服务文件存放根路径");
		this.parmService.add(param);
	}

	private void initAtmPort() {

		IParam param = new Param();
		param.setParamKey("atm_port");
		param.setParamValue("8080");
		param.setClassify(1);
		param.setDescription("Remote服务监听端口");
		this.parmService.add(param);
	}

	private void initAtmLogFileCfg() {

		IParam param = new Param();
		param.setParamKey("atm_log_file_cfg");
		param.setParamValue("bizLog_{terminalId}_{YYYY-MM-DD}.zip");
		param.setClassify(1);
		param.setDescription("ATM APP日志存放规则");
		this.parmService.add(param);
	}

	private void initAtmLogDoc() {

		IParam param = new Param();
		param.setParamKey("atm_log_doc");
		param.setParamValue("c:\\yihua\\gump\\log");
		param.setClassify(1);
		param.setDescription("ATM APP 日志存放路径");
		this.parmService.add(param);
	}

	private void initDeviceOffline() {

		IParam param = new Param();
		param.setParamKey("device_offline");
		param.setParamValue("30");
		param.setClassify(1);
		param.setDescription("获取设备离线时间(单位:分钟)");
		this.parmService.add(param);
	}

	private void initStatusCheckTime() {

		IParam param = new Param();
		param.setParamKey("device_offline");
		param.setParamValue("30");
		param.setClassify(1);
		param.setDescription("获取设备离线时间(单位:分钟)");
		this.parmService.add(param);
	}


	private void initRegisterSerial() {

		IParam param = new Param();
		param.setParamKey("register_serial");
		// 本地注册ID
		param.setParamValue("V10520-PHFDHD-FEFEDF-A87108-HDGEDE1080");

		// 228Jenkins注册ID
		// param.setParamValue("FEFEDF-A87361-V05520-PHFDHD-HDGEDL6110");
		param.setClassify(0);
		param.setDescription("系统注册序列号");
		this.parmService.add(param);
	}

	private void initCheckTime() {

		IParam param = new Param();
		param.setParamKey("check_time");
		param.setParamValue("ON");
		param.setClassify(1);
		param.setDescription("ATM开机与监控对时开关(ON/OFF)");
		this.parmService.add(param);
	}

	private void initNotifyMould() {

		IFaultClassify faultClassify = faultClassifyService.make();
		faultClassify.setId("N_AP_STAT");
		faultClassify.setClassifyName("ATMC应用状态变化");
		faultClassify.setResponsorType(ResponsorType.ADMIN);
		faultClassify.setResolveHour(0.0);
		faultClassify.setUpgrade(0);
		faultClassify.setNotifyTimes(1);
		faultClassify.setNotifyWay(NotifyWay.SMS);
		faultClassifyService.save(faultClassify);

		INotifyMould notifyMould = notifyMouldService.make();
		notifyMould.setId(0);
		notifyMould.setFaultClassify(faultClassify);
		notifyMould.setNotifyType(NotifyType.CREATE);
		notifyMould.setNotifyWay(NotifyWay.SMS);
		notifyMould.setNotifySet("请关注{terminalId}{faultClassify}{appStatus},上海农商行新监控发送");
		notifyMouldService.save(notifyMould);

		IFaultClassify faultClassify1 = faultClassifyService.make();
		faultClassify1.setId("N_AP_SETUP");
		faultClassify1.setClassifyName("系统新安装初始化");
		faultClassify1.setResponsorType(ResponsorType.ADMIN);
		faultClassify1.setResolveHour(0.0);
		faultClassify1.setUpgrade(0);
		faultClassify1.setNotifyTimes(1);
		faultClassify1.setNotifyWay(NotifyWay.SMS);
		faultClassifyService.save(faultClassify1);

		INotifyMould notifyMould1 = notifyMouldService.make();
		notifyMould1.setId(1);
		notifyMould1.setFaultClassify(faultClassify1);
		notifyMould1.setNotifyType(NotifyType.CREATE);
		notifyMould1.setNotifyWay(NotifyWay.SMS);
		notifyMould1.setNotifySet("请关注{terminalId}{faultClassify},上海农商行新监控发送");
		notifyMouldService.save(notifyMould1);

		IFaultClassify faultClassify2 = faultClassifyService.make();
		faultClassify2.setId("F_MOD");
		faultClassify2.setClassifyName("硬件故障");
		faultClassify2.setResponsorType(ResponsorType.ADMIN);
		faultClassify2.setResolveHour(0.0);
		faultClassify2.setUpgrade(0);
		faultClassify2.setNotifyTimes(1);
		faultClassify2.setNotifyWay(NotifyWay.SMS);
		faultClassifyService.save(faultClassify2);

		INotifyMould notifyMould2 = notifyMouldService.make();
		notifyMould2.setId(2);
		notifyMould2.setFaultClassify(faultClassify2);
		notifyMould2.setNotifyType(NotifyType.CREATE);
		notifyMould2.setNotifyWay(NotifyWay.SMS);
		notifyMould2.setNotifySet("请关注{terminalId}{faultClassify}{faultMod},上海农商行新监控发送");
		notifyMouldService.save(notifyMould2);

		IFaultClassify faultClassify3 = faultClassifyService.make();
		faultClassify3.setId("R_CASSET");
		faultClassify3.setClassifyName("钞空");
		faultClassify3.setResponsorType(ResponsorType.ADMIN);
		faultClassify3.setResolveHour(0.0);
		faultClassify3.setUpgrade(0);
		faultClassify3.setNotifyTimes(1);
		faultClassify3.setNotifyWay(NotifyWay.SMS);
		faultClassifyService.save(faultClassify3);

		INotifyMould notifyMould3 = notifyMouldService.make();
		notifyMould3.setId(3);
		notifyMould3.setFaultClassify(faultClassify3);
		notifyMould3.setNotifyType(NotifyType.CREATE);
		notifyMould3.setNotifyWay(NotifyWay.SMS);
		notifyMould3.setNotifySet("请关注{terminalId}{faultClassify},上海农商行新监控发送");
		notifyMouldService.save(notifyMould3);

		IFaultClassify faultClassify4 = faultClassifyService.make();
		faultClassify4.setId("R_PTR");
		faultClassify4.setClassifyName("纸少");
		faultClassify4.setResponsorType(ResponsorType.ADMIN);
		faultClassify4.setResolveHour(0.0);
		faultClassify4.setUpgrade(0);
		faultClassify4.setNotifyTimes(1);
		faultClassify4.setNotifyWay(NotifyWay.SMS);
		faultClassifyService.save(faultClassify4);

		INotifyMould notifyMould4 = notifyMouldService.make();
		notifyMould4.setId(4);
		notifyMould4.setFaultClassify(faultClassify4);
		notifyMould4.setNotifyType(NotifyType.CREATE);
		notifyMould4.setNotifyWay(NotifyWay.SMS);
		notifyMould4.setNotifySet("请关注{terminalId}{faultClassify}{faultMod},上海农商行新监控发送");
		notifyMouldService.save(notifyMould4);
	}

}
