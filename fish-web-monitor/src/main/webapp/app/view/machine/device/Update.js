Ext.define('Eway.view.machine.device.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.device_info_update',

	requires: ['Eway.view.machine.device.Form'],

	title: Eway.locale.machine.device.changeDevInfo,
	modal: true,
	resizable: false,
	constrainHeader: true,
    height : 450,
	width : 750,
	initComponent: function() {
		Ext.apply(this, {
			bodyStyle : 'padding: 10px 10px 30px 10px',
			trackResetOnLoad : true,
			selectOnFocus : true,
			defaults: {
				width: 350,
				labelWidth: 80,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items:[{
				xtype:'machine_device_form',
				width : 700,
				height : 450
			}],
			buttonAlign : 'center',
			fbar: [{
				text: Eway.locale.button.confirm,
				action: 'update'
			}, {
				text: Eway.locale.button.cancel,
				handler: this.onOver
			}]
		});

		this.callParent(arguments);
	},
	onOver: function() {
		this.up('window').close();
	}
});