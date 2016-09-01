Ext.define('Eway.controller.cash.boxInfo.AlarmRule', {
	extend : 'Ext.app.Controller',

	stores : ['cash.initRule.CashInitRule'],
	models : ['cash.initRule.CashInitRule'],
	views : ['cash.initRule.View','cash.initRule.FilterForm','cash.initRule.Grid','cash.initRule.UpdateWin'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		autoCreate : true,//必须
		xtype : 'initRule_View'//必须
	}, {
		ref : 'grid',
		selector : 'initRule_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'initRule_filterForm'
	}],

	init : function() {
		 this.control({
			'initRule_grid button[action=query]' : {
				click : this.onQuery
			},
			'initRule_grid button[action=update]' : {
				click : this.onUpdate
			}
		});
	},
	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('initRule_filterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('initRule_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	onUpdate:function(){
		var me = this;
		var grid = this.getGrid(),
		sm = grid.getSelectionModel(),
		count = sm.getCount();
		if(count == 0){
			Eway.alert(EwayLocale.choiceUpdateMsg);
			return;
		}
		else if(count > 1){
			Eway.alert(EwayLocale.tip.update.one);
			return;
		}
		var win = Ext.create('Eway.view.cash.initRule.UpdateWin');
		win.down('button[action="confirm"]').on('click',me.updateConfirm,me);
		var record = sm.getLastSelected();
		form = win.down('form').getForm();
		form.loadRecord(record);
		win.show();
	},
	updateConfirm:function(button){
		var me = this,
		view = this.getEwayView(),
		win = button.up('window'),
		form = win.down('form');
		if(form.getForm().isValid()){//存在不合法的输入项
			var grid = me.getGrid(),
			store = grid.getStore(),
			oldParams = store.getUrlParams(),
			record;
			store.cleanUrlParam();
			record = grid.getSelectionModel().getLastSelected();
			form.getForm().updateRecord(record);
			
			record.save({
				 success: function(recordInDB) {
					Eway.alert(EwayLocale.button.update +EwayLocale.tip.success);
					win.close();
					me.onQuery();
				 },
				 failure: function(record,operation){
					store.setUrlParamsByObject(oldParams);
					Eway.alert(EwayLocale.button.update+ EwayLocale.tip.fail + operation.getError());
					store.rejectChanges();
				 },
				 button:button,
				 scope : this
			});
			
		}
	}
});
