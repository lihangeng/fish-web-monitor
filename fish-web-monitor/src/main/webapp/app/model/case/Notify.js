
Ext.define('Eway.model.case.Notify', {
	extend: 'Ext.data.Model',
	fields: [
	       'id', 'faultId','createTime', 'content','notifyWay','terminalId',
	       'mobile','mail','notifyTimes','sendTimes','sendInterval','sendTime','bankPer','serPer'
	],
	
	idProperty : 'id',  
	proxy : {
		type : 'rest',
		url : 'api/case/caseNotify',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
