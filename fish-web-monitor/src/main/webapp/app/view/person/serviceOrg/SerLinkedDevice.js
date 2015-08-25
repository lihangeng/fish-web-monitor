Ext.define('Eway.view.person.serviceOrg.SerLinkedDevice', {
	alias: 'widget.person_organization_serlinkWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.serviceOrg.LinkedDeviceSerGrid',
	           'Eway.view.person.organization.LinkedPersonGrid'],
	
	width: 800,
	height: 400,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : '该厂商的关联设备和人员',
			items : [{
				title : ' 该厂商下设备',
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'linkedDeviceSerGrid'
			},{
				title : '该厂商下人员',
				region: 'center',
				padding : 1,
				xtype : 'linkedPersonGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});