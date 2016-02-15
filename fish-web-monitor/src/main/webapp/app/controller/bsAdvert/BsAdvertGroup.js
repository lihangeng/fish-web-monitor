Ext.define('Eway.controller.bsAdvert.BsAdvertGroup', {
	extend : 'Ext.app.Controller',

	stores : [ 'bsAdvert.BsAdvertGroup','bsAdvert.BsAdvertGroupType','machine.DeviceAtmType','machine.atmType.DeviceAtmCatalog'],
	models : [ 'bsAdvert.BsAdvertGroup' ],
	views : [ 'bsAdvert.BsAdvertGroupView','bsAdvert.BsAdvertGroupFilterForm','bsAdvert.BsAdvertGroupGrid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		selector : '#bsadvertGroup', //这里的比较特殊，是对@@处的选择, 在其他地方没有任何定义
		autoCreate : true,//必须
		xtype : 'bs_advert_group_view',//必须
		id : 'bsadvertGroup'//@@
	}, {
		ref : 'grid',
		selector : 'bs_advert_group_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'bs_advert_group_filterform'
	},{
		ref : 'addWin',
		selector : 'bsAdvert_groupAdd'
	}],

	init : function() {
		 this.control({
			'#bsadvertGroup button[action=query]' : {
				click : this.onQuery
			},
			'#bsadvertGroup button[action=add]' : {
				click : this.onAdd
			},
			'#bsadvertGroup button[action=update]' : {
				click : this.onGroupUpdate
			},
			'#bsadvertGroup button[action=remove]' : {
				click : this.onGroupRemove
			},
			'#bsadvertGroup button[action=link]' : {
				click : this.onGroupLinkBefore
			},
			'#bsadvertGroup grid' :{
				rowclick : this.onRowClick
			},
			'#bsadvertGroup menuitem[action=preview1024]' :{
				click : this.onPreview1024
			},
			'#bsadvertGroup menuitem[action=preview800]' :{
				click : this.onPreview800
			},
			'#bsadvertGroup menuitem[action=preview600]' :{
				click : this.onPreview600
			}
		});
	},
	onRowClick:function( _this, record, tr, rowIndex, e, eOpts){
		var view = this.getEwayView();
//		if(record.get("advertFileName")!= null){
			view.down("grid button[code='advertPreview']").setDisabled(false);
//		}
//		else{
//			view.down("grid button[code='advertPreview']").setDisabled(true);
//		}
	},

	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('bs_advert_group_filterform').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('bs_advert_group_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	
	
	//增加广告组
	onAdd: function() {
		var win = Ext.create('Eway.view.bsAdvert.BsAdvertGroupAdd');
		win.down('button[action="add"]').on('click',Ext.bind(this.onAddConfirm,this,[win]),this);
		win.show();
	},
	

	onAddConfirm : function(win) {
		var ewayView = this.getEwayView();
		data = win.down('form').getForm().getValues();
		var record = Ext.create('Eway.model.bsAdvert.BsAdvertGroup',data);
		var store = this.getEwayView().down('bs_advert_group_grid').getStore();
		if(win.down('form').getForm().isValid()){// isValid对markInvalid不起作用
			record.save({
				success : function(record,operation){
					store.add(record);
					win.close();
					//点击增加成功后查询条件不带入重新查询。
					store.setUrlParamsByObject(null);
					store.loadPage(1);
					Eway.alert('保存成功');
			    },
			    failure: function(record,operation){
			    	Eway.alert(operation.error);
				},
				button:win.down('button[action="add"]')
			});
		}
	},
	
	
	onGroupUpdate : function(){
		var grid = this.getEwayView().down('bs_advert_group_grid');
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var win = Ext.create('Eway.view.bsAdvert.BsAdvertGroupUpdate');
			var record = sm.getLastSelected();
			win.down('form').getForm().loadRecord(record);
			win.down('button[action="update"]').on('click',Ext.bind(this.onGroupUpdateConfirm,this,[win]),this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	onGroupUpdateConfirm : function(win) {
		var ewayView = this.getEwayView();
		var sm = ewayView.down('bs_advert_group_grid').getSelectionModel();
		var record = sm.getLastSelected();
		var data = win.down('form').getValues();
		var store = ewayView.down('bs_advert_group_grid').getStore();
		var id = record.get("id");
		if(win.down('form').getForm().isValid()){
			record.set("groupName",Ext.String.trim(data.groupName));
			record.set("groupType",data.groupType);
			record.save({
				success : function(recordResult,operation){
					
					if(undefined==recordResult.get("id")||""==recordResult.get("id")||0==recordResult.get("id")){
						recordResult.set("id",id);
					}
					store.applyModel(recordResult);
					Eway.alert(EwayLocale.updateSuccess);
					win.close();
				},
				failure: function(record,operation){
					Eway.alert(operation.getError());
					//解决脏数据
					store.rejectChanges();
				},
				button:win.down('button[action="update"]')
			});
		}
	},
	
	
	onGroupRemove : function() {
		var grid = this.getEwayView().down('bs_advert_group_grid');
		var me  = this;
		var sm = grid.getSelectionModel();
		var quarydata = this.getEwayView().down('form').getForm().getValues();// 得到所有的查询条件的值
		var store = this.getEwayView().down('bs_advert_group_grid').getStore();
		if(sm.getCount() == 1) {
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,
					EwayLocale.tip.isConfirmRemove,
					function(button,text) {
						if(button=="yes"){
							var record = sm.getLastSelected();
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									store.setUrlParamsByObject(quarydata);
									store.load( {scope: this,
										callback: function(){
											if(grid.getStore().getCount()>0){
												grid.getSelectionModel().select(0);
//												me.onDeviceQueryDevice();
											}else{
												var view2 = this.getEwayView();
												var store2 = view2.down('bs_advert_group_grid').getStore();
													store2.setUrlParam('groupId','0');
													store2.loadPage(1);
											}
									}});
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									record.dropped = false;
									Eway.alert(operation.getError());
								},
								scope:this
							});
						}
					}, this);
		}
		else {
			Eway.alert(EwayLocale.choiceDeleteMsg);
		}

	},
	
	onGroupLinkBefore :function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		var me = this;
		
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			
			if(record.data.groupType == 1){
				Eway.alert('默认组不需要绑定设备');
				return;
			}
			Ext.Ajax.request({
				url :"api/bsadvert/advertgroup/actived",
				params: {
					advertGroupId: record.get('id')
			    },
				success: function(response){
					var obj = Ext.decode(response.responseText);
			        if(obj.success==true){
			        	me.onGroupLink();
			        }
			        else{
						Eway.alert(obj.errorMsg);
			        }
			        
			    },
			    failure:function(){
			    	Eway.alert(obj.errorMsg);
			    }
			});
		}
		else {
			Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
		}
	},
	
	/*关联设备管理*/
	onGroupLink : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			
			var advertDeviceWin = Ext.create('Eway.view.bsAdvert.BsAdvertDevice');
			var linkedFilterForm = advertDeviceWin.down('bsAdvert_linkedDeviceFilter');
			var linkedDeviceGrid = advertDeviceWin.down('bsAdvert_linkedDeviceGrid');

			var linkingDeviceGrid = advertDeviceWin.down('bsAdvert_linkingDeviceGrid');
			var linkingFilterForm = advertDeviceWin.down('bsAdvert_linkingDeviceFilter');

			linkedDeviceGrid.down('button[action="unlink"]').on('click',
					Ext.bind(this.onUnlinkConfirm,this,[linkedDeviceGrid,linkingDeviceGrid,record]),this
				);
			linkedDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.onQueryDevice,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
				);
			linkedDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.onLinkDeviceFresh,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
				);
			linkingDeviceGrid.down('button[action="link"]').on('click',
					Ext.bind(this.onLinkConfirm,this,[linkingDeviceGrid,linkedDeviceGrid,record]),this
				);
			linkingDeviceGrid.down('button[action="query"]').on('click',
					Ext.bind(this.onQueryDevice,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
				);
			linkingDeviceGrid.down('pagingtoolbar').on('beforechange',
					Ext.bind(this.onLinkDeviceFresh,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
			);

			advertDeviceWin.show();
			linkingDeviceGrid.getStore().load({
				params : {
					flag:1,
					guid:record.data.id,
					organizationId:record.data.orgId
				}
			});
			linkedDeviceGrid.getStore().load({
				params : {
					flag:0,
					guid:record.data.id,
					organizationId:record.data.orgId
				}
			});
		}
		else {
			Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
		}
	},
	//刷新“已/可关联的设备”列表
	onLinkDeviceFresh : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.id);
		store.setBaseParam('organizationId',record.data.orgId);
	},

	onQueryDevice : function(flag,grid,form,record){
		var store = grid.getStore();
		store.cleanUrlParam();
		var data = form.getForm().getValues();
		store.setUrlParamsByObject(data);
		store.setBaseParam('flag',flag);
		store.setBaseParam('guid',record.data.id);
		store.setBaseParam('organizationId',record.data.orgId);
		store.loadPage(1);
	},

	/**
	 * 获得选中的行id数组：
	 * @param {} grid
	 * @return {}
	 */
	multiSelect : function(grid){
		var record=grid.getSelectionModel().getSelection();
        if(record == null || record.length == 0){
        	return null;
        }
        var array = new Array(record.length);
        for(var i=0;i<record.length;i++)
        {
            array[i] = record[i].get('id');
        }
        return array;
	},


	/**
	 * 解除关联
	 * @param {} linkedDeviceGrid
	 * @param {} linkingDeviceGrid
	 * @param {} record
	 */
	onUnlinkConfirm : function(linkedDeviceGrid,linkingDeviceGrid,record){
		var array = this.multiSelect(linkedDeviceGrid);
		if(array != null) {
			var info = '';
			for(var i=0;i<array.length;i++){
                    info += array[i] + ',';
            }
			Ext.Ajax.request({
				scope : this,
				method : 'POST',
				url : 'api/bsadvert/advertgroup/unlink',
				params : {groupId :record.data.id,deviceId:info},
				success: function(){
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
					linkingDeviceGrid.getStore().cleanUrlParam();
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
				},
				failure: function(){
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkDevFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.unlinkDev);
		}
	},

	/**
	 * 进行关联
	 * @param {} linkingDeviceGrid
	 * @param {} linkedDeviceGrid
	 * @param {} record
	 */
	onLinkConfirm : function(linkingDeviceGrid,linkedDeviceGrid,record){

		var array = this.multiSelect(linkingDeviceGrid);
		if(array != null) {
			linkingDeviceGrid.down('button[action="link"]').disable();
			var data = new Object();
			var record2 = Ext.create('Eway.model.bsAdvert.BsAdvertDevice',data);
			for(var i=0;i<array.length-1;i++){
            	record2.set('id',0);
				record2.set('groupId',record.data.id);
				record2.set('deviceId',array[i]);
				record2.save();
            }
			record2.set('id',0);
			record2.set('groupId',record.data.id);
			record2.set('deviceId',array[array.length-1]);
			record2.save({
				success: function(){
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.id,
							organizationId:record.data.orgId
						},
						callback: function(records, operation, success) {
							linkingDeviceGrid.down('button[action="link"]').enable();
						}
					});
					linkedDeviceGrid.getStore().cleanUrlParam();
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
				},
				failure: function(){
					linkingDeviceGrid.down('button[action="link"]').enable();
					Eway.alert(EwayLocale.tip.bankPer.link.unLinkPersonFail);
				},
				scope:this
			});
		}else{
			Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
		}
	},
	onPreview1024 : function(){
		this.onPreview("1024");
	},

	onPreview800 : function(){
		this.onPreview("800");
	},

	onPreview600 : function(){
		this.onPreview("600");
	},
	//广告预览
	onPreview : function(screen){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
				var advertId = record.get('id');
				Ext.Ajax.request({
					url :"api/bsadvert/advertgroup/preview2",
					params: {
				        id: advertId,
				        screen:screen
				    },
					success: function(response){
						 var images = Ext.decode(response.responseText);
						 if(Ext.isEmpty(images)){
						 	Eway.alert("["+screen+"]"+EwayLocale.msg.noAdvertResAtTheResolution);
						 }else{
							 var vedioCfg = '<OBJECT id=WindowsMediaPlayer1 name="player" height=490 width=700 classid=clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6>'+
												'<PARAM NAME="URL" VALUE="">'+
												'<PARAM NAME="rate" VALUE="1">'+
												'<PARAM NAME="balance" VALUE="0">'+
												'<PARAM NAME="currentPosition" VALUE="0">'+
												'<PARAM NAME="defaultFrame" VALUE="">'+
												'<PARAM NAME="playCount" VALUE="1">'+
												'<PARAM NAME="autoStart" VALUE="-1">'+
												'<PARAM NAME="currentMarker" VALUE="0">'+
												'<PARAM NAME="invokeURLs" VALUE="-1">'+
												'<PARAM NAME="baseURL" VALUE="">'+
												'<PARAM NAME="volume" VALUE="50">'+
												'<PARAM NAME="mute" VALUE="0">'+
												'<PARAM NAME="uiMode" VALUE="full">'+
												'<PARAM NAME="stretchToFit" VALUE="0">'+
												'<PARAM NAME="windowlessVideo" VALUE="0">'+
												'<PARAM NAME="enabled" VALUE="-1">'+
												'<PARAM NAME="enableContextMenu" VALUE="-1">'+
												'<PARAM NAME="fullScreen" VALUE="0">'+
												'<PARAM NAME="SAMIStyle" VALUE="">'+
												'<PARAM NAME="SAMILang" VALUE="">'+
												'<PARAM NAME="SAMIFilename" VALUE="">'+
												'<PARAM NAME="captioningID" VALUE="">'+
												'<PARAM NAME="enableErrorDialogs" VALUE="0">'+
												'<PARAM NAME="_cx" VALUE="12700">'+
												'<PARAM NAME="_cy" VALUE="9525">'+
													'<embed src="" width="480" height="360" autostart="-1" url="" rate="1" balance="0" currentposition="0" defaultframe="" playcount="1" '+
														'currentmarker="0" invokeurls="-1" baseurl="" volume="50" mute="0" uimode="full" stretchtofit="0" windowlessvideo="0" '+
													    'enabled="-1" enablecontextmenu="-1" fullscreen="0" samistyle="" samilang="" samifilename="" captioningid="" enableerrordialogs="0" _cx="12700" _cy="9525">'+
													'</embed> '+
											'</OBJECT>';
							 var len = images.length;
							 var stoped = false;
							 var win = Ext.create('Ext.window.Window',{
								title:EwayLocale.advert.advertPreviewTitle0+ len +EwayLocale.advert.advertPreviewTitle1,
								width: 705,
								height: document.body.clientHeight < 525 ? document.body.clientHeight: 525,
								layout : 'fit',
								maximizable: false,
								autoScroll : true,
								modal : true,
								resizable : true,
								constrainHeader : true,
								html:'<div class="_previewAD"><img width="680" height="485"></div><div class="_previewVedio" style="display:none">'+vedioCfg+'</div>',
								listeners:{
									'beforeclose' : function(win){
										stoped = true;
									}
								}
							});

							win.show();
							var title = win.title;
							var el = win.getEl();
							var div = Ext.DomQuery.select("div[class=_previewAD]",el.dom)[0],
								divVedio =  Ext.DomQuery.select("div[class=_previewVedio]",el.dom)[0],
								img = Ext.DomQuery.select("div[class=_previewAD] img",el.dom)[0],
								v = document.getElementById('WindowsMediaPlayer1');
							var time = 0;
							var i = 0;
							var playNext = function(){
								if(!stoped){
									if(i < len){
										var currentPic = images[i];
										if(currentPic.picName.indexOf('.avi') > 0){
											divVedio.style.display = "block";
											div.style.display = "none";
											img.src = "";
											if(Ext.isIE){
												v.URL = currentPic.picName;
											}else{
												divVedio.innerHTML = '<font color="red">'+EwayLocale.advert.perviewAdertWithIEBrowse+'</font>';
											}
										}else{
											divVedio.style.display = "none";
											div.style.display = "block";
											img.src = currentPic.picName;
										}

									   	win.setTitle(title + (i+1) +EwayLocale.advert.advertPreviewTitle2);
									   	time = currentPic.playTime * 1000;
										i ++
									}
									if(i==len){
										i = 0;
									}

									setTimeout(function(){
										playNext();
									},time);
								}
							};
							playNext();
						}
					}
				});
		}else{
			Eway.alert(EwayLocale.msg.choseResToPerview);
		}
	}
	
});
