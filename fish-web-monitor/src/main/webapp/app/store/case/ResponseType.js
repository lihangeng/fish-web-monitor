
Ext.define('Eway.store.case.ResponseType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':Eway.locale.commen.comboxType.machineManager},
		{'value':'2', 'name':Eway.locale.cases.faultClassify.maintain},
		{'value':'3', 'name':Eway.locale.cases.faultClassify.manageAndMaintain}
	]

});