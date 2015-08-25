
Ext.define('Eway.model.person.user.UserRole', {
	extend: 'Ext.data.Model',
	fields: ['id','userId', 'roleId'],
    proxy: {
        type: 'rest',
        url : 'api/person/user/addRole',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});