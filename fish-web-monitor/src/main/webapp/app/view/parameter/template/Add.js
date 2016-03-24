Ext.define('Eway.view.parameter.template.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_add',
	
	requires: [],
	
	title:'新增',
	modal: true,
	resizable: false,
	constrainHeader: true,

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
					msgTarget : 'side',
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : '模板备注',
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'add'
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