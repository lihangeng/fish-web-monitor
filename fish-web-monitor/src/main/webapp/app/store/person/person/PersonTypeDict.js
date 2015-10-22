
Ext.define('Eway.store.person.person.PersonTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.commen.comboxType.machineManager}, 
	       {value:'1', display:Eway.locale.commen.comboxType.machineRepairer}]
});