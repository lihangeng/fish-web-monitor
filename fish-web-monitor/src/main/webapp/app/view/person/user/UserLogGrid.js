
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
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			}],
			columns : [{
				header : EwayLocale.person.user.operCode,
				dataIndex : 'operCode',
				width : 100
			}, {
				header : EwayLocale.person.user.operName,
				dataIndex : 'operName',
				width : 100
			}, {
				header : EwayLocale.person.user.operTime,
				dataIndex : 'operTime',
				width : 150
			},	{
				header : EwayLocale.person.user.operResult,
				dataIndex : 'operResult',
				width : 100
			},{
				header : EwayLocale.person.user.operContent,
				dataIndex : 'operContent',
				width : 270
			},{
				header : EwayLocale.person.user.serverIp,
				dataIndex : 'serverIp',
				width : 150
			},{
				header : EwayLocale.person.user.clientIP,
				dataIndex : 'clientIP',
				width : 150
			},{
				header : EwayLocale.person.user.times,
				dataIndex : 'times',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}

//	onReload: function() {
//		this.getStore().load();
//	}


});