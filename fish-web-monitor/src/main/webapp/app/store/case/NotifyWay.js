
Ext.define('Eway.store.case.NotifyWay',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'SMS', 'display':Eway.locale.cases.caseFault.message},
		{'value':'MAIL', 'display':Eway.locale.cases.caseFault.mail},
		{'value':'BOTH', 'display':Eway.locale.cases.caseFault.messageAndMail}
	]

});