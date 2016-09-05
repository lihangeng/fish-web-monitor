package com.yihuacomputer.fish.report.service;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardMonth;
import com.yihuacomputer.fish.api.report.engine.IMonthPdfReportService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateMonth;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeMonth;
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
public class MonthPdfReportService extends PdfReportService implements IMonthPdfReportService {

	@Autowired
	private IDeviceCatalogSummaryMonthService deviceCatalogSummaryMonthService;
	@Autowired
	private IDeviceTypeSummaryMonthService deviceTypeSummaryMonthService;
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
	private int chartWidth = (int) (PageSize.A4.getWidth() * 0.8);
	private int devChartWidth = (int) (PageSize.A4.getWidth() * 0.9);

	@Override
	public String generateMonthPDF(int month) {
		File file = PdfConfig.getMonthReportFile(month);
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			pdf.addLogo(PdfConfig.getLogo());
			pdf.addTitle(PdfConfig.getTitle());
			pdf.addTitle(PdfConfig.getSubTile(file.getName()));
			pdf.addEmptyLine(2);
			generateDeviceMonth(pdf, month);
			generateOpenRateMonth(pdf, month);
			generateTransMonth(pdf, month);
			generateRetainCardMonth(pdf, month);
			generateFaultMonth(pdf, month);
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file.getAbsolutePath();
	}


	private void generateFaultMonth(Pdf pdf, int month) throws Exception {
		pdf.addL1Chapter("五、设备故障数据汇总");
		IFaultMonth fault = faultEtlService.getMonth(month);
		long amountFault = 0l;
		long closeCount = 0l;
		long openCount = 0l;
		if (fault != null) {
			openCount = fault.getOpenCount();
			closeCount = fault.getCloseCount();
			amountFault = fault.getOpenCount() + fault.getCloseCount();
		}
		pdf.addParagraph("1.上月共产生故障数" + amountFault + "个，其中已关闭" + openCount + "个，未关闭" + closeCount + "个。");
		pdf.addParagraph("2.上月按照故障类型统计故障数量");

		pdf.addChart(PdfChart.generateBarChart(createBarFaultMonth(month)), devChartWidth, 260);

		pdf.addParagraph("3.上月故障关闭时间的分布图");
		JFreeChart faultChart = PdfChart.generatePieChart(createPieFaultMonth(month));
		pdf.addChart(faultChart, devChartWidth, 250);
	}

	private void generateRetainCardMonth(Pdf pdf, int month) throws Exception {
		pdf.addL1Chapter("四、吞卡数据汇总");
		Long[] retain = retainCardEtlService.getMonthTotal(Long.valueOf(month));
		String retainStr = "";
		if (retain[2] > 0) {
			retainStr = retainStr + "增加" + retain[2];
		} else {
			retainStr = retainStr + "减少" + retain[2];
		}
		pdf.addParagraph("1.上月共产生吞卡" + retain[1] + "次，平均" + retain[1] / 30 + "次/日（/台）。相比较上月" + retainStr + "次吞卡。");
		pdf.addParagraph("2.上月各型号设备吞卡次数统计表");
		PdfPTable table = pdf.addTableHeader(4, 80, new float[] { 18, 18, 18, 26 }, new String[] { "设备型号", "设备数量", "吞卡数量", "上月吞卡数量" });
		List<IRetainCardMonth> listRetain = retainCardEtlService.getMonth(Long.valueOf(month));
		for (IRetainCardMonth ir : listRetain) {
			pdf.addTableCell(table, ir.getDevType());
			pdf.addTableCell(table, String.valueOf(ir.getDeviceCount()));
			pdf.addTableCell(table, String.valueOf(ir.getRetainCount()));
			pdf.addTableCell(table, String.valueOf(ir.getLastRetainCount()));
		}
		pdf.getDocument().add(table);
	}

	private void generateTransMonth(Pdf pdf, int month) throws Exception {
		pdf.addL1Chapter("三、交易数据汇总");
		Long[] trans = transTypeEtlService.getMonthTotal(Long.valueOf(month));
		Long[] lastTrans = transTypeEtlService.getMonthTotal(Long.valueOf(month - 1));
		long remains = trans[1] - lastTrans[1];
		String tranStr = "";
		if (remains > 0) {
			tranStr += "增加" + remains + "次。";
		} else {
			tranStr += "减少" + (lastTrans[1] - trans[1]) + "次。";
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.valueOf(String.valueOf(month).substring(0, 4)));
		int mon = 0;
		if (String.valueOf(month).substring(4, 5).equals("0")) {
			mon = Integer.valueOf(String.valueOf(month).substring(5));
		} else {
			mon = Integer.valueOf(String.valueOf(month).substring(4));
		}
		c.set(Calendar.MONTH, mon - 1); // 6 月
		pdf.addParagraph("1.上月共产生交易" + trans[1] + "次，平均" + (trans[1] / c.getActualMaximum(Calendar.DAY_OF_MONTH)) + "次/日。相比较上月交易次数" + tranStr);

		pdf.addParagraph("2.上月按照交易类型统计的交易次数和交易金额");
		PdfPTable transTable = pdf.addTableHeader(3, 75, new float[] { 25, 25, 25 }, new String[] { "交易类型", "交易数量", "交易金额" });

		List<ITransTypeMonth> transList = transTypeEtlService.getMonth(month);
		for (ITransTypeMonth it : transList) {
			pdf.addTableCell(transTable, it.getTransCode());
			pdf.addTableCell(transTable, String.valueOf(it.getTransCount()));
			pdf.addTableCell(transTable, String.valueOf(it.getTransAmount()));
		}
		pdf.getDocument().add(transTable);
	}

	private void generateOpenRateMonth(Pdf pdf, int month) throws Exception {
		pdf.addL1Chapter("二、开机率汇总");
		Object[] obj = avgOpenRateEtlService.getMonthTotal(month);
		String avgRate = String.valueOf(obj[2]);
		pdf.addParagraph("1. 上月所有设备平均开机率为" + avgRate + "%");
		pdf.addParagraph("2.上月每日的开机率趋势");
		DefaultCategoryDataset openRateDataset2 = createDatasetRateMonth(month);
		pdf.addChart(PdfChart.generateLineChart(openRateDataset2), devChartWidth, 260);

		pdf.addParagraph("3.上月所有设备型号的开机率，从高到低排列");
		List<IDeviceTypeOpenRateMonth> typeRate = deviceTypeOpenRateEtlService.getDeviceTypeMonth(Long.valueOf(month));

		PdfPTable typeTable = pdf.addTableHeader(4, 88, new float[] { 16, 24, 24, 20 }, new String[] { "设备型号", "应开机时长", "实际开机时长", "开机率(%)" });

		for (IDeviceTypeOpenRateMonth idt : typeRate) {
			pdf.addTableCell(typeTable, idt.getDevType());
			pdf.addTableCell(typeTable, String.valueOf(secondToDay(idt.getOpenTimes())));
			pdf.addTableCell(typeTable, String.valueOf(secondToDay(idt.getHealthyTimeReal())));
			pdf.addTableCell(typeTable, NumUtils.format(idt.getOpenRate()));
		}
		pdf.getDocument().add(typeTable);
		pdf.addParagraph("4.上月各网点统计的开机率汇总。");
		pdf.addParagraph("4.1 上月各网点统计的开机率，较好开机率前10为：");
		PdfPTable orgTableTop = pdf.addTableHeader(4, 88, new float[] { 12, 26, 26, 21 }, new String[] { "网点名称", "应开机时长", "实际开机时长", "开机率(%)" });

		List<IOrgOpenRateMonth> orgTop = orgOpenRateEtlService.getTopOrgMonth(month, 10);
		for (IOrgOpenRateMonth ior : orgTop) {
			pdf.addTableCell(orgTableTop, ior.getOrgName());
			pdf.addTableCell(orgTableTop, String.valueOf(secondToDay(ior.getOpenTimes())));
			pdf.addTableCell(orgTableTop, String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			pdf.addTableCell(orgTableTop, NumUtils.format(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableTop);

		pdf.addParagraph("4.2 上月各网点统计的开机率，较差的开机率前10为：");
		PdfPTable orgTableLast = pdf.addTableHeader(4, 88, new float[] { 12, 26, 26, 21 }, new String[] { "网点名称", "应开机时长", "实际开机时长", "开机率(%)" });

		List<IOrgOpenRateMonth> orgLast = orgOpenRateEtlService.getLastOrgMonth(month, 10);
		for (IOrgOpenRateMonth ior : orgLast) {
			pdf.addTableCell(orgTableLast, ior.getOrgName());
			pdf.addTableCell(orgTableLast, String.valueOf(secondToDay(ior.getOpenTimes())));
			pdf.addTableCell(orgTableLast, String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			pdf.addTableCell(orgTableLast, NumUtils.format(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableLast);

		pdf.addParagraph("5.上月所有设备开机率汇总。");
		pdf.addParagraph("5.1 上月设备统计的开机率，较好开机率设备的前10为：");
		PdfPTable devTableTop = pdf.addTableHeader(5, 88, new float[] { 16, 16, 22, 22, 12 }, new String[] { "设备编号", "机构名称", "应开机时长", "实际开机时长", "开机率(%)" });

		List<IDeviceOpenRateMonth> devListTop = deviceOpenRateEtlService.getTopDeviceMonth(month, 10);
		for (IDeviceOpenRateMonth ido : devListTop) {
			pdf.addTableCell(devTableTop, ido.getTerminalId());
			pdf.addTableCell(devTableTop, ido.getOrgName());
			pdf.addTableCell(devTableTop, String.valueOf(secondToDay(ido.getOpenTimes())));
			pdf.addTableCell(devTableTop, String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			pdf.addTableCell(devTableTop, NumUtils.format(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableTop);

		pdf.addParagraph("5.2 上月设备统计的开机率，较差开机率设备的前10为：");
		PdfPTable devTableLast = pdf.addTableHeader(5, 88, new float[] { 16, 16, 22, 22, 12 }, new String[] { "设备编号", "机构名称", "应开机时长", "实际开机时长", "开机率(%)" });

		List<IDeviceOpenRateMonth> devListLast = deviceOpenRateEtlService.getLastDeviceMonth(month, 10);
		for (IDeviceOpenRateMonth ido : devListLast) {
			pdf.addTableCell(devTableLast, ido.getTerminalId());
			pdf.addTableCell(devTableLast, ido.getOrgName());
			pdf.addTableCell(devTableLast, String.valueOf(secondToDay(ido.getOpenTimes())));
			pdf.addTableCell(devTableLast, String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			pdf.addTableCell(devTableLast, String.valueOf(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableLast);
	}

	private void generateDeviceMonth(Pdf pdf, int month) throws Exception {
		pdf.addL1Chapter("一、设备信息汇总");
		ParagraphMgr mgr = ParagraphMgr.getInstance();
		IFilter filter = new Filter();
		filter.eq("date", String.valueOf(month));
		List<IDeviceCatalogSummaryMonth> list1 = deviceCatalogSummaryMonthService.list(filter);
		int devNum = 0;
		int devAddNum = 0;
		int devScraNum = 0;
		for (IDeviceCatalogSummaryMonth device : list1) {
			devNum += device.getNum();
			devAddNum += device.getAddDevNum();
			devScraNum += device.getScrappedDevNum();
		}

		String date1 = DateUtils.getLastDate();

		mgr.addChunk("1. 截止" + date1 + "，共有设备" + devNum + "台，其中", FontMgr.getFont14());
		for (IDeviceCatalogSummaryMonth device : list1) {
			mgr.addChunk(device.getCatalog()).addChunk("有" + device.getNum() + "台，", FontMgr.getFont14());
		}
		mgr.addChunk("上月新增设备" + devAddNum + "台，报废设备" + devScraNum + "台。", FontMgr.getFont14());
		pdf.addParagraph(mgr);
		pdf.addParagraph("2.设备型号统计");

		PdfPTable table = pdf.addTableHeader(5, 85, new float[] { 16, 16, 16, 22, 22 }, new String[] { "设备型号", "设备数量", "月份", "新增设备数量", "报废设备数量" });
		List<IDeviceTypeSummaryMonth> list2 = deviceTypeSummaryMonthService.list(filter);
		for (IDeviceTypeSummaryMonth dt : list2) {
			pdf.addTableCell(table, dt.getDevType());
			pdf.addTableCell(table, String.valueOf(dt.getNum()));
			pdf.addTableCell(table, dt.getDate());
			pdf.addTableCell(table, String.valueOf(dt.getAddDevNum()));
			pdf.addTableCell(table, String.valueOf(dt.getScrappedDevNum()));
		}
		pdf.getDocument().add(table);

		pdf.addEmptyLine(1);
		pdf.addParagraph("3.最近3个月新增设备和报废设备的趋势图");

		DefaultCategoryDataset dataset1 = createDatasetMonth(month);
		JFreeChart devChart = PdfChart.generateLineChart(dataset1);
		pdf.addChart(devChart, chartWidth, 260);
	}

	public DefaultCategoryDataset createDatasetMonth(int month) {
		IFilter filter = new Filter();
		filter.ge("date", String.valueOf(month - 2));
		filter.le("date", String.valueOf(month));
		filter.order("date");
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IDeviceCatalogSummaryMonth> list1 = deviceCatalogSummaryMonthService.list(filter);
		String mon = "01";
		for (IDeviceCatalogSummaryMonth idc : list1) {
			mon = idc.getDate().substring(4) + "月";
			dataSet.setValue(idc.getAddDevNum(), "新增设备", mon);
			dataSet.setValue(idc.getScrappedDevNum(), "报废设备", mon);
		}
		return dataSet;
	}

	private DefaultCategoryDataset createDatasetRateMonth(int month) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		long start = month * 100 + 1;
		long end = month * 100 + 32;
		List<IAvgDayOpenRate> rates = avgOpenRateEtlService.getAvgDays(start, end);
		String day = "01";
		for (IAvgDayOpenRate ia : rates) {
			day = String.valueOf(ia.getDate()).substring(6) + "日";
			dataSet.setValue(ia.getOpenRate(), "开机率", day);
		}
		return dataSet;
	}

	private DefaultCategoryDataset createBarFaultMonth(int month) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IFaultClassifyMonth> listFault = faultEtlService.getClassifyMonth(Long.valueOf(month));
		for (IFaultClassifyMonth ifc : listFault) {
			dataSet.setValue(ifc.getCount(), "Fault", ifc.getClassifyName());
		}
		return dataSet;
	}

	private DefaultPieDataset createPieFaultMonth(int month) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<IFaultDurationMonth> listFaultDura = faultEtlService.getDurationMonth(Long.valueOf(month));
		for (IFaultDurationMonth ifd : listFaultDura) {
			dataSet.setValue(pieTimeToStr(ifd.getDuration()) + "故障数: " + ifd.getCount(), ifd.getCount());
		}

		return dataSet;
	}

}
