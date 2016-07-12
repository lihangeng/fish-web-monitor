Ext.define('Eway.view.monitor.companynews.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_companynews_filterform',

	requires: ['Eway.lib.Util'],
	height : 70,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
//			bodyStyle:'background:#edefef;padding:15px;',
			items : [ {
					columnWidth : .3,
					xtype : 'datefield',
					format: 'Y-m',
					fieldLabel : '月份',
					name : 'time_month',
//					margin:'15px 0 0 10px'
				}],
				tbar:['->',{
					text : '查询',
	                xtype : 'button',
	                glyph : 0xf002,
	                bodyStyle:'background:#edefef;padding:15px;',
//	                margin:'40px 0 0 730px'
				}]
			
		});
		this.callParent(arguments);
	}
});