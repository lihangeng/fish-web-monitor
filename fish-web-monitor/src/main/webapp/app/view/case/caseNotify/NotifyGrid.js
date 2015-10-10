
Ext.define('Eway.view.case.caseNotify.NotifyGrid', {
	alias: 'widget.caseNotify_notifyGrid',
	extend: 'Eway.view.base.Grid',

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.case.Notify');
		//store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->', {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : '创建时间',
				dataIndex : 'createTime',
				width : 120
			}, {
				header : '通知内容',
				dataIndex : 'content',
				width : 200
			}, {
				header : '通知方式',
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return "短信";
	                }
					else if(value=="MAIL"){
						 return "邮件";
	                }
					else if(value=="BOTH"){
						return "短信和邮件";
					}
				},
				width : 80
			}, {
				header : '通知手机号',
				dataIndex : 'mobile',
				width : 120
			}, {
				header : 'E-Mail',
				dataIndex : 'mail',
				width : 120
			}, {
				header : '重复通知次数',
				dataIndex : 'notifyTimes',
				width : 60
			}, {
				header : '发送次数',
				dataIndex : 'sendTimes',
				width : 60
			}, {
				header : '发送时间间隔',
				dataIndex : 'sendInterval',
				width : 100
			}, {
				header : '发送时间',
				dataIndex : 'sendTime',
				width : 120
			}, {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 80
			}, {
				xtype:'actioncolumn',
				header : '故障',
				dataIndex : 'faultId',
				items : [{
					icon : 'resources/images/s_error.gif',
					tooltip: '查看详情',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('faultId');
						if(result != 0){
							return 'changeCursor';
						}
						else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var aboutInfoWin = Ext.create('Ext.window.Window', {
						    height: 350,
						    width: 400,
						    title : '故障详情',
						    layout: 'border',
						    items: [{
							    	xtype : 'form',
							    	border : 0 ,
							    	region:'center',
							    	defaults: {
										width: 400,
										labelWidth: 100,
										labelAlign: 'right',
										msgTarget : 'side'
									},
							    	items : [{
							    		fieldLabel : Eway.locale.commen.terminalId,
										xtype : 'displayfield',
										name : 'terminalId',
										allowBlank : false
									}, {
										fieldLabel : '故障模块',
										xtype : 'displayfield',
										name : 'devMod',
										allowBlank : false
									}, {
										fieldLabel : '故障分类',
										xtype : 'displayfield',
										name : 'faultClassify',
										allowBlank : false
									}, {
										fieldLabel : '故障码',
										xtype : 'displayfield',
										name : 'faultCode',
										hidden : true,
										allowBlank : false
									}, {
										fieldLabel : '厂商故障码',
										xtype : 'displayfield',
										name : 'vendorHwCode',
										allowBlank : false
									}, {
										fieldLabel : '故障开始时间',
										xtype : 'displayfield',
										name : 'faultTime',
										allowBlank : false
									}, {
										fieldLabel : '故障关闭时间',
										xtype : 'displayfield',
										name : 'closedTime',
										allowBlank : false
									}, {
										fieldLabel : '故障持续时长(单位:小时)',
										xtype : 'displayfield',
										name : 'duration',
										allowBlank : false
									}, {
										fieldLabel : '故障状态',
										xtype : 'displayfield',
										name : 'faultStatus',
										allowBlank : false
									}, {
										fieldLabel : '升级次数',
										xtype : 'displayfield',
										name : 'upgrade',
										allowBlank : false
									}]
								}]
						});
						var record = grid.getStore().getAt(rowIndex);
						var faultId = record.get('faultId');
						Ext.Ajax.request({
							method : 'POST',
							url : 'api/case/caseFault?faultId=' + faultId,
							success : function(response){
								var object = Ext.decode(response.responseText);
								if(object.success==true){
									aboutInfoWin.down('field[name="terminalId"]').setValue(object.data.terminalId);
									if(object.data.devMod=="IDC"){
										aboutInfoWin.down('field[name="devMod"]').setValue("读卡器模块");
					                }
									else if(object.data.devMod=="CIM"){
										aboutInfoWin.down('field[name="devMod"]').setValue("存款模块");
					                }
									else if(object.data.devMod=="CDM"){
										aboutInfoWin.down('field[name="devMod"]').setValue("取款模块");
									}
									else if(object.data.devMod=="RPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue("凭条打印模块");
									}
									else if(object.data.devMod=="JPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue("日志打印模块");
									}
									else if(object.data.devMod=="PIN"){
										aboutInfoWin.down('field[name="devMod"]').setValue("密码键盘模块");
									}
									else if(object.data.devMod=="TTU"){
										aboutInfoWin.down('field[name="devMod"]').setValue("文本终端单元");
									}
									else if(object.data.devMod=="SIU"){
										aboutInfoWin.down('field[name="devMod"]').setValue("传感器模块");
									}
									aboutInfoWin.down('field[name="faultClassify"]').setValue(object.data.faultClassify);
									aboutInfoWin.down('field[name="faultCode"]').setValue(object.data.faultCode);
									aboutInfoWin.down('field[name="vendorHwCode"]').setValue(object.data.vendorHwCode);
									aboutInfoWin.down('field[name="faultTime"]').setValue(object.data.faultTime);
									aboutInfoWin.down('field[name="closedTime"]').setValue(object.data.closedTime);
									aboutInfoWin.down('field[name="duration"]').setValue(object.data.duration);
									if(object.data.faultStatus=="OPEN"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue("未关闭");
					                }
									else if(object.data.faultStatus=="CLOSED"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue("已关闭");
					                }
									aboutInfoWin.down('field[name="upgrade"]').setValue(object.data.upgrade);
									aboutInfoWin.show();
								}else{
									Eway.alert('查看失败！');
								}
							},
							failure : function(){
								Eway.alert('内部错误');
							}
						})
					},
					scope : this
				}],
			}, {
				header : '银行联系人',
				dataIndex : 'bankPer'
			}, {
				header : '供应商联系人',
				dataIndex : 'serPer',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});