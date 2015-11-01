
Ext.define('Eway.store.operatingPlan.PlanStateType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Enabled', 'display':'正常启用'},
		{'value':'NotEnabled', 'display':'未启用'},
		{'value':'WExpired', 'display':'即将过期'},
		{'value':'Expired', 'display':'过期'},
		{'value':'NotEnabled', 'display':'停用'}
	]

});