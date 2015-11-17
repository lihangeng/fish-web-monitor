/**
 * 机构下的人员列表Grid
 */
Ext.define('Eway.view.machine.device.person.PersonTMManagerGrid', {
	alias: 'widget.personTM_manager_grid',
	extend: 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	multiSelect:true,
	selModel:{selType:'checkboxmodel'},

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.PersonTM',{
			autoLoad : false
		});
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.refresh,
				glyph : 0xf021,
				action: 'queryRe'
				}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add'
				}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove'}],
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