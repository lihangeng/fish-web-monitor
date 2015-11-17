
Ext.define('Eway.store.person.user.UserTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.person.user.rootUser}, 
	       {value:'1', display:EwayLocale.person.user.generalUser}]
});