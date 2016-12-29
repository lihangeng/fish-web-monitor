package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.ITransType;


/**
 * @author YiHua
 *
 */
public class TransTypeForm {

	private long id;
	
	private String transCode;
	
	private String codeDesc;
	
	
	public TransTypeForm(){
		
	}
	
	/**
	 * @param tansType
	 */
	public TransTypeForm(ITransType tansType){
		this.id = tansType.getId();
		this.transCode = tansType.getTransCode();
		this.codeDesc = tansType.getCodeDesc();
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static List<TransTypeForm> convert(List<ITransType> list) {
		List<TransTypeForm> result = new ArrayList<TransTypeForm>();
		for (ITransType item : list) {
			result.add(new TransTypeForm(item));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	
	
	
}
