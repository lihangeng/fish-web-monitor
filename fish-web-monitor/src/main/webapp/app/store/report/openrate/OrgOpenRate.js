Ext.define('Eway.store.report.openrate.OrgOpenRate', {
	extend : 'Ext.data.TreeStore',
	model : 'Eway.model.report.openrate.OrgOpenRate',
	root : {
		terminalId : ewayUser.getOrgName(),
		openRate : '-1',
		expanded : true
	},
	defaultRootId : ewayUser.getOrgId(),
	proxy : {
		type : 'ajax',
		url : 'api/report/openrate/orgOpenRate',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});