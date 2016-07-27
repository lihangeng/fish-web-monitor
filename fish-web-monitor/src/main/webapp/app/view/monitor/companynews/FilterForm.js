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
//					columnWidth : .3,
					width:'290px',
					xtype : 'datefield',
					format: 'Y-m',
					fieldLabel : '月份',
					name : 'time_month',
					margin:'15px 0 0 10px'
				},{
					xtype:'button',
					text:'查询',
					glyph : 0xf002,
					margin:'45px 0 0 755px',
				}],
//				tbar:['->',{
//					text : '查询',
//	                xtype : 'button',
//	                glyph : 0xf002,
////	                bodyStyle:'background:#edefef;padding:15px;',
////	                margin:'70px 0 0 0'
//				}]
			
		});
		this.callParent(arguments);
	}
});