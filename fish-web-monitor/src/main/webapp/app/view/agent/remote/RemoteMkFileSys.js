
Ext.define('Eway.view.agent.remote.RemoteMkFileSys', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_mkFileSys',

	title : '上传文件',
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
					regexText:'不能包含一下字符:\/?*":<>|',
					name: 'file',
					waitMsg:'正在新建...',
					allowBlank : false
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: '当前路径',
					name:'nowPath'
				} ],
				buttons : [ {
					text : '保存',
					action : 'confirm'
				}, {
					text : '重置',
					handler : this.onReset
				}, {
					text : '返回',
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