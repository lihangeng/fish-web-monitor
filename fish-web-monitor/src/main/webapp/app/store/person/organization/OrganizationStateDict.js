
Ext.define('Eway.store.person.organization.OrganizationStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:Eway.locale.person.bankOrg.organizationStateDict.newCreate}, 
	       {value:'1', display:Eway.locale.person.bankOrg.organizationStateDict.normal}, 
	       {value:'2', display:Eway.locale.person.bankOrg.organizationStateDict.locked}, 
	       {value:'3', display:Eway.locale.person.bankOrg.organizationStateDict.disable}, 
	       {value:'4', display:Eway.locale.person.bankOrg.organizationStateDict.frozen}]
});
