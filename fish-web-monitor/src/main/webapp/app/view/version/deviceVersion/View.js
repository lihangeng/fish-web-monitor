Ext.define('Eway.view.version.deviceVersion.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.deviceVersionView',

	requires : [ 'Eway.view.version.deviceVersion.Grid', 'Eway.view.version.deviceVersion.FilterForm'],

	title : '设备版本',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype: 'deviceVersion_filterForm'
				}, {
					region : 'center',
					xtype : 'deviceVersion_grid'
				} 
			],
			listeners : {
				activate : function(panel){
					panel.down('deviceVersion_filterForm').down('field_card_DeviceAtmVendorComboBox').getStore().load();
					panel.down('deviceVersion_filterForm').down('field_card_DeviceTypeComboBox').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});