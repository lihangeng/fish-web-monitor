
Ext.define('Eway.controller.person.User', {
	extend: 'Ext.app.Controller',

	stores: ['permission.Role',
			'person.user.AddedRole',
			'person.user.AddingRole',
			'GenderDict',
			'person.user.UserStateDict',
			'person.user.UserStateFilterDict',
			'person.person.GenderFilterDict',
			'person.person.PersonTypeFilterDict',
			'person.person.PersonStateFilterDict',
			'person.user.UserLog',
			'person.person.PersonJob'],

	models: ['person.user.User',
			'person.person.Person',
			'permission.Role',
			'person.user.UserRole',
			'person.user.AddedRole',
			'person.user.AddingRole',
			'Dict',
			'person.user.UserLog',
			'person.person.PersonJob'],

	views: [
	        'person.user.Update',
	        'person.user.View',
	        'person.user.UserLogGrid'],

	refs: [{
		ref: 'ewayView',
		selector: '#user',
		autoCreate: true,
		xtype: 'user_view',
		id: 'user'
	}, {
		ref: 'grid',
		selector: 'user_grid'
	},{
		ref :'filterForm',
		selector:'user_filterform'
	}, {
		ref: 'personWin',
		selector: 'user_personWin'
	}, {
		ref: 'addWin',
		selector: 'user_add'
	}, {
		ref: 'updateWin',
		selector: 'user_update'
	}, {
		ref: 'roleGrid',
		selector: 'user_update user_roleGrid'
	}, {
		ref: 'addedRoleGrid',
		selector: 'user_update user_addedRoleGrid'
	} ],

	init: function() {
		this.control({
			'#user button[action=query]': {
				click: this.onQuery
			},
			'#user button[action=add]': {
				click: this.onAdd
			},
			'#user button[action=remove]': {
				click: this.onRemove
			},
			'#user button[action=update]': {
				click: this.onUpdate
			},
			'user_grid':{
				cellclick : this.onCellClik
			},
			'user_update' : {
				destroy : this.onUserUpdateClose
			}
		});
	},

	/**
	 * 根据条件查询
	 */
	onQuery: function(){
		var store = this.getEwayView().down('user_grid').getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},


	/**
	 * 增加一条账户信息：
	 */
	onAdd: function() {
		var addWin = Ext.create('Eway.view.person.user.Add');
		addWin.show();
		addWin.down('button[name="personAddButton"]').on('click',this.onSelectPerson,this);
		this.addWin = addWin;
		addWin.down('button[action="confirm"]').on('click',this.onAddConfirm,this);


		addWin.down('grid').getStore().reload();
	},

	onSelectPerson: function() {
		var win = Ext.create('Eway.view.person.user.PersonWin');
		var personGrid = win.down('user_personGrid');
		personGrid.getStore().cleanUrlParam();
		personGrid.getStore().setBaseParam('type','0');
		personGrid.down('button[action="select"]').on('click',Ext.bind(this.onCreateConfirm,this,[win]),this);
		personGrid.down('button[action="queryPerson"]').on('click',Ext.bind(this.onQueryPerson,this,[win]),this);
		personGrid.on('itemdblclick',Ext.bind(this.onCreateConfirm,this,[win]),this);
		win.show();
		personGrid.getStore().loadPage(1);
	},

	onQueryPerson : function(win){
		var store = win.down('user_personGrid').getStore();
		var data = win.down('person_user_personUserFilterForm').getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('type','0');
		store.loadPage(1);
	},

	onCreateConfirm: function(win) {
		var personGrid = win.down('user_personGrid');
		var sm = personGrid.getSelectionModel();
		if(sm.getCount() == 1) {
			this.addWin.down('field[name="name"]').setValue(sm.getLastSelected().data.name);
			this.addWin.down('field[name="userGuid"]').setValue(sm.getLastSelected().data.guid);
			this.addWin.down('field[name="gender"]').setValue(sm.getLastSelected().data.gender);
			win.close();
		}else{
			Eway.alert('请选择您要创建的账号的人员.');
		}
	},

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

	onAddConfirm: function() {
		var ewayView = this.getEwayView();
		var win = this.getAddWin();
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.person.user.User',data);
		var grid = this.getAddWin().down('user_role');
		var array = this.multiSelect(grid);
		if(win.down('form').getForm().isValid()){
			if(array != null) {
				var info = '';
				for(var i=0;i<array.length;i++){
	                    info += array[i] + ',';
	            }
	            record.data.roles = info;
			}
			record.save({
				success : function(record,operation){
					ewayView.down('user_grid').getStore()
					Eway.alert('创建成功,新建账户'+data.code+'初始密码为: 888888');
					var store = ewayView.down('user_grid').getStore();
					store.cleanUrlParam();
					store.load();
					win.close();
			    },
			    failure: function(record,operation){
					Eway.alert(operation.getError());
				}
			});
		}
	},

	/**
	 * 删除一条账户信息：
	 */
	onRemove: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			if(sm.getLastSelected().data.code=='admin'){
				Eway.alert("删除失败:系统管理员用户,无法删除.");
				return;
			}
			Ext.MessageBox.confirm("请确认",
					"是否删除该记录:删除用户会删除该用户的日志信息.",
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
		}
		else {
			Eway.alert(Eway.choiceDeleteMsg);
		}
	},


	/**
	 * 更新一条账户信息：
	 */
	onUpdate: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		var flag = true;
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.person.user.Update');
			var record = sm.getLastSelected();

			var roleGrid = this.getRoleGrid();
			var addedRoleGrid = this.getAddedRoleGrid();

			addedRoleGrid.getView().on('drop', this.onAddedRole, this);
			roleGrid.getView().on('drop', this.onAddingRole, this);


			addedRoleGrid.getStore().load({
				params : {
					id: record.data.id
				},
				callback: function(records, operation, success) {
					if(success==false){
						Eway.alert("更改失败:记录不存在,请刷新后操作.");
						win.close();
					}
			    }
			});

			roleGrid.getStore().load({
				params : {
					id: record.data.id
				},
				callback: function(records, operation, success) {
					if(success==false){
						Eway.alert("更改失败:记录不存在,请刷新后操作.");
						win.close();
					}
			    }
			});

			win.show();
		}else {
			Eway.alert(Eway.choiceUpdateMsg);
		}
	},


	onAddedRole: function(node, record, overModel, dropPosition) {

		var addedRoleRecord = this.getAddedRoleGrid().getSelectionModel().getLastSelected();
		var userRecord = this.getGrid().getSelectionModel().getLastSelected();//得到用户的ID

		this.onAddedConfirmRole(addedRoleRecord.get('id'), userRecord.get('id'));
	},

	//给用户增加角色（拖动放下的时候）
	onAddedConfirmRole: function(roleId, userId){
		var data = new Object();
		var record = Ext.create('Eway.model.person.user.UserRole',data);

		record.set('userId', userId);
		record.set('roleId', roleId);

		record.save({
			success : function(record, operation) {
				Eway.alert(Eway.addSuccess);
			},
			failure : function(record, operation) {
				Eway.alert('增加失败:' + operation.getError());

				this.getRoleGrid().getStore().reload();
				this.getAddedRoleGrid().getStore().reload();
			},
			scope: this
		});
	},

	onAddingRole: function(node, record, overModel, dropPosition){
		var addingRoleRecord = this.getRoleGrid().getSelectionModel().getLastSelected();
		if(addingRoleRecord == null){
			Eway.alert('请选择需要移动的记录.');
			this.getRoleGrid().getStore().load();
			return;
		}

		var userRecord = this.getGrid().getSelectionModel().getLastSelected();
		this.onAddingConfirmRole(addingRoleRecord.get('id'), userRecord.get('id'));
	},

	onAddingConfirmRole: function(roleId,userId) {
		Ext.Ajax.request({
			method : 'POST',
			url : 'api/person/user/removeRole',
			params : {
				userId : userId,
				roleId:roleId
			},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if(object.success == true){
					Eway.alert(Eway.deleteSuccess);
				} else {
					Eway.alert('删除失败.');
				}
			},
			failure : function() {
				Eway.alert("删除失败:无法删除角色,请重新操作.");
			},
			scope : this
		});
	},

	onCellClik : function (grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {//当单击超链接的时候
		if(cellIndex==0&&e.getTarget().tagName=='A'){
			var code = record.get("code");;
			var sm = grid.getSelectionModel();
			var viewUserLogWin = Ext.create('Eway.view.person.user.ViewUserLogWin');
			var uesrLogGrid = viewUserLogWin.down('userLog_grid');
			var form = viewUserLogWin.down('form').getForm();
			var values = form.getValues();
			var store = uesrLogGrid.getStore();
			store.setUrlParamsByObject(values);
			store.setBaseParam('operCode',code);
			store.loadPage(1);
			uesrLogGrid.down('button[action="query"]').on('click',Ext.bind(this.onQueryLog,this,[viewUserLogWin,code]),this);
			viewUserLogWin.show();
		}
		else if(cellIndex==9&&e.getTarget().tagName=='A'){
			var viewUserRoleWin = Ext.create('Eway.view.person.user.UserRoleWin');
			viewUserRoleWin.show();
			viewUserRoleWin.down('user_role_grid').getStore().load({
				params : {
					id: record.data.id
				}
			});
		}
	},

	onQueryLog: function (viewUserLogWin,code){
		var form = viewUserLogWin.down('form').getForm();
		var values = form.getValues();
		var grid = viewUserLogWin.down('userLog_grid')
		var store = grid.getStore();
		store.setUrlParamsByObject(values);
		store.setBaseParam('operCode',code);
		store.loadPage(1);
	},

	/**
	 * 当更改用户角色窗口关闭时,需要调用此方法
	 */
	onUserUpdateClose : function() {
		this.getGrid().getStore().loadPage(1);
	}
});