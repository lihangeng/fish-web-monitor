
Ext.define('Eway.model.DeviceLinkArea', {
	extend: 'Ext.data.Model',
	fields: ['id','areaId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/machine/areadevice',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});