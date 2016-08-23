Ext.define('Eway.view.cash.initRule.UpdateWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.initRule_UpdateWin',

	title : EwayLocale.initRule.updateRule,
	modal : true,
	resizable : false,
	constrainHeader : true,

	initComponent : function() {
		var startUsingstate = Ext.create('Ext.data.Store', {
	    fields: ['value', 'name'],
		    data : [
		        {"value":"true", "name":"启用"},
		        {"value":"false", "name":"停用"}
		    ]
		});
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				maximizable : true,
				defaults : {
					width : 400,
					labelWidth : 100,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [ {
					xtype : 'displayfield',
					name : 'name',
					fieldLabel : EwayLocale.initRule.ruleName
				}, {
					xtype : 'displayfield',
					name : 'ruleDesc',
					fieldLabel : EwayLocale.initRule.ruleDesc
				}, {
					xtype : 'combobox',
					fieldLabel : EwayLocale.initRule.startUsing,
					name:'startUsing',
					editable:false,
					emptyText : EwayLocale.combox.select,
				    store: startUsingstate,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'value'
				}],
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
			}
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