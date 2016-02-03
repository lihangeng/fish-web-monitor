Ext.define('Eway.model.monitor.transaction.colorset.ColorSet', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'hostRet', 'backgroundColor', 'fontColor', 'localRet',
			'localBackgroundColor', 'localFontColor', 'userName',
			'updateDateTime', 'remark','hostRetDes', 'localRetDes' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/transaction/color',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});