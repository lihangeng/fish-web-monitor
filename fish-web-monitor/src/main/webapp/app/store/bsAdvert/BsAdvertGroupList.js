Ext.define('Eway.store.bsAdvert.BsAdvertGroupList', {
	extend : 'Ext.data.Store',
	fields : [ 'id', 'groupName' ],
	proxy : {
		type : 'ajax',
		url : 'api/bsadvert/advertgroup/list',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : true
});