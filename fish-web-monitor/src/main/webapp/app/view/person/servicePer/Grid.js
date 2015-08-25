
Ext.define('Eway.view.person.servicePer.Grid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.ser_person_grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.SerPerson');
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
				code : 'servicePerAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'servicePerUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'servicePerDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '关联设备',
				glyph : 0xf0c1,
				action: 'link',
				code : 'servicePerlink',
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
			},{
				header : '厂商',
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
				dataIndex : 'email'
			}, {
				header : '固话',
				dataIndex : 'phone'
			},{
				header : '备注',
				dataIndex : 'remark',
				flex :1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});