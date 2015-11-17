
Ext.define('Eway.view.agent.remote.RemoteMkFileSys', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_mkFileSys',

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
					xtype: 'textfield',
					fieldLabel:'',
//					regex:/^[a-zA-Z]:?\\(?:[^\\\?\/\*\|<>:"]+\\)*[^\\\?\/\*\|<>:"]*?\.*[^.\\\?\/\*\|<>:"]*$/,
					regex:/^[^\\\/\:\*\?\"\<\>\|\,]+(\,[^\\\/\:\*\?\"\<\>\|\,]+)*$/,
					regexText:EwayLocale.agent.remote.rules,
					name: 'file',
					waitMsg:EwayLocale.agent.remote.nowCreat,
					allowBlank : false
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: EwayLocale.agent.remote.nowPath,
					name:'nowPath'
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