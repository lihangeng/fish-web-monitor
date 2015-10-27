
Ext.define('Eway.store.case.NotifyWay',{

	extend: 'Ext.data.Store',

	fields : ['value','display'],
	data : [
	    {'value':'NONE', 'display':Eway.locale.cases.caseFault.none},
		{'value':'SMS', 'display':Eway.locale.cases.caseFault.message}
	]

});