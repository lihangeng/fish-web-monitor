/**
 * 维护商人员基本信息控制：
 */
Ext.define('Eway.controller.person.ServicePer', {
	extend: 'Ext.app.Controller',

	stores: ['person.organization.Organization',
			 'GenderDict',
			 'person.person.PersonTypeDict',
			 'person.person.LinkedDevice',
			 'person.person.LinkingDevice',
			 'person.person.GenderFilterDict',
			 'person.person.PersonTypeFilterDict',
			 'person.person.PersonStateDict',
	         'person.person.PersonStateFilterDict'],

	models: ['person.organization.Organization',
			 'Dict',
			 'person.person.Person',
			 'person.person.LinkDevice',
			 'person.person.PersonDevice'],

	views: ['person.servicePer.View'],

	refs: [{
		ref: 'ewayView',
		selector: '#ser_person',
		autoCreate: true,
		xtype: 'ser_person_view',
		id: 'ser_person'
	}, {
		ref: 'grid',
		selector: 'ser_person_grid'
	}, {
		ref: 'addWin',
		selector: 'ser_person_add'
//	},{
//		ref :'organizationTree',
//		selector:'treepanel'
	},{
		ref :'filterForm',
		selector:'ser_person_filterform'
	}, {
		ref: 'updateWin',
		selector: 'ser_person_update'
	}],

	init: function() {
		this.control({
			'#ser_person button[action=query]' :{
				click : this.onQuery
			},
			'#ser_person button[action=add]': {
				click: this.onAdd
			},
			'#ser_person button[action=remove]': {
				click: this.onRemove
			},
			'#ser_person button[action=update]': {
				click: this.onUpdate
			},
			'#ser_person button[action=link]': {
				click: this.onLink
			},
			'#ser_person menuitem[action=queryDevice]': {
				click: this.onQueryDevice
			},
			'#ser_person menuitem[action=linkDevice]': {
				click: this.onLinkDevice
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
			var PersonDeviceWin = Ext.create('Eway.view.person.person.PersonDevice');
			var linkedFilterForm = PersonDeviceWin.down('person_linkedDeviceFilter');
			var linkedDeviceGrid = PersonDeviceWin.down('person_linkedDeviceGrid');

			var linkingDeviceGrid = PersonDeviceWin.down('person_linkingDeviceGrid');
			var linkingFilterForm = PersonDeviceWin.down('person_linkingDeviceFilter');

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
			PersonDeviceWin.show();
			
			linkedDeviceGrid.getView().on('drop',Ext.bind(this.onUnlinkConfirmDrop,this,[linkedDeviceGrid,linkingDeviceGrid,record]),this);				
			linkingDeviceGrid.getView().on('drop',Ext.bind(this.onLinkConfirmDrop,this,[linkingDeviceGrid,linkedDeviceGrid,record]),this);
			linkedDeviceGrid.getStore().load({
				params : {
					flag:0,
					guid:record.data.guid,
					organizationId:record.data.organizationId
				}
			});
			linkingDeviceGrid.getStore().load({
				params : {
					flag:1,
					guid:record.data.guid,
					organizationId:record.data.organizationId
				}
			});
		}
		else {
			Eway.alert(EwayLocale.tip.bankPer.link.linkPerson);
		}
	},

	//刷新“已/可关联的设备”列表
	onLinkDeviceFresh : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.guid);
		store.setBaseParam('organizationId',record.data.organizationId);
	},

	onQueryDevice : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.guid);
		store.setBaseParam('organizationId',record.data.organizationId);
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
				url : 'api/person/person/unlink',
				params : {personId :record.data.guid,deviceId:info},
				success: function(){
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						}
					});
					linkingDeviceGrid.getStore().cleanUrlParam();
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.guid,
							organizationId:record.data.organizationId
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

	onUnlinkConfirmDrop : function(linkedDeviceGrid,linkingDeviceGrid,record){

		var array = this.multiSelect(linkedDeviceGrid);
		if(array != null) {
			linkingDeviceGrid.down('button[action="link"]').disable();
			var data = new Object();
			var record2 = Ext.create('Eway.model.person.person.PersonDevice',data);
			for(var i=0;i<array.length-1;i++){
            	record2.set('id',0);
				record2.set('personId',record.data.guid);
				record2.set('deviceId',array[i]);
				record2.save();
            }
			record2.set('id',0);
			record2.set('personId',record.data.guid);
			record2.set('deviceId',array[array.length-1]);
			record2.save({
				success: function(){
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						},
						callback: function(records, operation, success) {
							linkingDeviceGrid.down('button[action="link"]').enable();
						}
					});
					linkedDeviceGrid.getStore().cleanUrlParam();
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.guid,
							organizationId:record.data.organizationId
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
			linkingDeviceGrid.down('button[action="link"]').disable();
			var record2 = Ext.create('Eway.model.person.person.PersonDevice',data);
			for(var i=0;i<array.length-1;i++){
            	record2.set('id',0);
				record2.set('personId',record.data.guid);
				record2.set('deviceId',array[i]);
				record2.save();
            }
			record2.set('id',0);
			record2.set('personId',record.data.guid);
			record2.set('deviceId',array[array.length-1]);
			record2.save({
				success: function(){
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						},
						callback: function(records, operation, success) {
							linkingDeviceGrid.down('button[action="link"]').enable();
						}
					});
					linkedDeviceGrid.getStore().cleanUrlParam();
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						}
					});
				},
				failure: function(){
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkPersonFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
		}
	},

	onLinkConfirmDrop : function(linkingDeviceGrid,linkedDeviceGrid,record){


		var array = this.multiSelect(linkingDeviceGrid);
		if(array != null) {
			var info = '';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/person/person/unlink',
				params : {personId :record.data.guid,deviceId:info},
				success: function(){
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						}
					});
					linkingDeviceGrid.getStore().cleanUrlParam();
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.guid,
							organizationId:record.data.organizationId
						}
					});
				},
				failure: function(){
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkDevFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
		}
	},
	
	/**
	 * 根据条件查询
	 */
	onQuery: function(){
		 var store = this.getEwayView().down('ser_person_grid').getStore();
		 var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		 store.cleanUrlParam();
		 store.setBaseParam('type','1');
		 for(var i in data){
		 	store.setUrlParam(i,data[i])
		 }
		 store.loadPage(1);
	},

	/**
	 * 单击机构树节点
	 * 显示下级树节点，同时刷新机构列表
	 */
	onItemClick: function(view, node, item, index, e) {
		/** 取消双击展开折叠菜单行为 */
		view.toggleOnDblClick = false;
		if (!node.isLeaf()) {
			view.expand(node);
			var store = this.getEwayView().down('ser_person_grid').getStore();
			store.cleanUrlParam();
			if(node.data.id != '1'){
				store.setBaseParam('selectedNode',node.data.id);
				store.loadPage(1);

			}else{
				store.setBaseParam('type','1');
				store.loadPage(1);
			}
        }else{
        	var store = this.getEwayView().down('ser_person_grid').getStore();
			store.cleanUrlParam();
			store.setBaseParam('selectedNode',node.data.id);
			store.loadPage(1);
	    }
    },

    /**
     * 增加人员信息：
     */
	onAdd: function() {
		var win = Ext.create('Eway.view.person.servicePer.Add');
		var code = win.down("form").getForm().findField("code");
		this.win = win;
		win.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this),this);
		win.show();
	},

	onAddConfirm: function() {
		var me = this;
		var ewayView = this.getEwayView();
		var win = this.win;
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.person.person.Person',data);
		var store = this.getEwayView().down('ser_person_grid').getStore();
		if(win.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.set("type",'1');
			record.save({
				success : function(record,operation){
					me.onQueryAfterAdd();
					win.close();
					Eway.alert(EwayLocale.addSuccess);
			    },
			    failure: function(record,operation){
					Eway.alert(EwayLocale.tip.add.error);
				},
				button:win.down('button[action="add"]')
			});
		}
	},

    /**
     * 更新人员信息：
     */
	onUpdate: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.person.servicePer.Update');
			var record = sm.getLastSelected();
			var value=record.get("gender");
			if(value=="MALE"){
				value=0;
			}else{
				value=1;
			};
			record.set('gender',value) ;
			win.down('form').getForm().loadRecord(record);
			win.down('button[action="update"]').on('click',this.onUpdateConfirm,this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	getUpdateFormFiled : function(name){
	    	return this.getUpdateForm().findField(name);
	},

	getUpdateForm : function(){
	    	return this.getUpdateWin().down("form").getForm();
	},

	onUpdateConfirm: function() {
		var ewayView = this.getEwayView();
		var sm = this.getGrid().getSelectionModel();
		var record = sm.getLastSelected();
		var win  = this.getUpdateWin();
		var data = this.getUpdateWin().down('form').getValues();
		var store = ewayView.down('ser_person_grid').getStore();
		var code = record.data.code;
		if(this.getUpdateWin().down('form').getForm().isValid()){
			record.set("birthday",data.birthday);
			record.set("code",data.code);
			record.set("email",data.email);
			record.set("gender",data.gender);
			record.set("mobile",data.mobile);
			record.set("name",data.name);
			record.set("organizationId",data.organizationId);
			record.set("organizationName",data.organizationName);
			record.set("phone",data.phone);
			record.set("state",data.state);
			record.set("remark",data.remark);
			record.set("jobNum",data.jobNum);
			record.save({
				success : function(record,operation){
					Eway.alert(EwayLocale.updateSuccess);
					store.load();
					win.close();
				},
				failure: function(record,operation){
					Eway.alert(operation.getError());
					store.load();
				},
				button:win.down('button[action="update"]')
			});
		}
	},
	/**
	 * 增加后不带入查询条件
	 */
	onQueryAfterAdd: function(){
		 var store = this.getGrid().getStore();
		 var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		 store.cleanUrlParam();
		 store.setBaseParam('type','1');
		 for(var i in data){
		 	store.setUrlParam(i,null)
		 }
		 store.loadPage(1);
	},

	/**
	 * 删除人员信息：
	 */
	onRemove: function() {
		var me = this;
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									me.onQueryAfterAdd();
								},
								failure: function(record,operation){
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