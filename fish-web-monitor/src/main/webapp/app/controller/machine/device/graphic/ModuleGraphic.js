Ext.define('Eway.controller.machine.device.graphic.ModuleGraphic', {
	extend : 'Eway.controller.base.FishController',
	views : ['Eway.view.machine.device.graphic.View'],
	stores : ['monitor.device.DeviceStatus'],
	models : ['monitor.device.DeviceStatus'],

	init : function() {
		this.control({});
	},
	display : function(record) {
		var win = Ext.widget('machine_device_graphic_view');
		var me = this;
		this.win = win;
		this.onLoadStatus(record);
		win.show();
	},
	onLoadStatus : function(record) {
		this.setIdcImg(record.data.idc);
		this.setCdmImg(record.data.cdm);
		this.setCimImg(record.data.cim);
		this.setJprImg(record.data.jpr);
		this.setRprImg(record.data.rpr);
	},

	setBoxAmt : function() {
		var DeviceBox = Ext.ModelMgr.getModel('DeviceBox');
		var ip = this.record.get('ip');
		DeviceBox.load(ip, {
					success : function(record) {

						var store = record.boxList();
						store.each(function(recordBox, index) {
									var data = recordBox.data;

								});
					}
				})
	},

	setIdcImg : function(form) {
		var status = form.status;
		if (status == 'Healthy') { // 正常
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '30px 0 0 380px',
						src : 'resources/images/atm_status/IDC_Ok.gif'
					});
		}

		if (status == 'Warning') { // 警告
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '10px 0 0 380px',
						src : 'resources/images/atm_status/IDC_Warning.gif'
					});
		}

		if (status == 'Fatal') { // 错误
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '10px 0 0 380px',
						src : 'resources/images/atm_status/IDC_Error.gif'
					});
		}
		var mediaStatus = form.media;
		if (mediaStatus == 'Present') { // 有卡
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '10px 0 0 380px',
				src : 'resources/images/atm_status/IDC_PRESENT.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC01.gif');
						}
					}
				}
			});
		}
		if (mediaStatus == 'Jammed') { // 夹卡
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '10px 0 0 380px',
				src : 'resources/images/atm_status/IDC_JAMMED.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC02.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '87px 0 0 240px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC02.gif');
						}
					}
				}
			});
		}
		var retainBinStatus = form.retainBin;
		if (retainBinStatus == 'Full') { // 回收箱满
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '10px 0 0 380px',
				src : 'resources/images/atm_status/IDC_BINFULL.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC03.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 25,
				height : 4,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '110px 0 0 204px',
				src : 'resources/images/atm_status/IDC_Red.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC03.gif');
						}
					}
				}
			});
		}
		if (retainBinStatus == 'High') { // 将满
			this.win.add({
				xtype : 'image',
				width : 25,
				height : 4,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '110px 0 0 204px',
				src : 'resources/images/atm_status/IDC_Yellow.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/IDC03.gif');
						}
					}
				}
			});
		}

		// 吞卡张数
		this.win.add({
					xtype : 'label',
					width : 25,
					height : 18,
					text : '' + form.cards,
					style : {
						position : 'absolute',
						cursor : 'pointer',
						textAlign : 'center'
					},
					margin : '92px 0 0 204px'
				});

	},
	setCimImg : function(form) {
		var status = form.status;
		if (status == 'Healthy') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '258px 0 0 25px',
						src : 'resources/images/atm_status/CIM_Ok.gif'
					});
		}
		if (status == 'Warning') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '238px 0 0 25px',
						src : 'resources/images/atm_status/CIM_Warning.gif'
					});
		}
		if (status == 'Fatal') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '238px 0 0 25px',
						src : 'resources/images/atm_status/CDM_Error.gif'
					});
		}

		var shutterStatus = form.shutter;
		if (shutterStatus == 'Jammed,Jammed') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '238px 0 0 25px',
				src : 'resources/images/atm_status/CIM_ShutterErr.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CDM01.gif');
						}
					}
				}
			});
		}
		if (shutterStatus == 'Open,Open') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '238px 0 0 25px',
				src : 'resources/images/atm_status/CIM_ShutterOPEN.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CDM02.gif');
						}
					}
				}
			});
		}

		if (shutterStatus == 'Open,Open' || shutterStatus == 'Jammed,Jammed') {
			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '240px 0 0 340px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							if (shutterStatus == 'Open,Open') {
								Ext
										.getCmp('img_help')
										.setSrc('resources/images/atm_help/CDM02.gif');
							} else {
								Ext
										.getCmp('img_help')
										.setSrc('resources/images/atm_help/CDM01.gif');
							}
						}
					}
				}
			});
		}

		var cdmTransportStatus = form.cdmTransport;
		if (cdmTransportStatus == 'Inoperable,Inoperable') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '238px 0 0 25px',
				src : 'resources/images/atm_status/CIM_TransportErr.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CIM01.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '240px 0 0 270px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CIM01.gif');
						}
					}
				}
			});
		}
	},
	setCdmImg : function(form) {
		var status = form.status;
		if (status == 'Healthy') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '150px 0 0 25px',
						src : 'resources/images/atm_status/CDM_Ok.gif'
					});
		}
		if (status == 'Warning') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '130px 0 0 25px',
						src : 'resources/images/atm_status/CDM_Warning.gif'
					});
		}
		if (status == 'Fatal') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '130px 0 0 25px',
						src : 'resources/images/atm_status/CDM_Error.gif'
					});
		}

		var shutterStatus = form.shutter;
		if (shutterStatus == 'Jammed') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '130px 0 0 25px',
				src : 'resources/images/atm_status/CDM_ShutterErr.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CDM01.gif');
						}
					}
				}
			});
		}
		if (shutterStatus == 'Open') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '130px 0 0 25px',
				src : 'resources/images/atm_status/CDM_ShutterOPEN.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/CDM02.gif');
						}
					}
				}
			});
		}

		if (shutterStatus == 'Open' || shutterStatus == 'Jammed') {
			this.win.add({
						xtype : 'image',
						width : 20,
						height : 20,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '240px 0 0 340px',
						src : 'resources/images/atm_status/Error.gif'
					});
		}

		var cdmTransportStatus = form.cdmTransport;
		if (cdmTransportStatus == 'Inoperable') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '130px 0 0 25px',
						src : 'resources/images/atm_status/CDM_TransportErr.gif'
					});

			this.win.add({
						xtype : 'image',
						width : 20,
						height : 20,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '240px 0 0 270px',
						src : 'resources/images/atm_status/Error.gif'
					});
		}

		// *******************安全门状态****************************
		var cdmSafeDoorStatus = form.safeDoor;
		if (cdmSafeDoorStatus == 'NotSupported'
				|| cdmSafeDoorStatus == 'Closed'
				|| cdmSafeDoorStatus == 'Unknown') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '360px 0 0 25px',
						src : 'resources/images/atm_status/Doors_Ok.gif'
					});
		}

		if (cdmSafeDoorStatus == 'Open') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '340px 0 0 25px',
				src : 'resources/images/atm_status/Doors_OpenInfo.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/PULL05.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 30,
				height : 205,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '315px 0 0 118px',
				src : 'resources/images/atm_status/Doors_Open.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/PULL05.gif');
						}
					}
				}
			});
		}
		// *******************安全门状态END****************************

	},
	setRprImg : function(form) {
		var status = form.status;
		if (status == 'Healthy') {

			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '140px 0 0 380px',
						src : 'resources/images/atm_status/RPR_Ok.gif'
					});
		}
		if (status == 'Warning') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '120px 0 0 380px',
						src : 'resources/images/atm_status/RPR_Warning.gif'
					});
		}
		if (status == 'Fatal') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '120px 0 0 380px',
						src : 'resources/images/atm_status/RPR_Error.gif'
					});
		}

		var supplyLevelStatus = form.supplyLevel;
		if (supplyLevelStatus == 'Low') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '120px 0 0 380px',
				src : 'resources/images/atm_status/RPR_LOW.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR01.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '193px 0 0 238px',
				src : 'resources/images/atm_status/Yellow.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR01.gif');
						}
					}
				}
			});
		}

		if (supplyLevelStatus == 'Out') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '120px 0 0 380px',
				src : 'resources/images/atm_status/RPR_OUT.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR01.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '193px 0 0 238px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR01.gif');
						}
					}
				}
			});
		}

		var binStatus = form.bin;

		if (binStatus == 'High' || binStatus == 'Full') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '120px 0 0 380px',
				src : 'resources/images/atm_status/RPR_BINFULL.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR03.gif');
						}
					}
				}
			});
		}
		if (binStatus == 'High') {
			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '200px 0 0 320px',
				src : 'resources/images/atm_status/Yellow.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR03.gif');
						}
					}
				}
			});
		} else if (binStatus == 'Full') {
			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '200px 0 0 320px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/RPR03.gif');
						}
					}
				}
			});
		}

	},
	setJprImg : function(form) {
		var status = form.status;
		if (status == 'Healthy') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 34,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '40px 0 0 25px',
						src : 'resources/images/atm_status/JPR_Ok.gif'
					});
		}
		if (status == 'Warning') {
			// this.win.add({
			// xtype : 'image',
			// width : 93,
			// height : 34,
			// style : {
			// position : 'absolute',
			// cursor : 'pointer'
			// },
			// margin : '40px 0 0 25px',
			// src : 'resources/images/atm_status/JPR_LOW.gif'
			// });
		}
		if (status == 'Fatal') {
			this.win.add({
						xtype : 'image',
						width : 93,
						height : 56,
						style : {
							position : 'absolute',
							cursor : 'pointer'
						},
						margin : '20px 0 0 25px',
						src : 'resources/images/atm_status/JPR_Error.gif'
					});
		}
		
		
		var supplyLevelStatus = form.supplyLevel;
		if (supplyLevelStatus == 'Low') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '20px 0 0 25px',
				src : 'resources/images/atm_status/JPR_LOW.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/JPR01.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '125px 0 0 213px',
				src : 'resources/images/atm_status/Yellow.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/JPR01.gif');
						}
					}
				}
			});
		}

		if (supplyLevelStatus == 'Out') {
			this.win.add({
				xtype : 'image',
				width : 93,
				height : 56,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '20px 0 0 25px',
				src : 'resources/images/atm_status/JPR_OUT.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/JPR01.gif');
						}
					}
				}
			});

			this.win.add({
				xtype : 'image',
				width : 20,
				height : 20,
				style : {
					position : 'absolute',
					cursor : 'pointer'
				},
				margin : '125px 0 0 213px',
				src : 'resources/images/atm_status/Error.gif',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							Ext
									.getCmp('img_help')
									.setSrc('resources/images/atm_help/JPR01.gif');
						}
					}
				}
			});
		}

//		var binStatus = form.bin;

//		if (binStatus == 'High' || binStatus == 'Full') {
//			this.win.add({
//				xtype : 'image',
//				width : 93,
//				height : 56,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				},
//				margin : '120px 0 0 380px',
//				src : 'resources/images/atm_status/JPR_BINFULL.gif',
//				listeners : {
//					click : {
//						element : 'el',
//						fn : function() {
//							Ext
//									.getCmp('img_help')
//									.setSrc('resources/images/atm_help/JPR03.gif');
//						}
//					}
//				}
//			});
//		}
//		if (binStatus == 'High') {
//			this.win.add({
//				xtype : 'image',
//				width : 20,
//				height : 20,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				},
//				margin : '200px 0 0 320px',
//				src : 'resources/images/atm_status/Yellow.gif',
//				listeners : {
//					click : {
//						element : 'el',
//						fn : function() {
//							Ext
//									.getCmp('img_help')
//									.setSrc('resources/images/atm_help/RPR03.gif');
//						}
//					}
//				}
//			});
//		} else if (binStatus == 'Full') {
//			this.win.add({
//				xtype : 'image',
//				width : 20,
//				height : 20,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				},
//				margin : '200px 0 0 320px',
//				src : 'resources/images/atm_status/Error.gif',
//				listeners : {
//					click : {
//						element : 'el',
//						fn : function() {
//							Ext
//									.getCmp('img_help')
//									.setSrc('resources/images/atm_help/RPR03.gif');
//						}
//					}
//				}
//			});
//		}
		
	},
	setPinImg : function(form) {

	},
	setSiuImg : function(form) {

	},
	setTtuImg : function(form) {

	},
	setBoxImg : function(form) {
		var img = this.win.down('#img_cashbox');
		if (status == 'Healthy') {
			/** 模块正常 */
			return;
		}

		if (status == 'Full') {
			/** 取款钞满正常 */
			return;
		}

		if (status == 'Unknown' || status == 'Low') {
			/** 钞少 */
			img.setSrc('resources/images/atm_status/CASHBOX_Low.gif');
			img.setHeight(56);
			img.setWidth(93);
			img.margin = "340px 0 0 25px"
			return;
		}
		if (status == 'Empty') {
			/** 钞空 */
			img.setSrc('resources/images/atm_status/CASHBOX_Empty.gif');
			img.setHeight(56);
			img.setWidth(93);
			img.margin = "340px 0 0 25px"
			return;
		}

		if (status == 'High') {
			/** 存款入钞满 */
			return;
		}

		if (status == 'Fatal') {
			/** 钞箱故障 */
			return;
		}
		if (status == 'Unknown') {
			/** 钞箱未知 */
			return;
		}

	}
});