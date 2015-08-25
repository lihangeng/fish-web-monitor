/**
 * 银行机构树Store：
 */
Ext.define('Eway.store.person.organization.BankOrganizationTree', {
	extend: 'Ext.data.TreeStore',
	requires : 'Eway.model.common.OrganizationTree',
	model : 'Eway.model.common.OrganizationTree',
	root: {
		id: ewayUser.getOrgId(),
		text: ewayUser.getOrgName(),
		expanded: true
	},
    proxy: {
        type: 'ajax',
        url : 'api/person/organization/tree',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    },
	    extraParams : {type:'0'}
    },
    autoLoad: false
});