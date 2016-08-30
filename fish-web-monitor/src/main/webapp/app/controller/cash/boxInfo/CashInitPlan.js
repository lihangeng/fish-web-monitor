Ext.define('Eway.controller.cash.boxInfo.CashInitPlan', {
	extend : 'Ext.app.Controller',

	stores : ['cash.initPlan.CashInitPlan'],
	models : ['cash.initPlan.CashInitPlan'],
	views : ['cash.initPlan.View','cash.initPlan.FilterForm','cash.initPlan.Grid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		autoCreate : true,//必须
		xtype : 'initPlan_View'//必须
	}, {
		ref : 'grid',
		selector : 'initPlan_grid'//ComponentQuery的query规则
	}, {
		ref : 'detailGrid',
		selector : 'initPlan_detailGrid'//ComponentQuery的query规则
	}, {
		ref : 'detailForm',
		selector : 'initPlan_detailFilterForm'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'initPlan_filterForm'
	}],

	init : function() {
		 this.control({
			'initPlan_grid button[action=query]' : {
				click : this.onQuery
			},
			'initPlan_detailGrid button[action=query]' : {
				click : this.onDetailQuery
			},
			'initPlan_grid button[action=update]' : {
				click : this.onUpdate
			},
			'initPlan_detailGrid button[action=toPlan]' : {
				click : this.onBack
			},
			'initPlan_detailGrid' : {
				beforeedit : this.beforeEdit
			},
			'initPlan_detailGrid' : {
				edit : this.onEdit
			}
		});
	},
	beforeEdit:function( editor, context, eOpts ){
    	alert("beforeEdit");
    },
    onEdit:function(editor, context){

    	if(context.record.get("maxAmt")<context.newValues.actualAmt){
    		Eway.alert("更改失败最大额度为"+context.record.get("maxAmt"));
    		return false;
    	}
		context.record.save({
			 success: function(recordInDB) {
					Eway.alert("更改成功");
				 },
				 failure: function(record,operation){
					store.rejectChanges();
				 },
				 scope : this
			});
	},
	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('initPlan_filterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('initPlan_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	onDetailQuery:function(){
		var view = this.getEwayView();
		var form = view.down('initPlan_detailFilterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('initPlan_detailGrid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	onBack:function(){

		var view = this.getEwayView();
		view.getLayout().setActiveItem("initPlan");
	},
	onUpdate:function(){
		var view = this.getEwayView(),
		grid = this.getGrid(),
		detailGrid = this.getDetailGrid(),
		detailFilterForm = this.getDetailForm(),
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
		var record = sm.getLastSelected();
		view.getLayout().setActiveItem("initDetailPlan");
		
		var cashPlanInitId = detailFilterForm.down("hidden[name=cashInitPlanInfoId]").setValue(record.get("id"));
		var detailStore = detailGrid.getStore();
		detailStore.setBaseParam("cashInitPlanInfoId",record.get("id"));
		detailStore.load();
	},
	updateConfirm:function(button){
		var me = this,
		view = this.getEwayView(),
		win = button.up('window'),
		form = win.down('form');
		if(form.getForm().isValid()){//存在不合法的输入项
			var grid = me.getGrid(),
			store = grid.getStore(),
//			action = win.getAction(),
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
