Ext.define('Eway.view.monitor.device.box.Grid', {
	alias : 'widget.monitor_device_box_grid',
	extend : 'Eway.view.base.Grid',
	store : 'monitor.device.DeviceBox',
	border : false,
	initComponent : function() {
		Ext.apply(this, {
			columns : [ {
				header : '钞箱标识',
				dataIndex : 'boxId'
			}, {
				header : '钞箱类型',
				dataIndex : 'type'
			}, {
				header : Eway.locale.monitor.devMonitor.boxStatus,
				dataIndex : 'binStatus'
			}, {
				header : '初始张数',
				dataIndex : 'initialCount'
			}, {
				header : '存款张数',
				dataIndex : 'cashInCount'
			}, {
				header : '当前计数[张/笔]',
				dataIndex : 'currentCount',
				width:120
			}, {
				header : '钞箱面值',
				dataIndex : 'noteValue'
			}, {
				header : '钞箱币种',
				dataIndex : 'currency',
				flex:1
			} ]
		});
		this.callParent(arguments);
	}
});