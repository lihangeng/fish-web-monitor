Ext.define('Eway.controller.parameter.devParameter.DevParameter',{
	extend :'Eway.controller.base.FishController',
	
	stores :['parameter.devParameter.DevInfo','parameter.devParameter.ParamInfo'],
	models :['parameter.devParameter.DevInfo','parameter.devParameter.ParamInfo'],
	views  :['parameter.devParameter.View','Eway.view.parameter.devParameter.ParamGrid','Eway.view.parameter.devParameter.ParamView'],
	
	refs :[{
		ref:'ewayView',
		selector:'#parameter_devParameter_view',
		xtype :'parameter_devParameter_view',
		autoCreate:true,
		id:'parameter_devParameter_view'
	},{
		ref:'grid',
		selector:'parameter_devParameter_devGrid'
	},{
		ref:'paramGrid',
		selector:'parameter_devParameter_paramGrid'
	}],
	
	
	init : function(){
		this.control({
			'parameter_devParameter_devGrid button[action=query]' : {
				click :this.onQuery
			},
			'parameter_devParameter_paramGrid button[action=query]' : {
				click :this.onParamQuery
			}
		});
	},
	
	
	onParamQuery : function(){
		var view = this.getEwayView();
		var form = view.down('parameter_devParameter_paramFilterForm').getForm();
		var bool = form.isValid();
		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = view.down('parameter_devParameter_paramGrid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	}


	
	
	
});