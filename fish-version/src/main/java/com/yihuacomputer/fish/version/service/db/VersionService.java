package com.yihuacomputer.fish.version.service.db;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.exception.DependException;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.file.FileMD5;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.TaskCanceledException;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.service.api.IDomainVersionService;

/**
 * 
 * @author xuxigang
 *
 */
@Service
@Transactional
public class VersionService implements IDomainVersionService {

	private Logger logger = LoggerFactory.getLogger(VersionService.class);

	@Autowired
	private IGenericDao dao;
	@Autowired(required = false)
	private IUserService userService;
	@Autowired
	private IDeviceSoftVersionService deviceVersionService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IVersionTypeService typeService;

	@Override
	@Transactional(readOnly = true)
	public IVersion make() {
		return new Version();
	}

	@Override
	@Transactional(readOnly = true)
	public IVersion getById(long id) {
		return dao.get(id, Version.class);
	}

	@Override
	public IVersion add(IVersion version) {
		Version entity = this.interface2Entity(version, false);
		String serverPath = VersionCfg.getVersionDir() + File.separator + version.getVersionType().getTypeName();
		entity.setMd5CheckNum(getMD5CheckNum(serverPath, entity.getServerPath()));
		if (version.getReleaseDate() == null) {
			version.setReleaseDate(new Date());
		}
		dao.save(entity);
		return entity;
	}

	private String getMD5CheckNum(String serverPath, String fileName) {
		File file = new File(serverPath, fileName);
		if (file.exists()) {
			return FileMD5.getFileMD5(file);
		}
		return "0";
	}

	/**
	 * 如果更新之前，压缩包有调整，需要自行修改压缩包的MD5
	 */
	@Override
	public void update(IVersion version) {
		dao.update(this.interface2Entity(version, true));
	}

	@Override
	public void delete(IVersion version) {
		delete(version.getId());
	}

	@Override
	public void delete(long id) {
		IVersion version = getById(id);
		if (version == null) {
			// throw new AppException("删除失败：删除的记录不存在，请刷新列表。");
			return;
		}
		if (findByDependVersion(id).size() > 0) {
			throw new DependException("删除失败:该版本被其它版本依赖,无法删除.");
		}
		IVersionType type = version.getVersionType();
		String fileName = version.getServerPath();
		dao.delete(id, Version.class);

		String file = VersionCfg.getVersionDir() + File.separator + type.getTypeName() + File.separator + fileName;
		File f = new File(file);
		if (f.exists() && f.isFile()) {
			f.delete();
		}
	}

	private List<Version> findByDependVersion(long id) {
		return dao.findByHQL("from Version v where v.dependVersion.id = ?", id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IVersion> list(IFilter filter) {
		return dao.findByFilter(filter, IVersion.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IPageResult<IVersion> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Version.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IPageResult<IVersion> pageCardTable(int offset, int limit, IFilter outerFilter) {
		IFilter filter = null;
		if (outerFilter == null) {
			filter = new Filter();
		} else {
			filter = outerFilter;
		}
		filter.eq("versionType.typeName", "cardTable");
		return dao.page(offset, limit, filter, Version.class);
	}

	private Version interface2Entity(IVersion version, boolean load) {
		if (version instanceof Version) {
			return (Version) version;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Version getVersion(String typeName, String versionNo) {
		Version v = findVersion(typeName, versionNo);
		if (v != null) {
			return v;
		} else {
			throw new NotFoundException(String.format("not found entity [Version.typeName = %s,versioNo = %s]", typeName, versionNo));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Version findVersion(String typeName, String versionNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Version t  where t.versionType.typeName = ? and t.versionNo = ?");
		List<Version> lists = dao.findByHQL(hql.toString(), typeName, versionNo);
		if (lists.size() > 0) {
			return lists.get(0);
		}
		return null;
	}

	public IUserService getUserService() {
		return this.userService;
	}

	/**
	 * 收集版本下发后的更新报告结果
	 * 
	 * @param taskId
	 *            任务编号
	 * @param ret
	 *            处理结果
	 */
	@Override
	public void collectUpdateReport(long taskId, String ret) {
		ITask task = taskService.get(taskId);
		if (task == null) {
			return;
		}
		if (TaskStatus.isCancel(task.getStatus())) {
			throw new TaskCanceledException("任务已取消");
		}
		AgentRet agentRet = AgentRet.valueOf(ret);
		if (agentRet.equals(AgentRet.RET40)) {
			task.setStatus(TaskStatus.DOWNLOADED);
		} else if (AgentRet.isDownFail(agentRet)) {
			task.setStatus(TaskStatus.DOWNLOADED_FAIL);
			task.setReason(agentRet.getText());
		} else if (agentRet.equals(AgentRet.RET50)) {
			task.setStatus(TaskStatus.DEPLOYED);
			task.setReason(null);
		} else if (agentRet.equals(AgentRet.RET51)) {
			task.setStatus(TaskStatus.DEPLOYED_WAIT);
			task.setReason(null);
		} else if (AgentRet.isDeployFail(agentRet)) {
			task.setStatus(TaskStatus.DEPLOYED_FAIL);
			task.setReason(agentRet.getText());
		} else {
			task.setStatus(TaskStatus.OTHER);
		}
		taskService.updateTask(task);
	}

	/**
	 * 根据软件分类和版本号计算出可以自动更新的最合适的版本
	 */
	@Override
	public IVersion autoUpdate(String typeName, String versionNo) {
		IVersionType type = typeService.getByName(typeName);
		if (type == null) {
			logger.error(String.format("在数据库中没有找到对应的软件分类:typeName = %s", typeName));
			return null;
		}
		IVersion currentVersion = this.findVersion(typeName, versionNo);
		if (currentVersion == null) {// 虚拟一个对象出来，这样就不需要通过页面配置一个初始化版本
			logger.warn(String.format("在数据库中没有找到版本:typeName = %s ,versionNo = %s", typeName, versionNo));
			currentVersion = this.make();
			currentVersion.setVersionNo(versionNo);
			currentVersion.setVersionType(type);
		}
		// 更新本版状态
		if (currentVersion.getId() > 0 && !currentVersion.getVersionStatus().equals(VersionStatus.DOWNLOADED)) {
			currentVersion.setVersionStatus(VersionStatus.DOWNLOADED);
			this.update(currentVersion);
		}
		// 获取可以下发的最高版本
		IVersion targetVersion = null;
		Filter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("autoDown", Boolean.TRUE));// 自动下发
		filter.addFilterEntry(FilterFactory.eq("versionType.typeName", typeName));// 版本类型匹配
		/*
		 * filter.addFilterEntry(FilterFactory.gt("versionNo", versionNo));//
		 * 大于versionNo的版本，会出错的，
		 * 比如存在0.4和0.14，按照我们的版本规则0.14大于0.4。当我们根据0.4去获得可下发的版本的版本时
		 * ，在数据库中就找不到0.14，因为数据库中的比较式基于字符串的
		 */
		filter.addOrder(new OrderBy("id", OrderBy.DESC));
		List<IVersion> versions = list(filter);
		targetVersion = getMaxVersion(versions, currentVersion);
		if (targetVersion == null) {
			logger.info(String.format("没有找到合适的自动更新版本:typeName = %s ,versionNo = %s", typeName, versionNo));
		}
		return targetVersion;
	}

	private IVersion getMaxVersion(List<IVersion> maybeVersions, IVersion currentVersion) {
		IVersion version = currentVersion;
		for (IVersion each : maybeVersions) {
			if (each.isAfter(version)) {
				version = each;
			}
		}

		if (version.getVersionNo().equals(currentVersion.getVersionNo())) {
			return null;
		}
		return version;
	}

	/**
	 * 登记软件版本信息
	 */
	@Override
	public void collectCurrentVersionInfo(String terminalId, String typeName, String versionNo) {
		try {
			if (typeName == null || "".equals(typeName)) {
				throw new AppException("版本类型为空");
			}
			if (versionNo == null || "".equals(versionNo)) {
				throw new AppException("版本号为空");
			}
			IDeviceSoftVersion dsv = deviceVersionService.get(terminalId, typeName);
			if (dsv != null) {
				if (!versionNo.equals(dsv.getVersionNo())) {
					dsv.setVersionNo(versionNo);
					deviceVersionService.update(dsv);
				}
			} else {
				dsv = deviceVersionService.make();
				dsv.setTerminalId(terminalId);
				dsv.setTypeName(typeName);
				dsv.setVersionNo(versionNo);
				deviceVersionService.add(dsv);
			}
		} catch (Exception ex) {
			logger.error(String.format("登记软件版本信息失败[%s]", ex.getMessage()));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getNextVersionNo(long versionTypeId) {
		IFilter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("versionType.id", versionTypeId));
		filter.addOrder(new OrderBy("id", OrderBy.DESC));
		List<IVersion> versions = this.list(filter);
		IVersion result = null;
		if (versions.size() > 0) {
			result = versions.get(0);
		}
		if (result == null) {
			return "0001";
		} else {
			String no = result.getVersionNo();
			Long v = Long.valueOf(no) + 1;
			return getStandNo(v);
		}
	}

	private String getStandNo(Long v) {
		if (v.intValue() < 10) {
			return "000" + v;
		}
		if (v.intValue() < 100) {
			return "00" + v;
		}
		if (v.intValue() < 1000) {
			return "0" + v;
		}
		return String.valueOf(v);
	}
}

enum AgentRet {
	RET00("成功"), // 成功
	RET01("失败"), // 失败
	RET0100("相同的软件分类正在升级"), RET02("ATC应用忙"), // ATMC应用忙
	RET03("Agent异常"), // Agent异常
	RET40("升级包下载成功"), RET41("升级包下载失败"), RET4100("D盘磁盘空间不足"), RET4101("部署路径所在盘磁盘空间不足"), RET4102("部署路径所在盘不存在"), RET4103("部署路径没有配置"), RET4104("服务器中断"), RET4105("下发文件MD5值校验失败"), RET4106(
			"分发的版本号比ATM上已有的版本号低,无需分发"), RET50("正在部署"), RET51("等待部署"), RET52("部署失败"), RET99("其它");

	private String text;

	private AgentRet(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static boolean isDownFail(AgentRet agentRet) {
		if (agentRet.name().startsWith("RET41")) {
			return true;
		}
		return false;
	}

	public static boolean isDeployFail(AgentRet agentRet) {
		if (agentRet.name().startsWith("RET52")) {
			return true;
		}
		return false;
	}

}
