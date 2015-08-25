
Ext.define('Eway.model.person.user.AddedRole', {
	extend: 'Ext.data.Model',
	fields:['id','name','description','permissions','system'],
    proxy: {
        type: 'rest',
        url : 'api/person/user/addedRole',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});