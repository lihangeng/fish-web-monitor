/**
 * 版本详情的模型定义
 */
Ext.define('Eway.model.version.VersionCharts', {
	extend: 'Ext.data.Model',
	fields: ['title', 'value','flag','versionId']
    ,proxy: {
        type: 'rest',
        url : 'api/version/version/versionDetails',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }/**/
});