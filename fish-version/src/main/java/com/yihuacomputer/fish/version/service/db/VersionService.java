package com.yihuacomputer.fish.version.service.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.exception.DependException;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.file.FileMD5;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.TaskCanceledException;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.VersionDistribute;
import com.yihuacomputer.fish.api.version.VersionDistributeDetail;
import com.yihuacomputer.fish.api.version.VersionNo;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.VersionStatusDistribute;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.entity.VersionTypeAtmTypeRelation;
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
    private IDeviceService deviceService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IVersionTypeService typeService;

    @Autowired
    private MessageSource messageSourceVersion;

    @Override
    public IVersion make() {
        return new Version();
    }

    @Override
    public IVersion getById(long id) {
        return dao.get(id, Version.class);
    }

    public synchronized IVersion updateDownLoadCounter(IVersion version) {
        int counter = version.getDownloadCounter();
        version.setDownloadCounter(++counter);
        return dao.update(version);
    }

    @Override
    public IVersion add(IVersion version) {
        Version entity = this.interface2Entity(version, false);
        String serverPath = VersionCfg.getVersionDir() + File.separator + version.getVersionType().getTypeName();
        entity.setMd5CheckNum(getMD5CheckNum(serverPath, entity.getServerPath()));
        if (version.getReleaseDate() == null) {
            version.setReleaseDate(new Date());
        }
        entity.setVersionStr(getVersionStrByVersionNo(version.getVersionNo()));
        dao.save(entity);
        return entity;
    }

    public String getVersionStrByVersionNo(String versionNostr){
    	if(versionNostr==null){
    		versionNostr="0";
    	}
    	VersionNo versionNo = new VersionNo(versionNostr);
    	StringBuffer versionNoSb = new StringBuffer();
        versionNoSb.append(StringUtils.preZeroStr(String.valueOf(versionNo.getMajor()), 8))
                .append(StringUtils.preZeroStr(String.valueOf(versionNo.getMinor()), 8))
                .append(StringUtils.preZeroStr(String.valueOf(versionNo.getIncremental()), 8))
                .append(StringUtils.preZeroStr(String.valueOf(versionNo.getRevision()), 8));
        return versionNoSb.toString();
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
            String exceptionMsg = messageSourceVersion.getMessage("exception.delete.exsitDepend", null, FishCfg.locale);
            // throw new DependException("删除失败:该版本被其它版本依赖,无法删除.");
            throw new DependException(exceptionMsg);
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
            throw new NotFoundException(String.format("not found entity [Version.typeName = %s,versioNo = %s]",
                    typeName, versionNo));
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

    @Autowired
    private MessageSource messageSourceEnum;

    private String getEnumI18n(String enumText) {
        if (null == enumText) {
            return "";
        }
        return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
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
    public void collectUpdateReport(long taskId, String ret, String ip) {
        ITask task = taskService.get(taskId);
        if (task == null) {
            return;
        }
        if (TaskStatus.isCancel(task.getStatus())) {
            String exceptionMsg = messageSourceVersion.getMessage("exception.task.canceled", null, FishCfg.locale);
            // throw new TaskCanceledException("任务已取消");
            throw new TaskCanceledException(exceptionMsg);
        }
        AgentRet agentRet = AgentRet.valueOf(ret);
        if (agentRet.equals(AgentRet.RET40)) {
            task.setStatus(TaskStatus.DOWNLOADED);
            task.setDownloadFinishTime(DateUtils.getTimestamp(new Date()));
        } else if (AgentRet.isDownFail(agentRet)) {
            task.setStatus(TaskStatus.DOWNLOADED_FAIL);
            task.setReason(getEnumI18n(agentRet.getText()));
        } else if (agentRet.equals(AgentRet.RET50)) {
            task.setStatus(TaskStatus.DEPLOYED);
            task.setReason(null);
        } else if (agentRet.equals(AgentRet.RET51)) {
            task.setStatus(TaskStatus.DEPLOYED_WAIT);
            task.setReason(null);
        } else if (AgentRet.isDeployFail(agentRet)) {
            task.setStatus(TaskStatus.DEPLOYED_FAIL);
            task.setReason(getEnumI18n(agentRet.getText()));
        } else {
            task.setStatus(TaskStatus.OTHER);
        }
//        task.setDownSource(ip);
        taskService.updateTask(task);
    }

    @Override
    public ITask collectUpdateReport(long taskId, String ret, double size, String startTime, String endTime) {
        ITask task = taskService.get(taskId);

        if (task == null) {
            return null;
        }
        if (TaskStatus.isCancel(task.getStatus())) {
            throw new TaskCanceledException("任务已取消");
        }

        if ((TaskType.AUTO_UPDATE.equals(task.getTaskType()) && TaskStatus.NEW.equals(task.getStatus()))
                || (TaskType.MANUAL.equals(task.getTaskType()) && TaskStatus.NOTICED.equals(task.getStatus()))
                || TaskStatus.DOWNLOADING.equals(task.getStatus())) {
            task.setStatus(TaskStatus.DOWNLOADING);
            task.setProcess(size);
            
            taskService.updateTask(task);
        }
        return task;
    }

    /**
     * 根据软件分类和版本号计算出可以自动更新的最合适的版本
     */
    @Override
    public IVersion autoUpdate(String typeName, String versionNo) {
        IVersionType type = typeService.getByName(typeName);
        if (type == null) {
            // logger.error(String.format("在数据库中没有找到对应的软件分类:typeName = %s",
            // typeName));
            logger.error(String.format("Don't find versionType in the database:typeName = %s", typeName));
            return null;
        }
        IVersion currentVersion = this.findVersion(typeName, versionNo);
        if (currentVersion == null) {// 虚拟一个对象出来，这样就不需要通过页面配置一个初始化版本
            // logger.warn(String.format("在数据库中没有找到版本:typeName = %s ,versionNo = %s",
            // typeName, versionNo));
            logger.warn(String.format("Don't find version in the database:typeName = %s ,versionNo = %s", typeName,
                    versionNo));
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
            // logger.info(String.format("没有找到合适的自动更新版本:typeName = %s ,versionNo = %s",
            // typeName, versionNo));
            logger.info(String.format("Don't find version to autoUpdate:typeName = %s ,versionNo = %s", typeName,
                    versionNo));
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
      
	public List<IVersion> getUpdateVersion(List<IVersion> maybeVersions, IVersion currentVersion){
    	 List<IVersion>  versions = new ArrayList<IVersion>();
          for (IVersion version : maybeVersions) {
              if (version.isAfter(currentVersion)) {
            	  versions.add(version);
              }
          }      
          return versions;   	
    } 

    /**
     * 登记软件版本信息
     */
    @Override
    public void collectCurrentVersionInfo(String terminalId, String typeName, String versionNo) {
        try {
            IDevice device = deviceService.get(terminalId);

            if (null == device) {
                String exceptionMsg = messageSourceVersion.getMessage("exception.versionCollection.deviceNotExist",
                        null, FishCfg.locale);
                throw new AppException(exceptionMsg);
            }
            if (typeName == null || "".equals(typeName)) {
                String exceptionMsg = messageSourceVersion.getMessage("exception.versionCollection.versionTypeIsEmpty",
                        null, FishCfg.locale);
                throw new AppException(exceptionMsg);
            }
            if (versionNo == null || "".equals(versionNo)) {
                String exceptionMsg = messageSourceVersion.getMessage("exception.versionCollection.versionNoIsEmpty",
                        null, FishCfg.locale);
                throw new AppException(exceptionMsg);
            }
            IDeviceSoftVersion dsv = deviceVersionService.get(device.getTerminalId(), typeName);
            if (dsv != null) {
                if (!versionNo.equals(dsv.getVersionNo())) {
                    dsv.setVersionNo(versionNo);
                    dsv.setVersionStr(getVersionStrByVersionNo(versionNo));
                    dsv.setLastUpdatedTime(new Date());
                    deviceVersionService.update(dsv);
                }
            } else {
                dsv = deviceVersionService.make();
                dsv.setVersionType(typeService.getByName(typeName));
                dsv.setTerminalId(device.getTerminalId());
                dsv.setTypeName(typeName);
                dsv.setVersionNo(versionNo);
                dsv.setVersionStr(getVersionStrByVersionNo(versionNo));
                deviceVersionService.add(dsv);
            }
        }
        catch (Exception ex) {
            // logger.error(String.format("登记软件版本信息失败[%s]", ex.getMessage()));
            logger.error(String.format("save version fail[%s]", ex.getMessage()));
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

    /**
     * versionType,orgId
     * 
     * @param filter
     * @return
     */
    public List<VersionDistribute> getVersionDistribute(IFilter filter) {
        StringBuffer hqlSb = new StringBuffer();
        List<Object> hqlArgList = new ArrayList<Object>();
        Object versionType = filter.getValue("versionType");
        IVersionType versionTypeObj = typeService.getById(Long.parseLong(String.valueOf(versionType)));
        
        hqlSb.append("select devicesoftVersion.typeName,devicesoftVersion.versionNo,count(devicesoftVersion) from ")
                .append(DeviceSoftVersion.class.getSimpleName())
                .append(" devicesoftVersion,")
                .append(Device.class.getSimpleName())
                .append(" device, ")
                .append(VersionTypeAtmTypeRelation.class.getSimpleName())
                .append(" versionTypeAtmType ")
                .append("where device.devType.id= versionTypeAtmType.atmTypeId ")
                .append(" and devicesoftVersion.typeName='").append(versionTypeObj.getTypeName())
                .append("' and device.terminalId= devicesoftVersion.terminalId ").append(" and versionTypeAtmType.versionTypeId=? ")
                .append(" and device.status= ? ").append(" and device.organization.orgFlag like ? ");
        Object orgFlag = filter.getValue("orgFlag");
        hqlArgList.add(versionType);
        hqlArgList.add(DevStatus.OPEN);
        hqlArgList.add(orgFlag + "%");
        hqlSb.append(" group by devicesoftVersion.typeName,devicesoftVersion.versionNo order by devicesoftVersion.versionNo desc");
        List<Object> hqlResultList = dao.findByHQL(hqlSb.toString(), hqlArgList.toArray());
        List<VersionDistribute> list = new ArrayList<VersionDistribute>();
        for (int index = 0; index < hqlResultList.size(); index++) {
            Object hqlResult = hqlResultList.get(index);
            Object obj[] = (Object[]) hqlResult;
            String versionNo = String.valueOf(obj[1] == null ? "" : obj[1]);
            String versionTypeName = String.valueOf(obj[0] == null ? "" : obj[0]);
            VersionDistribute versionDistribute = new VersionDistribute();
            versionDistribute.setVersionTypeId(Long.parseLong(String.valueOf(versionType)));
            IVersion version = this.findVersion(versionTypeName, versionNo);
            versionDistribute.setVersionId(null==version?0:version.getId());
            versionDistribute.setVersionNo(versionNo);
            versionDistribute.setVersionNoNumber(Integer.parseInt(String.valueOf(obj[2] == null ? 0 : obj[2])));
            list.add(versionDistribute);
        }
        return list;
    }

    public List<VersionStatusDistribute> getVersionStatusDistribute(IFilter filter) {
        StringBuffer statusHql = new StringBuffer();
        List<Object> hqlArgList = new ArrayList<Object>();
        statusHql
                .append("select task.status,count(task) from ")
                .append(Device.class.getSimpleName())
                .append(" device, ")
                .append(Task.class.getSimpleName())
                .append(" task, ")
                .append(Version.class.getSimpleName())
                .append(" version, ")
                .append(VersionTypeAtmTypeRelation.class.getSimpleName())
                .append(" versionTypeAtmType ")
                .append(" where device.id=task.deviceId  and version.id = task.version.id and ")
                .append(" device.devType.id= versionTypeAtmType.atmTypeId and version.versionType.id=versionTypeAtmType.versionTypeId and ")
                .append(" version.id=? and device.status=? and device.organization.orgFlag like ? ")
                .append(" group by task.status order by task.status ");
        long versionId = Long.parseLong(String.valueOf(filter.getValue("versionId")));
        Object orgFlag = filter.getValue("orgFlag");
        hqlArgList.add(versionId);
        hqlArgList.add(DevStatus.OPEN);
        hqlArgList.add(orgFlag + "%");
        List<Object> hqlResultList = dao.findByHQL(statusHql.toString(), hqlArgList.toArray());
        List<VersionStatusDistribute> statusDistributeList = new ArrayList<VersionStatusDistribute>();
        // 有状态设备计数器
        // int hasStatusCounter = 0;
        for (int index = 0; index < hqlResultList.size(); index++) {
            Object hqlResult = hqlResultList.get(index);
            Object obj[] = (Object[]) hqlResult;
            VersionStatusDistribute versionStatusDistribute = new VersionStatusDistribute();
            versionStatusDistribute.setTaskStatus(String.valueOf(obj[0]));
            versionStatusDistribute.setVersionId(versionId);
            versionStatusDistribute.setTaskStatusNumber(Integer.parseInt(String.valueOf(obj[1])));
            TaskStatus status = TaskStatus.valueOf(versionStatusDistribute.getTaskStatus());
            versionStatusDistribute.setTaskStatusText(getEnumI18n(status.getText()));
            statusDistributeList.add(versionStatusDistribute);
        }
        
        return statusDistributeList;
    }

    public IPageResult<VersionDistributeDetail> getVersionStatusDistributeDetail(int start, int limit, IFilter filter) {
        StringBuffer statusDetailHql = new StringBuffer();
        List<Object> hqlArgList = new ArrayList<Object>();
        statusDetailHql
                .append("select task,device from ")
                .append(Device.class.getSimpleName())
                .append(" device, ")
                .append(Task.class.getSimpleName())
                .append(" task, ")
                .append(Version.class.getSimpleName())
                .append(" version, ")
                .append(VersionTypeAtmTypeRelation.class.getSimpleName())
                .append(" versionTypeAtmType ")
                .append(" where device.id=task.deviceId  and version.id = task.version.id and ")
                .append(" device.devType.id= versionTypeAtmType.atmTypeId and version.versionType.id=versionTypeAtmType.versionTypeId and ")
                .append(" version.id=? and device.status=? and device.organization.orgFlag like ? and task.status=? ");
        long versionId = Long.parseLong(String.valueOf(filter.getValue("versionId")));
        Object orgFlag = filter.getValue("orgFlag");
        Object taskStatusObj = filter.getValue("taskStatus");
        hqlArgList.add(versionId);
        hqlArgList.add(DevStatus.OPEN);
        hqlArgList.add(orgFlag + "%");
        TaskStatus taskStatus = TaskStatus.valueOf(String.valueOf(taskStatusObj));
        hqlArgList.add(taskStatus);
        @SuppressWarnings("unchecked")
        IPageResult<Object> hqlResultList = (IPageResult<Object>) dao.page(start, limit, statusDetailHql.toString(),
                hqlArgList.toArray());
        List<VersionDistributeDetail> statusDistributeList = new ArrayList<VersionDistributeDetail>();
        for (Object result : hqlResultList.list()) {
            Object[] objs = (Object[]) result;
            ITask task = (Task) objs[0];
            IDevice device = (Device) objs[1];
            VersionDistributeDetail versionDistributeDetail = new VersionDistributeDetail();
            versionDistributeDetail.setTerminalId(device.getTerminalId());
            versionDistributeDetail.setAfterUpdateVersionNo(task.getExceptVersion());
            versionDistributeDetail.setBeforeUpdateVersionNo(task.getVersionBeforeUpdate());
            versionDistributeDetail.setDeviceType(device.getDevType().getName());
            versionDistributeDetail.setIp(device.getIp().toString());
            versionDistributeDetail.setOrgName(device.getOrganization().getName());
            versionDistributeDetail.setStatusText(getEnumI18n(task.getStatus().getText()));
            versionDistributeDetail.setUpdateType(getEnumI18n(task.getTaskType().getText()));
            versionDistributeDetail.setTaskStatus(task.getStatus().name());
            versionDistributeDetail.setVersionId(task.getVersion().getId());
            versionDistributeDetail.setVendor(device.getDevType().getDevVendor().getName());
            statusDistributeList.add(versionDistributeDetail);
        }
        return new PageResult<VersionDistributeDetail>(hqlResultList.getTotal(), statusDistributeList);
    }
}

enum AgentRet {
    /**
     * 成功
     */
    RET00("AgentRet.RET00"), // 成功
    /**
     * 失败
     */
    RET01("AgentRet.RET01"), // 失败
    /**
     * 相同的软件分类正在升级
     */
    RET0100("AgentRet.RET0100"),
    /**
     * ATC应用忙
     */
    RET02("AgentRet.RET02"), // ATMC应用忙
    /**
     * Agent异常
     */
    RET03("AgentRet.RET03"), // Agent异常
    /**
     * 升级包下载成功
     */
    RET40("AgentRet.RET40"),
    /**
     * 升级包下载失败
     */
    RET41("AgentRet.RET41"),
    /**
     * D盘磁盘空间不足
     */
    RET4100("AgentRet.RET4100"),
    /**
     * 部署路径所在盘磁盘空间不足
     */
    RET4101("AgentRet.RET4101"),
    /**
     * 部署路径所在盘不存在
     */
    RET4102("AgentRet.RET4102"),
    /**
     * 部署路径没有配置
     */
    RET4103("AgentRet.RET4103"),
    /**
     * 服务器中断
     */
    RET4104("AgentRet.RET4104"),
    /**
     * 下发文件MD5值校验失败
     */
    RET4105("AgentRet.RET4105"),
    /**
     * 分发的版本号比ATM上已有的版本号低,无需分发
     */
    RET4106("AgentRet.RET4106"),
    /**
     * 正在部署
     */
    RET50("AgentRet.RET50"),
    /**
     * 等待部署
     */
    RET51("AgentRet.RET51"),
    /**
     * 部署失败
     */
    RET52("AgentRet.RET52"),
    /**
     * 其它
     */
    RET99("AgentRet.RET99");

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
