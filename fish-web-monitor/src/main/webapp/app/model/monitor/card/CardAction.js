
Ext.define('Eway.model.monitor.card.CardAction', {
	extend: 'Ext.data.Model',
	fields: ['id',
	         'terminalId',
	         'handOverOrgId',
	         'handOverOrgName',
	         'accountNo',
	         'cardRetainTime', 
	         'cardDistributionBank',
	         'status',
	         'reason', 
	         'treatmentPeople',
	         'treatmentTime',
	         'customerName',
	         'customerPhone',
	         'customerPapers',
	         'cardType',
	         'userOrgId',
	         'subsidiaryorganId',
	         'subsidiaryorganName',
	         'cardRetainType'],
		proxy : {
		type : 'rest',
		url : 'api/monitor/retainCard/findCardByStatus',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		 extraParams : {
			 organizationId : ewayUser.getOrgId()
		}
	}

});