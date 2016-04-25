Ext.define('Eway.view.parameter.template.TemplateDevice', {
	alias: 'widget.parameter_templateDevice',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	requires: ['Eway.view.parameter.template.LinkedDeviceGrid',
	           'Eway.view.parameter.template.LinkedDeviceFilter',
	           'Eway.view.parameter.template.LinkingDeviceFilter',
	           'Eway.view.parameter.template.LinkingDeviceGrid'],
	
	width: 1200,
	height: 550,
	maximizable: true,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			title : EwayLocale.param.template.bankPerlink,
			items : [{
				region: 'west',
				width:'50%',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'parameter_linkedDeviceFilter'
				}, {
					region: 'center',
					xtype : 'parameter_linkedDeviceGrid',
					autoLoadStore : true
				}]
			},{
				region: 'center',
				padding : 1,
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'parameter_linkingDeviceFilter'
				}, {
					region: 'center',
					xtype : 'parameter_linkingDeviceGrid',
					autoLoadStore : true
				}]
			}]
		});
		this.callParent(arguments);
	}
	
});