
Ext.define('Eway.view.agent.remote.RemoteMkFileSys', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_mkFileSys',

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
					xtype: 'textfield',
					fieldLabel:'',
//					regex:/^[a-zA-Z]:?\\(?:[^\\\?\/\*\|<>:"]+\\)*[^\\\?\/\*\|<>:"]*?\.*[^.\\\?\/\*\|<>:"]*$/,
					regex:/^[^\\\/\:\*\?\"\<\>\|\,]+(\,[^\\\/\:\*\?\"\<\>\|\,]+)*$/,
					regexText:Eway.locale.agent.remote.rules,
					name: 'file',
					waitMsg:Eway.locale.agent.remote.nowCreat,
					allowBlank : false
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: Eway.locale.agent.remote.nowPath,
					name:'nowPath'
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