Ext.define('Eway.store.person.organization.OrganizationLevelDict', {
	extend: 'Ext.data.Store',
	model: 'Eway.model.Dict',
    data : [{value:0, display:'总行'},
            {value:1, display:'分行'},
            {value:2, display:'支行'},
            {value:3, display:'网点'}]


});


