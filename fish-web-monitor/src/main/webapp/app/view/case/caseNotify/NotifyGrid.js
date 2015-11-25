
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : EwayLocale.cases.caseFault.createTime,
				dataIndex : 'createTime',
				width : 120
			}, {
				header :  EwayLocale.cases.caseFault.informContent,
				dataIndex : 'content',
				width : 200
			}, {
				header : EwayLocale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return EwayLocale.cases.caseFault.message;
	                }
					else if(value=="MAIL"){
						 return EwayLocale.cases.caseFault.mail;
	                }
					else if(value=="BOTH"){
						return EwayLocale.cases.caseFault.messageAndMail;
					}
				},
				width : 80
			}, {
				header : EwayLocale.cases.caseFault.informMobile,
				dataIndex : 'mobile',
				width : 120
			}, {
				header : EwayLocale.cases.caseFault.notifyRepeatTimes,
				dataIndex : 'notifyTimes',
				width : 60
			}, {
				header : EwayLocale.cases.caseFault.sendTimes,
				dataIndex : 'sendTimes',
				width : 60
			}, {
				header : EwayLocale.cases.caseFault.sendInterval,
				dataIndex : 'sendInterval',
				width : 100
			}, {
				header : EwayLocale.cases.caseFault.sendTime,
				dataIndex : 'sendTime',
				width : 120
			}, {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 80
			}, {
				xtype:'actioncolumn',
				header : EwayLocale.cases.caseNotify.fault,
				dataIndex : 'faultId',
				width : 60,
				items : [{
					icon : 'resources/images/s_error.gif',
					tooltip: EwayLocale.cases.caseFault.checkDetails,
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
						    title : EwayLocale.cases.caseNotify.faultDetails,
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
							    		fieldLabel : EwayLocale.commen.terminalId,
										xtype : 'displayfield',
										name : 'terminalId',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultModule,
										xtype : 'displayfield',
										name : 'devMod',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultClassify,
										xtype : 'displayfield',
										name : 'faultClassify',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultCode,
										xtype : 'displayfield',
										name : 'faultCode',
										hidden : true,
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.providerFaultCode,
										xtype : 'displayfield',
										name : 'vendorHwCode',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultStartTime,
										xtype : 'displayfield',
										name : 'faultTime',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultCloseTime,
										xtype : 'displayfield',
										name : 'closedTime',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseNotify.faultlastTime,
										xtype : 'displayfield',
										name : 'duration',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.faultState,
										xtype : 'displayfield',
										name : 'faultStatus',
										allowBlank : false
									}, {
										fieldLabel : EwayLocale.cases.caseFault.upgradeTimes,
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
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.IDC);
					                }
									else if(object.data.devMod=="CIM"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.CIM);
					                }
									else if(object.data.devMod=="CDM"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.CDM);
									}
									else if(object.data.devMod=="RPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.RPR);
									}
									else if(object.data.devMod=="JPR"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.JPR);
									}
									else if(object.data.devMod=="PIN"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.PIN);
									}
									else if(object.data.devMod=="TTU"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.TTU);
									}
									else if(object.data.devMod=="SIU"){
										aboutInfoWin.down('field[name="devMod"]').setValue(EwayLocale.machine.device.SIU);
									}
									aboutInfoWin.down('field[name="faultClassify"]').setValue(object.data.faultClassify);
									aboutInfoWin.down('field[name="faultCode"]').setValue(object.data.faultCode);
									aboutInfoWin.down('field[name="vendorHwCode"]').setValue(object.data.vendorHwCode);
									aboutInfoWin.down('field[name="faultTime"]').setValue(object.data.faultTime);
									aboutInfoWin.down('field[name="closedTime"]').setValue(object.data.closedTime);
									aboutInfoWin.down('field[name="duration"]').setValue(object.data.duration);
									if(object.data.faultStatus=="OPEN"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue(EwayLocale.cases.caseFault.status.open);
					                }
									else if(object.data.faultStatus=="CLOSED"){
										aboutInfoWin.down('field[name="faultStatus"]').setValue(EwayLocale.cases.caseFault.status.close);
					                }
									aboutInfoWin.down('field[name="upgrade"]').setValue(object.data.upgrade);
									aboutInfoWin.show();
								}else{
									Eway.alert(EwayLocale.cases.caseNotify.checkFailure);
								}
							},
							failure : function(){
								Eway.alert(EwayLocale.cases.caseNotify.innerFault);
							}
						})
					},
					scope : this
				}]
			}, {
				header : EwayLocale.cases.caseFault.bankPer,
				dataIndex : 'bankPer'
			}, {
				header : EwayLocale.cases.caseFault.serPer,
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