/**
 * 设备显示窗口：
 */
Ext.define('Eway.view.machine.atmGroup.DeviceWin', {
	alias: 'widget.atmGroup_deviceWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	requires: ['Eway.view.machine.atmGroup.DeviceAddingGrid',
	           	'Eway.view.machine.atmGroup.DeviceFilter'],
	
	width: 1040,
	height: 600,
	maximizable: true,
	layout : 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : EwayLocale.machine.atmGroup.groupDev,
			items : [{
				region: 'north',
				xtype: 'atmGroup_deviceFilter'
			},{
				region: 'center',	
				xtype : 'atmGroup_deviceAddingGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});