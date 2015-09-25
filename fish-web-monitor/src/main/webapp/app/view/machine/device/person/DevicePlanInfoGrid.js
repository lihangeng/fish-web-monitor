
Ext.define('Eway.view.machine.device.person.DevicePlanInfoGrid', {
	alias: 'widget.device_planInfo_grid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlanDetailForDevice');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			autoScroll: true,
			height: 200,
			columns : [{
				header : EwayLocale.machine.device.person.week,
				flex : 1,
				dataIndex : 'week',
				renderer: function(value,metadata,record){
					if(value=="Mon"){
	                 	 return EwayLocale.machine.device.person.Mon;
	                }
					else if(value=="Tues"){
						 return EwayLocale.machine.device.person.Tues;
	                }
					else if(value=="Wed"){
						return EwayLocale.machine.device.person.Wed;
					}
					else if(value=="Thur"){
						return EwayLocale.machine.device.person.Thur;
					}
					else if(value=="Fri"){
						return EwayLocale.machine.device.person.Fri;
					}
					else if(value=="Sat"){
						return EwayLocale.machine.device.person.Sat;
					}
					else if(value=="Sun"){
						return EwayLocale.machine.device.person.Sun;
					}
				}
			},{
				header : EwayLocale.machine.device.person.openClose,
				flex : 1,
				dataIndex : 'openClose',
				renderer: function(value,metadata,record){
					if(value=="Open"){
	                 	 return EwayLocale.machine.device.person.Open;
	                }
					else if(value=="Close"){
						 return EwayLocale.machine.device.person.Close;
	                }
				}
			},{
				header :EwayLocale.commen.startDataTime,
				flex : 1,
				dataIndex : 'startTime'
			},{
				header : EwayLocale.commen.endDataTime,
				flex : 1,
				dataIndex : 'endTime'
			}]
		});
		
		this.callParent(arguments);
	},

	onReload : function(params) {
		this.getStore().load({
			params : params
		});
	}
});