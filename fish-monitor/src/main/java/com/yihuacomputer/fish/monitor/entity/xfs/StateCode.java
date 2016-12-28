package com.yihuacomputer.fish.monitor.entity.xfs;

/**
 * @author YiHua
 *
 */
public class StateCode {

	private String code;
	private String catalog;
	private String stateDescription;
	private String solution;

	/**
	 * @param code
	 * @param catalog
	 * @param desc
	 * @param solution
	 */
	public StateCode(String code, String catalog, String desc, String solution) {
		this.code = code;
		this.catalog = catalog;
		this.stateDescription = desc;
		this.solution = solution;
	}

	public String getCode() {
		return this.code;
	}

	public String getCatalog() {
		return this.catalog;
	}

	public String getStateDescription() {
		return this.stateDescription;
	}

	public String getSolution() {
		return this.solution;
	}
}
