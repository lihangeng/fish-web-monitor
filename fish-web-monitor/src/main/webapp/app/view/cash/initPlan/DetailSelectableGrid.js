
Ext.define('Eway.view.cash.initPlan.DetailSelectableGrid', {
	alias: 'widget.initPlan_detailSelectableGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.initPlan.CashInitPlanSelectableDevice');
		var sm = Ext.create('Ext.selection.CheckboxModel',{
			checkOnly: true,//只保留checkbox的选择能力，row选择失效
			mode:'SIMPLE',
			listeners:{
				beforeselect : function(me,record,rowIndex){
					var form = this.up('initPlan_AddDeviceWin').down('form').getForm();
					var terminalIdsField = form.findField("terminalIds");
					var deviceId = record.get("terminalId");
					if(!this.isExist(terminalIdsField.value,deviceId)){
						terminalIdsField.value = terminalIdsField.value + "," + deviceId;
					}
					return true;
				},
				deselect: function(me,record,rowIndex){
					var form = this.up('initPlan_AddDeviceWin').down('form').getForm();
					var terminalIdsField = form.findField("terminalIds");
					var deviceId = record.get("terminalId");
					this.updateDeviceIdValue(terminalIdsField,deviceId);
					return true;
				},
				scope:this
			}
		});
		Ext.apply(this, {
			initRegion : true,
			store: store,
			selModel : sm,
			multiSelect : true,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'cashInitPlanSelectableDeviceSeach',
				glyph : 0xf002
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
	                allowBlank: false
				}
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
			}]
		});
		
		this.callParent(arguments);
	},
	updateDeviceIdValue: function(terminalIdsField,deviceId){
		var arr = this.toArray(terminalIdsField.value);
		Ext.Array.remove(arr,deviceId.toString());
		var s = "";
		for(var i in arr){
			s = s + "," + arr[i];
		}
		terminalIdsField.setValue(s);
	},

	toArray : function(s){
		var d = [];
		if(!Ext.isEmpty(s)){
			d = s.substring(1).split(",");
		}
		return d;
	},

	isExist : function(terminalIds,id){
		var d = this.toArray(terminalIds);
		for(var i in d){
			if(d[i] == id){
				return true;
			}
		}
		return false;
	}
});