Ext.define('Eway.model.version.VersionDownload', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'deviceIds','planTime','versionFile','versionId',/*{'name':'eagerRestart','type':'boolean',defaultValue:true},*/
	         'versionName','jobName','taskType','jobStatus',/*'jobPriority','desc','extraBody',*/
	         'deployStartDate','deployEndDate',{'name':'allDevice','type':'boolean',defaultValue:true}/*,'versionCatalog'*/],
    proxy: {
        type: 'rest',
        url : 'api/version/download',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});