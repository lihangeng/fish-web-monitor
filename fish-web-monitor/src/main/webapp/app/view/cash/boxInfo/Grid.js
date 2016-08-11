
Ext.define('Eway.view.cash.boxInfo.Grid', {
	alias: 'widget.boxInfo_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.boxInfo.CashBoxInfo');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'cashBoxInfoQuery',
				glyph : 0xf002,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,//'删除',
				action: 'remove',
				glyph : 0xf014,
				code : 'cashBoxInfoDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [ {
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				width : 100
			}, {
				header : EwayLocale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'organizationName',
				width : 140
			}, {
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName',
				width : 100
			},{
				header : EwayLocale.machine.device.onBankSignal,
				dataIndex : 'awayFlagName',
				width : 160
			}, {
				header :  EwayLocale.machine.atmGroup.cashboxLimit,
				dataIndex : 'maxAlarm',
				width : 120,    
				editor: {
	                xtype: 'numberfield',
	                allowBlank: false
	            }
			} , {
				header :  EwayLocale.machine.atmGroup.cashboxLimit,
				dataIndex : 'minAlarm',
				flex : 1,    
				editor: {
	                xtype: 'numberfield',
	                allowBlank: false
	            }
			} ,{
		        text: '同步预警金额',
				flex : 1,    

		        code:'cashBoxInfoSync',
		        // This is our Widget column
		        xtype: 'widgetcolumn',
	
		        // This is the widget definition for each cell.
		        // Its "value" setting is taken from the column's dataIndex
		        widget: {
		        	xtype: 'button',
			        action:'cashBoxInfoSync',
		        	text:'同步'
		        },
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onColumnBeforeRender
				}
		    },{
		        text: '查看钞箱信息',
				flex : 1,    
		        code:'showBoxDetail',
		        // This is our Widget column
		        xtype: 'widgetcolumn',
	
		        // This is the widget definition for each cell.
		        // Its "value" setting is taken from the column's dataIndex
		        widget: {
		        	xtype: 'button',
		        	text:'钞箱明细'
		        }
		    }
			],
			selModel: 'rowmodel',
		    plugins: {
		        ptype: 'rowediting',
		        clicksToEdit: 1
		    },
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	},
	showUpdate:function(){
		var has = false;
		Ext.each(Ext.fishButtons,function(code){
			if("cashBoxInfoUpdate" == code){
				has = true;
				return;
			}
		});
		
		return has;
	}
});