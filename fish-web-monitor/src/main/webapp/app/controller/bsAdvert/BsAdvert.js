Ext.define('Eway.controller.bsAdvert.BsAdvert', {
	extend : 'Ext.app.Controller',

	stores : [ 'bsAdvert.BsAdvertStatus', 'bsAdvert.BsAdvert','bsAdvert.BsAdvertResource','bsAdvert.BsAdvertGroupList',
				'advert.AdvertDownMethod','Hour','Minute'],
	models : [ 'bsAdvert.BsAdvert','bsAdvert.BsAdvertResource' ],
	views : [ 'bsAdvert.BsAdvertView','bsAdvert.AddBsWait','bsAdvert.BsAdvertFilterForm','bsAdvert.BsAdvertGrid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		selector : '#bsadvert', //这里的比较特殊，是对@@处的选择, 在其他地方没有任何定义
		autoCreate : true,//必须
		xtype : 'bs_advert_view',//必须
		id : 'bsadvert'//@@
	}, {
		ref : 'grid',
		selector : 'bs_advert_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'bs_advert_filterForm'
	},{
		ref : 'addWaitWin',
		selector: 'advert_add_bsWait'
	}],

	init : function() {
		 this.control({
			'#bsadvert button[action=query]' : {
				click : this.onQuery
			},
			'#bsadvert button[action=add]' : {
				click : this.onAdd
			},
			'#bsadvert button[action=update]' : {
				click : this.onLoadBsAdvert
			},
			'#bsadvert button[action=actived]' : {
				click : this.onActive
			},
			'#bsadvert button[action=remove]' : {
				click : this.onRemove
			},
			'#bsadvert grid' :{
				rowclick : this.onRowClick
			},
			'#bsadvert menuitem[action=preview1024]' :{
				click : this.onPreview1024
			},
			'#bsadvert menuitem[action=preview800]' :{
				click : this.onPreview800
			},
			'#bsadvert menuitem[action=preview600]' :{
				click : this.onPreview600
			}
		});
	},
	onRowClick:function( _this, record, tr, rowIndex, e, eOpts){
		var view = this.getEwayView();
		if(record.get("advertFileName")!=null){
			view.down("grid button[code='advertPreview']").setDisabled(false);
			view.down("grid button[code='bsAdvertActive']").setDisabled(false);
			view.down("grid button[code='bsAdvertUpdate']").setDisabled(false);
		}
		else{
			view.down("grid button[code='advertPreview']").setDisabled(true);
			view.down("grid button[code='bsAdvertActive']").setDisabled(true);
			view.down("grid button[code='bsAdvertUpdate']").setDisabled(true);
		}
	},
	onRemove:function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,EwayLocale.tip.remove.confirm.info,
					function(button,text) {
						if(button=="yes"){
							record.erase({
								success: function(){
									Eway.alert(EwayLocale.deleteSuccess);
									//刷新详细配置列表
									grid.getStore().remove(record);
								},
								failure: function(record,operation){
									//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
									var object = Ext.decode(operation._response.responseText);
									record.dropped = false;
									Eway.alert(object.errorMsg);
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
	onActive:function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('id') != 0 && record.get('advertFileName') != null){
				var me = this;
				Ext.Ajax.request({
					url :"api/bsadvert/advert/actived",
					params: {
						advertId: record.get('id')
				    },
					success: function(response){
						var obj = Ext.decode(response.responseText);
				        if(obj.success==true){
							Eway.alert(EwayLocale.bsAdvert.activedSuccessfully);
				        }
				        else{
							Eway.alert(obj.errorMsg);
				        }
				        me.onQuery();
				    },
				    failure:function(){
				    	Eway.alert(EwayLocale.bsAdvert.activedFailed);
				    }
				});
				
			}else{
				Eway.alert(EwayLocale.bsAdvert.notExistFile);
			}
		}
		else {
			Eway.alert(EwayLocale.msg.chooseAdvert);
		}
	},
	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('bs_advert_filterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('bs_advert_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	onLoadBsAdvert:function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('id') != 0 && record.get('advertFileName') != null){
				var me = this;
				Ext.Ajax.request({
					url :"api/bsadvert/advert/loadAdvertToUpdate",
					params: {
						advertId: record.get('id')
				    },
					success: function(response){
						var obj = Ext.decode(response.responseText);
				        if(obj.success==true){
							var info1024 = obj.data.screen1024;
							var info800 = obj.data.screen800;
							var info600 = obj.data.screen600;
							me.onUpdate(record,info1024,info800,info600);
				        }
				        else{
							Eway.alert(obj.errorMsg);
				        }
				    },
				    failure:function(){
				    	Eway.alert(EwayLocale.bsAdvert.loadingFail);
				    }
				});
				
			}else{
				Eway.alert(EwayLocale.bsAdvert.notExistFile);
			}
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},
	loadPic:function(s1024,info1024){
		var s1024Store = s1024.getStore();
		for(var index=0;index<info1024.length;index++){
			var record = Ext.create("Eway.model.bsAdvert.BsAdvertResource",info1024[index]);
			s1024Store.insert(index,record);
		}
	},
	onUpdate:function(record,info1024,info800,info600){
		var me = this;
		var win = Ext.create("Eway.view.bsAdvert.AddBsWait");
		win.setTitle(EwayLocale.advert.updateTitle);
		win.down("field_advert_advertGroup").setDisabled(true);
		var tab = win.down('advert_bs_waitTab');
		var s1024 = tab.down('bsadvertimgview[name=1024]');
		//加载1024图片
		this.loadPic(s1024,info1024);
		var s800 = tab.down('bsadvertimgview[name=800]');
		//加载800图片
		this.loadPic(s800,info800);
		var s600 = tab.down('bsadvertimgview[name=600]');
		//加载600图片
		this.loadPic(s600,info600);

		win.down("field_advert_advertGroup").setValue([record.get("groupId"),record.get("groupName")]);
		win.down("textfield[name='advertName']").setValue(record.get("advertName"));
		win.down("hidden[name='id']").setValue(record.get("id"));
		
		
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', Ext.bind(me.onUpdateWaitConfirm,this,[record]), this);
		var b5 = win.query('filefield[name=file]')[0];
		b5.on('change',this.onFileChangedScreen,this);
		var b51 = win.query('filefield[name=file]')[1];
		b51.on('change',this.onFileChangedScreen,this);
		var b52 = win.query('filefield[name=file]')[2];
		b52.on('change',this.onFileChangedScreen,this);
		var b6 = win.query('bsadvertimgview[name=1024]')[0];
		b6.on('itemclick',this.onAdvertImgItemClick,this);
		var b61 = win.query('bsadvertimgview[name=800]')[0];
		b61.on('itemclick',this.onAdvertImgItemClick,this);
		var b62 = win.query('bsadvertimgview[name=600]')[0];
		b62.on('itemclick',this.onAdvertImgItemClick,this);
		var resConfigForm = win.down('advert_bs_resourceConfigForm').getForm();
		var b7 = resConfigForm.findField('playTime');
		b7.on('change',this.onResourceConfigChanged,this);
		var b8 = resConfigForm.findField('beginDate');
		b8.on('change',this.onResourceConfigChanged,this);
		var b9 = resConfigForm.findField('endDate');
		b9.on('change',this.onResourceConfigChanged,this);
		var b10 = resConfigForm.findField('beginHour');
		b10.on('change',this.onResourceConfigChanged,this);
		var b11 = resConfigForm.findField('beginMinute');
		b11.on('change',this.onResourceConfigChanged,this);
		var b12 = resConfigForm.findField('beginSecond');
		b12.on('change',this.onResourceConfigChanged,this);
		var b13 = resConfigForm.findField('endHour');
		b13.on('change',this.onResourceConfigChanged,this);
		var b14 = resConfigForm.findField('endMinute');
		b14.on('change',this.onResourceConfigChanged,this);
		var b15 = resConfigForm.findField('endSecond');
		b15.on('change',this.onResourceConfigChanged,this);
		win.show();
	},
	onUpdateWaitConfirm:function(record){
		var win = this.getAddWaitWin();
		var addForm = win.down("form").getForm();
		var store = Ext.StoreManager.get("bsAdvert.BsAdvert");
		if(addForm.isValid()){
			var tab = win.down('advert_bs_waitTab');
			var s1024 = tab.down('bsadvertimgview[name=1024]');
			var s1024Store = s1024.getStore();
			if(s1024Store.getCount() == 0){
				Eway.alert(EwayLocale.advert.mustContainerOnePicAt1024);
				return;
			}

			var s800 = tab.down('bsadvertimgview[name=800]');
			var s800Store = s800.getStore();
			var s600 = tab.down('bsadvertimgview[name=600]');
			var s600Store = s600.getStore();
			var data = addForm.getValues();
			this.doUpdate(win,data,s1024Store,s800Store,s600Store,record);
		}
	},
	doUpdate : function(win,data,s1024Strore,s800Strore,s600Strore,record){
		var me =this;
		var advRess = [];
    	this.generateAdvertResource(advRess,s1024Strore);
    	this.generateAdvertResource(advRess,s800Strore);
    	this.generateAdvertResource(advRess,s600Strore);
    	var groupId = win.down("field_advert_advertGroup").getValue();
    	var advertName = win.down("textfield[name='advertName']").getValue();
    	var resources = '[';
    	for(var i in advRess){
    		var res = advRess[i];
			var advRes_str = "{'id':"+res.data.id+",'playTime':" + res.data.playTime + ",'beginTime':'" + res.data.beginTime
			+ "','endTime':'"+res.data.endTime
			+"','beginDate':'"+Ext.Date.format(res.data.beginDate, "Y-m-d")
			+"','endDate':'"+Ext.Date.format(res.data.endDate, "Y-m-d")
			+"','screen':'"+res.data.screen
			+ "','content':'" + res.data.content +"'}";
			if(resources == '['){
				resources = resources + advRes_str;
			}else{
				resources = resources + "," + advRes_str;
			}
    	}
    	resources = resources + "]";

    	var adv = Ext.create("Eway.model.bsAdvert.BsAdvert",{
    		advertType : "WAIT_INSERT_CARD",
    		groupId : groupId,
    		id:win.down("hidden[name='id']").getValue(),
    		advertName : advertName,
    		resources : resources
    	});
		var btn = win.down('button[action=confirm]');
		var id = win.down("hidden[name='id']").getValue();
		
    	adv.save({
			 success: function(recordResult,operation) {
				var view = me.getEwayView();
				record.set("advertName",recordResult.get("advertName"));
				record.set("lastTime",recordResult.get("lastTime"));
				record.set("userId",recordResult.get("userId"));
				record.set("userName",recordResult.get("userName"));
				record.set("activeUserId",recordResult.get("activeUserId"));
				record.set("activeUserName",recordResult.get("activeUserName"));
				record.set("bsAdvertStatus",recordResult.get("bsAdvertStatus"));
				record.set("groupName",recordResult.get("groupName"));
				Eway.alert(EwayLocale.updateSuccess);
				win.close();
			 },
			 failure: function(record,operation){
				Eway.alert(operation.error);
			 },
			 button:btn
		});
	},
	//增加
	onAdd:function(){
		var win = Ext.create("Eway.view.bsAdvert.AddBsWait");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddWaitConfirm, this);
		var b5 = win.query('filefield[name=file]')[0];
		b5.on('change',this.onFileChangedScreen,this);
		var b51 = win.query('filefield[name=file]')[1];
		b51.on('change',this.onFileChangedScreen,this);
		var b52 = win.query('filefield[name=file]')[2];
		b52.on('change',this.onFileChangedScreen,this);
		var b6 = win.query('bsadvertimgview[name=1024]')[0];
		b6.on('itemclick',this.onAdvertImgItemClick,this);
		var b61 = win.query('bsadvertimgview[name=800]')[0];
		b61.on('itemclick',this.onAdvertImgItemClick,this);
		var b62 = win.query('bsadvertimgview[name=600]')[0];
		b62.on('itemclick',this.onAdvertImgItemClick,this);
		var resConfigForm = win.down('advert_bs_resourceConfigForm').getForm();
		var b7 = resConfigForm.findField('playTime');
		b7.on('change',this.onResourceConfigChanged,this);
		var b8 = resConfigForm.findField('beginDate');
		b8.on('change',this.onResourceConfigChanged,this);
		var b9 = resConfigForm.findField('endDate');
		b9.on('change',this.onResourceConfigChanged,this);
		var b10 = resConfigForm.findField('beginHour');
		b10.on('change',this.onResourceConfigChanged,this);
		var b11 = resConfigForm.findField('beginMinute');
		b11.on('change',this.onResourceConfigChanged,this);
		var b12 = resConfigForm.findField('beginSecond');
		b12.on('change',this.onResourceConfigChanged,this);
		var b13 = resConfigForm.findField('endHour');
		b13.on('change',this.onResourceConfigChanged,this);
		var b14 = resConfigForm.findField('endMinute');
		b14.on('change',this.onResourceConfigChanged,this);
		var b15 = resConfigForm.findField('endSecond');
		b15.on('change',this.onResourceConfigChanged,this);
		win.show();
	},
	//点击添加确认按钮
	onAddWaitConfirm:function(){
		var win = this.getAddWaitWin();
		var addForm = win.down("form").getForm();
		var store = Ext.StoreManager.get("bsAdvert.BsAdvert");
		if(addForm.isValid()){
			var tab = win.down('advert_bs_waitTab');
			var s1024 = tab.down('bsadvertimgview[name=1024]');
			var s1024Store = s1024.getStore();
			if(s1024Store.getCount() == 0){
				Eway.alert(EwayLocale.advert.mustContainerOnePicAt1024);
				return;
			}

			var s800 = tab.down('bsadvertimgview[name=800]');
			var s800Store = s800.getStore();
			var s600 = tab.down('bsadvertimgview[name=600]');
			var s600Store = s600.getStore();
			var data = addForm.getValues();
			this.doSaveWait(win,data,s1024Store,s800Store,s600Store);
		}
	},
	//将dataview中数据进行收集
	generateAdvertResource : function(advRess,store){
		for(var i = 0; i < store.getCount(); i++){
			var record = store.getAt(i);
			advRess.push(record.convertToAdvertResource());
		}
	},
	//保存操作
	doSaveWait : function(win,data,s1024Strore,s800Strore,s600Strore){
		var me =this;
		var advRess = [];
    	this.generateAdvertResource(advRess,s1024Strore);
    	this.generateAdvertResource(advRess,s800Strore);
    	this.generateAdvertResource(advRess,s600Strore);
    	var groupId = win.down("field_advert_advertGroup").getValue();
    	var advertName = win.down("textfield[name='advertName']").getValue();
    	var resources = '[';
    	for(var i in advRess){
    		var res = advRess[i];
			var advRes_str = "{'playTime':" + res.data.playTime + ",'beginTime':'" + res.data.beginTime
			+ "','endTime':'"+res.data.endTime
			+"','beginDate':'"+Ext.Date.format(res.data.beginDate, "Y-m-d")
			+"','endDate':'"+Ext.Date.format(res.data.endDate, "Y-m-d")
			+"','screen':'"+res.data.screen
			+ "','content':'" + res.data.content +"'}";
			if(resources == '['){
				resources = resources + advRes_str;
			}else{
				resources = resources + "," + advRes_str;
			}
    	}
    	resources = resources + "]";

    	var adv = Ext.create("Eway.model.bsAdvert.BsAdvert",{
    		advertType : "WAIT_INSERT_CARD",
    		groupId : groupId,
    		advertName : advertName,
    		resources : resources
    	});
		var btn = win.down('button[action=confirm]');
    	adv.save({
			 success: function(ed) {
				var view = me.getEwayView();
				var store = view.down('bs_advert_grid').getStore();
				store.setUrlParamsByObject(null);
				store.loadPage(1);
				Eway.alert(EwayLocale.addSuccess);
				win.close();
			 },
			 failure: function(record,operation){
				Eway.alert(operation.error);
			 },
			 button:btn,
			 scope : this
		});
	},
	//资源配置信息更改
	onResourceConfigChanged:function(field,newValue,oldValue){
		var win = this.getAddWaitWin();
		var fileNameField = win.down('advert_bs_resourceConfigForm').getForm().findField('fileName');
		if(fileNameField.getValue() != ''){
			var activeTab = win.down('advert_bs_waitTab').getActiveTab();
			var advertImgStore = activeTab.down('bsadvertimgview').getStore();
			var selectedRecord = advertImgStore.findRecord("fileName",fileNameField.getValue());
			if(selectedRecord != null){
				selectedRecord.set(field.name,newValue);
			}
		}
	},
	//点击图片资源是对应的Form进行处理
	onAdvertImgItemClick:function(me,record,itemHtml,index,e){
		var win = this.getAddWaitWin();
		var advertView = win.down('bsadvertimgview');
		var lastSeletedItem = advertView.selectedItem;
		
		var currentEle = Ext.get(itemHtml);
		currentEle.addCls('advert-item-selected');
		advertView.selectedItem = currentEle;
		var resourceConfigForm = win.down('advert_bs_resourceConfigForm').getForm();
		resourceConfigForm.loadRecord(record);
	    return false;
	},
	//添加图片资源
	onFileChangedScreen:function(file,value){
		if(value==""){
			return;
		}
		var form = file.up('form').getForm();
		var title = file.up('form').up('panel').title;
		var win = this.getAddWaitWin();
		var tab = win.down('advert_bs_waitTab').getActiveTab();
		var count = tab.down('bsadvertimgview').getStore().getCount();
		if(count == 10){
			 Eway.alert(EwayLocale.advert.limitNumberTenForEveryResolution);
			return;
		}else{
			if(!Ext.isEmpty(value)){
				if(!form.isValid()){
					var tip = form.findField("tip");
					tip.setValue(EwayLocale.advert.fileFormatTipsInfo);
					setTimeout(function(){
								tip.setValue("");
							},3000);
					return;
				}
				form.submit({
					 	url: 'api/bsadvert/advert/uploadRes/screen',
					 	params: {
					 		screen: title
					 	},
					 	waitMsg: EwayLocale.advert.uploading,
					    success: function(form, action) {
					    	var record = Ext.create("Eway.model.bsAdvert.BsAdvertResource",{
					    		fileName:action.result.fileName,
					    		displayName:action.result.displayName,
					    		path:action.result.path,
					    		originalFileName:action.result.originalFileName,
					    		playTime: action.result.playTime,
					    		beginDate: action.result.beginDate,
					    		endDate:action.result.endDate,
					    		screen: action.result.screen,
					    		beginHour: action.result.beginHour,
					    		beginMinute: action.result.beginMinute,
					    		beginSecond: action.result.beginSecond,
					    		endHour: action.result.endHour,
					    		endMinute: action.result.endMinute,
					    		endSecond: action.result.endSecond
					    	});
					    	tab.down('bsadvertimgview').updateStoreData(record);
					    	file.lastValue="";
					    },
					    failure: function(form, action) {
				       	   switch (action.failureType) {
					            case Ext.form.action.Action.CONNECT_FAILURE:
					                Eway.alert(EwayLocale.msg.saveFileCommunicationFail);
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               Eway.alert(action.result.errors);
					       }
					    },
					    scope: this
				});
			}
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
					url :"api/bsadvert/advert/preview2",
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
