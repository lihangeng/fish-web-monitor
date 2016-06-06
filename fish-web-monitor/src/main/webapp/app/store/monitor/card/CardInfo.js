
Ext.define('Eway.store.monitor.card.CardInfo', {
	extend: 'Eway.store.base.Store',
	
	model: 'Eway.model.monitor.card.CardInfo',
	
    proxy: {
	    type: 'rest',
	    url : 'api/monitor/retainCard',
	    reader: {
	        type: 'json',
	        rootProperty : 'data'
	    },
	    extraParams : {
			 organizationId : Eway.user.getOrgId()
		}
    }
});