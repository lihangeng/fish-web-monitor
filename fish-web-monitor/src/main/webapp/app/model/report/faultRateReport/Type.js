Ext.define('Eway.model.report.faultRateReport.Type',{
	extend :'Ext.data.Model',
	fields:['name','faultCount','tradeCount','rate'],

	proxy:{
		type:'rest',
		url :'api/report/faultRate/faultByType',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});