Ext.define('Eway.controller.parameter.AppSystem',{
	extend :'Eway.controller.base.FishController',
	
	stores :['parameter.AppSystem'],
	models :['parameter.AppSystem'],
	views  :['parameter.appSystem.View','parameter.appSystem.Update'],
	
	refs :[{
		ref:'ewayView',
		selector:'#parameter_appSystem_view',
		xtype :'parameter_appSystem_view',
		autoCreate:true,
		id:'parameter_appSystem_view'
	},{
		ref:'grid',
		selector:'parameter_appSystem_grid'
	},{
		ref:'filterForm',
		selector:'parameter_appSystem_filterForm'
	},{
		ref:'updateWin',
		selector:'parameter_appSystem_update'
	}],
	
	formConfig:{
		form:'Eway.view.parameter.appSystem.Update',
		xtype:'parameter_appSystem_update',
		width:450,
		height:280,
		title:'应用系统信息'
	},
	
	init : function(){
		this.control({
			'#parameter_appSystem_view button[action=query]' : {
				click :this.onQuery
			},
			'#parameter_appSystem_view button[action=update]' : {
				click :this.onUpdate
			}
		});
	}
	
	
	
});