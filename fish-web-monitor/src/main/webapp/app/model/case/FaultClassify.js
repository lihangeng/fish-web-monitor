Ext.define('Eway.model.case.FaultClassify',{
	extend : 'Ext.data.Model',
	fields: ['id', 'classifyName','responsorType','resolveHour','upgrade','notifyTimes','notifyWay'],
	proxy : {
		type : 'rest',
		url : 'api/case/faultClassify',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});