
Ext.define('Eway.view.case.caseNotify.NotifyView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.case_notifyView',
	
	requires: ['Eway.view.case.caseNotify.NotifyGrid',
	           'Eway.view.case.caseNotify.FilterForm'],
	
	title: EwayLocale.cases.caseNotify.messageCheck,
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'caseNotify_filterForm'
			}, {
				region: 'center',
				xtype: 'caseNotify_notifyGrid'
			}]
		});
		
		this.callParent(arguments);
	}
});