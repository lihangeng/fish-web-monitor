
Ext.define('Eway.model.machine.atmType.AtmType', {
	extend: 'Ext.data.Model',
	fields: ['id','devCatalogId','devVendorId',/* 'no',*/'name','devVendorName','devCatalogName','spec','weight','watt','cashtype'],
    proxy: {
        type: 'rest',
        url : 'api/machine/atmType',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});