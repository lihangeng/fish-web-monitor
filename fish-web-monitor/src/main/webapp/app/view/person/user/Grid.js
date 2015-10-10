
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
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'userAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'userUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.remove,
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
				header : Eway.locale.person.user.code,
				dataIndex : 'code',
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+Eway.locale.person.user.clickToCheckLog + value+Eway.locale.person.user.userLog+'"';
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				}
			},{
				header : Eway.locale.commen.name,
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
			}, {
				header : Eway.locale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : Eway.locale.commen.phone,
				dataIndex : 'phone'
			}, {
				header : Eway.locale.person.bankPer.organizationName,
				dataIndex : 'organizationName'
			},{
				header : Eway.locale.commen.email,
				dataIndex : 'email'
			},{
				header : Eway.locale.commen.state,
				dataIndex : 'userState',
				renderer: function(value,metadata,record){
						if(value==0){
	                	   return Eway.locale.person.bankOrg.organizationStateDict.newCreate;
	                   }else if(value==1){
	                	   return Eway.locale.person.bankOrg.organizationStateDict.newCreate;
	                   }else if(value==2){
	                	   return Eway.locale.person.bankOrg.organizationStateDict.normal;
	                   }else if(value==3){
	                	   return Eway.locale.person.bankOrg.organizationStateDict.locked;
	                   }else if(value==4){
	                	   return Eway.locale.person.bankOrg.organizationStateDict.deleted;
	                   }else{
	                	   return Eway.locale.person.bankOrg.organizationStateDict.frozen;
	                   }
					}
			},{
				header : Eway.locale.person.user.resetPasswd,
				xtype:'actioncolumn',
				items:[{
                    icon:"././././resources/images/update.png",
					getClass:function(v,metadata,r,rowIndex,colIndex,store){
						var rec=store.getAt(rowIndex);
						if(rec.data.code==ewayUser.getCode()){
							return "actioncolumn-hidden";
	                    }else{
	                        metadata.tdAttr ='data-qtip="'+Eway.locale.person.user.clickToPasswdInit+'"';
	                    }
					},
                    handler:function(grid,rowIndex,colIndex){
                       var rec=grid.getStore().getAt(rowIndex);
                       var username = rec.get('code');
	                   var gridEl = grid.getEl();
					   Ext.MessageBox.confirm(Eway.locale.tip.tips,Eway.locale.tip.passwd.confirmPasswd,callBack);
					    function callBack(button){
					    	if(button=='yes'){
					    		gridEl.mask(Eway.locale.tip.tips.passwd.resetPasswding);
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
			               				Eway.alert(Eway.locale.tip.tips.passwd.resetPasswdFail);
			               			}
		               			});
					    	}
					    }
					}
				}]
			},{
				header : Eway.locale.person.user.role,
				dataIndex : 'roles',
				flex :1,
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+Eway.locale.person.user.clickToRole+'"';
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