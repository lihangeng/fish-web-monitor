
Ext.define('Eway.view.parameter.template.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.template_FilterForm',


	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'name',
					fieldLabel : EwayLocale.param.template.templateName,
					maxLength : 30,
					msgTarget : 'side'
				}]
			}]
		});

		this.callParent(arguments);
	}
});