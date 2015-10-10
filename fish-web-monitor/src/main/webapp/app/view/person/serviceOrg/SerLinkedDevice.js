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
			title : Eway.locale.person.serviceOrg.personDevSerLink,
			items : [{
				title : Eway.locale.person.serviceOrg.devSerLink,
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'linkedDeviceSerGrid'
			},{
				title : Eway.locale.person.serviceOrg.personSerLink,
				region: 'center',
				padding : 1,
				xtype : 'linkedPersonGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});