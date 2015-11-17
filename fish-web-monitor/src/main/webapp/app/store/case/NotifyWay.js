
Ext.define('Eway.store.case.NotifyWay',{

	extend: 'Ext.data.Store',

	fields : ['value','display'],
	data : [
	    {'value':'NONE', 'display':EwayLocale.cases.caseFault.none},
		{'value':'SMS', 'display':EwayLocale.cases.caseFault.message}
	]

});