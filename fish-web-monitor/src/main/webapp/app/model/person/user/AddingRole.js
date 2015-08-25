
Ext.define('Eway.model.person.user.AddingRole', {
	extend: 'Ext.data.Model',
	fields:['id','name','description','permissions','system'],
    proxy: {
        type: 'rest',
        url : 'api/person/user/addingRole',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});