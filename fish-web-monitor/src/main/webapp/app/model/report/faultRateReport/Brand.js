Ext.define('Eway.model.report.faultRateReport.Brand',{
	extend :'Ext.data.Model',
	fields:['name','faultCount','tradeCount','rate'],

	proxy:{
		type:'rest',
		url :'api/report/faultRate',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});