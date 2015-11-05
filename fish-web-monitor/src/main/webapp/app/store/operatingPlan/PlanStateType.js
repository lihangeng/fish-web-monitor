
Ext.define('Eway.store.operatingPlan.PlanStateType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Enabled', 'display':Eway.locale.report.openplan.useSuccess},
		{'value':'NotEnabled', 'display':Eway.locale.report.openplan.notSuccess},
		{'value':'WExpired', 'display':Eway.locale.commen.comboxStatus.pastDueSoon},
		{'value':'Expired', 'display':Eway.locale.commen.comboxStatus.pastDue},
		{'value':'NotEnabled', 'display':Eway.locale.commen.comboxStatus.close}
	]

});