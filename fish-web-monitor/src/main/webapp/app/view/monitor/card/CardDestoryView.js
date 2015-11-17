
Ext.define('Eway.view.monitor.card.CardDestoryView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.card_CardDestoryView',
	
	requires: ['Eway.view.monitor.card.CardDestoryGrid',
	           'Eway.view.monitor.card.CardDestoryFilterForm'
	           ],
	
	title: EwayLocale.monitor.business.card.destory,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'card_CardDestoryFilterForm'
			}, {
				region: 'center',
				xtype: 'card_CardDestoryGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('card_CardDestoryGrid').onReload();
					panel.down('card_CardDestoryFilterForm').down('field_card_DeviceAtmVendorComboBox').getStore().load();
					panel.down('card_CardDestoryFilterForm').down('field_card_DeviceTypeComboBox').getStore().load();
					//刷新组织机构
					panel.down('card_CardDestoryFilterForm').down('common_orgComboOrgTree').reflesh();
				}
			} 
		});
		
		this.callParent(arguments);
	}
});