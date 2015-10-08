/**
 * 维护商机构树Store：
 */
Ext.define('Eway.store.person.organization.SerOrganizationTree', {
	extend: 'Ext.data.TreeStore',
	requires : 'Eway.model.common.OrganizationTree',
	model : 'Eway.model.common.OrganizationTree',
	root: {
		text: Eway.locale.person.bankOrg.serOrganization,
		expanded: true
	},
	defaultRootId : 1,
    proxy: {
        type: 'ajax',
        url : 'api/person/organization/tree',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    },
	    extraParams : {type:'1'}
    },
    autoLoad: false
});