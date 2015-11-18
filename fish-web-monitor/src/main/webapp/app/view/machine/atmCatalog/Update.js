
Ext.define('Eway.view.machine.atmCatalog.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmCatalog_update',
	
	requires: ['Eway.view.field.atmCatalog.Name',
	           'Eway.view.field.atmCatalog.No',
	           'Eway.view.field.atmCatalog.Note'],
	
	title: EwayLocale.machine.atmCatalog.updateTitle,
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
					xtype : 'field_atmCatalog_no',
					allowBlank : false
				},{
					xtype : 'field_atmCatalog_name',
					allowBlank : false
				},{
					xtype : 'field_atmCatalog_note',
					allowBlank : false
				}],
				fbar: [{
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