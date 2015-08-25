Ext.define('Eway.view.advert.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.advert_update',
	
	requires : [/*'Eway.view.version.field.VersionName', 'Eway.view.version.field.Desc'*/],
	title: '更改广告信息',
	modal: true,
	resizable: false,
	constrainHeader: true,
	width: 500,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				selectOnFocus : true,
				defaults: {
					width: 320,
					labelWidth: 60,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [],
				fbar: [{
					text: '更改',
					id: 'updateVersionConfirmId'
				}, {
					text: '重置',
					handler: this.onReset
				}, {
					text: '返回',
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