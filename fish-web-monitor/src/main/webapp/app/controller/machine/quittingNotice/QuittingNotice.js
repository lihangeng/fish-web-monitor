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
			Eway.alert(EwayLocale.vtype.choseDev);
		}
	},

	onAddConfirm : function() {		
		var win = this.win;
		var form = win.down('form');
		var data = win.down('form').getForm().getValues();
		var view = this.getEwayView();
		var quaryData = view.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.machine.quittingNotice.QuittingNotice',data);
		record.set('stopTime',data['stopTime'] + " 00:00:00");
		if(data['openTime'] !='' && data['openTime'] != null)
		{
			record.set('openTime',data['openTime'] + " 00:00:00");
		}		
		var store = this.getEwayView().down('gridpanel').getStore();
		if(win.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.add(record);
					win.close();
					Eway.alert(EwayLocale.addSuccess);
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
			var stopTime = record.get('stopTime');
			var openTime = record.get('openTime');
			var date = Ext.Date.format(new Date(),'Y-m-d');
			win.down('form').getForm().loadRecord(record);
			itemStopTime = win.down('form').getForm().findField("stopTime");
			itemOpenTime = win.down('form').getForm().findField("openTime");
			var OpenDate = null;
			if(openTime != null)
			{
			   var openYear = openTime.substring(0,4);
		       var openMonth = openTime.substring(5,7);
		       var openDay = openTime.substring(8,10);
	           OpenDate = new Date(openYear,openMonth,openDay);
			}
			   var nowYear = date.substring(0,4);
	           var nowMonth = date.substring(5,7);
	           var nowDay = date.substring(8,10);
	           nowDate = new Date(nowYear,nowMonth,nowDay);
			   if(OpenDate != null && OpenDate.getTime() <= nowDate.getTime())
	            {
	            	Ext.Msg.alert("提示", "不能对已执行完成的报停记录进行修改.");

	            }
	            else
	            {
	            	if(stopTime != null)
	    			{
	    				itemStopTime.setValue(stopTime.substring(0,10));
	    			}
	    			if(openTime != null)
	    			{
	    				itemOpenTime.setValue(openTime.substring(0,10));
	    			}
	    			var button = win.query('button[action=confirm]')[0];
	    			this.win = win;
	    			button.on('click', this.onUpdateConfirm, this);
	    			win.show();
	            }		
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
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
		var me = this;
		if(win.down('form').getForm().isValid()){
			var oldId = record.get("id");
			record.set("deviceCode",data.deviceCode);		
			record.set("stopTime",data.stopTime + " 00:00:00");
			if(data['openTime'] !='' && data['openTime'] != null)
			{
				record.set('openTime',data['openTime'] + " 00:00:00");
			}		
			record.set("setTime",data.setTime);
			record.set("stopType",data.stopType);
			record.set("stopReason",data.stopReason);
			record.set("devStatus",data.devStatus);
			record.set("responsibilityName",data.responsibilityName);
			record.save({
				success : function(record,operation){
					Eway.alert(EwayLocale.updateSuccess);
					record.set("id",oldId);
					store.applyModel(record);
					win.close();
					me.onQuery();
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
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.updateSuccess);
									grid.getStore().remove(record);
									store.setUrlParamsByObject(quaryData);
									store.setBaseParam('organizationID',ewayUser.getOrgId());
									store.loadPage(1);
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