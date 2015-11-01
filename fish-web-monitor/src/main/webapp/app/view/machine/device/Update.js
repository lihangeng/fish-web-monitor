Ext.define('Eway.view.machine.device.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.device_info_update',

	requires: ['Eway.view.machine.device.Form'],

	title: '更改设备信息',
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
				text: '确认',
				action: 'update'
			}, {
				text: '取消',
				handler: this.onOver
			}]
		});

		this.callParent(arguments);
	},
	onOver: function() {
		this.up('window').close();
	}
});