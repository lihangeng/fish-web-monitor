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
				maxLength : 64,
				regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,31}$/,
				regexText: EwayLocale.param.classify.regexText1,
				allowBlank: false
			},{
				xtype : 'field_paramClassify_ClassifyRemark',
				maxLength : 128,
				regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5，。“”（）]{0,127}$/,
				regexText: EwayLocale.param.classify.regexText2,
				allowBlank: true
		    }]
		});
		this.callParent(arguments);
	}
});