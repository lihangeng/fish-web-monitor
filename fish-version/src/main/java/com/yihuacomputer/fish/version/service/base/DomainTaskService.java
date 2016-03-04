///**
// * 
// */
//package com.yihuacomputer.fish.version.service.base;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.yihuacomputer.common.IFilter;
//import com.yihuacomputer.common.filter.Filter;
//import com.yihuacomputer.common.filter.FilterFactory;
//import com.yihuacomputer.fish.api.device.IDevice;
//import com.yihuacomputer.fish.api.device.IDeviceService;
//import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
//import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
//import com.yihuacomputer.fish.api.version.job.task.ITask;
//import com.yihuacomputer.fish.version.entity.Task;
//import com.yihuacomputer.fish.version.service.api.IDomainTaskService;
//
///**
// * @author xuxigang
// *
// */
//public abstract class DomainTaskService implements IDomainTaskService{
//
//	@Autowired
//	private IDeviceService deviceService;
//
//    @Autowired
//    private IDeviceSoftVersionService deviceSoftVersionService;
//    
//	
//	@Override
//	public IDeviceService getDeviceService() {
//		return this.deviceService;
//	}
//	
//	@Override
//	public ITask make() {
//		Task task = new Task();
//		task.setTaskService(this);
//		return task;
//	}
//	
//	@Override
//	public ITask make(IDevice device) {
//		return make(device,null);
//	}
//	
//   public ITask make(IDevice device,String versionTypeName) {
//        ITask task = make();
//        task.setDevice(device);
//        IDeviceSoftVersion dsv = deviceSoftVersionService.get(device.getTerminalId(), versionTypeName);
//        if(dsv != null){
//            task.setVersionBeforeUpdate(versionTypeName + "_" + dsv.getVersionNo());
//        }
//        return task;
//    }
//	
//    @Override
//	public ITask make(long deviceId,String versionTypeName){
//	    return make(deviceService.get(deviceId),versionTypeName);
//	}
//
//	@Override
//	public ITask make(long deviceId) {
//		return make(deviceService.get(deviceId));
//	}
//
//	@Override
//	public List<ITask> findTasksByJobId(long jobId) {
//		IFilter filter = new Filter();
//		filter.addFilterEntry(FilterFactory.eq("job.jobId", jobId));
//		return list(filter);
//	}
//}
