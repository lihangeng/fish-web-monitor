
Ext.define('Eway.view.person.organization.LinkedPersonGrid', {
	alias: 'widget.linkedPersonGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	border : false,
	
	title: Eway.locale.person.bankOrg.linkPeronTitle,
	
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
				header : Eway.locale.commen.name,
				dataIndex : 'name'
			},{
				header : Eway.locale.commen.gender,
				dataIndex : 'gender',
				renderer: function(value,metadata,record){
					if(value=="MALE"){
	                	   return Eway.locale.commen.comboxGender.male;
	                   }else if(value=="FEMALE"){
	                	   return Eway.locale.commen.comboxGender.female;
	                   }else{
	                	   return Eway.locale.commen.comboxGender.unkown;
	                   }
				}
			},{
				header : Eway.locale.commen.birthday,
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : Eway.locale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : Eway.locale.commen.phone,
				dataIndex : 'phone'
			},{
				header : Eway.locale.commen.email,
				dataIndex : 'email'
			},{
				header : Eway.locale.commen.type,
				dataIndex : 'type',
				renderer: function(value,metadata,record){
					if(value==0){
		                	 return Eway.locale.commen.comboxType.machineManager;
		             }else if(value==1){
		                	   return Eway.locale.commen.comboxType.machineRepairer;
		             }
				}
			},{
				header : Eway.locale.person.bankPer.organizationName,
				dataIndex : 'organizationName'
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
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});