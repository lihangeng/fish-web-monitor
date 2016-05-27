Ext.define('Eway.controller.report.faultRate.FaultRateReport', {
	extend : 'Eway.controller.base.FishController',

	stores : ['Eway.store.report.faultRateReport.Brand','Eway.store.report.faultRateReport.Type',
			'Eway.store.report.faultRateReport.Module' ],
	models : ['Eway.model.report.faultRateReport.Brand','Eway.model.report.faultRateReport.Type',
			'Eway.model.report.faultRateReport.Module' ],
	views : ['Eway.view.report.faultRateReport.View' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#report_faultRateReport_view',
		xtype : 'report_faultRateReport_view',
		autoCreate : true,
		id : 'report_faultRateReport_view'
	}, {
		ref : 'brandGrid',
		selector : 'report_faultRateReport_BrandGrid'
	}, {
		ref : 'typeGrid',
		selector : 'report_faultRateReport_TypeGrid'
	}, {
		ref : 'moduleGrid',
		selector : 'report_faultRateReport_ModuleGrid'
	} ],

	init : function() {
		this.control({
			'#report_faultRateReport_view button[action=query]' : {
				click : this.onQuery
			},
			'report_faultRateReport_BrandGrid ' : {
				itemclick : this.brandQuery
			},
			'report_faultRateReport_TypeGrid' : {
				itemclick : this.typeQuery
			},
			'report_faultRateReport_ModuleGrid' : {
				itemclick : this.moduleQuery
			}
		});
	},
	
	brandQuery:function(){
		var brandGrid = this.getBrandGrid();
		var typeGrid = this.getTypeGrid();
		var store = typeGrid.getStore();
		var record = brandGrid.getSelectionModel().getLastSelected();
		if(record != null){
			store.setBaseParam("name",record.get('name'));
			store.loadPage(1);
		}
	}

});