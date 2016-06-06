
Ext.define('Eway.store.common.OrganizationTree', {
	extend: 'Ext.data.TreeStore',
	model : 'Eway.model.common.OrganizationTree',
	root: {
		id: Eway.user.getOrgId(),
		text: Eway.user.getOrgName(),
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