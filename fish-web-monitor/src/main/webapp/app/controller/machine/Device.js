Ext.define('Eway.controller.machine.Device', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.Device', 'Hour', 'Minute', 'machine.DeviceAtmType',
			'machine.PersonM', 'machine.PersonTM',
			'machine.DeviceAwayFlagComboBox',
			'machine.atmType.DeviceAtmVendor',
			'machine.atmType.DeviceAtmCatalog',
			'machine.DeviceStatusComboBox',
			'machine.CarrierOrdeComboBox',
			'operatingPlan.TempOpenPlanForDevice',
			'person.person.PersonStateFilterDict'
			
			],

	models : [ 'machine.Device', 'machine.Person' ],

	views : [ 'machine.device.View','operatingPlan.TempOpenPlanForDevGrid' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#device',
		autoCreate : true,
		xtype : 'device.view',
		id : 'device'
	}, {
		ref : 'grid',
		selector : 'device_grid'
	},  {
		ref : 'tempGrid',
		selector : 'device_tempGrid'
	},	{
		ref : 'addWin',
		selector : 'device_add'
	}, {
		ref : 'updateWin',
		selector : 'device_update'
	},  {
		ref : 'updateWin',
		selector:'device_info_update'
	},	{
		ref : 'filterForm',
		selector : 'device_filterform'
	},	{
		ref :'addWin',
		selector:'device_info_add'
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
			'#device button[action=openPlan]' : {
				click : this.onOpenPlan
			},
			'#device menuitem[action=personTM]' : {
				click : this.onPersonTM
			},
			'#device menuitem[action=personM]' : {
				click : this.onPersonM
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
	
	onPersonTM : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var addManagerWin = Ext.create('Eway.view.machine.device.person.PersonTMManager');
			var personGrid = addManagerWin.down('personTM_manager_grid');
			var personStore=personGrid.getStore();
			var type = 0;
			var terminalId = record.data.terminalId;
			//personStore.cleanUrlParam();
			personStore.load({
				params : {
					terminalId : terminalId,
					type : type
				}
			});
			personGrid.down('button[action="queryRe"]').on('click',Ext.bind(this.onQuaryPerson,this,[personStore,terminalId,type]),this);
			personGrid.down('button[action="add"]').on('click',Ext.bind(this.onSetPerson,this,[personStore,terminalId,type]),this);
			personGrid.down('button[action="remove"]').on('click',Ext.bind(this.onDelPersonConfirm,this,[personGrid,personStore,type]),this);
			addManagerWin.show();
		}
		else {
			Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.mustSelectDevice);
		}
	},
	
	onSetPerson: function (personStore,terminalId,type){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		if(sm1.getCount() == 1) {
		var selectManagerWin = Ext.create('Eway.view.machine.device.person.SelectPersonManager');
		var selectGrid = selectManagerWin.down('select_person_manager_grid');
		var selectForm = selectManagerWin.down('bank_person_filterform');
		var record1 = sm1.getLastSelected();
		var store = selectGrid.getStore();
		store.setBaseParam('deviceId',record1.data.id);
		store.setBaseParam('type',type);
		store.load();
		selectGrid.down('button[action="query"]').on('click',Ext.bind(this.onQueryPersonConfirm,this,[selectGrid,selectForm,type]),this);
		selectGrid.down('button[action="addselect"]').on('click',Ext.bind(this.onAddPersonConfirm,this,[selectManagerWin,selectGrid,selectForm,type]),this);
		selectManagerWin.on("destroy",Ext.bind(this.onQuaryPerson,this,[personStore,terminalId,type]));
		selectManagerWin.show();
		}else {
			Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.mustSelectPerson);
		}
	},
	
	onAddPersonConfirm : function(win,selectGrid,selectForm,personType){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var record1 = sm1.getLastSelected();
		var array = this.multiSelect(selectGrid);
		if(array != null) {
			var info = '';
			var type = 'add';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/person/person/devicePerson',
				params : {terminalId :record1.data.terminalId,personId:info,type:type,personType:personType},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						//Ext.Msg.alert("提示", "添加成功.",this.onQueryPersonConfirm(selectGrid,selectForm,personType));
						Ext.Msg.confirm(Eway.locale.confirm.title, Eway.locale.msg.savaSuccess, function(result) {
							if ("yes" == result) {
								this.onQueryPersonConfirm(selectGrid,selectForm,personType);
							}else{
								win.close();
							}
						}, this);
					}else{
						Ext.Msg.alert(Eway.locale.confirm.title, Ext.decode(response.responseText).errors+Eway.locale.msg.saveFailPleaseRefresh,this.onQueryPersonConfirm(selectGrid,selectForm,personType));
					}
				},
				failure: function(response){
					Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.saveFail);
				},
				scope:this
			});
		}else{
			Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.selectPerson);
		}
	},

	onQueryPersonConfirm:function(selectGrid,selectForm,type){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var record1 = sm1.getLastSelected();
		var store = selectGrid.getStore();
//		store.cleanUrlParam();
		var data = selectForm.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('deviceId',record1.data.id);
		store.setBaseParam('type',type);
		store.loadPage(1);
	},
	multiSelect : function(grid){
		var record=grid.getSelectionModel().getSelection();
        if(record == null || record.length == 0){
        	return null;
        }
        var array = new Array(record.length);
        for(var i=0;i<record.length;i++)
        {
            array[i] = record[i].get('guid');
        }
        return array;
	},
	onDelPersonConfirm: function (personGrid,personStore,personType){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var record1 = sm1.getLastSelected();
		var array = this.multiSelect(personGrid);
		if(array != null) {
			var info = '';
			var type = 'del';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/person/person/devicePerson',
				params : {terminalId :record1.data.terminalId,personId:info,type:type,personType:personType},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.removeSuccess,this.onQuaryPerson(personStore,record1.data.terminalId,personType));
					}else{
						Ext.Msg.alert(Eway.locale.confirm.title, Ext.decode(response.responseText).errors+Eway.locale.msg.Eway.locale.msg.someStripRemoveFailePleaseRefresh,this.onQuaryPerson(personStore,record1.data.terminalId,personType));
					}
				},
				failure: function(response){
					Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.removeFail);
				},
				scope:this
			});
		}else{
			Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.selectPerson);
		}
	},

	onQuaryPerson: function(personStore,terminalId,type){
//		personStore.cleanUrlParam();
		personStore.load({
			params : {
				terminalId : terminalId,
				type : type
			}
		});
	},

	onPersonM : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var addManagerWin = Ext.create('Eway.view.machine.device.person.PersonMManager');
			var personGrid = addManagerWin.down('personM_manager_grid');
			var personStore=personGrid.getStore();
			var type = 1;
			var terminalId = record.data.terminalId;
	//		personStore.cleanUrlParam();
			personStore.load({
				params : {
					terminalId : terminalId,
					type : type
				}
			});
			personGrid.down('button[action="queryRe"]').on('click',Ext.bind(this.onQuaryPerson,this,[personStore,terminalId,type]),this);	
			personGrid.down('button[action="add"]').on('click',Ext.bind(this.onSetPerson,this,[personStore,terminalId,type]),this);
			personGrid.down('button[action="remove"]').on('click',Ext.bind(this.onDelPersonConfirm,this,[personGrid,personStore,type]),this);
			addManagerWin.show();
		}
		else {
			Ext.Msg.alert(Eway.locale.confirm.title, Eway.locale.msg.mustSelectDevice);
		}
	},
	
	onOpenPlan : function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var c = this.getController('operatingPlan.OpenPlan');
			var detailWin = Ext.create('Eway.view.operatingPlan.PlanInfoForDevice');
			var record = sm.getLastSelected();
			c.init();
			c.onOpenPlan(detailWin,record.data.id,record.data.terminalId,0);
		} else {
			Ext.Msg.alert("提示", "请选择您要查看的记录！");
		}
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
			
			// 机构管理员
			var orgPerson = tabPanel.query('[itemid=organizationItemID]')[0];

			// 开机方案
			var planInfo = tabPanel.query('[itemid=devicePlanInfoID]')[0];

			maintain.on('render', this.renderMaintain, this);
			tubeMachine.on('render', this.renderTubeMachine, this);
			orgPerson.on('render', this.renderOrganization, this);
			planInfo.on('render', this.renderPlanInfo, this);

			win.show();

		} else {
			Eway.alert(Eway.locale.tip.search.record);
		}
	},
	
	//
	renderPlanInfo : function(tab) {
		var store = tab.getStore();
		store.load({
			params : {
				terminalId : this.terminalId,
			}
		});
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
		/*var form = win.down("form").getForm();
		form.findField('status').setDisabled(true);*/
		win.show();
		},
		
		onUpdate : function(){
			//this._onAddOrUpdate('update');
		    var grid = this.getGrid();
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
				form.loadCusRecord(sm.getLastSelected());
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
			params = store.getUrlParams();
			if(form.getForm().isValid()){
				var me = this;
				var record = grid.getSelectionModel().getLastSelected();
				form.updateCusRecord(record);
				if(store.getModifiedRecords().length == 0){
					Ext.Msg.alert('提示','没有更改数据,请更改后再点击确定!');
				}else{
					store.sync({
						callback:function(batch,option){
							var response  = batch.operations[0].response;
							var object = Ext.decode(response.responseText);
							if(object.hasOwnProperty('errorMsg')){
							record.reject(true);
							store.commitChanges();
							Ext.Msg.alert('提示',object.errorMsg);
						}else if(object.hasOwnProperty('updateMsg')){
							win.close();
							Ext.Msg.alert('提示',object.updateMsg);
						}else{
							Ext.Msg.alert('提示','操作成功');
							this.onQuery();
							win.close();
						}
						},
						scope:me
					});
				}
			}

		},
		
		onRemove : function(){
			var me = this;
			var view = this.getEwayView();
				grid = this.getGrid();
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
					var params = store.getUrlParams();
					var index = store.indexOf(record);
					store.cleanUrlParam();
					store.remove(record);
					store.remove(record);
					store.sync({
						callback : function(batch,option){
							var response = batch.operations[0].response;
							var object = Ext.decode(response.responseText);
							if(object.hasOwnProperty('errorMsg')){
								store.insert(index,[record]);
								store.commitChanges();
								Ext.Msg.alert('提示',object.errorMsg);
							}
							else {
								Ext.Msg.alert('提示','操作成功');
							}
						},
						scope : me
					});

				}
			});

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
				if(form.getForm().isValid()){
					var me = this;
					var record = grid.getSelectionModel().getLastSelected();
					form.updateCusRecord(record);
					if(store.getModifiedRecords().length == 0){
						Ext.Msg.alert('提示','没有更改数据,请更改后再点击确定!');
					}else{
						store.sync({
							callback:function(batch,option){
								var response  = batch.operations[0].response;
								var object = Ext.decode(response.responseText);
								if(object.hasOwnProperty('errorMsg')){
								record.reject(true);
								store.commitChanges();
								Ext.Msg.alert('提示',object.errorMsg);
								win.close();
							}else if(object.hasOwnProperty('updateMsg')){
								win.close();
								Ext.Msg.alert('提示',object.updateMsg);
								this.onTempDevQuery();
							}else{
								Ext.Msg.alert('提示','操作成功');
								this.onTempDevQuery();
								win.close();
							}
							},
							scope:me
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
						store.remove(record);
						store.sync({
							callback : function(batch,option){
								var response = batch.operations[0].response;
								var object = Ext.decode(response.responseText);
								if(object.hasOwnProperty('errorMsg')){
									store.insert(index,[record]);
									store.commitChanges();
									Ext.Msg.alert('提示',object.errorMsg);
								}
								else {
									Ext.Msg.alert('提示','操作成功');
								}
							},
							scope : me
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
			onDeviceLink :function(planInfoGrid,tempGrid,win){
				var count=planInfoGrid.getStore().getCount();
				if(count==0){
					var viewWin = Ext.create('Eway.view.operatingPlan.ViewForDevice');
					//var store = viewWin.down('operatingPlan_gridForDevice').getStore();
					//store.loadPage(1);
					var openPlanGrid = viewWin.down('grid');
					openPlanGrid.down('button[action="query"]').on('click',Ext.bind(this.onDeviceQuery,this,[viewWin]),this);
					openPlanGrid.down('button[action="link"]').on('click',Ext.bind(this.onTempDeviceLinkOpenPlan,this,[viewWin,tempGrid,win]),this);
					viewWin.show();
				}else{
					Ext.Msg.alert("提示", "设备已关联开机方案！");
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
						url : 'api/plan/tempunlinkplan',
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
			onDeviceQuery : function(viewWin){
				var store = viewWin.down('operatingPlan_gridForDevice').getStore();
				var values = viewWin.down('operatingPlan_filterForm').getForm().getValues();
				store.setUrlParamsByObject(values);
				store.loadPage(1);
				viewWin.show();
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
			},
		
	_onAddOrUpdate : function(action){
		var title = action=='add' ? Eway.locale.button.add+this.formConfig.title : Eway.locale.button.update+this.formConfig.title;
		var me = this;
		Ext.require([this.formConfig.form],function(){
			if(action=='update'){
				var grid = this.getGridPanel(),
					sm = grid.getSelectionModel(),
					count = sm.getCount();
				if(count == 0){
					Eway.alert(Eway.choiceUpdateMsg);
					return;
				}
				else if(count > 1){
					Eway.alert(Eway.locale.tip.update.one);
					return;
				}
			}

			var win = Ext.create('Eway.view.base.Window',{
				title : title,
				width : this.formConfig.width ? this.formConfig.width : 500,
				items : [{
					xtype : this.formConfig.xtype
				}]
			});
			win.setAction(action);
			if(action == 'add'){
			    //将设备维护商默认选择怡化
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
			}
			win.down('button[action="confirm"]').on('click',me._save,me);
			if(action == 'update'){
				grid = this.getGridPanel(),
				sm = grid.getSelectionModel(),
				form = win.down('form');
				var record = sm.getLastSelected();
				form.loadCusRecord(record);
				this.boforeShowUpdateWin(win,grid,record);
			}else{
				this.beforeShowAddWin(win,grid);
			}
			win.show();
		},this);
	}
});
