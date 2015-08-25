
Ext.define('Eway.view.person.user.UserLogGrid', {
	alias: 'widget.userLog_grid',
	extend: 'Eway.view.base.Grid',

	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.person.user.UserLog');
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:'查询',
				glyph : 0xf002,
				action:'query'
			}],
			columns : [{
				header : '操作人编号',
				dataIndex : 'operCode',
				width : 100
			}, {
				header : '操作人姓名',
				dataIndex : 'operName',
				width : 100
			}, {
				header : '操作时间',
				dataIndex : 'operTime',
				width : 150
			},	{
				header : '操作结果',
				dataIndex : 'operResult',
				width : 100
			},{
				header : '操作内容',
				dataIndex : 'operContent',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

//	onReload: function() {
//		this.getStore().load();
//	}


});