package com.yihuacomputer.fish.report.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;
import com.yihuacomputer.fish.api.report.engine.IWeekPdfReportService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeWeek;
import com.yihuacomputer.fish.report.engine.pdf.FontMgr;
import com.yihuacomputer.fish.report.engine.pdf.ParagraphMgr;
import com.yihuacomputer.fish.report.engine.pdf.Pdf;
import com.yihuacomputer.fish.report.engine.pdf.PdfChart;
import com.yihuacomputer.fish.report.engine.pdf.PdfConfig;

/**
 * 
 * @author xuxiang
 *
 */
@Service
public class WeekPdfReportService extends PdfReportService implements IWeekPdfReportService {

	private Logger logger = LoggerFactory.getLogger(WeekPdfReportService.class);
			
	@Autowired
	private IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	@Autowired
	private IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	@Autowired
	private IAvgOpenRateEtlService avgOpenRateEtlService;
	@Autowired
	private ITransTypeEtlService transTypeEtlService;
	@Autowired
	private IRetainCardEtlService retainCardEtlService;
	@Autowired
	private IFaultEtlService faultEtlService;
	@Autowired
	private IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	@Autowired
	private IOrgOpenRateEtlService orgOpenRateEtlService;
	@Autowired
	private IDeviceOpenRateEtlService deviceOpenRateEtlService;
	@Autowired
	private ITransTypeService transTypeService;
	
	private int chartWidth = (int) (PageSize.A4.getWidth() * 0.8);
	private int devChartWidth = (int) (PageSize.A4.getWidth() * 0.92);
	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public String generateWeekPDF(int weekOfYear) {
		File file = PdfConfig.getWeekReportFile(weekOfYear);
		String fileName = file.getName();
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			pdf.addLogo(PdfConfig.getLogo());
			pdf.addTitle(PdfConfig.getTitle());
			pdf.addTitle(PdfConfig.getSubTile(fileName));
			pdf.addEmptyLine(2);
			generateDevice(pdf, weekOfYear);
			generateOpenRate(pdf, weekOfYear);
			generateTrans(pdf, weekOfYear);
			generateRetainCard(pdf, weekOfYear);
			generateFault(pdf, weekOfYear);
			pdf.close();
		} catch (Exception e) {
			logger.error(String.format("Exception is [%s]", e.getMessage()));
		}

		return file.getAbsolutePath();
	}

	private void generateFault(Pdf pdf, int weekOfYear) throws Exception {
		pdf.addL1Chapter("五、设备故障数据汇总");
		IFaultWeek fault = faultEtlService.getWeek(weekOfYear);
		long amountFault = 0l;
		long closeCount = 0l;
		long openCount = 0l;
		if (fault != null) {
			openCount = fault.getOpenCount();
			closeCount = fault.getCloseCount();
			amountFault = fault.getOpenCount() + fault.getCloseCount();
		}
		pdf.addParagraph("1.上周共产生故障数" + amountFault + "个，其中已关闭" + closeCount + "个，未关闭" + openCount + "个。");
		pdf.addParagraph("2.上周按照故障类型统计故障数量");

		pdf.addChart(PdfChart.generateBarChart(createBarFault(weekOfYear)), devChartWidth, 260);

		pdf.addParagraph("3.上周故障关闭时间的分布图");
		JFreeChart faultChart = PdfChart.generatePieChart(createPieFault(weekOfYear));
		pdf.addChart(faultChart, devChartWidth, 250);
	}

	private void generateRetainCard(Pdf pdf, int weekOfYear) throws Exception {
		pdf.addL1Chapter("四、吞卡数据汇总");
		Long[] retain = retainCardEtlService.getWeekTotal(Long.valueOf(weekOfYear));
		String retainStr = "";
		if (retain[1]- retain[2] > 0) {
			retainStr = retainStr + "增加" + (retain[1]- retain[2]);
		} else {
			retainStr = retainStr + "减少" + (retain[2]- retain[1]);
		}
		pdf.addParagraph("1.上周共产生吞卡" + retain[1] + "次，平均" + (df.format((double)retain[1] / 7)) + "次/日。相比较上周" + retainStr + "次吞卡。");
		pdf.addParagraph("2.上周各型号设备吞卡次数统计表");
		PdfPTable table = pdf.addTableHeader(4, 84, new float[] { 19, 19, 19, 27 }, new String[] { "设备型号", "设备数量(台)", "吞卡数量(次)", "上周吞卡数量(次)" });
		List<IRetainCardWeek> listRetain = retainCardEtlService.getWeek(Long.valueOf(weekOfYear));
		for (IRetainCardWeek ir : listRetain) {
			pdf.addTableCell(table, ir.getDevType(),false);
			pdf.addTableCell(table, String.valueOf(ir.getDeviceCount()),false);
			pdf.addTableCell(table, String.valueOf(ir.getRetainCount()),false);
			pdf.addTableCell(table, String.valueOf(ir.getLastRetainCount()),true);
		}
		pdf.getDocument().add(table);
	}

	private void generateTrans(Pdf pdf, int weekOfYear) throws Exception {
		pdf.addL1Chapter("三、交易数据汇总");
		Long[] trans = transTypeEtlService.getWeekTotal(Long.valueOf(weekOfYear));
		Long[] lastTrans = transTypeEtlService.getWeekTotal(Long.valueOf(weekOfYear - 1l));
		long remains = trans[0] - lastTrans[0];
		String tranStr = "";
		if (remains > 0) {
			tranStr += "增加" + remains + "次。";
		} else {
			tranStr += "减少" + (lastTrans[0] - trans[0]) + "次。";
		}
		pdf.addParagraph("1.上周共产生交易" + trans[0] + "次，平均" + (df.format((double)trans[0] / 7)) + "次/日。相比较上周交易次数" + tranStr);

		pdf.addParagraph("2.上周按照交易类型统计的交易次数和交易金额");
		PdfPTable table = pdf.addTableHeader(3, 83, new float[] { 27, 27, 27 }, new String[] { "交易类型", "交易数量(次)", "交易金额(元)" });
		IFilter filter = new Filter();
		List<ITransType> transType = transTypeService.list(filter);
		List<ITransTypeWeek> transList = transTypeEtlService.getWeek(weekOfYear);
		for (ITransTypeWeek it : transList) {
			for(ITransType itt:transType){
				if(itt.getTransCode().equals(it.getTransCode())){
					pdf.addTableCell(table, itt.getCodeDesc(),false);
					pdf.addTableCell(table, String.valueOf(it.getTransCount()),false);
					pdf.addTableCell(table, String.valueOf(it.getTransAmount()),true);
				}
			}
		}
		pdf.getDocument().add(table);
	}

	private void generateOpenRate(Pdf pdf, int weekOfYear) throws Exception {
		pdf.addL1Chapter("二、开机率汇总");
		Object[] obj = avgOpenRateEtlService.getWeekTotal(weekOfYear);
		String avgRate = String.valueOf(obj[2]);
		pdf.addParagraph("1. 上周所有设备平均开机率为" + NumUtils.format(Double.parseDouble(avgRate)) + "%");
		pdf.addParagraph("2.上周每日的开机率趋势");
		DefaultCategoryDataset openRateDataset2 = createDatasetRate(weekOfYear);
		pdf.addChart(PdfChart.generateLineChart(openRateDataset2,"","百分比"), chartWidth, 260);

		pdf.addParagraph("3.上周所有设备型号的开机率，从高到低排列");
		List<IDeviceTypeOpenRateWeek> typeRate = deviceTypeOpenRateEtlService.getDeviceTypeWeek(Long.valueOf(weekOfYear));

		PdfPTable table = pdf.addTableHeader(4, 88, new float[] { 16, 24, 24, 20 }, new String[] { "设备型号", "应开机时长", "实际开机时长", "开机率(%)" });
		for (IDeviceTypeOpenRateWeek idt : typeRate) {
			pdf.addTableCell(table, idt.getDevType(),false);
			pdf.addTableCell(table, secondToDay(idt.getOpenTimes()),false);
			pdf.addTableCell(table, secondToDay(idt.getHealthyTimeReal()),false);
			pdf.addTableCell(table, NumUtils.format(idt.getOpenRate()),true);
		}
		pdf.getDocument().add(table);
		pdf.addParagraph("4.上周各网点开机率汇总");
		Paragraph paragraph1 = new Paragraph("较好开机率的网点前10为",FontMgr.getFont12());
		paragraph1.setFirstLineIndent(200);//首行缩进
		pdf.getDocument().add(paragraph1);
		PdfPTable orgTableTop = pdf.addTableHeader(4, 88, new float[] { 12, 26, 26, 21 }, new String[] { "网点名称", "设备应开机时长", "设备实际开机时长", "开机率(%)" });

		List<IOrgOpenRateWeek> orgTop = orgOpenRateEtlService.getTopOrgWeek(weekOfYear, 10);
		for (IOrgOpenRateWeek ior : orgTop) {
			pdf.addTableCell(orgTableTop, ior.getOrgName(),false);
			pdf.addTableCell(orgTableTop, secondToDay(ior.getOpenTimes()),false);
			pdf.addTableCell(orgTableTop, secondToDay(ior.getHealthyTimeReal()),false);
			pdf.addTableCell(orgTableTop, NumUtils.format(ior.getOpenRate()),true);
		}
		pdf.getDocument().add(orgTableTop);
		Paragraph paragraph2 = new Paragraph("较差开机率的网点前10为",FontMgr.getFont12());
		paragraph2.setFirstLineIndent(200);//首行缩进
		pdf.getDocument().add(paragraph2);
		PdfPTable orgTableLast = pdf.addTableHeader(4, 88, new float[] { 12, 26, 26, 21 }, new String[] { "网点名称", "设备应开机时长", "设备实际开机时长", "开机率(%)" });

		List<IOrgOpenRateWeek> orgLast = orgOpenRateEtlService.getLastOrgWeek(weekOfYear, 10);
		for (IOrgOpenRateWeek ior : orgLast) {
			pdf.addTableCell(orgTableLast, ior.getOrgName(),false);
			pdf.addTableCell(orgTableLast, secondToDay(ior.getOpenTimes()),false);
			pdf.addTableCell(orgTableLast, secondToDay(ior.getHealthyTimeReal()),false);
			pdf.addTableCell(orgTableLast, NumUtils.format(ior.getOpenRate()),true);
		}
		pdf.getDocument().add(orgTableLast);

		pdf.addParagraph("5.上周所有设备开机率汇总");
		Paragraph paragraph3 = new Paragraph("较好开机率的设备前10为",FontMgr.getFont12());
		paragraph3.setFirstLineIndent(200);//首行缩进
		pdf.getDocument().add(paragraph3);
		PdfPTable devTableTop = pdf.addTableHeader(5, 90, new float[] { 16, 16, 22, 22, 14 }, new String[] { "设备编号", "机构名称", "应开机时长", "实际开机时长", "开机率(%)" });
		List<IDeviceOpenRateWeek> devListTop = deviceOpenRateEtlService.getTopDeviceWeek(weekOfYear, 10);
		for (IDeviceOpenRateWeek ido : devListTop) {
			pdf.addTableCell(devTableTop, ido.getTerminalId(),false);
			pdf.addTableCell(devTableTop, ido.getOrgName(),false);
			pdf.addTableCell(devTableTop, String.valueOf(secondToDay(ido.getOpenTimes())),false);
			pdf.addTableCell(devTableTop, String.valueOf(secondToDay(ido.getHealthyTimeReal())),false);
			pdf.addTableCell(devTableTop, NumUtils.format(ido.getOpenRate()),true);
		}
		pdf.getDocument().add(devTableTop);

		Paragraph paragraph4 = new Paragraph("较差开机率的设备前10为",FontMgr.getFont12());
		paragraph4.setFirstLineIndent(200);//首行缩进
		pdf.getDocument().add(paragraph4);
		PdfPTable devTableLast = pdf.addTableHeader(5, 90, new float[] { 16, 16, 22, 22, 14 }, new String[] { "设备编号", "机构名称", "应开机时长", "实际开机时长", "开机率(%)" });

		List<IDeviceOpenRateWeek> devListLast = deviceOpenRateEtlService.getLastDeviceWeek(weekOfYear, 10);
		for (IDeviceOpenRateWeek ido : devListLast) {
			pdf.addTableCell(devTableLast, ido.getTerminalId(),false);
			pdf.addTableCell(devTableLast, ido.getOrgName(),false);
			pdf.addTableCell(devTableLast, String.valueOf(secondToDay(ido.getOpenTimes())),false);
			pdf.addTableCell(devTableLast, String.valueOf(secondToDay(ido.getHealthyTimeReal())),false);
			pdf.addTableCell(devTableLast,NumUtils.format(ido.getOpenRate()),true);
		}
		pdf.getDocument().add(devTableLast);
	}

	private void generateDevice(Pdf pdf, int weekOfYear) throws Exception {
		pdf.addL1Chapter("一、设备信息汇总");
		ParagraphMgr mgr = ParagraphMgr.getInstance();
		IFilter filter = new Filter();
		filter.eq("date", String.valueOf(weekOfYear));
		filter.order("date");
		List<IDeviceCatalogSummaryWeek> list1 = deviceCatalogSummaryWeekService.list(filter);
		int devNum = 0;
		int devAddNum = 0;
		int devScraNum = 0;
		for (IDeviceCatalogSummaryWeek device : list1) {
			devNum += device.getNum();
			devAddNum += device.getAddDevNum();
			devScraNum += device.getScrappedDevNum();
		}

		String[] dates = PdfConfig.getDayByWeek(weekOfYear);
		String nowDate = dates[0] + "年" + dates[2].substring(0, 2) + "月" + dates[2].substring(3) + "日";
		mgr.addChunk("1. 截止" + nowDate + "，共有设备" + devNum + "台，其中", FontMgr.getFont12());
		for (IDeviceCatalogSummaryWeek device : list1) {
			mgr.addChunk(device.getCatalog(),FontMgr.getFont12()).addChunk("有" + device.getNum() + "台，", FontMgr.getFont12());
		}
		mgr.addChunk("上周新增设备" + devAddNum + "台，报废设备" + devScraNum + "台。", FontMgr.getFont12());
		pdf.addParagraph(mgr);
		pdf.addParagraph("2.设备型号统计");

		PdfPTable table = pdf.addTableHeader(4, 88, new float[] { 22, 22, 22, 22}, new String[] { "设备型号", "设备数量(台)",  "新增设备数量(台)", "报废设备数量(台)" });

		List<IDeviceTypeSummaryWeek> list2 = deviceTypeSummaryWeekService.list(filter);
		for (IDeviceTypeSummaryWeek dt : list2) {
			pdf.addTableCell(table, dt.getDevType(),false);
			pdf.addTableCell(table, String.valueOf(dt.getNum()),false);
			pdf.addTableCell(table, String.valueOf(dt.getAddDevNum()),false);
			pdf.addTableCell(table, String.valueOf(dt.getScrappedDevNum()),true);
		}
		pdf.getDocument().add(table);

		pdf.addEmptyLine(1);
		pdf.addParagraph("3.最近4周新增设备和报废设备的趋势图");

		DefaultCategoryDataset dataset1 = createDatasetFW(weekOfYear);
		JFreeChart devChart = PdfChart.generateLineChart(dataset1,"","设备数量(台)");
		pdf.addChart(devChart, chartWidth, 260);
	}

	public DefaultCategoryDataset createDatasetFW(int weekOfYear) {
		List<IDeviceCatalogSummaryWeek> list = deviceCatalogSummaryWeekService.getAddAndScrp(weekOfYear,weekOfYear-4);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (IDeviceCatalogSummaryWeek idc : list) {
			String week = idc.getDate().substring(4) + "周";
			dataset.setValue(idc.getAddDevNum(), "新增设备", week);
			dataset.setValue(idc.getScrappedDevNum(), "报废设备", week);
		}
		return dataset;

	}

	private DefaultCategoryDataset createDatasetRate(int weekOfYear) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		String[] dates = PdfConfig.getDayByWeek(weekOfYear);
		String firstDay = dates[0]+dates[1].substring(0, 2)+dates[1].substring(3);
		String endDay = dates[0]+dates[2].substring(0, 2)+dates[2].substring(3);
		List<IAvgDayOpenRate> rates = avgOpenRateEtlService.getAvgDays(Long.parseLong(firstDay), Long.parseLong(endDay));
		String day = "01";
		for (IAvgDayOpenRate ia : rates) {
			day = String.valueOf(ia.getDate()).substring(6) + "日";
			dataSet.setValue(Double.parseDouble(NumUtils.format(ia.getOpenRate())), "开机率", day);
		}
		return dataSet;
	}

	private DefaultCategoryDataset createBarFault(int weekOfYear) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IFaultClassifyWeek> listFault = faultEtlService.getClassifyWeek(Long.valueOf(weekOfYear));
		for (IFaultClassifyWeek ifc : listFault) {
			dataSet.setValue(ifc.getCount(), "Fault", ifc.getClassifyName());
		}
		return dataSet;
	}

	private DefaultPieDataset createPieFault(int weekOfYear) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<IFaultDurationWeek> listFaultDura = faultEtlService.getDurationWeek(Long.valueOf(weekOfYear));
		for (IFaultDurationWeek ifd : listFaultDura) {
			if(ifd.getCount() !=0l){
				dataSet.setValue(pieTimeToStr(ifd.getDuration()) , ifd.getCount());
			}
		}

		return dataSet;
	}

}
