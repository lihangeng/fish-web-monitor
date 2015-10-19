Ext.define('Eway.controller.advert.Advert', {
	extend : 'Ext.app.Controller',

	requires : ['Ext.form.field.File'],
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
			'#advert button[action=preview]' :{
				click : this.onPreview
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
		var values = view.down('advert_filterForm').getForm().getValues();
		var store = view.down('advert_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},

	//广告预览
	onPreview : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get("advertType") == 'TEXT' || record.get('advertType') == 'ANNOUNCEMENT'){
				Eway.alert(Eway.locale.msg.perviewFailForText);//"预览失败:不支持文字滚动广告和公告的预览.");
			}else{
				var advertId = record.get('id');
				Ext.Ajax.request({
					url :"api/advert/preview2",
					params: {
				        id: advertId
				    },
					success: function(response){
						 var images = Ext.decode(response.responseText);
						 if(Ext.isEmpty(images)){
						 	Eway.alert(Eway.locale.msg.perviewFailNoResource);//"预览失败:此广告没有配置广告资源.");
						 }
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
							title:Eway.locale.advert.advertPreviewTitle0+len+Eway.locale.advert.advertPreviewTitle1,
//							title:'广告预览(共有 '+ len +' 个资源,当前播放第 ',
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
											divVedio.innerHTML = '<font color="red">'+Eway.locale.advert.perviewAdertWithIEBrowse+'</font>';//非IE浏览器不支持视频广告的预览.
										}
									}else{
										divVedio.style.display = "none";
										div.style.display = "block";
										img.src = currentPic.picName;
									}

								   	win.setTitle(title + (i+1) +Eway.locale.advert.advertPreviewTitle1);//" 个)");
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
				});
			}
		}else{
			Eway.alert(Eway.locale.msg.choseResToPerview);//"请选择您要预览的广告.");
		}
	},
	//作业详情
	onDetail : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();

		}else{
			Eway.alert(Eway.locale.msg.chooseAdvert);//"请选择一条广告.");
		}
	},
	//删除广告
	onRemove : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			Eway.locale.version.View.downLoaded
			if(record.get("versionStatus") == Eway.locale.version.View.downLoaded || record.get("versionStatus") == Eway.locale.version.View.waitting ){
//				if(record.get("versionStatus") == '已下发' || record.get("versionStatus") == '等待下发' ){
				Eway.alert(Eway.locale.msg.downLoadedAdvertCantDelete);//'删除失败:不能删除"已下发"和"等待下发"状态的广告.');
			}else{
				Ext.MessageBox.confirm(Eway.locale.confirm.titleSure,Eway.locale.confirm.todoDelete,//"请确认","是否删除该记录?",
						function(button,text) {
							if(button=="yes"){
								record.erase({
									success: function(){
										grid.getStore().remove(record);
										Eway.alert(Eway.deleteSuccess);
										//刷新详细配置列表
										var tabCard = this.getTabs().getActiveTab();
										tabCard.refresh(0);
									},
									failure: function(record,operation){
										Eway.alert(operation.getError());
									},
									scope:this
								});
							}
				}, this);
			}
		}
		else {
			Eway.alert(Eway.locale.msg.chooseAdvertToDelete);//"请选择您要删除的广告.");
		}
	},

	//生成版本文件
	onGenerateVersion: function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('versionStatus') == Eway.locale.version.View.downLoaded){//'已下发'){
				Eway.alert(Eway.locale.msg.generalVersionFailForDownloaded);//'生成版本文件失败:"已下发"状态的广告不能再生成版本信息.');
			}else{
				Ext.Ajax.request({
				    url: 'api/advert/' + record.get("id") + "/generateVersion",
				    success: function(response){
				        var text = response.responseText;
				        Eway.alert(Eway.locale.msg.generalVersionSuccess);//"生成版本文件成功.");
				     	grid.getStore().load();//刷新列表页面
				    }
				});
			}
		}
		else {
			Eway.alert(Eway.locale.msg.chooseAdvert);//"请选择一条广告.");
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
			var tabs = this.getTabs();
			var tabCard = tabs.getActiveTab();
			if(tabCard.action == "advertConfig"){
				tabCard.refresh(record.get("id"));
			}else if(tabCard.action == "advertVersion"){
				tabCard.loader.load({params:{id:record.get('id')}});
			}
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

	//下发广告
	onDownAdvert : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('versionId') != 0 && record.get('versionFile') != null){
				var win = Ext.create('Eway.view.advert.DownAdvert');
				win.down("button[action=confirm]").on("click",this.onDownConfirm,this);
				win.on("destroy",this.onCloseDownWin,this);
				win.down("form combobox[name=taskType]").on('change',this.onJobTypeChange,this);
				win.down("form textfield[name=jobName]").setValue(record.get("versionFile"));
				win.down("version_download_multiselectableDeviceGrid pagingtoolbar").on("beforechange",this.onSelectalbeDeviceFresh,this);
				var pagingtoolbar = win.down("pagingtoolbar");
				//增加请求参数
				pagingtoolbar.store.proxy.extraParams = {versionId :record.get("versionId")};
				var grid = win.down("version_download_multiselectableDeviceGrid");
				grid.down("combobox[name=pageSize]").on("change",this.onPageSizeChange,this);
				grid.down('button[action=queryDownDevice]').on('click',this.onQueryDownDevice,this);
				var form = win.down("form").getForm();
				form.findField("versionId").setValue(record.get("versionId"));
				form.findField("versionType").setValue(record.get("versionType"));
				form.findField("versionNo").setValue(record.get("versionNo"));
				form.findField("serverPath").setValue(record.get("versionFile"));

				win.show();
			}else{
				Eway.alert(Eway.locale.msg.downloadFailForNoVersion);//"下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.");
			}
		}
		else {
			Eway.alert(Eway.locale.msg.chooseAdvertToDownload);//"请选择您要下发的广告.");
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
		var form = grid.up('window').down('form').getForm();
		this.setSearchFilter(grid,form);
		grid.getStore().loadPage(1);
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
		if(addForm.isValid()){
			var deviceIdsField = addForm.findField("deviceIds");
			if(Ext.isEmpty(deviceIdsField.value)){
				Eway.alert(Eway.locale.msg.chooseDevice);//"请选择设备.");
				btn.enable();
			}else{
				record.set("deviceIds",deviceIdsField.value);
				record.save({
					 success: function(ed) {
					 	deviceIdsField.setValue("");
					 	var linkedGrid = win.down('version_download_linkedDeviceGrid');
						linkedGrid.getStore().removeAll();//清空已选择的设备列表
						Eway.locale.version.selectDeviceInfo0
						linkedGrid.setTitle(Eway.locale.version.selectDeviceInfo0+0+Eway.locale.version.selectDeviceInfo1);
//						linkedGrid.setTitle("已选择的设备(<font color='red'>0</font>)台");
					 	//保存成功后刷新下发设备列表
					 	Eway.alert(Eway.locale.msg.saveSuccess);//'保存成功！');
						win.close();
						Ext.MessageBox.confirm(Eway.locale.confirm.title,//'提示',作业保存成功,是否跳转到"分发监控"页面?
								Eway.locale.confirm.withoutNumTaskConfirmInfo,this.goToVersionDownloadPage,this);
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
//		b1.on('doubleClick');
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
//		b1.on('doubleClick');
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
//		b1.on('doubleClick');
		var b3 = win.query('button[action=addMore]')[0];
		b3.on('click',this.onAddWaitMore,this);
		var b4 = win.query('form combobox[name=advertValidity]')[0];
		b4.on('change',this.onValidityChange,this);
		var b5 = win.query('form filefield[name=file]')[0];
		b5.on('change',this.onFileChanged,this);
		win.show();
	},
	//创建公告
	onAddAnnoucement : function(button){
		var win = Ext.create("Eway.view.advert.AddAnnoucement");
		var b1 = win.query('button[action=confirm]')[0];
		b1.on('click', this.onAddAnnoucementConfirm, this);
//		b1.on('doubleClick');
		var b2 = win.query('form combobox[name=advertValidity]')[0];
		b2.on('change',this.onValidityChange,this);
		win.show();
	},

	//继续增加交易广告资源
	onAddTransMore: function(){
		var form = this.getAddTransWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.TransResourceFieldSet',{
			title:Eway.locale.advert.transAdvertResConfig//"交易页面广告资源配置"
		});
		this.onAddMore(form,fs);
	},

	//继续增加等待插卡广告资源
	onAddWaitMore: function(){
		var form = this.getAddWaitWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.WaitResourceFieldSet',{
			title:Eway.locale.advert.idleAdvertResConfig//"等待插卡广告资源配置"
		});
		this.onAddMore(form,fs);
	},
	//继续增加文字滚动广告资源
	onAddTextMore: function(){
		var form = this.getAddTextWin().down('form');
		var fs = Ext.create('Eway.view.advert.field.TextResourceFieldSet',{
			title:Eway.locale.advert.textAdvertResConfig//"文字滚动广告资源配置"
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
				 	waitMsg: Eway.locale.advert.uploading,//'正在上传资源...',
				    success: function(form, action) {
				    	var oFileName = action.result.oFileName;
//				    	form.findField("oFileName").setValue("您已经选择了 <b>"+ oFileName + "</b>");
				    	form.findField("oFileName").setValue(Eway.locale.advert.choosedAdvertRes+" <b>"+ oFileName + "</b>");
				    	form.findField("content").setValue(oFileName);
				    	file.allowBlank = true;
				    },
				    failure: function(form, action) {
			       	   switch (action.failureType) {
				            case Ext.form.action.Action.CONNECT_FAILURE:
				                Eway.alert(Eway.locale.msg.saveFileCommunicationFail);//'保存失败:与服务器通讯失败.');
				                break;
				            case Ext.form.action.Action.SERVER_INVALID:
				            	if(action.result.msg==0){
				            		Eway.alert(Eway.locale.msg.saveFileSizeMaxFail);//'保存失败:超过最大单个文件大小限制（最大30M）');
				            	}else{
				            		Eway.alert(Eway.locale.msg.saveFail+action.result.msg);//保存失败
				            	}
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
		var fss = win.query("field_waitResourceFieldSet");
		this.onAddConfirm(win,fss,"WAIT_INSERT_CARD");
	},

	//保存广告信息
	onAddConfirm : function(win,fss,advertType){
		var addForm = win.down("form").getForm();
		var store = Ext.StoreManager.get("advert.Advert");
		if(addForm.isValid()){
			var data = addForm.getValues();
			if(Ext.isEmpty(fss)){//如果没有一个广告资源
				Eway.alert(Eway.locale.msg.mustHaveOneResource);//"至少包含一个广告资源!");
			}else {
				this.doSave(win,fss,data,store,advertType);
				win.down("toolbar [action ='confirm']").setDisabled(true)
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
			 	store.insert(0,ed);
			 	Eway.alert(Eway.locale.msg.createSuccess);//"创建成功.");
				win.close();
			 },
			 failure: function(record,operation){
				 Eway.alert(operation.getError());
			 },
			 scope : this
		});
	}
});
