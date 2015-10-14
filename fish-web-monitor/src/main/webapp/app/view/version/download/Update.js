
Ext.define('Eway.view.version.download.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.version_download_update',
	
	requires : ['Eway.view.version.field.VersionName', 'Eway.view.version.field.Desc'],
	title: Eway.locale.version.download.updateTitle,//'修改版本下发信息',
	modal: true,
	resizable: false,
	constrainHeader: true,
	width: 500,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				selectOnFocus : true,
				defaults: {
					width: 320,
					labelWidth: 60,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype: 'field.versionName',
					allowBlank: false
				},{
					xtype: 'field.desc'
				}],
				fbar: [{
					text: Eway.locale.button.update,//'更改',
					action:'confirm'
				}, {
					text: Eway.locale.button.reset,//'重置',
					handler: this.onReset
				}, {
					text: Eway.locale.button.back,//'返回',
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