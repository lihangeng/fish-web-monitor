Ext.define('Eway.view.monitor.device.remote.Grid', {
	alias : 'widget.monitor_device_remote_grid',
	extend : 'Eway.view.base.Grid',
	requires : [ 'Eway.lib.Util' ],
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.device.RemoteCommand');
//		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header : EwayLocale.commen.orgNameBelongs,
				width : 160,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.monitor.remoteCommand.grid.commandType,
				width : 150,
				dataIndex : 'commandType'
			}, {
				header : EwayLocale.monitor.remoteCommand.grid.commandResult,
				width : 150,
				dataIndex : 'commandResult'
			}, {
				header : EwayLocale.monitor.remoteCommand.grid.datetime,
				width : 150,
				dataIndex : 'datetime'
			}, {
				header : EwayLocale.monitor.remoteCommand.grid.handlePerson,
				dataIndex : 'handlePerson',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : EwayLocale.commen.toolbar
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});