
Ext.define('Eway.store.person.person.PersonTypeFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.person.bankPer.allGender}, 
		   {value:'0', display:Eway.locale.person.bankPer.comboxType.machineManager}, 
	       {value:'1', display:Eway.locale.person.bankPer.comboxType.machineRepairer}]
});