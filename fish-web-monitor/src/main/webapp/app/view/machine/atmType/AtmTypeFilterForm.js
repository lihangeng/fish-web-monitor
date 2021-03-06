Ext.define('Eway.view.machine.atmType.AtmTypeFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.atmType_AtmTypeFilterForm',

	requires : [ 'Eway.view.field.atmType.CashtypeComboBox',
			'Eway.view.field.atmType.Dev_catalog',
			'Eway.view.field.atmType.Dev_vendor',
			'Eway.view.field.atmType.DeviceAtmCatalogComboBox' ],

	layout : 'column',
	height : 40,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .3,
				items : [ {
					xtype : 'textfield',
					name : 'name',
					fieldLabel : EwayLocale.machine.atmType.name,
					msgTarget : 'side',
					labelAlign : 'right'
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field_atmType_cashtypeComboBox',
					labelAlign : 'right',
					editable : false
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});