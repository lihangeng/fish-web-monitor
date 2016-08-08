package com.yihuacomputer.fish.web.atm.format;

import java.util.List;

public class DeviceBoxReportMsg {
	private String termianlId;
	private List<BoxDetailReportMsg> boxdetailList;

	public String getTermianlId() {
		return termianlId;
	}

	public void setTermianlId(String termianlId) {
		this.termianlId = termianlId;
	}

	public List<BoxDetailReportMsg> getBoxdetailList() {
		return boxdetailList;
	}

	public void setBoxdetailList(List<BoxDetailReportMsg> boxdetailList) {
		this.boxdetailList = boxdetailList;
	}

}
