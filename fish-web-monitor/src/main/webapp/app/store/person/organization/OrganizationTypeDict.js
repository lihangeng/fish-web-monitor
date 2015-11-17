
Ext.define('Eway.store.person.organization.OrganizationTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.person.bankOrg.bank}, 
	       {value:'1', display:EwayLocale.person.bankOrg.serviceOrg}]
});