package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.IDeviceUseCountRpt;
import com.yihuacomputer.fish.api.report.base.IDeviceUseCountRptService;

@Service
@Transactional(readOnly = true)
public class DeviceUseCountRptService implements IDeviceUseCountRptService {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(DeviceUseCountRptService.class);
    
    @Autowired
    private IGenericDao dao;

    private final String countName = "设备数量";

    @Override
    public List<IDeviceUseCountRpt> listDeviceUseCount(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select org.name, type.name, device.status, count(device.terminalId) ");
        hql.append("from Organization org, Device device, AtmType type ");
        hql.append("where org.id = device.organization  and type.id = device.devType ");

        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry devTypeId = filter.getFilterEntry("devTypeId");
        IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
        
        if (orgFlag != null) {
            hql.append("and org.orgFlag like ? ");
            valueObj.add("%" + orgFlag.getValue());
        }
        if (devTypeId != null) {
            hql.append("and device.devType.id = ? ");
            valueObj.add(devTypeId.getValue());
        }
        
        if (devVendorId != null) {
            hql.append(" and type.devVendor.id = ? ");
            valueObj.add(devVendorId.getValue());
        }
        
        hql.append("group by org.name, device.status, type.name ");
        
        logger.info(hql.toString());
        
        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<IDeviceUseCountRpt> deviceUseCountList = new ArrayList<IDeviceUseCountRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            DeviceUseCountRpt deviceUseCount = new DeviceUseCountRpt();
            deviceUseCount.setCouneName(countName);
            deviceUseCount.setOrgName(objectToString(o[0]));
            deviceUseCount.setDevTypeName(objectToString(o[1]));
            if (objectToString(o[2]).equals("OPENING")) {
                deviceUseCount.setDevUseState("开通");
            }
            if (objectToString(o[2]).equals("DISABLED")) {
                deviceUseCount.setDevUseState("停用");
            }
            deviceUseCount.setDeviceCount(Integer.valueOf(objectToString(o[3])));
            deviceUseCountList.add(deviceUseCount);
        }
        return deviceUseCountList;
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
