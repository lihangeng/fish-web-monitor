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
	},{
		ref : 'addWin',
		selector : 'classify_ClassifyAdd'
	},{
		ref : 'updateWin',
		selector : 'classify_ClassifyUpdate'
	}],

//	formConfig : {
//		form : 'Eway.view.parameter.classify.Form',
//		xtype : 'parameter_element_form',
//		width: 500,
//		height:280,
//		title : // EwayLocale.machine.atmBrand.title
//	},

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
		store.loadPage(1);
	},
			
	onAdd : function(){
		var classifyAddWin = Ext.create('Eway.view.parameter.classify.ClassifyAdd');
		classifyAddWin.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[classifyAddWin]),this);
		classifyAddWin.show();
	},
	
	onAddConfirm : function(win){
		var ewayView = this.getEwayView();
		data = win.down('form').getForm().getValues();
		var name = data.name;
		data.name = Ext.String.trim(name);
		var record = Ext.create('Eway.model.parameter.classify.Classify',data);
		var store = this.getEwayView().down('classify_Grid').getStore();
		var quarydata = ewayView.down('form').getForm().getValues();// 得到所有的查询条件的值
		if(win.down('form').getForm().isValid()){// isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.add(record);
					win.close();
					//点击增加成功后查询条件不带入重新查询。
					store.setUrlParamsByObject(null);
					store.loadPage(1);
					Eway.alert(EwayLocale.addSuccess);
			    },
			    failure: function(record,operation){
			    	Eway.alert(operation.getError());
				},
				button:win.down('button[action="add"]')
			});
		}
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
	
	onUpdateConfirm : function(win){
		var ewayView = this.getEwayView();
		var sm = ewayView.down('classify_Grid').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var store = ewayView.down('classify_Grid').getStore();
		var quarydata = ewayView.down('form').getForm().getValues();// 得到所有的查询条件的值
		var id = record.get("id");
		if(win.down('form').getForm().isValid()){
			record.set("name",Ext.String.trim(data.name));
			record.set("remark",data.remark);
			record.save({
				success : function(recordResult,operation){
					Eway.alert(EwayLocale.updateSuccess);
					if(undefined==recordResult.get("id")||""==recordResult.get("id")||0==recordResult.get("id")){
						recordResult.set("id",id);
					}
					store.applyModel(recordResult);
					win.close();
				},
				failure: function(record,operation){
					Eway.alert(operation.getError());
					//解决脏数据
					store.rejectChanges();
				},
				button:win.down('button[action="update"]')
			});
		}
	},
	
	onRemove: function() {
		var grid = this.getEwayView().down('classify_Grid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
//			if(sm.getLastSelected().data.code=='admin'){
//				Eway.alert(EwayLocale.tip.user.remove.failRoot);
//				return;
//			}
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.info,
					EwayLocale.tip.user.remove.confirm,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									grid.getStore().remove(record);
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									record.dropped = false;
									Eway.alert(operation.getError());
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(EwayLocale.choiceDeleteMsg);
		}
	}
});