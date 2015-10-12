/**
 * 设备组信息修改窗口：
 */
Ext.define('Eway.view.machine.atmGroup.GroupUpdate', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmGroup_groupUpdate',

	title: Eway.locale.machine.atmGroup.updateTitle,
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
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : Eway.locale.machine.atmGroup.note,
				    name : 'note',
				    autoScroll : true,
					maxLength :30,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					action: 'update'
				}, {
					text: Eway.locale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: Eway.locale.button.cancle,
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