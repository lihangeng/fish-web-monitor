
Ext.define('Eway.model.machine.atmGroup.DeviceGroupRelation', {
	extend: 'Ext.data.Model',
	fields: ['id','groupId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/machine/atmGroup/addDevice',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
