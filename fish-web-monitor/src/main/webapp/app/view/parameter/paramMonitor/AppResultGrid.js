Ext.define('Eway.view.parameter.paramMonitor.AppResultGrid', {
	alias : 'widget.parameter_paramMonitor_AppResultGrid',
	extend : 'Eway.view.base.Grid',

	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.paramMonitor.TaskStatus');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			 columns : [{
					header : EwayLocale.param.element.paramBelongs,
					dataIndex : 'appSystem',
					width:120
				},{
					header : EwayLocale.param.paramDownloadMonitor.taskStatus,
					dataIndex : 'status',
					width:160
				},{
					header : EwayLocale.param.paramDownloadMonitor.reason,
					dataIndex : 'reason',
					flex:1
				}]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});