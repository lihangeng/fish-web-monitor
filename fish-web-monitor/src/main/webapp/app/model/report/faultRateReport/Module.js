Ext.define('Eway.model.report.faultRateReport.Module',{
	extend :'Ext.data.Model',
	fields:['vendorId','devTypeId','name','tradeCount','faultCount','rate'],

	proxy:{
		type:'rest',
		url :'api/report/faultRate/faultByModule',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});