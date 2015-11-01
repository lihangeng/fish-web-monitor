package com.yihuacomputer.fish.monitor.entity.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

@Entity
@Table(name = "SM_STATUS_FILTER")
public class StatusFilter implements IStatusFilter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_STATUS_FILTER")
    @SequenceGenerator(name = "SEQ_SM_STATUS_FILTER", sequenceName = "SEQ_SM_STATUS_FILTER")
    @Column(name = "ID")
    private long id;
    
    @Column(name = "FILTER_NAME", length = 20)
    private String filterName;

	@Column(name = "USER_ID", length = 20, nullable = false)
	private String userId;

	@Transient
	private String terminalId;

	@Column(name = "OFFSET")
	private int offset;

	@Column(name = "DEV_LIMIT")
	private int limit;

	@Column(name = "ORG_ID")
	private String orgId;

	@Column(name = "DEV_VENDOR")
	private long devVendor;

	@Column(name = "DEV_TYPE")
	private long devType;

	@Column(name = "WORK_TYPE")
	private int workType;

	@Column(name = "AWAY_FLAG")
	private int awayFlag;

	@Column(name = "DEV_GROUP_ID")
	private long atmGroup;

	@Transient
	private List<String> deviceList;

	@Transient
	private List<Long> subOrg;

	@Embedded
	private RunStatusFilter runStatusFilter;

	@Embedded
	private BoxStatusFilter boxStatusFilter;

	@Embedded
	private ModStatusFilter modStatusFilter;

	@Embedded
	private NetStatusFilter netStatusFilter;

	@Transient
	private Status deviceStatus = Status.OPENING;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<String> getDeviceList() {
		return this.deviceList;
	}

	public IRunStatusFilter getRunStatusFilter() {
		return this.runStatusFilter;
	}

	public IBoxStatusFilter getBoxStatusFilter() {
		return this.boxStatusFilter;
	}

	public IModStatusFilter getModStatusFilter() {
		return this.modStatusFilter;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setDeviceList(List<String> deviceList) {
		this.deviceList = new ArrayList<String>();
		this.deviceList.addAll(deviceList);
	}

	public void setRunStatusFilter(IRunStatusFilter runStatusFilter) {
		this.runStatusFilter = (RunStatusFilter) runStatusFilter;
	}

	public void setBoxStatusFilter(IBoxStatusFilter boxStatusFilter) {
		this.boxStatusFilter = (BoxStatusFilter) boxStatusFilter;
	}

	public void setModStatusFilter(IModStatusFilter modStatusFilter) {
		this.modStatusFilter = (ModStatusFilter) modStatusFilter;
	}

	public INetStatusFilter getNetStatusFilter() {
		return this.netStatusFilter;
	}

	public void setNetStatusFilter(INetStatusFilter netFilter) {
		this.netStatusFilter = (NetStatusFilter) netFilter;
	}

	public long getDevVendor() {
		return devVendor;
	}

	public void setDevVendor(long devVendor) {
		this.devVendor = devVendor;
	}

	public long getDevType() {
		return devType;
	}

	public void setDevType(long devType) {
		this.devType = devType;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public int getAwayFlag() {
		return awayFlag;
	}

	public void setAwayFlag(int awayFlag) {
		this.awayFlag = awayFlag;
	}

	/**
	 * 条件判断进行过滤
	 *
	 * @param deviceReport
	 * @return
	 */
	public ReportMedthod filterStatus(IDeviceReport deviceReport) {
		IXfsStatus xfsStatus = deviceReport.getXfsStatus();
		IRunInfo runInfo = deviceReport.getRunInfo();
		IDevice device = deviceReport.getDevice();

		if (this.terminalId != null && !"".equals(this.terminalId)) {
			if (device.getTerminalId().equals(this.terminalId)) {
				return ReportMedthod.UPDATE;
			} else {
				return ReportMedthod.BEFILTERED;
			}
		}

		if (device.getOrganization() == null || device.getAwayFlag() == null
				|| device.getWorkType() == null || device.getDevType() == null) {
			return ReportMedthod.BEFILTERED;
		}

		if (this.subOrg != null
				&& !this.subOrg.contains(Long.parseLong(device
						.getOrganization().getGuid()))) {
			return ReportMedthod.BEFILTERED;
		}

		if (this.getAwayFlag() != 0
				&& device.getAwayFlag().getId() != this.getAwayFlag()) {
			return ReportMedthod.BEFILTERED;
		}

		if (this.getWorkType() != 0
				&& device.getWorkType().getId() != this.getWorkType()) {
			return ReportMedthod.BEFILTERED;
		}

		if (this.getDevType() != 0
				&& this.getDevType() != device.getDevType().getId()) {
			return ReportMedthod.BEFILTERED;
		}

		if (this.getDevVendor() != 0
				&& this.getDevVendor() != device.getDevType().getDevVendor()
						.getId()) {
			return ReportMedthod.BEFILTERED;
		}

		if (!this.isMatchStatus(xfsStatus, runInfo)) {
			if (this.deviceList.contains(deviceReport.getDeviceId())) {
				return ReportMedthod.DELETE;
			} else {
				return ReportMedthod.BEFILTERED;
			}
		} else {
			if (this.deviceList.contains(deviceReport.getDeviceId())) {
				return ReportMedthod.UPDATE;
			} else {
//				if (this.deviceList.size() < this.limit) {
//					return ReportMedthod.ADD;
//				} else {
//					return ReportMedthod.BEFILTERED;
//				}
				// 监控设备时,对于新符合监控条件的设备,不增加
				return ReportMedthod.BEFILTERED;
			}
		}
	}

	/**
	 * 判断条件是否符合
	 *
	 * @param xfsStatus
	 * @return
	 */
	@SuppressWarnings("incomplete-switch")
	private boolean isMatchStatus(IXfsStatus xfsStatus, IRunInfo runInfo) {

		if (!runStatusFilter.isAll()) {
			switch (runInfo.getRunStatus()) {
			case Healthy: {
				if (!runStatusFilter.isHealth()) {
					return false;
				}
				break;
			}
			case StopUnKnown: {
				if (!runStatusFilter.isStop()) {
					return false;
				}
				break;
			}
			case SubHealth: {
				if (!runStatusFilter.isHalf()) {
					return false;
				}
				break;
			}
			case Unknown: {
				if (!runStatusFilter.isUnknow()) {
					return false;
				}
				break;
			}
			case StopAtmp: {
				if (!runStatusFilter.isAtmpStop()) {
					return false;
				}
				break;
			}
			case Maintain: {
				if (!runStatusFilter.isMaintain()) {
					return false;
				}
				break;
			}
			case Halt: {
				if (!runStatusFilter.isShutdown()) {
					return false;
				}
				break;
			}
			case StopManmade: {
				if (!runStatusFilter.isStopManMade()) {
					return false;
				}
				break;
			}
			case Vdm: {
				if (!runStatusFilter.isVdm()) {
					return false;
				}
				break;
			}
			case Initial: {
				if (!runStatusFilter.isInitial()) {
					return false;
				}
				break;
			}
			case Customer: {
				if (!runStatusFilter.isCustomer()) {
					return false;
				}
				break;
			}
			case ReBoot: {
				if (!runStatusFilter.isReBoot()) {
					return false;
				}
				break;
			}
			case StopMod: {
				if (!runStatusFilter.isStopMod()) {
					return false;
				}
				break;
			}
			case StopUnCashIn: {
				if (!runStatusFilter.isStopUnCashIn()) {
					return false;
				}
				break;
			}
			}
		}

		if (!modStatusFilter.isAll()) {
			switch (xfsStatus.getModStatus()) {
			case Healthy: {
				if (!modStatusFilter.isHealth()) {
					return false;
				}
				break;
			}
			case Warning: {
				if (!modStatusFilter.isWarning()) {
					return false;
				}
				break;
			}
			case Fatal: {
				if (!modStatusFilter.isFatal()) {
					return false;
				}
				break;
			}
			case Unknown: {
				if (!modStatusFilter.isUnknown()) {
					return false;
				}
				break;
			}
			case NoDevice: {
				if (!modStatusFilter.isNodevice()) {
					return false;
				}
				break;
			}
			}
		}
		if (!boxStatusFilter.isAll()) {
			switch (xfsStatus.getBoxStatus()) {
			case Full: {
				if (!boxStatusFilter.isFull()) {
					return false;
				}
				break;
			}
			case Healthy: {
				if (!boxStatusFilter.isHealthy()) {
					return false;
				}
				break;
			}
			case Low: {
				if (!boxStatusFilter.isLow()) {
					return false;
				}
				break;
			}
			case Empty: {
				if (!boxStatusFilter.isEmpty()) {
					return false;
				}
				break;
			}
			case High: {
				if (!boxStatusFilter.isHigh()) {
					return false;
				}
				break;
			}
			case Fatal: {
				if (!boxStatusFilter.isFatal()) {
					return false;
				}
				break;
			}
			}
		}

		if (!netStatusFilter.isAll()) {
			switch (xfsStatus.getNetStatus()) {
			case Healthy: {
				if (!netStatusFilter.isHealth()) {
					return false;
				}
				break;
			}
			case Warning: {
				if (!netStatusFilter.isWarning()) {
					return false;
				}
				break;
			}
			case Fatal: {
				if (!netStatusFilter.isFatal()) {
					return false;
				}
				break;
			}
			case Unknown: {
				if (!netStatusFilter.isUnknown()) {
					return false;
				}
				break;
			}
			}
		}
		return true;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setRunStattusFilter(IRunStatusFilter runFilter) {
		this.runStatusFilter = (RunStatusFilter) runFilter;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public void addDevice(String terminalId) {
		this.deviceList.add(terminalId);
	}

	@Override
	public void remoteDevice(String terminalId) {
		this.deviceList.remove(terminalId);
	}

	@Override
	public List<Long> getSubOrg() {
		return this.subOrg;
	}

	@Override
	public void setSubOrg(List<Long> subOrg) {
		this.subOrg = subOrg;
	}

	@Override
	public long getAtmGroup() {
		return this.atmGroup;
	}

	@Override
	public void setAtmGroup(long atmGroupId) {
		this.atmGroup = atmGroupId;
	}

	public Status getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Status deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
}
