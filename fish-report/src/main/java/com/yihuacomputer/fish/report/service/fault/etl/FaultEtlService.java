package com.yihuacomputer.fish.report.service.fault.etl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class FaultEtlService implements IFaultEtlService {
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IFaultWeek getWeek(long weekOfYear) {
		List<IFaultWeek> weekList = dao.findByHQL("from FaultWeek where date = ?", weekOfYear);
		if(weekList.size()>0){
			return weekList.get(0);
		}
		return null;
	}

	@Override
	public IFaultMonth getMonth(long month) {
		return dao.findUniqueByHql("from FaultMonth where date = ?", month);
	}

	@Override
	public List<IFaultClassifyWeek> getClassifyWeek(long weekOfYear) {
		return dao.findByHQL("from FaultClassifyWeek where date = ? order by faultCount desc", weekOfYear);
	}

	@Override
	public List<IFaultClassifyMonth> getClassifyMonth(long month) {
		return dao.findByHQL("from FaultClassifyMonth where date = ? order by faultCount desc", month);
	}

	@Override
	public List<IFaultDurationWeek> getDurationWeek(long weekOfYear) {
		return dao.findByHQL("from FaultDurationWeek where date = ?", weekOfYear);
	}

	@Override
	public List<IFaultDurationMonth> getDurationMonth(long month) {
		return dao.findByHQL("from FaultDurationMonth where date = ?", month);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date"})
	@Override
	public IFaultWeek saveByFaultWeek(IFaultWeek faultWeek) {
		return dao.save(faultWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date"})
	@Override
	public IFaultMonth saveByFaultMonth(IFaultMonth faultMonth) {
		return dao.save(faultMonth);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","classifyId"})
	@Override
	public IFaultClassifyWeek saveByFaultClassifyWeek(IFaultClassifyWeek faultClassifyWeek) {
		return dao.save(faultClassifyWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","classifyId"})
	@Override
	public IFaultClassifyMonth saveByFaultClassifyMonth(IFaultClassifyMonth faultClassifyMonth) {
		return dao.save(faultClassifyMonth);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","duration"})
	@Override
	public IFaultDurationWeek saveByFaultDurationWeek(IFaultDurationWeek faultDurationWeek) {
		return dao.save(faultDurationWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","duration"})
	@Override
	public IFaultDurationMonth saveByFaultDurationMonth(IFaultDurationMonth faultDurationMonth) {
		return dao.save(faultDurationMonth);
	}

}
