/**
 * 
 */
package com.yihuacomputer.fish.web.index.form;

/**
 * @author xuxiang
 *
 */
public class ChartForm {

	private String month;
	private String data1;
	
	public ChartForm(){
		
	}
	
	public ChartForm(String month,String data1){
		this.month = month;
		this.data1 = data1;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

}
