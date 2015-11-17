
Ext.define('Eway.store.case.NotifyType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':EwayLocale.cases.notifyMould.createNotice},
		{'value':'2', 'name':EwayLocale.cases.notifyMould.upgradeNotice},
		{'value':'3', 'name':EwayLocale.cases.notifyMould.closeNotice}
	]

});