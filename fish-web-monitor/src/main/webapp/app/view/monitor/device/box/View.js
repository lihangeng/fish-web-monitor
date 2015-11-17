Ext.define('Eway.view.monitor.device.box.View', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_box_view',

	requires : [ 'Eway.view.monitor.device.box.FilterForm',
			'Eway.view.monitor.device.box.Grid' ],
	title : EwayLocale.monitor.devMonitor.cash.boxDetail,
	layout : 'border',
	width : 900,
	height : 400,
	modal : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'monitor_device_box_filterForm'
			}, {
				region : 'center',
				xtype : 'monitor_device_box_grid'
			} ]
		});
		this.callParent(arguments);
	}
});