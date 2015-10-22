Ext.define('Eway.controller.permission.Role', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'permission.Role'],
	models : [ 'permission.Role'],

	views : [ 'permission.role.View' ],

	collectors :['permission.RoleEdit'],

	refs : [ {
		ref : 'ewayView',
		selector : 'permission_role_view',
		autoCreate : true,
		xtype : 'permission_role_view'
	}, {
		ref : 'grid',
		selector : 'permission_role_grid'
	} ,{
		ref : 'filterForm',
		selector:'permission_role_filterform'
	}],

	formConfig : {
		form : 'Eway.view.permission.role.Form',
		xtype : 'permission_role_form',
		title : Eway.locale.permission.role.title
	},

	init : function() {
		this.initBaseControl();
		this.control({
			'permission_role_view button[action=add]' : {
				click : this.onAdd
			},
			'permission_role_view button[action=remove]' : {
				click : this.onRemove
			},
			'permission_role_view button[action=update]' : {
				click : this.onUpdate
			},
			'permission_role_view button[action=query]':{
				click:this.onQuery
			}
		});
	},

	onQueryAfterAdd : function(){
		var store = this.getGridPanel().getStore();
		store.setUrlParamsByObject();
		store.loadPage(1);
	},

	onAdd : function(){
		//初始化树并在RoleEdit调用增加的请求
		var me = this;
		var roleEditController = this.getController("permission.RoleEdit");
		roleEditController.init(0);
		var window = roleEditController.getEwayView();
		window.add({xtype:'permission_role_Add'});
		window.show();
		this.onQuery
		scop:me

	},


	onUpdate : function(){
		var me = this;
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		if(sm.getCount() == 1){
				Ext.require([me.formConfig.form],function(){
					var win = Ext.create('Eway.view.base.Window',{
					layout : 'fit',
					title : Eway.locale.permission.role.title.update,
					width :700,
					height :540,
					maximizable : false,
					items : [{
						itemId : 'formItemId',
						xtype : me.formConfig.xtype
					}]
				});
				win.down('button[action="confirm"]').on('click',me.save,me);
				win.show();
				var view = me.getEwayView(),
				grid = view.getComponent('gridItemId'),
				sm = grid.getSelectionModel(),
				form = win.getComponent('formItemId');
				form.loadCusRecord(sm.getLastSelected());
				},this);
		}else{
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},

	save : function(button){
		var view = this.getEwayView(),
			win = button.up('window'),
			form = win.getComponent('formItemId');

		if(form.getForm().isValid()) {
			button.disable();
			var me = this;
			var view = this.getEwayView(),
			win = button.up('window'),
			form = win.getComponent('formItemId'),
			grid = view.getComponent('gridItemId'),
			store = grid.getStore(),
			params = store.getUrlParams(),
			action = win.getAction(),
			backupRecord;
			store.cleanUrlParam();
			var record = grid.getSelectionModel().getLastSelected();


			// 判断角色权限有没有发生改变,如果没有发生改变，就不改变store
			// 注意：第一次进角色权限更改页面,不管有没有更改,只要点了按钮确定,都会进行一次请求,
			// 因为permissions第一次进来是为null的。
			var permissions = record.get('permissions');
			if (!permissions || permissions != form.getPerminssions()) {

				form.updateCusRecord(record);

				// 角色权限发生改变,同步更新到store
				var updateRecord = store.getById(record.get('id'));
				updateRecord.set('name', record.get('name'));
				updateRecord.set('description', record.get('description'));
				updateRecord.set('permissions', record.get('permissions'));
				updateRecord.set('system', record.get('system'));
			}


			//如果form中的数据没有改动，则关闭窗口
			if (store.getModifiedRecords().length == 0){
				Eway.alert(Eway.updateSuccess);
				button.up('window').close();
			}else{
				store.sync({
				callback : function(batch,option){
					var response = batch.operations[0]._response;
					var object = Ext.decode(response.responseText);
					if(object.hasOwnProperty('errorMsg')){
						if(action == 'add'){
							store.removeAt(0);
						}
						else if(action == 'update'){
						}
						store.commitChanges();
						Eway.alert(object.errorMsg);
						button.enable();
					}else if(object.hasOwnProperty('updateMsg')){
						button.up('window').close();
						Eway.alert(object.updateMsg);
					}
					else {
						Eway.alert(Eway.updateSuccess);
						button.up('window').close();
						this.onQuery();
					}
				},
				scope : me
			});

			}
			store.setUrlParamsByObject(params);
		}else{
		}
	}

});