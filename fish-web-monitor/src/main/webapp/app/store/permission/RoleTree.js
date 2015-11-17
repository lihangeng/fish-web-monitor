
Ext.define('Eway.store.permission.RoleTree', {
	extend: 'Ext.data.TreeStore',
	model: 'Eway.model.permission.Permission',
	root: {
		text: EwayLocale.permission.systemMenu,
		expanded: false

	},
    proxy: {
        type: 'ajax',
        url : 'api/relation/permission/checkedTree',
        reader: {
	        type: 'json',
	        rootProperty: 'data'
	    },
	    wirter : {
	    	type : 'json'
	    }
    },

    autoLoad: false

});