Ext.define('Eway.model.machine.atmMove.AtmMoveNotice', {
	extend : 'Ext.data.Model',
	fields : ['id', 'date', 'terminalId', 'address', 'orgId', 'orgName', 'responsibility',
			'targetAddress', 'targetOrganization', 'notice','targetPerson','destPerson',
			'targetOrganizationId' ],

	proxy : {
		type : 'rest',
		url : 'api/machine/atmMove',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});