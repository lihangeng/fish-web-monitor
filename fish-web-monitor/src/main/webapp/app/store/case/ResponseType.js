
Ext.define('Eway.store.case.ResponseType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':EwayLocale.commen.comboxType.machineManager},
		{'value':'2', 'name':EwayLocale.cases.faultClassify.maintain},
		{'value':'3', 'name':EwayLocale.cases.faultClassify.manageAndMaintain}
	]

});