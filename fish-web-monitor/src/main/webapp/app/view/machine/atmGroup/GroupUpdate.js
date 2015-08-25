/**
 * 设备组信息修改窗口：
 */
Ext.define('Eway.view.machine.atmGroup.GroupUpdate', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmGroup_groupUpdate',

	title: '更改设备组信息',
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: [],

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 400,
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font> 组名',
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : '备注',
				    name : 'note',
				    autoScroll : true,
					maxLength :30,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: '确认',
					action: 'update'
				}, {
					text: '重置',
					handler: this.onReset,
					hidden : true
				}, {
					text: '取消',
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