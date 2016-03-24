
Ext.define('Eway.view.parameter.element.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.element_add',

	requires: ['Eway.view.field.element.ParamName',
	           'Eway.view.field.element.ParamValue',
	           'Eway.view.field.element.ParamType',
	           'Eway.view.field.element.VersionNo',
	           'Eway.view.field.element.ParamRights',
	           'Eway.view.field.element.ParamBelongs',
	           'Eway.view.field.element.Remark',
	           'Eway.view.field.element.CreateTime',
	           'Eway.view.field.element.Name',],

	title:'新增',
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 400,
					labelWidth: 60,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype : 'field_element_ParamName',
					allowBlank : false
				},{
					xtype : 'field_element_ParamValue',
					allowBlank : false
				},{
					xtype : 'field_element_ParamType',
					allowBlank : false
				},{
					xtype : 'field_element_VersionNo',
					allowBlank : false
				},{
					xtype : 'field_element_ParamRights',
					allowBlank : false
				},{
					xtype : 'field_element_ParamBelongs',
					allowBlank : false
				},{
					xtype : 'field_element_Remark',
					allowBlank : false
				},{
					xtype : 'field_element_CreateTime',
					allowBlank : false
				},{
					xtype : 'field_element_LastModifyTime',
					allowBlank : false
				}],
				buttons: [{
					text: EwayLocale.button.update,
					action: 'confirm'
				}, {
					text: EwayLocale.button.reset,
					handler: this.onReset
				}, {
					text: EwayLocale.button.back,
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});