Ext.define('Eway.controller.report.faultRate.FaultRateReport',{
	extend :'Eway.controller.base.FishController',

//	stores :['parameter.appSystem.AppSystem'],
//	models :['parameter.appSystem.AppSystem'],
	views  :['Eway.view.report.faultRateReport.View'],

	refs :[{
		ref:'ewayView',
		selector:'#report_faultRateReport_view',
		xtype :'report_faultRateReport_view',
		autoCreate:true,
		id:'report_faultRateReport_view'
	}/*,{
		ref:'grid',
		selector:'parameter_appSystem_grid'
	},{
		ref:'filterForm',
		selector:'parameter_appSystem_filterForm'
	},{
		ref:'updateWin',
		selector:'parameter_appSystem_update'
	}*/],


	init : function(){
		this.control({
			'#report_faultRateReport_view button[action=query]' : {
				click :this.onQuery
			},
			'#report_faultRateReport_view button[action=update]' : {
				click :this.onUpdate
			}
		});
	}



});