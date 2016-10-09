Ext.define('Eway.controller.machine.Device', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.Device', 'Hour', 'Minute', 'machine.DeviceAtmType',
			'machine.PersonM', 'machine.PersonTM',
			'machine.DeviceAwayFlagComboBox',
			'machine.atmType.DeviceAtmVendor',
			'machine.atmType.DeviceAtmCatalog',
			'person.person.PersonStateFilterDict',
			'person.person.PersonJob'],

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
		selector : 'device_add'
	}, {
		ref : 'updateWin',
		selector : 'device_update'
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
		title : EwayLocale.machine.device.devInfo,
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
			'#device button[action=openPlan]' : {
				click : this.onOpenPlan
			},
			'#device menuitem[action=personTM]' : {
				click : this.onPersonTM
			},
			'#device menuitem[action=personM]' : {
				click : this.onPersonM
			},
			'#device button[action=export]' : {
				click : this.onExport
			}
		});
	},
	onExport : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();

		// 查询输入验证
		if (bool == false) {
			return;
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
		var columns = this.getGrid().getColumns();
		var headerName = new Array();
		var colIndex = new Array();
		var colWidth = new Array();
		Ext.Array.forEach(columns,function(item,index,opt){
			headerName.push(item.text);
			colWidth.push(item.cellWidth);
			colIndex.push(item.dataIndex);
		},this);
		params+="&gridInfoHeaderNames="+headerName+"&gridInfoColIndexs="+colIndex+"&gridInfoColWidths="+colWidth;
		window.location.href = 'api/machine/device/export?_dc=' + params;
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
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.tip.search.record);
		}
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
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.tochenkDervice);
		}
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
			//personStore.cleanUrlParam();
			personStore.load({
				params : {
					terminalId : terminalId,
					type : type
				}
			});
			personGrid.down('button[action="queryRe"]').on('click',Ext.bind(this.onQuaryPerson,this,[personStore,terminalId,type]),this);			addManagerWin.show();
		}
		else {
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.tochenkDervice);
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
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.tochenckPeople);
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
						Ext.Msg.confirm(EwayLocale.confirm.title, EwayLocale.report.openplan.addSuccess, function(result) {
							if ("yes" == result) {
								this.onQueryPersonConfirm(selectGrid,selectForm,personType);
							}else{
								win.close();
							}
						}, this);
					}else{
						Ext.Msg.alert(EwayLocale.confirm.title, Ext.decode(response.responseText).errors+EwayLocale.report.openplan.tipAddError,this.onQueryPersonConfirm(selectGrid,selectForm,personType));
					}
				},
				failure: function(response){
					Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.addFail);
				},
				scope:this
			});
		}else{
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.tochenckPeople);
		}
	},
	onQueryPersonConfirm:function(selectGrid,selectForm,type){
		var grid = this.getGrid();
		var sm1 = grid.getSelectionModel();
		var record1 = sm1.getLastSelected();
		var store = selectGrid.getStore();
		//store.cleanUrlParam();
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
						Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.msg.removeSuccess,this.onQuaryPerson(personStore,record1.data.terminalId,personType));
					}else{
						Ext.Msg.alert(EwayLocale.confirm.title, Ext.decode(response.responseText).errors+"条解除失败,请刷新后查看.",this.onQuaryPerson(personStore,record1.data.terminalId,personType));
					}
				},
				failure: function(response){
					Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.msg.removeFail);
				},
				scope:this
			});
		}else{
			Ext.Msg.alert(EwayLocale.confirm.title, EwayLocale.report.openplan.tochenckPeople);
		}
	},
	onQuaryPerson: function(personStore,terminalId,type){
		//personStore.cleanUrlParam();
		personStore.load({
			params : {
				terminalId : terminalId,
				type : type
			}
		});
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
			Eway.alert(EwayLocale.tip.search.record);
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
	_onAddOrUpdate : function(action){
		
		var midStr ="";
		if(!Ext.String.startsWith(Eway.user.language,"zh")){
			midStr="&nbsp;";
		}
		var title = action=='add' ? EwayLocale.button.add+midStr+this.formConfig.title : EwayLocale.button.update+midStr+this.formConfig.title;
		var me = this;
		Ext.require([this.formConfig.form],function(){
			if(action=='update'){
				var grid = this.getGridPanel(),
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
				if(Eway.user.getOrgId() == 1 || Eway.user.getOrgType() == 1){
					win.down('field[name="orgId"]').setValue(null);
					win.down('field[name="orgName"]').setValue(null);
				//如果是银行机构登陆则默认显示当前机构
				}else{
					win.down('field[name="orgId"]').setValue(Eway.user.getOrgId());
					win.down('field[name="orgName"]').setValue(Eway.user.getOrgName());
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
