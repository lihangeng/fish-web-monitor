Ext.define('Eway.controller.cash.boxInfo.CashBoxInfo', {
	extend : 'Ext.app.Controller',

	stores : ['cash.boxInfo.CashBoxInfo','machine.DeviceAtmType'
	          ,'machine.DeviceAwayFlagComboBox',
				'machine.atmType.DeviceAtmVendor'],
	models : ['cash.boxInfo.CashBoxInfo'],
	views : ['cash.boxInfo.View','cash.boxInfo.FilterForm','cash.boxInfo.Grid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		autoCreate : true,//必须
		xtype : 'boxInfo_View'//必须
	}, {
		ref : 'grid',
		selector : 'boxInfo_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'boxInfo_filterForm'
	}],

	init : function() {
		 this.control({
			'boxInfo_grid button[action=query]' : {
				click : this.onQuery
			},
			'boxInfo_grid button[action=remove]' : {
				click : this.onRemove
			},
			'boxInfo_grid' : {
				edit : this.onUpdate
			},
			'boxInfo_grid  button[action=cashBoxInfoSync]':{
				click:this.sync
			}
		});
	},
	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('boxInfo_filterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('boxInfo_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	onRemove:function(){
		
	},
	sync:function(btn){
		var record = btn.getWidgetRecord();	
		Ext.Ajax.request({
			url :"api/cashbox/synchronizedBoxLimit",
			params: {
		        id: record.data.id
		    },
			success: function(response){
				 var obj = Ext.decode(response.responseText);
				 if(obj.success){
				 	Eway.alert("同步成功");
				 }else{
					 
				 }
			}
		});
	},
	
	onUpdate:function(editor, context){
		context.record.save({
			 success: function(recordInDB) {
					Eway.alert("更改成功");
				 },
				 failure: function(record,operation){
					store.rejectChanges();
				 },
				 scope : this
			});
	}
	
});
