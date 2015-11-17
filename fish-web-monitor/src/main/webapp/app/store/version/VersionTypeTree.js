
Ext.define('Eway.store.version.VersionTypeTree', {
	extend: 'Ext.data.TreeStore',

	root: {
		text: EwayLocale.versionType.treeRoot,//'所有软件分类',
		expanded: true
	},
	defaultRootId: 0,
    proxy: {
        type: 'ajax',
        url : 'api/version/versionType/tree',
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