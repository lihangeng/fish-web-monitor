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
		'Eway.lib.FunctionUtils',
    	'Ext.ux.BoxReorderer',
    	'Ext.ux.DataView.Animated',
    	'Eway.view.common.OrgComboOrgTree'
	],

	screen:"",

	initComponent : function(){
		var me = this;
		var store = Ext.create('Eway.store.monitor.device.DataView',{});
		var monitorTpl = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="thumb-wrap" >',
						'<div class="thumb">',
							'<ul>',
								'<li><img class="left" src="{[this.getRunPath(values.run)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.runStatus,\'{runStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getModulePath(values.mod)]}" ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.modStatus,\'{modStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getBoxPath(values.box)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.boxStatus,\'{boxStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getNetPath(values.net)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.netStatus,\'{netStatus}\');"/></li>',
							'</ul>',
						'</div>',
					'<span class="x-editable">{code}({org})</span>',
				'</div>',
			'</tpl>',
			'<div class="x-clear"></div>',{
				getRunPath : function(status){

					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
					if(status == 'Healthy'){//正常服务
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
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
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
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
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
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
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
			}
		);

		var gridStore = Ext.create('Eway.store.monitor.device.DataView',{});
		var dataViewStore = Ext.create('Eway.store.monitor.device.DataView',{});
		Ext.apply(this,{
			items : [{
				region : 'center',
				id : 'images-view',
				layout : 'border',
				border : false,
				items : [{
					region : 'center',
					xtype : 'panel',
					layout : 'card',
					activeItem : 1,
					itemId : 'card_itemId',
					tbar : [
						{
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'orgId'
						}, {
							xtype : 'common_orgComboOrgTree',
							fieldLabel : Eway.locale.commen.orgNameBelongs,
							emptyText : Eway.locale.combox.select,
							name : 'orgName',
							hiddenValue : 'orgId',
							editable : false,
							labelWidth : 60,
							filters : '{"type" : "0"}',
							rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false,
							onTreeItemClick : function(view,record){
								this.setValue(record.get('text'));
								this.up('panel').down("hiddenfield[name=orgId]").setValue(record.get('id'));
								this.collapse();
							},
							clearValue : function(){
								this.setValue('');
								this.up('panel').down("hiddenfield[name=orgId]").setValue('');
							}
						},
						Eway.locale.commen.terminalId+':',
						{xtype : 'textfield',width:100,action:'deviceNumber'},
						{xtype : 'button',text : Eway.locale.button.search,action : 'query',glyph : 0xf002},
						'-',
						{xtype : 'button',text : Eway.locale.button.pause,action : 'monitorOK',glyph : 0xf04c,},
						'-',
						{text : Eway.locale.monitor.devMonitor.monitorState,action : 'monitorState'},
						{text : Eway.locale.monitor.devMonitor.showWay, menu: [
						                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.matrixPattern, action : 'matrixPattern'},
						                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.maxIconPattern, action : 'maxIconPattern'},
						                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.listPattern, action : 'listPattern'},
						                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.boxPattern, action : 'boxPattern'}
						                       ]},
						{text : Eway.locale.commen.filter,action : 'filter'},
						Eway.locale.monitor.devMonitor.numberfield+':',
						{xtype : 'numberfield',hideTrigger:true,width:50,value:120,action:'number',
													maxValue:1000,minValue:1},
					'-',
					{xtype : 'button',text : Eway.locale.commen.previous,action : 'previous',iconCls : 'previousBtn'},
					{xtype : 'button',text : Eway.locale.commen.next,action : 'next',iconCls : 'nextBtn'}
					],
					items : [{
						itemId : 'list',
						xtype : 'gridpanel',
						name : 'list',
						store : gridStore,
						viewConfig : {
							forceFit:true,
							stripeRows: true
						},
						columns : [
							{xtype: 'rownumberer',resizable:true},
							{header : Eway.locale.person.bankOrg.name, dataIndex : 'org', flex : 1},
							{header : Eway.locale.commen.terminalId,dataIndex : 'code',width:100,tdCls:'pointerLink'},
							{header : Eway.locale.commen.ip,dataIndex : 'ip',width:100},
							{header : Eway.locale.monitor.devMonitor.runStatus,dataIndex : 'runStatus',
								renderer:function(value,meta,record){
									var runFatals= ['SubHealth','Maintain','Halt','ReBoot','StopAtmp','StopManmade','StopMod','StopUnCashIn','StopUnKnown'];
									var run = record.get('run');
									if(Ext.Array.contains(runFatals,run)){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									}else{
										return value;
									}
								},width:100},
							{header : Eway.locale.monitor.devMonitor.modStatus,dataIndex : 'modStatus',
								renderer:function(value,meta,record){
									var mod = record.get('mod');
									if(mod == "Warning"){
										return "<span class='warningHighLight'>"+ value + "</span>";
									}else if(mod == "Fatal"){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									}else{
										return value;
									}
								},width:100},
							{header : Eway.locale.monitor.devMonitor.boxStatus,dataIndex : 'boxStatus',
								renderer:function(value,meta,record){
									var boxFatals= ['Full','Low','Empty','High','Fatal'];
									var box = record.get('box');
									if(Ext.Array.contains(boxFatals,box)){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									}else{
										return value;
									}
								},width:100},
							{header : Eway.locale.monitor.devMonitor.netStatus,dataIndex : 'netStatus',
							renderer:function(value,meta,record){
									var net = record.get('net');
									if(net == "Warning"){
										return "<span class='warningHighLight'>"+ value + "</span>";
									}else if(net == "Fatal"){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									}else{
										return value;
									}
								},width:100},
							{header : Eway.locale.monitor.devMonitor.retainCardCount , dataIndex : 'retainCardCount',width:100},
							{header : Eway.locale.monitor.devMonitor.boxInitCount , dataIndex : 'boxInitCount',width:100},
							{header : Eway.locale.monitor.devMonitor.boxCurrentCount , dataIndex : 'boxCurrentCount',width:100},
							{header : Eway.locale.commen.devTypeName,dataIndex : 'type',width:100},
							{header : Eway.locale.commen.orgNameBelongs,dataIndex : 'org', flex: 1},
							{header : Eway.locale.commen.installAddr,dataIndex : 'address', flex: 1},
							{header : Eway.locale.commen.seviceMode,dataIndex : 'seviceMode',width:100},
							{header : Eway.locale.commen.insideOutside,dataIndex : 'insideOutside',width:100}
						]
					},{
						itemId : 'dataview',
						xtype : 'dataview',
						name : 'matrix',
//						autoScroll : true,
						// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
						// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
						style : 'overflow:auto;',
						frame : true,
						store : dataViewStore,
						tpl : monitorTpl,
						multiSelect : false,
						trackOver : true,
						overItemCls : 'x-item-over',
						itemSelector : 'div.thumb-wrap',
						emptyText : Eway.locale.monitor.devMonitor.noData,
						loadData : function(store){
							var myStore = this.getStore();
							myStore.removeAll();
							var records = [];
							store.each(function(record){
								records[records.length] = record;
							});
							myStore.loadRecords(records);
						}
					}, {

						itemId : 'box',
						xtype : 'gridpanel',
						name : 'box',
						store : gridStore,
						viewConfig : {
							forceFit:true,
							stripeRows: true
						},
						columns : [
							{xtype: 'rownumberer'},
							{header : Eway.locale.person.bankOrg.name, dataIndex : 'org', flex : 1},
							{header : Eway.locale.commen.terminalId, dataIndex : 'code', flex : 1, tdCls:'pointerLink'},
							{header : Eway.locale.monitor.devMonitor.boxStatus,dataIndex : 'boxStatus',
								renderer:function(value,meta,record){
									var boxFatals= ['Full','Empty','High','Fatal'];
									var box = record.get('box');
									if(Ext.Array.contains(boxFatals,box)){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									}else if (box == 'Low'){
										return "<span class='warningHighLight'>"+ value + "</span>";
									} else {
										return value;
									}
								}, flex : 1,tdCls:'pointerLink'},
							{header : Eway.locale.monitor.devMonitor.boxInitCount , dataIndex : 'boxInitCount',flex : 1},
							{header : Eway.locale.monitor.devMonitor.boxCurrentCount , dataIndex : 'boxCurrentCount',flex : 1},
							{header : Eway.locale.monitor.devMonitor.cashboxLimit,dataIndex : 'cashboxLimit',flex : 1},
							{header : Eway.locale.monitor.devMonitor.retainCardCount,dataIndex : 'retainCardCount',
								renderer:function(value,meta,record){
									if(value >= 20){
										return "<span class='fatalHighLight'>"+ value + "</span>";
									} else {
										return value;
									}
								}, flex : 1}
						]
					}]
				} ],
				listeners : {
					scope : this
				}
			}],
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
		//清空查询条件
		this.resetFormFilter(cardp);

		var p = cardp.getLayout().getActiveItem();
		if (p.getItemId() == 'mapview') {
			var confirm = this.down('button[action="changeNumber"]');
			confirm.fireEvent('click', confirm);
		}
		var store = p.getStore();
		store.on('load',Ext.Function.pass(this.publish,[''],this),this,{
			single : true
		});
		this.doCometd(store);
	},

	//激活页面的时候，清空查询条件
	resetFormFilter : function(panel){
		var numberField = panel.down('textfield[action="number"]');
		numberField.setValue(120);
		var deviceCodeField = panel.down('textfield[action="deviceNumber"]');
		deviceCodeField.setValue(null);
		var common_orgComboOrgTree = panel.down("common_orgComboOrgTree");
		common_orgComboOrgTree.clearValue();
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
	_metaHandshake : function(message,store){
		if(message.successful){
			var isDis = Ext.Cometd.isDisconnected();
			if(!isDis){
				this._connectionInitalized();
				var numberField = this.down('textfield[action="number"]');
				var limitP = this.getPageSize();
				store.load({
					params : {
						startP : 0 ,
						limitP : limitP,
						userId : test_userId,
						filterUserId : ewayUser.getId(),
						orgId: ewayUser.getOrgId()
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
		else if(itemId == 'dataview'){
			this.doDataViewPanel(currentPanel,object);
		}
		else if(itemId == 'mapview') {
			this.doMapViewPanel(currentPanel, object);
		}
	},

	doMapViewPanel : function(view, object) {
		var me = this;
		var store = view.getStore();
		var action = object.method;
		if(action == 'ADD'){
			var record = Ext.ModelManager.create(object,'Eway.model.monitor.device.DeviceMonitorMap');
			store.add(record);
		}
		else if(action == 'UPDATE'){
			var code = object.code,
				index = store.find('code', code),
				model = store.getAt(index);

			for(var i in object){
				if(object[i]==null || i == 'id'){
					continue;
				}else{
					model.set(i,object[i]);
				}
			}

			model.commit();

			var div = $('#' + code);

			div.find('li img').each(function(index) {

				var _img = $(this);

				if (index == 0) {
					_img.attr('src', me.getRunPath(object.run));
					_img.bind('mouseover', function() {
						Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.runStatus, object.runStatus);
					});
				} else if (index == 1) {
					_img.attr('src', me.getModulePath(object.mod));
					_img.bind('mouseover', function() {
						Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.modStatus, object.modStatus);
					});
				} else if (index == 2) {
					_img.attr('src', me.getBoxPath(object.box));
					_img.bind('mouseover', function() {
						Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.boxStatus, object.boxStatus);
					});
				} else if (index == 3) {
					_img.attr('src', me.getNetPath(object.net));
					_img.bind('mouseover', function() {
						Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.netStatus, object.netStatus);
					});
				}
			});
		}
		else if(action == 'DELETE'){
			var code = object.code,
			index = store.find('code',code),
			model = store.getAt(index);
			store.remove(model);

			view.deleteMarker(code);
		}
	},

	doDataViewPanel : function(view,object){
		var store = view.getStore();
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
		var store = panel.getStore();
		var me = this;
		store.on('load',Ext.Function.pass(this.publish,[params],me),this,{
			single : true
		});
		var tempParams = this.getParams(params);
		if (!tempParams) {
			return;
		}
		store.load({
			method : 'POST',
			params : tempParams
		});
	},

	getParams : function(params) {
		var tempParams = {};
		for(var i in params){
			if((typeof params[i]) == 'object' ){
				for(var j in params[i]){
					tempParams[j] = params[i][j];
				}
			}
			else {
				tempParams[i] = params[i];
			}
		}
		tempParams.userId = test_userId;
		tempParams.filterUserId = ewayUser.getId();

		if(!tempParams.startP){
			tempParams.startP = 0 ;
			tempParams.limitP = this.getPageSize();
		}
		if(!tempParams.orgId){
			tempParams.orgId = ewayUser.getOrgId();
		}
		return tempParams;
	},

	getPageSize : function(){
		var numberField = this.down('textfield[action="number"]');
		var limitP =numberField.getValue();

		if(!Ext.isNumeric(limitP) || limitP<=0 || limitP>1000){
			limitP = 120;
			numberField.setValue(limitP);
		}
		return limitP;
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

	//订阅
	publish : function(params,store){
		var me = this;
		if(((typeof params) == 'string') && (params == '')){
			params = undefined;
		}
		var number = this.getPageSize();
		var devices = "";
		/**上一页，下一页**/
		var totalCount = store.totalCount;
		this.setTotal(totalCount);
		if(params && params.page){
			this.setCurrentPage(params.page);
		}else {
			this.setCurrentPage(1);
			this.down('button[action="previous"]').disable();
			if(this.getTotal() <= number){
				this.down('button[action="next"]').disable();
			}else {
				this.down('button[action="next"]').enable();
			}
		}

		store.each(function(record){
			devices = devices+"/"+record.get('code');
		});

		var params = params || {};
		params.userId = test_userId;
		params.devices = devices.slice(1);
		if(!params.number){
			params.number = number;
		}
		if(!params.orgId){
			params.orgId = ewayUser.getOrgId();
		}
		if(!params.filterUserId){
			params.filterUserId = ewayUser.getId();
		}
		Ext.Cometd.publish('/service/status/join',params);
	},

	getRunPath : function(status){
		var path = "resources/images/monitor/";
		if(status == 'Healthy'){//正常服务
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
