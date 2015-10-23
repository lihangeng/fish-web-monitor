
Ext.define('Eway.view.case.caseFault.FaultGrid', {
	alias: 'widget.caseFault_faultGrid',
	extend: 'Eway.view.base.Grid',

	border : false,

	requires: ['Eway.view.case.caseFault.CaseNotifyWin'],

	initComponent: function() {
		var store = Ext.create('Eway.store.case.Fault');
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->', {
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			},{
				text : Eway.locale.button.exported,
				glyph : 0xf1c3,
				action : 'export'
			}],
			columns : [{
				header : Eway.locale.commen.orgNameBelongs,
				dataIndex : 'org',
				width : 100
			},{
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 80
			}, {
				header : Eway.locale.cases.caseFault.faultModule,
				dataIndex : 'devMod',
				width : 120,
				renderer: function(value,metadata,record){
					if(value=="IDC"){
	                 	 return Eway.locale.cases.caseFault.cardReaderModule;
	                }
					else if(value=="CIM"){
						 return Eway.locale.cases.caseFault.depoistModule;
	                }
					else if(value=="CDM"){
						return Eway.locale.cases.caseFault.drawModule;
					}
					else if(value=="RPR"){
						return Eway.locale.cases.caseFault.rprModule;
					}
					else if(value=="JPR"){
						return Eway.locale.cases.caseFault.jprModule;
					}
					else if(value=="PIN"){
						return Eway.locale.cases.caseFault.pinModule;
					}
					else if(value=="TTU"){
						return Eway.locale.cases.caseFault.textTerminalUnit;
					}
					else if(value=="SIU"){
						return Eway.locale.cases.caseFault.sensoModule;
					}
					else if(value=="ISC"){
						return "身份证扫描仪模块";
					}
					else if(value=="ICC"){
						return "发卡器模块";
					}
					else if(value=="FGP"){
						return "指纹仪模块";
					}
				}
			}, {
				header : Eway.locale.cases.caseFault.faultClassify,
				dataIndex : 'faultClassify',
				width : 120
			}, {
				header : Eway.locale.cases.caseFault.faultCode,
				dataIndex : 'faultCode',
				hidden : true,
				width : 60
			}, {
				header : Eway.locale.cases.caseFault.providerFaultCode,
				dataIndex : 'vendorHwCode',
				width : 80
			}, {
				header : Eway.locale.cases.caseFault.faultStartTime,
				dataIndex : 'faultTime',
				width : 120
			}, {
				header : Eway.locale.cases.caseFault.faultCloseTime,
				dataIndex : 'closedTime',
				width : 120
			}, {
				header : Eway.locale.cases.caseFault.faultContinueTime,
				dataIndex : 'duration',
				width : 60
			}, {
				header : Eway.locale.cases.caseFault.faultState,
				dataIndex : 'faultStatus',
				width : 80,
				renderer: function(value,metadata,record){
					if(value=="OPEN"){
	                 	 return '<font color="red">'+Eway.locale.cases.caseFault.status.open+'</font>';
	                }
					else if(value=="CLOSED"){
						 return Eway.locale.cases.caseFault.status.close;
	                }
				}
			}, {
				header : Eway.locale.cases.caseFault.upgradeTimes,
				dataIndex : 'upgrade',
				width : 60
			}, {
				xtype:'actioncolumn',
				header : Eway.locale.cases.caseFault.message,
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/icon_email.gif',
					tooltip: Eway.locale.cases.caseFault.checkDetails,
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('id');
						if(result != null){
							return 'changeCursor';
						}
						else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var aboutInfoWin = Ext.create('Eway.view.case.caseFault.CaseNotifyWin');
						aboutInfoWin.show();
						var record = grid.getStore().getAt(rowIndex);
						var faultId = record.get('id');
						var store = aboutInfoWin.down("caseFault_faultNotifyGrid").getStore();
						store.cleanUrlParam();
						store.setBaseParam('faultId',faultId);
						store.load();
						scope : this
					}
				}]
			}, {
				header : Eway.locale.cases.caseFault.bankPer,
				dataIndex : 'bankPer'
			}, {
				header : Eway.locale.cases.caseFault.serPer,
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
