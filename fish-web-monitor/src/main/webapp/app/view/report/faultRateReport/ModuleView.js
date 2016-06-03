Ext.define('Eway.view.report.faultRateReport.ModuleView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_moduleView',

	requires : [ 'Eway.lib.Util',
			'Eway.view.report.faultRateReport.ModuleGrid',
			'Eway.view.report.faultRateReport.ModuleCharts' ],

	border : false,
	autoScroll : true,
	closable : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		loadMask : true,
		enableTextSelection : true
	},
	firstIn : true,
	config : {
		name : null
	},
	
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			layout : 'border',
			initRegion : true,
			border : true,
			weight : 15,
			tbar : [ {
				text : '返回',
				glyph : 0xf048,
				action : 'back',
				tooltip : '返回',
				code : 'back'
			}, "->", {
				text : '上一个型号',
				glyph : 0xf060,
				action : 'pref',
				tooltip : '上一个型号',
				code : 'pref'
			}, {
				text : '下一个型号',
				glyph : 0xf061,
				action : 'next',
				tooltip : '下一个型号',
				code : 'next'
			} ],
			items : [ {
				xtype : 'report_faultRateReport_ModuleGrid',
				region : 'north',
				border : true,
				height : 140
			}, {
				xtype : 'report_faultRateReport_ModuleCharts',
				region : 'center',
			} ],
			listeners : {
				activate : function(_this, eOpts) {
					var jobId = _this.getConfig().jobId;
					_this.down('report_faultRateReport_ModuleGrid').getStore()
							.setBaseParam("name", name);
					_this.down('report_faultRateReport_ModuleGrid').getStore()
							.load();
					_this.down('report_faultRateReport_ModuleCharts').down(
							'cartesian').getStore().setBaseParam("name", name);
					_this.down('report_faultRateReport_ModuleCharts').down(
							'cartesian').getStore().load();

				}
			}
		});

		this.callParent(arguments);
	}
});