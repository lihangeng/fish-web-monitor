Ext.define('Eway.view.parameter.classify.ClassifyAdd', {
	extend: 'Ext.window.Window',
	alias: 'widget.classify_ClassifyAdd',
	
	requires: [],
	
	title: '增加参数元数据分类信息',// EwayLocale.machine.atmGroup.addTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>参数名',// +EwayLocale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : '参数备注',// EwayLocale.machine.atmGroup.note,
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'add'
				},{
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				},{
					text: EwayLocale.button.cancle,
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