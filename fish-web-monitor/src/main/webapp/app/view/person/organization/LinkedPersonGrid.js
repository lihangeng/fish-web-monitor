
Ext.define('Eway.view.person.organization.LinkedPersonGrid', {
	alias: 'widget.linkedPersonGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	border : false,
	
	title: '当前机构下人员：',
	
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
				header : '类型',
				dataIndex : 'type',
				renderer: function(value,metadata,record){
					if(value==0){
		                	 return "管机员";
		             }else if(value==1){
		                	   return "维修人员";
		             }
				}
			},{
				header : '机构',
				dataIndex : 'organizationName'
			},{
				header : '状态',
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return "在岗";
		             }else if(value==2){
		                	   return "调休";
		             }else if(value==3){
		                	   return "休假";
		             }else if(value==0){
		                	   return "其他";
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