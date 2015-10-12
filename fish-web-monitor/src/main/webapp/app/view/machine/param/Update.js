Ext.define('Eway.view.machine.param.Update', {
	extend : 'Ext.window.Window',
	alias : 'widget.param_update',

	requires : [ 'Eway.view.field.param.ParamKey',
			'Eway.view.field.param.ParamValue',
			'Eway.view.field.param.Description' ],

	title : Eway.locale.machine.param.updateSystemCon,
	modal : true,
	resizable : false,
	constrainHeader : true,

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults : {
					width : 400,
					labelWidth : 60,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [ {
					xtype : 'field_param_ParamKey',
					disabled : true
				}, {
					xtype : 'field_param_ParamValue',
					allowBlank : false
				}, {
					xtype : 'field_param_Description',
					maxLength : 40,
					allowBlank : false
				} ],
				buttonAlign : 'center',
				buttons : [ {
					xtype : 'button',
					text : Eway.locale.button.confirm,
					action : 'confirm'
				}, {
					xtype : 'button',
					text : Eway.locale.button.back,
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