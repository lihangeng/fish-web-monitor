/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.version.IDeviceVersion;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.version.entity.DeviceVersion;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceVersionService;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional
public class DeviceVersionService implements IDomainDeviceVersionService {

    @Autowired
    private IGenericDao dao;

    private Logger logger = LoggerFactory.getLogger(DeviceVersionService.class);

    @Override
    public IDeviceVersion saveOrUpdateDeviceVersion(long deviceId, long versionId, TaskStatus taskStatus, String reason) {
        IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
        if (dv == null) {
            dv = new DeviceVersion();
            dv.setDeviceId(deviceId);
            dv.setVersionId(versionId);
        }
        dv.setTaskStatus(taskStatus);
        dv.setDesc(reason);

        return dao.saveOrUpdate(dv);
    }

    public void saveOrUpdateDeviceVersionForList(long deviceId, long versionId, TaskStatus taskStatus, String reason) {
        List<IDeviceVersion> dvList = this.findDeviceVersionContainsRemovedList(deviceId, versionId);
        if (dvList == null || dvList.isEmpty()) {
            IDeviceVersion dv = new DeviceVersion();
            dv.setDeviceId(deviceId);
            dv.setVersionId(versionId);
            dv.setTaskStatus(taskStatus);
            dv.setDesc(reason);
            dao.saveOrUpdate(dv);
        } else {
            for (IDeviceVersion tempDv : dvList) {
                try {
                    tempDv.setTaskStatus(taskStatus);
                    tempDv.setDesc(reason);
                    dao.saveOrUpdate(tempDv);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(String.format("update terminal [%s] deviceVersion error : [%s]", tempDv == null ? ""
                            : tempDv.getDeviceId(), e));
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public IDeviceVersion findDeviceVersionContainsRemoved(long deviceId, long versionId) {
        return dao
                .findUniqueByHql("from DeviceVersion t where t.deviceId = ? and t.versionId = ?", deviceId, versionId);
    }

    @Transactional(readOnly = true)
    private List<IDeviceVersion> findDeviceVersionContainsRemovedList(long deviceId, long versionId) {
        return dao.findByHQL("from DeviceVersion t where t.deviceId = ? and t.versionId = ?", deviceId, versionId);
    }

    @Transactional(readOnly = true)
    public List<IDeviceVersion> findDeviceVersionContainsRemoved(long versionId) {
        return dao.findByHQL("from DeviceVersion t where t.versionId = ?", versionId);
    }

    @Override
    @Transactional(readOnly = true)
    public IDeviceVersion findDeviceVersion(long deviceId, long versionId) {
        List<IDeviceVersion> dvList = dao.findByHQL(
                "from DeviceVersion t where t.deviceId = ? and t.versionId = ? and t.taskStatus <> ?", deviceId,
                versionId, TaskStatus.REMOVED);
        if (dvList != null && !dvList.isEmpty()) {
            return dvList.get(0);
        }
        return null;
    }

    @Override
    public void deleteDeviceVersion(long versionId) {
        dao.batchUpdate("delete DeviceVersion dv  where dv.versionId = ? and dv.taskStatus = ?", versionId,
                TaskStatus.NEW);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IDeviceVersion> listDeviceVersions() {
        return dao.loadAll(IDeviceVersion.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IDeviceVersion make() {
        return new DeviceVersion();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IDeviceVersion> listDeviceVersions(long deviceId) {
        return dao.findByHQL("from DeviceVersion t where t.deviceId = ? order by t.lastUpdatedTime desc", deviceId);
    }

    @Override
    public IDeviceVersion updateDeviceVersionStatus(long deviceId, long versionId, TaskStatus taskStatus) {
        IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
        if (dv == null) {
            dv = new DeviceVersion();
            dv.setDeviceId(deviceId);
            dv.setVersionId(versionId);
        }
        dv.setTaskStatus(taskStatus);
        return dao.saveOrUpdate(dv);
    }

    @Override
    public void updateDeviceVersionStatusForList(long deviceId, long versionId, TaskStatus taskStatus) {
        List<IDeviceVersion> dvList = this.findDeviceVersionContainsRemovedList(deviceId, versionId);
        if (dvList == null || dvList.isEmpty()) {
            IDeviceVersion dv = new DeviceVersion();
            dv.setDeviceId(deviceId);
            dv.setVersionId(versionId);
            dv.setTaskStatus(taskStatus);
            dao.saveOrUpdate(dv);
        } else {
            for (IDeviceVersion tempDv : dvList) {
                try {
                    tempDv.setTaskStatus(taskStatus);
                    dao.saveOrUpdate(tempDv);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(String.format("update terminal [%s] deviceVersion error : [%s]", tempDv == null ? ""
                            : tempDv.getDeviceId(), e));
                }
            }
        }
    }

    @Override
    public int getRelationTaskSize(long deviceId, long versionId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from ").append(Task.class.getName())
                .append(" task where task.deviceId = ? and task.version.id = ?");
        Object object = dao.findUniqueByHql(hql.toString(), deviceId, versionId);
        return Integer.parseInt(object.toString());
    }

    public long getSuccess(long versionId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from ").append(DeviceVersion.class.getName())
                .append(" dv where dv.taskStatus = ? and dv.versionId = ?");
        Object object = dao.findUniqueByHql(hql.toString(), TaskStatus.CHECKED, versionId);
        return Long.parseLong(object.toString());
    }

    public long getFail(long versionId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from ").append(DeviceVersion.class.getName())
                .append(" dv where dv.taskStatus != ? and dv.taskStatus != ? and dv.versionId = ?");
        Object object = dao.findUniqueByHql(hql.toString(), TaskStatus.CHECKED, TaskStatus.REMOVED, versionId);
        return Long.parseLong(object.toString());
    }

    @Override
    public List<IDeviceVersion> list(IFilter filter) {
        return dao.findByFilter(filter, IDeviceVersion.class);
    }
}
