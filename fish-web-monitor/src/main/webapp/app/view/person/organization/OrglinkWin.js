Ext.define('Eway.view.person.organization.OrglinkWin', {
	alias: 'widget.person_organization_orglinkWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.organization.LinkedDeviceGrid',
	           'Eway.view.person.organization.LinkedPersonGrid'],
	
	width: 890,
	height: 350,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : Eway.locale.person.bankOrg.orgLinkTitle,
			items : [{
				title : Eway.locale.person.bankOrg.machineOrg,
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'linkedDeviceGrid'
			},{
				title : Eway.locale.person.bankOrg.personOrg,
				region: 'center',
				padding : 1,
				xtype : 'linkedPersonGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});