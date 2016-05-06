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
					header : '应用系统',
					dataIndex : 'appSystem',
					width:150
				},{
					header : '状态',
					dataIndex : 'status',
					width:150
				},{
					header : '原因',
					dataIndex : 'reason',
					width:150
				}]
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});