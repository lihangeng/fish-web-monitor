Ext.define('Eway.view.monitor.device.box.Grid', {
	alias : 'widget.monitor_device_box_grid',
	extend : 'Eway.view.base.Grid',
	store : 'monitor.device.DeviceBox',
	border : false,
	initComponent : function() {
		Ext.apply(this, {
			columns : [ {
				header : EwayLocale.monitor.devMonitor.cash.boxId,
				dataIndex : 'boxId'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.type,
				dataIndex : 'typeName'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxStatus,
				dataIndex : 'binStatusName'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.initialCount,
				dataIndex : 'initialCount'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.cashInCount,
				dataIndex : 'cashInCount'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.currentCount,
				dataIndex : 'currentCount',
				width:120
			}, {
				header : EwayLocale.monitor.devMonitor.cash.noteValue,
				dataIndex : 'noteValue'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.currency,
				dataIndex : 'currency',
				flex:1
			} ]
		});
		this.callParent(arguments);
	}
});