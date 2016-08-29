
Ext.define('Eway.view.cash.initPlan.DetailGrid', {
	alias: 'widget.initPlan_detailGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.initPlan.CashInitPlanDevice');
//		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: [{
				text:  EwayLocale.version.download.callBack,//'查询',
				glyph : 0xf122,
				action: 'toPlan',
				tooltip:EwayLocale.version.download.callBackJob,//'根据条件查询选中作业下的详情信息'
				code:'toJob'
			},'->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'cashInitPlanSeach',
				glyph : 0xf002,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,//'更改',
				action: 'update',
				code:'cashInitPlanUpdate',
				glyph : 0xf040,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [ {
				header :  EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			},  {
				header :  EwayLocale.initPlan.actualAmt,
				dataIndex : 'actualAmt',
				editor: {
	                xtype: 'numberfield',
	                allowBlank: false,
	                focus:function(_this,event,opt){
	                	me = 	_this;	
	                }
//	            },
//	            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
//	            	if(record.get("maxAmt")!=-1){
//	            		metaData.column.getEditor().maxValue=record.get("maxAmt");
//	            	}
//	            	metaData.column.getEditor().minValue=0;
//	                return value;
	            }
//			}, {
//				header :  '最大加钞额',
//				dataIndex : 'maxAmt',
//				flex : 1
			},{
				header :  EwayLocale.initPlan.adviceAmt,
				dataIndex : 'adviceAmt',
				flex : 1
			},{
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devType',
				flex : 1
			}, {
				header :  EwayLocale.machine.device.onBankSignal,
				dataIndex : 'awayFlag',
				flex : 1
			}, {
				header :  EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header :  EwayLocale.initPlan.lastAmt,
				dataIndex : 'lastAmt',
				flex : 1
			}, {
				header :  EwayLocale.initPlan.lastDate,
				dataIndex : 'lastDate',
				flex : 1
			},{
				header :  EwayLocale.machine.device.devAddress,
				dataIndex : 'address',
				flex : 1
			}],
			selModel: 'rowmodel',
		    plugins: {
		        ptype: 'rowediting',
		        clicksToEdit: 1
		    },
		});
		
		this.callParent(arguments);
	},
	showUpdate:function(){
		var has = false;
		Ext.each(Ext.fishButtons,function(code){
			if("cashBoxInfoUpdate" == code){
				has = true;
				return ;
			}
		});
		
		return has;
	}
});