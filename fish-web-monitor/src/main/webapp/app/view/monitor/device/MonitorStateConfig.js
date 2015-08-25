Ext.define('Eway.view.monitor.device.MonitorStateConfig', {
	extend : 'Ext.form.Panel',
	alias : 'widget.monitor_device_stateConfig',
	border : false,
	action : 'monitor_device_stateConfig',
	config : {
		memoryRecord : undefined
	},
	initComponent : function() {
		Ext.apply(this, {
			layout : 'border',
			bodyStyle : {
					padding: '5px 8px 5px 10px',
					'background-color' : 'white'
				},
			items : [ {
				region : 'north',
				xtype : 'fieldset',
				title : '快捷选择',
				items : {
					fieldLabel : '快捷选择',
					hideLabel : true,
					xtype : 'radiogroup',
					allowBlank : false,
					defaultType : 'radiofield',
					items : [ {
						boxLabel : '全部',
						name : 'shortcusStatus',
						inputValue : 'all'
					}, {
						boxLabel : '正常',
						name : 'shortcusStatus',
						inputValue : 'healthy'
					}, {
						boxLabel : '报警',
						name : 'shortcusStatus',
						inputValue : 'waring'
					}, {
						boxLabel : '故障',
						name : 'shortcusStatus',
						inputValue : 'fatal'
					}, {
						boxLabel : '未知',
						name : 'shortcusStatus',
						inputValue : 'unknown'
					} ],
					listeners : {
						scope : this,
						change : this.onShortcusStatus
					}
				}
			}, {
				region : 'center',
				xtype : 'checkboxgroup',
				defaultType : 'container',
				items : [ {
					columnWidth : .25,
					defaults : {
						xtype : 'checkboxfield',
						checked : true
					},
					items : [ {
						xtype : 'component',
						html : '运行状态',
						cls : 'x-form-check-group-label',
						action : 'title'
					}, {
						boxLabel : '所有',
						name : 'run_all',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'all');
							},
							'blur' : function() {
							}
						}
					},

					{
						boxLabel : '未知',
						name : 'run_unknown',
						statusGroup : 'unknown',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '初始化',
						name : 'run_initial',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '正常服务',
						name : 'run_healthy',
						statusGroup : 'healthy',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '半功能服务',
						name : 'run_subHealth',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '客户交易',
						name : 'run_customer',
						statusGroup : 'healthy',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '维护',
						name : 'run_maintain',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '厂商模式维护',
						name : 'run_vdm',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '关机',
						name : 'run_halt',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '重启',
						name : 'run_reboot',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : 'P端通讯故障',
						name : 'run_stopAtmp',
						statusGroup : 'fatal',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '人工报停',
						name : 'run_stopManmade',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '模块故障暂停服务',
						name : 'run_stopMod',
						statusGroup : 'fatal',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '未加钞暂停服务',
						name : 'run_stopUnCashIn',
						statusGroup : 'waring',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '未知原因暂停服务',
						name : 'run_stopunknown',
						statusGroup : 'unknown',
						action : 'run',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					} ]
				}, {
					columnWidth : .25,
					defaults : {
						xtype : 'checkboxfield',
						checked : true
					},
					items : [ {
						xtype : 'component',
						html : '模块状态',
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : '所有',
						name : 'module_all',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'all');
							}
						}
					},

					{
						boxLabel : '模块正常',
						name : 'module_healthy',
						statusGroup : 'healthy',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '报警',
						name : 'module_waring',
						statusGroup : 'waring',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '故障',
						name : 'module_fatal',
						statusGroup : 'fatal',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '未知',
						name : 'module_unknown',
						statusGroup : 'unknown',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '无设备',
						name : 'module_noDevice',
						statusGroup : 'unknown',
						action : 'module',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					} ]
				}, {
					columnWidth : .25,
					defaults : {
						xtype : 'checkboxfield',
						checked : true
					},
					items : [ {
						xtype : 'component',
						html : '钞箱状态',
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : '所有',
						name : 'box_all',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'all');
							}
						}
					}, {
						boxLabel : '正常',
						name : 'box_healthy',
						statusGroup : 'healthy',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '钞少',
						name : 'box_low',
						statusGroup : 'waring',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '钞空',
						name : 'box_empty',
						statusGroup : 'waring',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '存款钞将满',
						name : 'box_high',
						statusGroup : 'waring',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '存款钞满',
						name : 'box_full',
						statusGroup : 'waring',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '钞箱故障',
						name : 'box_fatal',
						statusGroup : 'fatal',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '钞箱未知',
						name : 'box_unknown',
						statusGroup : 'unknown',
						action : 'box',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					} ]
				}, {
					columnWidth : .25,
					defaults : {
						xtype : 'checkboxfield',
						checked : true
					},
					items : [ {
						xtype : 'component',
						html : '网络状态',
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : '所有',
						name : 'net_all',
						action : 'net',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'all');
							}
						}
					}, {
						boxLabel : '网络正常',
						name : 'net_healthy',
						statusGroup : 'healthy',
						action : 'net',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '网络不稳定',
						name : 'net_warning',
						statusGroup : 'waring',
						action : 'net',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '网络故障',
						name : 'net_fatal',
						statusGroup : 'fatal',
						action : 'net',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					}, {
						boxLabel : '未知',
						name : 'net_unknown',
						statusGroup : 'unknown',
						action : 'net',
						listeners : {
							scope : this,
							afterrender : function(comp) {
								// 解决chrome下focus事件不能触发
								this.bindClick(comp, 'single');
							}
						}
					} ]
				} ]
			} ]
		})
		this.callParent(arguments);
	},

	bindClick : function(comp, fn) {
		comp.getEl().on('click', function(obj, target, options) {
			var targetObj = Ext.get(target);
			if (targetObj.is('label')) {
				return;
			}
			if (fn == 'single') {
				this.handSingle(comp);
			} else if (fn == 'all') {
				this.handAll(comp);
			}
		}, this);
	},

	handAll : function(comp) {
		var action = comp.action,
			comps = this.query('checkboxfield[action="'+ action + '"]');
		for ( var i = 1; i < comps.length; i++) {
			// 解决chrome下focus事件不能触发
			comps[i].setValue(comps[0].checked);
		}
		var win = this.up('window'), button = win.down('button');
		button.focus();
	},
	handSingle : function(comp) {
		var action = comp.action,
			comps = this.query('checkboxfield[action="' + action + '"]'), 
			checked = comp.checked;

		if (!checked) {
			comps[0].setValue(false);
		} else {
			var flag = true;
			for ( var i = 1; i < comps.length; i++) {
				var com = comps[i];
				if (com != comp) {
					if (!com.checked) {
						flag = false;
						break;
					}
				}
			}
			comps[0].setValue(flag);
		}

		var win = this.up('window'), button = win.down('button');
		button.focus();
	},
	onShortcusStatus : function(comp, newValue, oldValue) {
		var value = newValue.shortcusStatus;
		var arrayCheckbox;
		if (value == 'all') {
			arrayCheckbox = this.query('checkboxfield')
		} else {
			var offCheckbox = this.query('checkboxfield');
			for ( var int = 0; int < offCheckbox.length; int++) {
				if (!offCheckbox[int].is('radiofield')) {
					offCheckbox[int].setValue(false);
				}
			}
			arrayCheckbox = this.query('checkboxfield[statusGroup="' + value
					+ '"]');
		}
		for ( var int = 0; int < arrayCheckbox.length; int++) {
			if (!arrayCheckbox[int].is('radiofield')) {
				arrayCheckbox[int].setValue(true);
			}
		}
	}
});