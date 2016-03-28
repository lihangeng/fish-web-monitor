Ext.define('Eway.view.parameter.classify.ClassifyAdd', {
	extend: 'Ext.window.Window',
	alias: 'widget.classify_ClassifyAdd',
	
	requires: ['Eway.view.field.paramClassify.ClassifyName',
	           	   'Eway.view.field.paramClassify.ClassifyRemark'],
	
	title: '新增分类信息',// EwayLocale.machine.atmGroup.addTitle,
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
					xtype : 'field_paramClassify_ClassifyName',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,19}$/,
					regexText: '只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’， 只能以中文、字母或数字开头！',
					maxLength : 32,
					allowBlank : false
				},{
				    xtype : 'field_paramClassify_ClassifyRemark',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5，。“”（）]{0,39}$/,
					regexText: '只能输入1到40字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’、逗号、句号、括号、双引号， 只能以中文、字母或数字开头！',
					maxLength :128,
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