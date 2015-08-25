
Ext.define('Eway.controller.case.CaseFault', {
	extend: 'Ext.app.Controller',
	
	stores: ['case.Fault','case.DevMod','case.FaultStatus','case.FaultClassify'],
			
	models: ['case.Fault','case.FaultClassify'],
			
	views: ['case.caseFault.FaultView'],
	
	refs: [{
		ref: 'ewayView',
		selector: '#fault',
		autoCreate: true,
		xtype: 'case_faultView',
		id: 'fault'
	}, {
		ref: 'grid',
		selector: 'caseFault_faultGrid'
	},{
		ref :'filterForm',
		selector:'caseFault_filterForm'
	}],
	
	init: function() {
		this.control({
			'caseFault_faultGrid button[action=query]': {
				click: this.onQuery
			},
			'caseFault_faultGrid button[action=export]': {
				click: this.onExport
			}
		});
		this.onQuery();
	},
	
	/**
	 * 根据条件查询
	 */
	onQuery: function(){
		var store = this.getEwayView().down('caseFault_faultGrid').getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},
	
	onExport : function(){
		var data = this.getFilterForm().getForm().getValues();
		var terminalId = data.terminalId;
		var closedTime = data.closedTime;
		var devMod = data.devMod;
		var faultClassify = data.faultClassify;
		var faultStatus = data.faultStatus;
		var faultTime = data.faultTime;
		
		window.location.href = 'api/case/caseFault/export?devMod=' + devMod + '&terminalId=' + terminalId
														+ '&closedTime=' + closedTime + '&faultClassify=' + faultClassify
														+ '&faultStatus=' + faultStatus + '&faultTime=' + faultTime;
		
	}
	
});