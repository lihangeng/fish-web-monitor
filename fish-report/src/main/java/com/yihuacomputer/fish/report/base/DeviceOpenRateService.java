package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.report.base.IDayOpenRate;
import com.yihuacomputer.fish.api.report.base.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

@Service
public class DeviceOpenRateService implements IReportDataETL {


	private Logger logger = LoggerFactory.getLogger(DeviceOpenRateService.class);
	@Autowired
	private IRunInfoService runInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDayOpenRateService dayOpenRateService;

	@Autowired
	private IExportDataETLService exportDataETLService;

	private final String etlName = "BOIMC_OPEN_RATE";

	@Override
	@PostConstruct
	public void init() {
		exportDataETLService.addEveryDayETL(this);
	}

	@Override
	public String getReportETLName() {
		return etlName;
	}

	@Override
	public void reportETL(String date) {
		String currDate = date;

		String startDateTime = currDate + " 00:00:00";
		String endDateTime = currDate + " 23:59:59";

		IFilter filter = new Filter();
		filter.ge("statusTime", startDateTime);
		filter.le("statusTime", endDateTime);
		filter.order("terminalId");
		filter.order("statusTime");

		List<IRunInfo> runInfoList = runInfoService.list(filter);

		Map<String, List<IRunInfo>> runInfoMap = new HashMap<String, List<IRunInfo>>();

		listToMap(runInfoList, runInfoMap);

		for (Entry<String, List<IRunInfo>> entry : runInfoMap.entrySet()) {

			String terminalId = entry.getKey(); // 设备号

			IDevice device = deviceService.get(terminalId);
			if(device==null){
				logger.warn(String.format("device [%s] not exist!cann't get OpenRate",terminalId));
				continue;
			}
			//TODO 按照上海农商银行的开机方案调整
			String deviceOpenTime = "";//device.getDeviceExtend().getOpenTime(); // 开机时间
			String deviceCloseTime = "";//device.getDeviceExtend().getCloseTime(); // 关机时间

			if (deviceOpenTime==null||deviceCloseTime==null||deviceOpenTime.isEmpty() || deviceCloseTime.isEmpty()) {
				// 开机时间或者关机时间为空，则不统计此设备
				continue;
			}

			String openDate = currDate + " " + deviceOpenTime; // 当前日期 + 开机时间
			String closeDate = currDate + " " + deviceCloseTime; // 当前日期 + 关机时间

			IDayOpenRate dayOpenRate = statDayOpenRateTo(terminalId, openDate, closeDate, entry.getValue());

			dayOpenRate.setStatDate(currDate);

			dayOpenRateService.save(dayOpenRate);
		}
	}

	private IDayOpenRate statDayOpenRateTo(String terminalId, String openDateTime, String closeDateTime,
			List<IRunInfo> listRunInfo) {
		IDayOpenRate dayOpenRate = dayOpenRateService.make();

		boolean isBreak = false;// 当状态时间大于设备关机时间，计算最后一次后，直接跳出循环

		/** 设备应开机时长 */
		long openTimes = distanceTimes(openDateTime, closeDateTime); // 设备应开机时长(单位：分钟)

		/** 设备正常时间段内实际开机时长 */
		long healthyTimeReal = 0;

		/** 设备未知时长 */
		long unknownTimeReal = 0;

		/** 设备维护时长 */
		long maintainTimeReal = 0;

		/** 设备故障时长 */
		long faultTimeReal = 0;

		/** 设备atmp故障时长 */
		long atmpTimeReal = 0;

		/** 设备暂停时长 */
		long stopTimeReal = 0;

		/**设备显示对外画面时长 */
		long noScreenReal = 0;

		// 默认设备状态为正常
		RunStatus status = RunStatus.Healthy;
		for (int i = 0; i < listRunInfo.size(); i++) {
			IRunInfo runInfo = listRunInfo.get(i);
			String statusTime = runInfo.getStatusTime();
			if (isBreak) {
				break;
			}

			if (!(distanceTimes(openDateTime, statusTime) > 0)) {
				continue;
			}

			String s1 = "";
			String s2 = "";
			if (i == 0) {
				s1 = openDateTime;
			} else {
				s1 = listRunInfo.get(i - 1).getStatusTime();
				if (!(distanceTimes(openDateTime, s1) > 0)) {
					s1 = openDateTime;
				}
			}

			s2 = statusTime;
			if (!(distanceTimes(statusTime, closeDateTime) > 0)) {
				s2 = closeDateTime;
				isBreak = true;
			}

			if (i != 0) {
				status = listRunInfo.get(i - 1).getRunStatus();
			}

			long constant = distanceTimes(s1, s2);

			if (RunStatus.Healthy.equals(status) || RunStatus.SubHealth.equals(status)
					|| RunStatus.Customer.equals(status)) {
				// 正常模式
				healthyTimeReal += constant;
			} else if (RunStatus.StopAtmp.equals(status)) {
				// atmp故障
				atmpTimeReal += constant;
			} else if (RunStatus.Unknown.equals(status)) {
				// 未知
				unknownTimeReal += constant;
			} else if (RunStatus.StopMod.equals(status)) {
				// 硬件故障
				faultTimeReal += constant;
			} else if (RunStatus.Maintain.equals(status) || RunStatus.Vdm.equals(status)) {
				// 维护
				if(RunStatus.Vdm.equals(status)){
					noScreenReal += constant;
				}
				maintainTimeReal += constant;
			} else if (RunStatus.Halt.equals(status) || RunStatus.Initial.equals(status)
					|| RunStatus.ReBoot.equals(status) || RunStatus.StopManmade.equals(status)
					|| RunStatus.StopUnCashIn.equals(status) || RunStatus.StopUnKnown.equals(status)) {
				// 暂停故障
				if(RunStatus.Halt.equals(status) || RunStatus.ReBoot.equals(status) || RunStatus.StopManmade.equals(status))
				{
					noScreenReal += constant;
				}
				stopTimeReal += constant;
			}

			// 当状态表里最后一个时间，小于方案设置的时间，则最后时间至方案结束时间也要计算
			if (i == listRunInfo.size() - 1 && distanceTimes(statusTime, closeDateTime) > 0) {
				status = runInfo.getRunStatus();

				constant = distanceTimes(s2, closeDateTime);

				if (RunStatus.Healthy.equals(status) || RunStatus.SubHealth.equals(status)
						|| RunStatus.Customer.equals(status)) {
					// 正常模式
					healthyTimeReal += constant;
				} else if (RunStatus.StopAtmp.equals(status)) {
					// atmp故障
					atmpTimeReal += constant;
				} else if (RunStatus.Unknown.equals(status)) {
					// 未知
					unknownTimeReal += constant;
				} else if (RunStatus.StopMod.equals(status)) {
					// 硬件故障
					faultTimeReal += constant;
				} else if (RunStatus.Maintain.equals(status) || RunStatus.Vdm.equals(status)) {
					// 维护
					if(RunStatus.Vdm.equals(status)){
						noScreenReal += constant;
					}
					maintainTimeReal += constant;
				} else if (RunStatus.Halt.equals(status) || RunStatus.Initial.equals(status)
						|| RunStatus.ReBoot.equals(status) || RunStatus.StopManmade.equals(status)
						|| RunStatus.StopUnCashIn.equals(status) || RunStatus.StopUnKnown.equals(status)) {
					// 暂停故障
					if(RunStatus.Halt.equals(status) || RunStatus.ReBoot.equals(status) || RunStatus.StopManmade.equals(status))
					{
						noScreenReal += constant;
					}
					stopTimeReal += constant;
				}

			}
		}

		dayOpenRate.setAtmpTimeReal(atmpTimeReal);
		dayOpenRate.setFaultTimeReal(faultTimeReal);
		dayOpenRate.setHealthyTimeReal(healthyTimeReal);
		dayOpenRate.setMaintainTimeReal(maintainTimeReal);
		dayOpenRate.setOpenTimes(openTimes);
		dayOpenRate.setStopTimeReal(stopTimeReal);
		dayOpenRate.setTerminalId(terminalId);
		dayOpenRate.setUnknownTimeReal(unknownTimeReal);
		dayOpenRate.setNoScreenReal(noScreenReal);

		return dayOpenRate;
	}

	/**
	 * 计算两个日期时间相差多毫秒数
	 *
	 * @param startStr
	 *            开始日期时间
	 * @param endStr
	 *            结束日期时间
	 * @return int 毫秒数
	 */
	private long distanceTimes(String startStr, String endStr) {
		long result = 0L;

		long startTime = DateUtils.getTimestamp(startStr).getTime();
		long endTime = DateUtils.getTimestamp(endStr).getTime();

		result = endTime - startTime;

		return result;
	}

	private Map<String, List<IRunInfo>> listToMap(List<IRunInfo> target, Map<String, List<IRunInfo>> result) {
		List<IRunInfo> value = null;
		String key = "";
		for (IRunInfo runInfo : target) {

			if (result.containsKey(runInfo.getTerminalId())) {
				result.get(runInfo.getTerminalId()).add(runInfo);
				continue;
			}
			key = runInfo.getTerminalId();
			value = new ArrayList<IRunInfo>();
			value.add(runInfo);
			result.put(key, value);
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "1000000000000000000";
		long l = Long.valueOf(s);
		System.out.println(l);
	}
}
