package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.api.report.trans.ICashInRptService;
import com.yihuacomputer.fish.api.report.trans.ISettlementCashInRptService;
import com.yihuacomputer.fish.api.report.trans.ISettlementRptService;
import com.yihuacomputer.fish.api.report.trans.ITransRptService;
import com.yihuacomputer.fish.api.report.trans.ITransactionDaysService;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonthsService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.monitor.entity.volume.DayTradingVolumeService;
import com.yihuacomputer.fish.monitor.service.CashInitPlanInfoService;
import com.yihuacomputer.fish.report.service.trans.CashInRptService;
import com.yihuacomputer.fish.report.service.trans.SettlementCashInRptService;
import com.yihuacomputer.fish.report.service.trans.SettlementRptService;
import com.yihuacomputer.fish.report.service.trans.TransRptService;
import com.yihuacomputer.fish.report.service.trans.TransactionDaysService;
import com.yihuacomputer.fish.report.service.trans.TransactionMonthsService;
import com.yihuacomputer.fish.report.service.trans.etl.TransTypeEtlService;

/**
 * 报表分析模块配置
 *
 * @author xuxiang
 * @since 1.4.0
 *
 */
@Configuration
public class ReportTransModule {
	
	@Bean
	public ISettlementCashInRptService settlementCashInRptService() {
		return new SettlementCashInRptService();
	}

	@Bean
	public ISettlementRptService settlementRptService() {
		return new SettlementRptService();
	}

	@Bean
	public ITransRptService transRptService() {
		return new TransRptService();
	}

	@Bean
	public ITransactionDaysService transactionDaysService() {
		return new TransactionDaysService();
	}

	@Bean
	public ITransactionMonthsService transactionMonthsService() {
		return new TransactionMonthsService();
	}

	@Bean
	public ITransTypeEtlService transTypeEtlService(){
		return new TransTypeEtlService();
	}

	@Bean
	public ICashInRptService cashInRptService() {
		return new CashInRptService();
	}
	
	@Bean
	public IDayTradingVolumeService dayTradingVolumeService(){
		return new DayTradingVolumeService();
	}
	
	@Bean
	public ICashInitPlanInfoService cashInitPlanInfoService(){
		return new CashInitPlanInfoService();
	}
	
}