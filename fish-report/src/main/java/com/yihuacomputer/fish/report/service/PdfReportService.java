package com.yihuacomputer.fish.report.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.report.engine.IPdfReportService;
import com.yihuacomputer.fish.report.engine.pdf.Pdf;

/**
 * 
 * @author xuxiang
 *
 */
@Service
public class PdfReportService implements IPdfReportService{

	@Override
	public String generateWeekPDF(int weekOfYear) {
		File file = new File("");
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			generateDevice(pdf);
			generateOpenRate(pdf);
			generateTrans(pdf);
			generateRetainCard(pdf);
			generateFault(pdf);
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file.getAbsolutePath();
	}

	private void generateFault(Pdf pdf) {
		
	}

	private void generateRetainCard(Pdf pdf) {
		
	}

	private void generateTrans(Pdf pdf) {
		
	}

	private void generateOpenRate(Pdf pdf) {
		
	}

	private void generateDevice(Pdf pdf) {
		
	}

	@Override
	public void sendPdf(String pdfName) {
	}

	@Override
	public String generateMonthPDF(int month) {
		return null;
	}

}
