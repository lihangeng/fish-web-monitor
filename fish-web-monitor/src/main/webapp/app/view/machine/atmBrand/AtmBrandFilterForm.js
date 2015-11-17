
Ext.define('Eway.view.machine.atmBrand.AtmBrandFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmBrand_AtmBrandFilterForm',
	
	
	requires: ['Eway.view.field.atmBrand.StatusComboBox',
	           'Eway.view.field.atmBrand.Country'],
	
	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .3,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'name',
					fieldLabel : EwayLocale.machine.atmBrand.name,
					maxLength : 20,
					msgTarget : 'side'
				}]
			},{
				columnWidth : .3,
				items : [{
					xtype : 'field_atmBrand_statusComboBox',
					labelAlign : 'right',
					editable:false
				}]
			},{
				columnWidth : .3,
				items : [{
					xtype : 'field_atmBrand_country',
					labelWidth: 110,
					msgTarget : 'side',
					maxLength : 30,
					labelAlign : 'right'
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});