Ext.define('Eway.store.person.organization.OrganizationLevelDict', {
	extend: 'Ext.data.Store',
	model: 'Eway.model.Dict',
    data : [{value:0, display:Eway.locale.person.bankOrg.organizationLevelDict.rootBank},
            {value:1, display:Eway.locale.person.bankOrg.organizationLevelDict.branchBank},
            {value:2, display:Eway.locale.person.bankOrg.organizationLevelDict.tagBranchBank},
            {value:3, display:Eway.locale.person.bankOrg.organizationLevelDict.netBank}]


});


