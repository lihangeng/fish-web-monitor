Ext.define('Eway.controller.machine.quittingNotice.QuittingNotice', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.quittingNotice.StopTypeDict','machine.quittingNotice.QuittingNotice','machine.quittingNotice.DevStatusDict','machine.quittingNotice.DeviceCode'],

	models : [ 'Dict','machine.quittingNotice.QuittingNotice','machine.quittingNotice.DeviceCode'],

	views : ['machine.quittingNotice.QuittingNoticeView' ],

	refs : [ {
		ref : 'ewayView',
		selector : '#quittingNotice',
		autoCreate : true,
		xtype : 'quittingNotice_quittingNoticeView',
		id : 'quittingNotice'
	}, {
		ref : 'quittingNoticeFilterForm',
		selector : 'quittingNotice_FilterForm'
	}, {
		ref : 'addWin',
		selector : 'quittingNotice_add'
	}, {
		ref : 'updateWin',
		selector : 'quittingNotice_update'
	}, {
		ref : 'quittingNoticeGrid',
		selector : 'quittingNotice_quittingNoticeGrid'
	} ],

	addView : 'Eway.view.machine.quittingNotice.Add',
	updateView : 'Eway.view.machine.quittingNotice.Update',

	init : function() {
		this.control({
			'#quittingNotice button[action=query]':{
			 	click:this.onQuery
			},
			'#quittingNotice button[action=add]' : {
				click : this.onAdd
			},
			'#quittingNotice button[action=remove]' : {
				click : this.onRemove
			},
			'#quittingNotice button[action=update]' : {
				click : this.onUpdate
			}
		});

	},

	onAdd : function(){
		var win = Ext.create(this.addView);
		var button = win.query('button[action=confirm]')[0];
		this.win = win;
		button.on('click', this.onAddConfirm, this);
		win.show();
	},

	onSelectDevice: function() {
		var win = Ext.create('Eway.view.machine.quittingNotice.DeviceWin');
		var deviceGrid = win.down('quittingNotice_DeviceGrid');
		deviceGrid.getStore().cleanUrlParam();
		deviceGrid.getStore().setBaseParam('organizationID',ewayUser.getOrgId());
		deviceGrid.down('button[action="select"]').on('click',Ext.bind(this.onCreateConfirm,this,[win]),this);
		deviceGrid.down('button[action="queryDevice"]').on('click',Ext.bind(this.onQueryDevice,this,[win]),this);
		deviceGrid.on('itemdblclick',Ext.bind(this.onCreateConfirm,this,[win]),this);
		win.show();
		deviceGrid.getStore().loadPage(1);
	},

	onQueryDevice : function(win){
		var store = win.down('quittingNotice_DeviceGrid').getStore();
		var data = win.down('quittingNotice_DeviceFilterForm').getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('organizationID',ewayUser.getOrgId());
		store.loadPage(1);
	},

	onCreateConfirm: function(win) {
		var deviceGrid = win.down('quittingNotice_DeviceGrid');
		var sm = deviceGrid.getSelectionModel();
		if(sm.getCount() == 1) {
			this.win.down('field[name="deviceCode"]').setValue(sm.getLastSelected().data.terminalId);
			win.close();
		}else{
			Eway.alert('请选择您报停的设备.');
		}
	},

	onAddConfirm : function() {
		var win = this.win;
		var form = win.down('form');
		var data = win.down('form').getForm().getValues();
		var view = this.getEwayView();
		var quaryData = view.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.machine.quittingNotice.QuittingNotice',data);
		var store = this.getEwayView().down('gridpanel').getStore();
		if(win.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.add(record);
					win.close();
					Eway.alert(Eway.addSuccess);
					//点击增加成功后查询条件不带入重新查询。
					store.setUrlParamsByObject(null);
					store.setBaseParam('organizationID',ewayUser.getOrgId());
					store.loadPage(1);
			    },
			    failure: function(record,operation){
					Eway.alert(operation.getError());
				}
			});
		}
	},

	onUpdate: function() {
		var ewayView = this.getEwayView();
		var grid = ewayView.down('gridpanel');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create(this.updateView);
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			var button = win.query('button[action=confirm]')[0];
			this.win = win;
			button.on('click', this.onUpdateConfirm, this);
			win.show();
		}
		else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	onUpdateConfirm: function() {
		var win = this.win;
		var sm = this.getEwayView().down('gridpanel').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var view = this.getEwayView();
		var quaryData = view.down('form').getForm().getValues();
		var store = this.getEwayView().down('gridpanel').getStore();
		if(win.down('form').getForm().isValid()){
			var oldId = record.get("id");
			record.set("deviceCode",data.deviceCode);
			record.set("openTime",data.openTime);
			record.set("stopTime",data.stopTime);
			record.set("setTime",data.setTime);
			record.set("stopType",data.stopType);
			record.set("stopReason",data.stopReason);
			record.set("devStatus",data.devStatus);
			record.set("responsibilityName",data.responsibilityName);
			record.save({
				success : function(record,operation){
					Eway.alert(Eway.updateSuccess);
					record.set("id",oldId);
					store.applyModel(record);
					win.close();
				},
				failure: function(record,operation){
					Eway.alert(operation.getError());
					//解决脏数据
					store.rejectChanges();
				}

			});
		}
	},

	onRemove: function() {
		var grid = this.getQuittingNoticeGrid();
		var sm = grid.getSelectionModel();
		var view = this.getEwayView();
		var quaryData = view.down('form').getForm().getValues();
		var store = this.getEwayView().down('gridpanel').getStore();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm("请确认",
					"是否删除该记录?",
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(Eway.deleteSuccess);
									grid.getStore().remove(record);
									store.setUrlParamsByObject(quaryData);
									store.setBaseParam('organizationID',ewayUser.getOrgId());
									store.loadPage(1);
								},
								failure: function(record,operation){
									Eway.alert(operation.getError());
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(Eway.choiceDeleteMsg);
		}
	}
});