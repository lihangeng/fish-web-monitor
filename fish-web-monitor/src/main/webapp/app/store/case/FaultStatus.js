
Ext.define('Eway.store.case.FaultStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPEN', display:Eway.locale.cases.caseFault.status.open}, 
	       {value:'CLOSED', display:Eway.locale.cases.caseFault.status.close}]
});