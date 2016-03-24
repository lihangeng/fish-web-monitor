Ext.define('Eway.view.parameter.classify.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.classify_FilterForm',

	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'name',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,19}$/,
					regexText: '只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’， 只能以中文、字母或数字开头！',
					fieldLabel : '参数类名',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					// labelWidth: 110,
					name : 'remark',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5，。“”（）]{0,39}$/,
					regexText: '只能输入1到40字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’、逗号、句号、括号、双引号， 只能以中文、字母或数字开头！',
					labelAlign : 'right',
					fieldLabel :'参数类备注',
					msgTarget : 'side'
				}]
			}]
		});
		this.callParent(arguments);
	}
});