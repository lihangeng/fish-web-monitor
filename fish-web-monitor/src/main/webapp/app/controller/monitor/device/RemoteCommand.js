Ext.define('Eway.controller.monitor.device.RemoteCommand', {
	extend : 'Eway.controller.base.FishController',
	views : [ 'monitor.device.remote.View' ],
	stores : [ 'monitor.device.RemoteCommand' ],
	models : [ 'monitor.device.RemoteCommand' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#monitor_device_remote_view',
		autoCreate : true,
		xtype : 'monitor_device_remote_view'
	} ],
	init : function(){
		this.control({
			'monitor_device_remote_view button[action=query]' : {
				click : this.onQuery,
				scope : this
			}
		});
	}
});