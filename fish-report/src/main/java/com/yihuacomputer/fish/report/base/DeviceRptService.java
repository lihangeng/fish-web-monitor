package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.IDeviceRpt;
import com.yihuacomputer.fish.api.report.base.IDeviceRptService;
import com.yihuacomputer.fish.api.report.base.IDeviceTypeCountRpt;

@Service
@Transactional(readOnly = true)
public class DeviceRptService implements IDeviceRptService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public List<IDeviceRpt> getDeviceByOrg(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDeviceRpt> getDeviceByVendor(String vendorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDeviceTypeCountRpt> getDeviceCountByOrg(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<IDeviceRpt> listDevice(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select device.organization.code, device.organization.name, device.terminalId, type.devVendor.name, type.name, " +
        		"device.ip, device.awayFlag, device.setupType, device.devService.name, device.address ");
        hql.append("from Device device, AtmType type ");
        hql.append("where device.devType.id = type.id ");

        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");

        if (orgFlag != null) {
            hql.append("and device.organization.orgFlag like ? ");
            valueObj.add("%" + orgFlag.getValue());
        }
        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<IDeviceRpt> deviceList = new ArrayList<IDeviceRpt>();
        int index = 1;
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            DeviceRpt deviceRpt = new DeviceRpt();
            deviceRpt.setOrgCode(objectToString(o[0]));
            deviceRpt.setOrgName(objectToString(o[1]));
            deviceRpt.setTerminalId(objectToString(o[2]));
            deviceRpt.setDevVendorName(objectToString(o[3]));
            deviceRpt.setDevTypeName(objectToString(o[4]));
            deviceRpt.setDeviceIp(objectToString(o[5]));
            
            String awayType = objectToString(o[6]);
            if ("LINE".equals(awayType)) {
            	deviceRpt.setAwayFlag(messageSource.getMessage("report.device.line", null, FishCfg.locale));
            } else if ("FROM_THE_LINE".equals(awayType)) {
            	deviceRpt.setAwayFlag(messageSource.getMessage("report.device.fromline", null, FishCfg.locale));
            } else if ("STAND_ALONE".equals(awayType)) {
            	deviceRpt.setAwayFlag(messageSource.getMessage("report.device.standAlone", null, FishCfg.locale));
            }
            
            if (objectToString(o[7]).equals("WEAR_WALL")) {
                deviceRpt.setSetupType(messageSource.getMessage("report.deviceBoxDetail.wareWall", null, FishCfg.locale));
            } else if (objectToString(o[7]).equals("LOBBY")) {
                deviceRpt.setSetupType(messageSource.getMessage("report.deviceBoxDetail.lobby", null, FishCfg.locale));
            }
            deviceRpt.setMantainOrg(objectToString(o[8]));
            deviceRpt.setAddress(objectToString(o[9]));
            deviceRpt.setIndex(index + "");
            index++;
            
            deviceList.add(deviceRpt);
        }
        return deviceList;
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
