Ext.define('Eway.model.report.faultRateReport.Brand',{
	extend :'Ext.data.Model',
	fields:['brandName','fault','trade','rate'],

	proxy:{
		type:'rest',
		url :'api/report/faultRate/faultByBrand',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});