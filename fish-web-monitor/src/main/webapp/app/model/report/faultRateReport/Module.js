Ext.define('Eway.model.report.faultRateReport.Module',{
	extend :'Ext.data.Model',
	fields:['moduleName','fault','trade','rate'],

	proxy:{
		type:'rest',
		url :'api/parameter/appSystem',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});