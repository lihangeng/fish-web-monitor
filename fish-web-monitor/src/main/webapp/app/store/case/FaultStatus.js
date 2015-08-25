
Ext.define('Eway.store.case.FaultStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPEN', display:'未关闭'}, 
	       {value:'CLOSED', display:'已关闭'}]
});