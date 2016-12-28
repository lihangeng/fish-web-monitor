package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.monitor.entity.alarm.IllegalProcess;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class ProcessService implements IProcessService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IProcess get(long id) {
		return dao.get(id, SysProcess.class);
	}

	@Override
	public IProcess add(IProcess process) {
		return this.dao.save(process);
	}

	@Override
	public void remove(long id) {
		this.dao.delete(id, SysProcess.class);
	}

	@Override
	public void update(IProcess process) {
		this.dao.update(process);
	}

	@Override
	public Iterable<IProcess> list() {
		List<IProcess> process = new ArrayList<IProcess>();
		process.addAll(this.dao.loadAll(SysProcess.class));
		return process;
	}

	@Override
	public Iterable<IProcess> list(IFilter filter) {
		List<IProcess> process = new ArrayList<IProcess>();
		process.addAll(this.dao.findByFilter(filter, SysProcess.class));
		return process;
	}

	@Override
	public List<IProcess> getSchindlerList() {
		List<IProcess> process = new ArrayList<IProcess>();
		process.addAll(this.dao.loadAll(SysProcess.class));
		return process;
	}

	@Override
	public void saveSchindlerAlarm(String terminalId, List<IIllegalProcess> processList) {
		for (IIllegalProcess process : processList) {
			process.setTerminalId(terminalId);
			this.dao.save(process);
		}
	}

	@Override
	public IIllegalProcess makeIlegProc() {
		return new IllegalProcess();
	}

	@Override
	public IIllegalProcess getSchindlerAlarm(long id) {
		return dao.get(id, IllegalProcess.class);
	}

	@Override
	public Iterable<IIllegalProcess> listSchindlerAlarm() {
		List<IIllegalProcess> illegalProcess = new ArrayList<IIllegalProcess>();
		illegalProcess.addAll(this.dao.loadAll(IllegalProcess.class));
		return illegalProcess;
	}

	@Override
	public Iterable<IIllegalProcess> listSchindlerAlarm(IFilter filter) {
		List<IIllegalProcess> illegalProcess = new ArrayList<IIllegalProcess>();
		illegalProcess.addAll(this.dao.findByFilter(filter, IllegalProcess.class));
		return illegalProcess;
	}

	@Override
	public List<IProcess> findByHQL(String value) {
		String hql = "from SysProcess sys where Upper(sys.name) = ?";
		List<IProcess> list = dao.findByHQL(hql, value);
		return list;
	}

	@Override
	public IProcess make() {
		return new SysProcess();
	}

	@Override
	public IPageResult<IProcess> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, SysProcess.class);
	}

	@Override
	public IPageResult<IIllegalProcess> pageSchindlerAlarm(int offset, int limit, IFilter filter) {
		List<IIllegalProcess> lists = new ArrayList<IIllegalProcess>();
		EntityUtils.convert(listSchindlerAlarm(filter), lists);
		return new PageResult<IIllegalProcess>(lists, offset, limit);
	}
}
