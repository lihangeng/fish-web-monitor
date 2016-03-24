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
					fieldLabel : '参数类名',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					// labelWidth: 110,
					name : 'remark',
					labelAlign : 'right',
					fieldLabel :'参数类备注',
					msgTarget : 'side'
				}]
			}]
		});
		this.callParent(arguments);
	}
});