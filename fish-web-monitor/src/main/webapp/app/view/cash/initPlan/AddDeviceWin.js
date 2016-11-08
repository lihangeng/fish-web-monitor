Ext.define('Eway.view.cash.initPlan.AddDeviceWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.initPlan_AddDeviceWin',

	title : EwayLocale.initPlan.addDeviceWin,
	modal : true,
	resizable : true,
	constrainHeader : true,
    maximizable: true,
    minWidth: 600,
    minHeight: 400,
	width : 900,
	height : 450,
	autoScroll : true,
	layout: 'fit',

	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype : 'initPlan_detailSelectableFilterForm'
				}, {
					region : 'center',
					xtype : 'initPlan_detailSelectableGrid'
			} ],
			buttonAlign : 'center',
			buttons : [ {
				xtype : 'button',
				text : EwayLocale.cases.confirm,
				action : 'confirm'
			}, {
				xtype : 'button',
				text : EwayLocale.cases.cancel,
				handler : this.onOver
			} ]
		});

		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});