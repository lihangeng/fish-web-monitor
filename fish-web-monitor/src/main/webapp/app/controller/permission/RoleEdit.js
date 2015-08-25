Ext.define('Eway.controller.permission.RoleEdit', {
	extend : 'Ext.app.Controller',

	stores : [ 'permission.Role'],
	models : [ 'permission.Role'],

	views : [ 'permission.role.EditView','permission.role.Add' ],

	requires: ['Eway.controller.permission.Role'],

	refs : [{
			ref : 'ewayView',
			selector : 'permission_role_editView',
			autoCreate : true,
			xtype : 'permission_role_editView'
		},{
			ref : 'roleTree',
			selector : 'permission_role_tree'
		},{
			ref : 'addBtn',
			selector : 'button[action=confirm]'
		}],

	roleId : 0,
	isInitControl : false,
	init : function(roleId) {
		this.roleId = roleId;
		if(!this.isInitControl){
			this.control({
				'permission_role_editView button[action=confirm]':{
					click : this.onAddConfirm
				 },
				'permission_role_tree' : {
					checkchange : this.onCheckChange,
					beforeload : this.onBeforeLoadRoleTree,
					beforerender : this.onBeforerender,
					afterrender : this.onAfterrender
				},
				'permission_role_tree button[action=checkAll]' : {
					click : this.onCheckAll
				},
				'permission_role_tree button[action=checkNone]' : {
					click : this.onCheckNone
				},
				'permission_role_tree button[action=expandAll]' : {
					click : this.onExpandAll
				},
				'permission_role_tree button[action=collapseAll]' : {
					click : this.onCollapseAll
				}
			});
			this.isInitControl = true;
		}

	},

	onBeforerender : function(){
		var tree = this.getRoleTree();
		var root = tree.getRootNode();
		root.expand(true);
	},

	onAfterrender : function(){
		var buttons = Ext.ComponentQuery.query('permission_role_tree button');
		Ext.each(buttons,function(btn){
			btn.enable();
		});
	},

	onBeforeLoadRoleTree : function(store) {
		store.getProxy().extraParams.roleId = this.roleId;
	},

	onCheckAll : function() {
		var addBtn = this.getAddBtn();
		addBtn.disable();
		var tree = this.getRoleTree();
		var root = tree.getRootNode();
		root.set("checked", true);
		root.expand(true, function() {
			root.cascadeBy(function(child) {
				child.set('checked', true);
			});
		});
		addBtn.enable();
	},

	onCheckNone : function() {
		var tree = this.getRoleTree();
		var root = tree.getRootNode();
		root.set("checked", false);
		root.cascadeBy(function(child) {
			child.set('checked', false);
		});
	},

	onExpandAll : function(){
		var tree = this.getRoleTree();
		var root = tree.getRootNode();
		root.expandChildren(true);
	},

	onCollapseAll: function(){
		var tree = this.getRoleTree();
		var root = tree.getRootNode();
		root.collapseChildren(true);
	},

	onCheckChange : function(node, checked, options) {
		if (!node.isLeaf()) {
			if (!node.isExpanded()) {
				node.expand(true, function() {
					node.cascadeBy(function(child) {
						child.set('checked', checked);
					});
				});
			} else {
				node.cascadeBy(function(child) {
					child.set('checked', checked);
				});
			}
		}
		if (checked) {
			var p = node.parentNode;
			while (p != null) {
				p.set("checked", true);
				p = p.parentNode;
			}
		} else {
			this.removeParentChecked(node.parentNode, false);
		}
	},
	removeParentChecked : function(node, checked) {
		var hasBrotherChecked = false;
		if (node != null) {
			node.eachChild(function(child) {
				if (child.get('checked')) {
					hasBrotherChecked = true;
					return true;
				}
			});
			if (!hasBrotherChecked) {
				node.set("checked", false);
				this.removeParentChecked(node.parentNode, checked);
			}
		}
	},

	onAddConfirm : function(){
		var win = this.getEwayView();
		var form = win.down('form').getForm();
		var data = form.getValues();
		var bool = win.down('form').getForm().isValid();
		if(bool == true){
			if(!data.id){
				//增加页面
				this.addRecord(data);
			}else{
				//修改页面
				this.updateRecord(form);
			}
		}else{
			Eway.alert('存在不合法的输入项.');
		}
	},
	updateRecord : function(updateForm){
		var me = this;
		var data = updateForm.getValues();
		var win = this.getEwayView();
		var grid = Ext.ComponentQuery.query('permission_role_grid')[0];
		var record = grid.getSelectionModel().getLastSelected();
		var name = record.data.name;
		this.getPermissions(record);//得到权限列表
		record.set('name',data.name);
		record.set('description',data.description);
		record.save({//save操作自动识别是增加还是修改操作，根据主键是否有值来判断的。
	 		success: function(ed) {
	 			Eway.alert(Eway.updateSuccess);
	    		win.close();

	    		this.queryRoleGrid();

		 	},
	 		failure: function(record,operation){
				Eway.alert(operation.getError());
				record.set('name',name);
	 		},
		 scope: me //增加这句后，可以调用this.onQuery();
		});
	},

	getRoleStore : function(){
		return Ext.StoreMgr.get("permission.Role");
	},
	getRoleModel : function(){
		return Ext.ModelManager.getModel("permission.Role");
	},
	getPermissions : function(record){
		var tree = this.getRoleTree();
		if (tree) {
			var checks = "";
			for ( var i = 0; i < tree.getChecked().length; i++) {
				var node = tree.getChecked()[i];
				checks = checks + "," + node.getId();
			}
			if (checks != "") {
				checks = checks.substring(1);
			}
			record.set('permissions', checks);
		}
	},
	addRecord : function(data) {
		var me = this;
		var win = this.getEwayView();
		var record = Ext.create('Eway.model.permission.Role',data);
		this.getPermissions(record);
		var addBtn = this.getAddBtn();
		addBtn.disable();
		record.set('id', 0);
		record.save({
			 success: function(record,operation) {
				 Eway.alert(Eway.addSuccess);
				  win.close();
				  this.queryRoleAdd();
			 	},
			 failure: function(record,operation){
					Eway.alert(operation.getError());
					addBtn.enable();
				 },
		   scope: me
			});
	},

	queryRoleGrid :function(){
		var roleController = this.getController("permission.Role");
		roleController.onQuery();
	},

	queryRoleAdd :function(){
		var roleController = this.getController("permission.Role");
		roleController.onQueryAfterAdd();
	}


});