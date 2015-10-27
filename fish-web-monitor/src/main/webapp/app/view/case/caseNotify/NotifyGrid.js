
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
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : Eway.locale.cases.caseFault.createTime,
				dataIndex : 'createTime',
				width : 120
			}, {
				header :  Eway.locale.cases.caseFault.informContent,
				dataIndex : 'content',
				width : 200
			}, {
				header : Eway.locale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return Eway.locale.cases.caseFault.message;
	                }
					else if(value=="MAIL"){
						 return Eway.locale.cases.caseFault.mail;
	                }
					else if(value=="BOTH"){
						return Eway.locale.cases.caseFault.messageAndMail;
					}
				},
				width : 80
			}, {
				header : Eway.locale.cases.caseFault.informMobile,
				dataIndex : 'mobile',
				width : 120
			}, {
				header : Eway.locale.cases.caseFault.notifyRepeatTimes,
				dataIndex : 'notifyTimes',
				width : 60
			}, {
				header : Eway.locale.cases.caseFault.sendTimes,
				dataIndex : 'sendTimes',
				width : 60
			}, {
				header : Eway.locale.cases.caseFault.sendInterval,
				dataIndex : 'sendInterval',
				width : 100
			}, {
				header : Eway.locale.cases.caseFault.sendTime,
				dataIndex : 'sendTime',
				width : 120
			}, {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 80
			}, {
				xtype:'actioncolumn',
				header : Eway.locale.cases.caseNotify.fault,
				dataIndex : 'faultId',
				width : 60,
				items : [{
					icon : 'resources/images/s_error.gif',
					tooltip: Eway.locale.cases.caseFault.checkDetails,
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
						    title : Eway.locale.cases.caseNotify.faultDetails,
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
										fieldLabel : Eway.locale.cases.caseFault.faultModule,
										xtype : 'displayfield',
										name : 'devMod',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.faultClassify,
										xtype : 'displayfield',
										name : 'faultClassify',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.faultCode,
										xtype : 'displayfield',
										name : 'faultCode',
										hidden : true,
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.providerFaultCode,
										xtype : 'displayfield',
										name : 'vendorHwCode',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.faultStartTime,
										xtype : 'displayfield',
										name : 'faultTime',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.faultCloseTime,
										xtype : 'displayfield',
										name : 'closedTime',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseNotify.faultlastTime,
										xtype : 'displayfield',
										name : 'duration',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.faultState,
										xtype : 'displayfield',
										name : 'faultStatus',
										allowBlank : false
									}, {
										fieldLabel : Eway.locale.cases.caseFault.upgradeTimes,
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
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.IDC);
					                }
									else if(object.data.devMod=="CIM"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.CIM);
					                }
									else if(object.data.devMod=="CDM"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.CDM);
									}
									else if(object.data.devMod=="RPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.RPR);
									}
									else if(object.data.devMod=="JPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.JPR);
									}
									else if(object.data.devMod=="PIN"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.PIN);
									}
									else if(object.data.devMod=="TTU"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.TTU);
									}
									else if(object.data.devMod=="SIU"){
										aboutInfoWin.down('field[name="devMod"]').setValue(Eway.locale.machine.device.SIU);
									}
									aboutInfoWin.down('field[name="faultClassify"]').setValue(object.data.faultClassify);
									aboutInfoWin.down('field[name="faultCode"]').setValue(object.data.faultCode);
									aboutInfoWin.down('field[name="vendorHwCode"]').setValue(object.data.vendorHwCode);
									aboutInfoWin.down('field[name="faultTime"]').setValue(object.data.faultTime);
									aboutInfoWin.down('field[name="closedTime"]').setValue(object.data.closedTime);
									aboutInfoWin.down('field[name="duration"]').setValue(object.data.duration);
									if(object.data.faultStatus=="OPEN"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue(Eway.locale.cases.caseFault.status.open);
					                }
									else if(object.data.faultStatus=="CLOSED"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue(Eway.locale.cases.caseFault.status.close);
					                }
									aboutInfoWin.down('field[name="upgrade"]').setValue(object.data.upgrade);
									aboutInfoWin.show();
								}else{
									Eway.alert(Eway.locale.cases.caseNotify.checkFailure);
								}
							},
							failure : function(){
								Eway.alert(Eway.locale.cases.caseNotify.innerFault);
							}
						})
					},
					scope : this
				}],
			}, {
				header : Eway.locale.cases.caseFault.bankPer,
				dataIndex : 'bankPer'
			}, {
				header : Eway.locale.cases.caseFault.serPer,
				dataIndex : 'serPer',
				width : 100
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});