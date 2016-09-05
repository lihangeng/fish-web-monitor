package com.yihuacomputer.fish.report.service;

import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.report.engine.IPdfReportService;

/**
 * 
 * @author xuxiang
 *
 */
@Service
public class PdfReportService implements IPdfReportService {

	@Override
	public void sendPdf(String pdfName) {

	}

	protected String secondToDay(long second) {
		double dayCount = (double) second / (60 * 60 * 24);
		String day = new java.text.DecimalFormat("0.00").format(dayCount);
		int dayIn = (int) Double.parseDouble(day);

		long bala = second - dayIn * 60 * 60 * 24;

		double hourCount = (double) bala / (60 * 60);
		String hour = new java.text.DecimalFormat("0.00").format(hourCount);
		int hourIn = (int) Double.parseDouble(hour);

		long balaM = second - dayIn * 60 * 60 * 24 - hourIn * 60 * 60;

		double minCount = (double) balaM / (60);
		String min = new java.text.DecimalFormat("0.00").format(minCount);
		int minIn = (int) Double.parseDouble(min);

		long balaS = second - dayIn * 60 * 60 * 24 - hourIn * 60 * 60 - minIn * 60;
		String time = dayIn + "天" + hourIn + "时" + minIn + "分" + balaS + "秒";
		return time;
	}

	protected String pieTimeToStr(int dura) {
		String str = "";
		if (dura == 1) {
			str = "半小时内";
		} else if (dura == 2) {
			str = "半小时到2小时";
		} else if (dura == 3) {
			str = "2小时到4小时";
		} else if (dura == 4) {
			str = "4小时到1天";
		} else if (dura == 5) {
			str = "1天到3天";
		} else {
			str = "3天以上";
		}
		return str;
	}

}
