package com.yihuacomputer.fish.report.engine;

import com.yihuacomputer.fish.api.report.engine.IReportExport;

public class ReportExport implements IReportExport {

	private boolean isSuccess;
	
	private String pdfFile;
	
	private String htmlFile;
	
	private String xlsFile;
	
	@Override
	public String getPdfFile() {
		return pdfFile;
	}

	@Override
	public String getHtmlFile() {
		return htmlFile;
	}

	@Override
	public String getXlsFile() {
		return xlsFile;
	}

	@Override
	public boolean isSuccess() {
		return isSuccess;
	}	
	
	public void setPdfFile(String pdfFile) {
		this.pdfFile =  pdfFile;
	}

	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}

	public void setXlsFile(String xlsFile) {
		this.xlsFile = xlsFile;
	}

	public void setResult(boolean result) {
		this.isSuccess = result;
	}
}
