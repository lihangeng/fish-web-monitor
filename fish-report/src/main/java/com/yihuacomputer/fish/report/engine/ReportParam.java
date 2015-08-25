package com.yihuacomputer.fish.report.engine;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.fish.api.report.engine.IReportParam;

public class ReportParam implements IReportParam {

	private boolean pdf;

	private boolean html;

	private boolean xls;

	private String[] sheetNames;

	private Map<String, Object> parameters;

	private String reportModule;

	private List<?> dataList;

	private String reportFilepath;

	@Override
	public boolean isPdf() {
		return pdf;
	}

	@Override
	public boolean isHtml() {
		return html;
	}

	@Override
	public boolean isXls() {
		return xls;
	}

	@Override
	public Map<String, Object> getParameters() {
		return parameters;
	}

	@Override
	public String getReportModule() {
		return reportModule;
	}

	@Override
	public List<?> getDataList() {
		return dataList;
	}

	public String[] getSheetNames() {
		return sheetNames;
	}

	public String getReportFilepath() {
		return reportFilepath;
	}

	public void setPdf(boolean pdf) {
		this.pdf = pdf;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public void setXls(boolean xls) {
		this.xls = xls;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public void setReportModule(String reportModule) {
		this.reportModule = reportModule;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public void setReportFilepath(String reportFilepath) {
		this.reportFilepath = reportFilepath;
	}

	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}
}
