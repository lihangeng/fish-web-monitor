
Ext.define('Eway.view.cash.initPlan.DetailGrid', {
	alias: 'widget.initPlan_detailGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		getRowClass: function(record, rowIndex, rowParams, store){
		        if(record.get("flag")==0)
		        	return 'user-online';
		        else if(record.get("flag")==1)
		        	return 'blue';
		        else
		        	return '';
	    }
	},
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.initPlan.CashInitPlanDevice');
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: [{
				text:  EwayLocale.version.download.callBack,//'返回',
				glyph : 0xf122,
				action: 'toPlan',
				tooltip:EwayLocale.version.download.callBackJob,
				code:'toJob'
			},{
				//text:  EwayLocale.version.download.beforeJob,//'查询',
				glyph : 0xf060,
				action: 'pref',
				tooltip:EwayLocale.initPlan.lastPlan,//'根据条件查询选中作业下的详情信息'
				code:'pref'
			},{
				//text:  EwayLocale.version.download.afterJob,//'查询',
				glyph : 0xf061,
				action: 'next',
				tooltip:EwayLocale.initPlan.nextPlan,//'根据条件查询选中作业下的详情信息'
				code:'next'
			},'->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'cashInitPlanDeviceSearch',
				glyph : 0xf002,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				code:'cashInitPlanDeviceAdd',
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'cashInitPlanDeviceDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.exported,
				glyph : 0xf1c3,
				action : 'export',
				code : 'cashInitPlanDeviceExport',
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
	                minValue:0,
	                step:100,
	                allowBlank: false,
	                msgTarget :'side'
	            }
			}, {
				header : EwayLocale.initPlan.maxAmt,
				dataIndex : 'strMaxAmt',
				renderer : function(value){
					if(value == -1){
						return EwayLocale.tip.unCertain;
					}
					return value;
				},
				flex : 1
			},{
				header :  EwayLocale.initPlan.adviceAmt,
				dataIndex : 'adviceAmt',
				flex : 1
			},{
				header : EwayLocale.machine.atmGroup.devTypeName,
				dataIndex : 'devType',
				flex : 1
			}, {
				header : EwayLocale.initPlan.billAmt,
				dataIndex : 'billAmt',
				flex : 1
			}, {
				header : EwayLocale.initPlan.cashInAmt,
				dataIndex : 'cashInAmt',
				flex : 1
			},{
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
			if("cashInitPlanDeviceUpdate" == code){
				has = true;
				return ;
			}
		});
		return has;
	}
});