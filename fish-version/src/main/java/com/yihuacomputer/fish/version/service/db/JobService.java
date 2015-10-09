///**
// *
// */
//package com.yihuacomputer.fish.version.service.db;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.yihuacomputer.common.IFilter;
//import com.yihuacomputer.common.IPageResult;
//import com.yihuacomputer.common.filter.Filter;
//import com.yihuacomputer.domain.dao.IGenericDao;
//import com.yihuacomputer.fish.api.version.job.IJob;
//import com.yihuacomputer.fish.api.version.job.IJobService;
//import com.yihuacomputer.fish.version.entity.Job;
//
///**
// * @author xuxigang
// *
// */
//@Service
//@Transactional
//public class JobService implements IJobService {
//
//	private Logger logger = LoggerFactory.getLogger(JobService.class);
//    @Autowired
//    private IGenericDao dao;
//
//    @Override
//    @Transactional(readOnly = true)
//    public Job getById(long jobId) {
//        return dao.get(jobId, Job.class);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public IJob getByName(String jobName) {
//        IFilter filter = new Filter();
//        filter.eq("jobName", jobName);
//        return dao.findUniqueByFilter(filter, Job.class);
//    }
//
//    @Override
//    public Job add(IJob job) {
//        // 保存作业信息
//        Job entity = dao.save(this.interface2Entity(job, false));
//        return entity;
//    }
//
//    @Override
//    public void update(IJob job) {
//        // 更新作业信息
//        try{
//        	 Job entity = this.interface2Entity(job, true);
//        	 dao.merge(entity);
//        }catch(Exception ex){
//        	logger.error(String.format("update job status fail [%s] " ,ex.getMessage()));
//        }
//
//    }
//
//    @Override
//    public void delete(IJob job) {
//        this.dao.delete(job);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<IJob> list(IFilter outerFilter) {
//        IFilter filter = null;
//        if(outerFilter == null){
//            filter = new Filter();
//        }else{
//            filter = outerFilter;
//        }
//        return dao.findByFilter(filter, IJob.class);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public IPageResult<IJob> page(int offset, int limit, IFilter outerFilter) {
//        IFilter filter = null;
//        if(outerFilter == null){
//            filter = new Filter();
//        }else{
//            filter = outerFilter;
//        }
//        return dao.page(offset, limit, filter, Job.class);
//    }
//
//    private Job interface2Entity(IJob job, boolean load) {
//        if (job instanceof Job) {
//            return (Job) job;
//        }
//        return null;
//    }
//
//	@Override
//	public IJob make() {
//		Job job = new Job();
//		return job;
//	}
//
//}
