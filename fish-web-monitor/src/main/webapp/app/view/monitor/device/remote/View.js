Ext.define('Eway.view.monitor.device.remote.View', {
	extend : 'Eway.view.base.Panel',
	
	alias : 'widget.monitor_device_remote_view',
	
	requires : [ 'Eway.view.monitor.device.remote.FilterForm',
			'Eway.view.monitor.device.remote.Grid' ],

	title : EwayLocale.monitor.remoteCommand.titile,
	
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'monitor_device_remote_filterform'
			}, {
				region : 'center',
				xtype : 'monitor_device_remote_grid'
			} ]
		});
		this.callParent(arguments);
	}
});