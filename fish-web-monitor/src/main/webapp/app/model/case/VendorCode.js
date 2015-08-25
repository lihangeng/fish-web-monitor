Ext.define('Eway.model.case.VendorCode',{
	extend : 'Ext.data.Model',
	fields: ['id', 'vendorId','vendorName','code','description','solution'],
	proxy : {
		type : 'rest',
		url : 'api/case/vendorCode',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});