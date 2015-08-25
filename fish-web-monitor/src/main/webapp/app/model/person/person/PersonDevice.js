
Ext.define('Eway.model.person.person.PersonDevice', {
	extend: 'Ext.data.Model',
	fields: ['id','personId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/person/person/link',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
