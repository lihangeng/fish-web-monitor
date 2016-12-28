package com.yihuacomputer.fish.report.service.device;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.hardware.ICpu;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRptService;

/**
 * @author YiHua
 *
 */
@Service
@Transactional(readOnly = true)
public class DeviceHardwareRptService implements IDeviceHardwareRptService {

    private DecimalFormat fd = new DecimalFormat("0.0");

    @Autowired
    private IGenericDao dao;

    @Override
    public List<IDeviceHardwareRpt> listDeviceHardwareInfo(IFilter filter) {
//        StringBuffer hql = new StringBuffer();
//        hql.append("select device.organization.name, device.devType.name, device.terminalId, hardware.memory.memorySize, cpu.frequency, hardware.diskMem ");
//        hql.append("from Device device, Hardware hardware, Cpu cpu ");
//        hql.append("where device.terminalId = hardware.terminalId and cpu.hardware.terminalId = hardware.terminalId ");
//
//        List<Object> valueObj = new ArrayList<Object>();
//        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
//        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
//
//        if (orgFlag != null) {
//            hql.append("and device.organization.orgFlag like ? ");
//            valueObj.add(orgFlag.getValue() + "%");
//        }
//        if (terminalId != null) {
//            hql.append("and device.terminalId like ? ");
//            valueObj.add(terminalId.getValue());
//        }
//
//        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
//        List<IDeviceHardwareRpt> hardwareList = new ArrayList<IDeviceHardwareRpt>();
//        for (Object obj : list) {
//            Object[] o = (Object[]) obj;
//            DeviceHardwareRpt deviceHardware = new DeviceHardwareRpt();
//            deviceHardware.setOrgName(objectToString(o[0]));
//            deviceHardware.setDevTypeName(objectToString(o[1]));
//            deviceHardware.setTerminalId(objectToString(o[2]));
//            deviceHardware.setMemory(objectToString(o[3]));
//            deviceHardware.setCpuHz(objectToString(o[4]));
//            deviceHardware.setHardDisk(objectToString(o[5]));
//            hardwareList.add(deviceHardware);
//        }
//        return hardwareList;



        StringBuffer hql = new StringBuffer();
        hql.append("select device, hardware ");
        hql.append("from Device device, Hardware hardware ");
        hql.append("where device.terminalId = hardware.terminalId ");

        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");

        if (orgFlag != null) {
            hql.append("and device.organization.orgFlag like ? ");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (terminalId != null) {
            hql.append("and device.terminalId like ? ");
            valueObj.add("%" + terminalId.getValue() + "%");
        }

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<IDeviceHardwareRpt> hardwareList = new ArrayList<IDeviceHardwareRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;

            IDevice device = (IDevice) o[0];
            IHardware hardware = (IHardware) o[1];

            DeviceHardwareRpt deviceHardware = new DeviceHardwareRpt();
            deviceHardware.setOrgName(objectToString(device.getOrganization().getName()));
            deviceHardware.setDevTypeName(objectToString(device.getDevType().getName()));
            deviceHardware.setTerminalId(objectToString(device.getTerminalId()));

            deviceHardware.setMemory(objectToString(fd.format(hardware.getMemory().getMemorySize() * 1.0 / (1024 * 1024 * 1024))));

            List<ICpu> listCpu = hardware.getCpu();

            StringBuffer cpuSb = new StringBuffer();

            for (ICpu cpu : listCpu) {
                cpuSb.append(cpu.getVendor()).append(" ");
                cpuSb.append(cpu.getModel()).append(" ");
                cpuSb.append(fd.format(cpu.getFrequency() * 1.0 / 1024)).append("GHz ");
            }

            deviceHardware.setCpuHz(objectToString(cpuSb));
            deviceHardware.setHardDisk(objectToString(fd.format(hardware.getDiskMem() * 1.0 / (1024 * 1024))));
            hardwareList.add(deviceHardware);
        }

        return hardwareList;




    }

    /**
     * 对象转换为字符串，null转为""
     *
     * @param obj
     *            目标对象
     * @return 返回字符串
     */
    private String objectToString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
}
