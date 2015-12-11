
Ext.define('Eway.view.monitor.card.CardInfoView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.card_CardInfoView',

	requires: ['Eway.view.monitor.card.CardInfoGrid',
	           'Eway.view.monitor.card.CardInfoFilterForm'
	           ],

	title: EwayLocale.monitor.business.card.title,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'card_cardInfoFilterForm'
			}, {
				region: 'center',
				xtype: 'card_CardInfoGrid'
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('card_CardInfoGrid').onReload();
					panel.down('card_cardInfoFilterForm').down('field_card_DeviceTypeComboBox').getStore().load();
					//刷新组织机构
					panel.down('card_cardInfoFilterForm').down('common_orgComboOrgTree').reflesh();
				}
			}
		});

		this.callParent(arguments);
	}
});