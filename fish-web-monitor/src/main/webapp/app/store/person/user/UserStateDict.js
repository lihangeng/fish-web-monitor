
Ext.define('Eway.store.person.user.UserStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.commen.stateDict.newCreate}, 
	       {value:'1', display:EwayLocale.commen.stateDict.normal}, 
	       {value:'2', display:EwayLocale.commen.stateDict.locked}, 
	       {value:'4', display:EwayLocale.commen.stateDict.frozen},
	       {value:'3', display:EwayLocale.commen.stateDict.deleted}]
});
