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
		long dayCount =  second / (60 * 60 * 24);

		long bala = second - dayCount * 60 * 60 * 24;

		long hourCount = bala / (60 * 60);

		long balaM = second - dayCount * 60 * 60 * 24 - hourCount * 60 * 60;

		long minCount = balaM / (60);

		long balaS = second - dayCount * 60 * 60 * 24 - hourCount * 60 * 60 - minCount * 60;
		String time = dayCount + "天" + hourCount + "时" + minCount + "分" + balaS + "秒";
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
