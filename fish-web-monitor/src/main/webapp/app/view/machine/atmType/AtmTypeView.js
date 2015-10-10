
Ext.define('Eway.view.machine.atmType.AtmTypeView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmType_AtmTypeView',

	requires: ['Eway.view.machine.atmType.AtmTypeGrid',
	           'Eway.view.machine.atmType.AtmTypeFilterForm'
	           ],

	title: Eway.locale.commen.devTypeName,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				itemId : 'filterFormItemId',
				region: 'north',
				xtype: 'atmType_AtmTypeFilterForm'
			}, {
				itemId : 'gridItemId',
				region: 'center',
				xtype: 'atmType_AtmTypeGrid'
			}]
		});

		this.callParent(arguments);
	}
});