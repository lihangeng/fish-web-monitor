package com.yihuacomputer.fish.web.report.form;

import java.util.Comparator;

public class RunAnalysisComparator implements Comparator<RunAnalysisReportForm> {

	@Override
	public int compare(RunAnalysisReportForm o1, RunAnalysisReportForm o2) {
		if(o1.getFileName().compareTo(o2.getFileName())>0){
			return -1;
		}
		return 1;
	}

}
