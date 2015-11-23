
Ext.define('Eway.view.machine.atmModule.AtmModuleFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmModule_AtmModuleFilterForm',
	
	layout : 'column',
	height: 40,
	initComponent: function() {
		Ext.apply(this, {
			
			items : [/*{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					name : 'no',
					fieldLabel : '模块编号',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,20}$/,
					regexText: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以字母或数字开头。(1-20位)'
				}]
			},*/{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					name : 'name',
					fieldLabel : EwayLocale.machine.atmModule.moduleName,
					msgTarget : 'side',
					labelAlign : 'right'
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});