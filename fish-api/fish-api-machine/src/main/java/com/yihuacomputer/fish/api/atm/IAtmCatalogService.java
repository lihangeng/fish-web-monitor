package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmCatalogService {
	/**
	 * @return
	 */
	public IAtmCatalog make();

	/**
	 * @param id
	 * @return
	 */
	public IAtmCatalog get(long id);

	/**
	 * @param no
	 * @return
	 */
	public IAtmCatalog get(String no);

	/**
	 * @param catalog
	 * @return
	 */
	public IAtmCatalog add(IAtmCatalog catalog);

	/**
	 * @param id
	 */
	public void remove(long id);

	/**
	 * @param no
	 */
	public void remove(String no);

	/**
	 * @param catalog
	 */
	public void update(IAtmCatalog catalog);

	/**
	 * @return
	 */
	public Iterable<IAtmCatalog> list();

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmCatalog> page(int offset, int limit, IFilter filter);

	/**
	 * @param filter
	 * @return
	 */
	public Iterable<IAtmCatalog> list(IFilter filter);
}
