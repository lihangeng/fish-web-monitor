
Ext.define('Eway.store.person.organization.OrganizationTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.person.bankOrg.bank}, 
	       {value:'1', display:Eway.locale.person.bankOrg.serviceOrg}]
});