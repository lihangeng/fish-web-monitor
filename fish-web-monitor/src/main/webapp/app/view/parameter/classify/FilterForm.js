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
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,31}$/,
					regexText: EwayLocale.param.classify.regexText1,
					fieldLabel : EwayLocale.param.classify.name,
					msgTarget : 'side'
				}]
			},{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					// labelWidth: 110,
					name : 'remark',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5，。“”（）]{0,127}$/,
					regexText: EwayLocale.param.classify.regexText2,
					labelAlign : 'right',
					fieldLabel : EwayLocale.param.classify.remark,
					msgTarget : 'side'
				}]
			}]
		});
		this.callParent(arguments);
	}
});