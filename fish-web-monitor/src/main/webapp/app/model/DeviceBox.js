
Ext.define('Eway.model.DeviceBox', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id','boxId', 'type', 'binStatus', 'initialCount', 'currentCount', 'noteValue','currency','cashInCount'],
    proxy: {
        type: 'rest',
        url : 'api/agent/boxdetail/deviceBox',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});