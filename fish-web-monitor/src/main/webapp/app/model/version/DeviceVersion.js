/**
 * 设备版本的模型定义
 */
Ext.define('Eway.model.version.DeviceVersion', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'ip','terminalId','orgName','devType','devBrand','devCatalog','appVersion'],
    proxy: {
        type: 'rest',
        url : 'api/version/device',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});