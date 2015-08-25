Ext.define('Eway.store.person.organization.ServiceOrganization', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.person.organization.ServiceOrganization',
	autoSync : false,
	initComponent : function(){
		this.callParent(arguments);
	},
	remoteFilter: true//在服务器端过滤，默认是false
});