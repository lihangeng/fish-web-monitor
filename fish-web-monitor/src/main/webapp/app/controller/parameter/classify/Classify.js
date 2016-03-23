Ext.define('Eway.controller.parameter.classify.Classify', {
	extend : 'Eway.controller.base.FishController',

	stores : ['parameter.classify.Classify'],
	models : ['parameter.classify.Classify'],

	views : ['parameter.classify.View',
	            'parameter.classify.FilterForm',
	            'parameter.classify.Grid'],

	refs : [{
		ref : 'ewayView',
		selector : 'classify_View',
		autoCreate : true,
		xtype : 'classify_View'
	},{
		ref : 'classifyFilterForm',
		selector : 'classify_FilterForm'
	},{
		ref : 'classifyGrid',
		selector : 'classify_Grid'
	}
//					, {
//						ref : 'addWin',
//						selector : 'atmBrand_add'
//					}, {
//						ref : 'updateWin',
//						selector : 'atmBrand_update'
//					}
	],
	

	init : function() {
		this.control({
			'classify_View button[action=query]' : {
					click : this.onQuery
			},
			'classify_View button[action=add]' : {
					click : this.onAdd,
			},
			'classify_View button[action=update]' : {
					click : this.onUpdate,
			},
			'classify_View button[action=remove]' : {
					click : this.onRemove
			}
		});
	},
	
	// 根据条件查询组信息：
	onQuery : function(){
		var store = this.getEwayView().down('classify_Grid').getStore();
		var data = this.getEwayView().down('form').getForm().getValues();// 得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		// store.loadPage(1);
	},
			
	onAdd : function(){
		var groupAddWin = Ext.create('Eway.view.parameter.classify.ClassifyAdd');
		groupAddWin.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[groupAddWin]),this);
		groupAddWin.show();
	},
	
	onAddConfirm : function(){
		
	},
	
	onUpdate : function(){
		var grid = this.getEwayView().down('classify_Grid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.parameter.classify.ClassifyUpdate');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			win.down('button[action="update"]').on('click',Ext.bind(this.onUpdateConfirm,this,[win]),this);
			win.show();
		} else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},
	
	onUpdateConfirm : function(){
		
	}

});