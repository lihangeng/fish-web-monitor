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
				text: '设置',
				iconCls :'sureBtn',
				action: 'set'
			}],
			columns : [{
				header : '姓名',
				dataIndex : 'name'
			},{
				header : '性别',
				dataIndex : 'gender',
				renderer: function(value,metadata,record){
					if(value=="MALE"){
	                	   return "男";
	                   }else if(value=="FEMALE"){
	                	   return "女";
	                   }else{
	                	   return "未知";
	                   }
				}
			},{
				header : '出生年月',
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : '手机',
				dataIndex : 'mobile'
			}, {
				header : '固话',
				dataIndex : 'phone'
			},{
				header : '邮箱',
				dataIndex : 'email'
			},{
				header : '厂商',
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