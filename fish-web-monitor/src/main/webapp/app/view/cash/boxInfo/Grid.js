
Ext.define('Eway.view.cash.boxInfo.Grid', {
	alias: 'widget.boxInfo_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		getRowClass: function(record, rowIndex, rowParams, store){
		        return record.get("flag") ? 'user-online' : 'user-yellow';
	    }
	},
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
			}],
			columns : [ {
				menuDisabled:true,
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				width : 100
			}, {
				menuDisabled:true,
				header : EwayLocale.machine.atmGroup.ip,
				dataIndex : 'ip',
				width : 120
			}, {
				menuDisabled:true,
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'organizationName',
				width : 140
			}, {
				menuDisabled:true,
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devTypeName',
				width : 100
			},{
				menuDisabled:true,
				header : EwayLocale.machine.device.onBankSignal,
				dataIndex : 'awayFlagName',
				width : 140
			}, {
				menuDisabled:true,
				header :  EwayLocale.boxInfo.cashboxInLimit,
				dataIndex : 'maxAlarm',
				width : 140,    
				editor: {
	                xtype: 'numberfield',
	                minValue:0,
	                step:100,
	                msgTarget :'side',
	                allowBlank: false
	            }
			} , {
				menuDisabled:true,
				header :  EwayLocale.boxInfo.cashboxOutLimit,
				dataIndex : 'minAlarm',
				flex : 1,    
				editor: {
	                xtype: 'numberfield',
	                msgTarget :'side',
	                minValue:0,
	                step:100,
	                allowBlank: false
	            }
			} ,{
		        text: EwayLocale.boxInfo.ansynLimitAmt,
				flex : 1,    
				menuDisabled:true,
				align:'center',
		        code:'cashBoxInfoSync',
		        // This is our Widget column
		        xtype: 'widgetcolumn',
		        // This is the widget definition for each cell.
		        // Its "value" setting is taken from the column's dataIndex
		        widget: {
		        	xtype: 'button',
					width : 60,    
					tooltip:EwayLocale.boxInfo.ansyntooltip,
			        action:'cashBoxInfoSync',
		        	text:EwayLocale.boxInfo.ansyn
		        },
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onColumnBeforeRender
				}
		    },{
				menuDisabled:true,
		        text: EwayLocale.boxInfo.lookAtBoxInfo,
				flex : 1,    
		        code:'showBoxDetail',
		        // This is our Widget column
		        xtype: 'widgetcolumn',

				align:'center',
		        // This is the widget definition for each cell.
		        // Its "value" setting is taken from the column's dataIndex
		        widget: {
					width : 80,   
					tooltip:EwayLocale.boxInfo.boxDetailtooltip, 
		        	xtype: 'button',
			        action:'showBoxDetail',
		        	text:EwayLocale.boxInfo.boxDetail
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