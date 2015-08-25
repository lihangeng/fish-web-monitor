/**
 * 版本类型的模型定义
 */
Ext.define('Eway.model.version.VersionType', {
	extend: 'Ext.data.Model',
	fields: ['id', 'typeName','defaultInstallPath','autoDeploy','desc','versionCatalog','cashType','awayFlag','atmTypes'],
    proxy: {
        type: 'rest',
        url : 'api/version/versionType',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});