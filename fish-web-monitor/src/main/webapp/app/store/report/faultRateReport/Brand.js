Ext.define('Eway.store.report.faultRateReport.Brand', {
	extend: 'Eway.store.base.Store',
	alias:'store.brandRate',
	autoSync : false,
	model: 'Eway.model.report.faultRateReport.Brand',
    autoLoad: false,
    pageSize:200
});