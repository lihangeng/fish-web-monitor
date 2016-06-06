
Ext.define('Eway.store.person.organization.OrganizationTree', {
	extend: 'Ext.data.TreeStore',
	requires : 'Eway.model.common.OrganizationTree',
	model : 'Eway.model.common.OrganizationTree',
	root: {
		text: Eway.user.getOrgName(),
		expanded: true
	},
	defaultRootId:Eway.user.getOrgId(),
    proxy: {
        type: 'ajax',
        url : 'api/person/organization/tree',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: true
});