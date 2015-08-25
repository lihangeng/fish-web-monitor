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
			title : '该机构的关联设备和人员',
			items : [{
				title : ' 该机构下设备',
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'linkedDeviceGrid'
			},{
				title : '该机构下人员',
				region: 'center',
				padding : 1,
				xtype : 'linkedPersonGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});