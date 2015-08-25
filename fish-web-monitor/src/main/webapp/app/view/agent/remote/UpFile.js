
Ext.define('Eway.view.agent.remote.UpFile', {
	extend : 'Ext.window.Window',
	alias : 'widget.remote_upFile',

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
					xtype: 'filefield',
					fieldLabel:'待上传文件',
					emptyText:'请选择上传文件',
					name: 'file',
					waitMsg:'正在上传文件...',
					allowBlank : false,
					buttonText: '浏览...'
				},{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: '文件在服务器上的位置',
					name:'serverPath'
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