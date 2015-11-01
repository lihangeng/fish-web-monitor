
Ext.define('Eway.store.operatingPlan.PlanState',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Normal', 'display':'正常'},
		{'value':'Stoped', 'display':'停用'}
	]

});