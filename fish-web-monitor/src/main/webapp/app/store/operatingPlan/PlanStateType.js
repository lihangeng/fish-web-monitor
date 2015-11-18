
Ext.define('Eway.store.operatingPlan.PlanStateType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Enabled', 'display':EwayLocale.report.serviceplan.useSuccess},
		{'value':'NotEnabled', 'display':EwayLocale.report.serviceplan.notSuccess},
		{'value':'WExpired', 'display':EwayLocale.commen.comboxStatus.pastDueSoon},
		{'value':'Expired', 'display':EwayLocale.commen.comboxStatus.pastDue},
		{'value':'NotEnabled', 'display':EwayLocale.commen.comboxStatus.close}
	]

});