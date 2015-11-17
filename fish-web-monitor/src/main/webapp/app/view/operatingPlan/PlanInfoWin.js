Ext.define('Eway.view.operatingPlan.PlanInfoWin', {
	alias: 'widget.operatingPlan_planInfoWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.operatingPlan.PlanInfoGrid'],
	
	width: 600,
	height: 400,
	layout: 'fit',
	
	initComponent: function() {
		Ext.apply(this, {
			title : EwayLocale.tip.planDetail,
			items : [{
				padding : 1,
				xtype : 'planInfo_grid'
			}]
		});
		this.callParent(arguments);
	}
	
});