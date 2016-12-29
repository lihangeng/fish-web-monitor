/**
 *
 */
package com.yihuacomputer.fish.web.report.form;

/**
 * @author xuxiang
 *
 */
public class CaseStatisticsForm {

	private String angle;
	private String total;

	public CaseStatisticsForm(){

	}

	/**
	 * @param angle
	 * @param total
	 */
	public CaseStatisticsForm(String angle, String total) {
		super();
		this.angle = angle;
		this.total = total;
	}

	public String getAngle() {
		return angle;
	}

	public void setAngle(String angle) {
		this.angle = angle;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


}
