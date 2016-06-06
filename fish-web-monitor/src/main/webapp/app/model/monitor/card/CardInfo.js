Ext.define('Eway.model.monitor.card.CardInfo', {
	extend : 'Ext.data.Model',
	fields : ['id', 'terminalId', 'handOverOrgId', 'handOverOrgName', 'accountNo',
			'cardRetainTime', 'cardDistributionBank', 'status', 'reason',
			'treatmentPeople', 'treatmentTime', 'customerName',
			'customerPhone', 'customerPapers', 'cardType', 'userOrgId',
			'subsidiaryorganId', 'subsidiaryorganName', 'cardRetainType' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/retainCard',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		extraParams : {
			organizationId : Eway.user.getOrgId()
		}
	}

});