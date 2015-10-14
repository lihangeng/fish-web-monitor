
Ext.define('Eway.store.case.NotifyType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':Eway.locale.cases.notifyMould.createNotice},
		{'value':'2', 'name':Eway.locale.cases.notifyMould.upgradeNotice},
		{'value':'3', 'name':Eway.locale.cases.notifyMould.closeNotice}
	]

});