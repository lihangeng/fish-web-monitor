Ext.define('Eway.controller.machine.atmGroup.AtmGroup', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'machine.atmGroup.Device', 'Hour', 'Minute', 'machine.DeviceAtmType',
				'machine.PersonM', 'machine.PersonTM',
				'machine.DeviceAwayFlagComboBox',
				'machine.atmType.DeviceAtmVendor',
				'machine.atmType.DeviceAtmCatalog',
	           'machine.atmGroup.DeviceGroup'],


	models : [ 'machine.atmGroup.Device',
	           'machine.atmGroup.DeviceGroupRelation',
	           'machine.atmGroup.DeviceGroup'],

	views : [ 'Eway.view.machine.atmGroup.View' ],
	infoView : 'Eway.view.machine.device.Info',

	refs : [ {
		ref : 'ewayView',
		selector : 'machine_atmGroup_view',
		autoCreate : true,
		xtype : 'machine_atmGroup_view'
	}, {
		ref : 'deviceGrid',
		selector : 'atmGroup_deviceGrid'
	}, {
		ref : 'groupGrid',
		selector : 'atmGroup_groupGrid'
	} ],
	init : function() {
		this.control({
			'atmGroup_groupGrid button[action=query]' : {
				click : this.onGroupQuery
			},
			'atmGroup_groupGrid' : {
				afterrender : this.onFirstSelect
			},
			'atmGroup_groupGrid button[action=add]' : {
				click : this.onGroupAdd
			},
			'machine_atmGroup_view tabpanel':{
				tabchange:this.onTabChange
			},
			'atmGroup_groupGrid button[action=showDetail]' : {
				click : this.showDetail
			},
			'atmGroup_groupGrid button[action=update]' : {
				click : this.onGroupUpdate
			},
			'atmGroup_groupGrid button[action=remove]' : {
				click : this.onGroupRemove
			},
			'atmGroup_deviceGrid button[action=info]' : {
				click : this.onDeviceInfo
			},
			'atmGroup_deviceGrid button[action=query]' : {
				click : this.onDeviceQueryDevice
			},
			'atmGroup_deviceGrid button[action=add]' : {
				click : this.onDeviceAdd
			},
			'atmGroup_deviceGrid button[action=remove]' : {
				click : this.onDeviceRemove
			},
			'machine_atmGroup_view' : {
				activate : this.onQuery
			}
		});

	},
	//tab标签切换时只保留主tab页，分组详情的tab页移除
	onTabChange:function( tabPanel, newCard, oldCard, eOpts ){
		if(newCard.name=='groupPanel'){
			var tabpanel = this.getEwayView().down("tabpanel");
			var deviceDetailPanel = this.getEwayView().down("panel[name='atmGroupDeviceDetails']");
			deviceDetailPanel.setTitle(EwayLocale.monitor.devMonitor.atmGroupTip);
			tabpanel.remove(deviceDetailPanel,true);
		}
	},
	//查看分组的设备信息
	showDetail:function(){
		var groupRecord = this.getEwayView().down('atmGroup_groupGrid').getSelectionModel().getLastSelected();
		if(groupRecord==undefined){
			Eway.alert(EwayLocale.tip.search.record);
			return ;
		}
		var deviceDetailPanel = Ext.create("Eway.view.machine.atmGroup.DeviceView");
		var tabpanel = this.getEwayView().down("tabpanel");
		tabpanel.add(deviceDetailPanel);
		deviceDetailPanel.setTitle(groupRecord.get("name")+EwayLocale.monitor.devMonitor.atmGroupTip);
		tabpanel.setActiveItem(deviceDetailPanel);
		this.onDeviceQueryDevice();
	},
	//默认选中第一条记录
	onFirstSelect : function (){
		// 选中第一条记录：
		var grid = this.getEwayView().down('atmGroup_groupGrid');
		var store = grid.getStore();
		var me  = this;
		store.loadPage(1);
		store.on({
	       load: {
	           fn: function() {
	        	   if(store.getCount()>0) {
	        		   grid.getSelectionModel().select(0);
	        	   } 
	           }
	       },
	       scope: this
		 });
	},
	// 根据条件查询组信息：
	onGroupQuery : function(){
		var store = this.getEwayView().down('atmGroup_groupGrid').getStore();
		var data = this.getEwayView().down('form').getForm().getValues();// 得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},
	//增加分组信息
	onGroupAdd : function(){
		var groupAddWin = Ext.create('Eway.view.machine.atmGroup.GroupAdd');
		groupAddWin.down('button[action="add"]').on('click',Ext.bind(this.onGroupAddConfirm,this,[groupAddWin]),this);
		groupAddWin.show();
	},
	//确认增加分组
	onGroupAddConfirm :  function(win){
		var ewayView = this.getEwayView();
		data = win.down('form').getForm().getValues();
		var name = data.name;
		data.name = Ext.String.trim(name);
		var record = Ext.create('Eway.model.machine.atmGroup.DeviceGroup',data);
		var store = this.getEwayView().down('atmGroup_groupGrid').getStore();
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
	//更改分组信息
	onGroupUpdate : function(){
		var grid = this.getEwayView().down('atmGroup_groupGrid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.machine.atmGroup.GroupUpdate');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			win.down('button[action="update"]').on('click',Ext.bind(this.onGroupUpdateConfirm,this,[win]),this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},
	//确认更改分组信息
	onGroupUpdateConfirm : function(win) {
		var ewayView = this.getEwayView();
		var sm = ewayView.down('atmGroup_groupGrid').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var store = ewayView.down('atmGroup_groupGrid').getStore();
		var quarydata = ewayView.down('form').getForm().getValues();// 得到所有的查询条件的值
		var id = record.get("id");
		if(win.down('form').getForm().isValid()){
			record.set("name",Ext.String.trim(data.name));
			record.set("note",data.note);
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

	onGroupRemove : function() {
		var grid = this.getEwayView().down('atmGroup_groupGrid');
		var me  = this;
		var sm = grid.getSelectionModel();
		var quarydata = this.getEwayView().down('form').getForm().getValues();// 得到所有的查询条件的值
		var store = this.getEwayView().down('atmGroup_groupGrid').getStore();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.isConfirmRemove,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									store.setUrlParamsByObject(quarydata);
									store.load( {scope: this,
										callback: function(){
											if(grid.getStore().getCount()>0){
												grid.getSelectionModel().select(0);
											}
									}});
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

	},

	onDeviceInfo : function(){
		var grid = this.getEwayView().down('atmGroup_deviceGrid');
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
		var store = tab.getStore();
		store.load({
			params : {
				terminalId : this.terminalId,
				type : 1
			}
		});
	},
	// 加载管机员的数据
	renderTubeMachine : function(tab) {
		var store = tab.getStore();
		store.load({
			params : {
				terminalId : this.terminalId,
				type : 0
			}
		});
	},

	onDeviceQueryDevice : function(){
		var view = this.getEwayView();
		var form = view.down('atmGroup_deviceFilter').getForm();
		var bool = form.isValid();
		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = view.down('atmGroup_deviceGrid').getStore();
		store.setUrlParamsByObject(values);
		var groupRecord = view.down('atmGroup_groupGrid').getSelectionModel().getLastSelected();
		if(groupRecord!=null){
			store.setBaseParam('groupId',groupRecord.data.id);
			store.loadPage(1);
		}else {
			Eway.alert(EwayLocale.tip.noGroupInfo);
		}

	},

	onDeviceAdd : function(){
		var view = this.getEwayView();
		var deviceGrid = view.down('atmGroup_deviceGrid');
		var groupGrid = this.getEwayView().down('atmGroup_groupGrid');
		var sm = groupGrid.getSelectionModel();
		if(sm.getCount() == 1) {
			var groupRecord = view.down('atmGroup_groupGrid').getSelectionModel().getLastSelected();
			var deviceWin = Ext.create('Eway.view.machine.atmGroup.DeviceWin');
			var deviceAddingGrid = deviceWin.down('atmGroup_deviceAddingGrid');
			deviceAddingGrid.getStore().cleanUrlParam();


			deviceAddingGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.refleshDeviceAdding,this,[deviceAddingGrid.down('pagingtoolbar'),groupRecord.data.id]),this
				);
			deviceAddingGrid.down('button[action="query"]').on('click',
					Ext.bind(this.onQueryUnDevice,this,[deviceWin,groupRecord.data.id]),this);
			deviceAddingGrid.down('button[action="confirm"]').on('click',
					Ext.bind(this.onAddConfirm,this,[deviceWin,groupRecord.data.id]),this);
			deviceWin.show();

			deviceAddingGrid.getStore().load({
				params : {
					groupId:groupRecord.data.id
				}
			});
		}else {
			Eway.alert(EwayLocale.tip.selectAdd);
		}
	},

	refleshDeviceAdding : function(pagingtoolbar,groupId){
		pagingtoolbar.store.setUrlParam('groupId',groupId);
	},

	onQueryUnDevice : function(win,groupId){
		var form = win.down('atmGroup_deviceFilter').getForm();
		var bool = form.isValid();
		// 查询输入验证
		if (bool == false) {
			Eway.alert(EwayLocale.tip.searchOfNoLegal);
			return
		}
		var values = form.getValues();
		var store = win.down('atmGroup_deviceAddingGrid').getStore();
		store.setUrlParamsByObject(values);
		store.setUrlParam('groupId',groupId);
		store.loadPage(1);
	},

	/**
	 * 获得选中的行id数组：
	 *
	 * @param {}
	 *            grid
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

	// 设备添加至组：
	onAddConfirm : function(win,groupId){
		var view = this.getEwayView();
		var deviceAddingGrid = win.down('atmGroup_deviceAddingGrid');
		var deviceGrid = view.down('atmGroup_deviceGrid');
		var array = this.multiSelect(deviceAddingGrid);
		if(array != null) {
			win.down('button[action="confirm"]').disable();
			var data = new Object();
			var record = Ext.create('Eway.model.machine.atmGroup.DeviceGroupRelation',data);
			for(var i=0;i<array.length-1;i++){
            	record.set('id',0);
				record.set('groupId',groupId);
				record.set('deviceId',array[i]);
				record.save();
            }
			record.set('id',0);
			record.set('groupId',groupId);
			record.set('deviceId',array[array.length-1]);
			record.save({
				success: function() {

					Ext.Msg.confirm(EwayLocale.tip.remind, EwayLocale.tip.continueAdd, function(result) {
						if ("yes" != result) {
							win.close();
						}
					}, this);

					var store1 = deviceAddingGrid.getStore();
					store1.setBaseParam("groupId",groupId);
					//多页加载数据，最后一页数据加载完了，再次加载最后一页数据返回的信息为空，所以此处更改为加载页面一
					store1.loadPage(1);
					

					var store2 = deviceGrid.getStore();
					//点击增加成功后查询条件不带入重新查询。
					store2.setUrlParamsByObject(null);
					store2.setBaseParam("groupId",groupId);
					store2.load();
					win.down('button[action="confirm"]').enable();
				},
				failure: function(){
					Eway.alert(EwayLocale.tip.addFail);
					win.down('button[action="confirm"]').enable();
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.selectAdd);
		}

	},

	onDeviceRemove : function(){
		var me = this;
		var view = this.getEwayView();
		var grid = view.down('atmGroup_deviceGrid');
		var groupRecord = view.down('atmGroup_groupGrid').getSelectionModel().getLastSelected();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			if(groupRecord!=null){
				Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
						EwayLocale.tip.isRemoveDev,
						function(button,text) {
							if(button=="yes"){
								var record = sm.getLastSelected();
								Ext.Ajax.request({
									method : 'POST',
									url : 'api/machine/atmGroup/unlink',
									params : {groupId :groupRecord.data.id,deviceId:record.data.id},
									success: function(){
										Eway.alert(EwayLocale.deleteSuccess);
										me.onDeviceQueryDevice();
									},
									failure: function(){
										Eway.alert(EwayLocale.tip.removeFail);
									},
									scope:this
								});
							}
						}, this);
			}else {
				Eway.alert(EwayLocale.tip.selectRemoveGroup);
			}

		}
		else {
			Eway.alert(EwayLocale.tip.selectRemoveDev);
		}
	}
});