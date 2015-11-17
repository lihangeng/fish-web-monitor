
Ext.define('Eway.view.agent.remote.UpFile', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_upFile',

	title : EwayLocale.agent.remote.uploadFile,
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 500,
	
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				defaults : {
					labelWidth : 80,
					anchor: '100%',
					labelAlign : 'right',
					msgTarget : 'side',
					grow : true
				},
				items : [{
					xtype: 'filefield',
					fieldLabel:EwayLocale.agent.remote.prepareFile,
					emptyText:EwayLocale.agent.remote.choseUploadFile,
					name: 'file',
					waitMsg:EwayLocale.agent.remote.nowUploadFile,
					allowBlank : false,
					buttonText: EwayLocale.agent.remote.explorer
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: EwayLocale.agent.remote.serverPath,
					name:'serverPath'
				} ],
				buttons : [ {
					text : EwayLocale.agent.remote.confirm,
					action : 'confirm'
				}, {
					text : EwayLocale.agent.remote.reset,
					handler : this.onReset
				}, {
					text : EwayLocale.agent.remote.back,
					handler : this.onOver
				} ]
			}
		});
		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});