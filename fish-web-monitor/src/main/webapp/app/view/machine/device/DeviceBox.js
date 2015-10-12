
Ext.define('Eway.view.machine.device.DeviceBox', {
	alias: 'widget.device.deviceBox',
	extend: 'Eway.view.base.Grid',
		
	store: 'DeviceBox',
	id : 'deviceBoxId',
	columnLines:true,
	viewConfig: {
		loadMask:false //为了解决me.loadMask.bindStore(store)错误的问题
	},

	initComponent: function() {
		Ext.apply(this, {
			
			columns : [{
				header :'钞箱标识',
				sortable : true,
				dataIndex : 'log'
			}, {
				header : '钞箱类型',
				dataIndex : 'style',
				sortable : true
			}, {
				header : '钞箱状态',
				dataIndex : 'status',
				sortable : true
			},{
				header : '初始张数',
				dataIndex : 'initailnumber',
				sortable :true
			},{
				header : '存款张数',
				dataIndex : 'postnumber',
				sortable : true
			},{
				header : '当前钞箱张数',
				dataIndex : 'currentnumber',
				sortable : true
			},{
				header : Eway.locale.monitor.devMonitor.cash.currentCount,
				dataIndex : 'facevalue',
				sortable : true
			},{
				header : Eway.locale.monitor.devMonitor.cash.currency,
				dataIndex : 'currency',
				sortable : true
			}]
		});
		
		this.callParent(arguments);
	}
	
});