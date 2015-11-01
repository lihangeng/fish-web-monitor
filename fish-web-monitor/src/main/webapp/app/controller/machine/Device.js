Ext.define('Eway.controller.machine.Device', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.Device', 'Hour', 'Minute', 'machine.DeviceAtmType',
			'machine.PersonM', 'machine.PersonTM',
			'machine.DeviceAwayFlagComboBox',
			'machine.atmType.DeviceAtmVendor',
			'machine.atmType.DeviceAtmCatalog' ],

	models : [ 'machine.Device', 'machine.Person' ],

	views : [ 'machine.device.View' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#device',
		autoCreate : true,
		xtype : 'device.view',
		id : 'device'
	}, {
		ref : 'grid',
		selector : 'device_grid'
	}, {
		ref : 'addWin',
		selector : 'device_info_add'
	}, {
		ref : 'tempGrid',
		selector : 'device_tempGrid'
	},{
		ref : 'updateWin',
		selector : 'device_info_update'
	}, {
		ref : 'filterForm',
		selector : 'device_filterform'
	} ],

	addView : 'Eway.view.machine.device.Add',
	updateView : 'Eway.view.machine.device.Update',
	infoView : 'Eway.view.machine.device.Info',

	formConfig : {
		form : 'Eway.view.machine.device.Form',
		xtype : 'machine_device_form',
		title : Eway.locale.machine.device.devInfo,
		width : 700,
		height : 450
	},

	init : function() {
		this.initBaseControl();
		this.control({
			'#device button[action=add]' : {
				click : this.onAdd
			},
			'#device button[action=query]' : {
				click : this.onQuery
			},
			'#device button[action=remove]' : {
				click : this.onRemove
			},
			'#device button[action=info]' : {
				click : this.onInfo
			},
			'#device button[action=update]' : {
				click : this.onUpdate
			},
			'#device button[action=export]' : {
				click : this.onExport
			},
			'#device button[action=tempDevQuery]' : {
				click : this.onTempDevQuery
			},
			'#device button[action=tempDevUpdate]' : {
				click : this.onTempDevUpdate
			},
			'#device button[action=tempDevDelete]' : {
				click : this.onTempDevDelete
			},
			'#device button[action=tempDevOpenPlan]' :{
				click : this.onTempDevOpenPlan
			}
		});
	},
	onExport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();

		// 查询输入验证
		if (bool == false) {
			return
		}
		var values = form.getValues();

		var params = "";
		if (values.address) {
			params += '&address=' + values.address;
		}
		if (values.awayFlag) {
			params += '&awayFlag=' + values.awayFlag;
		}
		if (values.devCatalogId) {
			params += '&devCatalogId=' + values.devCatalogId;
		}
		if (values.devService) {
			params += '&devService=' + values.devService;
		}
		if (values.devType) {
			params += '&devType=' + values.devType;
		}
		if (values.devVendorId) {
			params += '&devVendorId=' + values.devVendorId;
		}
		if (values.endCashboxLimit) {
			params += '&endCashboxLimit=' + values.endCashboxLimit;
		}
		if (values.ip) {
			params += '&ip=' + values.ip;
		}
		if (values.organization) {
			params += '&organization=' + values.organization;
		}
		if (values.terminalId) {
			params += '&terminalId=' + values.terminalId;
		}

		window.location.href = 'api/machine/device/export?_dc=' + params;
	},
	onInfo : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var win = Ext.create(this.infoView);

			var record = sm.getLastSelected();
			this.terminalId = record.get('terminalId');
			win.down('form').getForm().loadRecord(record);

			var tabPanel = win.down('tabpanel');

			// 维护员
			var maintain = tabPanel.query('[itemid=maintainItemID]')[0];

			// 管机员
			var tubeMachine = tabPanel.query('[itemid=tubeMachineItemID]')[0];

			maintain.on('render', this.renderMaintain, this);
			tubeMachine.on('render', this.renderTubeMachine, this);

			win.show();

		} else {
			Eway.alert(Eway.locale.tip.search.record);
		}
	},
	// 加载维护员的数据
	renderMaintain : function(tab) {
		var params = {
			terminalId : this.terminalId,
			type : 1
		};
		tab.onReload(params);
	},
	// 加载管机员的数据
	renderTubeMachine : function(tab) {
		var params = {
			terminalId : this.terminalId,
			type : 0
		};
		tab.onReload(params);
	},

	onAdd : function(){
	var win = Ext.create('Eway.view.machine.device.Add');
	this.win = win ;
	win.down('button[action = "add"]').on('click',this.onAddConfirm,this);
	win.down('field[name="devServiceId"]').setValue("2");
	win.down('field[name="devServiceName"]').setValue(Eway.yihua);
    //如果是维护商用户或者是admin用户登陆时设置银行机构为空。
	if(ewayUser.getOrgId() == 1 || ewayUser.getOrgType() == 1){
		win.down('field[name="orgId"]').setValue(null);
		win.down('field[name="orgName"]').setValue(null);
	//如果是银行机构登陆则默认显示当前机构
	}else{
		win.down('field[name="orgId"]').setValue(ewayUser.getOrgId());
		win.down('field[name="orgName"]').setValue(ewayUser.getOrgName());
	}
	win.show();
	},

	onAddConfirm: function(){
		var view = this.getEwayView();
		var win = this.win;
		var form = win.down('form');
		if(form.getForm().isValid()){
		var values = form.getValues();
		values.status = '1'; // status被设置为disable 没有值,故要手动添加
		var grid = this.getGrid();
		var store = grid.getStore();
		params = store.getUrlParams();		
		var me = this;
		var actionName =Eway.locale.button.add;		
		var record = Ext.create(store.getModelName(),values);
		oldParams = store.getUrlParams();
	     record.save({
			 success: function(recordInDB) {
				Eway.alert(actionName + Eway.locale.tip.success);
				win.close();				
				var store = this.getGridPanel().getStore();
				store.setUrlParamsByObject();
				store.loadPage(1);
				
			 },
			 failure: function(record,operation){
				store.setUrlParamsByObject(oldParams);
				Eway.alert(actionName + Eway.locale.tip.fail + operation.getError());
				store.rejectChanges();				
			 },
			 scope : this
		});		
		}	
	},
	onUpdate : function(){
	    var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		var count = sm.getCount();
		if(count == 0){
			Eway.alert(Eway.choiceUpdateMsg);
			return;
		}
		else if(count > 1){
			Eway.alert(Eway.locale.tip.update.one);
			return;
		}else{
			var win = Ext.create('Eway.view.machine.device.Update');
			var record = sm.getLastSelected();
			var form = win.down('form');
			var formValues=form.getForm().getValues(); 
			var effectiveDate = formValues["effectiveDate"];			
			form.loadCusRecord(sm.getLastSelected());		
			form.getForm().findField("effectiveDate").setValue(effectiveDate);
			var grid = this.getGrid();
			var record = grid.getSelectionModel().getLastSelected();
			win.down('button[action="update"]').on('click',this.onUpdateConfirm,this);
			win.show();
		}
	},

	onUpdateConfirm: function(){
		var view = this.getEwayView();
		var win  = this.getUpdateWin();
		var form = win.down('form');
		var grid = this.getGrid();
		var store = grid.getStore();
		actionName = Eway.locale.button.update;
		params = store.getUrlParams();
		oldParams = store.getUrlParams();
		if(form.getForm().isValid()){
			var me = this;
			var record = grid.getSelectionModel().getLastSelected();
			form.updateCusRecord(record);
			var effectiveDate = record.get('effectiveDate');
			record.set('effectiveDate', Ext.Date.format(effectiveDate, 'Y-m-d'))
			var effectiveDate = record.get('effectiveDate');
			var id = record.get("id");
			record.save({
				 success: function(recordInDB) {
					Eway.alert(actionName + Eway.locale.tip.success);
					win.close();					
					var resId = recordInDB.get("id");
					if(undefined==resId||""==resId||0==resId){
						recordInDB.set("id",id);
					}
						store.applyModel(recordInDB);					
				 },
				 failure: function(record,operation){
					store.setUrlParamsByObject(oldParams);
					Eway.alert(actionName + Eway.locale.tip.fail + operation.getError());
					store.rejectChanges();					
				 },
				 scope : this
			});		
			}
	},
	onTempDevQuery:function(){
		   var view = this.getEwayView();
		   var form = view.down('form').getForm();
		   var bool = form.isValid();
		   if(bool == false){
			   Ext.Msg.alert("提示","查询条件存在错误项.");
			   return;
		   }
		   var vaules = form.getValues();
		   var tempGrid= this.getTempGrid();
		   var store = tempGrid.getStore();
		   store.setUrlParamsByObject(vaules);
		   store.loadPage(1);
		},

		onTempDevUpdate : function(){
		    var grid = this.getTempGrid();
			var sm = grid.getSelectionModel();
			var count = sm.getCount();
			if(count == 0){
				Ext.Msg.alert("提示", "请选择您要更改的记录！");
				return;
			}
			else if(count > 1){
				Ext.Msg.alert("提示", "一次只能修改一条记录！");
				return;
			}else{
				var win = Ext.create('Eway.view.machine.device.Update');
				var record = sm.getLastSelected();
				var form = win.down('form');
				form.loadCusRecord(record);
				win.down('button[action="update"]').on('click',this.onUpdateTempDevConfirm,this);
				win.show();
			}
		},

		onUpdateTempDevConfirm: function(){
			var view = this.getEwayView();
			var win  = this.getUpdateWin();
			var form = win.down('form');
			var grid = this.getTempGrid();
			var store = grid.getStore();
			params = store.getUrlParams();
			actionName = Eway.locale.button.update;
			if(form.getForm().isValid()){
				var me = this;
				var record = grid.getSelectionModel().getLastSelected();
				form.updateCusRecord(record);
				var effectiveDate = record.get('effectiveDate');
				record.set('effectiveDate', Ext.Date.format(effectiveDate, 'Y-m-d'))
				var effectiveDate = record.get('effectiveDate');
				var id = record.get("id");	
			
				if(store.getModifiedRecords().length == 0){
					Ext.Msg.alert('提示','没有更改数据,请更改后再点击确定!');
				}else{					
					record.save({
						 success: function(recordInDB) {
							Eway.alert(actionName + Eway.locale.tip.success);
							win.close();					
							var resId = recordInDB.get("id");
							if(undefined==resId||""==resId||0==resId){
								recordInDB.set("id",id);
							}
								store.applyModel(recordInDB);	
								store.loadPage(1);
						 },
						 failure: function(record,operation){
							store.setUrlParamsByObject(oldParams);
							Eway.alert(actionName + Eway.locale.tip.fail + operation.getError());
							store.rejectChanges();					
						 },
						 scope : this
					});
					
				}
			}

		},
		

		onTempDevDelete : function(){
			var me = this;
			var view = this.getEwayView();
				grid = this.getTempGrid();
				store = grid.getStore();
				sm = grid.getSelectionModel();
				count = sm.getCount();
				params = store.getUrlParams();
			if(count == 0){
				Ext.Msg.alert("提示", "请选择您要删除的记录！");
				return;
			}
			else if(count > 1){
				Ext.Msg.alert("提示", "一次只能删除一条记录！");
				return;
			}
			Ext.MessageBox.confirm("请确认", "是否真的要删除指定的记录？",function(button,text){
				if(button == 'yes'){
					var record = sm.getLastSelected();
					record.erase({						
						success: function(record,operation){
							Eway.alert(Eway.deleteSuccess);
							me.onTempDevQuery();
						},
						failure: function(record,operation){
							Eway.alert(Eway.locale.tip.remove.error+ operation.getError());
						},
						scope:this
					});

				}
			});
		},
		onTempDevOpenPlan : function(){
			var grid = this.getTempGrid();
			var sm = grid.getSelectionModel();
			if(sm.getCount() ==1)
			{
				var win = Ext.create('Eway.view.operatingPlan.TempOpenPlanForDevWin');
				var planInfoGrid = win.down('temp_planofdevice_grid');
				var record = sm.getLastSelected();
				var store = planInfoGrid.getStore();
				store.load({
					params:{
						deviceId : record.data.id
					}
				});
				win.setTitle("设备"+record.data.terminalId+"开机方案");
				win.down('button[action="tempDevLink"]').on('click',Ext.bind(this.onDeviceLink,this,[planInfoGrid,grid,win]),this);
				win.down('button[action="tempDevUnlink"]').on('click',Ext.bind(this.onTempDevUnlink,this,[planInfoGrid,win]),this);
				win.down('button[action="tempDevQueryDetail"]').on('click',Ext.bind(this.onTempDevQueryDetail,this,[planInfoGrid]),this);
				win.show();
			}else
			{
				Ext.Msg.alert("提示", "请选择您要关联的设备！");
				return;
			}
		},
		onTempDevQueryDetail : function(planInfoGrid){
			var sm = planInfoGrid.getSelectionModel();
			if(sm.getCount() == 1) {
				var detailWin = Ext.create('Eway.view.operatingPlan.PlanInfoWin');
				var record = sm.getLastSelected();
				var store = detailWin.down('planInfo_grid').getStore();
				store.load({
					params : {
						openPlanId:record.data.id
					},
					callback: function(records, operation, success) {
				        if(Ext.isEmpty(records)){
				        	detailWin.close();
							Ext.Msg.alert("提示", "该方案无详细设置！");
				        }else{
				        	if(record.data.planType=="DATE"){
								detailWin.down('planInfo_grid').columns[0].hidden=true;
								detailWin.setTitle("方案详情(日期)");
								detailWin.show();
							}else{
								detailWin.setTitle("方案详情(星期)");
								detailWin.show();
							}
				        }
				    }
				});
			}else {
				Ext.Msg.alert("提示", "请选择您要查看的方案！");
			}
		},
		
		onTempDevUnlink :function(planInfoGrid,win){
			var sm = planInfoGrid.getSelectionModel();
			if(sm.getCount() == 1)
			{
				var record_openPlan = sm.getLastSelected();
				Ext.Ajax.request({
					scope : this,
					method : 'POST',
					url : 'api/srcb/plan/tempunlinkplan',
					params : {
						planId :record_openPlan.data.id,
						tempDeviceId:record_openPlan.data.deviceId
						},
					success: function(response){
						var object = Ext.decode(response.responseText);
						if(object.success == true){
							win.close();
							this.onTempDevOpenPlan();
						}else{
							Ext.Msg.alert("提示", "解除失败！");
						}
					},
					failure: function(response){
						Ext.Msg.alert("提示", "解除失败！");
					},
					scope:this

				})
			}
		},
		
		onTempDeviceLinkOpenPlan : function(viewWin,tempGrid,win){
			var openPlanGrid = viewWin.down('grid');
			var sm_openPlan = openPlanGrid.getSelectionModel();
			var sm_dev = tempGrid.getSelectionModel();
			if (sm_openPlan.getCount() == 1&&sm_dev.getCount() == 1) {
				var record_openPlan = sm_openPlan.getLastSelected();
				var record_dev = sm_dev.getLastSelected();
				Ext.Ajax.request({
					scope : this,
					method : 'POST',
					url : 'api/srcb/plan/tempdevlinkplan',
					params : {planId :record_openPlan.data.id,tempDeviceId:record_dev.data.id},
					success: function(response){
						var object = Ext.decode(response.responseText);
						if(object.success == true){
							viewWin.close();
							win.close();
							this.onTempDevOpenPlan();
						}else{
							Ext.Msg.alert("提示", "关联失败！");
						}
					},
					failure: function(response){
						Ext.Msg.alert("提示", "关联失败！");
					},
					scope:this
				});
			}else{
				Ext.Msg.alert("提示", "请选择您要关联的记录.");
			}
		}
});