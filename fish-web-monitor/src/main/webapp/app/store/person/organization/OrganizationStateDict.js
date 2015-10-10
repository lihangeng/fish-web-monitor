
Ext.define('Eway.store.person.organization.OrganizationStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.commen.stateDict.newCreate}, 
	       {value:'1', display:Eway.locale.commen.stateDict.normal}, 
	       {value:'2', display:Eway.locale.commen.stateDict.locked}, 
	       {value:'3', display:Eway.locale.commen.stateDict.disable}, 
	       {value:'4', display:Eway.locale.commen.stateDict.frozen}]
});
