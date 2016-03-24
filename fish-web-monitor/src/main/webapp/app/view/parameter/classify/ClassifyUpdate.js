/**
 * 参数元数据分组管理修改窗口：
 */
Ext.define('Eway.view.parameter.classify.ClassifyUpdate', {
	extend: 'Ext.window.Window',
	alias: 'widget.classify_ClassifyUpdate',

	title: '更改参数元数据分类信息',// EwayLocale.machine.atmGroup.updateTitle,
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
					fieldLabel : '<font color="red">*</font>参数名',// +EwayLocale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel :  '参数备注',// EwayLocale.machine.atmGroup.note,
				    name : 'note',
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