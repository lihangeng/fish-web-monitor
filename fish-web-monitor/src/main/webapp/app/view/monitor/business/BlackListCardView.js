
Ext.define('Eway.view.monitor.business.BlackListCardView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.business_BlackListCardView',
	
	requires: ['Eway.view.monitor.business.BlackListCardGrid',
	           'Eway.view.monitor.business.BlackListCardFilterForm'],
	
	title: EwayLocale.monitor.business.blackList.title,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'business_BlackListCardFilterForm'
			}, {
				region: 'center',
				xtype: 'business_BlackListCardGrid'
			}]
		});
		
		this.callParent(arguments);
	}
});