
Ext.define('Eway.store.case.FaultStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPEN', display:EwayLocale.cases.caseFault.status.open}, 
	       {value:'CLOSED', display:EwayLocale.cases.caseFault.status.close}]
});