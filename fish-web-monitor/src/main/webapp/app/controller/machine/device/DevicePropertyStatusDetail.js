/**
 * 设备详情页面的控制层 使用方式：
 * var c = this.getController("device.Detail");
 * c.init(deviceId);//deviceId表示设备的Id
 */
Ext.define('Eway.controller.machine.device.DevicePropertyStatusDetail', {
	extend : 'Eway.controller.base.FishController',
	views : [ 'machine.device.DevicePropertyStatusDetailView' ],

	stores : [ 'monitor.device.DeviceProperty', 'monitor.device.DeviceStatus',
			'machine.atmHardSoft.AtmHardSoft', 'machine.atmHardSoft.Cpu',
			'machine.atmHardSoft.Disk', 'machine.atmHardSoft.ModuleHardVersion' ],
	models : [ 'monitor.device.DeviceProperty', 'monitor.device.DeviceStatus',
			'machine.atmHardSoft.AtmHardSoft', 'machine.atmHardSoft.Cpu',
			'machine.atmHardSoft.Disk', 'machine.atmHardSoft.ModuleHardVersion' ],

	deviceId : 0,
	ip : '',
	init : function() {
		this.control({
			'machine_device_softAndHardwareInfo' : {
				render : this.loadDeviceBase
			},
			'machine_device_spinfo' : {
				render : this.loadModuleHardVersion
			},
			"machine_device_devicemodulestatus" : {
				render : this.onLoadStatus
			},
			'machine_device_deviceModuleConfig' : {
				render : this.onLoadProperty
			}
		});
	},


	display : function(deviceId, ip, record) {
		
		var win = Ext.widget('machine_device_devicepropertystatusdetailview');
		win.setRecord(record);
		
		var me = this;
		this.win = win;
		this.deviceId = deviceId;
		this.ip = ip;
		win.setTitle(EwayLocale.machine.device.devices + deviceId + EwayLocale.machine.device.configuration);
		win.show();
	},

	// 设备模块属性信息--设备软硬件信息加载数据
	loadDeviceBase : function(tab) {
		var store = this.getMachineAtmHardSoftAtmHardSoftStore();
		var winEl = this.win.getEl();
		winEl.mask(EwayLocale.vtype.dataLoad);
		store.load({
			params : {
				terminalId : this.deviceId
			},
			callback : function(records, operation, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert(EwayLocale.vtype.devLinkNormal);
					return;
				}
				if (records && records.length > 0) { // 判断是否有数据
//					tab.getForm().setValues(records[0].data);
					var fields = tab.getForm().getFields();

					fields.each(function(item) {
						var name = item.getName();
						var value = records[0].get(name);
						if (name == 'diskMem') {
							value = (value / (1024 * 1024)).toFixed(1) + "GB";
						} else if (name == 'memorySize' || name == 'used' || name == 'free') {
							value = (value / (1024 * 1024)).toFixed(1) + "MB";
						} else if (name == 'usedPercent') {
							value = value.toFixed(1) + "%";
						}
						item.setValue(value);
					});

					// 加载cpu和disk的信息
					var tabItems = tab.query('tabpanel')[0].getLayout()
							.getLayoutItems();
					for ( var i = 0; i < tabItems.length; i++) {
						var item = tabItems[i];

						item.getStore().loadData(records[0].get(item.itemid == 'cpuItemID' ? 'cpu' : 'hardDisk'));
					}
				}
			}
		});
	},

	// 模块硬件版本信息
	loadModuleHardVersion : function(tab) {
		var store = this.getMachineAtmHardSoftModuleHardVersionStore();
		var winEl = this.win.getEl();
		winEl.mask(EwayLocale.vtype.dataLoad);
		store.load({
			params : {
				terminalId : this.deviceId,
				ip : this.ip
			},
			callback : function(records, operation, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert(EwayLocale.vtype.devLinkNormal);
					return;
				}
				if (records) { // 判断是否有数据
					if(records.length == 0){
						Eway.alert(EwayLocale.vtype.hardwayInitialize);
						return ;
					}
					if (!records[0].data.listHal) {
						Eway.alert(EwayLocale.vtype.hardwayInitialize);
						return ;
					}
					for(var i = 0; i<records[0].data.listHal.length; i++) {
						var obj = records[0].data.listHal[i];
						tab.add({
							title : obj.name,
							layout : 'column',
							border : false,
							items : [ {
								columnWidth : .5,
								layout : 'anchor',
								border : false,
								defaults : {
									anchor : '90%',
									readOnly : true,
									xtype : 'textareafield',
									height: 40
								},
								items : [ {
									fieldLabel : EwayLocale.machine.device.spVersion,
									value : obj.spVersion == 'notsupport' ? EwayLocale.machine.device.notSupport : obj.spVersion,
									style : 'margin-top:2px'
								}, {
									fieldLabel : EwayLocale.machine.device.drive,
									value : obj.driverVersion == 'notsupport' ? EwayLocale.machine.device.notSupport : obj.driverVersion
								} ]
							}, {
								columnWidth : .5,
								layout : 'anchor',
								border : false,
								defaults : {
									anchor : '90%',
									readOnly : true,
									xtype : 'textareafield',
									height: 40
								},
								items : [ {
									fieldLabel : EwayLocale.machine.device.firmway,
									value : obj.fwVersion == 'notsupport' ? EwayLocale.machine.device.notSupport : obj.fwVersion,
									style : 'margin-top:2px'
								} ]
							} ]
						});
					}
				}
			}
		});
	},

	/**加载模块配置信息**/
	onLoadProperty : function(tab) {
		var store = this.getMonitorDeviceDevicePropertyStore();
		var winEl = this.win.getEl();
		winEl.mask(EwayLocale.vtype.dataLoad);
		store.load({
			params : {
				deviceId : this.deviceId,
				ip : this.ip
			},
			scope : this,
			callback : function(records, operation, success) {
				winEl.unmask();
				if (!success) {
					Eway.alert(EwayLocale.vtype.devLinkNormal);
					return;
				}
				if(records.length == 0){
					Eway.alert(EwayLocale.vtype.hardwayInitialize);
					return ;
				}
				if (records && records.length > 0) { // 判断是否有数据
					var form = tab.down('form').getForm();
					form.setValues({
						propertyJpr : this.getDeviceModulePropertyValue(records[0].data.properties.jpr),
						propertySiu : this.getDeviceModulePropertyValue(records[0].data.properties.siu),
						propertyTtu : this.getDeviceModulePropertyValue (records[0].data.properties.ttu),
						propertyPin : this.getDeviceModulePropertyValue(records[0].data.properties.pin),
						propertyCdm : this.getDeviceModulePropertyValue(records[0].data.properties.cdm),
						propertyCim : this.getDeviceModulePropertyValue(records[0].data.properties.cim),
						propertyIdc : this.getDeviceModulePropertyValue(records[0].data.properties.idc),
						propertyRpr : this.getDeviceModulePropertyValue(records[0].data.properties.rpr),
						
						propertyIsc : this.getDeviceModulePropertyValue(records[0].data.properties.isc),
						propertyIcc : this.getDeviceModulePropertyValue(records[0].data.properties.icc),
						propertyFgp : this.getDeviceModulePropertyValue(records[0].data.properties.fgp),
						propertyCam : this.getDeviceModulePropertyValue(records[0].data.properties.cam),
						propertyBcr : this.getDeviceModulePropertyValue(records[0].data.properties.bcr),
						propertyNfc : this.getDeviceModulePropertyValue(records[0].data.properties.nfc),
						propertyPbk : this.getDeviceModulePropertyValue(records[0].data.properties.pbk),
						propertyUkr : this.getDeviceModulePropertyValue(records[0].data.properties.ukr),
						propertyUkd : this.getDeviceModulePropertyValue(records[0].data.properties.ukd)
					});
				}
				this.propertyBindClick(tab);
			}
		});
	},

	getDeviceModulePropertyValue : function(value){
		if(value == 'T'){
			return '<a href="#" class="link">'+EwayLocale.tip.own.have+'</a>';
		}else{
			return EwayLocale.tip.own.nothing;
		}
	},

	// 设备模块状态在渲染的时候加载数据
	onLoadStatus : function(tab) {
		var store = this.getMonitorDeviceDeviceStatusStore();
		var winEl = this.win.getEl();
		winEl.mask(EwayLocale.vtype.dataLoad);
		store.load({
			params : {
				deviceId : this.deviceId,
				ip : this.ip
			},
			scope : this,
			callback : function(records, operation, success) {// 回调函数
				winEl.unmask();
				if (!success) {
					Eway.alert(EwayLocale.vtype.devLinkNormal);
					return;
				}
				if(records.length == 0){
					Eway.alert(EwayLocale.vtype.hardwayInitialize);
					return ;
				}
				if (records && records.length > 0) { // 判断是否有数据
					var form = tab.down('form').getForm();
					form.setValues({
						statusJpr : this.getDeviceModuleStatusValue(records[0].data.jpr),
						statusSiu : this.getDeviceModuleStatusValue(records[0].data.siu),
						statusTtu : this.getDeviceModuleStatusValue(records[0].data.ttu),
						statusPin : this.getDeviceModuleStatusValue(records[0].data.pin),
						statusCdm : this.getDeviceModuleStatusValue(records[0].data.cdm),
						statusCim : this.getDeviceModuleStatusValue(records[0].data.cim),
						statusIdc : this.getDeviceModuleStatusValue(records[0].data.idc),
						statusRpr : this.getDeviceModuleStatusValue(records[0].data.rpr),
						
						statusBcr : this.getDeviceModuleStatusValue(records[0].data.bcr),
						statusCam : this.getDeviceModuleStatusValue(records[0].data.cam),
						
						statusIsc : this.getDeviceModuleStatusValue(records[0].data.isc),
						statusIcc : this.getDeviceModuleStatusValue(records[0].data.icc),
						statusFgp : this.getDeviceModuleStatusValue(records[0].data.fgp),
						statusNfc : this.getDeviceModuleStatusValue(records[0].data.nfc),
						statusPbk : this.getDeviceModuleStatusValue(records[0].data.pbk),
						statusUkr : this.getDeviceModuleStatusValue(records[0].data.ukr),
						statusUkd : this.getDeviceModuleStatusValue(records[0].data.ukd)
					});
					this.statusBindClick(tab);
				}
			}
		});
	},

	getDeviceModuleStatusValue : function(mod) {
		if (mod) {
			var statusName = mod.statusName;
			
			if(statusName == EwayLocale.tip.unCertain || statusName == EwayLocale.machine.device.noDevice){
				return statusName;
			}else{
				return '<a href="#" class="link">' + statusName + '</a>';
			}
		} else {
			return EwayLocale.machine.device.noDevice;
		}
	},

	/**绑定状态的单击事件*/
	statusBindClick : function(tab) {
		var card = tab.down('#statusItemId');
		var layout = card.getLayout();

		var form = tab.down('form').getForm();
		var fields = form.getFields();
		fields.each(function(item) {
			var name = item.name;
			if (item && item.getEl() && item.getEl().down('a.link')) {
				var el = item.getEl().down('a.link');
				el.on('click', function(e, htmlEl) {
					var itemid = '';
					var module = '';
					if ('statusJpr' == name) {
						itemid = 'jprid';
						module = 'jpr';
					} else if ('statusSiu' == name) {
						itemid = 'siuid';
						module = 'siu';
					} else if ('statusTtu' == name) {
						itemid = 'ttuid';
						module = 'ttu';
					}else if ('statusPin' == name) {
						itemid = 'pinid';
						module = 'pin';
					} else if ('statusCdm' == name) {
						itemid = 'cdmid';
						module = 'cdm';
					} else if ('statusCim' == name) {
						itemid = 'cimid';
						module = 'cim';
					} else if ('statusIdc' == name) {
						itemid = 'idcid';
						module = 'idc';
					} else if ('statusRpr' == name) {
						itemid = 'rprid';
						module = 'rpr';
					} else if ('statusIcc' == name) {
						itemid = 'iccid';
						module = 'icc';
					} else if ('statusFgp' == name) {
						itemid = 'fgpid';
						module = 'fgp';
					} else if ('statusPbk' == name) {
						itemid = 'pbkid';
						module = 'pbk';
					} else if ('statusIsc' == name) {
						itemid = 'iscid';
						module = 'isc';
					} else if ('statusCam' == name) {
						itemid = 'camid';
						module = 'cam';
					} else if ('statusBcr' == name) {
						itemid = 'bcrid';
						module = 'bcr';
					} else if ('statusNfc' == name) {
						itemid = 'nfcid';
						module = 'nfc';
					}else if ('statusUkr' == name) {
						itemid = 'ukrid';
						module = 'ukr';
					}else if ('statusUkd' == name) {
						itemid = 'ukdid';
						module = 'ukd';
					}
					layout.setActiveItem(itemid);
					this.setStatus(layout.getActiveItem(), module);
				}, this);
			}
		}, this);
	},

	/**模块配置的单击事件绑定*/
	propertyBindClick : function(tab) {
		var card = tab.down('#propertyItemId');
		var layout = card.getLayout();

		var form = tab.down('form').getForm();
		var fields = form.getFields();
		fields.each(function(item) {
			var name = item.name;
			if (item && item.getEl() && item.getEl().down('a.link')) {
				var el = item.getEl().down('a.link');
				el.on('click', function(e, htmlEl) {
					var itemid = '';
					var module = '';
					if ('propertyJpr' == name) {
						itemid = 'jprid';
						module = 'jpr';
					} else if ('propertySiu' == name) {
						itemid = 'siuid';
						module = 'siu';
					} else if ('propertyTtu' == name) {
						itemid = 'ttuid';
						module = 'ttu';
					}else if ('propertyPin' == name) {
						itemid = 'pinid';
						module = 'pin';
					} else if ('propertyCdm' == name) {
						itemid = 'cdmid';
						module = 'cdm';
					} else if ('propertyCim' == name) {
						itemid = 'cimid';
						module = 'cim';
					} else if ('propertyIdc' == name) {
						itemid = 'idcid';
						module = 'idc';
					} else if ('propertyRpr' == name) {
						itemid = 'rprid';
						module = 'rpr';
					} else if ('propertyIcc' == name) {
						itemid = 'iccid';
						module = 'icc';
					} else if ('propertyFgp' == name) {
						itemid = 'fgpid';
						module = 'fgp';
					} else if ('propertyPbk' == name) {
						itemid = 'pbkid';
						module = 'pbk';
					} else if ('propertyIsc' == name) {
						itemid = 'iscid';
						module = 'isc';
					} else if ('propertyCam' == name) {
						itemid = 'camid';
						module = 'cam';
					} else if ('propertyBcr' == name) {
						itemid = 'bcrid';
						module = 'bcr';
					}else if ('propertyNfc' == name) {
						itemid = 'nfcid';
						module = 'nfc';
					}else if ('propertyUkr' == name) {
						itemid = 'ukrid';
						module = 'ukr';
					}else if ('propertyUkd' == name) {
						itemid = 'ukdid';
						module = 'ukd';
					}
					layout.setActiveItem(itemid);
					this.setProperty(layout.getActiveItem(), module);
				}, this);
			}
		}, this);
	},

	setProperty : function(form, module) {
		var store = this.getMonitorDeviceDevicePropertyStore();

		if (store.getCount() == 1) {
			var fields = form.getForm().getFields();
			var record = store.getRange(0, 1)[0].get(module);
			fields.each(function(item) {
				var name = item.getName();
				var value = record[name];
				item.setValue(value == 'T' ? EwayLocale.tip.right.yes : value == 'F' ? EwayLocale.tip.right.no : value);
			});
		}
	},

	setStatus : function(form, module) {
		var store = this.getMonitorDeviceDeviceStatusStore();
		if (store.getCount() == 1) {
			var fields = form.getForm().getFields();
			var record = store.getRange(0, 1)[0].get(module);
			fields.each(function(item) {
				var name = item.getName();
				var value = record[name];
				item.setValue(value == 'notsupport' ? EwayLocale.machine.device.notSupport : value);
			});
//			form.getForm().setValues(store.getRange(0, 1)[0].get(module));
		}
	}
});