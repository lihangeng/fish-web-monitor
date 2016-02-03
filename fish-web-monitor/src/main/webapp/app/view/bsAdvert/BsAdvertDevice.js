/**
 * 人员设备关联显示窗口：
 */
Ext.define('Eway.view.bsAdvert.BsAdvertDevice', {
	alias: 'widget.bsAdvert_bsAdvertDevice',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	requires: ['Eway.view.bsAdvert.LinkedDeviceGrid',
	           'Eway.view.bsAdvert.LinkedDeviceFilter',
	           'Eway.view.bsAdvert.LinkingDeviceFilter',
	           'Eway.view.bsAdvert.LinkingDeviceGrid'],
	
	width: 1200,
	height: 550,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : '广告组<-->设备',
			items : [{
				region: 'west',
				width:'50%',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'bsAdvert_linkedDeviceFilter'
				}, {
					region: 'center',
					xtype : 'bsAdvert_linkedDeviceGrid',
					autoLoadStore : true
				}]
			},{
				region: 'center',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'bsAdvert_linkingDeviceFilter'
				}, {
					region: 'center',
					xtype : 'bsAdvert_linkingDeviceGrid',
					autoLoadStore : true
				}]
			}]
		});
		this.callParent(arguments);
	}
	
});