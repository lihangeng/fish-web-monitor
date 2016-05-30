Ext.define('Eway.view.report.faultRateReport.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_view',

	requires : [ 'Eway.view.report.faultRateReport.BrandGrid',
			'Eway.view.report.faultRateReport.BrandCharts',
			'Eway.view.report.faultRateReport.TypeGrid',
			'Eway.view.report.faultRateReport.TypeCharts',
			'Eway.view.report.faultRateReport.ModuleGrid',
			'Eway.view.report.faultRateReport.ModuleCharts',
			'Eway.lib.DateMonth'],

	title : '故障率报表',

	scrollable : true,
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				margin : 20,
				items : [ {
					xtype : 'datefield',
					fieldLabel : '月份',
					labelWidth : 50,
					editable : false,
					value : new Date(),
					labelAlign : 'left',
					format : 'Y-m',
					name:'dateMonth',
					width : 220,
					anchor: '100%',
					height : 20,
					listeners : {
						blur : {
				            fn: function(This, options){
				            	return;
				            }
						}
					}
				}, {
					height:30,
					tbar : [ '->', {
						text : EwayLocale.button.search,
						action : 'query',
						glyph : 0xf002,
					} ]
				} ]
			}, {
				layout : {
					type : 'table',
					columns : 2,
				},
				defaults : {
					width : 540,
					height : 420
				},
				items : [ {
					xtype : 'report_faultRateReport_BrandGrid',
					margin : '5 10 10 10',
					border : true,
					style : 'border:solid #D2D2D2'
				}, {
					xtype : 'report_faultRateReport_BrandCharts',
					split : true,
					margin : '-5 0 0 0',
					border : true,
					style : 'border:solid #D2D2D2'
				}, {
					xtype : 'report_faultRateReport_TypeGrid',
					margin : 10,
					border : true,
					style : 'border:solid #D2D2D2'
				}, {
					xtype : 'report_faultRateReport_TypeCharts',
					border : true,
					style : 'border:solid #D2D2D2'
				}, {
					xtype : 'report_faultRateReport_ModuleGrid',
					margin : '5 10 10 10',
					border : true,
					style : 'border:solid #D2D2D2'
				}, {
					xtype : 'report_faultRateReport_ModuleCharts',
					margin : '-5 0 0 0',
					border : true,
					style : 'border:solid #D2D2D2'
				} ]
			} ]
		});

		this.callParent(arguments);
	}

});
