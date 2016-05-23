Ext.define('Eway.model.report.faultRateReport.Module',{
	extend :'Ext.data.Model',
	fields:['moduleName','fault','trade','rate'],

	proxy:{
		type:'rest',
		url :'api/report/faultRate/faultByModule',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});