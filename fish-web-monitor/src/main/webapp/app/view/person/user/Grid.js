
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'userAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'userUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,
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
				header : EwayLocale.person.user.code,
				dataIndex : 'code',
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+EwayLocale.person.user.clickToCheckLog + value+EwayLocale.person.user.userLog+'"';
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";
					}else{
						return value;
					}
				}
			},{
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
			}, {
				header : EwayLocale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : EwayLocale.commen.phone,
				dataIndex : 'phone'
			}, {
				header : EwayLocale.person.bankPer.organizationName,
				dataIndex : 'organizationName'
			},{
				header : EwayLocale.commen.email,
				dataIndex : 'email'
			},{
				header : EwayLocale.commen.state,
				dataIndex : 'userState',
				renderer: function(value,metadata,record){
						if(value==0){
	                	   return EwayLocale.commen.stateDict.newCreate;
	                   }else if(value==1){
	                	   return EwayLocale.commen.stateDict.newCreate;
	                   }else if(value==2){
	                	   return EwayLocale.commen.stateDict.normal;
	                   }else if(value==3){
	                	   return EwayLocale.commen.stateDict.locked;
	                   }else if(value==4){
	                	   return EwayLocale.commen.stateDict.deleted;
	                   }else{
	                	   return EwayLocale.commen.stateDict.frozen;
	                   }
					}
			},{
				header : EwayLocale.person.user.resetPasswd,
				xtype:'actioncolumn',
				menuText : EwayLocale.person.user.resetPasswd,
				items:[{
                    icon:"././././resources/images/update.png",
					getClass:function(v,metadata,r,rowIndex,colIndex,store){
						var rec=store.getAt(rowIndex);
						if(rec.data.code==Eway.user.getCode()){
							return "actioncolumn-hidden";
	                    }else{
	                        metadata.tdAttr ='data-qtip="'+EwayLocale.person.user.clickToPasswdInit+'"';
	                    }
					},
                    handler:function(grid,rowIndex,colIndex){
                       var rec=grid.getStore().getAt(rowIndex);
                       var username = rec.get('code');
	                   var gridEl = grid.getEl();
					   Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.passwd.confirmPasswd,callBack);
					    function callBack(button){
					    	if(button=='yes'){
					    		gridEl.mask(EwayLocale.tip.passwd.resetPasswding);
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
			               					//修改成功置为新建状态
			                                rec.set('userState',1);
			               					Eway.alert(object.message);
			               				}else{
			               					gridEl.unmask();
			               					Eway.alert(object.errors);
			               				}
			               			},
			               			failure : function(){
			               				gridEl.unmask();
			               				Eway.alert(EwayLocale.tip.passwd.resetPasswdFail);
			               			}
		               			});
					    	}
					    }
					}
				}]
			},{
				header : EwayLocale.person.user.role,
				dataIndex : 'roles',
				flex :1,
				renderer:function(value,meta,record){
					meta.tdAttr ='data-qtip="'+EwayLocale.person.user.clickToRole+'"';
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