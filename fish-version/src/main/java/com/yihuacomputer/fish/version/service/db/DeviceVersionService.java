///**
// * 
// */
//package com.yihuacomputer.fish.version.service.db;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.yihuacomputer.domain.dao.IGenericDao;
//import com.yihuacomputer.fish.api.version.IDeviceVersion;
//import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
//import com.yihuacomputer.fish.version.entity.DeviceVersion;
//import com.yihuacomputer.fish.version.entity.Task;
//import com.yihuacomputer.fish.version.service.api.IDomainDeviceVersionService;
//
///**
// * @author xuxigang
// * 
// */
//@Service
//@Transactional
//public class DeviceVersionService implements IDomainDeviceVersionService {
//
//	@Autowired
//	private IGenericDao dao;
//	
//	@Override
//	public IDeviceVersion saveOrUpdateDeviceVersion(long deviceId, long versionId, TaskStatus taskStatus, String reason) {
//		IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
//		if(dv == null){
//			dv = new DeviceVersion();
//			dv.setDeviceId(deviceId);
//			dv.setVersionId(versionId);
//		}
//		dv.setTaskStatus(taskStatus);
//		dv.setDesc(reason);
//		return dao.saveOrUpdate(dv);
//	}
//	@Override
//	public IDeviceVersion saveOrUpdateDeviceVersionWithTaskId(long deviceId, long versionId, TaskStatus taskStatus, String reason,long taskId) {
//		IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
//		if(dv == null){
//			dv = new DeviceVersion();
//			dv.setDeviceId(deviceId);
//			dv.setVersionId(versionId);
//		}
//		dv.setCompleteTaskId(taskId);
//		dv.setTaskStatus(taskStatus);
//		dv.setDesc(reason);
//		return dao.saveOrUpdate(dv);
//	}
//	
//   @Transactional(readOnly = true)
//   public IDeviceVersion findDeviceVersionContainsRemoved(long deviceId, long versionId) {
//	      return dao.findUniqueByHql("from DeviceVersion t where t.deviceId = ? and t.versionId = ?", deviceId,versionId);
//   }
//
//    @Override
//    @Transactional(readOnly = true)
//	public IDeviceVersion findDeviceVersion(long deviceId, long versionId) {
//		return dao.findUniqueByHql("from DeviceVersion t where t.deviceId = ? and t.versionId = ? and t.taskStatus <> ?", deviceId,versionId,TaskStatus.REMOVED);
//	}
//
//	@Override
//	public void deleteDeviceVersion(long versionId) {
//		dao.batchUpdate("delete DeviceVersion dv  where dv.versionId = ? and dv.taskStatus = ?",versionId, TaskStatus.NEW);
//	}
//
//	@Override
//    @Transactional(readOnly = true)
//	public List<IDeviceVersion> listDeviceVersions() {
//		return dao.loadAll(IDeviceVersion.class);
//	}
//
//    @Override
//    @Transactional(readOnly = true)
//    public IDeviceVersion make() {
//        return new DeviceVersion();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<IDeviceVersion> listDeviceVersions(long deviceId) {
//        return dao.findByHQL("from DeviceVersion t where t.deviceId = ? order by t.lastUpdatedTime desc", deviceId);
//    }
//
//
//	@Override
//	public IDeviceVersion updateDeviceVersionStatus(long deviceId,long versionId, TaskStatus taskStatus) {
//		IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
//		if(dv == null){
//			dv = new DeviceVersion();
//			dv.setDeviceId(deviceId);
//			dv.setVersionId(versionId);
//		}
//		dv.setTaskStatus(taskStatus);
//		return dao.saveOrUpdate(dv);
//	}
//	
//	public IDeviceVersion updateDeviceVersionStatusWithTaskId(long deviceId,long versionId, TaskStatus taskStatus,long taskId) {
//		IDeviceVersion dv = this.findDeviceVersionContainsRemoved(deviceId, versionId);
//		if(dv == null){
//			dv = new DeviceVersion();
//			dv.setDeviceId(deviceId);
//			dv.setVersionId(versionId);
//		}
//		dv.setCompleteTaskId(taskId);
//		dv.setTaskStatus(taskStatus);
//		return dao.saveOrUpdate(dv);
//	}
//
//    @Override
//    public int getRelationTaskSize(long deviceId,long versionId) {
//        StringBuffer hql = new StringBuffer();
//        hql.append("select count(*) from ").append(Task.class.getName()).append(" task where task.deviceId = ? and task.version.id = ?");
//        Object object = dao.findUniqueByHql(hql.toString(), deviceId,versionId);
//        return Integer.parseInt(object.toString());
//    }
//    
//    public long getSuccess(long versionId){
//        StringBuffer hql = new StringBuffer();
//        hql.append("select count(*) from ").append(DeviceVersion.class.getName()).append(" dv where dv.taskStatus = ? and dv.versionId = ?");
//        Object object = dao.findUniqueByHql(hql.toString(), TaskStatus.CHECKED,versionId);
//        return Long.parseLong(object.toString());
//    }
//    
//    public long getFail(long versionId){
//        StringBuffer hql = new StringBuffer();
//        hql.append("select count(*) from ").append(DeviceVersion.class.getName()).append(" dv where dv.taskStatus != ? and dv.taskStatus != ? and dv.versionId = ?");
//        Object object = dao.findUniqueByHql(hql.toString(), TaskStatus.CHECKED,TaskStatus.REMOVED,versionId);
//        return Long.parseLong(object.toString());
//    }
//
//
//	@Override
//	public List<IDeviceVersion> findDevices(long versionId) {
//		return dao.findByHQL("from DeviceVersion t where t.versionId = ? and t.taskStatus <> ?", versionId,TaskStatus.REMOVED);
//
//	}
//    
//
//}
