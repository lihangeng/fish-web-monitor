
Ext.define('Eway.view.person.user.Grid', {
	alias: 'widget.user_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,

	/*title: '符合条件的用户：',*/
	initComponent: function() {
		var store = Ext.create('Eway.store.person.user.User');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'userAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'userUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'userDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '用户名',
				dataIndex : 'code',
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+"单击即可查看用户   " + value+" 的操作日志"+'"';
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				}
			},{
				header : '姓名',
				dataIndex : 'name'
//			},{
//				header : '用户类型',
//				dataIndex : 'userType',
//				renderer: function(value,metadata,record){
//					if(value=="0"){
//	                	   return "超级用户";
//	                   }else if(value=="1"){
//	                	   return "普通用户";
//	                   }else{
//	                	   return "未知";
//	                   }
//					}
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
			}, {
				header : '手机',
				dataIndex : 'mobile'
			}, {
				header : '固话',
				dataIndex : 'phone'
			}, {
				header : '机构',
				dataIndex : 'organizationName'
			},{
				header : '邮箱',
				dataIndex : 'email'
			},{
				header : '状态',
				dataIndex : 'userState',
				renderer: function(value,metadata,record){
						if(value==0){
	                	   return "新建";
	                   }else if(value==1){
	                	   return "新建";
	                   }else if(value==2){
	                	   return "正常";
	                   }else if(value==3){
	                	   return "锁定";
	                   }else if(value==4){
	                	   return "已删除";
	                   }else{
	                	   return "冻结";
	                   }
					}
			},{
				header : '密码重置',
				xtype:'actioncolumn',
				items:[{
                    icon:"././././resources/images/update.png",
					getClass:function(v,metadata,r,rowIndex,colIndex,store){
						var rec=store.getAt(rowIndex);
						if(rec.data.code==ewayUser.getCode()){
							return "actioncolumn-hidden";
	                    }else{
	                        metadata.tdAttr ='data-qtip="'+"单击即可密码重置为初始化密码"+'"';
	                    }
					},
                    handler:function(grid,rowIndex,colIndex){
                       var rec=grid.getStore().getAt(rowIndex);
                       var username = rec.get('code');
	                   var gridEl = grid.getEl();
					   Ext.MessageBox.confirm('提示','是否确认密码重置？',callBack);
					    function callBack(button){
					    	if(button=='yes'){
					    		gridEl.mask('正在重置密码......');
					    		Ext.Ajax.request({
			               			method : 'POST',
			               			url : 'api/person/user/resetPassword',
			               			params :{
			               				username: username,
			               				newPassword : '888888'
			               			},
			               			success : function(response){
			               				var object = Ext.decode(response.responseText);
			               				if(object.success == true){
			               					gridEl.unmask();
			               					Eway.alert(object.message);
			               				}else{
			               					gridEl.unmask();
			               					Eway.alert(object.errors);
			               				}
			               			},
			               			failure : function(){
			               				gridEl.unmask();
			               				Eway.alert("密码重置失败！");
			               			}
		               			});
					    	}
					    }
					}
				}]
			},{
				header : '角色',
				dataIndex : 'roles',
				flex :1,
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+"单击即可查看所有角色列表"+'"';
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
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