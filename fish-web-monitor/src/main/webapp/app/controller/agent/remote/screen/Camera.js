Ext.define('Eway.controller.agent.remote.screen.Camera', {
	extend : 'Ext.app.Controller',
	views : [ 'Eway.view.agent.remote.screen.CameraView',
			'Eway.view.agent.remote.screen.CameraGrid' ],
	stores : [ 'agent.ScreenCamera' ],
	models : [ 'agent.ScreenCamera' ],
	refs : [ {
		ref : 'grid',
		selector : 'agent_remote_screen_cameraGrid'
	} ],
	terminalId : 0,
	ip : '127.0.0.1',
	code : '',
	init : function() {
		this.control({
			'agent_remote_screen_cameraGrid' : {
				'itemclick' : this.onCellclick
			},
			'agent_remote_screen_cameraGrid button[action=startcustom]' : {
				'click' : this.onStartCustom
			},
			'agent_remote_screen_cameraGrid button[action=stopcustom]' : {
				'click' : this.onStopCustom
			},
			'agent_remote_screen_cameraGrid button[action=startadmin]' : {
				'click' : this.onStartAdmin
			},
			'agent_remote_screen_cameraGrid button[action=stopadmin]' : {
				'click' : this.onStopAdmin
			},
			'agent_remote_screen_cameraGrid button[action=startadvertise]' : {
				'click' : this.onStartAdvertise
			},
			'agent_remote_screen_cameraGrid button[action=stopadvertise]' : {
				'click' : this.onStopAdvertise
			}
		});
	},
	onCellclick : function(grid, record, item, index, e, options) {
		if (e && e.getTarget().innerHTML === '下载') { // 借助事件的target来判断，这里是链接可以这样判断，其他方式亦可
			var code = record.data.code;

			var itemEl = grid.getEl();
			var iframe = itemEl.prev();
			if(iframe){
				Ext.core.Element.get(iframe).destroy();
			}
			iframe = Ext.core.DomHelper.createDom({
				tag : 'iframe',
				src : 'api/agent/screenshot/download?code=' + code,
				style : "display:none"
			});
			itemEl.insertSibling(iframe);
		}
	},
	winCloseFire : function() {
		Ext.Ajax.request({
			method : 'get',
			url : 'api/agent/screenshot/removeAvi',
			params : {
				userId : ewayUser.getName(),
				terminalId : this.terminalId
			},
			scope : this,
			callback : function() {//
			}
		});
		return true;
	},
	display : function(terminalId, ip) {
		var win = Ext.widget('agent_remote_screen_cameraView', {
			width : 1000,
			height : 600
		});

		// 窗口销毁前，需要把历史录制信息删除
		win.on("beforedestroy", this.winCloseFire, this);

		win.show();
		this.win = win;
		var me = this;
		this.terminalId = terminalId;
		this.ip = ip;
		var grid = this.getGrid();
		this.startcustom = grid.down("button[action=startcustom]");
		this.stopcustom = grid.down("button[action=stopcustom]");
		this.startadmin = grid.down("button[action=startadmin]");
		this.stopadmin = grid.down("button[action=stopadmin]");
		this.startadvertise = grid.down("button[action=startadvertise]");
		this.stopadvertise = grid.down("button[action=stopadvertise]");
		this.initDisabled();

		this.onLoad();
	},
	onLoad : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在加载数据......');
		store.load({
			params : {
				userId : ewayUser.getName(),
				terminalId : this.terminalId
			},
			scope : this,
			callback : function(r) {// 回调函数
				winEl.unmask();
				if (r && r.length > 0) { // 判断是否有数据
					for ( var int = 0; int < r.length; int++) {
						var type = r[int].data.monitorType;
						var status = r[int].data.status;
						var groupField = r[int].data.groupField;
						if (status == 1 && groupField == 'current') {
							this.setDisabled(type, 'start');
							this.code = r[int].data.code;
							break;
						}
					}
				}
			}
		});
	},
	onStartCustom : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在启动录制......');
		store.load({
			url : 'api/agent/screenshot/startCamera',
			params : {
				monitorType : 'CUSTOM',
				ip : this.ip,
				terminalId : this.terminalId,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'CUSTOM', 'start');
			}
		});
	},
	onStopCustom : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在停止录制......');
		store.load({
			url : 'api/agent/screenshot/stopCamera',
			params : {
				monitorType : 'CUSTOM',
				terminalId : this.terminalId,
				ip : this.ip,
				code : this.code,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'CUSTOM', 'stop');
			}
		});
	},
	onStartAdmin : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在启动录制......');
		store.load({
			url : 'api/agent/screenshot/startCamera',
			params : {
				monitorType : 'ADMIN',
				ip : this.ip,
				terminalId : this.terminalId,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'ADMIN', 'start');
			}
		});
	},
	onStopAdmin : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在停止录制......');
		store.load({
			url : 'api/agent/screenshot/stopCamera',
			params : {
				monitorType : 'ADMIN',
				terminalId : this.terminalId,
				ip : this.ip,
				code : this.code,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'ADMIN', 'stop');
			}
		});
	},
	onStartAdvertise : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在启动录制......');
		store.load({
			url : 'api/agent/screenshot/startCamera',
			params : {
				monitorType : 'ADVERTISE',
				ip : this.ip,
				terminalId : this.terminalId,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'ADVERTISE', 'start');
			}
		});
	},
	onStopAdvertise : function() {
		var grid = this.getGrid();
		var store = grid.getStore();
		var winEl = this.win.getEl();
		winEl.mask('正在停止录制......');
		store.load({
			url : 'api/agent/screenshot/stopCamera',
			params : {
				monitorType : 'ADVERTISE',
				terminalId : this.terminalId,
				ip : this.ip,
				code : this.code,
				userId : ewayUser.getName()
			},
			scope : this,
			callback : function(r, options, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert('请检查与设备的连接是否正常.');
					return;
				}
				this._callback(r, 'ADVERTISE', 'stop');
			}
		});
	},

	_callback : function(records, camera, type) {
		if (records && records.length > 0) { // 判断是否有数据
			var status = records[0].data.status;
			if (status == 5) {
				Eway.alert('不存在此屏幕.');
			} else if (status == 2) {
				var currUserId = records[0].data.currUserId;
				Eway.alert('该台设备已经被\"' + currUserId + '\"用户占用!');
			} else {
				this.setDisabled(camera, type);
				if (type == 'stop') {
					var code = records[0].data.code;
					this.downloadService(code);
				}
			}
			this.onLoad();
		}
	},

	downloadService : function(code) {
		var This = this;
		var winEl = this.win.getEl();
		winEl.mask('正在下载录制好的视频文件......');
		setTimeout(function() {
			Ext.Ajax.request({
				method : 'get',
				url : 'api/agent/screenshot/downloadToService',
				params : {
					code : code
				},
				callback : function() {// 回调函数
					winEl.unmask();
					This.onLoad();
				}
			});
		}, 1000);
	},

	initDisabled : function() {
		this.startcustom.setDisabled(false);
		this.stopcustom.setDisabled(true);
		this.startadmin.setDisabled(false);
		this.stopadmin.setDisabled(true);
		this.startadvertise.setDisabled(false);
		this.stopadvertise.setDisabled(true);
	},
	setDisabled : function(type, action) {
		if (action == 'start') {
			this.startcustom.setDisabled(true);
			this.stopcustom.setDisabled(type == 'CUSTOM' ? false : true);
			this.startadmin.setDisabled(true);
			this.stopadmin.setDisabled(type == 'ADMIN' ? false : true);
			this.startadvertise.setDisabled(true);
			this.stopadvertise.setDisabled(type == 'ADVERTISE' ? false : true);
		} else {
			this.startcustom.setDisabled(false);
			this.stopcustom.setDisabled(true);
			this.startadmin.setDisabled(false);
			this.stopadmin.setDisabled(true);
			this.startadvertise.setDisabled(false);
			this.stopadvertise.setDisabled(true);
		}
	}
});