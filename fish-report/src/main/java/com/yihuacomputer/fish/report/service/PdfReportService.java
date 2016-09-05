package com.yihuacomputer.fish.report.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
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
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardMonth;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;
import com.yihuacomputer.fish.api.report.engine.IPdfReportService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateWeek;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeMonth;
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
public class PdfReportService implements IPdfReportService{

	@Autowired
	private IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	@Autowired
	private IDeviceCatalogSummaryMonthService deviceCatalogSummaryMonthService;
	@Autowired
	private IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
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
	private int chartWidth = (int)(PageSize.A4.getWidth() * 0.8);
	private int devChartWidth = (int)(PageSize.A4.getWidth() * 0.9);
	@Override
	public String generateWeekPDF(int weekOfYear) {
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(week);
		String weekOf =String.valueOf(weekOfYear).substring(4);
		File file = PdfConfig.getWeekReportFile(weekOfYear);
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			pdf.addTitle("中国农业银行");
			pdf.addSubTitle("自助设备运行分析报告");
			pdf.addTriTitle("2016年"+weekOf+"周("+dates[0].toString().substring(4)+"-"+dates[0].toString().substring(4)+")");
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
		
		pdf.addChart(PdfChart.generateBarChart(createBarFault(weekOfYear)), devChartWidth, 260);
		
		pdf.addContent("3.上周故障关闭时间的分布图");
		JFreeChart faultChart = PdfChart.generatePieChart(createPieFault(weekOfYear));
		pdf.addChart(faultChart, devChartWidth, 250);
	}

	private void generateRetainCard(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("四、吞卡数据汇总");
		Long[] retain = retainCardEtlService.getWeekTotal(Long.valueOf(weekOfYear));
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
		retainCell = new PdfPCell(new Phrase("设备数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell); 
		retainCell = new PdfPCell(new Phrase("吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		retainCell = new PdfPCell(new Phrase("同比上周吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		List<IRetainCardWeek> listRetain = retainCardEtlService.getWeek(Long.valueOf(weekOfYear));
		for(IRetainCardWeek ir:listRetain){
			retainTable.addCell(ir.getDevType());
			retainTable.addCell(String.valueOf(ir.getDeviceCount()));
			retainTable.addCell(String.valueOf(ir.getRetainCount()));
			retainTable.addCell(String.valueOf(ir.getLastRetainCount()));
		}
		pdf.getDocument().add(retainTable);
	}

	private void generateTrans(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("三、交易数据汇总");
		Long[] trans = transTypeEtlService.getWeekTotal(Long.valueOf(weekOfYear));
		Long[] lastTrans = transTypeEtlService.getWeekTotal(Long.valueOf(weekOfYear-1));
		long remains = trans[1] - lastTrans[1];
		String tranStr ="";
		if(remains>0){
			tranStr+="增加"+remains+"次。";
		}else{
			tranStr+="减少"+ (lastTrans[1] - trans[1])+"次。";
		}
		pdf.addContent("1.上周共产生交易"+trans[1]+"次，平均"+(trans[1]/7)+"次/日。相比较上周交易次数"+tranStr);
		
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
		
		List<ITransTypeWeek> transList = transTypeEtlService.getWeek(weekOfYear);
		for(ITransTypeWeek it:transList){
			transTable.addCell(it.getTransCode());
			transTable.addCell(String.valueOf(it.getTransCount()));
			transTable.addCell(String.valueOf(it.getTransAmount()));
		}
		pdf.getDocument().add(transTable);
	}

	private void generateOpenRate(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("二、开机率汇总");
		Object [] obj= avgOpenRateEtlService.getWeekTotal(weekOfYear);
		String avgRate = String.valueOf(obj[2]);
		pdf.addContent("1. 上周所有设备平均开机率为"+avgRate+"%");
		pdf.addContent("2.上周每日的开机率趋势");
		DefaultCategoryDataset openRateDataset2=createDatasetRate(weekOfYear);
		pdf.addChart(PdfChart.generateLineChart(openRateDataset2), chartWidth, 260);
		
		pdf.addContent("3.上周所有设备型号的开机率，从高到低排列");
		List<IDeviceTypeOpenRateWeek> typeRate =deviceTypeOpenRateEtlService.getDeviceTypeWeek(Long.valueOf(weekOfYear)); 
		
		PdfPTable typeTable = new PdfPTable(4);
		typeTable.setWidthPercentage(88);
		typeTable.setWidths(new float[]{18,22,22,22});
		PdfPCell typeCell = new PdfPCell(new Phrase("设备型号名称",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);  
		typeCell = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell); 
		typeCell = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		typeCell = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		for(IDeviceTypeOpenRateWeek idt:typeRate){
			typeTable.addCell(idt.getDevType());
			typeTable.addCell(String.valueOf(secondToDay(idt.getOpenTimes())));
			typeTable.addCell(String.valueOf(secondToDay(idt.getHealthyTimeReal())));
			typeTable.addCell(String.valueOf(idt.getOpenRate()));
		}
		pdf.getDocument().add(typeTable);
		pdf.addContent("4.上周各网点开机率汇总。");
		pdf.addContent("4.1 上周各网点统计的开机率，较好开机率前10为：");
		PdfPTable orgTableTop = new PdfPTable(4);
		orgTableTop.setWidthPercentage(88);
		orgTableTop.setWidths(new float[]{12,25,25,21});
		PdfPCell orgCellTop = new PdfPCell(new Phrase("网点名称",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);  
		orgCellTop = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop); 
		orgCellTop = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);
		orgCellTop = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);
		List<IOrgOpenRateWeek> orgTop =orgOpenRateEtlService.getTopOrgWeek(weekOfYear, 10);
		for(IOrgOpenRateWeek ior:orgTop){
			orgTableTop.addCell(ior.getOrgName());
			orgTableTop.addCell(String.valueOf(secondToDay(ior.getOpenTimes())));
			orgTableTop.addCell(String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			orgTableTop.addCell(String.valueOf(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableTop);
		
		pdf.addContent("4.2 上周各网点统计的开机率，较差的开机率前10为：");
		PdfPTable orgTableLast = new PdfPTable(4);
		orgTableLast.setWidthPercentage(88);
		orgTableLast.setWidths(new float[]{12,25,25,21});
		PdfPCell orgCellLast = new PdfPCell(new Phrase("网点名称",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);  
		orgCellLast = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast); 
		orgCellLast = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);
		orgCellLast = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);
		List<IOrgOpenRateWeek> orgLast =orgOpenRateEtlService.getLastOrgWeek(weekOfYear, 10);
		for(IOrgOpenRateWeek ior:orgLast){
			orgTableLast.addCell(ior.getOrgName());
			orgTableLast.addCell(String.valueOf(secondToDay(ior.getOpenTimes())));
			orgTableLast.addCell(String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			orgTableLast.addCell(String.valueOf(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableLast);
		
		pdf.addContent("5.上周所有设备开机率汇总。");
		pdf.addContent("5.1 上周设备统计的开机率，较好开机率设备的前10为：");
		PdfPTable devTableTop = new PdfPTable(5);
		devTableTop.setWidthPercentage(88);
		PdfPCell devCellTop = new PdfPCell(new Phrase("设备终端号",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);  
		devCellTop = new PdfPCell(new Phrase("机构名称",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop); 
		devCellTop = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		devCellTop = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		devCellTop = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		List<IDeviceOpenRateWeek> devListTop=deviceOpenRateEtlService.getTopDeviceWeek(weekOfYear,10); 
		for(IDeviceOpenRateWeek ido:devListTop){
			devTableTop.addCell(ido.getTerminalId());
			devTableTop.addCell(ido.getOrgName());
			devTableTop.addCell(String.valueOf(secondToDay(ido.getOpenTimes())));
			devTableTop.addCell(String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			devTableTop.addCell(String.valueOf(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableTop);
		
		pdf.addContent("5.2 上周设备统计的开机率，较差开机率设备的前10为：");
		PdfPTable devTableLast = new PdfPTable(5);
		devTableLast.setWidthPercentage(88);
		PdfPCell devCellLast = new PdfPCell(new Phrase("设备终端号",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);  
		devCellLast = new PdfPCell(new Phrase("设备所属机构名称",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast); 
		devCellLast = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		devCellLast = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		devCellLast = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		List<IDeviceOpenRateWeek> devListLast=deviceOpenRateEtlService.getLastDeviceWeek(weekOfYear, 10); 
		for(IDeviceOpenRateWeek ido:devListLast){
			devTableLast.addCell(ido.getTerminalId());
			devTableLast.addCell(ido.getOrgName());
			devTableLast.addCell(String.valueOf(secondToDay(ido.getOpenTimes())));
			devTableLast.addCell(String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			devTableLast.addCell(String.valueOf(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableLast);
	}

	private void generateDevice(Pdf pdf,int weekOfYear) throws Exception {
		pdf.addChapter("一、设备信息汇总");
		ParagraphMgr mgr = ParagraphMgr.getInstance();
		IFilter filter = new Filter();
		filter.eq("date", String.valueOf(weekOfYear));
		filter.order("date");
		List<IDeviceCatalogSummaryWeek> list1 = deviceCatalogSummaryWeekService.list(filter);
		int devNum=0;
		int devAddNum=0;
		int devScraNum=0;
		for(IDeviceCatalogSummaryWeek device :list1){
			devNum+=device.getNum();
			devAddNum+=device.getAddDevNum();
			devScraNum+=device.getScrappedDevNum();
		}
		
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(week);
		String nowDate = dates[1].toString().substring(0, 4)+"年"+dates[1].toString().substring(4, 6)+"月"+dates[1].toString().substring(6)+"日";
		mgr.addChunk("1. 截止"+nowDate+"，共有设备"+devNum+"台，其中",FontMgr.getFont14());
		for(IDeviceCatalogSummaryWeek device :list1){
			mgr.addChunk(device.getCatalog())
				.addChunk("有"+device.getNum()+"台，",FontMgr.getFont14());
		}
		mgr.addChunk("上周新增设备"+devAddNum+"台，报废设备"+devScraNum+"台。",FontMgr.getFont14());
		pdf.addParagraph(mgr);
		pdf.addContent("2.设备型号统计");
		
		PdfPTable table = pdf.addTableHeader(5,85,new float[]{16,16,16,22,22},new String []{"型号名称","设备数量","日期（周）","新增设备数量","报废设备数量"});
		
		List<IDeviceTypeSummaryWeek> list2 = deviceTypeSummaryWeekService.list(filter);
		for(IDeviceTypeSummaryWeek dt:list2){
			table.addCell(dt.getDevType());
			table.addCell(String.valueOf(dt.getNum()));
			table.addCell(String.valueOf(dt.getDate()).substring(4));
			table.addCell(String.valueOf(dt.getAddDevNum()));
			table.addCell(String.valueOf(dt.getScrappedDevNum()));
		}
		pdf.getDocument().add(table);  
		
		pdf.addEmptyLine(1);
		pdf.addContent("3.最近4周新增设备和报废设备的趋势图");
		
		DefaultCategoryDataset  dataset1= createDatasetFW(weekOfYear);
		JFreeChart devChart = PdfChart.generateLineChart(dataset1);
		pdf.addChart(devChart, chartWidth, 260);
	}
	
	public  DefaultCategoryDataset  createDatasetFW(int weekOfYear) {
		IFilter filter = new Filter();
		filter.ge("date", String.valueOf(weekOfYear-4));
		filter.le("date", String.valueOf(weekOfYear));
		filter.order("date");
		List<IDeviceCatalogSummaryWeek> list1 = deviceCatalogSummaryWeekService.list(filter);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 for(IDeviceCatalogSummaryWeek idc:list1){
			 String week =idc.getDate().substring(4)+"周";
			 dataset.setValue(idc.getAddDevNum(),"新增设备" ,week);
			 dataset.setValue(idc.getScrappedDevNum(), "报废设备", week);
	        }
        return dataset;
        
    }
	
	private DefaultCategoryDataset createDatasetRate(int weekOfYear){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(week);
		List<IAvgDayOpenRate> rates = avgOpenRateEtlService.getAvgDays(dates[0].intValue(), dates[1].intValue());
		String day="01";
		for(IAvgDayOpenRate ia:rates){
			day = String.valueOf(ia.getDate()).substring(6)+"日";
			dataSet.setValue(ia.getOpenRate(), "开机率", day);;
		}
        return dataSet;
	}
	
	private DefaultCategoryDataset createBarFault(int weekOfYear){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IFaultClassifyWeek> listFault =faultEtlService.getClassifyWeek(Long.valueOf(weekOfYear));
		for(IFaultClassifyWeek ifc:listFault){
			dataSet.setValue(ifc.getCount(), "Fault", ifc.getClassifyName());
		}
		return dataSet;
	}
	
	private DefaultPieDataset createPieFault(int weekOfYear){
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<IFaultDurationWeek> listFaultDura = faultEtlService.getDurationWeek(Long.valueOf(weekOfYear));
		for(IFaultDurationWeek ifd:listFaultDura){
			dataSet.setValue(pieTimeToStr(ifd.getDuration())+"故障数: "+ifd.getCount(), ifd.getCount());
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
	
	private String secondToDay(long second){
		double day = (double)second/(60*60*24);
		return new java.text.DecimalFormat("0.00").format(day);
	}
	

	@Override
	public void sendPdf(String pdfName) {
	}

	@Override
	public String generateMonthPDF(int month) {
		File file = new File("D:/自助设备运行分析报告_2016年第"+month+"月.pdf");
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(file);
			pdf.addTitle("中国农业银行");
			pdf.addSubTitle("自助设备运行分析报告");
			pdf.addTriTitle("2016年第"+month+"月");
			pdf.addEmptyLine(2);
			generateDeviceMonth(pdf,month);
			generateOpenRateMonth(pdf,month);
			generateTransMonth(pdf,month);
			generateRetainCardMonth(pdf,month);
			generateFaultMonth(pdf,month);
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file.getAbsolutePath();
	}
	
	private void generateFaultMonth(Pdf pdf,int month) throws Exception {
		pdf.addChapter("五、设备故障数据汇总");
		IFaultMonth fault = faultEtlService.getMonth(month);
		long amountFault = 0l;
		long closeCount =0l;
		long openCount =0l;
		if(fault !=null){
			openCount = fault.getOpenCount();
			closeCount=fault.getCloseCount();
			amountFault = fault.getOpenCount()+fault.getCloseCount();
		}
		pdf.addContent("1.上月共产生故障数"+amountFault+"个，其中已关闭"+openCount+"个，未关闭"+closeCount+"个。");
		pdf.addContent("2.上月按照故障类型统计故障数量");
		
		pdf.addChart(PdfChart.generateBarChart(createBarFaultMonth(month)), devChartWidth, 260);
		
		pdf.addContent("3.上月故障关闭时间的分布图");
		JFreeChart faultChart = PdfChart.generatePieChart(createPieFaultMonth(month));
		pdf.addChart(faultChart, devChartWidth, 250);
	}

	private void generateRetainCardMonth(Pdf pdf,int month) throws Exception {
		pdf.addChapter("四、吞卡数据汇总");
		Long[] retain = retainCardEtlService.getMonthTotal(Long.valueOf(month));
		String retainStr = "";
		if(retain[2]>0){
			retainStr=retainStr+"增加"+retain[2];
		}else{
			retainStr=retainStr+"减少"+retain[2];
		}
		pdf.addContent("1.上月共产生吞卡"+retain[1]+"次，平均"+retain[1]/30+"次/日（/台）。相比较上月"+retainStr+"次吞卡。");
		pdf.addContent("2.上月各型号设备吞卡次数统计表");
		PdfPTable retainTable = new PdfPTable(4);
		PdfPCell retainCell = new PdfPCell(new Phrase("设备类型",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);  
		retainCell = new PdfPCell(new Phrase("设备数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell); 
		retainCell = new PdfPCell(new Phrase("吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		retainCell = new PdfPCell(new Phrase("同比上月吞卡数量",FontMgr.getFont14()));
		retainCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		retainTable.addCell(retainCell);
		List<IRetainCardMonth> listRetain = retainCardEtlService.getMonth(Long.valueOf(month));
		for(IRetainCardMonth ir:listRetain){
			retainTable.addCell(ir.getDevType());
			retainTable.addCell(String.valueOf(ir.getDeviceCount()));
			retainTable.addCell(String.valueOf(ir.getRetainCount()));
			retainTable.addCell(String.valueOf(ir.getLastRetainCount()));
		}
		pdf.getDocument().add(retainTable);
	}

	private void generateTransMonth(Pdf pdf,int month) throws Exception {
		pdf.addChapter("三、交易数据汇总");
		Long[] trans = transTypeEtlService.getMonthTotal(Long.valueOf(month));
		Long[] lastTrans = transTypeEtlService.getMonthTotal(Long.valueOf(month-1));
		long remains = trans[1] - lastTrans[1];
		String tranStr ="";
		if(remains>0){
			tranStr+="增加"+remains+"次。";
		}else{
			tranStr+="减少"+ (lastTrans[1] - trans[1])+"次。";
		}
		Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.valueOf(String.valueOf(month).substring(0, 4))); 
        int mon=0;
        if(String.valueOf(month).substring(4, 5).equals("0")){
        	mon=Integer.valueOf(String.valueOf(month).substring(5));
        }else{
        	mon=Integer.valueOf(String.valueOf(month).substring(4));
        }
        c.set(Calendar.MONTH, mon-1); // 6 月
		pdf.addContent("1.上月共产生交易"+trans[1]+"次，平均"+(trans[1]/c.getActualMaximum(Calendar.DAY_OF_MONTH))+"次/日。相比较上月交易次数"+tranStr);
		
		pdf.addContent("2.上月按照交易类型统计的交易次数和交易金额");
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
		
		List<ITransTypeMonth> transList = transTypeEtlService.getMonth(month);
		for(ITransTypeMonth it:transList){
			transTable.addCell(it.getTransCode());
			transTable.addCell(String.valueOf(it.getTransCount()));
			transTable.addCell(String.valueOf(it.getTransAmount()));
		}
		pdf.getDocument().add(transTable);
	}

	private void generateOpenRateMonth(Pdf pdf,int month) throws Exception {
		pdf.addChapter("二、开机率汇总");
		Object [] obj= avgOpenRateEtlService.getMonthTotal(month);
		String avgRate = String.valueOf(obj[2]);
		pdf.addContent("1. 上月所有设备平均开机率为"+avgRate+"%");
		pdf.addContent("2.上月每日的开机率趋势");
		DefaultCategoryDataset openRateDataset2=createDatasetRateMonth(month);//TODO ..
		pdf.addChart(PdfChart.generateLineChart(openRateDataset2), devChartWidth, 260);
		
		pdf.addContent("3.上月所有设备型号的开机率，从高到低排列");
		List<IDeviceTypeOpenRateMonth> typeRate =deviceTypeOpenRateEtlService.getDeviceTypeMonth(Long.valueOf(month)); 
		
		PdfPTable typeTable = new PdfPTable(4);
		typeTable.setWidthPercentage(88);
		typeTable.setWidths(new float[]{18,22,22,22});
		PdfPCell typeCell = new PdfPCell(new Phrase("设备型号名称",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);  
		typeCell = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell); 
		typeCell = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		typeCell = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		typeCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		typeTable.addCell(typeCell);
		for(IDeviceTypeOpenRateMonth idt:typeRate){
			typeTable.addCell(idt.getDevType());
			typeTable.addCell(String.valueOf(secondToDay(idt.getOpenTimes())));
			typeTable.addCell(String.valueOf(secondToDay(idt.getHealthyTimeReal())));
			typeTable.addCell(String.valueOf(idt.getOpenRate()));
		}
		pdf.getDocument().add(typeTable);
		pdf.addContent("4.上月各网点统计的开机率汇总。");
		pdf.addContent("4.1 上月各网点统计的开机率，较好开机率前10为：");
		PdfPTable orgTableTop = new PdfPTable(4);
		orgTableTop.setWidthPercentage(88);
		orgTableTop.setWidths(new float[]{12,25,25,21});
		PdfPCell orgCellTop = new PdfPCell(new Phrase("网点名称",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);  
		orgCellTop = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop); 
		orgCellTop = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);
		orgCellTop = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		orgCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableTop.addCell(orgCellTop);
		List<IOrgOpenRateMonth> orgTop =orgOpenRateEtlService.getTopOrgMonth(month, 10);
		for(IOrgOpenRateMonth ior:orgTop){
			orgTableTop.addCell(ior.getOrgName());
			orgTableTop.addCell(String.valueOf(secondToDay(ior.getOpenTimes())));
			orgTableTop.addCell(String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			orgTableTop.addCell(String.valueOf(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableTop);
		
		pdf.addContent("4.2 上月各网点统计的开机率，较差的开机率前10为：");
		PdfPTable orgTableLast = new PdfPTable(4);
		orgTableLast.setWidthPercentage(88);
		orgTableLast.setWidths(new float[]{12,25,25,21});
		PdfPCell orgCellLast = new PdfPCell(new Phrase("网点名称",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);  
		orgCellLast = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast); 
		orgCellLast = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);
		orgCellLast = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		orgCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		orgTableLast.addCell(orgCellLast);
		List<IOrgOpenRateMonth> orgLast =orgOpenRateEtlService.getLastOrgMonth(month, 10);
		for(IOrgOpenRateMonth ior:orgLast){
			orgTableLast.addCell(ior.getOrgName());
			orgTableLast.addCell(String.valueOf(secondToDay(ior.getOpenTimes())));
			orgTableLast.addCell(String.valueOf(secondToDay(ior.getHealthyTimeReal())));
			orgTableLast.addCell(String.valueOf(ior.getOpenRate()));
		}
		pdf.getDocument().add(orgTableLast);
		
		pdf.addContent("5.上月所有设备开机率汇总。");
		pdf.addContent("5.1 上月设备统计的开机率，较好开机率设备的前10为：");
		PdfPTable devTableTop = new PdfPTable(5);
		devTableTop.setWidthPercentage(88);
		PdfPCell devCellTop = new PdfPCell(new Phrase("设备终端号",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);  
		devCellTop = new PdfPCell(new Phrase("设备所属机构名称",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop); 
		devCellTop = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		devCellTop = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		devCellTop = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		devCellTop.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableTop.addCell(devCellTop);
		List<IDeviceOpenRateMonth> devListTop=deviceOpenRateEtlService.getTopDeviceMonth(month,10); 
		for(IDeviceOpenRateMonth ido:devListTop){
			devTableTop.addCell(ido.getTerminalId());
			devTableTop.addCell(ido.getOrgName());
			devTableTop.addCell(String.valueOf(secondToDay(ido.getOpenTimes())));
			devTableTop.addCell(String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			devTableTop.addCell(String.valueOf(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableTop);
		
		pdf.addContent("5.2 上月设备统计的开机率，较差开机率设备的前10为：");
		PdfPTable devTableLast = new PdfPTable(5);
		devTableLast.setWidthPercentage(88);
		PdfPCell devCellLast = new PdfPCell(new Phrase("设备终端号",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);  
		devCellLast = new PdfPCell(new Phrase("设备所属机构名称",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast); 
		devCellLast = new PdfPCell(new Phrase("设备应开机时长（天）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		devCellLast = new PdfPCell(new Phrase("设备实际开机时长（天）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		devCellLast = new PdfPCell(new Phrase("开机率（百分比）",FontMgr.getFont14()));
		devCellLast.setBackgroundColor(BaseColor.LIGHT_GRAY);
		devTableLast.addCell(devCellLast);
		List<IDeviceOpenRateMonth> devListLast=deviceOpenRateEtlService.getLastDeviceMonth(month, 10); 
		for(IDeviceOpenRateMonth ido:devListLast){
			devTableLast.addCell(ido.getTerminalId());
			devTableLast.addCell(ido.getOrgName());
			devTableLast.addCell(String.valueOf(secondToDay(ido.getOpenTimes())));
			devTableLast.addCell(String.valueOf(secondToDay(ido.getHealthyTimeReal())));
			devTableLast.addCell(String.valueOf(ido.getOpenRate()));
		}
		pdf.getDocument().add(devTableLast);
	}

	private void generateDeviceMonth(Pdf pdf,int month) throws Exception {
		pdf.addChapter("一、设备信息汇总");
		ParagraphMgr mgr = ParagraphMgr.getInstance();
		IFilter filter = new Filter();
		filter.eq("date", String.valueOf(month));
		List<IDeviceCatalogSummaryMonth> list1 = deviceCatalogSummaryMonthService.list(filter);
		int devNum=0;
		int devAddNum=0;
		int devScraNum=0;
		for(IDeviceCatalogSummaryMonth device :list1){
			devNum+=device.getNum();
			devAddNum+=device.getAddDevNum();
			devScraNum+=device.getScrappedDevNum();
		}
		
		String date1=DateUtils.getLastDate();
		
		mgr.addChunk("1. 截止"+date1+"，共有设备"+devNum+"台，其中",FontMgr.getFont14());
		for(IDeviceCatalogSummaryMonth device :list1){
			mgr.addChunk(device.getCatalog())
				.addChunk("有"+device.getNum()+"台，",FontMgr.getFont14());
		}
		mgr.addChunk("上月新增设备"+devAddNum+"台，报废设备"+devScraNum+"台。",FontMgr.getFont14());
		pdf.addParagraph(mgr);
		pdf.addContent("2.设备型号统计");
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(85);
		table.setTotalWidth(new float[]{16,16,16,22,22});
		PdfPCell cell = new PdfPCell(new Phrase("型号名称",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);  
		cell = new PdfPCell(new Phrase("设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		cell = new PdfPCell(new Phrase("日期（月）",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("新增设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("报废设备数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		
		List<IDeviceTypeSummaryMonth> list2 = deviceTypeSummaryMonthService.list(filter);
		for(IDeviceTypeSummaryMonth dt:list2){
			table.addCell(dt.getDevType());
			table.addCell(String.valueOf(dt.getNum()));
			table.addCell(dt.getDate());
			table.addCell(String.valueOf(dt.getAddDevNum()));
			table.addCell(String.valueOf(dt.getScrappedDevNum()));
		}
		pdf.getDocument().add(table);  
		
		pdf.addEmptyLine(1);
		pdf.addContent("3.最近3个月新增设备和报废设备的趋势图");
		
		DefaultCategoryDataset dataset1= createDatasetMonth(month);
		JFreeChart devChart = PdfChart.generateLineChart(dataset1);
		pdf.addChart(devChart, chartWidth, 260);
	}
	
	public  DefaultCategoryDataset createDatasetMonth(int month) {
		IFilter filter = new Filter();//TODO 时间格式不对
		filter.gt("date", String.valueOf(month-2));
		filter.le("date", String.valueOf(month));
		filter.descOrder("date");
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IDeviceCatalogSummaryMonth> list1 = deviceCatalogSummaryMonthService.list(filter);
		String mon="01";
        for(IDeviceCatalogSummaryMonth idc:list1){
        	mon = idc.getDate().substring(4)+"月";
        	dataSet.setValue(idc.getAddDevNum(), "新增设备", mon);
        	dataSet.setValue(idc.getScrappedDevNum(), "报废设备", mon);
        }
        return dataSet;
    }
	
	private DefaultCategoryDataset createDatasetRateMonth(int month){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		long start = month*100+1;
		long end = month*100+32;
		List<IAvgDayOpenRate> rates = avgOpenRateEtlService.getAvgDays(start, end);
		String day="01";
		for(IAvgDayOpenRate ia:rates){
			day=String.valueOf(ia.getDate()).substring(6)+"日";
			dataSet.setValue(ia.getOpenRate(), "开机率", day);
		}
        return dataSet;
	}
	
	private DefaultCategoryDataset createBarFaultMonth(int month){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<IFaultClassifyMonth> listFault =faultEtlService.getClassifyMonth(Long.valueOf(month));
		for(IFaultClassifyMonth ifc:listFault){
			dataSet.setValue(ifc.getCount(), "Fault", ifc.getClassifyName());
		}
		return dataSet;
	}
	
	private DefaultPieDataset createPieFaultMonth(int month){
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<IFaultDurationMonth> listFaultDura = faultEtlService.getDurationMonth(Long.valueOf(month));
		for(IFaultDurationMonth ifd:listFaultDura){
			dataSet.setValue(pieTimeToStr(ifd.getDuration())+"故障数: "+ifd.getCount(), ifd.getCount());
		}
		
		return dataSet;
	}
	

}
