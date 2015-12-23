
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			},{
				text : EwayLocale.button.exported,
				glyph : 0xf1c3,
				action : 'export'
			}],
			columns : [{
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'org',
				width : 100
			},{
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 80
			}, {
				header : EwayLocale.cases.caseFault.faultModule,
				dataIndex : 'devMod',
				width : 120,
				renderer: function(value,metadata,record){
					if(value=="IDC"){
	                 	 return EwayLocale.cases.caseFault.cardReaderModule;
	                }
					else if(value=="CIM"){
						 return EwayLocale.cases.caseFault.depoistModule;
	                }
					else if(value=="CDM"){
						return EwayLocale.cases.caseFault.drawModule;
					}
					else if(value=="RPR"){
						return EwayLocale.cases.caseFault.rprModule;
					}
					else if(value=="JPR"){
						return EwayLocale.cases.caseFault.jprModule;
					}
					else if(value=="PIN"){
						return EwayLocale.cases.caseFault.pinModule;
					}
					else if(value=="TTU"){
						return EwayLocale.cases.caseFault.textTerminalUnit;
					}
					else if(value=="SIU"){
						return EwayLocale.cases.caseFault.sensoModule;
					}
					else if(value=="ISC"){
						return EwayLocale.cases.caseFault.iscModule;
					}
					else if(value=="ICC"){
						return EwayLocale.cases.caseFault.iccModule;
					}
					else if(value=="FGP"){
						return EwayLocale.cases.caseFault.fgpModule;
					}
				}
			}, {
				header : EwayLocale.cases.caseFault.faultClassify,
				dataIndex : 'faultClassify',
				width : 120
			}, {
				header : EwayLocale.cases.caseFault.faultCode,
				dataIndex : 'faultCode',
				hidden : true,
				width : 60
			}, {
				header : EwayLocale.cases.caseFault.providerFaultCode,
				dataIndex : 'vendorHwCode',
				width : 160
			}, {
				header : EwayLocale.cases.caseFault.faultStartTime,
				dataIndex : 'faultTime',
				width : 120
			}, {
				header : EwayLocale.cases.caseFault.faultCloseTime,
				dataIndex : 'closedTime',
				width : 120
			}, {
				header : EwayLocale.cases.caseFault.faultContinueTime,
				dataIndex : 'duration',
				width : 85
			}, {
				header : EwayLocale.cases.caseFault.faultState,
				dataIndex : 'faultStatus',
				width : 80,
				renderer: function(value,metadata,record){
					if(value=="OPEN"){
						 metadata.tdAttr ='data-qtip="'+EwayLocale.cases.caseFault.closeByForce+'"';
	                 	 return '<a class="link" href="#">'+EwayLocale.cases.caseFault.status.open+'</a>';
	                }
					else if(value=="CLOSED"){
						 return EwayLocale.cases.caseFault.status.close;
	                }
				}
			}, {
				header : EwayLocale.cases.caseFault.upgradeTimes,
				dataIndex : 'upgrade',
				width : 120
			},{
				header : EwayLocale.cases.caseFault.faultCloseType,
				dataIndex : 'faultCloseType',
				width : 130,
				renderer: function(value,metadata,record){
				if(value=="FORCE"){
                 	 return EwayLocale.cases.caseFault.closeType.force;
                }
				else if(value=="NORMAL"){
					 return EwayLocale.cases.caseFault.closeType.normal;
                }
			}
			}, {
				xtype:'actioncolumn',
				header : EwayLocale.cases.caseFault.message,
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/icon_email.gif',
					tooltip: EwayLocale.cases.caseFault.checkDetails,
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
				header : EwayLocale.cases.caseFault.bankPer,
				dataIndex : 'bankPer'
			}, {
				header : EwayLocale.cases.caseFault.serPer,
				dataIndex : 'serPer',
				width : 120
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});
