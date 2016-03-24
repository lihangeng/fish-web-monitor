
Ext.define('Eway.view.parameter.element.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.element_FilterForm',


//	requires: ['Eway.view.field.atmBrand.StatusComboBox',
//	           'Eway.view.field.atmBrand.Country'],

	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'paramName',
					fieldLabel : '参数名',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					labelWidth: 110,
					name : 'paramValue',
					fieldLabel :'参数值',
					msgTarget : 'side',
					labelAlign : 'right'
				}]
			}]
		});

		this.callParent(arguments);
	}
});