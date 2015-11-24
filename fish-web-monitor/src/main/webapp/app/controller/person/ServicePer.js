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
//			},
//			'#ser_person treepanel': {
//				beforerender : this.onBeforeRender,
//				afterrender: this.onTreePanelAfterRender,
//				itemclick: this.onItemClick,
//				beforeitemdblclick : this.onBeforeitemdblclick
			}
		});
	},

	//机构树渲染之前设置根节点
//	onBeforeRender : function(){
//		var treePanel = this.getEwayView().down('treepanel');
//		if (ewayUser.getOrgType() == '1') {
//			treePanel.setRootNode({
//					id : ewayUser.getOrgId(),
//					text : ewayUser.getOrgName(),
//					expanded : true
//				});
//		}
//	},

//	onBeforeitemdblclick : function(){
//		return false;
//	},

//	onTreePanelAfterRender : function(treepanel){
//	    var selectedNode = this.getSelectionInTree();
//	    var nodeId = selectedNode.data.id;
//		var store = this.getGrid().getStore();
//		if(ewayUser.getOrgType()=='1'){
//			store.setBaseParam('selectedNode',nodeId);
//		}
//		store.setBaseParam('type','1');
//	  	store.loadPage(1);
//	    var actionTip = this.getEwayView().down("tbtext[action=tip]");
//	    actionTip.setText(EwayLocale.tip.servicePer.allSerPer);
//	},


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
//		 var actionTip = this.getEwayView().down("tbtext[action=tip]");
//	     actionTip.setText(EwayLocale.tip.bankPer.personEligible);
		 store.loadPage(1);
//		 this.getEwayView().down('treepanel').getSelectionModel().select(0,true);//选择根节点
	},

	//得到组织树中选中的节点
//	getSelectionInTree : function(){
//		 var treePanel = this.getEwayView().down('treepanel');
//		 var selections = treePanel.getSelectionModel().getSelection();
//		 var selectedNode = null;
//	     if(Ext.isEmpty(selections)){//如果当前没有选中任何节点
//	    	treePanel.getSelectionModel().select(0,true);//默认选择根节点
//	    	selectedNode = treePanel.getRootNode();
//	     }else{
//	    	selectedNode = selections[0];
//	     }
//	     return selectedNode;
//	},

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
//            	var actionTip = this.getEwayView().down("tbtext[action=tip]");
//		     	actionTip.setText('<font color="red">'+this.getSelectionInTree().data.text+'</font>'+EwayLocale.tip.bankPer.downGradePer);

			}else{
				store.setBaseParam('type','1');
				store.loadPage(1);
//            	var actionTip = this.getEwayView().down("tbtext[action=tip]");
//	    		actionTip.setText(EwayLocale.person.servicePer.maintainInfo);
			}
        }else{
        	var store = this.getEwayView().down('ser_person_grid').getStore();
			store.cleanUrlParam();
			store.setBaseParam('selectedNode',node.data.id);
			store.loadPage(1);
//       		var actionTip = this.getEwayView().down("tbtext[action=tip]");
//	     	actionTip.setText('<font color="red">'+this.getSelectionInTree().data.text+'</font>'+EwayLocale.tip.bankPer.downGradePer);
	    }
    },

    /**
     * 增加人员信息：
     */
	onAdd: function() {
		var win = Ext.create('Eway.view.person.servicePer.Add');
		var code = win.down("form").getForm().findField("code");
		this.win = win;
//		var selectedNode = this.getSelectionInTree();
//		var nodeId = selectedNode.data.id;
//		if(nodeId != "1"){
//			win.down('field[name="organizationName"]').setValue(selectedNode.data.text);
//			win.down('field[name="organizationId"]').setValue(nodeId);
//		}
		win.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this),this);
		win.show();
	},

	onAddConfirm: function() {
		var ewayView = this.getEwayView();
		var win = this.win;
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.person.person.Person',data);
		var store = this.getEwayView().down('ser_person_grid').getStore();
		if(win.down('form').getForm().isValid()){//isValid对markInvalid不起作用
			record.set("type",'1');
			record.save({
				success : function(record,operation){
//					ewayView.down('treepanel').getSelectionModel().select(0,true);//选择根节点
//					store.cleanUrlParam();
//					store.setBaseParam('organizationId',data.organizationId);
//					store.load({
//						params:{
//							organizationId : data.organizationId
//						}
//					});
					store.load();
//					var actionTip = ewayView.down("tbtext[action=tip]");
//	     			actionTip.setText('<font color="red">'+record.data.organizationName+'</font>'+EwayLocale.tip.bankPer.downGradePer);
					win.close();
					Eway.alert(EwayLocale.addSuccess);
			    },
			    failure: function(record,operation){
					Eway.alert(EwayLocale.tip.add.error + operation.getError());
				}
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
//					ewayView.down('treepanel').getSelectionModel().select(0,true);//选择根节点
//					store.cleanUrlParam();
//					store.setBaseParam('organizationId',data.organizationId);
//					store.load({
//						params:{
//							organizationId : data.organizationId
//						}
//					});
					store.load();
//					var actionTip = ewayView.down("tbtext[action=tip]");
//	     			actionTip.setText('<font color="red">'+record.data.organizationName+'</font>'+EwayLocale.tip.bankPer.personBelongs);
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
	 * 删除人员信息：
	 */
	onRemove: function() {
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
									Eway.alert(EwayLocale.updateSuccess);
//									grid.getStore().remove(record);
									grid.getStore().load();
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