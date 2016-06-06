Ext.define('Eway.store.report.openrate.OrgOpenRate', {
	extend : 'Ext.data.TreeStore',
	model : 'Eway.model.report.openrate.OrgOpenRate',
	root : {
		terminalId : Eway.user.getOrgName(),
		openRate : '-1',
		expanded : true
	},
	defaultRootId : Eway.user.getOrgId(),
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