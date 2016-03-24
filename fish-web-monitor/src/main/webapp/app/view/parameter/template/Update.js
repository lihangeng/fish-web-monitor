Ext.define('Eway.view.parameter.template.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_update',

	title: '更改模板',
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: [],

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>模板名称',
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel :  '模板备注',
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'update'
				},{
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				},{
					text: EwayLocale.button.cancle,
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