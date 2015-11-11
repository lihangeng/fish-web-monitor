package com.yihuacomputer.fish.report.service.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IDevicePlanRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDeviceRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanService;
import com.yihuacomputer.fish.api.openplan.OpenClose;
import com.yihuacomputer.fish.api.openplan.PlanType;
import com.yihuacomputer.fish.api.openplan.Week;
import com.yihuacomputer.fish.api.report.IDayOpenRate;
import com.yihuacomputer.fish.api.report.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.IDeviceOpenRateService;

@Service
public class DeviceOpenRateService implements IDeviceOpenRateService {

	private Logger logger = LoggerFactory.getLogger(DeviceOpenRateService.class);

	@Autowired
	private IRunInfoService runInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDayOpenRateService dayOpenRateService;

	@Autowired
	private IOpenPlanDeviceRelation relationService;

	@Autowired
	private IOpenPlanService openPlanService;
	

	@Override
	public void dayOpenRate(String date) {
		logger.info("start open rate...");
		long start = System.currentTimeMillis();
		// 开机方案根据方案来计算统计，查找方案为第一步
		IFilter dfilter = new Filter();
		dfilter.addFilterEntry(FilterFactory.le("startDate", DateUtils.getDate(date)));
		dfilter.addFilterEntry(FilterFactory.ge("endDate", DateUtils.getDate(date)));
		String currDate = date;
		// 查找所有当前可以用来计算的方案的设备
		List<IDevicePlanRelation> allDevice = openPlanService.page(dfilter);

		// 查询设备状态
		String startDateTime = currDate + " 00:00:00";
		String endDateTime = currDate + " 23:59:59";
		IFilter filter = new Filter();
		filter.addFilterEntry(FilterFactory.ge("statusTime", startDateTime));
		filter.addFilterEntry(FilterFactory.le("statusTime", endDateTime));
		filter.addOrder(new OrderBy("terminalId"));
		filter.addOrder(new OrderBy("statusTime"));
		List<IRunInfo> runInfoList = runInfoService.list(filter);

		// 将list转换成map
		Map<String, List<IRunInfo>> runInfoMap = new HashMap<String, List<IRunInfo>>();
		runInfoMap = listToMap(runInfoList, runInfoMap);

		for (IDevicePlanRelation idpr : allDevice) {
			IDevice device = deviceService.get(idpr.getDeviceId());
			if (device == null) {
				logger.warn("设备不存在");
				continue;
			}
			if (device.getStatus().ordinal()==DevStatus.DISABLED.ordinal()) {
				logger.warn("设备已停用");
				continue;
			}
			List<IRunInfo> runInfos = runInfoMap.get(device.getTerminalId());
			IRunInfo yestodayRunInfo = null;
			// 最近一天最后一次记录
			IFilter yestodayFilter = new Filter();
			yestodayFilter.addFilterEntry(FilterFactory.le("statusTime", startDateTime));
			yestodayFilter.addOrder(new OrderBy("statusTime", OrderBy.DESC));
			yestodayFilter.addOrder(new OrderBy("id", OrderBy.DESC));
			yestodayFilter.addFilterEntry(FilterFactory.eq("terminalId", device.getTerminalId()));
			List<IRunInfo> yestodayRunInfoList = runInfoService.list(yestodayFilter);
			if (null != yestodayRunInfoList && yestodayRunInfoList.size() > 0) {
				yestodayRunInfo = yestodayRunInfoList.get(0);
			}
			// 找到所有关联的方案
			List<IDeviceOpenPlan> listDeviceOpenPlan = relationService.listPlanByDevice(device);
			// 有效的方案
			IDeviceOpenPlan deviceOpenPlan = getDeviceOpenPlan(listDeviceOpenPlan, date);

			// 方案明细
			List<IOpenPlanDetail> listOpenPlanDetail = openPlanService.getOpenPlanDetialById(deviceOpenPlan.getId());

			// 得到当前日期的方案明细
			List<IOpenPlanDetail> avaiListOpenPlanDetail = new ArrayList<IOpenPlanDetail>();

			if (deviceOpenPlan.getPlanType().equals(PlanType.DATE)) {
				avaiListOpenPlanDetail = listOpenPlanDetail;
			} else {
				Week week = getWeek(date);
				boolean isAddCloseNoExist = true;
				for (IOpenPlanDetail detail : listOpenPlanDetail) {
					if (detail.getOpenClose().equals(OpenClose.Open) && detail.getWeek().equals(week)) {
						avaiListOpenPlanDetail.add(detail);
					} else if (detail.getOpenClose().equals(OpenClose.Close)) {
						if (detail.getWeek().equals(week)) {
							avaiListOpenPlanDetail.add(detail);
							isAddCloseNoExist = false;
						}
					}
				}
				if (listOpenPlanDetail.get(0).getOpenClose().equals(OpenClose.Close) && isAddCloseNoExist) {
					IOpenPlanDetail newDetail = openPlanService.make();
					newDetail.setStartTime("00:00:00");
					newDetail.setEndTime("23:59:59");
					newDetail.setOpenClose(OpenClose.Open);
					newDetail.setWeek(week);
					avaiListOpenPlanDetail.add(newDetail);
				}
			}
			if (avaiListOpenPlanDetail.isEmpty()) {

				continue;
			}
			List<IOpenPlanDetail> avaiListOpenPlanDetailNew = listOpenPlanDetail(avaiListOpenPlanDetail);

			IDayOpenRate dayOpenRate = statDayOpenRate(currDate, device.getTerminalId(), avaiListOpenPlanDetailNew, runInfos, yestodayRunInfo);
			dayOpenRate.setStatDate(currDate);

			dayOpenRateService.save(dayOpenRate);
			logger.info(String.format("end open rate spend [%s]ms",System.currentTimeMillis() - start));
		}
	}

	/**
	 * 将方案关机时间,转换成开机时间来进行统计
	 *
	 * @param avaiListOpenPlanDetail
	 * @return
	 */
	private List<IOpenPlanDetail> listOpenPlanDetail(List<IOpenPlanDetail> avaiListOpenPlanDetail) {

		List<IOpenPlanDetail> result = new ArrayList<IOpenPlanDetail>();

		IOpenPlanDetail openPlanDetail = null;

		// 由于方案只能设置关机 、开机两种，如果有一个是开机，则全部为开机，不需要转换
		for (IOpenPlanDetail detail : avaiListOpenPlanDetail) {
			if (detail.getOpenClose().equals(OpenClose.Open)) {
				return avaiListOpenPlanDetail;
			}
		}

		for (int i = 0; i < avaiListOpenPlanDetail.size(); i++) {
			IOpenPlanDetail detail = avaiListOpenPlanDetail.get(i);

			if (i == 0) {
				if (!"00:00:00".equals(detail.getStartTime())) {
					openPlanDetail = openPlanService.make();
					openPlanDetail.setOpenClose(OpenClose.Open);
					openPlanDetail.setStartTime("00:00:00");
					openPlanDetail.setEndTime(detail.getStartTime());

					result.add(openPlanDetail);

				}
			}

			if (i == avaiListOpenPlanDetail.size() - 1) {
				if (!"23:59:59".equals(detail.getEndTime())) {
					openPlanDetail = openPlanService.make();
					openPlanDetail.setOpenClose(OpenClose.Open);
					openPlanDetail.setStartTime(detail.getEndTime());
					openPlanDetail.setEndTime("23:59:59");

					result.add(openPlanDetail);
				}
				break;
			}
			openPlanDetail = openPlanService.make();
			openPlanDetail.setOpenClose(OpenClose.Open);
			openPlanDetail.setStartTime(detail.getEndTime());
			openPlanDetail.setEndTime(avaiListOpenPlanDetail.get(i + 1).getStartTime());

			result.add(openPlanDetail);
		}

		return result;
	}

	/**
	 * 统计日期在哪个方案区间内,如果符合条件的有多个方案,只取第一个方案
	 *
	 * @param listDeviceOpenPlan
	 * @param date
	 * @return
	 */
	private IDeviceOpenPlan getDeviceOpenPlan(List<IDeviceOpenPlan> listDeviceOpenPlan, String date) {

		IDeviceOpenPlan result = null;

		date = date + " 00:00:00";

		for (IDeviceOpenPlan plan : listDeviceOpenPlan) {

			if (distanceTimes(DateUtils.getDate(plan.getStartDate()) + " 00:00:00", date) >= 0 && distanceTimes(date, DateUtils.getDate(plan.getEndDate()) + " 23:59:59") >= 0) {
				result = plan;
				break;
			}
		}

		return result;
	}

	private IDayOpenRate statDayOpenRate(String currDate, String terminalId, List<IOpenPlanDetail> avaiListOpenPlanDetail, List<IRunInfo> listRunInfo, IRunInfo yestodayRunInfo) {
		try {
			IDayOpenRate dayOpenRate = dayOpenRateService.make();

			/** 设备应开机时长 */
			int openTimes = 0; // 设备应开机时长(单位：分钟)

			// 状态要根据每次状态变化为准，不能每个详情中都默认为正常
			RunStatus status = RunStatus.Healthy;

			for (IOpenPlanDetail detail : avaiListOpenPlanDetail) {
				int i = 0;
				String openDateTime = currDate + " " + detail.getStartTime();
				String closeDateTime = currDate + " " + detail.getEndTime();
				List<IRunInfo> resultRunInfo = new ArrayList<IRunInfo>();
				if (listRunInfo != null) {
					for (IRunInfo runInfo : listRunInfo) {
						if (!(distanceTimes(openDateTime, runInfo.getStatusTime()) > 0)) {
							yestodayRunInfo = runInfo;
						} else {
							resultRunInfo.add(runInfo);
						}
					}
				}
				openTimes += distanceTimes(openDateTime, closeDateTime);
				// 当状态时间大于设备关机时间，计算最后一次后，直接跳出循环
				boolean isBreak = false;
				// 如果当天没有变化
				if (null == resultRunInfo || resultRunInfo.size() == 0) {
					if (null != yestodayRunInfo) {
						status = yestodayRunInfo.getRunStatus();
					}
					long constant = distanceTimes(openDateTime, closeDateTime);
					dayOpenRate = sumAllTimes(dayOpenRate, status, (int) constant);
				} else {
					for (; i < resultRunInfo.size();) {
						/**
						 * 此次状态信息
						 * */
						IRunInfo firstRunInfo = resultRunInfo.get(i);
						if (i == 0) {
							// 前一天的状态,刚上此系统前一天的数据可能为null
							if (null != yestodayRunInfo) {
								status = yestodayRunInfo.getRunStatus();
							}
						} else {
							// 上一次状态
							status = resultRunInfo.get(i - 1).getRunStatus();
						}
						// 此次状态切换时间点
						String firststatusTime = firstRunInfo.getStatusTime();
						// 是否在方案时间断之后，跳出
						if (isBreak) {
							break;
						}

						String s1 = "";
						String s2 = "";
						if (i == 0) {
							s1 = openDateTime;
						} else {
							String lastStatusTime = resultRunInfo.get(i - 1).getStatusTime();
							if (distanceTimes(openDateTime, lastStatusTime) > 0 && distanceTimes(closeDateTime, lastStatusTime) < 0) {
								s1 = lastStatusTime;
							} else {
								s1 = openDateTime;
							}
						}
						// 如果状态切换时间点小于等于方案开机时间，取下一个状态切换
						if (!(distanceTimes(openDateTime, firststatusTime) > 0)) {

							i++;
							continue;
						}
						// 如果状态在方案明细时间内
						else if (distanceTimes(openDateTime, firststatusTime) > 0 && distanceTimes(closeDateTime, firststatusTime) < 0) {
							i++;
							s2 = firststatusTime;
						}
						// 如果状态时间大于等于方案关机时间
						else {
							// 直接计算此方案时间
							s2 = closeDateTime;
							isBreak = true;
						}
						long constant = distanceTimes(s1, s2);

						dayOpenRate = sumAllTimes(dayOpenRate, status, (int) constant);
						// 当状态表里最后一个时间，小于方案设置的时间，则最后时间至方案结束时间也要计算
						if (i == resultRunInfo.size() && distanceTimes(firststatusTime, closeDateTime) > 0) {
							status = firstRunInfo.getRunStatus();
							constant = distanceTimes(s2, closeDateTime);
							dayOpenRate = sumAllTimes(dayOpenRate, status, (int) constant);
						}
					}
				}
			}
			dayOpenRate.setOpenTimes(openTimes);
			dayOpenRate.setTerminalId(terminalId);
			return dayOpenRate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private IDayOpenRate sumAllTimes(IDayOpenRate dayOpenRate, RunStatus status, int constant) {
		if (RunStatus.Healthy.equals(status) || RunStatus.SubHealth.equals(status) || RunStatus.Customer.equals(status)) {
			// 正常模式
			dayOpenRate.setHealthyTimeReal(dayOpenRate.getHealthyTimeReal() + constant);
		} else if (RunStatus.StopAtmp.equals(status)) {
			// atmp故障
			dayOpenRate.setAtmpTimeReal(dayOpenRate.getAtmpTimeReal() + constant);
		} else if (RunStatus.Unknown.equals(status)) {
			// 未知
			dayOpenRate.setUnknownTimeReal(dayOpenRate.getUnknownTimeReal() + constant);
		} else if (RunStatus.StopMod.equals(status)) {
			// 硬件故障
			dayOpenRate.setFaultTimeReal(dayOpenRate.getFaultTimeReal() + constant);
		} else if (RunStatus.Maintain.equals(status) || RunStatus.Vdm.equals(status)) {
			// 维护
			dayOpenRate.setMaintainTimeReal(dayOpenRate.getMaintainTimeReal() + constant);
		} else if (RunStatus.Halt.equals(status) || RunStatus.Initial.equals(status) || RunStatus.ReBoot.equals(status) || RunStatus.StopManmade.equals(status)
				|| RunStatus.StopUnCashIn.equals(status) || RunStatus.StopUnKnown.equals(status) /*|| RunStatus.StopMonitor.equals(status)*/) {
			// 暂停故障
			dayOpenRate.setStopTimeReal(dayOpenRate.getStopTimeReal() + constant);
		}
		return dayOpenRate;
	}

	/**
	 * 计算两个日期时间相差多分钟
	 *
	 * @param startStr
	 *            开始日期时间
	 * @param endStr
	 *            结束日期时间
	 * @return long 分钟数
	 */
	private int distanceTimes(String startStr, String endStr) {
		long result = 0L;

		long time1 = DateUtils.getTimestamp(startStr).getTime();
		long time2 = DateUtils.getTimestamp(endStr).getTime();

		if (endStr.endsWith("23:59:59")) {
			time2 += 1000l;
		}

		result = (time2 - time1) / 1000;
		return (int) result;
	}

	/**
	 * 将集合中，相同设备号的集中到MAP
	 *
	 * @param target
	 * @param result
	 * @return
	 */
	private Map<String, List<IRunInfo>> listToMap(List<IRunInfo> target, Map<String, List<IRunInfo>> result) {
		List<IRunInfo> value = null;
		String key = "";

		for (IRunInfo runInfo : target) {
			key = runInfo.getTerminalId();

			if (result.containsKey(key)) {
				result.get(key).add(runInfo);
			} else {
				value = new ArrayList<IRunInfo>();
				value.add(runInfo);
				result.put(key, value);
			}
		}

		return result;
	}

	/**
	 * 将日期转换为星期
	 *
	 * @param date
	 *            日期
	 * @return 星期
	 */
	private Week getWeek(String date) {

		Week week = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getDate(date));
		int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;

		switch (weekDay) {
		case 0:
			week = Week.Sun;
			break;
		case 1:
			week = Week.Mon;
			break;
		case 2:
			week = Week.Tues;
			break;
		case 3:
			week = Week.Wed;
			break;
		case 4:
			week = Week.Thur;
			break;
		case 5:
			week = Week.Fri;
			break;
		case 6:
			week = Week.Sat;
			break;
		default:
			break;
		}
		return week;
	}

	public static void main(String[] args) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}