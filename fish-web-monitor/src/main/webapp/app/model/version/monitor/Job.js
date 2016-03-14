Ext.define('Eway.model.version.monitor.Job', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: [{name:'id',type:'int'}, 'userName','deployStartDate','deployEndDate',
	         'planTime','versionFile','downloadCounter',
	         {name:'versionId',type:'int',defaultValue:100},{'name':'eagerRestart','type':'boolean',defaultValue:true},
	         'versionName','jobName','jobType',{name:'jobStatus',defaultValue: 'NEW'},'jobPriority','desc','extraBody',
	         'finishTaskCount','failTaskCount','runTaskCount','allTaskCount'],
    proxy: {
        type: 'rest',
        url : 'api/version/download',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});