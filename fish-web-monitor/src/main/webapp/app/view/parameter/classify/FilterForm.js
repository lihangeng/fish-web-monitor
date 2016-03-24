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
					fieldLabel : '参数名',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					// labelWidth: 110,
					labelAlign : 'right',
					fieldLabel :'参数备注',
					msgTarget : 'side'
				}]
			}]
		});
		this.callParent(arguments);
	}
});