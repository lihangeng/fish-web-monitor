
Ext.define('Eway.view.person.bankPer.Grid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.bank_person_grid',

	requires: ['Eway.lib.Util'],

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.BankPerson');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->', {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'bankPerAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'bankPerUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'bankPerDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '关联设备',
				glyph : 0xf0c1,
				action: 'link',
				code : 'bankPerlink',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '工号',
				dataIndex : 'jobNum'
			},{
				header : '姓名',
				dataIndex : 'name'
			}, {
				header : '岗位',
				dataIndex : 'personJobName'
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
			}, {
				header : '手机',
				dataIndex : 'mobile'
			},{
				header : '邮箱',
				dataIndex : 'email',
				width : 150
			}, {
				header : '固话',
				dataIndex : 'phone'
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
				header : '备注',
				dataIndex : 'remark',
				width : 150
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});