Ext.define('Eway.controller.cash.boxInfo.CashInitPlan', {
	extend : 'Eway.controller.base.FishController',

	stores : ['cash.initPlan.CashInitPlan','machine.DeviceAtmType',
				'machine.DeviceAwayFlagComboBox'],
	models : ['cash.initPlan.CashInitPlan'],
	views : ['cash.initPlan.View','cash.initPlan.FilterForm','cash.initPlan.Grid'
	         ,'cash.initPlan.AddDeviceWin','cash.initPlan.DetailSelectableGrid'
	         ,'cash.initPlan.DetailSelectableFilterForm'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		autoCreate : true,//必须
		xtype : 'initPlan_View'//必须
	}, {
		ref : 'grid',
		selector : 'initPlan_grid'
	}, {
		ref : 'detailGrid',
		selector : 'initPlan_detailGrid'
	},  {
		ref : 'detailForm',
		selector : 'initPlan_detailFilterForm'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'initPlan_filterForm'
	},{
		ref : 'initPlanAddDevice',
		selector: 'initPlan_AddDeviceWin'
	},{
		ref : 'detailSelectableForm',
		selector : 'initPlan_detailSelectableFilterForm'
	}, {
		ref : 'initPlanSelectableGrid',
		selector: 'initPlan_detailSelectableGrid'
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
				edit : this.onEdit
			},
			'initPlan_detailGrid  button[action=add]' : {
				click : this.onAddInitPlanDevice
			},
			'initPlan_detailGrid  button[action=export]' : {
				click : this.onInitPlanDetailExport
			},
			'initPlan_detailGrid  button[action=remove]' : {
				click : this.onDeleteCashInitPlanDeviceInfo
			},
			'initPlan_detailGrid  button[action=next]' : {
				click : this.nextCashInitPlan
			},
			'initPlan_detailGrid  button[action=pref]' : {
				click : this.prefCashInitPlan
			},
			'initPlan_detailSelectableGrid  button[action=query]' : {
				click : this.onDetailSelectableQuery
			}
		});
	},
	//下一个加钞计划
	nextCashInitPlan:function(){
		var planField = this.getDetailForm().down("hidden[name=cashInitPlanInfoId]");
		var planId = planField.getValue();
		var detailGrid = this.getDetailGrid();
		var  me =this;
		Ext.Ajax.request({
		    url: 'api/cashInitPlan/nextCashInitPlanId',
	    	params: {
	    		planId: planId
		    },
		    success: function(response){
		    	var obj = Ext.decode(response.responseText);
		    	if(obj.success){
		    		var planInfo = obj.data;
		    		planField.setValue(planInfo.id);
		    		detailGrid.setTitle(planInfo.orgName+EwayLocale.initPlan.cashInitCode+planInfo.cashInitCode);
			    	me.onDetailQuery();
		    	}
		    	else{
		    		Eway.alert(obj.errorMsg);
		    	}
		    }
		});
	},
	//上一个加钞计划
	prefCashInitPlan:function(){
		var planField = this.getDetailForm().down("hidden[name=cashInitPlanInfoId]");
		var planId = planField.getValue();
		var detailGrid = this.getDetailGrid();
		var  me =this;
		Ext.Ajax.request({
		    url: 'api/cashInitPlan/prefCashInitPlanId',
	    	params: {
	    		planId: planId
		    },
		    success: function(response){
		    	var obj = Ext.decode(response.responseText);
		    	if(obj.success){
		    		var planInfo = obj.data;
		    		planField.setValue(planInfo.id);
		    		detailGrid.setTitle(planInfo.orgName+EwayLocale.initPlan.cashInitCode+planInfo.cashInitCode);
			    	me.onDetailQuery();
		    	}
		    	else{
		    		Eway.alert(obj.errorMsg);
		    	}
		    }
		});
	},
	//加钞计划明细导出
	onInitPlanDetailExport:function(){
		var planId = this.getDetailForm().down("hidden[name=cashInitPlanInfoId]").getValue();
		var params="&cashInitPlanInfoId="+planId;
		
		var columns = this.getDetailGrid().getColumns();
		params = this.getToExcel(columns,params);
		window.location.href = 'api/cashInitPlanDevice/export?_dc=' + params;
	},
	//加钞计划增加设备的查询条件
	onDetailSelectableQuery:function(){
		var form = this.getDetailSelectableForm().getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = this.getInitPlanSelectableGrid().getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	//删除加钞计划中的设备信息
	onDeleteCashInitPlanDeviceInfo:function(){
		var me =this;
		var grid = this.getDetailGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							record.erase({
								success: function(recordInDB){
									grid.getStore().remove(record);
									Eway.alert(EwayLocale.deleteSuccess);
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									var object = Ext.decode(operation._response.responseText);
									record.dropped = false;
									Eway.alert(object.errorMsg);
								},
								scope:this
							});
						}
			}, this);
		}
		else {
			Eway.alert(EwayLocale.tip.remove.none);
		}
	},
	//向加钞计划中添加设备
	onAddInitPlanDevice:function(){
		var me = this;
		var win = Ext.create("Eway.view.cash.initPlan.AddDeviceWin");
		var store = win.down("initPlan_detailSelectableGrid").getStore();
		var planId = this.getDetailForm().down("hidden[name=cashInitPlanInfoId]").getValue();
		win.down("initPlan_detailSelectableFilterForm hidden[name=cashInitPlanInfoId]").setValue(planId);
		var form = win.down("initPlan_detailSelectableFilterForm").getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		store.setUrlParamsByObject(values);
		store.load();
		win.down('button[action="confirm"]').on('click', Ext.bind(me.saveConfirm, me, [win]), me);
		win.show();
	},
	//确认加钞计划新增设备
	saveConfirm:function(win){
		var me = this;
		var form = win.down("initPlan_detailSelectableFilterForm").getForm();
		var terminalIdsField = form.findField("terminalIds");
		var cashInitPlanInfoId = form.findField("cashInitPlanInfoId");
		var terminlIds = terminalIdsField.value;
		Ext.Ajax.request({
		    url: 'api/cashInitPlanDevice/addDevice',
	    	params: {
	    		terminalIds: terminlIds,
	    		cashInitPlanInfoId:cashInitPlanInfoId.value
		    },
		    success: function(response){
		    	var obj = Ext.decode(response.responseText);
		    	if(obj.success){
			    	win.close();
			    	me.onDetailQuery();
		    	}
		    	else{
		    		Eway.alert(obj.errorMsg);
		    	}
		    },
		    failure: function(response, opts) {
		        Eway.alert(EwayLocale.initPlan.addDeviceFailer);
		    }
		});
	},
    //更改额度
    onEdit:function(editor, context){
    	var maxAmt = context.record.get("maxAmt");
    	if(maxAmt<context.newValues.actualAmt&&maxAmt!=-1){
    		Eway.alert(EwayLocale.initPlan.actualMaxAmt+context.record.get("maxAmt"));
    		return false;
    	}
		context.record.save({
			 	success: function(recordInDB) {
					Eway.alert(EwayLocale.updateSuccess);
				 },
				 failure: function(record,operation){
					 var obj = Ext.decode(operation._response.responseText);
					 Eway.alert(obj.errorMsg);
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
		this.onQuery();
	},
	onUpdate:function(){
		var view = this.getEwayView(),
		grid = this.getGrid(),
		detailGrid = this.getDetailGrid(),
		detailFilterForm = this.getDetailForm(),
		sm = grid.getSelectionModel(),
		count = sm.getCount();
		if(count == 0){
			Eway.alert(EwayLocale.initPlan.chooseOne);
			return;
		}
		var record = sm.getLastSelected();
		view.getLayout().setActiveItem("initDetailPlan");
		
		var cashPlanInitId = detailFilterForm.down("hidden[name=cashInitPlanInfoId]").setValue(record.get("id"));
		var detailStore = detailGrid.getStore();
		detailGrid.setTitle(record.get("orgName")+EwayLocale.initPlan.cashInitCode+record.get("cashInitCode"));
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
