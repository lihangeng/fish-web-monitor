
Ext.define('Eway.model.monitor.business.BlackListCard', {
	extend: 'Ext.data.Model',
	fields: ['id',
	         'cardNo',
	         'userName',
	         'organization',
	         'addDate'],
	proxy: {
        type: 'rest',
        url : 'api/monitor/blacklistcard',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
        
    }
});