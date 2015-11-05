
Ext.define('Eway.store.operatingPlan.PlanState',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Normal', 'display':Eway.locale.commen.stateDict.normal},
		{'value':'Stoped', 'display':Eway.locale.commen.comboxStatus.close}
	]

});