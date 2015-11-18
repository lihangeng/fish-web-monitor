
Ext.define('Eway.store.person.person.PersonTypeFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:EwayLocale.commen.all}, 
		   {value:'0', display:EwayLocale.commen.comboxType.machineManager}, 
	       {value:'1', display:EwayLocale.commen.comboxType.machineRepairer}]
});