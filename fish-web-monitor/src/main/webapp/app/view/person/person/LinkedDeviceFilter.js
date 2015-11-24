Ext.define('Eway.view.person.person.LinkedDeviceFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.person_linkedDeviceFilter',
	height : 40,
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
//				labelAlign : 'right'
			},
			items : [ {
				columnWidth : .5,
				xtype : 'textfield',
				fieldLabel : EwayLocale.commen.terminalId,
				name : 'terminalId'
			} ]
		});
		this.callParent(arguments);
	}
});