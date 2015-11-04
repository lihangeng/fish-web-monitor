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
				text: Eway.locale.button.refresh,
				iconCls: 'refreshBtn',
				action: 'queryRe'
				}/*, {
				text: Eway.locale.button.add,
				//iconCls :'sureBtn',
				glyph : 0xf067,
				action: 'add'
				}, {
				text: Eway.locale.button.remove,
				//iconCls :'removeBtn',
				glyph : 0xf014,
				action: 'remove'}*/],
			columns : [{
				header : Eway.locale.commen.jobNum,
				dataIndex : 'jobNum'
			},{
				header : Eway.locale.commen.name,
				dataIndex : 'name'
			}, {
				header : Eway.locale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : Eway.locale.commen.phone,
				dataIndex : 'phone'
			},{
				header : Eway.locale.commen.state,
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return Eway.locale.commen.comboxStatus.onJob;
		             }else if(value==2){
		                	   return Eway.locale.commen.comboxStatus.onAdjust;
		             }else if(value==3){
		                	   return Eway.locale.commen.comboxStatus.onVacation;
		             }else if(value==0){
		                	   return Eway.locale.commen.comboxStatus.other;
		             }
				}
			},{
				header : Eway.locale.commen.email,
				dataIndex : 'email'
			},{
				header : Eway.locale.person.bankOrg.name,
				dataIndex : 'organizationName',
				flex : 1
			}]
		});

		this.callParent(arguments);
	}
});