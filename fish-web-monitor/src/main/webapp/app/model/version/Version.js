/**
 * 版本的模型定义
 */
Ext.define('Eway.model.version.Version', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'serverPath','versionNo','createdTime','fullName',
	         'versionTypeId','versionType','dependVersionId','execBefore','execAfter',
	         {name:'versionStatus',defaultValue: 'NEW'},{'name':'uncompress','type':'boolean'},
	         'dependVersion','dvDisplayName','versionPath',{'name':'autoDown','type':'boolean'},
	         {'name':'eagerRestart','type':'boolean'},'desc','downLoadCounter','versionTypeDesc',
	        /* 'deviceTotal','mayBeDownTotal',
	         'downedTotal',
	         'successTotal',
	         'failTotal',
	         'unDownTotal',*/
	         'userName'
	         ],
    proxy: {
        type: 'rest',
        url : 'api/version/version',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});