
Ext.define('Eway.view.machine.atmModule.AtmModuleView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmModule_AtmModuleView',
	
	requires: ['Eway.view.machine.atmModule.AtmModuleGrid',
	           'Eway.view.machine.atmModule.AtmModuleFilterForm'
	           ],
	
	title: Eway.locale.machine.atmModule.atmModules,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'atmModule_AtmModuleFilterForm'
			}, {
				region: 'center',
				xtype: 'atmModule_AtmModuleGrid'
			}]
		});
		
		this.callParent(arguments);
	}
});