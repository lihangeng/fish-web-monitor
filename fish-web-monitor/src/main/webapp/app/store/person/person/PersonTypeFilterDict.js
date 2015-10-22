
Ext.define('Eway.store.person.person.PersonTypeFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.commen.all}, 
		   {value:'0', display:Eway.locale.commen.comboxType.machineManager}, 
	       {value:'1', display:Eway.locale.commen.comboxType.machineRepairer}]
});