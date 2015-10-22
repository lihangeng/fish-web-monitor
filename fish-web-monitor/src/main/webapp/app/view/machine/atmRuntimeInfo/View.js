
Ext.define('Eway.view.machine.atmRuntimeInfo.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.atmRuntimeInfo_View',
	
	requires: [	'Eway.view.machine.atmRuntimeInfo.Grid',
				'Eway.view.machine.device.FilterForm'
	           ],
	
	title: Eway.locale.machine.atmRuntimeInfo.msgCollect,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'device_filterform'
			},{
				region: 'center',
				xtype: 'atmRuntimeInfo_Grid'
			}],
			listeners : {
				activate : function(panel){
						if (!panel.isLoad) {
						
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					var orgTrees = panel.down('device_filterform').query('common_orgComboOrgTree');
					Ext.Array.each(orgTrees, function() {
						
						// 刷新维护商和所属机构信息
						this.reflesh();
					});
					panel.down('atmRuntimeInfo_Grid').onReload();
				}
			}
		});
		
		this.callParent(arguments);
	}
});