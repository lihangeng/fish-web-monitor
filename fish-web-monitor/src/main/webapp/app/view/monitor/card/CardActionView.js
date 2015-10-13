
Ext.define('Eway.view.monitor.card.CardActionView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.card_CardActionView',
	
	requires: ['Eway.view.monitor.card.CardActionGrid',
	           'Eway.view.monitor.card.CardActionFilterForm'
	           ],
	
	title: Eway.locale.monitor.business.card.processCard,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'card_CardActionFilterForm'
			}, {
				region: 'center',
				xtype: 'card_CardActionGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('card_CardActionGrid').onReload();
					panel.down('card_CardActionFilterForm').down('field_card_DeviceAtmVendorComboBox').getStore().load();
					panel.down('card_CardActionFilterForm').down('field_card_DeviceTypeComboBox').getStore().load();
					//刷新组织机构
					panel.down('card_CardActionFilterForm').down('common_orgComboOrgTree').reflesh();
				}
			} 
		});
		
		this.callParent(arguments);
	}
});