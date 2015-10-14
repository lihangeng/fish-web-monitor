Ext.define('Eway.view.report.openrate.device.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_openrate_device_view',

	requires : [ 
			'Eway.view.report.openrate.device.FilterForm',
			'Eway.view.report.openrate.device.Grid' ],

	title : Eway.locale.report.openrate.device.devOpenRate,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'report_openrate_device_filterform'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'report_openrate_device_grid'
			} ]
		});
		this.callParent(arguments);
	}
});