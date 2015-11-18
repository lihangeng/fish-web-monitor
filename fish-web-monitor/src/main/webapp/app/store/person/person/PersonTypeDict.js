
Ext.define('Eway.store.person.person.PersonTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.commen.comboxType.machineManager}, 
	       {value:'1', display:EwayLocale.commen.comboxType.machineRepairer}]
});