/**
 * 机构下的人员列表Grid
 */
Ext.define('Eway.view.person.organization.OrganizationManagerGrid', {
	alias: 'widget.Organization_organizationManagerGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.Person',{
			autoLoad : false
		});
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->', {
				text: EwayLocale.commen.setManager,
				glyph : 0xf0c1,
				action: 'set'
			}],
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
	                	   return EwayLocale.commen.comboxGender.unknow;
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
				header : EwayLocale.person.bankPer.organizationName,
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