
Ext.define('Eway.store.permission.PermissionTree', {
	extend: 'Ext.data.TreeStore',

	root: {
		text: 'root',
		expanded: true
	},
    proxy: {
        type: 'ajax',
        url : 'api/relation/permission/tree',
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