
Ext.define('Eway.model.bsAdvert.BsAdvertDevice', {
	extend: 'Ext.data.Model',
	fields: ['id','groupId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/bsadvert/advertgroup/link',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
