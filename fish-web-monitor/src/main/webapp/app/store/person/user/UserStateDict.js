
Ext.define('Eway.store.person.user.UserStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.commen.stateDict.newCreate}, 
	       {value:'1', display:Eway.locale.commen.stateDict.normal}, 
	       {value:'2', display:Eway.locale.commen.stateDict.locked}, 
	       {value:'4', display:Eway.locale.commen.stateDict.frozen},
	       {value:'3', display:Eway.locale.commen.stateDict.deleted}]
});
