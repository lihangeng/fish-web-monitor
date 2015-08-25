package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmCatalogService {
	public IAtmCatalog make();

	public IAtmCatalog get(long id);

	public IAtmCatalog get(String no);

	public IAtmCatalog add(IAtmCatalog catalog);

	public void remove(long id);

	public void remove(String no);

	public void update(IAtmCatalog catalog);

	public Iterable<IAtmCatalog> list();

	public IPageResult<IAtmCatalog> page(int offset, int limit, IFilter filter);

	public Iterable<IAtmCatalog> list(IFilter filter);
}
