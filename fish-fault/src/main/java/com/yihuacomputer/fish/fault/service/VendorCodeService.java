package com.yihuacomputer.fish.fault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.IVendorCode;
import com.yihuacomputer.fish.api.fault.IVendorCodeService;
import com.yihuacomputer.fish.fault.entity.VendorCode;


@Service
@Transactional
public class VendorCodeService implements IVendorCodeService {

	private final String OPEN_VENDORCODE_HQL = "from VendorCode v where v.vendor = ?";
	
	private final String DELETE_VENDORCODE_HQL = "delete from VendorCode v where v.vendor = ?";
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IVendorCode make() {
		return new VendorCode();
	}

	@Override
	public IPageResult<IVendorCode> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, VendorCode.class);
	}

	@Override
	public List<IVendorCode> getByVendor(long vendorId) {
		List<IVendorCode> result = dao.findByHQL(OPEN_VENDORCODE_HQL, vendorId);
		return result;
	}

	@Override
	public void deleteByVendor(long vendorId) {
		dao.batchUpdate(DELETE_VENDORCODE_HQL, vendorId);
	}

	@Override
	public void save(IVendorCode vendorCode) {
		dao.save(vendorCode);
	}

}
