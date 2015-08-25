/**
 * 
 * 版本详情的模型定义
 */
Ext.define('Eway.model.version.VersionChartsDetails', {
	extend: 'Ext.data.Model',
	fields: ['terminalId','orgName','ip','versionNo','devType','versionId','flag']
    ,proxy: {
        type: 'rest',
        url : 'api/version/version/versionChartsDetails',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }/**/
});