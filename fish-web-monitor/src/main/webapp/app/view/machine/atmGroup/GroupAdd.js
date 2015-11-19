
Ext.define('Eway.view.machine.atmGroup.GroupAdd', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmGroup_groupAdd',
	
	requires: [],
	
	title: EwayLocale.machine.atmGroup.addTitle,
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
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : EwayLocale.machine.atmGroup.note,
				    name : 'note',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'add'
				}, {
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
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