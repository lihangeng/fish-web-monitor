
Ext.define('Eway.model.machine.atmBrand.AtmBrand', {
	extend: 'Ext.data.Model',
	fields: ['id', /*'no',*/'name','country','address','hotline1','hotline2','status'],
    proxy: {
        type: 'rest',
        url : 'api/machine/atmBrand',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});