/**
 * 机构下的人员列表Grid
 */
Ext.define('Eway.view.machine.device.person.SelectPersonManagerGrid', {
	alias: 'widget.select_person_manager_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.SelectPerson',{
			autoLoad : true
		});
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
				}, {
				text: EwayLocale.commen.setManager,
				glyph : 0xf0c1,
				action: 'addselect'
			}],
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
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});