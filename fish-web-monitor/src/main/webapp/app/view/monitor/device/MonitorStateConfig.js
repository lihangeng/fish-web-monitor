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
				title : EwayLocale.monitor.devMonitor.fastChoose,
				items : {
					fieldLabel : EwayLocale.monitor.devMonitor.fastChoose,
					hideLabel : true,
					xtype : 'radiogroup',
					allowBlank : false,
					defaultType : 'radiofield',
					items : [ {
						boxLabel : EwayLocale.commen.all,
						name : 'shortcusStatus',
						inputValue : 'all'
					}, {
						boxLabel : EwayLocale.commen.stateDict.normal,
						name : 'shortcusStatus',
						inputValue : 'healthy'
					}, {
						boxLabel : EwayLocale.commen.warn,
						name : 'shortcusStatus',
						inputValue : 'waring'
					}, {
						boxLabel : EwayLocale.commen.fatal,
						name : 'shortcusStatus',
						inputValue : 'fatal'
					}, {
						boxLabel : EwayLocale.commen.unknow,
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
						html : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
						cls : 'x-form-check-group-label',
						action : 'title'
					}, {
						boxLabel : EwayLocale.commen.all,
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
						boxLabel : EwayLocale.commen.unknow,
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
						boxLabel : EwayLocale.monitor.devMonitor.init,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.healthy,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.halfSer,
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
						boxLabel : EwayLocale.monitor.devMonitor.accTrans,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.staff,
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
						boxLabel : EwayLocale.monitor.devMonitor.factureStaff,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.powerOff,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.restart,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.pFault,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.stopCash,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.pauseSer,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.pauseCash,
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
						boxLabel : EwayLocale.monitor.devMonitor.remote.pauseSerUnknow,
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
						html : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : EwayLocale.commen.all,
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
						boxLabel : EwayLocale.monitor.devMonitor.mod.healthy,
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
						boxLabel : EwayLocale.commen.warn,
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
						boxLabel : EwayLocale.commen.fatal,
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
						boxLabel : EwayLocale.commen.unknow,
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
						boxLabel : EwayLocale.monitor.devMonitor.noData,
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
						html : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : EwayLocale.commen.all,
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
						boxLabel : EwayLocale.commen.stateDict.normal,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.low,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.empty,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.cimAFull,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.cimFull,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.cashFault,
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
						boxLabel : EwayLocale.monitor.devMonitor.cash.cashUnknow,
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
						html : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
						cls : 'x-form-check-group-label'
					}, {
						boxLabel : EwayLocale.commen.all,
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
						boxLabel : EwayLocale.monitor.devMonitor.netHealthy,
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
						boxLabel : EwayLocale.monitor.devMonitor.netUnStable,
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
						boxLabel : EwayLocale.monitor.devMonitor.netFatal,
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
						boxLabel : EwayLocale.commen.unknow,
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