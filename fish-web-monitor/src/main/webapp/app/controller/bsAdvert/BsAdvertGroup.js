Ext.define('Eway.controller.bsAdvert.BsAdvertGroup', {
	extend : 'Ext.app.Controller',

	stores : [ 'bsAdvert.BsAdvertGroup','bsAdvert.BsAdvertGroupType'],
	models : [ 'bsAdvert.BsAdvertGroup' ],
	views : [ 'bsAdvert.BsAdvertGroupView','bsAdvert.BsAdvertGroupFilterForm','bsAdvert.BsAdvertGroupGrid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		selector : '#bsadvertGroup', //这里的比较特殊，是对@@处的选择, 在其他地方没有任何定义
		autoCreate : true,//必须
		xtype : 'bs_advert_group_view',//必须
		id : 'bsadvertGroup'//@@
	}, {
		ref : 'grid',
		selector : 'bs_advert_group_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'bs_advert_group_filterform'
	},{
		ref : 'addWin',
		selector : 'bsAdvert_groupAdd'
	}],

	init : function() {
		 this.control({
			'#bsadvertGroup button[action=query]' : {
				click : this.onQuery
			},
			'#bsadvertGroup button[action=add]' : {
				click : this.onAdd
			},
			'#bsadvertGroup button[action=update]' : {
				click : this.onGroupUpdate
			},
			'#bsadvertGroup button[action=remove]' : {
				click : this.onGroupRemove
			},
			'#bsadvertGroup button[action=link]' : {
				click : this.onGroupLink
			}
		});
	},

	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('bs_advert_group_filterform').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('bs_advert_group_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	
	
	//增加广告组
	onAdd: function() {
		var win = Ext.create('Eway.view.bsAdvert.BsAdvertGroupAdd');
		win.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[win]),this);
		win.show();
	},
	

	onAddConfirm : function(win) {
		var ewayView = this.getEwayView();
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.bsAdvert.BsAdvertGroup',data);
		var store = this.getEwayView().down('bs_advert_group_grid').getStore();
		if(win.down('form').getForm().isValid()){// isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.add(record);
					win.close();
					//点击增加成功后查询条件不带入重新查询。
					store.setUrlParamsByObject(null);
					store.loadPage(1);
					Eway.alert('保存成功');
			    },
			    failure: function(record,operation){
			    	Eway.alert(operation.error);
				},
				button:win.down('button[action="add"]')
			});
		}
	},
	
	
	onGroupUpdate : function(){
		var grid = this.getEwayView().down('bs_advert_group_grid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.bsAdvert.BsAdvertGroupUpdate');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			win.down('button[action="update"]').on('click',Ext.bind(this.onGroupUpdateConfirm,this,[win]),this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	onGroupUpdateConfirm : function(win) {
		var ewayView = this.getEwayView();
		var sm = ewayView.down('bs_advert_group_grid').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var store = ewayView.down('bs_advert_group_grid').getStore();
		var id = record.get("id");
		if(win.down('form').getForm().isValid()){
			record.set("groupName",Ext.String.trim(data.groupName));
			record.set("groupType",data.groupType);
			record.save({
				success : function(recordResult,operation){
					
					if(undefined==recordResult.get("id")||""==recordResult.get("id")||0==recordResult.get("id")){
						recordResult.set("id",id);
					}
					store.applyModel(recordResult);
					Eway.alert(EwayLocale.updateSuccess);
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
		var grid = this.getEwayView().down('bs_advert_group_grid');
		var me  = this;
		var sm = grid.getSelectionModel();
		var quarydata = this.getEwayView().down('form').getForm().getValues();// 得到所有的查询条件的值
		var store = this.getEwayView().down('bs_advert_group_grid').getStore();
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
//												me.onDeviceQueryDevice();
											}else{
												var view2 = this.getEwayView();
												var store2 = view2.down('bs_advert_group_grid').getStore();
													store2.setUrlParam('groupId','0');
													store2.loadPage(1);
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
	
	/*关联设备管理*/
	onGroupLink : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var advertDeviceWin = Ext.create('Eway.view.bsAdvert.BsAdvertDevice');
			var linkedFilterForm = advertDeviceWin.down('bsAdvert_linkedDeviceFilter');
			var linkedDeviceGrid = advertDeviceWin.down('bsAdvert_linkedDeviceGrid');

			var linkingDeviceGrid = advertDeviceWin.down('bsAdvert_linkingDeviceGrid');
			var linkingFilterForm = advertDeviceWin.down('bsAdvert_linkingDeviceFilter');

			linkedDeviceGrid.down('button[action="unlink"]').on('click',
					Ext.bind(this.onUnlinkConfirm,this,[linkedDeviceGrid,linkingDeviceGrid,record]),this
				);
			linkedDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.onQueryDevice,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
				);
			linkedDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.onLinkDeviceFresh,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
				);
			linkingDeviceGrid.down('button[action="link"]').on('click',
					Ext.bind(this.onLinkConfirm,this,[linkingDeviceGrid,linkedDeviceGrid,record]),this
				);
			linkingDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.onQueryDevice,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
				);
			linkingDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.onLinkDeviceFresh,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
			);

			advertDeviceWin.show();
			linkingDeviceGrid.getStore().load({
				params : {
					flag:1,
					guid:record.data.id,
					organizationId:record.data.orgId
				}
			});
			linkedDeviceGrid.getStore().load({
				params : {
					flag:0,
					guid:record.data.id,
					organizationId:record.data.orgId
				}
			});
		}
		else {
			Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
		}
	},
	//刷新“已/可关联的设备”列表
	onLinkDeviceFresh : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.id);
		store.setBaseParam('organizationId',record.data.orgId);
	},

	onQueryDevice : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.id);
		store.setBaseParam('organizationId',record.data.orgId);
		store.loadPage(1);
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
				url : 'api/bsadvert/advertgroup/unlink',
				params : {groupId :record.data.id,deviceId:info},
				success: function(){
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
					linkingDeviceGrid.getStore().cleanUrlParam();
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
				},
				failure: function(){
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkDevFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.unlinkDev);
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
			linkingDeviceGrid.down('button[action="link"]').disable();
			var data = new Object();
			var record2 = Ext.create('Eway.model.bsAdvert.BsAdvertDevice',data);
			for(var i=0;i<array.length-1;i++){
            	record2.set('id',0);
				record2.set('groupId',record.data.id);
				record2.set('deviceId',array[i]);
				record2.save();
            }
			record2.set('id',0);
			record2.set('groupId',record.data.id);
			record2.set('deviceId',array[array.length-1]);
			record2.save({
				success: function(){
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.id,
							organizationId:record.data.orgId
						},
						callback: function(records, operation, success) {
							linkingDeviceGrid.down('button[action="link"]').enable();
						}
					});
					linkedDeviceGrid.getStore().cleanUrlParam();
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
				},
				failure: function(){
					linkingDeviceGrid.down('button[action="link"]').enable();
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkPersonFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
		}
	}
	
});
