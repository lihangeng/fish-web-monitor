Ext.define('Eway.view.report.faultRateReport.TypeView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_faultRateReport_typeView',

	requires : [ 'Eway.view.report.faultRateReport.TypeGrid',
			'Eway.view.report.faultRateReport.TypeCharts',
			'Eway.view.report.faultRateReport.ModuleView' ],
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
	
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				items : [ {
					height : 40,
					tbar : [ {
						text : '返回',
						glyph : 0xf048,
						action : 'back',
						tooltip : '返回',
						code : 'back'
					}, "->", {
						text : '上一个品牌',
						glyph : 0xf060,
						action : 'pref',
						tooltip : '上一个品牌',
						code : 'pref'
					}, {
						text : '下一个品牌',
						glyph : 0xf061,
						action : 'next',
						tooltip : '下一个品牌',
						code : 'next'
					}]
				} ]
			}, {
				region : 'center',
				xtype : 'panel',
				layout : 'card',
				items : [ {
					name : 'groupPanel',
					xtype : 'panel',
					layout : 'border',
					items : [ {
						xtype : 'report_faultRateReport_TypeGrid',
						region : 'north',
						border : true,
						height : 140
					}, {
						xtype : 'report_faultRateReport_TypeCharts',
						region : 'center',
					} ]
				}, {
					xtype : 'report_faultRateReport_moduleView'
				} ]
			} ],
			listeners : {
				activate : function(panel) {
					panel.down('report_faultRateReport_TypeGrid').getStore()
							.load();
					panel.down('report_faultRateReport_TypeCharts').down(
							'cartesian').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}

});