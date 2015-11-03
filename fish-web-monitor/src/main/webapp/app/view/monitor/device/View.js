Ext.define('Eway.view.monitor.device.View',{
	extend : 'Eway.view.base.Panel',
	alias :'widget.monitor_device_view',
	title : Eway.locale.monitor.devMonitor.title,

	layout : 'border',
	config : {
		monitor : true,  // true:开始监听,false:暂停监听
		_deviceMartixSub : undefined,
		_deviceMartixHandsubSub : undefined,
		currentPage : 1,
		total : 0
	},

	requires : [
		'Eway.view.monitor.device.showType.BoxGrid',
		'Eway.view.monitor.device.showType.DataViewGrid',
		'Eway.view.monitor.device.showType.ListGrid',
		'Eway.view.monitor.device.showType.Tbar','Eway.view.monitor.charts.View'
	],

	initComponent : function(){
		var me = this;
		var dataViewStore = Ext.create('Eway.store.monitor.device.DataView',{});
		Ext.apply(this,{
			items : [{
				region : 'center',
				id : 'images-view',
				layout : 'border',
				border : false,
				items : [ {
					region : 'center',
					xtype : 'panel',
					layout : 'card',
					activeItem : 1,
					itemId : 'card_itemId',
					tbar : [
						'订阅条件:', {
							xtype : 'label'
						}
					],
					dockedItems: [ {
						
						// 工具栏
					    xtype: 'monitor_device_showtype_tbar',
					    dock: 'top'
					   
					} ],
					items : [ {
						
						// 列表方式
						itemId : 'list',
						name : 'list',
						xtype : 'monitor_device_showtype_listgrid'
					}, {
						
						// 矩形方式
						xtype : 'panel',
						itemId : 'martrixPanel',
						autoScroll : true,
						items : {
							name : 'matrix',
							itemId : 'dataview',
							xtype : 'monitor_device_showtype_dataviewgrid',
							store : 'dataViewStore'
						},
						bbar : Ext.create('Ext.PagingToolbar', {
							store : dataViewStore,
							displayInfo : true,
							displayMsg : Eway.locale.tip.displayMessage
						})
					}, {
						
						//　钞箱方式
						itemId : 'box',
						name : 'box',
						xtype : 'monitor_device_showtype_boxgrid'
					} ]
				} ]
			} ],
			listeners : {
				scope : this,
				deactivate : this.closeMatrixMonitor,
				activate : this.activatePanel
			}
		});
		this.callParent(arguments);
	},

	updateStoreSorters : function(direction,btn){
		var me = this;
		var card = this.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		var store = currentPanel.getStore();
		var dataIndex = btn.getDataIndex();
		store.sort(btn.getDataIndex(),direction);
	},

	updateStoreSortersSimple : function(btn){
		var me = this;
		var card = this.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		var store = currentPanel.getStore();
		var dataIndex = btn.dataIndex;
		var values = dataIndex.split('_');
		var key = "";
		if(values[0] == 'module') {key = 'mod'}
		else {key = values[0];}
		var data = store.data;
		data.sortBy(function(a,b){
			if(a.get(key).toUpperCase() == values[1].toUpperCase()
				|| b.get(key).toUpperCase() != values[1].toUpperCase()
			){
				return -1;
			}
			if(a.get(key).toUpperCase() != values[1].toUpperCase()
				|| b.get(key).toUpperCase() == values[1].toUpperCase()
			){
				return 1;
			}
			if(a.get(key).toUpperCase() == b.get(key).toUpperCase()
			){
				return 0;
			}
		});
		store.fireEvent('datachanged', store);
    	store.fireEvent('refresh', store);
	},

	//激活本页面
	activatePanel : function(){
		if(!this.config.monitor){
			return;
		}
		var cardp = this.down('panel[itemId="card_itemId"]');

		var p = cardp.getLayout().getActiveItem();
		var store;
		if(p.getItemId() == 'martrixPanel'){
//			cardp.tbar.setHtml("123");
			this.doCometd(store);
			return;
		}
		if (p.getItemId() == 'martrixPanel') {
			store = p.down('monitor_device_showtype_dataviewgrid').getStore();
			
		} else {
			store = p.getStore();
		}
		
		
		store.on('load',Ext.Function.pass(this.publish,[''],this),this,{
			single : true
		});
		this.doCometd(store);
	},
	
	//订阅
	publish : function(params, store){
		var me = this;
		var devices = "";
		store.each(function(record) {
			devices = devices+"/"+record.get('code');
		});
		var params = this.getParams();
		params.devices = devices.slice(1);
		Ext.Cometd.publish('/service/status/join', params);
	},

	//握手成功后加载数据，加载数据的时候订阅
	doCometd : function(store){
		var cometd = Ext.Cometd;
		var cometURL = location.protocol + "//" + location.host + Ext.cxtPath +"/cometd";
		cometd.configure(cometURL);
		if(this.config._deviceMartixHandsubSub){
			cometd.removeListener(this.config._deviceMartixHandsubSub);
		}
		this.config._deviceMartixHandsubSub = cometd.addListener('/meta/handshake',Ext.bind(this._metaHandshake,this,[store],true));
		cometd.handshake();
	},

	//握手
	_metaHandshake : function(message, store){
		if(message.successful){
			var isDis = Ext.Cometd.isDisconnected();
			if(!isDis){
				this._connectionInitalized();
				var filterNameField = this.down('combobox[action="filterName"]');
				var filterId = filterNameField.getValue();
				store.load({
					params : {
						userId: ewayUser.getId(),
						orgId: ewayUser.getOrgId(),
						filterId: filterId
					}
				});
			}
			else {
				Ext.defer(this.doCometd,1000,this,[store]);
			}
		}
	},

	_connectionInitalized : function(){
		if(this.config._deviceMartixSub){
			Ext.Cometd.removeListener(this.config._deviceMartixSub);
		}
		this.config._deviceMartixSub = Ext.Cometd.addListener('/service/status/join',Ext.bind(this._receive,this));
	},

	_receive : function(message){
		var object = Ext.JSON.decode(message.data);
		var card = this.down('#card_itemId');
		var layout = card.getLayout();
		var currentPanel = layout.getActiveItem();
		var itemId = currentPanel.itemId;
		if(itemId == 'list' || itemId == 'box'){
			this.doGridPanel(currentPanel,object);
		}
		else if (itemId == 'martrixPanel') {
			this.doDataViewPanel(currentPanel,object);
		}/*
		else if(itemId == 'mapview') {
			this.doMapViewPanel(currentPanel, object);
		}*/
	},

	doDataViewPanel : function(view,object){
		
//		var store = view.getStore();
		var store = view.down('monitor_device_showtype_dataviewgrid').getStore();
		
		var action = object.method;
		if(action == 'ADD'){
			var record = Ext.ModelManager.create(object,'Eway.model.monitor.device.DeviceMonitorList');
			store.add(record);
		}
		else if(action == 'UPDATE'){
			var code = object.code,
				index = store.find('code',code),
				model = store.getAt(index);
			for(var i in object){
				if(object[i]==null || i == 'id'){
					continue;
				}else{
					model.set(i,object[i]);
				}
			}
			model.commit();
		}
		else if(action == 'DELETE'){
				var code = object.code,
				index = store.find('code',code),
				model = store.getAt(index);
				store.remove(model);
		}
	},

	doGridPanel : function(gridpanel,object){
		var store = gridpanel.getStore();
		var action = object.method;
		if(action == 'ADD'){
			var record = Ext.ModelManager.create(object,'Eway.model.monitor.device.DataView');
			store.add(record);
		}
		else if(action == 'UPDATE'){

			var code = object.code,
				index = store.find('code',code),
				model = store.getAt(index);

			for(var i in object){
				if(object[i]==null || i == 'id'){
					continue;
				}else{
					model.set(i,object[i]);
				}
			}
			model.commit();
		}
		else if(action == 'DELETE'){
			var code = object.code,
			index = store.find('code',code),
			model = store.getAt(index);
			store.remove(model);
		}
	},

	changeFilterLoad : function(panel,params){
		var store;
		if (panel.getItemId() == 'martrixPanel') {
			store = panel.down('monitor_device_showtype_dataviewgrid').getStore();
		} else {
			store = panel.getStore();
		}
		var me = this;
		
		var tempParams = this.getParams(params);
		if (!tempParams) {
			return;
		}
		
		store.setUrlParamsByObject(tempParams);
		store.load({
			method : 'POST'
		});
		
		store.on('load',Ext.Function.pass(this.publish,[tempParams],me),this,{
			single : true
		});
	},

	getParams : function(params) {
		var tempParams = {};
		
		tempParams.userId = ewayUser.getId();
		tempParams.orgId = ewayUser.getOrgId();
		
		// 设备号
		var deviceCodeField = this.down('textfield[action="deviceNumber"]');
		var deviceCode = deviceCodeField.getValue();
		if(deviceCode) {
			tempParams.deviceCode = deviceCode;
		}
		
		// 监控条件
		var filterNameField = this.down('combobox[action="filterName"]');
		var filterId = filterNameField.getValue();
		if(filterId) {
			tempParams.filterId = filterId;
		}
		return tempParams;
	},

	loadPanelData : function(panel,store){
		var myStore = panel.getStore();
		myStore.removeAll();
		var records = [];
		store.each(function(record){
			records[records.length] = record;
		});
		myStore.loadRecords(records);
	},

	closeMatrixMonitor : function(){
		if(this.config._deviceMartixHandsubSub){
			Ext.Cometd.removeListener(this.config._deviceMartixHandsubSub);
			this.config._deviceMartixHandsubSub = null;
		}
		if(this.config._deviceMartixSub){
			Ext.Cometd.removeListener(this.config._deviceMartixSub);
			this.config._deviceMartixSub = null;
		}
		Ext.Cometd.disconnect();
	},

	getRunPath : function(status) {
		var path = "resources/images/monitor/";
		if(status == 'Healthy') {//正常服务
			path += 'run_HEALTHY.png';
		}
		else if(status == 'Initial'){//初始化
			path += 'run_Initial.png';
		}
		else if(status == 'SubHealth'){//半功能服务
			path += 'SubHealth.png';
		}
		else if(status == 'Customer'){//客户交易
			path += 'run_Customer.png';
		}
		else if(status == 'Maintain'){//维护
			path += 'run_Maintain.png';
		}
		else if(status == 'Vdm'){//厂商模式
			path += 'run_Vdm.png';
		}
		else if(status == 'Halt'){//关机
			path += 'run_Halt.png';
		}
		else if(status == 'ReBoot'){//重启
			path += 'run_ReBoot.png';
		}
		else if(status == 'StopAtmp'){//P端通讯故障
			path += 'run_StopAtmp.png';
		}
		else if(status == 'StopManmade'){//人工报停
			path += 'run_StopManmade.png';
		}
		else if(status == 'StopMod'){//暂停服务-模块故障
			path += 'run_StopMod.png';
		}
		else if(status == 'StopUnCashIn'){//暂停服务-未加钞
			path += 'run_StopUnCashIn.png';
		}
		else if(status == 'StopUnKnown'){//暂停服务-未知原因
			path += 'run_StopUnKnown.png';
		}
		else {
			path += 'run_UNKNOWN.png';
		}
		return path;
	},
	getModulePath : function(status){
		var path = "resources/images/monitor/";
		if(status == 'Healthy'){
			path += 'module_HEALTHY.png';
		}
		else if(status == 'Warning'){
			path += 'module_WARNING.png'
		}
		else if(status == 'Fatal'){
			path += 'module_FATAL.png';
		}
		else if(status == 'Nodevice'){
			path += 'module_Nodevice.png';
		}
		else {
			path += 'module_UNKNOWN.png';
		}
		return path;
	},
	getBoxPath : function(status){
		var path = "resources/images/monitor/";
		if(status == 'Healthy'){ //取款钞满正常
			path += 'box_HEALTHY.png';
		}else if(status == 'Full'){ 			//存款入钞满
			path += 'box_FULL.png';
		}
		else if (status == 'Low'){     //钞少
			path += 'box_LOW.png';
		}
		else if (status == 'Empty'){    //钞空
			path += 'box_EMPTY.png';
		}
		else if(status == 'High'){      //存款入钞将满
			path += 'box_High.png';
		}
		else if(status == 'Fatal'){     //钞箱故障
			path += 'box_Fatal.png';
		}
		else {
			path += 'box_UNKNOWN.png';  //钞箱未知
		}
		return path;
	},
	getNetPath : function(status){
		var path = "resources/images/monitor/";
		if(status == 'Healthy'){  //网络正常
			path += 'net_HEALTHY.png';
		}
		else if(status == 'Warning'){ //网络不稳定
			path += 'net_WARNING.png';
		}
		else if(status == 'Fatal'){ //网络故障
			path += 'net_FATAL.png';
		}
		else { //未知
			path += 'net_UNKNOWN.png';
		}
		return path;
	}
});
