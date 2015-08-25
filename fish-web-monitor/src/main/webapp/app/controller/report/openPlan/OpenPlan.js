/**
 *  开机方案--controller
 */

Ext.define('Eway.controller.report.openPlan.OpenPlan', {
	extend : 'Eway.controller.base.FishController',

	stores : ['report.plan.Plan','report.plan.LinkedDevice','report.plan.LinkingDevice'],

	models : ['report.plan.Plan','report.plan.LinkDevice','report.plan.PlanDevice'],

	views : ['Eway.view.report.plan.View'],

	refs : [ {
		ref : 'ewayView',
		selector : 'plan_view',
		autoCreate : true,
		xtype : 'plan_view'
	}, {
		ref : 'filterForm',
		selector : 'plan_filterForm'
	}, {
		ref : 'Grid',
		selector : 'plan_grid'
	} ],

	init : function() {
		this.control({
			'plan_grid button[action=query]' :{
				click : this.onQuery
			},
			'plan_grid button[action=remove]': {
				click: this.onRemove
			},
			'plan_grid button[action=add]': {
				click: this.onAdd
			},
			'plan_grid button[action=update]': {
				click: this.onUpdate
			},
			'plan_grid button[action=link]': {
				click: this.onLink
			}
		});
	},

	/**
	 * 关联设备管理
	 */
	onLink : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var now = Ext.Date.format(new Date(), 'Y-m-d');
			var record = sm.getLastSelected();
			var PlanDeviceWin = Ext.create('Eway.view.report.plan.PlanDevice');
			var linkedDeviceGrid = PlanDeviceWin.down('plan_linkedDeviceGrid');
			var actionTip1 = linkedDeviceGrid.down("tbtext[action=tip]");
			actionTip1.setText("已关联的设备");
			linkedDeviceGrid.getStore().load({
				params : {
					flag:0,
					planId:record.data.id
				}
			});
			var linkingDeviceGrid = PlanDeviceWin.down('plan_linkingDeviceGrid');
			var actionTip2 = linkingDeviceGrid.down("tbtext[action=tip]");
			actionTip2.setText("可关联的设备");
			linkingDeviceGrid.getStore().load({
				params : {
					flag:1,
					planId:record.data.id
				}
			});
			if(record.data.startDate<now){
				actionTip1.setText('已关联的设备'+'<font color="red">'+'(此方案已过期，不可应用！)'+'</font>');
				actionTip2.setText('可关联的设备'+'<font color="red">'+'(此方案已过期，不可应用！)'+'</font>');
				linkedDeviceGrid.down('button[action="unlink"]').setDisabled(true);
				linkingDeviceGrid.down('button[action="link"]').setDisabled(true);
			}
			linkedDeviceGrid.down('button[action="unlink"]').on('click',
					Ext.bind(this.onUnlinkConfirm,this,[linkedDeviceGrid,linkingDeviceGrid,record]),this
				);
			linkingDeviceGrid.down('button[action="link"]').on('click',
					Ext.bind(this.onLinkConfirm,this,[linkingDeviceGrid,linkedDeviceGrid,record]),this
				);
			linkingDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.refleshDevice,this,[linkingDeviceGrid.down('pagingtoolbar'),1,record]),this
				);
			linkedDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.refleshDevice,this,[linkedDeviceGrid.down('pagingtoolbar'),0,record]),this
				);
			PlanDeviceWin.show();
		}
		else {
			Eway.alert("请选择您应用的方案.");
		}
	},

	refleshDevice : function(pagingtoolbar,flag,record){
		pagingtoolbar.store.proxy.extraParams = {
			flag:flag,
			planId:record.data.id
		};
	},

	/**
	 * 获得选中的行id数组：
	 * @param {} grid
	 * @return {}
	 */
	multiSelect : function(grid){
		var record=grid.getSelectionModel().getSelection();
        if(record == null || record.length == 0){
        	return null;
        }
        var array = new Array(record.length);
        for(var i=0;i<record.length;i++)
        {
            array[i] = record[i].get('id');
        }
        return array;
	},


	/**
	 * 解除关联
	 * @param {} linkedDeviceGrid
	 * @param {} linkingDeviceGrid
	 * @param {} record
	 */
	onUnlinkConfirm : function(linkedDeviceGrid,linkingDeviceGrid,record){
		var array = this.multiSelect(linkedDeviceGrid);
		if(array != null) {
			var info = '';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/report/plan/unlink',
				params : {planId :record.data.id,deviceId:info},
				success: function(){
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							planId:record.data.id
						}
					});
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							planId:record.data.id
						}
					});
				},
				failure: function(){
					Eway.alert("解除失败.");
				},
				scope:this
			});
		}else{
			Eway.alert("请选择要解除的设备.");
		}
	},

	/**
	 * 进行关联
	 * @param {} linkingDeviceGrid
	 * @param {} linkedDeviceGrid
	 * @param {} record
	 */
	onLinkConfirm : function(linkingDeviceGrid,linkedDeviceGrid,record){
		var array = this.multiSelect(linkingDeviceGrid);
		if(array != null) {
			var data = new Object();
			var record2 = Ext.create('Eway.model.report.plan.PlanDevice',data);
			for(var i=0;i<array.length-1;i++){
            	record2.set('id',0);
				record2.set('planId',record.data.id);
				record2.set('deviceId',array[i]);
				record2.save();
            }
			record2.set('id',0);
			record2.set('planId',record.data.id);
			record2.set('deviceId',array[array.length-1]);
			record2.save({
				success: function(){
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							planId:record.data.id
						}
					});
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							planId:record.data.id
						}
					});
				},
				failure: function(){
					Eway.alert("关联失败.");
				},
				scope:this
			});
		}else{
			Eway.alert("请选择要关联的设备.");
		}
	},

	onAdd: function() {
		var addWin = Ext.create('Eway.view.report.plan.Add');
		addWin.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[addWin]),this);
		addWin.show();
	},

	onAddConfirm: function(addWin) {
		data = addWin.down('form').getForm().getValues();
		var name = data.name;
		data.name = Ext.String.trim(name);
		var record = Ext.create('Eway.model.report.plan.Plan',data);
		var view = this.getEwayView();
		var store = view.down('plan_grid').getStore();
		if(addWin.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					addWin.close();
					Eway.alert(Eway.addSuccess);
					store.add(record);
				},
			    failure: function(record,operation){
					Eway.alert(operation.getError());
				}
			});
		}
	},

	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var values = view.down('plan_filterForm').getForm().getValues();
		var store = view.down('plan_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	onUpdate : function(){
		var grid = this.getEwayView().down('plan_grid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.report.plan.Update');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			var now = Ext.Date.format(new Date(), 'Y-m-d');
			if(record.data.startDate>=now){
				win.down('button[action="update"]').on('click',Ext.bind(this.onUpdateConfirm,this,[win]),this);
				win.show();
			}else{
				Eway.alert("该方案已经执行,不可修改.");
			}
		}
		else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	onUpdateConfirm : function(win) {
		var ewayView = this.getEwayView();
		var sm = ewayView.down('plan_grid').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var store = ewayView.down('plan_grid').getStore();
		if(win.down('form').getForm().isValid()){
			record.set("name",Ext.String.trim(data.name));
			record.set("startDate",data.startDate);
			record.set("endDate",data.endDate);
			record.set("note",data.note);
			record.save({
				success : function(record,operation){
					Eway.alert(Eway.updateSuccess);
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

	/**
	 * 删除标签：
	 */
	onRemove: function() {
		var view = this.getEwayView();
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var now = Ext.Date.format(new Date(), 'Y-m-d');
			if(record.data.startDate>=now){
				Ext.MessageBox.confirm("请确认",
						"是否删除该记录?",
						function(button,text) {
							if(button=="yes"){
								var record = sm.getLastSelected();
								record.erase({
									success: function(){
										Eway.alert(Eway.deleteSuccess);
										grid.getStore().remove(record);
									},
									failure: function(record,operation){
										Eway.alert(operation.getError());
									},
									scope:this
								});
							}
						}, this);
			}else{
				Eway.alert("该方案已经执行,不可删除.");
			}
		}
		else {
			Eway.alert(Eway.choiceDeleteMsg);
		}
	}

});