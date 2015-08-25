Ext.define('Eway.view.permission.role.Tree', {
	alias : 'widget.permission_role_tree',
	extend : 'Ext.tree.Panel',

	initComponent : function() {
		Ext.apply(this, {
			rootVisible : false, //是否显示根(root)节点
			useArrows : true,
			autoScroll : true,
			store: Ext.create('Eway.store.permission.RoleTree'),
			dockedItems: [{
		        xtype: 'toolbar',
		        dock: 'top',
		        items: [{
		        	text:'请选择菜单权限',
		        	xtype:'tbtext'
		        },{
		            	text: '全部选择',
		            	action :'checkAll',
		            	disabled: true
		            },{
		            	text: '全部不选',
		            	action:'checkNone',
		            	disabled: true
		            }/*,{
		            	text: '全部展开',
		            	action:'expandAll',
		            	disabled: true
		            },{
		            	text: '全部合并',
		            	action:'collapseAll',
		            	disabled: true
		            }*/
		        ]
		    }]
		});

		this.callParent(arguments);
	}

});