Ext.define('Eway.controller.advert.Advert', {
	extend : 'Ext.app.Controller',

	stores : [  'advert.Advert','advert.AdvertResource','advert.AdvertType','machine.DeviceAtmType',
				'advert.AdvertDownMethod','advert.AdvertDownMethodSearch',
				'advert.AdvertValidity','Hour','Minute',
				'version.JobType','version.JobStatus','version.JobPriority'],
	models : [ 'advert.Advert','advert.AdvertResource' ],
	views : [ 'advert.View','advert.AddTrans','advert.AddText','advert.AddWait'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		selector : '#advert', //这里的比较特殊，是对@@处的选择, 在其他地方没有任何定义
		autoCreate : true,//必须
		xtype : 'advert_view',//必须
		id : 'advert'//@@
	}, {
		ref : 'grid',
		selector : 'advert_grid'//ComponentQuery的query规则
	},{
		ref: 'advertResourceGrid',
		selector: 'advert_advertResourceGrid'
	},{
		ref : 'addTransWin',
		selector: 'advert_addTrans'
	},{
		ref: 'addTextWin',
		selector: 'advert_addText'
	},{
		ref: 'addWaitWin',
		selector: 'advert_addWait'
	},{
		ref: 'addAnnoucementWin',
		selector: 'advert_addAnnoucement'
	},{
		ref: 'tabs',
		selector: 'advert_view tabpanel'
	}/*{
		ref:'updateWin',
		selector:'#versionDownloadUpdateWinId'
	}*/,{
		ref : 'filterForm',
		selector: 'advert_filterForm'
	},{
		ref :'downAdvert',
		selector: 'advert_downAdvert'
	}],

	init : function() {
		 this.control({
			'#advert button[action=query]' : {
				click : this.onQuery
			},
			'#advert menuitem[action=preview1024]' :{
				click : this.onPreview1024
			},
			'#advert menuitem[action=preview800]' :{
				click : this.onPreview800
			},
			'#advert menuitem[action=preview600]' :{
				click : this.onPreview600
			},
			'#advert button menuitem[action=text]' :{
				click : this.onAddText
			},
			'#advert button menuitem[action=trans]' :{
				click : this.onAddTrans
			},
			'#advert button menuitem[action=wait]' :{
				click : this.onAddWait
			},
			'#advert button menuitem[action=annoucement]' :{
				click : this.onAddAnnoucement
			},
			'#advert button[action=generateVersion]' :{
				click: this.onGenerateVersion
			},
			'#advert button[action=remove]' :{
				click : this.onRemove
			},
			'#advert button[action=detail]' :{
				click : this.onDetail
			},
			'#advert button[action=downAdvert]' :{
				click : this.onDownAdvert
			},
			'#advert advert_grid' :{
				itemclick : this.onGridItemClick
			},
			'#advert tabpanel tab' :{
				activate : this.onTabActivate
			}
		});
	},

	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('advert_filterForm').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = view.down('advert_filterForm').getForm().getValues();
		var store = view.down('advert_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
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
			if(record.get("advertType") == "TEXT" || record.get('advertType') == 'ANNOUNCEMENT'){
				Eway.alert(EwayLocale.msg.perviewFailForText);
			}else{
				var advertId = record.get('id');
				Ext.Ajax.request({
					url :"api/advert/preview2",
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
			}
		}else{
			Eway.alert(EwayLocale.msg.choseResToPerview);
		}
	},
	//作业详情
	onDetail : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();

		}else{
			Eway.alert(EwayLocale.msg.chooseAdvert);
		}
	},
	//删除广告
	onRemove : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get("versionStatus") == EwayLocale.version.View.downLoaded || record.get("versionStatus") == EwayLocale.version.View.waitting ){
				Eway.alert(EwayLocale.msg.downLoadedAdvertCantDelete);
			}else{
				Ext.MessageBox.confirm(EwayLocale.tip.remove.confirm.title,EwayLocale.tip.remove.confirm.info,
						function(button,text) {
							if(button=="yes"){
								record.erase({
									success: function(){
										grid.getStore().remove(record);
										Eway.alert(EwayLocale.updateSuccess);
										//刷新详细配置列表
										var resourceGrid = this.getAdvertResourceGrid();
										resourceGrid.getStore().load({params:{advertId:0}});
									},
									failure: function(record,operation){
										//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
										record.dropped = false;
										Eway.alert(operation.request.scope.reader.jsonData.errors);
									},
									scope:this
								});
							}
				}, this);
			}
		}
		else {
			Eway.alert(EwayLocale.msg.chooseAdvertToDelete);
		}
	},

	//生成版本文件
	onGenerateVersion: function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('versionStatus') == EwayLocale.version.taskStatus.downloaded){
				Eway.alert(EwayLocale.msg.generalVersionFailForDownloaded);
			}else{
				Ext.Ajax.request({
				    url: 'api/advert/' + record.get("id") + "/generateVersion",
				    success: function(response){
				        var text = response.responseText;
				        Eway.alert(EwayLocale.msg.generalVersionSuccess);
				     	grid.getStore().load();//刷新列表页面
				    }
				});
			}
		}
		else {
			Eway.alert(EwayLocale.msg.chooseAdvert);
		}
	},

	//广告有效期切换的时候，隐藏或显示开始和结束日期
	onValidityChange : function(field,newValue,oldValue){
		var dfs = field.up("form").query("fieldset datefield");
		Ext.each(dfs,function(df){
			if(newValue=="TEMP"){
				df.setVisible(true);
				df.allowBlank = false;
			}else{
				df.setVisible(false);
				df.allowBlank = true;
				df.setValue("");
			}
		});
	},

	//当grid条目被点击的时候
	onGridItemClick : function(grid,record,item, index, e, options){
		var fileName = record.get("versionFile");
		if (e && e.getTarget().innerHTML === fileName) { //当单击超链接的时候
			var typeName =  record.get("versionType");
			fileName = fileName.replace("&","%26");
			var url = 'api/version/version/download?typeName=' + typeName + '&fileName=' + fileName;
			var iframe = document.getElementById('downloadFileFromWeb');
			iframe.src = url;
		}else{//其他的时候，刷新tab显示内容
			var resourceGrid = this.getAdvertResourceGrid();
			var btnPreview = resourceGrid.down('button[code=advertPreview]');
			if(record.get('advertType') == 'TEXT'){
				btnPreview.disable();
			}else{
				btnPreview.enable();
			}
			resourceGrid.getStore().load({params:{advertId:record.get('id')}});
		}
	},

	//切换Tab页面的时候
	onTabActivate : function(tab){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var id = record.get("id");
			if(tab.isXType("tab")){
				if(tab.card.action == "advertConfig"){
					tab.card.refresh(id);
				}else{
					tab.card.loader.load({params:{id:record.get('id')}});
				}
			}
		}
	},
	//激活面板时候进行刷新Store操作
	refreshLinkedDeviceGridData:function(){
		var win = this.getDownAdvert();
		var linkedGrid = win.down('version_download_linkedDeviceGrid');
		linkedGrid.setStore(linkedGrid.getStore());
	},
	//下发广告
	onDownAdvert : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('versionId') != 0 && record.get('versionFile') != null){
				var win = Ext.create('Eway.view.advert.DownAdvert');
				win.show();
				win.down("button[action=confirm]").on("click",this.onDownConfirm,this);
				win.down("textfield[name=jobName]").setValue(record.get("versionDesc"));
				win.on("destroy",this.onCloseDownWin,this);
				win.down("form combobox[name=taskType]").on('change',this.onJobTypeChange,this);
				win.down("version_download_multiselectableDeviceGrid pagingtoolbar").on("beforechange",this.onSelectalbeDeviceFresh,this);
				var pagingtoolbar = win.down("pagingtoolbar");
				win.down("version_download_linkedDeviceGrid").on("activate",this.refreshLinkedDeviceGridData,this)
				//增加请求参数
				pagingtoolbar.store.proxy.extraParams = {versionId :record.get("versionId")};
				var grid = win.down("version_download_multiselectableDeviceGrid");
				grid.down("combobox[name=pageSize]").on("change",this.onPageSizeChange,this);
				grid.down('button[action=queryDownDevice]').on('click',this.onQueryDownDevice,this);
				win.down("textfield[name=ip]").on({keydown:this.queryOnKeyDownEnter, scope: this });
				win.down("textfield[name=terminalId]").on({keydown:this.queryOnKeyDownEnter, scope: this});
				win.down("common_orgComboOrgTree[name=orgName]").on({keydown:this.queryOnKeyDownEnter, scope: this});
				var form = win.down("form").getForm();
				form.findField("versionId").setValue(record.get("versionId"));
				form.findField("versionType").setValue(record.get("versionType"));
				form.findField("versionNo").setValue(record.get("versionNo"));
				form.findField("serverPath").setValue(record.get("versionFile"));
				win.down("radiogroup").on({change:this.setCheckBoxModel, scope: this});
			}else{
				Eway.alert(EwayLocale.msg.downloadFailForNoVersion);
			}
		}
		else {
			Eway.alert(EwayLocale.msg.chooseAdvert);
		}
	},
	queryOnKeyDownEnter:function( e, t, eOpts ){
		if(t.keyCode==13){
			var grid = this.getDownAdvert().down("version_download_multiselectableDeviceGrid");
			var form = grid.up('window').down('form').getForm();
			if(form.isValid()){
				this.setSearchFilter(grid,form)
				grid.getStore().loadPage(1);
			}
		}
	},
	setCheckBoxModel:function( _this, newValue, oldValue, eOpts ){
		var grid = this.getDownAdvert().down("version_download_multiselectableDeviceGrid");
		if(newValue.allDevice=="true"){
			grid.selModel.deselectAll();
			var linkedGrid = this.getDownAdvert().down("version_download_linkedDeviceGrid");
			linkedGrid.getStore().removeAll();
			this.getDownAdvert().down("hidden[name='deviceIds']").setValue("");
			linkedGrid.setTitle(EwayLocale.version.selectDeviceInfo0 + linkedGrid.getStore().getCount() + EwayLocale.version.selectDeviceInfo1);
			grid.selModel.setLocked(true);
		}
		else{
			grid.selModel.setLocked(false);
		}
	},
	//选择页面显示记录数
	onPageSizeChange:function(combo,newValue){
		var grid = combo.up("version_download_multiselectableDeviceGrid");
		grid.getStore().pageSize = newValue;
		var form = grid.up('window').down('form').getForm();
		this.setSearchFilter(grid,form);
		grid.getStore().loadPage(1);
	},

	//单击选择设备列表页面的查询按钮
	onQueryDownDevice : function(button){
		var grid = button.up("version_download_multiselectableDeviceGrid");
		if(grid.down("textfield[name='terminalId']").isValid()&&grid.down("textfield[name='ip']").isValid()){
			var form = grid.up('window').down('form').getForm();
			this.setSearchFilter(grid,form);
			grid.getStore().loadPage(1);
		}
	},

	//下发管理页面刷新可选择设备时
	onSelectalbeDeviceFresh : function(pagingtoolbar){
		var form = pagingtoolbar.up('window').down('form').getForm();
		var grid = pagingtoolbar.up('grid');
		this.setSearchFilter(grid,form);
	},

	//@private
	//获得下发设备列表的查询条件
	setSearchFilter  : function(grid,form){
		var extraParams = {versionId : form.findField("versionId").value};
		var fields = grid.query('field');
		Ext.each(fields,function(field){
			if(field.name !== 'orgName' && field.name !== 'inputItem' && field.name !== 'pageSize'){
				extraParams[field.name] = field.getValue();
			}
		});
		grid.getStore().proxy.extraParams = extraParams;
	},

	//关闭下发页面后刷新广告列表
	onCloseDownWin : function(){
		this.getGrid().getStore().load();
	},

	//下发保存
	onDownConfirm : function(){
		var win = this.getDownAdvert();
		var btn = win.down('button[action=confirm]');
		btn.disable();
		var addForm = win.down("form").getForm();
		var data = addForm.getValues();
		var record = Ext.create("Eway.model.version.VersionDownload",data);
		var linkGrid = win.down('version_download_multiselectableDeviceGrid');
		if(addForm.isValid()){
			var deviceIdsField = addForm.findField("deviceIds");
			var allDeviceField = addForm.findField("allDevice");
			if(Ext.isEmpty(deviceIdsField.value)&&!allDeviceField.value){
				Ext.MessageBox.alert(EwayLocale.confirm.title,EwayLocale.msg.chooseOneDevice);
				btn.enable();
			}else if(allDeviceField.value&&linkGrid.getStore().getCount()==0){
				Eway.alert(EwayLocale.msg.mustSelectDevice);
			}else{
				record.set("deviceIds",deviceIdsField.value);
				record.save({
					 success: function(ed) {
					 	deviceIdsField.setValue("");
					 	var linkedGrid = win.down('version_download_linkedDeviceGrid');
						linkedGrid.getStore().removeAll();//清空已选择的设备列表
						linkedGrid.setTitle(EwayLocale.version.selectDeviceInfo0+0+EwayLocale.version.selectDeviceInfo1);
					 	//保存成功后刷新下发设备列表
//					 	 Eway.alert('保存成功！');
//						//win.down("version_download_multiselectableDeviceGrid").getStore().load();
						win.close();
						Ext.MessageBox.confirm(EwayLocale.confirm.title,
								EwayLocale.confirm.withoutNumTaskConfirmInfo,this.goToVersionDownloadPage,this);
					 },
					 failure: function(ed){
						 btn.enable();
					 },
					 scope : this
				});
			}
		}
	},

	//跳转到分发监控页面
	goToVersionDownloadPage :function(button, text) {
		if (button == "yes") {
			this.parent.activeController('version.VersionDownload');
		}
	},

	//切换作业类型的时候，显示或隐藏作业执行时间
	onJobTypeChange :function(combo,newValue){
		var jobDate = combo.up('form').down('datefield');
		if(newValue == "SCHEDULER"){
			jobDate.enable();
			jobDate.allowBlank = false;
		}else{
			jobDate.disable();
			jobDate.value = null;
			jobDate.allowBlank = true;
		}
	},

	//打开文字滚动广告页面
	onAddText : function(){
		var win = Ext.create("Eway.view.advert.AddText");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddTextConfirm, this);
//		var b3 = win.query('button[action=addMore]')[0];
//		b3.on('click',this.onAddTextMore,this);
		var b4 = win.query('form combobox[name=advertValidity]')[0];
		b4.on('change',this.onValidityChange,this);
		win.show();
	},

	//打开交易广告创建页面
	onAddTrans : function(button){
		var win = Ext.create("Eway.view.advert.AddTrans");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddTransConfirm, this);
		var b3 = win.query('button[action=addMore]')[0];
		b3.on('click',this.onAddTransMore,this);
		var b4 = win.query('form combobox[name=advertValidity]')[0];
		b4.on('change',this.onValidityChange,this);
		var b5 = win.query('form filefield[name=file]')[0];
		b5.on('change',this.onFileChanged,this);
		win.show();
	},

	//打开等待插卡广告创建页面
	onAddWait : function(button){
		var win = Ext.create("Eway.view.advert.AddWait");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddWaitConfirm, this);
		var b5 = win.query('filefield[name=file]')[0];
		b5.on('change',this.onFileChangedScreen,this);
		var b51 = win.query('filefield[name=file]')[1];
		b51.on('change',this.onFileChangedScreen,this);
		var b52 = win.query('filefield[name=file]')[2];
		b52.on('change',this.onFileChangedScreen,this);
		var b6 = win.query('advertimgview[name=1024]')[0];
		b6.on('itemclick',this.onAdvertImgItemClick,this);
		var b61 = win.query('advertimgview[name=800]')[0];
		b61.on('itemclick',this.onAdvertImgItemClick,this);
		var b62 = win.query('advertimgview[name=600]')[0];
		b62.on('itemclick',this.onAdvertImgItemClick,this);
		var resConfigForm = win.down('advert_resourceConfigForm').getForm();
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

	onAdvertImgItemClick :  function(me,record,itemHtml,index,e){
		var win = this.getAddWaitWin();
		var advertView = win.down('advertimgview');
		var lastSeletedItem = advertView.selectedItem;
		if(lastSeletedItem != null){
			lastSeletedItem.removeCls('advert-item-selected');
		}
		var currentEle = Ext.get(itemHtml);
		currentEle.addCls('advert-item-selected');
		advertView.selectedItem = currentEle;
		var resourceConfigForm = win.down('advert_resourceConfigForm').getForm();
		resourceConfigForm.loadRecord(record);
	    return false;
	},

	//选择一个新文件的时候,立即上传到服务器
	onFileChangedScreen :function(file,value){
		if(value==""){
			return;
		}
		var form = file.up('form').getForm();
		var title = file.up('form').up('panel').title;
		var win = this.getAddWaitWin();
		var tab = win.down('advert_waitTab').getActiveTab();
		var count = tab.down('advertimgview').getStore().getCount();
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
					 	url: 'api/advert/uploadRes/screen',
					 	params: {
					 		screen: title
					 	},
					 	waitMsg: EwayLocale.advert.uploading,
					    success: function(form, action) {
					    	var record = Ext.create("Eway.model.advert.UploadResource",{
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
					    	tab.down('advertimgview').updateStoreData(record);
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

	//资源文件配置项修改时
	onResourceConfigChanged : function(field,newValue,oldValue){
		var win = this.getAddWaitWin();
		var fileNameField = win.down('advert_resourceConfigForm').getForm().findField('fileName');
		if(fileNameField.getValue() != ''){
			var activeTab = win.down('advert_waitTab').getActiveTab();
			var advertImgStore = activeTab.down('advertimgview').getStore();
			var selectedRecord = advertImgStore.findRecord("fileName",fileNameField.getValue());
			if(selectedRecord != null){
				selectedRecord.set(field.name,newValue);
			}
		}
	},

	//创建公告
	onAddAnnoucement : function(button){
		var win = Ext.create("Eway.view.advert.AddAnnoucement");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddAnnoucementConfirm, this);
		var b2 = win.query('form combobox[name=advertValidity]')[0];
		b2.on('change',this.onValidityChange,this);
		win.show();
	},

	//继续增加交易广告资源
	onAddTransMore: function(){
		var form = this.getAddTransWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.TransResourceFieldSet',{
			title:EwayLocale.advert.transAdvertResConfig
		});
		this.onAddMore(form,fs);
	},

	//继续增加等待插卡广告资源
	onAddWaitMore: function(){
		var form = this.getAddWaitWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.WaitResourceFieldSet',{
			title:EwayLocale.advert.idleAdvertResConfig
		});
		this.onAddMore(form,fs);
	},
	//继续增加文字滚动广告资源
	onAddTextMore: function(){
		var form = this.getAddTextWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.TextResourceFieldSet',{
			title:EwayLocale.advert.textAdvertResConfig
		});
		this.onAddMore(form,fs);
	},
	//
	onAddMore : function(form,fs){
		var dfs = fs.query("datefield");
		Ext.each(dfs,function(df){
			if(form.getForm().findField("advertValidity").getValue()=="TEMP"){
				df.setVisible(true);
				df.allowBlank = false;
			}
		});
		var file = fs.query("filefield")[0];
		file.on('change',this.onFileChanged,this);
		form.add(fs);
	},

	//选择一个新文件的时候,立即上传到服务器
	onFileChanged :function(file,value){
		var form = file.up('form').getForm();
		if(!Ext.isEmpty(value)){
			form.submit({
				 	url: 'api/advert/uploadRes',
				 	waitMsg: EwayLocale.advert.uploading,
				    success: function(form, action) {
				    	var oFileName = action.result.oFileName;
				    	form.findField("oFileName").setValue(EwayLocale.advert.choosedAdvertRes+" <b>"+ oFileName + "</b>");
				    	form.findField("content").setValue(oFileName);
				    	file.allowBlank = true;
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
	},

	//文字滚动页面保存
	onAddTextConfirm : function(){
		var win = this.getAddTextWin();
		var fss = win.query("field_textResourceFieldSet");
		this.onAddConfirm(win,fss,"TEXT");
	},

	//公告保存
	onAddAnnoucementConfirm : function(){
		var win = this.getAddAnnoucementWin();
		var fss = win.query("field_annoucementFieldSet");
		this.onAddConfirm(win,fss,"ANNOUNCEMENT");
	},

	//交易广告页面保存
	onAddTransConfirm : function(){
		var win = this.getAddTransWin();
		var fss = win.query("field_transResourceFieldSet");
		this.onAddConfirm(win,fss,"TRANS");
	},

	//等待插卡广告页面保存
	onAddWaitConfirm : function(){
		var win = this.getAddWaitWin();
		var btn = win.down('button[action=confirm]');
//		btn.disable();
		var addForm = win.down("form").getForm();
		var store = Ext.StoreManager.get("advert.Advert");
		if(addForm.isValid()){
			var tab = win.down('advert_waitTab');
			var s1024 = tab.down('advertimgview[name=1024]');
			var s1024Store = s1024.getStore();
			if(s1024Store.getCount() == 0){
				Ext.MessageBox.alert(EwayLocale.confirm.title,EwayLocale.advert.mustContainerOnePicAt1024);
				btn.enable();
				return;
			}

			var s800 = tab.down('advertimgview[name=800]');
			var s800Store = s800.getStore();
			/*if(s800Store.getCount() == 0){
				Ext.MessageBox.alert(EwayLocale.confirm.title,"800分辨率至少包含一个图片!");
				return;
			}*/

			var s600 = tab.down('advertimgview[name=600]');
			var s600Store = s600.getStore();
			/*if(s600Store.getCount() == 0){
				Ext.MessageBox.alert(EwayLocale.confirm.title,"600分辨率下至少包含一个图片!");
				return;
			}*/

			/*if((s1024Store.getCount() !== s800Store.getCount()) || (s800Store.getCount()!== s600Store.getCount())){
				Ext.MessageBox.alert(EwayLocale.confirm.title,"每种分辨率下的图片数量必须相等!");
				return;
			}*/

			var data = addForm.getValues();
			this.doSaveWait(win,data,s1024Store,s800Store,s600Store);
		}
	},

	generateAdvertResource : function(advRess,store){
		for(var i = 0; i < store.getCount(); i++){
			var record = store.getAt(i);
			advRess.push(record.convertToAdvertResource());
		}
	},

	doSaveWait : function(win,data,s1024Strore,s800Strore,s600Strore){
		var me =this;
		var advRess = [];
    	this.generateAdvertResource(advRess,s1024Strore);
    	this.generateAdvertResource(advRess,s800Strore);
    	this.generateAdvertResource(advRess,s600Strore);
    	var resources = '[';
    	for(var i in advRess){
    		var res = advRess[i];
			var advRes_str = "{'playTime':" + res.data.playTime + ",'beginTime':'" + res.data.beginTime
			+ "','endTime':'"+res.data.endTime
			+"','beginDate':'"+res.data.beginDate
			+"','endDate':'"+res.data.endDate
			+"','screen':'"+res.data.screen
			+ "','content':'" + res.data.content +"'}";
			if(resources == '['){
				resources = resources + advRes_str;
			}else{
				resources = resources + "," + advRes_str;
			}
    	}
    	resources = resources + "]";

    	var adv = Ext.create("Eway.model.advert.Advert",{
    		advertType : "WAIT_INSERT_CARD",
    		advertDownMethod : data.advertDownMethod,
    		advertValidity : data.advertValidity,
    		resources : resources
    	});

    	adv.save({
			 success: function(ed) {
				var view = me.getEwayView();
				var store = view.down('advert_grid').getStore();
				store.setUrlParamsByObject(null);
				store.loadPage(1);
			 	Ext.MessageBox.alert(EwayLocale.confirm.title,EwayLocale.msg.createSuccess);
				win.close();
			 },
			 failure: function(record,operation){
//				 Eway.alert(operation.request.scope.reader.jsonData.errors);
				 Eway.alert(operation.request._operation.error.statusText);
			 },
			 scope : this
		});
	},

	//保存广告信息
	onAddConfirm : function(win,fss,advertType){
		var addForm = win.down("form").getForm();
		var store = Ext.StoreManager.get("advert.Advert");
		if(addForm.isValid()){
			var data = addForm.getValues();
			if(Ext.isEmpty(fss)){//如果没有一个广告资源
				Ext.MessageBox.alert(EwayLocale.confirm.title,EwayLocale.msg.mustHaveOneResource);
			}else {
				this.doSave(win,fss,data,store,advertType);
				Ext.getCmp('savaAdvert').setDisabled(true)
			}
		}
	},

	//存在没有上传的广告资源
	hasEmptyContent : function(fss){
		Ext.each(fss,function(fs){
				var c = fs.query("hidden")[0];
				if(c.value == '0'){
					return true;
				}
		});
		return false;
	},

	//保存
	doSave : function(win,fss,data,store,advertType){
		var me = this;
		var advRess = [];
    	var resources = "";
    	for(var i in fss){
    		var playTime= Ext.isArray(data.playTime) ? data.playTime[i] : data.playTime;
    		var beginHour = data.hour[2*i];
    		var beginMin = data.minute[2*i];
    		var beginSec = data.second[2*i];
    		var beginTime = beginHour + ":" + beginMin + ":" + beginSec;

    		var endHour = data.hour[2*i+1];
    		var endMin = data.minute[2*i+1];
    		var endSec = data.second[2*i+1];
    		var endTime = endHour + ":" + endMin + ":" + endSec;
    		var contents = data.content;
    		var tempContent = Ext.isArray(contents) ? contents[i] : contents;
    		var advRes = Ext.create("Eway.model.advert.AdvertResource",{
    			playTime: playTime,
    			beginTime : beginTime,
    			endTime : endTime,
    			content:  tempContent
    		});
    		advRess.push(advRes);

    		var advRes_str = "{'playTime':" + playTime + ",'beginTime':'" + beginTime + "','endTime':'"+endTime
    						+ "','content':'" + tempContent +"'";
    		if(data.advertValidity == "TEMP"){
    			var beginDate = Ext.isArray(data.beginDate) ? data.beginDate[i] : data.beginDate;
    			var endDate = Ext.isArray(data.endDate) ? data.endDate[i] : data.endDate;
    			advRes_str = advRes_str + ",'beginDate':'" + beginDate + "','endDate':'" + endDate + "'";
    		}

    		advRes_str = advRes_str + "}";
    		resources = resources + "," + advRes_str;
    	}
    	if(resources != ""){
    		resources = resources.substring(1);
    		resources = "[" + resources + "]";
    	}
    	var adv = Ext.create("Eway.model.advert.Advert",{
    		advertType : advertType,
    		advertDownMethod : data.advertDownMethod,
    		advertValidity : data.advertValidity,
    		resources : resources
    	});

    	adv.save({
			 success: function(ed) {
				 me.onQuery();
			 	Ext.MessageBox.alert(EwayLocale.confirm.title,EwayLocale.msg.createSuccess);
				win.close();
			 },
			 failure: function(record,operation){
				 Eway.alert(operation.request.scope.reader.jsonData.errors);
			 },
			 scope : this
		});
	}
});
