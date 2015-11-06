/**
 * 机构下的人员列表Grid
 */
Ext.define('Eway.view.person.organization.OrganizationPersonGrid', {
	alias: 'widget.Organization_organizationPersonGrid',
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
				text: Eway.locale.commen.setManager,
				glyph : 0xf0c1,
				action: 'set'
			}],
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
	                	   return Eway.locale.commen.comboxGender.unknow;
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
				header : Eway.locale.commen.organizationName,
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