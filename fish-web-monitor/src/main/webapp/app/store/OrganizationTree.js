
Ext.define('Eway.store.OrganizationTree', {
	extend: 'Ext.data.TreeStore',

	root: {
		text: 'root',
		expanded: true
	},
    proxy: {
        type: 'ajax',
        url : 'api/person/organization/tree',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    },
	    wirter : {
	    	type : 'json'
	    }
    },
    autoLoad: true
});