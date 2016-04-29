
Ext.define('Eway.model.parameter.template.AddedParam', {
	extend: 'Ext.data.Model',
	fields:['id','name','description','permissions','system','flag','paramType','paramBelongsName','paramClassify'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/addedParam',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});