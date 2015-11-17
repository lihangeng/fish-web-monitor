
Ext.define('Eway.store.person.user.UserStateFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
		   {value:'1', display:EwayLocale.commen.stateDict.newCreate}, 
	       {value:'2', display:EwayLocale.commen.stateDict.normal},
	       {value:'3', display:EwayLocale.commen.stateDict.locked}]
});
