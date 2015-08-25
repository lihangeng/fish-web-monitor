
Ext.define('Eway.view.case.notifyMould.NotifyMouldView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.notifyMould_NotifyMouldView',
	
	requires: ['Eway.view.case.notifyMould.NotifyMouldGrid'],
	
	title: '短信内容配置',
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'center',
				xtype: 'notifyMould_NotifyMouldGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('notifyMould_NotifyMouldGrid').onReload();
				}
			}
		});
		
		this.callParent(arguments);
	}
});