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
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
				}, {
				text: Eway.locale.commen.setManager,
				glyph : 0xf0c1,
				action: 'addselect'
			}],
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
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});