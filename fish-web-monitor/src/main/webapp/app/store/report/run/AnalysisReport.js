Ext.define('Eway.store.report.run.AnalysisReport', {
	extend: 'Eway.store.base.Store',
	alias:'store.analysisReport',
	autoSync : false,
	model: 'Eway.model.report.run.AnalysisReport',
    autoLoad: false,
    pageSize:200
});