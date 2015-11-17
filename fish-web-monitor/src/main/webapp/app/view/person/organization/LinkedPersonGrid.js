
Ext.define('Eway.view.person.organization.LinkedPersonGrid', {
	alias: 'widget.linkedPersonGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	border : false,
	
	title: EwayLocale.person.bankOrg.linkPeronTitle,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.LinkedPerson');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.commen.name,
				dataIndex : 'name'
			},{
				header : EwayLocale.commen.gender,
				dataIndex : 'gender',
				renderer: function(value,metadata,record){
					if(value=="MALE"){
	                	   return EwayLocale.commen.comboxGender.male;
	                   }else if(value=="FEMALE"){
	                	   return EwayLocale.commen.comboxGender.female;
	                   }else{
	                	   return EwayLocale.commen.comboxGender.unkown;
	                   }
				}
			},{
				header : EwayLocale.commen.birthday,
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : EwayLocale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : EwayLocale.commen.phone,
				dataIndex : 'phone'
			},{
				header : EwayLocale.commen.email,
				dataIndex : 'email'
			},{
				header : EwayLocale.commen.type,
				dataIndex : 'type',
				renderer: function(value,metadata,record){
					if(value==0){
		                	 return EwayLocale.commen.comboxType.machineManager;
		             }else if(value==1){
		                	   return EwayLocale.commen.comboxType.machineRepairer;
		             }
				}
			},{
				header : EwayLocale.person.bankPer.organizationName,
				dataIndex : 'organizationName'
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
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});