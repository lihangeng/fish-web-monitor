Ext.define('Eway.model.machine.Device', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, 'terminalId', 'ip', {
		name : 'orgId'
	}, {
		name : 'orgName'
	}, {
		name : 'devTypeId'
	}, {
		name : 'devTypeName'
	}, {
		name : 'devVendorName'
	}, {
		name : 'devCatalogName'
	}, 'address', 'status', 'devServiceName', 'virtual', 'devServiceId', {
		name : 'cashboxLimit',
		type : 'number'
	}, {
		name : 'serial'
	}, {
		name : 'buyDate'
	}, {
		name : 'installDate'
	}, {
		name : 'startDate'
	}, {
		name : 'stopDate'
	}, {
		name : 'openTimeHour'
	}, {
		name : 'openTimeMinute'
	}, {
		name : 'openTimeSecond'
	}, {
		name : 'closeTimeHour'
	}, {
		name : 'closeTimeMinute'
	}, {
		name : 'closeTimeSecond'
	}, {
		name : 'expireDate'
	}, {
		name : 'patrolPeriod',
		type : 'number'
	}, {
		name : 'careLevel'
	}, {
		name : 'lastPmDate'
	}, {
		name : 'expirePmDate'
	}, {
		name : 'cashType'
	}, {
		name : 'setupType'
	}, {
		name : 'carrier'
	}, {
		name : 'moneyOrg'
	}, {
		name : 'price',
		type : 'number'
	}, {
		name : 'depreciationLife',
		type : 'number'
	}, {
		name : 'decoration',
		type : 'number'
	}, {
		name : 'decorationCost',
		type : 'number'
	}, {
		name : 'governanceRent',
		type : 'number'
	}, {
		name : 'governanceCost',
		type : 'number'
	}, {
		name : 'netCost',
		type : 'number'
	}, {
		name : 'powerCost',
		type : 'number'
	}, {
		name : 'moneyCost',
		type : 'number'
	}, {
		name : 'costInterest'
	}, {
		name : 'atmcSoft'
	}, {
		name : 'sp'
	}, {
		name : 'netType'
	}, {
		name : 'awayFlag'
	}, {
		name : 'workType'
	} ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});