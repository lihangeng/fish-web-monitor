Ext.define('Eway.controller.monitor.device.DeviceBox', {
	extend : 'Eway.controller.base.Controller',
	views : [ 'monitor.device.box.View' ],
	stores : [ 'monitor.device.DeviceBox' ],
	models : [ 'monitor.device.DeviceBox' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#monitor_device_box_view',
		autoCreate : true,
		xtype : 'monitor_device_box_view',
		id : 'monitor_device_box_view'
	} ],
	displayWin : function(record) {
		var win = this.getEwayView();
		record.data.dispenseAmount = record.data.initAmount-record.data.amount;
		win.down('form').getForm().loadRecord(record);
		win.down('grid').getStore().loadRecords(record.boxList().getRange(0,record.boxList().getCount()));
		win.show();
	}
});