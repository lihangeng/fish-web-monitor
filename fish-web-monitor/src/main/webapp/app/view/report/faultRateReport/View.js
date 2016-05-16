Ext.define('Eway.view.report.faultRateReport.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_view',

	requires : [ 'Eway.view.report.faultRateReport.BrandGrid','Eway.view.report.faultRateReport.BrandCharts',
	             'Eway.view.report.faultRateReport.TypeGrid','Eway.view.report.faultRateReport.TypeCharts',
	             'Eway.view.report.faultRateReport.ModuleGrid','Eway.view.report.faultRateReport.ModuleCharts'],

	title : '故障率报表',
	defaults : {
		width : 550,
		height : 400
	},

	scrollable : true,
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			layout : {
				type : 'table',
				columns : 2,
			},
			items : [ {
				xtype : 'report_faultRateReport_BrandGrid',
				margin : 20,
				border : true,
				style:'border-width:5px'
			}, {
				xtype : 'report_faultRateReport_BrandCharts',
				border : true,
			}, {
				xtype : 'report_faultRateReport_TypeGrid',
				margin : 20,
				border : true,
			},{
				xtype:'report_faultRateReport_TypeCharts',
				border:true,
			}, {
				xtype : 'report_faultRateReport_ModuleGrid',
				margin:20,
				border : true,
			},{
				xtype:'report_faultRateReport_ModuleCharts',
				border:true,
			} ]
		});

		this.callParent(arguments);
	}

});
