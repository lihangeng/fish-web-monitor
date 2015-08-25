
Ext.define('Eway.view.machine.atmCatalog.AtmCatalogFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmCatalog_AtmCatalogFilterForm',
	
	height: 40,
	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			
			items : [/*{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					name : 'no',
					fieldLabel : '编号'
				}]
			},*/{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					name : 'name',
					fieldLabel : '分类名称',
					regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,19}$/,
					regexText: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以字母,汉字或数字开头。(1-20位)',
					msgTarget : 'side',
					labelAlign : 'right'

				}]
			}]
		});
		
		this.callParent(arguments);
	}
});