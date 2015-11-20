Ext.define('Eway.view.machine.device.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.device_info_add',

	requires: ['Eway.view.machine.device.Form'],

	title: EwayLocale.machine.device.addDevInfo,

	modal: true,
	resizable: false,
	constrainHeader: true,
	width : 800,
	initComponent: function() {
		Ext.apply(this, {
			bodyStyle : 'padding: 10px 10px 10px 10px',
			trackResetOnLoad : true,
			selectOnFocus : true,
			items:[{
				xtype:'machine_device_form'
			}],
			buttonAlign : 'center',
			fbar: [{
				text: EwayLocale.button.confirm,
				action: 'add'
			}, {
				text: EwayLocale.button.cancel,
				handler: this.onOver
			}]
		});

		this.callParent(arguments);
	},
	onOver: function() {
		this.up('window').close();
	}
});