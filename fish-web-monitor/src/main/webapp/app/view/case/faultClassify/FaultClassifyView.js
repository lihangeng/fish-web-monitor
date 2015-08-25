
Ext.define('Eway.view.case.faultClassify.FaultClassifyView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.faultClassify_FaultClassifyView',
	
	requires: ['Eway.view.case.faultClassify.FaultClassifyGrid'],
	
	title: '故障类型配置',
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'center',
				xtype: 'faultClassify_FaultClassifyGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('faultClassify_FaultClassifyGrid').onReload();
				}
			}
		});
		
		this.callParent(arguments);
	}
});