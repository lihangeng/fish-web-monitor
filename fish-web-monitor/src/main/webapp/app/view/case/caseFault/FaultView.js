
Ext.define('Eway.view.case.caseFault.FaultView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.case_faultView',
	
	requires: ['Eway.view.case.caseFault.FaultGrid',
	           'Eway.view.case.caseFault.FilterForm'],
	
	title: '故障查询',
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'caseFault_filterForm'
			}, {
				region: 'center',
				xtype: 'caseFault_faultGrid'
			}]
		});
		
		this.callParent(arguments);
	}
});