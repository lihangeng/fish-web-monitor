Ext.define('Eway.model.case.NotifyMould',{
	extend : 'Ext.data.Model',
	fields: ['id', 'faultClassifyId','classifyName','notifyType','notifyWay','notifySet'],
	proxy : {
		type : 'rest',
		url : 'api/case/notifyMould',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});