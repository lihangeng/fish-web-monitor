Ext.define('Eway.controller.bsAdvert.BsAdvert', {
	extend : 'Ext.app.Controller',

	stores : [  'bsAdvert.BsAdvert','bsAdvert.BsAdvertResource','bsAdvert.BsAdvertGroupList',
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
			}
		});
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
	onAddWaitConfirm:function(){
		
	},
	onResourceConfigChanged:function(){
		
	},
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
	
	}
});
