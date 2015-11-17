
Ext.define('Eway.store.operatingPlan.PlanState',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Normal', 'display':EwayLocale.commen.stateDict.normal},
		{'value':'Stoped', 'display':EwayLocale.commen.comboxStatus.close}
	]

});