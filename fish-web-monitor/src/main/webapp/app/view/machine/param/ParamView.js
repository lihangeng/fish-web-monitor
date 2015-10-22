
Ext.define('Eway.view.machine.param.ParamView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.param_ParamView',

	requires: ['Eway.view.machine.param.ParamFilterForm',
	           'Eway.view.machine.param.ParamGrid'],

	title: Eway.locale.machine.param.systemCon,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'param_ParamFilterForm'
			}, {
				region: 'center',
				xtype: 'param_ParamGrid'
			}]
		});

		this.callParent(arguments);
	}
});