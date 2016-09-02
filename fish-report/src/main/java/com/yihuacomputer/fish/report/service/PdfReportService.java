package com.yihuacomputer.fish.report.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;
import com.yihuacomputer.fish.api.report.engine.IPdfReportService;
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
import com.yihuacomputer.fish.report.pdf.PdfTest;

/**
 * 
 * @author xuxiang
 *
 */
@Service
public class PdfReportService implements IPdfReportService{

	@Autowired
	IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	@Autowired
	IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	@Autowired
	IAvgOpenRateEtlService avgOpenRateEtlService;
	@Autowired
	ITransTypeEtlService transTypeEtlService;
	@Autowired
	IRetainCardEtlService retainCardEtlService;
	@Autowired
	IFaultEtlService faultEtlService;
	@Autowired
	IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	@Autowired
	IOrgOpenRateEtlService orgOpenRateEtlService;
	@Autowired
	IDeviceOpenRateEtlService deviceOpenRateEtlService;
	int chartWidth = (int)(PageSize.A4.getWidth() * 0.8);
	int devChartWidth = (int)(PageSize.A4.getWidth() * 0.9);
	@Override
	public String generateWeekPDF(int weekOfYear) {
		File file = new File("D:/自助设备运行分析报告_2016年第36周(8.29-9.4).pdf");
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			pdf.addTitle("中国农业银行");
			pdf.addSubTitle("自助设备运行分析报告");
			pdf.addTriTitle("2016年第36周(8.29-9.4)");
			pdf.addEmptyLine(2);
			generateDevice(pdf,weekOfYear);
			generateOpenRate(pdf,weekOfYear);
			generateTrans(pdf,weekOfYear);
			generateRetainCard(pdf,weekOfYear);
			generateFault(pdf,weekOfYear);
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file.getAbsolutePath();
	}

	private void generateFault(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("五、设备故障数据汇总");
		IFaultWeek fault = faultEtlService.getWeek(weekOfYear);
		long amountFault = 0l;
		long closeCount =0l;
		long openCount =0l;
		if(fault !=null){
			openCount = fault.getOpenCount();
			closeCount=fault.getCloseCount();
			amountFault = fault.getOpenCount()+fault.getCloseCount();
		}
		pdf.addContent("1.上周共产生故障数"+amountFault+"个，其中已关闭"+openCount+"个，未关闭"+closeCount+"个。");
		pdf.addContent("2.上周按照故障类型统计故障数量");
		
		pdf.addChart(PdfTest.generateBarChart(createBarFault(weekOfYear)), chartWidth, 260);
		
		pdf.addContent("3.上周故障关闭时间的分布图");
		JFreeChart faultChart = PdfTest.generatePieChart(createPieFault(weekOfYear));
		pdf.addChart(faultChart, chartWidth, 250);
	}

	private void generateRetainCard(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("四、吞卡数据汇总");
		Long[] retain = retainCardEtlService.getWeekTotal(weekOfYear);
		String retainStr = "";
		if(retain[2]>0){
			retainStr=retainStr+"增加"+retain[2];
		}else{
			retainStr=retainStr+"减少"+retain[2];
		}
		pdf.addContent("1.上周共产生吞卡"+retain[1]+"次，平均"+retain[1]/7+"次/日（/台）。相比较上周"+retainStr+"次吞卡。");
		pdf.addContent("2.上周各型号设备吞卡次数统计表");
		PdfPTable retainTable = new PdfPTable(4);
		PdfPCell retainCell = new PdfPCell(new Phrase("设备类型",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);  
		retainCell = new PdfPCell(new Phrase("设备数量数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell); 
		retainCell = new PdfPCell(new Phrase("吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		retainCell = new PdfPCell(new Phrase("同比上周吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		List<IRetainCardWeek> listRetain = retainCardEtlService.getWeek(weekOfYear);
		for(IRetainCardWeek ir:listRetain){
			retainTable.addCell(ir.getDevType());
			retainTable.addCell(String.valueOf(ir.getDeviceCount()));
			retainTable.addCell(String.valueOf(ir.getRetainCount()));
			retainTable.addCell(String.valueOf(ir.getLastRetainCount()));
		}
	}

	private void generateTrans(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("三、交易数据汇总");
		Long[] trans = transTypeEtlService.getWeekTotal(weekOfYear);
		Long[] lastTrans = transTypeEtlService.getWeekTotal(weekOfYear-1);
		long remains = trans[1] - lastTrans[1];
		String tranStr ="";
		if(remains>0){
			tranStr+="增加"+remains+"次。";
		}else{
			tranStr+="减少"+ (lastTrans[1] - trans[1])+"次。";
		}
		pdf.addContent("1.上周共产生交易"+trans[1]+"次，平均"+trans[1]+"次/日。相比较上周交易次数"+tranStr);
		
		pdf.addContent("2.上周按照交易类型统计的交易次数和交易金额");
		PdfPTable transTable = new PdfPTable(3);
		PdfPCell transCell = new PdfPCell(new Phrase("交易类型",FontMgr.getFont14()));
		transCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		transTable.addCell(transCell);  
		transCell = new PdfPCell(new Phrase("交易数量",FontMgr.getFont14()));
		transCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		transTable.addCell(transCell); 
		transCell = new PdfPCell(new Phrase("交易金额",FontMgr.getFont14()));
		transCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		transTable.addCell(transCell);
		
		List<ITransTypeWeek> list3 = transTypeEtlService.getWeek(weekOfYear);
		for(ITransTypeWeek it:list3){
			transTable.addCell(it.getTransCode());
			transTable.addCell(String.valueOf(it.getTransCount()));
			transTable.addCell(String.valueOf(it.getTransAmount()));
		}
	}

	private void generateOpenRate(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("二、开机率汇总");
		Object [] obj= avgOpenRateEtlService.getWeekTotal(weekOfYear);
		String avgRate = String.valueOf(obj[2]);
		pdf.addContent("1. 上周所有设备平均开机率为"+avgRate+"%");
		pdf.addContent("2.上周每日的开机率趋势");
		XYDataset dataset2=createDatasetRate(weekOfYear);//TODO ..
		pdf.addChart(PdfTest.generateLineChart(dataset2), chartWidth, 260);
		
		pdf.addContent("3.上周所有设备型号的开机率，从高到低排列");
		List<IDeviceTypeOpenRateWeek> typeRate =deviceTypeOpenRateEtlService.getDeviceTypeWeek(weekOfYear); 
		PdfPTable typeTable = new PdfPTable(4);
		PdfPCell typeCell = new PdfPCell(new Phrase("设备型号名称",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);  
		typeCell = new PdfPCell(new Phrase("设备应开机时长",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell); 
		typeCell = new PdfPCell(new Phrase("设备实际开机时长",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		typeCell = new PdfPCell(new Phrase("开机率",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		for(IDeviceTypeOpenRateWeek idt:typeRate){
			typeTable.addCell(idt.getDevType());
			typeTable.addCell(String.valueOf(idt.getOpenTimes()));
			typeTable.addCell(String.valueOf(idt.getHealthyTimeReal()));
			typeTable.addCell(String.valueOf(idt.getOpenRate()));
		}
		
		pdf.addContent("4.上周各网点统计的开机率，较好网点Top10，较差网点Top10");
		PdfPTable orgTable = new PdfPTable(4);
		PdfPCell orgCell = new PdfPCell(new Phrase("网点名称",FontMgr.getFont14()));
		orgCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTable.addCell(orgCell);  
		orgCell = new PdfPCell(new Phrase("设备应开机时长",FontMgr.getFont14()));
		orgCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTable.addCell(orgCell); 
		orgCell = new PdfPCell(new Phrase("设备实际开机时长",FontMgr.getFont14()));
		orgCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTable.addCell(orgCell);
		orgCell = new PdfPCell(new Phrase("开机率",FontMgr.getFont14()));
		orgCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTable.addCell(orgCell);
		List<IOrgOpenRateWeek> orgTop =orgOpenRateEtlService.getTopOrgWeek(weekOfYear, 10);
		for(IOrgOpenRateWeek ior:orgTop){
			orgTable.addCell(ior.getOrgName());
			orgTable.addCell(String.valueOf(ior.getOpenTimes()));
			orgTable.addCell(String.valueOf(ior.getHealthyTimeReal()));
			orgTable.addCell(String.valueOf(ior.getOpenRate()));
		}
		List<IOrgOpenRateWeek> orgLast =orgOpenRateEtlService.getLastOrgWeek(weekOfYear, 10);
		for(IOrgOpenRateWeek ior:orgLast){
			orgTable.addCell(ior.getOrgName());
			orgTable.addCell(String.valueOf(ior.getOpenTimes()));
			orgTable.addCell(String.valueOf(ior.getHealthyTimeReal()));
			orgTable.addCell(String.valueOf(ior.getOpenRate()));
		}
		
		pdf.addContent("5.上周设备统计的开机率，较好设备Top10，较差设备Top10");
		PdfPTable devTable = new PdfPTable(5);
		PdfPCell devCell = new PdfPCell(new Phrase("设备终端号",FontMgr.getFont14()));
		devCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTable.addCell(devCell);  
		devCell = new PdfPCell(new Phrase("设备所属机构名称",FontMgr.getFont14()));
		devCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTable.addCell(devCell); 
		devCell = new PdfPCell(new Phrase("设备应开机时长",FontMgr.getFont14()));
		devCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTable.addCell(devCell);
		devCell = new PdfPCell(new Phrase("设备实际开机时长",FontMgr.getFont14()));
		devCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTable.addCell(devCell);
		devCell = new PdfPCell(new Phrase("开机率",FontMgr.getFont14()));
		devCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTable.addCell(devCell);
		List<IDeviceOpenRateWeek> devListTop=deviceOpenRateEtlService.getTopDeviceWeek(weekOfYear,10); 
		for(IDeviceOpenRateWeek ido:devListTop){
			devTable.addCell(ido.getTerminalId());
			devTable.addCell(ido.getOrgName());
			devTable.addCell(String.valueOf(ido.getOpenTimes()));
			devTable.addCell(String.valueOf(ido.getHealthyTimeReal()));
			devTable.addCell(String.valueOf(ido.getOpenRate()));
		}
		List<IDeviceOpenRateWeek> devListLast=deviceOpenRateEtlService.getLastDeviceWeek(weekOfYear, 10); 
		for(IDeviceOpenRateWeek ido:devListLast){
			devTable.addCell(ido.getTerminalId());
			devTable.addCell(ido.getOrgName());
			devTable.addCell(String.valueOf(ido.getOpenTimes()));
			devTable.addCell(String.valueOf(ido.getHealthyTimeReal()));
			devTable.addCell(String.valueOf(ido.getOpenRate()));
		}
	}

	private void generateDevice(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("一、设备信息汇总");
		ParagraphMgr mgr = ParagraphMgr.getInstance();
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		List<IDeviceCatalogSummaryWeek> list1 = deviceCatalogSummaryWeekService.list(filter);
		int devNum=0;
		int devAddNum=0;
		int devScraNum=0;
		for(IDeviceCatalogSummaryWeek device :list1){
			devNum+=device.getNum();
			devAddNum+=device.getAddDevNum();
			devScraNum+=device.getScrappedDevNum();
		}
		
		String date1=DateUtils.getLastDate();
		
		mgr.addChunk("1. 截止"+date1+"，共有设备"+devNum+"台，其中",FontMgr.getFont14());
		for(IDeviceCatalogSummaryWeek device :list1){
			mgr.addChunk(device.getCatalog())
				.addChunk("有"+device.getNum()+"台，",FontMgr.getFont14());
		}
		mgr.addChunk("上周新增设备"+devAddNum+"台，报废设备"+devScraNum+"台。",FontMgr.getFont14());
		pdf.addParagraph(mgr);
		pdf.addContent("2.设备型号统计");
		
		PdfPTable table = new PdfPTable(5);
		PdfPCell cell = new PdfPCell(new Phrase("型号名称",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);  
		cell = new PdfPCell(new Phrase("设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		cell = new PdfPCell(new Phrase("日期",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("新增设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("报废设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		
		List<IDeviceTypeSummaryWeek> list2 = deviceTypeSummaryWeekService.list(filter);
		for(IDeviceTypeSummaryWeek dt:list2){
			table.addCell(dt.getDevType());
			table.addCell(String.valueOf(dt.getNum()));
			table.addCell(dt.getDate());
			table.addCell(String.valueOf(dt.getAddDevNum()));
			table.addCell(String.valueOf(dt.getScrappedDevNum()));
		}
		pdf.document.add(table);  
		
		pdf.addEmptyLine(1);
		pdf.addContent("3.最近4周新增设备和报废设备的趋势图");
		
//		XYDataset dataset1= createDataset(weekOfYear);
//		JFreeChart devChart = PdfTest.generateLineChart(dataset1);
//		pdf.addChart(devChart, devChartWidth, 260);
	}

	@Override
	public void sendPdf(String pdfName) {
	}

	@Override
	public String generateMonthPDF(int month) {
		return null;
	}
	
	public  XYDataset createDataset(int weekOfYear) {
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(week);
		IFilter filter = new Filter();//TODO 时间格式不对
		filter.gt("date", dates[0]);
		filter.le("date", dates[1]);
		List<IDeviceCatalogSummaryWeek> list1 = deviceCatalogSummaryWeekService.list(filter);
        final XYSeries series1 = new XYSeries("ATM");
        for(IDeviceCatalogSummaryWeek idc:list1){
        	series1.add(weekOfYear+1, idc.getAddDevNum());
        }

        final XYSeries series2 = new XYSeries("CDS");
//        filter.eq("catalog", "CDS");
		List<IDeviceCatalogSummaryWeek> list2 = deviceCatalogSummaryWeekService.list(filter);
        for(IDeviceCatalogSummaryWeek idc:list2){
        	series1.add(weekOfYear+1, idc.getAddDevNum());
        }

        final XYSeries series3 = new XYSeries("ASM");
//        filter.eq("catalog", "ASM");
		List<IDeviceCatalogSummaryWeek> list3 = deviceCatalogSummaryWeekService.list(filter);
        for(IDeviceCatalogSummaryWeek idc:list3){
        	series1.add(weekOfYear+1, idc.getAddDevNum());
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
                
        return dataset;
        
    }
	
	private XYDataset createDatasetRate(int weekOfYear){
		Date lastWeek = DateUtils.getLastWeek();
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(lastWeek);
		List<IAvgDayOpenRate> rates = avgOpenRateEtlService.getAvgDays(dates[0].intValue(), dates[1].intValue());
		final XYSeries series1 = new XYSeries("ATM");
		for(IAvgDayOpenRate ia:rates){
			series1.add(ia.getDate(), ia.getOpenRate());
		}
		final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
	}
	
	private DefaultCategoryDataset createBarFault(int weekOfYear){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IFaultClassifyWeek> listFault =faultEtlService.getClassifyWeek(weekOfYear);
		for(IFaultClassifyWeek ifc:listFault){
			dataSet.setValue(ifc.getCount(), "Fault", ifc.getClassifyName());
		}
		return dataSet;
	}
	
	private DefaultPieDataset createPieFault(int weekOfYear){
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<IFaultDurationWeek> listFaultDura = faultEtlService.getDurationWeek(weekOfYear);
		for(IFaultDurationWeek ifd:listFaultDura){
			dataSet.setValue(pieTimeToStr(ifd.getDuration()), ifd.getCount());
		}
		
		return dataSet;
	}
	private String pieTimeToStr(int dura){
		String str ="";
		if(dura==1){
			str="半小时内";
		}else if(dura==2){
			str="半小时到2小时";
		}else if(dura==3){
			str="2小时到4小时";
		}else if(dura==4){
			str="4小时到1天";
		}else if(dura==5){
			str="1天到3天";
		}else{
			str="3天以上";
		}
		return str;
	}

}
