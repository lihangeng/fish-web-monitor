Ext.define('Eway.view.parameter.classify.Form',{
	extend : 'Eway.view.base.Form',
	alias : 'widget.parameter_classify_form',

	requires: ['Eway.view.field.paramClassify.ClassifyName',
	           	   'Eway.view.field.paramClassify.ClassifyRemark'],

	defaults: {
					labelWidth: 120,
					labelAlign: 'right',
					msgTarget : 'side',
					width: 400
				  },

	initComponent : function(){
		Ext.apply(this,{
			defaults: {
				anchor : '90%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items: [{
				xtype : 'field_paramClassify_ClassifyName',
				maxLength : 32,
				regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,31}$/,
				regexText: '只能输入1到32字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’， 只能以中文、字母或数字开头！',
				allowBlank: false
			},{
				xtype : 'field_paramClassify_ClassifyRemark',
				maxLength : 128,
				regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5，。“”（）]{0,127}$/,
				regexText: '只能输入1到128字母‘a-z’或‘A-Z’、数字‘0-9’、中文、减号‘-’、下划线‘_’、点号‘.’、逗号、句号、括号、双引号， 只能以中文、字母或数字开头！',
				allowBlank: true
		    }]
		});
		this.callParent(arguments);
	}
});