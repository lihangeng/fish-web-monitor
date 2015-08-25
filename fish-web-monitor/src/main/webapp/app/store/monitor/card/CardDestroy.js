
Ext.define('Eway.store.monitor.card.CardDestroy', {
	extend: 'Eway.store.base.Store',
	
	model: 'Eway.model.monitor.card.CardDestroy',
	
	    proxy: {
        type: 'rest',
        url : 'api/monitor/retainCard/findCardByStatus',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        extraParams : {
			 organizationId : ewayUser.getOrgId()
		}
    }
});