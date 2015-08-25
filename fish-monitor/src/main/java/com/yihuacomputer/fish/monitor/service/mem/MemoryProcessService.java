package com.yihuacomputer.fish.monitor.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;
import com.yihuacomputer.fish.monitor.service.base.DomainProcessService;

@Service
public class MemoryProcessService extends DomainProcessService{
    private BaseMemoryService memService = new BaseMemoryService();

    List<SysProcess> entities = new ArrayList<SysProcess>();

    List<SysProcess> alarmProcess = new ArrayList<SysProcess>();


    public SysProcess get(long id)
    {
        for(SysProcess item : entities) {
            if(id == item.getId()) {
                return item;
            }
        }
        throw new NotFoundException(String.format("not found entity [Process.id = %s]",id));
    }


    public IProcess add(IProcess process)
    {
        SysProcess entity = memService.interface2Entity(process);
        entity.setId(memService.nextId());
        entities.add(entity);
        return entity;
    }


    public void remove(long id)
    {
        entities.remove(get(id));
    }

    public void update(IProcess process)
    {
        SysProcess org = get(process.getId());
        org.update(process);

    }

    public Iterable<IProcess> list()
    {
        return EntityUtils.<IProcess>convert(entities);
    }

    public Iterable<IProcess> list(IFilter filter)
    {
        List<IProcess> result = new ArrayList<IProcess>();
        for(SysProcess each : entities){
            if(memService.isMacth(each,filter)){
                result.add(each);
            }
        }
        return result;
    }

    public List<IProcess> getSchindlerList(){

       List<IProcess> processList = EntityUtils.<IProcess>convert(entities);
       return processList;
    }


    public void saveSchindlerAlarm(IProcess process){
        SysProcess entity = memService.interface2Entity(process);
        entity.setId(memService.nextId());
        alarmProcess.add(entity);
    }


	@Override
	public void saveSchindlerAlarm(String terminalId,List<IIllegalProcess> processList) {
		// TODO Auto-generated method stub

	}


	@Override
	public IIllegalProcess makeIlegProc() {
		// TODO Auto-generated method stub
		return null;
	}


    @Override
    public IIllegalProcess getSchindlerAlarm(long id)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Iterable<IIllegalProcess> listSchindlerAlarm()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Iterable<IIllegalProcess> listSchindlerAlarm(IFilter filter)
    {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List <IProcess> findByHQL(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
