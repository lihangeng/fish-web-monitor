Ext.define('Eway.view.monitor.device.filterManager.FilterWin',{

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_filtermanager_filterwin',
	layout : 'border',
	
	width : 1024,
	height : 600,
	modal : true,
	
	title : Eway.locale.monitor.devMonitor.filterManager.title,
	
	requires : [ 'Eway.view.monitor.device.filterManager.Grid',
				  'Eway.view.monitor.device.filterManager.FilterForm' ],
				  
	initComponent : function() {
		Ext.apply(this, {
			items: [ {
				region: 'north',
				xtype: 'monitor_device_filtermanager_filterform'
			}, {
				region: 'center',
				xtype: 'monitor_device_filtermanager_grid'
			} ],
			listeners : {
				scope : this
			}
		});
		this.callParent(arguments);
	}
});