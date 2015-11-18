Ext.define('Eway.view.machine.device.person.PersonMManagerGrid', {
	alias: 'widget.personM_manager_grid',
	extend: 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.PersonM',{
			autoLoad : false
		});
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.refresh,
				iconCls: 'refreshBtn',
				action: 'queryRe'
				}/*, {
				text: EwayLocale.button.add,
				//iconCls :'sureBtn',
				glyph : 0xf067,
				action: 'add'
				}, {
				text: EwayLocale.button.remove,
				//iconCls :'removeBtn',
				glyph : 0xf014,
				action: 'remove'}*/],
			columns : [{
				header : EwayLocale.commen.jobNum,
				dataIndex : 'jobNum'
			},{
				header : EwayLocale.commen.name,
				dataIndex : 'name'
			}, {
				header : EwayLocale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : EwayLocale.commen.phone,
				dataIndex : 'phone'
			},{
				header : EwayLocale.commen.state,
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return EwayLocale.commen.comboxStatus.onJob;
		             }else if(value==2){
		                	   return EwayLocale.commen.comboxStatus.onAdjust;
		             }else if(value==3){
		                	   return EwayLocale.commen.comboxStatus.onVacation;
		             }else if(value==0){
		                	   return EwayLocale.commen.comboxStatus.other;
		             }
				}
			},{
				header : EwayLocale.commen.email,
				dataIndex : 'email'
			},{
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'organizationName',
				flex : 1
			}]
		});

		this.callParent(arguments);
	}
});