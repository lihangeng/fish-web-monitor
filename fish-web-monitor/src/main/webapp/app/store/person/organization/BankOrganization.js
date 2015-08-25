Ext.define('Eway.store.person.organization.BankOrganization', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.person.organization.BankOrganization',
	autoSync : false,
	initComponent : function(){
		this.callParent(arguments);
	},
	remoteFilter: true//在服务器端过滤，默认是false
});