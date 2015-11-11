
Ext.define('Eway.store.person.user.UserStateFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
		   {value:'1', display:Eway.locale.commen.stateDict.newCreate}, 
	       {value:'2', display:Eway.locale.commen.stateDict.normal},
	       {value:'3', display:Eway.locale.commen.stateDict.locked}]
});
