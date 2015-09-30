Ext.define('Eway.view.machine.atmCatalog.AtmCatalogFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmCatalog_AtmCatalogFilterForm',
	
	height: 40,
	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			
			items : [{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					name : 'name',
					fieldLabel : Eway.locale.machine.atmCatalog.name
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});