
Ext.define('Eway.view.machine.param.ParamFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.param_ParamFilterForm',
	
	
	requires: ['Eway.view.field.param.ParamKey',
	           'Eway.view.field.param.ParamValue'],
	
	layout : 'column',
	height: 40,
	initComponent: function() {
		Ext.apply(this, {
			
			items : [{
				columnWidth : .4,
				items : [{
					xtype : 'field_param_ParamKey',
					labelAlign : 'right'
				}]
			},{
				columnWidth : .4,
				items : [{
					xtype : 'field_param_ParamValue',
					labelAlign : 'right'
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});