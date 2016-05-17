Ext.define('Eway.model.report.faultRateReport.Brand',{
	extend :'Ext.data.Model',
	fields:['brandName','fault','trade','rate'],

	proxy:{
		type:'rest',
		url :'api/parameter/appSystem',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});