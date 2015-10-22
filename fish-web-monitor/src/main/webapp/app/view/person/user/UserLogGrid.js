
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
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			}],
			columns : [{
				header : Eway.locale.person.user.operCode,
				dataIndex : 'operCode',
				width : 100
			}, {
				header : Eway.locale.person.user.operName,
				dataIndex : 'operName',
				width : 100
			}, {
				header : Eway.locale.person.user.operTime,
				dataIndex : 'operTime',
				width : 150
			},	{
				header : Eway.locale.person.user.operResult,
				dataIndex : 'operResult',
				width : 100
			},{
				header : Eway.locale.person.user.operContent,
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