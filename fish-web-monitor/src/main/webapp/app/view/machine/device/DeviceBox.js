
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
				header :Eway.locale.machine.device.log,
				sortable : true,
				dataIndex : 'log'
			}, {
				header : Eway.locale.machine.device.style,
				dataIndex : 'style',
				sortable : true
			}, {
				header : Eway.locale.machine.device.status,
				dataIndex : 'status',
				sortable : true
			},{
				header : Eway.locale.machine.device.initailnumber,
				dataIndex : 'initailnumber',
				sortable :true
			},{
				header : Eway.locale.machine.device.postnumber,
				dataIndex : 'postnumber',
				sortable : true
			},{
				header : Eway.locale.machine.device.currentnumber,
				dataIndex : 'currentnumber',
				sortable : true
			},{
				header : Eway.locale.machine.device.facevalue,
				dataIndex : 'facevalue',
				sortable : true
			},{
				header : Eway.locale.machine.device.currency,
				dataIndex : 'currency',
				sortable : true
			}]
		});
		
		this.callParent(arguments);
	}
	
});