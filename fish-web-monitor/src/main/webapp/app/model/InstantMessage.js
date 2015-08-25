
Ext.define('Eway.model.InstantMessage', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'content','user','createTime'],
    proxy: {
		//idProperty : 'id',
        type: 'rest',
        url : 'api/comet/recivemessage',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});