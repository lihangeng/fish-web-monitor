
Ext.define('Eway.model.bsAdvert.BsAdvertGroup', {
	extend: 'Ext.data.Model',
	fields: ["id","groupType","orgId","orgName","orgLevelId","orgLevel","groupName","resourcePath"],
	
	idProperty : 'id',  
	proxy : {
		type : 'rest',
		url : 'api/bsadvert/advertgroup',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
