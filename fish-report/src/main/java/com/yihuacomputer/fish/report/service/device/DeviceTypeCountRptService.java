package com.yihuacomputer.fish.report.service.device;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



//import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRptService;

@Service
@Transactional(readOnly = true)
public class DeviceTypeCountRptService implements IDeviceTypeCountRptService {

    @Autowired
    private IGenericDao dao;

//    private String countName ;
    
	@Autowired
	private MessageSource messageSource;

    @Override
    public List<IDeviceTypeCountRpt> listDeviceTypeCount(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select org.name, vendor.name, type.name, count(device.terminalId) ");
        hql.append("from Organization org, Device device, AtmVendor vendor, AtmType type ");
        hql.append("where device.organization.id = org.id and device.devType.id = type.id ");
        hql.append("and type.devVendor.id = vendor.id ");

        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
        IFilterEntry devTypeId = filter.getFilterEntry("devTypeId");
        IFilterEntry orgLevel = filter.getFilterEntry("orgLevel");
		IFilterEntry endData = filter.getFilterEntry("endDateTime");
		IFilterEntry startData = filter.getFilterEntry("startDateTime");

        if (orgFlag != null) {
            hql.append("and device.organization.orgFlag like ? ");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (devVendorId != null) {
            hql.append("and vendor.id = ? ");
            valueObj.add(devVendorId.getValue());
        }
        if (devTypeId != null) {
            hql.append("and type.id = ? ");
            valueObj.add(devTypeId.getValue());
        }
        if (orgLevel != null) {
            hql.append("and org.organizationLevel = ? ");
            valueObj.add(orgLevel.getValue());
        }
		if (endData != null) {
			hql.append(" and device.applyTime<=?");
			valueObj.add(endData.getValue());
		}
		if (startData != null) {
			hql.append(" and device.applyTime>=?");
			valueObj.add(startData.getValue());
		}
        hql.append("group by ");

        hql.append(" org.name, vendor.name, type.name ");

        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<IDeviceTypeCountRpt> deviceTypeList = new ArrayList<IDeviceTypeCountRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            DeviceTypeCountRpt deviceTypeCount = new DeviceTypeCountRpt();
//            countName = messageSource.getMessage("report.devTypeCount.countName", null, FishCfg.locale);
            
//            deviceTypeCount.setCountName(countName);
            deviceTypeCount.setOrgName(objectToString(o[0]));
            deviceTypeCount.setVendorName(objectToString(o[1]));
            deviceTypeCount.setDevTypeName(objectToString(o[2]));
            deviceTypeCount.setDeviceCount(Integer.valueOf(objectToString(o[3])));
            
//            deviceTypeCount.setOrgNameColumn(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
//            deviceTypeCount.setVendorNameColumn(messageSource.getMessage("report.devTypeCount.vendorName", null, FishCfg.locale));
//            deviceTypeCount.setDevTypeNameColumn(messageSource.getMessage("report.devTypeCount.type", null, FishCfg.locale));
//            deviceTypeCount.setSubtotalColumn(messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));
//            deviceTypeCount.setTotalColumn(messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
            
            deviceTypeList.add(deviceTypeCount);
        }
        return deviceTypeList;
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
