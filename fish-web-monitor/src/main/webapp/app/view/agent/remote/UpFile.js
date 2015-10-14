
Ext.define('Eway.view.agent.remote.UpFile', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_upFile',

	title : Eway.locale.agent.remote.uploadFile,
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
					fieldLabel:Eway.locale.agent.remote.prepareFile,
					emptyText:Eway.locale.agent.remote.choseUploadFile,
					name: 'file',
					waitMsg:Eway.locale.agent.remote.nowUploadFile,
					allowBlank : false,
					buttonText: Eway.locale.agent.remote.explorer
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: Eway.locale.agent.remote.serverPath,
					name:'serverPath'
				} ],
				buttons : [ {
					text : Eway.locale.agent.remote.confirm,
					action : 'confirm'
				}, {
					text : Eway.locale.agent.remote.reset,
					handler : this.onReset
				}, {
					text : Eway.locale.agent.remote.back,
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