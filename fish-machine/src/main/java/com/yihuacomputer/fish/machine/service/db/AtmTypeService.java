package com.yihuacomputer.fish.machine.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.machine.entity.AtmType;
import com.yihuacomputer.fish.machine.service.base.DomainAtmTypeService;

@Service
@Transactional
public class AtmTypeService extends DomainAtmTypeService
{
    @Autowired
    private IGenericDao dao;

    @Override
    @Transactional(readOnly = true)
    public IAtmType get(long id)
    {
        return dao.get(id, AtmType.class);
    }

    @Override
    public IAtmType add(IAtmType type)
    {
        return dao.save(type);
    }

    @Override
    public void remove(long id)
    {
    	dao.delete(id, AtmType.class);
    }

    @Override
    public void update(IAtmType type)
    {
        dao.update(interface2Entity(type,true));
    }

    @Override
    @Transactional(readOnly=true)
    public List<IAtmType> list()
    {
    	String hql = "select  atmType from AtmType  atmType  order by  typeStatus asc ";
    	return dao.findByHQL(hql);
    }

    @Override
    @Transactional(readOnly=true)
    public List<IAtmType> list(IFilter filter)
    {
        List<AtmType> atmTypeList = dao.findByFilter(filter, AtmType.class);
        return EntityUtils.<IAtmType> convert(atmTypeList);
    }

    private AtmType interface2Entity(IAtmType type, boolean load) {
		if (type instanceof AtmType) {
			return (AtmType) type;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public IAtmType get(String name) {
        IAtmType atmType = (IAtmType) dao.getCriteria(AtmType.class)
        .add(Restrictions.eq("name", name)).uniqueResult();
        return atmType;
	}

	@Override
	@Transactional(readOnly = true)
	public List<IAtmType> listByBrand(IAtmVendor vendor) {
		return dao.findByHQL("from AtmType atmType where atmType.devVendor.id = ?", vendor.getId());
	}

	@Override
	public List<String> getByVendor(long vendorId) {

		List<IAtmType> typeList = dao.findByHQL("from AtmType atmType where atmType.devVendor.id = ? ", vendorId);
		if(typeList.size()==0){
			return null;
		}
		else{
			List<Long>  typeIdList = new ArrayList<Long>();
			List<String> resultList = new ArrayList<String>();
			for(IAtmType type : typeList){
				typeIdList.add(type.getId());
			}
			IFilter filter = new Filter();
			filter.in("devType.id", typeIdList);
			List<IDevice> deviceList = dao.findByFilter(filter, IDevice.class);
			if(deviceList.size()==0){
				return null;
			}
			else{
				for(IDevice d : deviceList){
					resultList.add(d.getTerminalId());
				}
				return resultList;
			}

		}
	}
}
