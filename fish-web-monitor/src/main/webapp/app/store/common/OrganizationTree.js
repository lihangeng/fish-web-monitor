
Ext.define('Eway.store.common.OrganizationTree', {
	extend: 'Ext.data.TreeStore',
	model : 'Eway.model.common.OrganizationTree',
	root: {
		id: ewayUser.getOrgId(),
		text: ewayUser.getOrgName(),
		expanded: false
	},
    proxy: {
        type: 'ajax',
        url : 'api/person/organization/tree',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
    },
    autoLoad: false
});