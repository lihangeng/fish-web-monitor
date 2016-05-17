Ext.define('Eway.model.report.faultRateReport.Type',{
	extend :'Ext.data.Model',
	fields:['typeName','fault','trade','rate'],

	proxy:{
		type:'rest',
		url :'api/parameter/appSystem',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});