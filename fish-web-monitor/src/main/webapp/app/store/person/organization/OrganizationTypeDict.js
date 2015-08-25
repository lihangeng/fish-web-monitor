
Ext.define('Eway.store.person.organization.OrganizationTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'银行'}, 
	       {value:'1', display:'维护商'}]
});