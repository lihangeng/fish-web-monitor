Ext.define('Eway.view.machine.atmGroup.DeviceView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.machine_atmGroup_deviceView',

	requires : [ 
	             'Eway.view.machine.atmGroup.DeviceFilter',
			'Eway.view.machine.atmGroup.DeviceGrid' ],

	layout : 'border',
	name:'atmGroupDeviceDetails',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region: 'north',
				xtype : 'atmGroup_deviceFilter'
			}, {
				region: 'center',
				xtype : 'atmGroup_deviceGrid'
			}]
		});

		this.callParent(arguments);
	}
});