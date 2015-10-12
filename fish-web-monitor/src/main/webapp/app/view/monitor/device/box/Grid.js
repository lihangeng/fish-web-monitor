Ext.define('Eway.view.monitor.device.box.Grid', {
	alias : 'widget.monitor_device_box_grid',
	extend : 'Eway.view.base.Grid',
	store : 'monitor.device.DeviceBox',
	border : false,
	initComponent : function() {
		Ext.apply(this, {
			columns : [ {
				header : Eway.locale.monitor.devMonitor.cash.boxId,
				dataIndex : 'boxId'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.type,
				dataIndex : 'type'
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'binStatus'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.initialCount,
				dataIndex : 'initialCount'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.initialCount,
				dataIndex : 'cashInCount'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.currentCount,
				dataIndex : 'currentCount',
				width:120
			}, {
				header : Eway.locale.monitor.devMonitor.cash.currentCount,
				dataIndex : 'noteValue'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.currency,
				dataIndex : 'currency',
				flex:1
			} ]
		});
		this.callParent(arguments);
	}
});