package com.yihuacomputer.fish.report.service.device;

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
import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRptService;

/**
 * @author YiHua
 *
 */
@Service
@Transactional(readOnly = true)
public class DeviceBoxDetailRptService implements IDeviceBoxDetailRptService {

    @Autowired
    private IGenericDao dao;

	@Autowired
	private MessageSource messageSource;

    @Override
    public List<IDeviceBoxDetailRpt> listDeviceBoxDetail(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select device.organization.name,  device.terminalId, device.devType.devVendor.name, ");
        hql.append("device.devType.name, device.setupType, device.status, xfsStatus.boxCurrentCount ");
        hql.append("from Device device, XfsStatus xfsStatus ");
        hql.append("where device.terminalId = xfsStatus.terminalId ");

        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
        IFilterEntry devTypeId = filter.getFilterEntry("devTypeId");
        IFilterEntry setupId = filter.getFilterEntry("setupId");
        IFilterEntry deviceStatusId = filter.getFilterEntry("deviceStatusId");

        if (orgFlag != null) {
            hql.append("and device.organization.orgFlag like ? ");
            valueObj.add(orgFlag.getValue() + "%");
        }
        if (terminalId != null) {
            hql.append("and device.terminalId like ? ");
            valueObj.add("%" + terminalId.getValue() + "%");
        }
        if (devVendorId != null) {
            hql.append("and device.devType.devVendor.id = ? ");
            valueObj.add(devVendorId.getValue());
        }
        if (devTypeId != null) {
            hql.append("and device.devType.id = ? ");
            valueObj.add(devTypeId.getValue());
        }
        if (setupId != null) {
            hql.append("and device.setupType.id = ? ");
            valueObj.add(setupId.getValue());
        }
        if (deviceStatusId != null) {
            hql.append("and device.status.id = ? ");
            valueObj.add(deviceStatusId.getValue());
        }
        List<Object> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        List<IDeviceBoxDetailRpt> deviceBoxDetailList = new ArrayList<IDeviceBoxDetailRpt>();
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            DeviceBoxDetailRpt deviceBoxDetail = new DeviceBoxDetailRpt();
            deviceBoxDetail.setOrgName(objectToString(o[0]));
            deviceBoxDetail.setDeviceNo(objectToString(o[1]));
            deviceBoxDetail.setVendorName(objectToString(o[2]));
            deviceBoxDetail.setTypeName(objectToString(o[3]));
            if ("WEAR_WALL".equals(objectToString(o[4]))) {
                deviceBoxDetail.setSetupType(messageSource.getMessage("report.deviceBoxDetail.wareWall", null, FishCfg.locale));
            }
            if ("LOBBY".equals(objectToString(o[4]))) {
                deviceBoxDetail.setSetupType(messageSource.getMessage("report.deviceBoxDetail.lobby", null, FishCfg.locale));
            }
            if ("OPEN".equals(objectToString(o[5]))) {
                deviceBoxDetail.setStatus(messageSource.getMessage("report.deviceBoxDetail.opening", null, FishCfg.locale));
            }
            if ("DISABLED".equals(objectToString(o[5]))) {
                deviceBoxDetail.setStatus(messageSource.getMessage("report.deviceBoxDetail.disabled", null, FishCfg.locale));
            }
            if ("UNOPEN".equals(objectToString(o[5]))) {
                deviceBoxDetail.setStatus(messageSource.getMessage("report.deviceBoxDetail.unopen", null, FishCfg.locale));
            }
            if ("SCRAPPED".equals(objectToString(o[5]))) {
                deviceBoxDetail.setStatus(messageSource.getMessage("report.deviceBoxDetail.scrapped", null, FishCfg.locale));
            }
            deviceBoxDetail.setBoxLeft("-1".equals(objectToString(o[6]))?"":objectToString(o[6]));
            deviceBoxDetailList.add(deviceBoxDetail);
        }
        return deviceBoxDetailList;
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
