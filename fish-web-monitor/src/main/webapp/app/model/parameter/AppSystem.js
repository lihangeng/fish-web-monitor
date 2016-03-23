Ext.define('Eway.model.parameter.AppSystem',{
	extend :'Ext.data.Model',
	fields:['id','name','configName','configForm','configPath','remark'],

	proxy:{
		type:'rest',
		url :'api/parameter/appSystem',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	}

});