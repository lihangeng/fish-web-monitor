
Ext.define('Eway.view.system.quartz.QuartzFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.quartz_QuartzFilterForm',
	
	
	requires: [],
	
	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .45,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'name',
					fieldLabel : '任务名称',
					msgTarget : 'side'
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});