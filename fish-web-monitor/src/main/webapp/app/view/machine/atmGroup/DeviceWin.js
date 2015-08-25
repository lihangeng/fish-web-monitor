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
	
	width: 940,
	height: 600,
	maximizable: true,
	layout : 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : '分组<-->设备',
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