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
		        	text:Eway.locale.permission.role.chooseRight,
		        	xtype:'tbtext'
		        },{
		            	text: Eway.locale.commen.selectAll,
		            	action :'checkAll',
		            	disabled: true
		            },{
		            	text: Eway.locale.commen.selectNon,
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