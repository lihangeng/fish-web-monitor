Ext.define('Eway.view.person.person.LinkingDeviceFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.person_linkingDeviceFilter',
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