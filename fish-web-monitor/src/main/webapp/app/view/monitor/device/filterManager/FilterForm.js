
Ext.define('Eway.view.monitor.device.filterManager.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.monitor_device_filtermanager_filterform',
	height : 35,
	initComponent: function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'textfield',
				fieldLabel : Eway.locale.monitor.devMonitor.filterManager.filterForm.filterName,
				name : 'filterName',
				maxLength : 20
			} ]
		});

		this.callParent(arguments);
	}
});