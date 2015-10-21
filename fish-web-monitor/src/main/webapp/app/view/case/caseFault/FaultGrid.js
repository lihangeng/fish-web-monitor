
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
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			},{
				text : '导出',
				iconCls :'exportToExcel',
				action : 'export'
			}],
			columns : [{
				header : '所属机构',
				dataIndex : 'org',
				width : 100
			},{
				header : '设备号',
				dataIndex : 'terminalId',
				width : 80
			}, {
				header : '故障模块',
				dataIndex : 'devMod',
				width : 120,
				renderer: function(value,metadata,record){
					if(value=="IDC"){
	                 	 return "读卡器模块";
	                }
					else if(value=="CIM"){
						 return "存款模块";
	                }
					else if(value=="CDM"){
						return "取款模块";
					}
					else if(value=="RPR"){
						return "凭条打印模块";
					}
					else if(value=="JPR"){
						return "日志打印模块";
					}
					else if(value=="PIN"){
						return "密码键盘模块";
					}
					else if(value=="TTU"){
						return "文本终端单元";
					}
					else if(value=="SIU"){
						return "传感器模块";
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
				header : '故障分类',
				dataIndex : 'faultClassify',
				width : 120
			}, {
				header : '故障码',
				dataIndex : 'faultCode',
				hidden : true,
				width : 60
			}, {
				header : '厂商故障码',
				dataIndex : 'vendorHwCode',
				width : 80
			}, {
				header : '故障开始时间',
				dataIndex : 'faultTime',
				width : 120
			}, {
				header : '故障关闭时间',
				dataIndex : 'closedTime',
				width : 120
			}, {
				header : '持续时长',
				dataIndex : 'duration',
				width : 60
			}, {
				header : '故障状态',
				dataIndex : 'faultStatus',
				width : 80,
				renderer: function(value,metadata,record){
					if(value=="OPEN"){
	                 	 return '<font color="red">未关闭</font>';
	                }
					else if(value=="CLOSED"){
						 return "已关闭";
	                }
				}
			}, {
				header : '升级次数',
				dataIndex : 'upgrade',
				width : 60
			}, {
				xtype:'actioncolumn',
				header : '短信',
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/icon_email.gif',
					tooltip: '查看详情',
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