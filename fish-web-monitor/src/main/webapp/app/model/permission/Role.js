
Ext.define('Eway.model.permission.Role', {
	extend: 'Ext.data.Model',
	fields:['id','name','system','description','permissions'],
    proxy: {
        type: 'rest',
        url : 'api/relation/role',
        reader: {
            type: 'json',
            rootProperty : 'data'
        }
    }
//    hasMany: 'Permission'
});