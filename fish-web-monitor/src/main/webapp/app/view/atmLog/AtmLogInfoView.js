Ext.define('Eway.view.atmLog.AtmLogInfoView',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.atmLog_AtmLogInfoView',
	
	requires : [
		'Eway.view.atmLog.AtmLogInfoFilterForm',
		'Eway.view.atmLog.AtmLogInfoGrid'
	],
	
	layout : 'border',
	
	initComponent : function(){
		
		Ext.apply(this,{
			title : '日志备份统计',
			items : [{
				region : 'north',
				xtype : 'atmLog_AtmLogInfoFilterForm',
				height : 40
			},{
				region : 'center',
				xtype : 'atmLog_AtmLogInfoGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});