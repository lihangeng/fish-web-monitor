Ext.define('Eway.view.permission.role.Form',{
	extend : 'Ext.form.Panel',
	alias : 'widget.permission_role_form',

	requires: [
    	'Eway.view.field.RoleDescription',
    	'Eway.view.field.RoleName'
    ],

	initComponent : function(){
		var me = this;
		var treeStore = Ext.create('Eway.store.permission.RoleTree');
		Ext.apply(this,{
			bodyPadding: 8,
			items: [{
				items : [{
					xtype:'field_roleName'
				},{
					xtype:'field_roleDescription'
				},{
					xtype : 'treepanel',
					store : treeStore,
					rootVisible : false,
					useArrows : true,
					autoScroll : true,
					height: 360,
					tbar : [{
			        	text:'请选择菜单权限',
			        	xtype:'tbtext'
			        },{
						xtype : 'button',
						text : '全部选择',
						handler : this.onCheckAll,
						scope : this
					},{
						xtype : 'button',
						text : '全部不选',
						handler : this.onCheckNone,
						scope : this
					}],
					listeners : {
						checkchange : this.onCheckChange,
						scope : this
					}
				}]
			}]
			}
		);
		this.callParent(arguments);
	},

	onCheckAll : function(button) {
		button.disable();
		var tree = button.up('treepanel');
		var root = tree.getRootNode();
		this._expandChild(root,true);
		button.enable();
	},

	_expandChild : function(parentNode,checked){
		parentNode.set("checked", true);
		parentNode.expand(true, function() {
			parentNode.cascadeBy(function(child) {
				child.set('checked', checked);
			});
		});
	},

	onCheckNone : function(button) {
		var tree = button.up('treepanel');
		var root = tree.getRootNode();
		this._expandChild(root,false);
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
					child.expand(true, function(){
						child.cascadeBy(function(childNode){
							childNode.set('checked',checked);
						});
					});
				});

			}
		}
		if (checked) {
			var p = node.parentNode;
			while (p != null) {
				if(!p.isRoot()){
					p.set("checked", true);
				}
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

	getCusValues : function(){
		var formValues = this.getValues();
		var tree = this.down('treepanel');
		var records = tree.getChecked();
		var permissions = this.getPerminssions();
		formValues.permissions=permissions;
		return formValues;
	},

	setCusValues : function(record){
		this.getForm().loadRecord(record);
		var roleId = record.get('id');
		var tree = this.down('treepanel');
		tree.on('beforeitemexpand',function(node){
			tree.getStore().getProxy().setExtraParam('roleId',record.get('id'));
		},this);
//		tree.getRootNode().expand();
	},

	loadCusRecord : function(record){
		this.getForm().loadRecord(record);
		var roleId = record.get('id');
		var tree = this.down('treepanel');
		tree.on('beforeitemexpand',function(node){
			tree.getStore().getProxy().setExtraParam('roleId',record.get('id'));
		},this);
		tree.getRootNode().expand();
	},

	updateCusRecord : function(record){
		var tree = this.down('treepanel');
		var records = tree.getChecked();
		var permissions = this.getPerminssions();
		record.set('permissions',permissions);
		this.getForm().updateRecord(record);
	},

	getPerminssions : function(){
		var tree = this.down('treepanel');
		var records = tree.getChecked();
		var permissions = '';
		for(var i in records){
			var record = records[i];
			var code = record.get('id');
			permissions += code+',';
		}
		permissions = permissions.substring(0,permissions.length-1);
		return permissions;
	}
});