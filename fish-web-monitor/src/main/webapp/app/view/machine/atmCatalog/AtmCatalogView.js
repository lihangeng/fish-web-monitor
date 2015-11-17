
Ext.define('Eway.view.machine.atmCatalog.AtmCatalogView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmCatalog_AtmCatalogView',
	
	requires: ['Eway.view.machine.atmCatalog.AtmCatalogGrid',
	           'Eway.view.machine.atmCatalog.AtmCatalogFilterForm'
	           ],
	
	title: EwayLocale.machine.atmCatalog.title,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'atmCatalog_AtmCatalogFilterForm'
			}, {
				region: 'center',
				xtype: 'atmCatalog_AtmCatalogGrid'
			}]
		});
		
		this.callParent(arguments);
	}
});