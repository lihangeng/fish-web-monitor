
Ext.define('Eway.store.case.NotifyWay',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'SMS', 'display':'短信'},
		{'value':'MAIL', 'display':'邮件'},
		{'value':'BOTH', 'display':'短信和邮件'}
	]

});