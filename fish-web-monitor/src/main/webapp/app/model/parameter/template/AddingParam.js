
Ext.define('Eway.model.parameter.template.AddingParam', {
	extend: 'Ext.data.Model',
	fields:['id','name','description','permissions','system','paramBelongsName','paramClassify','appSystem'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/addingParam',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});