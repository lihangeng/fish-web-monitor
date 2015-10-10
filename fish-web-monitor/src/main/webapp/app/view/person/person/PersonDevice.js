/**
 * 人员设备关联显示窗口：
 */
Ext.define('Eway.view.person.person.PersonDevice', {
	alias: 'widget.person_person_personDevice',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	requires: ['Eway.view.person.person.LinkedDeviceGrid',
	           'Eway.view.person.person.LinkedDeviceFilter',
	           'Eway.view.person.person.LinkingDeviceFilter',
	           'Eway.view.person.person.LinkingDeviceGrid'],
	
	width: 890,
	height: 350,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : Eway.locale.person.user.personDevice,
			items : [{
				region: 'west',
				width:'50%',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'person_linkedDeviceFilter'
				}, {
					region: 'center',
					xtype : 'person_linkedDeviceGrid',
					autoLoadStore : true
				}]
			},{
				region: 'center',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'person_linkingDeviceFilter'
				}, {
					region: 'center',
					xtype : 'person_linkingDeviceGrid',
					autoLoadStore : true
				}]
			}]
		});
		this.callParent(arguments);
	}
	
});